package fr.groupe3.iem.pokecard.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.MainActivity;


public class DetailPokemonFragment extends BaseFragment {

    private TextView textViewNomPokemon;
    private ImageView imageViewPokemon;
    private MainActivity context;


    public static DetailPokemonFragment newInstance() {
        Bundle args = new Bundle();

        DetailPokemonFragment fragment = new DetailPokemonFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_detail_pokemon, container, false);

        textViewNomPokemon = v.findViewById(R.id.textViewNomPokemon);
        imageViewPokemon = v.findViewById(R.id.imageViewPokemon);


        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(context);
        context = (MainActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (MainActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
