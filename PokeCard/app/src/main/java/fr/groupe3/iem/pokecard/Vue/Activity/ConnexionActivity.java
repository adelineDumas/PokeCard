package fr.groupe3.iem.pokecard.Vue.Activity;

import android.content.Context;
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
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import fr.groupe3.iem.pokecard.Entities.ConnexionInternet;
import fr.groupe3.iem.pokecard.Entities.LoginFacebook;
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
        initFacebook();

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

        if (ConnexionInternet.isConnectedInternet(ConnexionActivity.this)) {
            callbackManager.onActivityResult(requestCode, resultCode, data);


        }
        else {
            Toast.makeText(ConnexionActivity.this, "Vous n'êtes pas connecté à internet", Toast.LENGTH_SHORT).show();
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
    }


    private void initFacebook() {
        final Context context = this;
        buttonConnexionFB = (LoginButton) findViewById(fr.groupe3.iem.pokecard.R.id.login_button);
        buttonConnexionFB.setReadPermissions("email");

        if (ConnexionInternet.isConnectedInternet(ConnexionActivity.this)) {
            //CallBack Login Facebook
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            GraphRequest grequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(
                                                JSONObject object,
                                                GraphResponse response) {
                                            try {
                                                Profile profile = Profile.getCurrentProfile();
                                                User.getINSTANCE().setLogin(object.getString("first_name") + " " + object.getString("last_name"));
                                                User.getINSTANCE().setPassword("facebook");
                                                User.getINSTANCE().setMail(object.getString("email") );
                                                User.getINSTANCE().setAvatar(profile.getProfilePictureUri(150, 150).toString());

                                                Call<JSONObject> pokemonCall = AppPokemon.getPokemonService().SignUp(new User(object.getString("first_name") + " " + object.getString("last_name"), "facebook", object.getString("email")));
                                                pokemonCall.enqueue(new Callback<JSONObject>() {
                                                    @Override
                                                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                                                    }

                                                    @Override
                                                    public void onFailure(Call<JSONObject>call, Throwable t) {
                                                    }
                                                });

                                                Call<User> user = AppPokemon.getPokemonService().verifyLogin(new User(User.getINSTANCE().getLogin(), User.getINSTANCE().getPassword()));
                                                user.enqueue(new Callback<User>() {
                                                    @Override
                                                    public void onResponse(Call<User> call, Response<User> response) {
                                                        User.getINSTANCE().setPoints(response.body().getPoints());
                                                        Intent intent = new Intent().setClass(ConnexionActivity.this, MainActivity.class);
                                                        Toast.makeText(context, "Connexion Facebook réussie", Toast.LENGTH_SHORT).show();
                                                        startActivity(intent);
                                                    }

                                                    @Override
                                                    public void onFailure(Call<User> call, Throwable t) {
                                                    }
                                                });


                                            } catch (Exception e) {
                                                Toast.makeText(ConnexionActivity.this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                                            }
                                            LoginManager.getInstance().logOut(); //déconnexion facebook
                                        }
                                    });
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "email,first_name,last_name,id"); // id,first_name,last_name,email,gender,birthday,cover,picture.type(large)
                            grequest.setParameters(parameters);
                            grequest.executeAsync();
                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(ConnexionActivity.this, "Annulé", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            Toast.makeText(ConnexionActivity.this, "Erreur" + exception, Toast.LENGTH_LONG).show();
                        }
                    });
        }
        else {
            Toast.makeText(ConnexionActivity.this, "Vous n'êtes pas connecté à internet", Toast.LENGTH_SHORT).show();
        }
    }
    //endregion

}