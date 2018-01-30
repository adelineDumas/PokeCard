package fr.groupe3.iem.pokecard.Vue;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import fr.groupe3.iem.pokecard.Entities.ConnexionInternet;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Modele.ManagerWS;
import fr.groupe3.iem.pokecard.R;

public class NewUserActivity extends AppCompatActivity {

    //region variables
    private ImageView imageViewPokecard ;
    private TextView textViewUser;
    private TextView textViewEmail;
    private TextView textViewPassword;
    private EditText editTextUser;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonCreateUser;
    private Button buttonAlreadyUser;

    //endregion


    //region methodes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        setTitle("Nouvel utilisateur");

        //ad - appel de la méthode qui initialise les élements
        init();
    }

    /***
     * Initialise les éléments de la vue
     * @author Adeline Dumas - 12/12/2017
     */
    private void init(){
        final Context context = this;
        imageViewPokecard = (ImageView) findViewById(R.id.imageViewPokecard);

        textViewUser = (TextView) findViewById(R.id.textViewUser);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewPassword = (TextView) findViewById(R.id.textViewPassword);

        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonCreateUser = (Button) findViewById(R.id.buttonCreateUSer);
        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConnexionInternet.isConnectedInternet(NewUserActivity.this)) {


                    // ad - s'il n'y a pas de nom d'utilisateur rentré : on affiche un toast
                    if (editTextUser.getText().toString().isEmpty()) {
                        Toast.makeText(NewUserActivity.this, "Pas de nom d'utilisateur", Toast.LENGTH_SHORT).show();
                    }

                    // ad - s'il n'y a pas de mot de passe de rentré : on affiche un toast
                    if (editTextPassword.getText().toString().isEmpty()) {
                        Toast.makeText(NewUserActivity.this, "Pas de mot passe", Toast.LENGTH_SHORT).show();
                    }
                    // ad - s'il n'y a pas d'email de rentré : on affiche un toast
                    if (editTextEmail.getText().toString().isEmpty()) {
                        Toast.makeText(NewUserActivity.this, "Pas d'email", Toast.LENGTH_SHORT).show();
                    }

                    // ad - s'il y a un nom d'utilisateur, un mot de passe et un email
                    // ad - s'il existe dans la BD
                    if (!editTextUser.getText().toString().isEmpty() && ! editTextPassword.getText().toString().isEmpty() && ! editTextEmail.getText().toString().isEmpty() ) {
                        new ManagerWS().Signup(new User(editTextUser.getText().toString(), editTextPassword.getText().toString(), editTextEmail.getText().toString()), context);
                        Intent intent = new Intent().setClass(NewUserActivity.this, ConnexionActivity.class);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(NewUserActivity.this, "Vous n'êtes pas connecté à internet", Toast.LENGTH_SHORT).show();
                }


            }
        });

        buttonAlreadyUser = (Button) findViewById(R.id.buttonAlreadyCompte);
        buttonAlreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(NewUserActivity.this, ConnexionActivity.class);
                startActivity(intent);
            }
        });
    }

    //endregion
}
