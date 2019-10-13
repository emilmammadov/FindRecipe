package com.gilas.findrecipe.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.recycleclick.RecycleClick;
import com.gilas.findrecipe.R;
import com.gilas.findrecipe.RecipeActivity;
import com.gilas.findrecipe.adapters.RecipeSearchRecAdapter;
import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.databinding.FragmentHomeBinding;
import com.gilas.findrecipe.dboperations.DatabaseOperations;

import java.util.ArrayList;
import java.util.List;

import static com.gilas.findrecipe.fragments.HomeFragment.RECIPE_OBJECT_EXTRA;

public class SearchFragment extends Fragment {


    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Recipe> listRecipes, listSearchedRecipes;
    FragmentHomeBinding binding;

    public SearchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        new DatabaseOperations(getContext()).getAllRecipeTitles(new DatabaseOperations.VolleyCallback() {
            @Override
            public void onSuccess(List<Recipe> result) {
                listRecipes = result;
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

                new DatabaseOperations(getContext()).getFavRecipe(listSearchedRecipes.get(i).getId(),
                        new DatabaseOperations.RecipeCallback() {
                            @Override
                            public void onSuccess(Recipe result) {
                                Intent intent = new Intent(getContext(), RecipeActivity.class);
                                intent.putExtra(RECIPE_OBJECT_EXTRA, result);
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
                    search(s);
                    return true;
                }
            });
        }
    }


    private void search(String str) {
        listSearchedRecipes = new ArrayList<>();
        List<String> listSearchedTitles = new ArrayList<>();

        if (listRecipes != null) {
            for (Recipe object : listRecipes) {
                String tagName = object.getTitle().toLowerCase();
                if (tagName.contains(str.toLowerCase()) && str.length() != 0) {
                    listSearchedRecipes.add(new Recipe(object.getId(), tagName));
                    listSearchedTitles.add(tagName);
                }
            }
        }

        RecipeSearchRecAdapter searchRecyclerAdapter = new RecipeSearchRecAdapter(listSearchedTitles);
        recyclerView.setAdapter(searchRecyclerAdapter);

    }
}