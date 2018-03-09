package fr.groupe3.iem.pokecard.Vue.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Metier.AppPokemon;
import fr.groupe3.iem.pokecard.Metier.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnexionActivity extends AppCompatActivity {

    //region variables

    private ImageView imageViewPokecard;
    private TextView textViewUser;
    private TextView textViewPassword;
    private EditText editTextUser;
    private EditText editTextPasword;
    private Button buttonNewUser;
    private Button buttonConnexion;
    private LoginButton buttonConnexionFB;
    private CallbackManager callbackManager;

    private ManagerWS managerWS;

    //endregion

    //region methodes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        init();

        if (!(ConnexionInternet.isConnectedInternet(ConnexionActivity.this))) {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setMessage("Vous n'êtes pas connecté à internet, veuillez vérifier votre connexion")
                    .setCancelable(true)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            alertDialogBuilder.show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        Intent intent = new Intent().setClass(ConnexionActivity.this, MainActivity.class);

        if (requestCode == 64206) {
            Toast.makeText(this, "Connexion Facebook réussie", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    /***
     * Initialise les composants de la vue
     * @author : Adeline Dumas 14/11/2017 : Création
     */
    private void init() {
        managerWS = new ManagerWS();

        imageViewPokecard = (ImageView) findViewById(R.id.imageViewPokecard);
        textViewUser = (TextView) findViewById(R.id.textViewUser);
        textViewPassword = (TextView) findViewById(R.id.textViewPassword);

        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextUser.setText("");
        editTextPasword = (EditText) findViewById(R.id.editTextPassword);
        editTextPasword.setText("");

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
                if (ConnexionInternet.isConnectedInternet(ConnexionActivity.this)) {
                    if (editTextUser.getText().toString().isEmpty()) {
                        Toast.makeText(ConnexionActivity.this, "Pas de nom d'utilisateur", Toast.LENGTH_SHORT).show();
                    }
                    // ad - s'il n'y a pas de mot de passe de rentré : on affiche un toast
                    if (editTextPasword.getText().toString().isEmpty()) {
                        Toast.makeText(ConnexionActivity.this, "Pas de mot passe", Toast.LENGTH_SHORT).show();
                    }
                    // ad - s'il y a un nom d'utilisateur et un mot de passe
                    if (!editTextUser.getText().toString().isEmpty() && !editTextPasword.getText().toString().isEmpty()) {
                        User.getINSTANCE().setLogin(editTextUser.getText().toString());
                        User.getINSTANCE().setPassword(editTextPasword.getText().toString());
                           final Intent intent = new Intent().setClass(ConnexionActivity.this, MainActivity.class);
                        Call<User> user = AppPokemon.getPokemonService().verifyLogin(new User(User.getINSTANCE().getLogin(), User.getINSTANCE().getPassword()));
                        user.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.body().getPassword()!= null) {
                                    if (response.body().getPassword().contains("false")) {
                                        Toast.makeText(ConnexionActivity.this, "Le mot de passe est incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        User.getINSTANCE().setMail(response.body().getMail());
                                        User.getINSTANCE().setPoints(response.body().getPoints());
                                        User.getINSTANCE().setAvatar(response.body().getAvatar());
                                        startActivity(intent);
                                    }
                                }
                                else if (response.body().getPassword()== null && response.body().getLogin()== null){
                                    Toast.makeText(ConnexionActivity.this, "Cet utilisateur n'existe pas.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                            }
                        });
                    }
                } else {
                    Toast.makeText(ConnexionActivity.this, "Vous n'êtes pas connecté à internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        buttonConnexionFB = (LoginButton) findViewById(fr.groupe3.iem.pokecard.R.id.login_button);
        buttonConnexionFB.setReadPermissions("email");
        buttonConnexionFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
            }
        });


    }

    //endregion

}