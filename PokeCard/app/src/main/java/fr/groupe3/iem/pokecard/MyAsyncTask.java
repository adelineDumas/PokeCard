package fr.groupe3.iem.pokecard;

/**
 * Created by thomaschaboud on 21/03/2017.
 */

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAsyncTask extends AsyncTask<Object, Void, String> {


    TextView tv;

    @Override
    protected String doInBackground(Object... params) {

        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        String textReturn = "";
        tv = (TextView) params[0];
        try {
            url = new URL(params[1].toString());
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

        return textReturn;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        tv.setText(s);
    }


}

