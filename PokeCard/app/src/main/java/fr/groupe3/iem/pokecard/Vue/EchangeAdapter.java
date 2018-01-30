package fr.groupe3.iem.pokecard.Vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Echange;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Entities.UserEchange;
import fr.groupe3.iem.pokecard.Modele.AppPokemon;
import fr.groupe3.iem.pokecard.Modele.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.Fragment.EchangeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adel on 25/01/2018.
 */

public class EchangeAdapter extends ArrayAdapter<Echange>{

    private List<Echange> listEchange = new ArrayList<>();

    //region constructeur
    public EchangeAdapter(Context context , List<Echange> objects) {
        super(context,0, objects);
        listEchange.add(objects.get(0));
    }

    //endregion

    //region methodes
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listadapterechange,parent, false);
        }

        EchangeAdapter.ViewHolder viewHolder  = (EchangeAdapter.ViewHolder) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new EchangeAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listadapterechange, parent, false);
            viewHolder.textViewPokemon = (TextView) convertView.findViewById(R.id.textViewNomPokemon);
            viewHolder.textViewUser = (TextView) convertView.findViewById(R.id.textViewUser);
            viewHolder.textViewPhrase = (TextView) convertView.findViewById(R.id.textViewPhrase);
            viewHolder.imageViewPokemon = (ImageView) convertView.findViewById(R.id.imageViewPokemon);
            viewHolder.buttonEchanger = (Button) convertView.findViewById(R.id.buttonEchanger);
            convertView.setTag(viewHolder);
        }

        final Echange echange = getItem(position);

        viewHolder.textViewPokemon.setText(echange.getNom_pokemon());
        viewHolder.textViewUser.setText(echange.getLogin_user());

        final Context context = this.getContext();
        viewHolder.buttonEchanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ManagerWS(listEchange).EchangeWith(context, echange);
            }
        });

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
        Button buttonEchanger;
    }
    //endregion


}
