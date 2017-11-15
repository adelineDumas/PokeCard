package fr.groupe3.iem.pokecard;

/**
 * Created by thomaschaboud on 21/03/2017.
 */

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;

public class MyAsyncTask extends AsyncTask<Object, Void, String> {

    private List list ;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected String doInBackground(Object... params) {

        BufferedReader in = null;
        String textReturn = "";
        Pokemon pokemon = new Pokemon();
        list = (ArrayList<Pokemon>) params[0];
        pokemonAdapter = (PokemonAdapter) params[1];
        try {
            URL url = new URL(params[2].toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader input = new BufferedReader(isr);
                String jsonStr = input.readLine();

                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray array = jsonObject.getJSONArray("pokemon_entries");
                for (int i=0 ; i<array.length(); i++){
                    JSONObject p = array.getJSONObject(i);
                    pokemon.setId_pokemon(p.getInt("entry_number"));
                    JSONObject pokemonsSpecies = p.getJSONObject("pokemon_species");
                    pokemon.setName_pokemon(pokemonsSpecies.getString("name"));
                    pokemon.setUrl_img(pokemonsSpecies.getString("url_img"));
                    list.add(pokemon);
                    pokemon = new Pokemon();
                }

                input.close();
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException je){
            je.printStackTrace();
        }

        return textReturn;

    }

    @Override
    protected void onPostExecute(String s) {
        pokemonAdapter.notifyDataSetChanged();
    }


}

