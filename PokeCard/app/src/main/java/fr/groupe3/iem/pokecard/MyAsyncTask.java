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
        import java.net.URL;

public class MyAsyncTask extends AsyncTask<Object,Void,String>{



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null) {
            Log.d("Erreur :", "ok");
        } else {
            Log.d(s, "ok");
        }
    }

    @Override
    protected String doInBackground(Object... params) {
        String stringUrl = "http://192.168.43.179:3000/pokedex";

        Log.d("DoInBackground :", "ok");

        InputStream inputStream = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(stringUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            int response = conn.getResponseCode();
            if (response != 200) {
                System.out.println("Code réponse différent de 200");
                return null;
            }

            inputStream = conn.getInputStream();
            if (inputStream == null) {
                return null;
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }

            System.out.println(buffer);
            return new String(buffer);
        } catch (IOException e) {
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


}

