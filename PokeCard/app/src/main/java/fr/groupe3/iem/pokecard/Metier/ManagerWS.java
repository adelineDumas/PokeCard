package fr.groupe3.iem.pokecard.Metier;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.internal.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.function.Predicate;

import fr.groupe3.iem.pokecard.Entities.Echange;
import fr.groupe3.iem.pokecard.Entities.Friend;
import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.PokemonDetail;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Entities.UserEchange;
import fr.groupe3.iem.pokecard.Vue.Adapter.EchangeAdapter;
import fr.groupe3.iem.pokecard.Vue.Adapter.FriendAdapter;
import fr.groupe3.iem.pokecard.Vue.Adapter.SearchAddFriendAdapter;
import fr.groupe3.iem.pokecard.Vue.Adapter.VisualisationEchangeAdapter;
import fr.groupe3.iem.pokecard.Vue.Fragment.AddFriendsFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.DetailPokemonEchangeableFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.DetailPokemonFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.EchangeFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.ListAllPokemonFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.ListFriendsFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.ListPokemonUserFragment;
import fr.groupe3.iem.pokecard.Vue.Adapter.PokemonAdapter;
import fr.groupe3.iem.pokecard.Vue.Fragment.VisualisationEchangeFragment;
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
    private List<Friend> listFriends;
    private PokemonAdapter adapterPokemon;
    private EchangeAdapter adapterechange;
    private VisualisationEchangeAdapter adaptervisuechange;
    private SearchAddFriendAdapter searchAddFriendAdapter;
    private  FriendAdapter adapterFriend;

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

    public ManagerWS(VisualisationEchangeAdapter adapter, List<Echange> pList) {
        this.adaptervisuechange = adapter;
        this.listEchange = pList;
    }

    public ManagerWS(List pList) {
        this.listEchange = pList;
        this.listFriends = pList;
    }

    public ManagerWS(SearchAddFriendAdapter adapter, List<Friend> pList) {
        this.searchAddFriendAdapter = adapter;
        this.listFriends = pList;
    }

    public ManagerWS(FriendAdapter adapter, List<Friend> pList) {
        this.adapterFriend = adapter;
        this.listFriends = pList;
    }

    public ManagerWS(){

    }

    //endregion

    //region methodes

    //region route

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

    public void getOnePokemon(int pId, final DetailPokemonEchangeableFragment callback , final LinearLayout pLoading) {
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
                callback.Refresh(listEchange);
            }
        });
    }

    public void EchangeReq(Echange echange, final VisualisationEchangeFragment callback, final LinearLayout pLoading){
        Call<List<Echange>> pokemonCall = AppPokemon.getPokemonService().EchangeReq(echange);
        pokemonCall.enqueue(new Callback<List<Echange>>() {
            @Override
            public void onResponse(Call<List<Echange>> call, Response<List<Echange>>response) {
                pLoading.setVisibility(View.GONE);
                listEchange.addAll(response.body());
                adaptervisuechange.notifyDataSetChanged();
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
    public void EchangeWith(final Context context, final Echange echange, final EchangeFragment callback){
        Call<JSONObject> pokemonCall = AppPokemon.getPokemonService().EchangeWith(new UserEchange(User.getINSTANCE().getLogin(), listEchange.get(0).getId_pokemon(), echange.getLogin_user(), echange.getId_pokemon()));
        pokemonCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.code() == 400 ) {
                    Toast.makeText(context, "L'échange n'a pas fonctionné, veuillez réessayer plus tard.", Toast.LENGTH_SHORT).show();
                }
                else {
                    AfficheDialogue("Echange réussi.",context );
                    listEchange.remove(echange);
                    callback.Refresh(listEchange);
                }
            }

            @Override
            public void onFailure(Call<JSONObject>call, Throwable t) {
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
        Call<JSONObject> pokemonCall = AppPokemon.getPokemonService().SignUp(pUser);
        pokemonCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.code() == 400 ) {
                    Toast.makeText(context, "Erreur dans la création de l'utilisateur, veuillez réessayer plus tard.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Utilisateur créé", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JSONObject>call, Throwable t) {
                AfficheDialogue("Erreur dans la création de l'utilisateur, veuillez réessayer plus tard.",context);
            }
        });
    }

    /***
     * Retourne 15 pokemons random
     * @param callback
     * @author Adeline Dumas
     */
    public void getBooster(final ListPokemonUserFragment callback, final LinearLayout pLoading){
        Call<List<Pokemon>> pokemonCall = AppPokemon.getPokemonService().GetBooster(User.getINSTANCE());
        pokemonCall.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful()) {
                    listPokemon.addAll(response.body());
                    adapterPokemon.notifyDataSetChanged();
                    pLoading.setVisibility(View.GONE);
                    callback.Refresh(listPokemon);
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                AfficheDialogue("Erreur de chargement", callback.getActivity());

            }
        });
    }

    /***
     * Retourne les utilisateurs ayant dans leur nom le texte rentré par l'utilisateur dans la searchView
     * @param pTextSearchView
     * @param callback
     * @param pLoading
     * @author Adeline Dumas
     */
    public void GetListUserSearched(String pTextSearchView, final AddFriendsFragment callback, final LinearLayout pLoading){
        Call<List<Friend>> userCall = AppPokemon.getPokemonService().GetListUserSearched(pTextSearchView);
        userCall.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                pLoading.setVisibility(View.GONE);
                listFriends = response.body();
                callback.Refresh(listFriends);
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                pLoading.setVisibility(View.GONE);
                AfficheDialogue("Erreur de chargement", callback.getActivity());
                callback.Refresh(listFriends);
            }
        });
    }

    /***
     * Retoune un certain nombre d'utilisateurs aléatoire
     * @param callback
     * @param pLoading
     * @author Adeline Dumas
     */
    public void GetListUserRandom(final AddFriendsFragment callback, final LinearLayout pLoading, User pUser){
        Call<List<Friend>> userCall = AppPokemon.getPokemonService().GetListUserRandom(pUser.getLogin());
        userCall.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                pLoading.setVisibility(View.GONE);
                listFriends = response.body();
                callback.Refresh(listFriends);
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                pLoading.setVisibility(View.GONE);
                AfficheDialogue("Erreur de chargement", callback.getActivity());
                callback.Refresh(listFriends);
            }
        });
    }

    /***
     * Retourne la liste des amis de l'utilisateur
     * @author : Adeline Dumas - 09/03/2018 - Création
     * @param pUser
     * @param callback
     * @param pLoading
     */
    public void GetListFriends(User pUser, final ListFriendsFragment callback, final LinearLayout pLoading){
        Call<List<Friend>> userCall = AppPokemon.getPokemonService().GetListFriends(pUser);
        userCall.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                pLoading.setVisibility(View.GONE);
                listFriends.addAll(response.body());
                //adapterPokemon.notifyDataSetChanged();
                callback.Refresh(listFriends);
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                pLoading.setVisibility(View.GONE);
                AfficheDialogue("Erreur de chargement", callback.getActivity());
                callback.Refresh(listFriends);
            }
        });
    }

    /***
     * Ajoute un ami
     * @author : Adeline Dumas - 09/03/2018 - Création
     * @param pFriendaAjouter
     * @param pContext
     * @param callback
     */
    public void AddFriend(final Friend pFriendaAjouter, final int pPosition , final Context pContext, final AddFriendsFragment callback){
        Call<Friend> userCall = AppPokemon.getPokemonService().AddFriend(pFriendaAjouter);
        userCall.enqueue(new Callback<Friend>() {
            @Override
            public void onResponse(Call<Friend> call, Response<Friend> response) {
                AfficheDialogue("Cet utilisateur a bien été ajouté dans vos amis.", pContext);
                listFriends.remove(pPosition);
                callback.Refresh(listFriends);
            }

            @Override
            public void onFailure(Call<Friend> call, Throwable t) {
                AfficheDialogue("Erreur de chargement", pContext);
                callback.Refresh(listFriends);
            }
        });
    }

    public void DeleteFriend(Friend pFriend, final Friend pFriendaSupprimer , final ListFriendsFragment callback, final LinearLayout pLoading){
        Call<JSONObject> userCall = AppPokemon.getPokemonService().DeleteFriend(pFriend);
        userCall.enqueue(new Callback<JSONObject> () {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                if (response.code() == 400 ) {
                    pLoading.setVisibility(View.GONE);
                    Toast.makeText(callback.getActivity(), "Erreur dans la suppression de l'ami", Toast.LENGTH_SHORT).show();
                }
                else {
                    pLoading.setVisibility(View.GONE);
                    AfficheDialogue("Cet utilisateur a bien été supprimé dans vos amis.", callback.getActivity());
                    listFriends.remove(pFriendaSupprimer);
                    callback.Refresh(listFriends);
                }

            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                pLoading.setVisibility(View.GONE);
                AfficheDialogue("Erreur dans la suppression de l'ami", callback.getActivity());
            }
        });
    }

    public void GetListPokemonSearched(String pTextSearchView, final ListAllPokemonFragment callback, final LinearLayout pLoading){
        Call<List<Pokemon>> userCall = AppPokemon.getPokemonService().GetListPokemonSearched(pTextSearchView);
        userCall.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                pLoading.setVisibility(View.GONE);
                listPokemon = response.body();
                callback.Refresh(listPokemon);
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                pLoading.setVisibility(View.GONE);
                AfficheDialogue("Erreur de chargement", callback.getActivity());
                callback.Refresh(listPokemon);
            }
        });
    }

    //endregion

    /***
     * Affiche l'Alerte Dialogue
     * @param pMessage
     * @param context
     * @author Adeline Dumas
     */
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

    //endregion


}
