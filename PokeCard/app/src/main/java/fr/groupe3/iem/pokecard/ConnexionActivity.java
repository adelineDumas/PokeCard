package fr.groupe3.iem.pokecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConnexionActivity extends AppCompatActivity {

    private TextView textViewTitre;
    private TextView textViewUser;
    private TextView textViewPassword;
    private EditText editTextUser;
    private EditText editTextPasword;
    private Button buttonNewUser;
    private Button buttonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        init();
    }

    /***
     * Initialise les composants de la vue
     * @author : Adeline Dumas 14/11/2017 : Création
     */
    private void init(){
        textViewTitre = (TextView) findViewById(R.id.textViewTitre);
        textViewUser = (TextView) findViewById(R.id.textViewUser);
        textViewPassword = (TextView) findViewById(R.id.textViewPassword);

        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPasword = (EditText) findViewById(R.id.editTextPassword);

        buttonNewUser = (Button) findViewById(R.id.buttonnewUser);
        buttonConnexion = (Button) findViewById(R.id.buttonConnexion);

        buttonNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(ConnexionActivity.this, NewUserActivity.class);
                startActivity(intent);
            }
        });

        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(ConnexionActivity.this    , MainActivity.class);

                // ad - s'il n'y a pas de nom d'utilisateur rentré : on affiche un toast
                if(editTextUser.getText().toString().isEmpty()){
                    Toast.makeText(ConnexionActivity.this, "Pas de nom d'utilisateur", Toast.LENGTH_SHORT).show();
                }

                // ad - s'il n'y a pas de mot de passe de rentré : on affiche un toast
                if (editTextPasword.getText().toString().isEmpty()){
                    Toast.makeText(ConnexionActivity.this, "Pas de mot passe", Toast.LENGTH_SHORT).show();
                }

                // ad - s'il y a un nom d'utilisateur et un mot de passe
                // ad - à rajouter : s'il existe dans la BD
                if(!editTextUser.getText().toString().isEmpty() && !editTextPasword.getText().toString().isEmpty()){
                    startActivity(intent);
                }

            }
        });
    }
}
