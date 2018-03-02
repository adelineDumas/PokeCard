package fr.groupe3.iem.pokecard.Metier;

import org.json.JSONArray;

import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Echange;
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
    Call<JSONArray> EchangeWith(@Body UserEchange userEchange);

    @POST("signup")
    Call<JSONArray> SignUp(@Body User user);

    @POST("getbooster")
    Call<List<Pokemon>> GetBooster(@Body User user);

    @GET("searchuser")
    Call<List<Pokemon>> GetUserSearched(@Body User user);


}
