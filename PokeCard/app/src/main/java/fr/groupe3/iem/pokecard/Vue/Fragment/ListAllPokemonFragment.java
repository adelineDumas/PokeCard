package fr.groupe3.iem.pokecard.Vue.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Modele.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.PokemonAdapter;

public class ListAllPokemonFragment extends BaseFragment {

    //region variables
    private View v ;
    private ListView listViewPokemon;
    private TextView textViewTitre;
    private List<Pokemon> listPokemon;

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
        v = pInflater.inflate(R.layout.fragment_list_all_pokemon, pContainer, false);

        textViewTitre = v.findViewById(R.id.textViewTitre);
        listViewPokemon = v.findViewById(R.id.listViewPokemon);
        listPokemon = new ArrayList<>();
        adapter = new PokemonAdapter(getActivity(), listPokemon);
        managerWS = new ManagerWS(adapter, listPokemon);

        managerWS.getAllPokemon(this);
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