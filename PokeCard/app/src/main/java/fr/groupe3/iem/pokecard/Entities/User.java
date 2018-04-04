package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by iem on 13/12/2017.
 */

public class User {

    //region variable

    private String login ;
    private String password;
    private String mail;
    private int points;
    private String avatar;

    //endregion

    //region constructeur

    private User(){

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, String mail) {
        this.login = login;
        this.password = password;
        this.mail = mail;
    }


    public User(String login, String password, String mail, int nbPoints) {
        this.login = login;
        this.password = password;
        this.mail = mail;

        this.points = nbPoints;
    }

    //endregion

    private static User INSTANCE = null;

    public static User getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new User();
        }
        return INSTANCE;
    }

    //region getters setters


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

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

    public int getPoints() {
        return points;
    }

//endregion
}
