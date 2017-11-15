package fr.groupe3.iem.pokecard;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;

/**
 * Created by iem on 15/11/2017.
 */

public class ManagerJSON {

    Pokemon pokemon = new Pokemon();

    /***
     * Parse le Json qui retourne tous les pokémons
     * @param pJsonStr
     * @param pList
     * @author Adeline Dumas - Création - 15/11/2017
     */
    public void ReturnAllPokemons(String pJsonStr, List<Pokemon> pList){
        try {
            JSONObject jsonObject = new JSONObject(pJsonStr);
            JSONArray array = jsonObject.getJSONArray("pokemon_entries");
            for (int i = 0; i < array.length(); i++) {
                JSONObject p = array.getJSONObject(i);
                pokemon.setId_pokemon(p.getInt("entry_number"));
                JSONObject pokemonsSpecies = p.getJSONObject("pokemon_species");
                pokemon.setName_pokemon(pokemonsSpecies.getString("name"));
                pokemon.setUrl_img(pokemonsSpecies.getString("url_img"));
                pList.add(pokemon);
                pokemon = new Pokemon();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * Parse le Json qui retourne un seul Pokémon
     * @param pJsonStr
     * @param pList
     * @param pPosition
     * @author Adeline Dumas - Création - 15/11/2017
     */
    public void ReturnOnePokemon(String pJsonStr, List<Pokemon> pList, int pPosition){
        try {
            JSONObject jsonObject = new JSONObject(pJsonStr);
            JSONArray array = jsonObject.getJSONArray("abilities");
                for (int i=0 ; i<array.length(); i++){
                    JSONObject p = array.getJSONObject(i);
                    JSONObject ability = p.getJSONObject("ability");
                    pList.get(pPosition).setAbilitie(ability.getString("name"));
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
