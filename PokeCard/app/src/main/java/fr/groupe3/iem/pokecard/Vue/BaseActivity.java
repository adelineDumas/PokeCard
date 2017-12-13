package fr.groupe3.iem.pokecard.Vue;

import android.support.v7.app.AppCompatActivity;

import fr.groupe3.iem.pokecard.Fragment.BaseFragment;
import fr.groupe3.iem.pokecard.R;

/**
 * Created by iem on 13/12/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public void showFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, fragment).addToBackStack("Nav").commit();
    }

    public void clearBackstack() {
        //getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
