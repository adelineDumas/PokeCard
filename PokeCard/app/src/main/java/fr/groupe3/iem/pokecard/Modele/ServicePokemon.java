package fr.groupe3.iem.pokecard.Modele;

import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.PokemonDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by iem on 15/11/2017.
 */

public interface ServicePokemon {

    @GET("pokedex")
    Call<List<Pokemon>> getAllPokemon();

    @GET("pokemon/{id}")
    Call<PokemonDetail> getOnePokemon(@Path("id_pokemon") int id);

    @GET("collectionuser/{login_user}")
    Call<List<Pokemon>> getCollectionUser(@Path("login_user") String login);

}
