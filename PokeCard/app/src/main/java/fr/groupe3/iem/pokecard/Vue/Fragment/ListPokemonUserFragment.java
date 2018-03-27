package fr.groupe3.iem.pokecard.Vue.Fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.RandomGifGenerator;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Metier.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.Adapter.PokemonAdapter;


public class ListPokemonUserFragment extends BaseFragment {

    //region variables
    private View v ;
    private ListView listViewPokemon;
    private TextView textViewTitre;
    private List<Pokemon> listPokemon;

    private LinearLayout linearLayoutLoading;
    private ImageView imageViewloading;
    private RandomGifGenerator randomGifGenerator;

    private PokemonAdapter adapter;
    private ManagerWS managerWS;

    //endregion

    //region methodes

    public ListPokemonUserFragment(){
    }

    public static ListPokemonUserFragment newInstance() {
        Bundle args = new Bundle();
        ListPokemonUserFragment fragment = new ListPokemonUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //region override
    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer, Bundle pSavedInstanceState) {
        v = pInflater.inflate(R.layout.fragment_list_pokemon, pContainer, false);

        textViewTitre = v.findViewById(R.id.textViewTitre);
        listViewPokemon = v.findViewById(R.id.listViewPokemon);

        //ad - initialisation du gif random
        imageViewloading = (ImageView) v.findViewById(R.id.imageViewLoading);
        randomGifGenerator = new RandomGifGenerator();
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewloading);
        Glide.with(this).load(randomGifGenerator.ReturnUrlGif()).into(imageViewTarget);
        linearLayoutLoading = (LinearLayout) v.findViewById(R.id.loading);

        listPokemon = new ArrayList<>();
        adapter = new PokemonAdapter(getActivity(), listPokemon);
        managerWS = new ManagerWS(adapter, listPokemon);


        managerWS.getCollectionUser(new User(User.getINSTANCE().getLogin(), User.getINSTANCE().getPassword()), this, linearLayoutLoading);


        return v;
    }

    //endregion

    public void Refresh(final List<Pokemon> pListPokemon){
        GestionBooster(pListPokemon);
        if (pListPokemon.size() >= 1 ) {
            textViewTitre.setText("Ma Collection");
            PokemonAdapter adapter = new PokemonAdapter(getActivity(), pListPokemon);
            listViewPokemon = (ListView) v.findViewById(R.id.listViewPokemon);
            listViewPokemon.setAdapter(adapter);

            listViewPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Bundle data = new Bundle();
                    data.putInt("id", pListPokemon.get(i).getId_pokemon());
                    showFragment(DetailPokemonFragment.newInstance(data));
                }
            });
        }
        else if(pListPokemon.size() == 0 ){
            textViewTitre.setText("Vous n'avez pas de Pokemon dans votre collection.");
        }
    }

    public void GestionBooster(List<Pokemon> pListPokemon){
        if (pListPokemon.size() == 0 ){
            Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.contentMain), "Vous avez gagné un booster !", Snackbar.LENGTH_LONG);
            snackbar.setAction("Ouvrir", new ListenerOpen(managerWS, this, linearLayoutLoading));
            snackbar.show();
        }
        if (User.getINSTANCE().getPoints() > 10){
            Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.contentMain), "Vous avez gagné un booster !", Snackbar.LENGTH_LONG);
            snackbar.setAction("Ouvrir", new ListenerOpen(managerWS, this, linearLayoutLoading));
            snackbar.show();
        }
    }

    //endregion

    private static class ListenerOpen implements View.OnClickListener{

        private ManagerWS managerWS;
        private ListPokemonUserFragment context;
        private LinearLayout linearLayout;

        public ListenerOpen(ManagerWS pManagerWS, ListPokemonUserFragment fragment, LinearLayout pLinearLayoutLoading) {
            managerWS = pManagerWS;
            context = fragment;
            linearLayout = pLinearLayoutLoading;
        }

        @Override
        public void onClick(View view) {
            linearLayout.setVisibility(View.VISIBLE);
            managerWS.getBooster(context, linearLayout);
        }
    }

}




