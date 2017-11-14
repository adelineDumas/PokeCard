package fr.groupe3.iem.pokecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by iem on 14/11/2017.
 */

public class PokemonAdapter extends ArrayAdapter<Pokemon> {


    private TextView textViewPokemon;


    public PokemonAdapter(Context context, List objects) {
        super(context, R.layout.listadapter, objects);
    }

    private static class ViewHolder{
        TextView textViewPokemon;
        ImageView imageViewPokemon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Pokemon pokemon = getItem(position);

        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listadapter, parent, false);
            viewHolder.textViewPokemon = (TextView) convertView.findViewById(R.id.textViewNomPokemon);
            viewHolder.imageViewPokemon = (ImageView) convertView.findViewById(R.id.imageViewPokemon);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        return convertView;
    }


}
