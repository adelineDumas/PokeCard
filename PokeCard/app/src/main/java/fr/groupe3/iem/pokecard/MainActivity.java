package fr.groupe3.iem.pokecard;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listViewPokemon ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPokemon = (ListView) findViewById(R.id.listViewPokemon);

        List list = new ArrayList<Pokemon>();

        PokemonAdapter adapter = new PokemonAdapter(this, list);

        listViewPokemon.setAdapter(adapter);

        //new MyAsyncTask().execute(list, adapter, "http://172.20.10.13:3000/pokedex");
        new MyAsyncTask().execute(list, adapter, "https://pokeapi.co/api/v2/pokemon/1/");


    }
}
