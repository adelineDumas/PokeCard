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

import fr.groupe3.iem.pokecard.Entities.Friend;
import fr.groupe3.iem.pokecard.R;

/**
 * Created by iem on 02/03/2018.
 */

public class FriendAdapter extends ArrayAdapter<Friend> {

    //region constructeur
    public FriendAdapter(Context context , List<Friend> objects) {
        super(context,0, objects);
    }

    //endregion

    //region methodes
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listadapterfriend,parent, false);
        }

        FriendAdapter.ViewHolder viewHolder  = (FriendAdapter.ViewHolder) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new FriendAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listadapterfriend, parent, false);
            viewHolder.textViewNomFriend = (TextView) convertView.findViewById(R.id.textViewNomFriend);
            viewHolder.imageViewFriend = (ImageView) convertView.findViewById(R.id.imageViewFriend);
            convertView.setTag(viewHolder);
        }

        Friend friend = getItem(position);
        viewHolder.textViewNomFriend.setText(friend.getLogin());

        // ad - Utilisation de Picasso pour afficher les images depuis une URL
        Picasso.with(getContext()).load(friend.getAvatar()).resize(200, 200).into(viewHolder.imageViewFriend);

        return convertView;
    }

    //endregion

    //region classe
    private static class ViewHolder{
        TextView textViewNomFriend;
        ImageView imageViewFriend;
    }
    //endregion
}
