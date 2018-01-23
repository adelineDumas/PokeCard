package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by iem on 11/12/2017.
 */

public class PokemonDetail {

    //region variable

    private int id_pokemon;
    private String name_pokemon;
    private String abilitie1;
    private String abilitie2;
    private int height;
    private int width;
    private String type1;
    private String type2;
    /*private int base_speed;
    private int base_spe_atk;
    private int base_spe_def;
    private int base_atk;
    private int base_def;
    private int base_hp;*/
    private String url_img;

    //endregion

    //region constructeur

    public PokemonDetail(){

    }

    public PokemonDetail(int id_pokemon, String name_pokemon, String abilitie1, String abilitie2, int height, int width, String type1, String type2, String url_img) {
        this.id_pokemon = id_pokemon;
        this.name_pokemon = name_pokemon;
        this.abilitie1 = abilitie1;
        this.abilitie2 = abilitie2;
        this.height = height;
        this.width = width;
        this.type1 = type1;
        this.type2 = type2;
        this.url_img = url_img;
    }

    //endregion

    //region getters setters

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

    public String getAbilitie1() {
        return abilitie1;
    }

    public void setAbilitie1(String abilitie1) {
        this.abilitie1 = abilitie1;
    }

    public String getAbilitie2() {
        return abilitie2;
    }

    public void setAbilitie2(String abilitie2) {
        this.abilitie2 = abilitie2;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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
