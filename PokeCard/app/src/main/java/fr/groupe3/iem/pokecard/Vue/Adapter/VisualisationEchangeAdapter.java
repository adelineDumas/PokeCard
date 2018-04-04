package fr.groupe3.iem.pokecard.Vue.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Echange;
import fr.groupe3.iem.pokecard.Metier.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.Fragment.EchangeFragment;
import fr.groupe3.iem.pokecard.Vue.Fragment.VisualisationEchangeFragment;

/**
 * Created by Adel on 25/01/2018.
 */

public class VisualisationEchangeAdapter extends ArrayAdapter<Echange>{

    private List<Echange> listEchange = new ArrayList<>();
    private VisualisationEchangeFragment echangeFragment;

    //region constructeur
    public VisualisationEchangeAdapter(Context context , List<Echange> objects, VisualisationEchangeFragment pFramgent) {
        super(context,0, objects);
        listEchange = objects;
        echangeFragment = pFramgent;
    }

    //endregion

    //region methodes
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listadaptervisualisationechange,parent, false);
        }

        VisualisationEchangeAdapter.ViewHolder viewHolder  = (VisualisationEchangeAdapter.ViewHolder) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new VisualisationEchangeAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listadaptervisualisationechange, parent, false);
            viewHolder.textViewPokemon = (TextView) convertView.findViewById(R.id.textViewNomPokemon);
            viewHolder.textViewUser = (TextView) convertView.findViewById(R.id.textViewUser);
            viewHolder.textViewPhrase = (TextView) convertView.findViewById(R.id.textViewPhrase);
            viewHolder.imageViewPokemon = (ImageView) convertView.findViewById(R.id.imageViewPokemon);
            convertView.setTag(viewHolder);
        }

        final Echange echange = getItem(position);

        viewHolder.textViewPokemon.setText(echange.getNom_pokemon());
        viewHolder.textViewUser.setText(echange.getLogin_user());

        // ad - Utilisation de Picasso pour afficher les images depuis une URL
        Picasso.with(getContext()).load(echange.getUrl()).resize(200, 200).into(viewHolder.imageViewPokemon);

        return convertView;
    }

    //endregion

    //region classe
    private static class ViewHolder{
        TextView textViewPokemon;
        TextView textViewUser;
        TextView textViewPhrase;
        ImageView imageViewPokemon;
    }
    //endregion


}
