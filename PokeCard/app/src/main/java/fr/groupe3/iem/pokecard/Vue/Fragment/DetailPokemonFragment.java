package fr.groupe3.iem.pokecard.Vue.Fragment;

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

    //region variable

    private TextView textViewNomPokemon;
    private ImageView imageViewPokemon;
    private MainActivity context;

    //endregion

    //region methodes
    public static DetailPokemonFragment newInstance() {
        Bundle args = new Bundle();

        DetailPokemonFragment fragment = new DetailPokemonFragment();
        fragment.setArguments(args);

        return fragment;
    }

    //region override
    @Override
    public void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer, Bundle pSavedInstanceState) {
        View v = pInflater.inflate(R.layout.fragment_fragment_detail_pokemon, pContainer, false);

        textViewNomPokemon = v.findViewById(R.id.textViewNomPokemon);
        imageViewPokemon = v.findViewById(R.id.imageViewPokemon);


        return v;
    }


    @Override
    public void onAttach(Activity pActivity) {
        super.onAttach(context);
        context = (MainActivity) pActivity;
    }

    @Override
    public void onAttach(Context pContext) {
        super.onAttach(pContext);
        this.context = (MainActivity) pContext;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //endregion

    //endregion

}
