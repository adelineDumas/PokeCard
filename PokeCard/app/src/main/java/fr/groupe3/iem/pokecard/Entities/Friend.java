package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by iem on 02/03/2018.
 */

public class Friend {

    private String login ;
    private String mail;
    private String avatar;

    public Friend(String login, String mail, String avatar) {
        this.login = login;
        this.mail = mail;
        this.avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
