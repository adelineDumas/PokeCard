package fr.groupe3.iem.pokecard.Vue.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.Activity.MainActivity;

/**
 * Created by Adel on 09/03/2018.
 */

public class SettingsFragment extends  PreferenceFragment {

    MainActivity activity;

    public SettingsFragment(){

    }

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_settings);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (MainActivity) context;
    }
}
