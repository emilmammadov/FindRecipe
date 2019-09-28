package com.gilas.findrecipe.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.Adapters.FavFlexRecyclerAdapter;
import com.gilas.findrecipe.Database.Recipes;
import com.gilas.findrecipe.R;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavFragment extends Fragment {

    RecyclerView flexBoxRecyclerView;
    ArrayList<Recipes> recipeList;

    public FavFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav, container, false);

        flexBoxRecyclerView = view.findViewById(R.id.favFlexRecyclerView);

        recipeList = new ArrayList<>();

        recipeList.add(new Recipes(5,"hey","adfasdf","dsaf",3,5,7));

        flexbox(recipeList);

        return view;
    }

    private void flexbox(ArrayList<Recipes> recipeList) {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        flexBoxRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new FavFlexRecyclerAdapter(recipeList);
        flexBoxRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
