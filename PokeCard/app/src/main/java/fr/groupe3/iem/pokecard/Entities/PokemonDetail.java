package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by iem on 11/12/2017.
 */

public class PokemonDetail {

    //region variable

    private int id_pokemon;
    private String name_pokemon;
    private String ability1;
    private String ability2;
    private String height;
    private String weight;
    private String type1;
    private String type2;
    private String url_img;

    //endregion

    //region constructeur

    public PokemonDetail(){

    }

    public PokemonDetail(int id_pokemon, String name_pokemon, String ability1, String ability2, String height, String weight, String type1, String type2, String url_img) {
        this.id_pokemon = id_pokemon;
        this.name_pokemon = name_pokemon;
        this.ability1 = ability1;
        this.ability2 = ability2;
        this.height = height;
        this.weight = weight;
        this.type1 = type1;
        this.type2 = type2;
        this.url_img = url_img;
    }

    //endregion

    //region methodes

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public String getName_pokemon() {
        return name_pokemon;
    }

    public void setName_pokemon(String name_pokemon) {
        this.name_pokemon = name_pokemon;
    }

    public String getAbility1() {
        return ability1;
    }

    public void setAbility1(String ability1) {
        this.ability1 = ability1;
    }

    public String getAbility2() {
        return ability2;
    }

    public void setAbility2(String ability2) {
        this.ability2 = ability2;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }


    //endregion
}
