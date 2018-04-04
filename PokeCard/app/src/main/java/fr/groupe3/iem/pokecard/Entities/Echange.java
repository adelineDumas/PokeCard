package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by Adel on 25/01/2018.
 */

public class Echange {

    private String login_user;
    private int id_pokemon;
    private String url;
    private String nom_pokemon;

    public Echange(String login_user, int id_pokemon) {
        this.login_user = login_user;
        this.id_pokemon = id_pokemon;
    }

    public Echange(String login_user, int id_pokemon, String nom_pokemon, String url) {
        this.login_user = login_user;
        this.id_pokemon = id_pokemon;
        this.url = url;
        this.nom_pokemon = nom_pokemon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNom_pokemon() {
        return nom_pokemon;
    }

    public void setNom_pokemon(String nom_pokemon) {
        this.nom_pokemon = nom_pokemon;
    }

    public Echange() {
    }

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_user) {
        this.login_user = login_user;
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }
}
