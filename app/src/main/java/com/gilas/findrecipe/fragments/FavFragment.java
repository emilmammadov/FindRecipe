package com.gilas.findrecipe.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.recycleclick.RecycleClick;
import com.gilas.findrecipe.R;
import com.gilas.findrecipe.RecipeActivity;
import com.gilas.findrecipe.adapters.FavRecyclerAdapter;
import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.databinding.FragmentFavBinding;
import com.gilas.findrecipe.viewmodels.FavViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.gilas.findrecipe.fragments.HomeFragment.RECIPE_OBJECT_EXTRA;


public class FavFragment extends Fragment {

    private static RecyclerView recyclerView;
    private static List<Recipe> recipeList;
    private FavViewModel favViewModel;
    private FavRecyclerAdapter favAdapter;
    FragmentFavBinding binding;

    public FavFragment() {
    }


    @Override
    public void onStart() {
        super.onStart();/*
        favViewModel = ViewModelProviders.of(this).get(FavViewModel.class);
        favViewModel.getAllRecipes().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                recipeList = new ArrayList<>(recipes);
            }
        });

        favAdapter = new FavRecyclerAdapter(getContext(), recipeList);
        recyclerView.setAdapter(favAdapter);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_fav, container, false);
        binding = FragmentFavBinding.inflate(inflater, container, false);

        favViewModel = ViewModelProviders.of(this).get(FavViewModel.class);
        favViewModel.getAllRecipes().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                //recipeList = new ArrayList<>(recipes);
                recyclerView = binding.favRecyclerView;
                FavRecyclerAdapter adapter = new FavRecyclerAdapter(recipes);
                recyclerView.setAdapter(adapter);
                recyclerClick(recipes);
            }
        });




        return binding.getRoot();

    }

    private void recyclerClick(final List<Recipe> recipeList) {
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
