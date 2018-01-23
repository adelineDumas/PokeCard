package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by iem on 13/12/2017.
 */

public class User {

    //region variable

    private String login ;
    private String password;

    //endregion

    //region constructeur
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    //endregion

    //region getters setters

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //endregion
}
