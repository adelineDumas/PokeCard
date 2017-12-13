package fr.groupe3.iem.pokecard.Fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import fr.groupe3.iem.pokecard.Vue.BaseActivity;
import fr.groupe3.iem.pokecard.Vue.MainActivity;

/**
 * Created by iem on 13/12/2017.
 */

public abstract class BaseFragment extends Fragment{

    BaseActivity context;


    public void showFragment(BaseFragment f) {
        context.showFragment(f);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = (MainActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (MainActivity)context;
    }
}
