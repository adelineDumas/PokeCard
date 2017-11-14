package fr.groupe3.iem.pokecard;

/**
 * Created by thomaschaboud on 21/03/2017.
 */

import android.os.AsyncTask;

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

        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        String textReturn = "";
        list = (ArrayList<Pokemon>) params[0];
        pokemonAdapter = (PokemonAdapter) params[1];
        try {
            url = new URL(params[2].toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                                String temp = "";
                while ((temp = in.readLine()) != null) {
                    textReturn += temp;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.add(textReturn);
        return textReturn;

    }

    @Override
    protected void onPostExecute(String s) {
        pokemonAdapter.notifyDataSetChanged();
    }


}

