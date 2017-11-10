package fr.groupe3.iem.pokecard;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    Button b ;
    TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.buttonJSON);
        tv = (TextView) findViewById(R.id.textViewJSON);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BUTTON:", "OK");
                new MyAsyncTask().execute(tv, "http://172.20.10.13:3000/pokedex");
            }
        });
    }
}
