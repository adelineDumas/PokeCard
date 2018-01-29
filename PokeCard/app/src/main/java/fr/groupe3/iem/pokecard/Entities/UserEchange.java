package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by Adel on 28/01/2018.
 */

public class UserEchange {

    private String loginUser1;
    private String loginUser2;

    public UserEchange(String loginUser1, String loginUser2) {
        this.loginUser1 = loginUser1;
        this.loginUser2 = loginUser2;
    }

    public String getLoginUser1() {
        return loginUser1;
    }

    public void setLoginUser1(String loginUser1) {
        this.loginUser1 = loginUser1;
    }

    public String getLoginUser2() {
        return loginUser2;
    }

    public void setLoginUser2(String loginUser2) {
        this.loginUser2 = loginUser2;
    }
}
