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

import fr.groupe3.iem.pokecard.Entities.Friend;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Metier.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.Fragment.AddFriendsFragment;

/**
 * Created by iem on 02/03/2018.
 */

public class SearchAddFriendAdapter extends ArrayAdapter<Friend> {

    private List<Friend> listSearchFriends = new ArrayList<>();
    private AddFriendsFragment addFriendsFragment;

    //region constructeur
    public SearchAddFriendAdapter(Context context , List<Friend> objects, AddFriendsFragment pFramgent) {
        super(context,0, objects);
        listSearchFriends = objects;
        addFriendsFragment = pFramgent;
    }

    //endregion

    //region methodes
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listadaptersearchfriend,parent, false);
        }

        SearchAddFriendAdapter.ViewHolder viewHolder  = (SearchAddFriendAdapter.ViewHolder) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new SearchAddFriendAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listadaptersearchfriend, parent, false);
            viewHolder.textViewNomFriend = (TextView) convertView.findViewById(R.id.textViewNomFriend);
            viewHolder.imageViewFriend = (ImageView) convertView.findViewById(R.id.imageViewFriend);
            viewHolder.imageViewFriend = (ImageView) convertView.findViewById(R.id.imageViewFriend);
            viewHolder.buttonAddFriend = (Button) convertView.findViewById(R.id.buttonAddFriend);
            convertView.setTag(viewHolder);
        }

        final Friend friend = getItem(position);
        viewHolder.textViewNomFriend.setText(friend.getLogin());

        final Context context = this.getContext();
        viewHolder.buttonAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ManagerWS(listSearchFriends).AddFriend(new Friend(friend.getLogin(), User.getINSTANCE().getLogin()),context,addFriendsFragment);
            }
        });

        // ad - Utilisation de Picasso pour afficher les images depuis une URL
        Picasso.with(getContext()).load(friend.getAvatar()).resize(200, 200).into(viewHolder.imageViewFriend);

        return convertView;
    }

    //endregion

    //region classe
    private static class ViewHolder{
        TextView textViewNomFriend;
        ImageView imageViewFriend;
        Button buttonAddFriend;
    }
    //endregion
}
