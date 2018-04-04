package fr.groupe3.iem.pokecard.Vue.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.R;

public class PokemonAdapter extends ArrayAdapter<Pokemon> {

    //region constructeur
    public PokemonAdapter(Context context ,List<Pokemon> objects) {
        super(context,0, objects);
    }

    //endregion

    //region methodes
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listadapterpokemon,parent, false);
        }

        ViewHolder viewHolder  = (ViewHolder) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listadapterpokemon, parent, false);
            viewHolder.textViewPokemon = (TextView) convertView.findViewById(R.id.textViewNomPokemon);
            viewHolder.imageViewPokemon = (ImageView) convertView.findViewById(R.id.imageViewPokemon);
            convertView.setTag(viewHolder);
        }

        Pokemon pokemon = getItem(position);
        viewHolder.textViewPokemon.setText(pokemon.getName_pokemon());

        // ad - Utilisation de Picasso pour afficher les images depuis une URL
        Picasso.with(getContext()).load(pokemon.getUrl_img()).resize(200, 200).into(viewHolder.imageViewPokemon);

        return convertView;
    }

    //endregion

    //region classe
    private static class ViewHolder{
        TextView textViewPokemon;
        ImageView imageViewPokemon;
    }
    //endregion



}
