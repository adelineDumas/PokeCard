package fr.groupe3.iem.pokecard.Metier;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import fr.groupe3.iem.pokecard.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iem on 15/11/2017.
 */

public class AppPokemon  extends Application{

    //region variables
    private static ServicePokemon pokemonService;
    //endregion

    //region methodes

    //region override
    @Override
    public void onCreate() {
        super.onCreate();

        /*Retrofit.Builder mBuilder =
                new Retrofit.Builder()
                        .baseUrl("http://172.20.10.13:3000/")
                        .addConverterFactory(GsonConverterFactory.create());*/


        Retrofit.Builder mBuilder =
                new Retrofit.Builder()
                        .baseUrl("http://192.168.1.14:3000/")
                        .addConverterFactory(GsonConverterFactory.create());

       /*Retrofit.Builder mBuilder =
                new Retrofit.Builder()
                        .baseUrl("http://172.31.246.176:3000/")
                        .addConverterFactory(GsonConverterFactory.create());*/

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        // log
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okBuilder.addInterceptor(logging);
        }
        okBuilder.readTimeout(1, TimeUnit.MINUTES);

        OkHttpClient httpClient = okBuilder.build();

        Retrofit retrofit = mBuilder.client(httpClient).build();
        pokemonService = retrofit.create(ServicePokemon.class);


    }

    //endregion

    public static ServicePokemon getPokemonService() {
        return pokemonService;
    }

    //endregion
}

