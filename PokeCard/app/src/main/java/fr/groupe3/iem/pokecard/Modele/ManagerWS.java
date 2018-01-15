package fr.groupe3.iem.pokecard.Modele;

import android.util.Log;
import android.widget.ListView;

import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.PokemonDetail;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Vue.PokemonAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iem on 15/11/2017.
 */

public class ManagerWS {

    private PokemonDetail pokemonDetail;
    private List<Pokemon> listPokemon;
    private PokemonAdapter adapter;

    public ManagerWS(PokemonAdapter adapter, List<Pokemon> pList) {
        this.adapter = adapter;
        this.listPokemon = pList;
    }

    public ManagerWS(){

    }

    public void getAllPokemon() {
        Call<List<Pokemon>> pokemonCall = AppPokemon.getPokemonService().getAllPokemon();
        pokemonCall.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful()) {
                     listPokemon.addAll(response.body());
                     adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
            }
        });

    }

    public void getOnePokemon(int id) {
        pokemonDetail = new PokemonDetail();
        Call<PokemonDetail> pokemonCall = AppPokemon.getPokemonService().getOnePokemon(id);
        pokemonCall.enqueue(new Callback<PokemonDetail>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                pokemonDetail = response.body();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {

            }
        });

    }

    public void getCollectionUser(User user){
        Call<List<Pokemon>> pokemonCall = AppPokemon.getPokemonService().getCollectionUser(user);
        pokemonCall.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>>response) {
                listPokemon.addAll(response.body());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Pokemon>>call, Throwable t) {

            }
        });
    }


}
