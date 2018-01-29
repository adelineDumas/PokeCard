package fr.groupe3.iem.pokecard.Modele;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Echange;
import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.PokemonDetail;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Entities.UserEchange;
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

public class ManagerWS {

    //region variables

    private PokemonDetail pokemonDetail;
    private List<Pokemon> listPokemon;
    private List<Echange> listEchange;
    private PokemonAdapter adapterPokemon;
    private EchangeAdapter adapterechange;

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
                AfficheDialogue("Erreur de chargement", callback.getActivity());
                callback.Refresh(listEchange);
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
