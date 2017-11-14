package fr.groupe3.iem.pokecard;

/**
 * Created by thomaschaboud on 21/03/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

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

                // ad - erreur pas un tableau le name
                JSONArray array = jsonObject.getJSONArray("name");

                Log.d("name", jsonObject.getString("name"));

                input.close();
                pokemon.setName_pokemon(array.getJSONObject(0).getString("name"));
                list.add(pokemon);
                pokemon = new Pokemon();
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

