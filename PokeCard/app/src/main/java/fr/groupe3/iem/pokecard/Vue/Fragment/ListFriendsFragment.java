package fr.groupe3.iem.pokecard.Vue.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import fr.groupe3.iem.pokecard.Entities.Friend;
import fr.groupe3.iem.pokecard.Entities.RandomGifGenerator;
import fr.groupe3.iem.pokecard.Entities.User;
import fr.groupe3.iem.pokecard.Metier.ManagerWS;
import fr.groupe3.iem.pokecard.R;
import fr.groupe3.iem.pokecard.Vue.Adapter.FriendAdapter;

/**
 * Created by iem on 02/03/2018.
 */

public class ListFriendsFragment extends BaseFragment{


    //region variables

    private View v;
    private TextView textviewMesAmis;
    private ListView listViewFriends;

    private LinearLayout linearLayoutLoading;
    private ImageView imageViewloading;
    private RandomGifGenerator randomGifGenerator;

    private List<Friend> ListFriends;

    private FriendAdapter adapter;
    private ManagerWS managerWS;


    //endregion

    public ListFriendsFragment() {
    }

    public static ListFriendsFragment newInstance() {
        Bundle args = new Bundle();
        ListFriendsFragment fragment = new ListFriendsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer, Bundle savedInstanceState) {
       final ListFriendsFragment self = this;
        v = pInflater.inflate(R.layout.fragment_list_friends, pContainer, false);

        textviewMesAmis = v.findViewById(R.id.textViewMesAmis);
        listViewFriends = v.findViewById(R.id.listViewFriend);
        randomGifGenerator = new RandomGifGenerator();
        imageViewloading = (ImageView) v.findViewById(R.id.imageViewLoading);
        randomGifGenerator = new RandomGifGenerator();
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewloading);
        Glide.with(this).load(randomGifGenerator.ReturnUrlGif()).into(imageViewTarget);
        linearLayoutLoading = (LinearLayout) v.findViewById(R.id.loading);

        ListFriends = new ArrayList<>();
        adapter = new FriendAdapter(getActivity(), ListFriends);
        managerWS = new ManagerWS(adapter, ListFriends);

        managerWS.GetListFriends(new User(User.getINSTANCE().getLogin(), User.getINSTANCE().getPassword()), this, linearLayoutLoading);

        listViewFriends.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder
                        .setMessage("Voulez-vous supprimer cet ami ?")
                        .setCancelable(true)
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                managerWS.DeleteFriend(new Friend(User.getINSTANCE().getLogin(), ListFriends.get(i).getLogin()),ListFriends.get(i) , self , linearLayoutLoading);

                            }
                        });
                alertDialogBuilder.show();
                return true;

            }
        });

        return v;

    }

    public void Refresh(final List<Friend> pListFriend){
        FriendAdapter adapter  = new FriendAdapter(getActivity(), pListFriend);
        listViewFriends = (ListView) v.findViewById(R.id.listViewFriend);
        listViewFriends.setAdapter(adapter);

    }
}
