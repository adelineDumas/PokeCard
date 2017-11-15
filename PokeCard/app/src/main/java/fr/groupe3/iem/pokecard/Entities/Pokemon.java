package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by iem on 14/11/2017.
 */

public class Pokemon {
    private int id_pokemon;
    private String name_pokemon;
    private String abilitie;
    private int height;
    private int width;
    private String type1;
    private String type2;
    private int base_speed;
    private int base_spe_atk;
    private int base_spe_def;
    private int base_atk;
    private int base_def;
    private int base_hp;
    private String url_img;

    public Pokemon(){

    }

    public Pokemon(int id_pokemon, String name_pokemon, String abilities, int height, int width, String type1, String type2, int base_speed, int base_spe_atk, int base_spe_def, int base_atk, int base_def, int base_hp, String url_img) {
        this.id_pokemon = id_pokemon;
        this.name_pokemon = name_pokemon;
        this.abilitie = abilities;
        this.height = height;
        this.width = width;
        this.type1 = type1;
        this.type2 = type2;
        this.base_speed = base_speed;
        this.base_spe_atk = base_spe_atk;
        this.base_spe_def = base_spe_def;
        this.base_atk = base_atk;
        this.base_def = base_def;
        this.base_hp = base_hp;
        this.url_img = url_img;
    }

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

    public String getAbilitie() {
        return abilitie;
    }

    public void setAbilitie(String abilities) {
        this.abilitie = abilities;
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

    public int getBase_speed() {
        return base_speed;
    }

    public void setBase_speed(int base_speed) {
        this.base_speed = base_speed;
    }

    public int getBase_spe_atk() {
        return base_spe_atk;
    }

    public void setBase_spe_atk(int base_spe_atk) {
        this.base_spe_atk = base_spe_atk;
    }

    public int getBase_spe_def() {
        return base_spe_def;
    }

    public void setBase_spe_def(int base_spe_def) {
        this.base_spe_def = base_spe_def;
    }

    public int getBase_atk() {
        return base_atk;
    }

    public void setBase_atk(int base_atk) {
        this.base_atk = base_atk;
    }

    public int getBase_def() {
        return base_def;
    }

    public void setBase_def(int base_def) {
        this.base_def = base_def;
    }

    public int getBase_hp() {
        return base_hp;
    }

    public void setBase_hp(int base_hp) {
        this.base_hp = base_hp;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
}
