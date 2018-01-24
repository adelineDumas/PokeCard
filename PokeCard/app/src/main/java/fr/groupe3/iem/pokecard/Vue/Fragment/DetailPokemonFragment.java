package fr.groupe3.iem.pokecard.Vue.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.groupe3.iem.pokecard.Entities.PokemonDetail;
import fr.groupe3.iem.pokecard.Modele.ManagerWS;
import fr.groupe3.iem.pokecard.R;


public class DetailPokemonFragment extends Fragment {

    //region variable
    private View v;
    private TextView textViewNom;
    private TextView textViewType;
    private TextView textViewType1;
    private TextView textViewType2;
    private TextView textViewHeight;
    private TextView textViewHeight1;
    private TextView textViewWeight;
    private TextView textViewWeight1;
    private ImageView imageViewPokemon;
    private PokemonDetail pokemonDetail;
    private ManagerWS managerWS;
    //endregion

    //region methodes

    public DetailPokemonFragment(){

    }

    public static DetailPokemonFragment newInstance(Bundle data) {
        DetailPokemonFragment fragment = new DetailPokemonFragment();
        fragment.setArguments(data);
        return fragment;
    }

    //region override

    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer, Bundle pSavedInstanceState) {
        View v = pInflater.inflate(R.layout.fragment_detail_pokemon, pContainer, false);

        textViewNom = (TextView) v.findViewById(R.id.textViewNom);
        imageViewPokemon = (ImageView) v.findViewById(R.id.imageViewPokemon);
        textViewType = (TextView) v.findViewById(R.id.textViewType);
        textViewType1 = (TextView) v.findViewById(R.id.textViewType1);
        textViewType2 = (TextView) v.findViewById(R.id.textViewType2);
        textViewWeight = (TextView) v.findViewById(R.id.textViewWeight);
        textViewWeight1 = (TextView) v.findViewById(R.id.textViewWeight1);
        textViewHeight= (TextView) v.findViewById(R.id.textViewHeight);
        textViewHeight1= (TextView) v.findViewById(R.id.textViewHeight1);
        Bundle data = getArguments();
        pokemonDetail = new PokemonDetail();
        managerWS = new ManagerWS(pokemonDetail);
        managerWS.getOnePokemon(data.getInt("id"), this);


        return v;
    }

    public void Refresh(final PokemonDetail pPokemonDetail){
        textViewNom.setText(pPokemonDetail.getName());
        Picasso.with(getContext()).load(pPokemonDetail.getUrl_img()).into(imageViewPokemon);
        textViewType1.setText(pPokemonDetail.getType1());
        textViewType2.setText(pPokemonDetail.getType2());
        textViewWeight1.setText(pPokemonDetail.getWeight());
        textViewHeight1.setText(pPokemonDetail.getHeight());

    }


    //endregion

    //endregion

}
