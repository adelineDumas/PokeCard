package fr.groupe3.iem.pokecard.Vue.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Echange;
import fr.groupe3.iem.pokecard.Entities.RandomGifGenerator;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Metier.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.Adapter.EchangeAdapter;

/**
 * Created by Adel on 25/01/2018.
 */

public class EchangeFragment extends BaseFragment {

    //region variables
    private View v ;
    private ListView listViewEchange;
    private TextView textViewEchange;
    private LinearLayout linearLayoutLoading;
    private ImageView imageViewloading;
    private RandomGifGenerator randomGifGenerator;

    private List<Echange> listEchange;

    private EchangeAdapter adapter;
    private ManagerWS managerWS;

    //endregion

    //region methodes

    public EchangeFragment(){

    }

    public static EchangeFragment newInstance(Bundle data) {
        EchangeFragment fragment = new EchangeFragment();
        fragment.setArguments(data);
        return fragment;
    }

    public static EchangeFragment newInstance() {
        Bundle args = new Bundle();
        EchangeFragment fragment = new EchangeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //region override
    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer, Bundle pSavedInstanceState) {
        v = pInflater.inflate(R.layout.fragment_echange, pContainer, false);

        textViewEchange = v.findViewById(R.id.textViewEchange);
        listViewEchange = v.findViewById(R.id.ListViewEchange);
        imageViewloading = (ImageView) v.findViewById(R.id.imageViewLoading);
        randomGifGenerator = new RandomGifGenerator();
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewloading);
        Glide.with(this).load(randomGifGenerator.ReturnUrlGif()).into(imageViewTarget);
        linearLayoutLoading = (LinearLayout) v.findViewById(R.id.loading);

        listEchange = new ArrayList<>();
        Bundle data = getArguments();
        Echange echange = new Echange(User.getINSTANCE().getLogin(), data.getInt("id"),data.getString("nom"),data.getString("url") );
        listEchange.add(echange);
        adapter = new EchangeAdapter(getActivity(), listEchange, this, activity);
        managerWS = new ManagerWS(adapter, listEchange);

        listEchange.clear();
        managerWS.EchangeReq(echange,this, linearLayoutLoading );


        return v;
    }

    //endregion

    public void Refresh(final List<Echange> pListEchange){
        if (pListEchange.size()>= 1) {
            EchangeAdapter adapter = new EchangeAdapter(getActivity(), pListEchange, this, activity);
            listViewEchange = (ListView) v.findViewById(R.id.ListViewEchange);
            listViewEchange.setAdapter(adapter);
        }
        else if (pListEchange.size() == 0 ){
            textViewEchange.setText("Il n'y a pas d'échange proposé par d'autres utilisateurs.");
            EchangeAdapter adapter = new EchangeAdapter(getActivity(), pListEchange, this, activity);
            listViewEchange = (ListView) v.findViewById(R.id.ListViewEchange);
            listViewEchange.setAdapter(adapter);
        }
    }

    //endregion
}
