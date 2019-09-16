package com.gilas.findrecipe.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.gilas.findrecipe.AdapterClass;
import com.gilas.findrecipe.Database.Tags;
import com.gilas.findrecipe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static String TAG = "HomeFragment";
    DatabaseReference myRef;
    ArrayList<Tags> listTags;
    RecyclerView recyclerView;
    SearchView searchView;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        myRef = FirebaseDatabase.getInstance().getReference().child("Tags");
        recyclerView = view.findViewById(R.id.searchRecyclerView);
        searchView = view.findViewById(R.id.searchView);
        searchView.setOnClickListener(this);

        return view;
    }

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
                        //AdapterClass adapterClass = new AdapterClass(listTags);
                        //recyclerView.setAdapter(adapterClass);

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

    private void search(String str) {
        ArrayList<Tags> list = new ArrayList<>();

        for(Tags object: listTags) {
            String tagName = object.getName().toLowerCase();
            if (tagName.contains(str.toLowerCase()) && str.length() != 0) {
                list.add(object);
            }
        }

        AdapterClass adapterClass = new AdapterClass(list);
        recyclerView.setAdapter(adapterClass);

    }

    @Override
    public void onClick(View view) {
        if (view == searchView) {
            searchView.onActionViewExpanded();
        }
    }
}
