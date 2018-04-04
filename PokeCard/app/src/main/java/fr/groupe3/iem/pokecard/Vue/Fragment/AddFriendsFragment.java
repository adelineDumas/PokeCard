package fr.groupe3.iem.pokecard.Vue.Fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
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
import fr.groupe3.iem.pokecard.Vue.Adapter.SearchAddFriendAdapter;


public class AddFriendsFragment extends BaseFragment {


    //region variables

    private View v;
    private TextView textviewSearch ;
    private SearchView searchView;
    private Button buttonOk;
    private ListView listViewSearchFriends;

    private LinearLayout linearLayoutLoading;
    private ImageView imageViewloading;
    private RandomGifGenerator randomGifGenerator;

    private List<Friend> ListSearchFriends;

    private SearchAddFriendAdapter adapter;
    private ManagerWS managerWS;


    //endregion

    public AddFriendsFragment() {
    }

    public static AddFriendsFragment newInstance() {
        Bundle args = new Bundle();
        AddFriendsFragment fragment = new AddFriendsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer, Bundle savedInstanceState) {
        final AddFriendsFragment self = this;
        v = pInflater.inflate(R.layout.fragment_add_friends, pContainer, false);

        textviewSearch = v.findViewById(R.id.textViewRechercheAmi);
        searchView = v.findViewById(R.id.searchView);
        listViewSearchFriends = v.findViewById(R.id.listViewSearchFriend);
        buttonOk = v.findViewById(R.id.buttonOk);
        randomGifGenerator = new RandomGifGenerator();
        imageViewloading = (ImageView) v.findViewById(R.id.imageViewLoading);
        randomGifGenerator = new RandomGifGenerator();
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewloading);
        Glide.with(this).load(randomGifGenerator.ReturnUrlGif()).into(imageViewTarget);
        linearLayoutLoading = (LinearLayout) v.findViewById(R.id.loading);

        ListSearchFriends = new ArrayList<>();
        adapter = new SearchAddFriendAdapter(getActivity(), ListSearchFriends, this);
        managerWS = new ManagerWS(adapter, ListSearchFriends);

        managerWS.GetListUserRandom(this, linearLayoutLoading, User.getINSTANCE());

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchView.getQuery().toString().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.contentMain), "Veuillez rentrer un nom d'utilisateur.", Snackbar.LENGTH_INDEFINITE);
                    managerWS.GetListUserRandom(self, linearLayoutLoading, User.getINSTANCE());
                    snackbar.show();
                }
                else {
                    managerWS.GetListUserSearched(searchView.getQuery().toString(), self, linearLayoutLoading); 
                }
            }
        });
        return v;

    }

    public void Refresh(final List<Friend> pListFriend){
        SearchAddFriendAdapter adapter  = new SearchAddFriendAdapter(getActivity(), pListFriend, this);
        listViewSearchFriends = (ListView) v.findViewById(R.id.listViewSearchFriend);
        listViewSearchFriends.setAdapter(adapter);

    }


}
