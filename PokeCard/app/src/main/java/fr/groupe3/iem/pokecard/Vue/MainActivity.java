package fr.groupe3.iem.pokecard.Vue;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Modele.AppPokemon;
import fr.groupe3.iem.pokecard.Modele.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    ListView listViewPokemon ;
    private List<Pokemon> list;
    PokemonAdapter adapter;
    ManagerWS managerWS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPokemon = (ListView) findViewById(R.id.listViewPokemon);

        list = new ArrayList<>();

        adapter = new PokemonAdapter(MainActivity.this, list);

        listViewPokemon.setAdapter(adapter);

        managerWS = new ManagerWS(adapter, list);

        try {
            // ad - appel du Web Service pour affichage de la liste des Pokemons de l'user
            managerWS.getCollectionUser("toto");
            listViewPokemon.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }

        listViewPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
        });

    }


}


