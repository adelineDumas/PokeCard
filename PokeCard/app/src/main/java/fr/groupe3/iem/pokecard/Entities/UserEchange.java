package fr.groupe3.iem.pokecard.Entities;

/**
 * Created by Adel on 28/01/2018.
 */

public class UserEchange {

    private String loginUser1;
    private int idPokemon1;
    private String loginUser2;
    private int idPokemon2;

    public UserEchange(String loginUser1, int idPokemon1, String loginUser2, int idPokemon2) {
        this.loginUser1 = loginUser1;
        this.idPokemon1 = idPokemon1;
        this.loginUser2 = loginUser2;
        this.idPokemon2 = idPokemon2;
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

    public int getIdPokemon1() {
        return idPokemon1;
    }

    public void setIdPokemon1(int idPokemon1) {
        this.idPokemon1 = idPokemon1;
    }

    public int getIdPokemon2() {
        return idPokemon2;
    }

    public void setIdPokemon2(int idPokemon2) {
        this.idPokemon2 = idPokemon2;
    }
}
