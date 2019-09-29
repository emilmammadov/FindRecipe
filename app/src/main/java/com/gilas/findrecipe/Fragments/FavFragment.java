package com.gilas.findrecipe.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.Adapters.FavRecyclerAdapter;
import com.gilas.findrecipe.Database.Recipes;
import com.gilas.findrecipe.R;
import com.gilas.findrecipe.SQLite.DBHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Recipes> recipeList;

    public FavFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav, container, false);

        recyclerView = view.findViewById(R.id.favRecyclerView);

        recipeList = new DBHelper(getContext()).getAllRecipes();

        //recipeList.add(new Recipes(5,"hey","adfasdf","dsaf",3,5,7));

        FavRecyclerAdapter favAdapter = new FavRecyclerAdapter(recipeList);
        recyclerView.setAdapter(favAdapter);

        return view;
    }

}
