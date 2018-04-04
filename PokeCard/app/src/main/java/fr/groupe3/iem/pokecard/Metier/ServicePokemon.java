package fr.groupe3.iem.pokecard.Metier;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Echange;
import fr.groupe3.iem.pokecard.Entities.Friend;
import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.PokemonDetail;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Entities.UserEchange;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by iem on 15/11/2017.
 */

public interface ServicePokemon {

    @GET("pokedex")
    Call<List<Pokemon>> getAllPokemon();

    @GET("pokemon/{id_pokemon}")
    Call<PokemonDetail> getOnePokemon(@Path("id_pokemon") int id);

    /*@GET("collectionuser/{login_user}")
    Call<List<Pokemon>> getCollectionUser(@Path("login_user") String login);*/

    @POST("verifylogin")
    Call<User> verifyLogin(@Body User user);

    @POST("collectionuser")
    Call<List<Pokemon>> getCollectionUser(@Body User user);

    @POST("exchangereq")
    Call<List<Echange>> EchangeReq(@Body Echange echange);

    @POST("exchangewith")
    Call<JSONObject> EchangeWith(@Body UserEchange userEchange);

    @POST("signup")
    Call<JSONObject> SignUp(@Body User user);

    @POST("getbooster")
    Call<List<Pokemon>> GetBooster(@Body User user);

    @GET("searchuser/{login}")
    Call<List<Friend>> GetListUserSearched(@Path ("login") String pTextSearchView );

    @POST("addfriend")
    Call<Friend> AddFriend(@Body Friend friend);

    @GET("randomuser/{login}")
    Call<List<Friend>> GetListUserRandom(@Path ("login") String pUser);

    @POST("friendslist")
    Call<List<Friend>> GetListFriends(@Body User user);

    @POST("deletefriend")
    Call<JSONObject> DeleteFriend(@Body Friend friend);

    @GET("searchpkmn/{name_pokemon}")
    Call<List<Pokemon>> GetListPokemonSearched (@Path ("name_pokemon") String pPokemon);


}
