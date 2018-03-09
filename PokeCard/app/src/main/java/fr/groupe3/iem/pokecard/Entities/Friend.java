package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by iem on 02/03/2018.
 */

public class Friend {

    private String login ;
    private String login_user;
    private String avatar;

    public Friend(String login, String login_user) {
        this.login = login;
        this.login_user = login_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_friend) {
        this.login_user = login_friend;
    }
}
