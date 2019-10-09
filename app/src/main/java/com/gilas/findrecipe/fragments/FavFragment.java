package com.gilas.findrecipe.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.recycleclick.RecycleClick;
import com.gilas.findrecipe.adapters.FavRecyclerAdapter;
import com.gilas.findrecipe.Entities.Recipe;
import com.gilas.findrecipe.R;
import com.gilas.findrecipe.RecipeActivity;
import com.gilas.findrecipe.dboperations.DBHelper;

import java.util.ArrayList;

import static com.gilas.findrecipe.fragments.HomeFragment.RECIPE_OBJECT_EXTRA;


public class FavFragment extends Fragment {

    static RecyclerView recyclerView;
    static ArrayList<Recipe> recipeList;

    public FavFragment() {
    }


    @Override
    public void onStart() {
        super.onStart();
        recipeList = new DBHelper(getContext()).getAllRecipes();
        FavRecyclerAdapter favAdapter = new FavRecyclerAdapter(getContext(), recipeList);
        recyclerView.setAdapter(favAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav, container, false);

        recyclerView = view.findViewById(R.id.favRecyclerView);

        recipeList = new DBHelper(getContext()).getAllRecipes();

        FavRecyclerAdapter favAdapter = new FavRecyclerAdapter(getContext(), recipeList);
        recyclerView.setAdapter(favAdapter);

        recyclerClick();


        return view;

    }

    private void recyclerClick() {
        RecycleClick.addTo(recyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                Intent intent = new Intent(getContext(), RecipeActivity.class);
                intent.putExtra(RECIPE_OBJECT_EXTRA, recipeList.get(i));
                startActivity(intent);

            }
        });
    }


}
