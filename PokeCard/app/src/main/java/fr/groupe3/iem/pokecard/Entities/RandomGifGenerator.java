package fr.groupe3.iem.pokecard.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adel on 25/01/2018.
 */

public class RandomGifGenerator {

    private List<String> listGif;

    /***
     * Initialisation de la liste des URLs pour les gifs
     * @author : Adeline Dumas
     */
    public RandomGifGenerator() {
        listGif = new ArrayList<>();
        listGif.add("https://media.giphy.com/media/PkXuh8TFTlBRK/giphy.gif");
        listGif.add("https://taranbassi.files.wordpress.com/2017/07/8f3dbfb3197f7b39bd88fe69b6b61f20.gif");
        listGif.add("https://lh3.googleusercontent.com/Zcv02iRFMYvEJAAQhh1Cde0HOrxox6WQ1EO8qvqLCiLaIxtH0uYICP2964oNr_2-VUpybmEA-Eif");
        listGif.add("https://cdn.dribbble.com/users/1505510/screenshots/3396863/browserpreview_tmp.gif");
        listGif.add("https://media.giphy.com/media/2prHSW9fHT0aY/giphy.gif");
        listGif.add("https://media.giphy.com/media/PGdd8hN2CKQYU/giphy.gif");
        listGif.add("https://media1.tenor.com/images/56ea1ee6b070799f7dd43cdea92c22f8/tenor.gif?itemid=5307711");
        listGif.add("https://media.giphy.com/media/j41gLB8pjun4c/giphy.gif");
        listGif.add("https://mir-s3-cdn-cf.behance.net/project_modules/disp/6c95a031650967.565b2c0f65a8a.gif");
        listGif.add("http://38.media.tumblr.com/72f0311f0e7603dfb7639f6864701804/tumblr_n7rhblz9cL1qlwf8co4_400.gif");
        listGif.add("https://upload.wikimedia.org/wikipedia/commons/e/e4/Bouncy_Poofer.gif");
        listGif.add("http://hitek.fr/42/evolutions-pokemon-gif-anime_1536");
        listGif.add("http://24.media.tumblr.com/tumblr_ma6oh36AHf1qh4eudo1_500.gif");
        listGif.add("https://image.noelshack.com/fichiers/2017/45/7/1510510041-42f438186b7a0fe07c97db47c80273af.gif");
        listGif.add("https://orig00.deviantart.net/776b/f/2012/324/c/3/victini___macaron_victory_dance__pokemon_animation_by_queen_of_cute-d5ll1i1.gif");

    }

    /***
     * Retourne l'url d'un gif au hasard
     * @return url
     * @author Adeline Dumas
     */
    public String ReturnUrlGif(){
        int random = (int) (Math.random() * (listGif.size() - 1 )) + 1 ;
        return listGif.get(random);
    }
}
