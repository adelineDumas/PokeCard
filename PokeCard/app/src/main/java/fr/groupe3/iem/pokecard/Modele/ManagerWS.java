package fr.groupe3.iem.pokecard.Modele;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONArray;

import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Echange;
import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.PokemonDetail;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Entities.UserEchange;
import fr.groupe3.iem.pokecard.Vue.ConnexionActivity;
import fr.groupe3.iem.pokecard.Vue.EchangeAdapter;
import fr.groupe3.iem.pokecard.Vue.Fragment.DetailPokemonFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.EchangeFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.ListAllPokemonFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.ListPokemonUserFragment;
import fr.groupe3.iem.pokecard.Vue.PokemonAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iem on 15/11/2017.
 */

public class ManagerWS extends AppCompatActivity{

    //region variables

    private PokemonDetail pokemonDetail;
    private List<Pokemon> listPokemon;
    private List<Echange> listEchange;
    private PokemonAdapter adapterPokemon;
    private EchangeAdapter adapterechange;
    private EchangeFragment callback;

    //endregion

    //region constructeur

    public ManagerWS(PokemonAdapter adapter, List<Pokemon> pList) {
        this.adapterPokemon = adapter;
        this.listPokemon = pList;
    }

    public ManagerWS(PokemonDetail pPokemonDetail) {
        this.pokemonDetail = pPokemonDetail;
    }

    public ManagerWS(EchangeAdapter adapter, List<Echange> pList) {
        this.adapterechange = adapter;
        this.listEchange = pList;
    }

    public ManagerWS(List<Echange> listEchange) {
        this.listEchange = listEchange;
    }

    public ManagerWS(){

    }

    //endregion

    //region methodes

    /***
     * Permet de récupérer tous les Pokemons
     * @param callback
     * @param pLoading
     * @author : Adeline Dumas
     */
    public void getAllPokemon(final ListAllPokemonFragment callback,final LinearLayout pLoading) {
        Call<List<Pokemon>> pokemonCall = AppPokemon.getPokemonService().getAllPokemon();
        pokemonCall.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful()) {
                     pLoading.setVisibility(View.GONE);
                     listPokemon = response.body();
                     adapterPokemon.notifyDataSetChanged();
                     callback.Refresh(listPokemon);
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                pLoading.setVisibility(View.GONE);
                AfficheDialogue("Erreur de chargement", callback.getActivity());
                callback.Refresh(listPokemon);

            }
        });

    }

    /***
     * Permet de récupérer un pokemon grâce à son id
     * @param pId
     * @param callback
     * @param pLoading
     * @author : Adeline Dumas
     */
    public void getOnePokemon(int pId,final DetailPokemonFragment callback , final LinearLayout pLoading) {
        pokemonDetail = new PokemonDetail();
        Call<PokemonDetail> pokemonCall = AppPokemon.getPokemonService().getOnePokemon(pId);
        pokemonCall.enqueue(new Callback<PokemonDetail>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                pLoading.setVisibility(View.GONE);
                pokemonDetail = response.body();
                callback.Refresh(pokemonDetail);
            }

            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {
                pLoading.setVisibility(View.GONE);
                AfficheDialogue("Erreur de chargement", callback.getActivity());
                callback.Refresh(pokemonDetail);
            }
        });

    }

    /***
     * Permet de récupérer tous les pokemons d'un utilisateur passé en paramètre
     * @param pUser
     * @param callback
     * @param pLoading
     * @author : Adeline Dumas
     */
    public void getCollectionUser(User pUser, final ListPokemonUserFragment callback, final LinearLayout pLoading){
        Call<List<Pokemon>> pokemonCall = AppPokemon.getPokemonService().getCollectionUser(pUser);
        pokemonCall.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>>response) {
                pLoading.setVisibility(View.GONE);
                listPokemon.addAll(response.body());
                adapterPokemon.notifyDataSetChanged();
                callback.Refresh(listPokemon);


            }

            @Override
            public void onFailure(Call<List<Pokemon>>call, Throwable t) {
                pLoading.setVisibility(View.GONE);
                AfficheDialogue("Erreur de chargement", callback.getActivity());
                callback.Refresh(listPokemon);
            }
        });
    }


    /***
     * Permet de récupérer d'ajouter la demande d'échange dans la BD + retourne s'il y a une demande d'achange
     * @param echange
     * @param callback
     * @param pLoading
     * @author : Adeline Dumas
     */
    public void EchangeReq(Echange echange, final EchangeFragment callback, final LinearLayout pLoading){
        this.callback = callback;
        Call<List<Echange>> pokemonCall = AppPokemon.getPokemonService().EchangeReq(echange);
        pokemonCall.enqueue(new Callback<List<Echange>>() {
            @Override
            public void onResponse(Call<List<Echange>> call, Response<List<Echange>>response) {
                pLoading.setVisibility(View.GONE);
                listEchange.addAll(response.body());
                adapterechange.notifyDataSetChanged();
                callback.Refresh(listEchange);

            }

            @Override
            public void onFailure(Call<List<Echange>>call, Throwable t) {
                pLoading.setVisibility(View.GONE);
                callback.Refresh(listEchange);
            }
        });
    }

    /***
     * Permet d'échanger les deux Pokémons
     * @param context
     * @param echange
     * @author Adeline Dumas
     */
    public void EchangeWith(final Context context, final Echange echange){
        Call<JSONArray> pokemonCall = AppPokemon.getPokemonService().EchangeWith(new UserEchange(User.getINSTANCE().getLogin(), listEchange.get(0).getId_pokemon(), echange.getLogin_user(), echange.getId_pokemon()));
        pokemonCall.enqueue(new Callback<JSONArray>() {
            @Override
            public void onResponse(Call<JSONArray> call, Response<JSONArray> response) {
                AfficheDialogue("Echange réussi.",context );
                listEchange.remove(echange);
                
            }

            @Override
            public void onFailure(Call<JSONArray>call, Throwable t) {
                AfficheDialogue("L'échange n'a pas fonctionné, veuillez réessayer plus tard.",context);
            }
        });
    }

    /***
     * Permet de créer un utilisateur
     * @param pUser
     * @param context
     * @author Adeline Dumas
     */
    public void Signup(User pUser, final Context context){
        Call<JSONArray> pokemonCall = AppPokemon.getPokemonService().SignUp(pUser);
        pokemonCall.enqueue(new Callback<JSONArray>() {
            @Override
            public void onResponse(Call<JSONArray> call, Response<JSONArray> response) {
                AfficheDialogue("Utilisateur crée", context);
            }

            @Override
            public void onFailure(Call<JSONArray>call, Throwable t) {
                AfficheDialogue("Erreur dans la création de l'utilisateur, veuillez réessayer plus tard.",context);
            }
        });
    }

    //endregion

    public void AfficheDialogue(String pMessage, Context context){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder
                .setMessage(pMessage)
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        alertDialogBuilder.show();
    }


}
