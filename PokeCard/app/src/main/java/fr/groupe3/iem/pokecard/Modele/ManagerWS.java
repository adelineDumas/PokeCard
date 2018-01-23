package fr.groupe3.iem.pokecard.Modele;

import android.util.Log;

import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.PokemonDetail;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Vue.Fragment.ListPokemonFragment;
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
    private PokemonAdapter adapter;
    private boolean isFinished;

    //endregion

    //region constructeur

    public ManagerWS(PokemonAdapter adapter, List<Pokemon> pList) {
        this.adapter = adapter;
        this.listPokemon = pList;
        isFinished = false;
    }

    public ManagerWS(){

    }

    //endregion

    //region methodes

    /***
     * Permet de récupérer tous les Pokemons
     * @author : Adeline Dumas
     */
    public void getAllPokemon(final ListPokemonFragment callback) {
        Call<List<Pokemon>> pokemonCall = AppPokemon.getPokemonService().getAllPokemon();
        pokemonCall.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful()) {
                     listPokemon = response.body();
                     adapter.notifyDataSetChanged();
                     callback.Refresh(listPokemon);
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
            }
        });

    }

    /***
     * Permet de récupérer un pokemon grâce à son id
     * @param pId
     * @author : Adeline Dumas
     */
    public void getOnePokemon(int pId) {
        pokemonDetail = new PokemonDetail();
        Call<PokemonDetail> pokemonCall = AppPokemon.getPokemonService().getOnePokemon(pId);
        pokemonCall.enqueue(new Callback<PokemonDetail>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                pokemonDetail = response.body();
                adapter.notifyDataSetChanged();
                isFinished = true;
            }

            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {

            }
        });

    }

    /***
     * Permet de récupérer tous les pokemons d'un utilisateur passé en paramètre
     * @param pUser
     * @author : Adeline Dumas
     */
    public void getCollectionUser(User pUser){
        Call<List<Pokemon>> pokemonCall = AppPokemon.getPokemonService().getCollectionUser(pUser);
        pokemonCall.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>>response) {
                //Log.d("response", response.body().toString());
                listPokemon.addAll(response.body());
                //Log.d("listpokemon", listPokemon.get(0).toString());
                adapter.notifyDataSetChanged();
                isFinished = true;

            }

            @Override
            public void onFailure(Call<List<Pokemon>>call, Throwable t) {

            }
        });
    }

    public boolean isFinished(){
        if(isFinished){
            isFinished = false;
            Log.d("finished", "finish");
            return true;
        }
        return isFinished;
    }

    //endregion


}
