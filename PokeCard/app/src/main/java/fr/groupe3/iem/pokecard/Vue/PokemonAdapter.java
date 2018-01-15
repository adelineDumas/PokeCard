package fr.groupe3.iem.pokecard.Vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Pokemon;
import fr.groupe3.iem.pokecard.Fragment.ListPokemonFragment;
import fr.groupe3.iem.pokecard.R;

public class PokemonAdapter extends ArrayAdapter<Pokemon> {

    public PokemonAdapter(Context context, List<Pokemon> objects) {
        super(context,0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listadapter,parent, false);
        }

        ViewHolder viewHolder  = (ViewHolder) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listadapter, parent, false);
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


    private static class ViewHolder{
        TextView textViewPokemon;
        ImageView imageViewPokemon;
    }


}
