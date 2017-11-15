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

    private PokemonAdapter pokemonAdapter;

    @Override
    protected String doInBackground(Object... params) {

        BufferedReader in = null;
        String jsonStr = "";

        pokemonAdapter = (PokemonAdapter) params[0];
        try {
            URL url = new URL(params[1].toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader input = new BufferedReader(isr);
                jsonStr = input.readLine();

                input.close();
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonStr;

    }

    @Override
    protected void onPostExecute(String s) {
        pokemonAdapter.notifyDataSetChanged();
        super.onPostExecute(s);
    }


}

