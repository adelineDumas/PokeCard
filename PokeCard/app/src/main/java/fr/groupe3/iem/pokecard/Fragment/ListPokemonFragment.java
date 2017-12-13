package fr.groupe3.iem.pokecard.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Modele.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.PokemonAdapter;


public class ListPokemonFragment extends BaseFragment {

    private ListView listViewPokemon;
    private TextView textViewTitre;
    private Context context;

    private List<Pokemon> list;
    private PokemonAdapter adapter;
    private ManagerWS managerWS;


    public static ListPokemonFragment newInstance() {
        ListPokemonFragment fragment = new ListPokemonFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_pokemon, container, false);
        listViewPokemon = v.findViewById(R.id.listViewPokemon);
        textViewTitre = v.findViewById(R.id.textViewTitre);

        Refresh();

        try {
            //Intent intent = getIntent();
            //Bundle bundle = intent.getExtras();
            //if (bundle != null){
                //User user = new User(bundle.getString("Login"), bundle.getString("Password"));
                // ad - appel du Web Service pour affichage de la liste des Pokemons de l'user
                User user = new User("toto", "azerty");
                managerWS.getCollectionUser(user);
            //}

            listViewPokemon.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        listViewPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    showFragment(DetailPokemonFragment.newInstance());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }

    private void Refresh(){
        list = new ArrayList<>();

        adapter = new PokemonAdapter(ListPokemonFragment.this, list);

        listViewPokemon.setAdapter(adapter);

        managerWS = new ManagerWS(adapter, list);
    }

}

