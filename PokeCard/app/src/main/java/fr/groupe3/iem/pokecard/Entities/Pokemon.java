package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by iem on 14/11/2017.
 */

public class Pokemon {
    private int id_pokemon;
    private String name_pokemon;
    private String login_user;
    private String url_img;

    public Pokemon(){

    }

    public Pokemon(int id_pokemon, String name_pokemon, String login_user, String url_img) {
        this.id_pokemon = id_pokemon;
        this.name_pokemon = name_pokemon;
        this.login_user = login_user;
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

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_user) {
        this.login_user = login_user;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
}
