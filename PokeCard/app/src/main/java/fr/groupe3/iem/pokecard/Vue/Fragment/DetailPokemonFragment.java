package fr.groupe3.iem.pokecard.Vue.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.squareup.picasso.Picasso;

import fr.groupe3.iem.pokecard.Entities.PokemonDetail;
import fr.groupe3.iem.pokecard.Entities.RandomGifGenerator;
import fr.groupe3.iem.pokecard.Metier.ManagerWS;
import fr.groupe3.iem.pokecard.R;


public class DetailPokemonFragment extends BaseFragment {

    //region variable
    private View v;
    private TextView textViewNom;
    private TextView textViewType;
    private TextView textViewType1;
    private TextView textViewType2;
    private TextView textViewAbility;
    private TextView textViewAbility1;
    private TextView textViewAbility2;
    private TextView textViewHeight;
    private TextView textViewHeight1;
    private TextView textViewWeight;
    private TextView textViewWeight1;
    private ImageView imageViewPokemon;

    private LinearLayout linearLayoutLoading;
    private ImageView imageViewloading;
    private RandomGifGenerator randomGifGenerator;

    private PokemonDetail pokemonDetail;
    private ManagerWS managerWS;
    private Context context;
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

        imageViewloading = (ImageView) v.findViewById(R.id.imageViewLoading);
        randomGifGenerator = new RandomGifGenerator();
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewloading);
        Glide.with(this).load(randomGifGenerator.ReturnUrlGif()).into(imageViewTarget);
        linearLayoutLoading = (LinearLayout) v.findViewById(R.id.loading);

        textViewNom = (TextView) v.findViewById(R.id.textViewNom);
        imageViewPokemon = (ImageView) v.findViewById(R.id.imageViewPokemon);
        textViewType = (TextView) v.findViewById(R.id.textViewType);
        textViewType1 = (TextView) v.findViewById(R.id.textViewType1);
        textViewType2 = (TextView) v.findViewById(R.id.textViewType2);
        textViewAbility = (TextView) v.findViewById(R.id.textViewAbility);
        textViewAbility1 = (TextView) v.findViewById(R.id.textViewAbility1);
        textViewAbility2 = (TextView) v.findViewById(R.id.textViewAbility2);
        textViewWeight = (TextView) v.findViewById(R.id.textViewWeight);
        textViewWeight1 = (TextView) v.findViewById(R.id.textViewWeight1);
        textViewHeight= (TextView) v.findViewById(R.id.textViewHeight);
        textViewHeight1= (TextView) v.findViewById(R.id.textViewHeight1);

        Bundle data = getArguments();
        pokemonDetail = new PokemonDetail();
        managerWS = new ManagerWS(pokemonDetail);
        managerWS.getOnePokemon(data.getInt("id"), this, linearLayoutLoading);

        context = getActivity();


        return v;
    }

    public void Refresh(final PokemonDetail pPokemonDetail){
        textViewNom.setText(pPokemonDetail.getName_pokemon());
        Picasso.with(getContext()).load(pPokemonDetail.getUrl_img()).into(imageViewPokemon);
        textViewType1.setText(pPokemonDetail.getType1());
        textViewType2.setText(pPokemonDetail.getType2());
        textViewWeight1.setText(pPokemonDetail.getWeight());
        textViewHeight1.setText(pPokemonDetail.getHeight());
        textViewAbility1.setText(pPokemonDetail.getAbility1());
        textViewAbility2.setText(pPokemonDetail.getAbility2());
    }


    //endregion

    //endregion

}
