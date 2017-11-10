package fr.groupe3.iem.pokecard;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConnexionActivity extends AppCompatActivity {

    private TextView textViewConnexion ;
    private TextView textViewUser;
    private TextView textViewPassword;
    private EditText editTextUser;
    private EditText editTextPassword ;
    private Button buttonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        init();
    }


    private void init(){
        textViewConnexion = (TextView) findViewById(R.id.textViewTitre);
        textViewUser = (TextView) findViewById(R.id.textViewUser);
        textViewPassword = (TextView) findViewById(R.id.textViewPassword);

        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonConnexion = (Button) findViewById(R.id.buttonConnexion);


    }
}
