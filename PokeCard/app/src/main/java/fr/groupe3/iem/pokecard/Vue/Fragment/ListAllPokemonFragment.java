package fr.groupe3.iem.pokecard.Vue.Fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.RandomGifGenerator;
import fr.groupe3.iem.pokecard.Metier.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.Adapter.PokemonAdapter;

public class ListAllPokemonFragment extends BaseFragment {

    //region variables
    private View v ;
    private ListView listViewPokemon;
    private TextView textViewTitre;
    private List<Pokemon> listPokemon;

    private Button buttonOk;
    private SearchView searchView;

    private LinearLayout linearLayoutLoading;
    private ImageView imageViewloading;
    private RandomGifGenerator randomGifGenerator;

    private PokemonAdapter adapter;
    private ManagerWS managerWS;

    //endregion

    //region methodes


    public ListAllPokemonFragment() {
    }


    public static ListAllPokemonFragment newInstance() {
        Bundle args = new Bundle();
        ListAllPokemonFragment fragment = new ListAllPokemonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //region override
    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer, Bundle pSavedInstanceState) {
        final ListAllPokemonFragment self = this;
        v = pInflater.inflate(R.layout.fragment_list_all_pokemon, pContainer, false);

        textViewTitre = v.findViewById(R.id.textViewTitre);
        listViewPokemon = v.findViewById(R.id.listViewPokemon);
        imageViewloading = (ImageView) v.findViewById(R.id.imageViewLoading);
        searchView = v.findViewById(R.id.searchView);
        buttonOk = v.findViewById(R.id.buttonOk);
        randomGifGenerator = new RandomGifGenerator();
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewloading);
        Glide.with(this).load(randomGifGenerator.ReturnUrlGif()).into(imageViewTarget);
        linearLayoutLoading = (LinearLayout) v.findViewById(R.id.loading);

        listPokemon = new ArrayList<>();
        adapter = new PokemonAdapter(getActivity(), listPokemon);
        managerWS = new ManagerWS(adapter, listPokemon);

        managerWS.getAllPokemon(this, linearLayoutLoading);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchView.getQuery().toString().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.contentMain), "Veuillez rentrer un nom de Pokemon", Snackbar.LENGTH_INDEFINITE);
                    managerWS.getAllPokemon(self, linearLayoutLoading);
                    snackbar.show();
                }
                else {
                    managerWS.GetListPokemonSearched(searchView.getQuery().toString(), self, linearLayoutLoading);
                }
            }
        });
        return v;
    }

    //endregion

    public void Refresh(final List<Pokemon> pListPokemon){
        PokemonAdapter adapter  = new PokemonAdapter(getActivity(), pListPokemon);
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

    //endregion
}
