package com.gilas.findrecipe.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.recycleclick.RecycleClick;
import com.gilas.findrecipe.Adapters.FlexRecyclerAdapter;
import com.gilas.findrecipe.Adapters.SearchRecyclerAdapter;
import com.gilas.findrecipe.Database.DatabaseOperations;
import com.gilas.findrecipe.Database.Tags;
import com.gilas.findrecipe.R;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static String TAG = "HomeFragment";

    private static ArrayList<Tags> listSearchedTags;
    private static ArrayList<Tags> listSelectedTags;
    private static ArrayList<String> listSelectedTagNames;
    private ArrayList<Tags> listTags;
    private RecyclerView searchRecyclerView, flexBoxRecyclerView;
    private SearchView searchView;
    private View view;
    private Button btnSearchRecipe;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        searchRecyclerView = view.findViewById(R.id.searchRecyclerView);
        flexBoxRecyclerView = view.findViewById(R.id.flexBoxRecyclerView);
        searchView = view.findViewById(R.id.searchView);
        searchView.setOnClickListener(this);
        btnSearchRecipe = view.findViewById(R.id.btnSearch);
        btnSearchRecipe.setOnClickListener(this);

        listSelectedTags = new ArrayList<>();
        listSelectedTagNames = new ArrayList<>();

        flexBox();

        searchRecycleClick();


        return view;
    }

    private void searchRecycleClick() {
        RecycleClick.addTo(searchRecyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                Toast.makeText(getContext(), listSearchedTags.get(i).getName() + "", Toast.LENGTH_SHORT).show();
                if (!listSelectedTags.contains(listSearchedTags.get(i))) {
                    listSelectedTags.add(listSearchedTags.get(i));
                    listSelectedTagNames.add(listSearchedTags.get(i).getName());
                    flexBox();
                }

            }
        });
    }

    private void flexBox() {

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        flexBoxRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new FlexRecyclerAdapter(listSelectedTagNames, new FlexRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e(TAG, "onItemClick: " + " Nasilsiniz " + position);
                listSelectedTags.remove(position);
                listSelectedTagNames.remove(position);
                flexBox();
            }
        });
        flexBoxRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    // Uygulama tıklandığı anda tag verilerini firebase'den çekiyor.
    @Override
    public void onStart() {
        super.onStart();

        new DatabaseOperations().getAllTags(getContext());


//        if (myRef != null) {
//            myRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        listTags = new ArrayList<>();
//                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                            listTags.add(ds.getValue(Tags.class));
//                        }
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        }

        if (searchView != null) {
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
            for (Tags object : listTags) {
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
        } else if (view == btnSearchRecipe) {
        }
    }
}
