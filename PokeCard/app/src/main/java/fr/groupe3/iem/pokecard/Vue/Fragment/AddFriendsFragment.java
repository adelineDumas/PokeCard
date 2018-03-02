package fr.groupe3.iem.pokecard.Vue.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.RandomGifGenerator;
import fr.groupe3.iem.pokecard.Metier.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.Adapter.FriendAdapter;
import fr.groupe3.iem.pokecard.Vue.Adapter.PokemonAdapter;


public class AddFriendsFragment extends BaseFragment {


    //region variables

    private View v;
    private TextView textviewSearch ;
    private SearchView searchView;
    private Button buttonOk;
    private ListView listViewSearchFriends;

    private LinearLayout linearLayoutLoading;
    private ImageView imageViewloading;
    private RandomGifGenerator randomGifGenerator;

    private List ListSearchFriends;

    private FriendAdapter adapter;
    private ManagerWS managerWS;


    //endregion

    public AddFriendsFragment() {
    }

    public static AddFriendsFragment newInstance() {
        Bundle args = new Bundle();
        AddFriendsFragment fragment = new AddFriendsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer, Bundle savedInstanceState) {
        v = pInflater.inflate(R.layout.fragment_add_friends, pContainer, false);

        textviewSearch = v.findViewById(R.id.textViewRechercheAmi);
        searchView = v.findViewById(R.id.searchView);
        listViewSearchFriends = v.findViewById(R.id.listViewSearchFriend);
        buttonOk = v.findViewById(R.id.buttonOk);
        randomGifGenerator = new RandomGifGenerator();
        imageViewloading = (ImageView) v.findViewById(R.id.imageViewLoading);
        randomGifGenerator = new RandomGifGenerator();
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewloading);
        Glide.with(this).load(randomGifGenerator.ReturnUrlGif()).into(imageViewTarget);
        linearLayoutLoading = (LinearLayout) v.findViewById(R.id.loading);

        ListSearchFriends = new ArrayList<>();
        adapter = new FriendAdapter(getActivity(), ListSearchFriends);
        managerWS = new ManagerWS(adapter, ListSearchFriends);

        //managerWS.(this, linearLayoutLoading);


        return v;

    }

    public void Refresh(final List<Pokemon> pListPokemon){
        PokemonAdapter adapter  = new PokemonAdapter(getActivity(), pListPokemon);
        listViewSearchFriends = (ListView) v.findViewById(R.id.listViewSearchFriend);
        listViewSearchFriends.setAdapter(adapter);

    }


}
