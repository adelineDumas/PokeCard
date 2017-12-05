package fr.groupe3.iem.pokecard;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import fr.groupe3.iem.pokecard.Entities.ConnexionInternet;

public class ConnexionActivity extends AppCompatActivity {

    private TextView textViewTitre;
    private TextView textViewUser;
    private TextView textViewPassword;
    private EditText editTextUser;
    private EditText editTextPasword;
    private Button buttonNewUser;
    private Button buttonConnexion;
    private LoginButton buttonConnexionFB;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        init();

        if(!(ConnexionInternet.isConnectedInternet(ConnexionActivity.this))){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setMessage("Vous n'êtes pas connecté à internet, veuillez vérifier votre connexion")
                    .setCancelable(true)
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {}
                    });
            alertDialogBuilder.show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 64206){
            Intent intent = new Intent().setClass(ConnexionActivity.this    , MainActivity.class);
            startActivity(intent);
        }
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
                if(ConnexionInternet.isConnectedInternet(ConnexionActivity.this)){
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
                else{
                    Toast.makeText(ConnexionActivity.this, "Vous n'êtes pas connecté à internet", Toast.LENGTH_SHORT).show();
                }

            }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        buttonConnexionFB = (LoginButton) findViewById(fr.groupe3.iem.pokecard.R.id.login_button);
        buttonConnexionFB.setReadPermissions("email");

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {}

            @Override
            public void onCancel() {}

            @Override
            public void onError(FacebookException exception) {}
        });



    }
}
