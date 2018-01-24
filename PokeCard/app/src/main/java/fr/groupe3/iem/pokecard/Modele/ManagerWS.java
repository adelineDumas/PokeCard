package fr.groupe3.iem.pokecard.Modele;

import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Entities.PokemonDetail;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Vue.Fragment.DetailPokemonFragment;
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
    private PokemonAdapter adapter;

    //endregion

    //region constructeur

    public ManagerWS(PokemonAdapter adapter, List<Pokemon> pList) {
        this.adapter = adapter;
        this.listPokemon = pList;
    }

    public ManagerWS(PokemonDetail pPokemonDetail) {
        this.pokemonDetail = pPokemonDetail;
    }

    public ManagerWS(){

    }

    //endregion

    //region methodes

    /***
     * Permet de récupérer tous les Pokemons
     * @author : Adeline Dumas
     */
    public void getAllPokemon(final ListAllPokemonFragment callback) {
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
    public void getOnePokemon(int pId,final DetailPokemonFragment callback ) {
        pokemonDetail = new PokemonDetail();
        Call<PokemonDetail> pokemonCall = AppPokemon.getPokemonService().getOnePokemon(pId);
        pokemonCall.enqueue(new Callback<PokemonDetail>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                pokemonDetail = response.body();
                callback.Refresh(pokemonDetail);
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
    public void getCollectionUser(User pUser, final ListPokemonUserFragment callback){
        Call<List<Pokemon>> pokemonCall = AppPokemon.getPokemonService().getCollectionUser(pUser);
        pokemonCall.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>>response) {
                listPokemon.addAll(response.body());
                adapter.notifyDataSetChanged();
                callback.Refresh(listPokemon);


            }

            @Override
            public void onFailure(Call<List<Pokemon>>call, Throwable t) {

            }
        });
    }

    //endregion


}
