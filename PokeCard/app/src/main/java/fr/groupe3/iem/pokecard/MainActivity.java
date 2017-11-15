package fr.groupe3.iem.pokecard;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Manager.ManagerJSON;


public class MainActivity extends AppCompatActivity {

    ListView listViewPokemon ;
    private List<Pokemon> list;
    ManagerJSON managerjson;
    PokemonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPokemon = (ListView) findViewById(R.id.listViewPokemon);

        list = new ArrayList<>();

        adapter = new PokemonAdapter(this, list);

        listViewPokemon.setAdapter(adapter);

        managerjson = new ManagerJSON();

        try {
            // ad - appel du Web Service pour affichage de la liste de tous les pokemon
             managerjson.ReturnAllPokemons(new MyAsyncTask().execute(adapter, "http://172.20.10.13:3000/pokedex").get(), list);
        }catch (Exception e){
            e.printStackTrace();
        }

        listViewPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    // ad - appel du Web Service pour affichage de la liste de tous les pokemon
                    //managerjson.ReturnOnePokemon(new MyAsyncTask().execute(adapter, "http://172.20.10.13:3000/pokemon/" + position + 1 ).get(), list, position);
                }catch (Exception e){
                    e.printStackTrace();
                }

                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);

                adb.setTitle("Détails du Pokémon");

                //adb.setMessage(list.get(position).getName_pokemon() + " " + list.get(position).getAbilitie());

                adb.setPositiveButton("Ok", null);

                adb.show();
            }
        });



    }

}


