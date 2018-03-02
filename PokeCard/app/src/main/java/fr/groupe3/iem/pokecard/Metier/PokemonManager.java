package fr.groupe3.iem.pokecard.Metier;

/**
 * Created by iem on 13/12/2017.
 */

public class PokemonManager {

    //region variable
    private static PokemonManager instance ;
    //endregion

    //region methodes
    public static PokemonManager getInstance(){
        if (instance == null){
            instance = new PokemonManager();
        }
        return  instance;
    }
    //endregion
}
