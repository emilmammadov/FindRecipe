package com.gilas.findrecipe.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.chootdev.recycleclick.RecycleClick;
import com.gilas.findrecipe.Adapters.FlexRecyclerAdapter;
import com.gilas.findrecipe.Adapters.SearchRecyclerAdapter;
import com.gilas.findrecipe.Database.Tags;
import com.gilas.findrecipe.R;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static String TAG = "HomeFragment";

    private static ArrayList<Tags> listSearchedTags;
    private static ArrayList<Tags> listSelectedTags;
    private static ArrayList<String> listSelectedTagNames;
    private DatabaseReference myRef;
    private ArrayList<Tags> listTags;
    private RecyclerView searchRecyclerView, flexBoxRecyclerView;
    private SearchView searchView;
    private View view,view2;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        myRef = FirebaseDatabase.getInstance().getReference().child("Tags");
        searchRecyclerView = view.findViewById(R.id.searchRecyclerView);
        searchView = view.findViewById(R.id.searchView);
        searchView.setOnClickListener(this);

        listSelectedTags = new ArrayList<>();
        listSelectedTagNames = new ArrayList<>();

        flexBox();

        recycleClick();




        return view;
    }

    private void recycleClick() {
        RecycleClick.addTo(searchRecyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                Toast.makeText(getContext(), listSearchedTags.get(i).getName()+"", Toast.LENGTH_SHORT).show();
                if (!listSelectedTags.contains(listSearchedTags.get(i))) {
                    listSelectedTags.add(listSearchedTags.get(i));
                    listSelectedTagNames.add(listSearchedTags.get(i).getName());
                    flexBox();
                }

            }
        });
    }

    private void flexBox() {

        flexBoxRecyclerView = view.findViewById(R.id.flexBoxRecyclerView);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        flexBoxRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new FlexRecyclerAdapter(listSelectedTagNames, new FlexRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e(TAG, "onItemClick: "+" Nasilsiniz"+position);
            }
        });
        flexBoxRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    // Uygulama tıklandığı anda tag verilerini firebase'den çekiyor.
    @Override
    public void onStart() {
        super.onStart();

        if(myRef != null) {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        listTags = new ArrayList<>();
                        for (DataSnapshot ds: dataSnapshot.getChildren()) {
                            listTags.add(ds.getValue(Tags.class));
                        }
                        //SearchRecyclerAdapter adapterClass = new SearchRecyclerAdapter(listTags);
                        //searchRecyclerView.setAdapter(adapterClass);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if(searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }

    }


    // SearchBar için arama fonksiyonu
    private void search(String str) {
        listSearchedTags = new ArrayList<>();

        if (listTags != null) {
            for(Tags object: listTags) {
                String tagName = object.getName().toLowerCase();
                if (tagName.contains(str.toLowerCase()) && str.length() != 0) {
                    listSearchedTags.add(object);
                }
            }
        }

        SearchRecyclerAdapter searchRecyclerAdapter = new SearchRecyclerAdapter(listSearchedTags);
        searchRecyclerView.setAdapter(searchRecyclerAdapter);

    }

    @Override
    public void onClick(View view) {
        if (view == searchView) {
            searchView.onActionViewExpanded();
        }
    }
}
