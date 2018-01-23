package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by iem on 15/11/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class ConnexionInternet {

    /***
     * Détermine si nous avons accès à Internet
     * @param pActivity
     * @return true si il y a internet
     * @author Thomas Chaboud
     */
    public static boolean isConnectedInternet(Activity pActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) pActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            State networkState = networkInfo.getState();
            if (networkState.compareTo(State.CONNECTED) == 0) {
                return true;
            } else return false;
        } else return false;
    }
}
