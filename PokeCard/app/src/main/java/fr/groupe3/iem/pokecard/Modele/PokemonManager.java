package fr.groupe3.iem.pokecard.Modele;

/**
 * Created by iem on 13/12/2017.
 */

public class PokemonManager {

    private static PokemonManager instance ;

    public static PokemonManager getInstance(){
        if (instance == null){
            instance = new PokemonManager();
        }
        return  instance;
    }
}
