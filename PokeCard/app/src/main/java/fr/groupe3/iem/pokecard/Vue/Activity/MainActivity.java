package fr.groupe3.iem.pokecard.Vue.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.Fragment.AddFriendsFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.EchangeFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.ListAllPokemonFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.ListFriendsFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.ListPokemonUserFragment;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textViewUser;

    //region methodes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment(ListPokemonUserFragment.newInstance());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        textViewUser = (TextView) header.findViewById(R.id.textViewUserNav);
        textViewUser.setText(User.getINSTANCE().getLogin());
        navigationView.setNavigationItemSelectedListener(MainActivity.this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_allPokemon) {
            clearBackstack();
            showFragment(ListAllPokemonFragment.newInstance());
            getSupportActionBar().setTitle("Tous les Pokemons");
        } else if (id == R.id.nav_MaCollection) {
            clearBackstack();
            showFragment(ListPokemonUserFragment.newInstance());
            getSupportActionBar().setTitle("Mes Pokemons");
        } else if (id == R.id.nav_amis) {
            clearBackstack();
            showFragment(ListFriendsFragment.newInstance());
            getSupportActionBar().setTitle("Mes amis");
        } else if (id == R.id.nav_addFriends) {
            clearBackstack();
        showFragment(AddFriendsFragment.newInstance());
            getSupportActionBar().setTitle("Mes amis");
        }else if (id == R.id.nav_allEchange) {
            clearBackstack();
            showFragment(EchangeFragment.newInstance());
            getSupportActionBar().setTitle("Echange");
        } else if (id == R.id.nav_Deconnexion) {
            clearBackstack();
            Intent intent = new Intent().setClass(MainActivity.this, ConnexionActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //endregion



}


