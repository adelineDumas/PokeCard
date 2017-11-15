package fr.groupe3.iem.pokecard;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class SplashScreen extends AppCompatActivity {

    // ad -- Splash screen timer
    private static int SPLASH_TIME_OUT = 6000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Init();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent intent = new Intent(SplashScreen.this, ConnexionActivity.class);
                startActivity(intent);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    /***
     * Initialise les élements de la vue
     * @author Adeline Dumas
     */
    private void Init(){
        ImageView imageView = (ImageView) findViewById(R.id.imageViewLoading);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.salameche).into(imageViewTarget);
    }
}



