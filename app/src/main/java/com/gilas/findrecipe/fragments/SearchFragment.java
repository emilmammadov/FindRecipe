package com.gilas.findrecipe.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.recycleclick.RecycleClick;
import com.gilas.findrecipe.FragmentBusinessLogic;
import com.gilas.findrecipe.R;
import com.gilas.findrecipe.RecipeActivity;
import com.gilas.findrecipe.adapters.RecipeSearchRecAdapter;
import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.databinding.FragmentHomeBinding;
import com.gilas.findrecipe.viewmodels.SearchViewModel;

import java.util.List;

import static com.gilas.findrecipe.fragments.HomeFragment.RECIPE_OBJECT_EXTRA;

public class SearchFragment extends Fragment {

    private RecipeSearchRecAdapter searchRecyclerAdapter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Recipe> listRecipes, listSearchedRecipes;
    private SearchViewModel searchViewModel;

    public SearchFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        searchViewModel.getAllRecipeTitles(new SearchViewModel.CallbackListRecipe() {
            @Override
            public void onSuccess(List<Recipe> recipes) {
                listRecipes = recipes;
            }
        });

        binding.layoutTable.setVisibility(View.GONE);
        binding.btnSearch.setVisibility(View.GONE);

        searchView = binding.searchViewHome;
        recyclerView = binding.searchRecyclerHome;

        searchView.setQueryHint(getResources().getString(R.string.searhFragmentHint));

        ViewGroup.LayoutParams params = searchView.getLayoutParams();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        searchView.setLayoutParams(params);

        searchRecycleClick();

        return binding.getRoot();
    }

    private void searchRecycleClick() {
        RecycleClick.addTo(recyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int i, View view) {

                searchViewModel.getRecipe(listSearchedRecipes.get(i).getId(), new SearchViewModel.CallbackRecipe() {
                    @Override
                    public void onSuccess(Recipe recipe) {
                        Intent intent = new Intent(getContext(), RecipeActivity.class);
                        intent.putExtra(RECIPE_OBJECT_EXTRA, recipe);
                        startActivity(intent);
                    }
                });


            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    listSearchedRecipes = FragmentBusinessLogic.searchRecipe(listRecipes, s);
                    searchRecyclerAdapter = new RecipeSearchRecAdapter(listSearchedRecipes);
                    recyclerView.setAdapter(searchRecyclerAdapter);
                    return true;
                }
            });
        }
    }


}