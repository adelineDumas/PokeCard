package fr.groupe3.iem.pokecard;

import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * Created by iem on 15/11/2017.
 */

public interface ServicePokemon {

    @GET("pokedex")
    Call<List<Pokemon>> getAllPokemon();

}
