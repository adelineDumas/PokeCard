package fr.groupe3.iem.pokecard.Manager;

import java.util.List;

import fr.groupe3.iem.pokecard.AppPokemon;
import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.ServicePokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iem on 15/11/2017.
 */

public class ManagerWS {

    List<Pokemon> getAllPokemon() {
        Call<List<Pokemon>> call = AppPokemon.getPokemonService().getAllPokemon();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful()) {
                    //pokedexRetrofit = response.body();
                    //refresh(pokedexRetrofit);
                }

            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {

            }
        });
        //ad - à changer
        return null;
    }
}
