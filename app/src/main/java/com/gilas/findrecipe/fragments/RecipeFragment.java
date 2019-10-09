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
import com.gilas.findrecipe.adapters.RecipeSearchRecAdapter;
import com.gilas.findrecipe.dboperations.DatabaseOperations;
import com.gilas.findrecipe.Entities.Recipe;
import com.gilas.findrecipe.R;
import com.gilas.findrecipe.RecipeActivity;

import java.util.ArrayList;

import static com.gilas.findrecipe.fragments.HomeFragment.RECIPE_OBJECT_EXTRA;

public class RecipeFragment extends Fragment implements View.OnClickListener {


    private View view;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ArrayList<Recipe> listRecipes, listSearchedRecipes;

    public RecipeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        new DatabaseOperations().getAllRecipeTitles(getContext(), new DatabaseOperations.VolleyCallback() {
            @Override
            public void onSuccess(ArrayList<Recipe> result) {
                listRecipes = result;
            }
        });

        view.findViewById(R.id.layoutTable).setVisibility(View.GONE);
        view.findViewById(R.id.btnSearch).setVisibility(View.GONE);

        searchView = view.findViewById(R.id.searchViewHome);
        recyclerView = view.findViewById(R.id.searchRecyclerHome);

        searchView.setQueryHint("Tarif ara");

        ViewGroup.LayoutParams params = searchView.getLayoutParams();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        searchView.setLayoutParams(params);

        searchRecycleClick();

        return view;
    }

    private void searchRecycleClick() {
        RecycleClick.addTo(recyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int i, View view) {

                new DatabaseOperations().getFavRecipe(getContext(), listSearchedRecipes.get(i).getId(),
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
    public void onClick(View view) {
        if (view == searchView) {

        }
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
        ArrayList<String> listSearchedTitles = new ArrayList<>();

        if (listRecipes != null) {
            for (Recipe object : listRecipes) {
                String tagName = object.getTitle().toLowerCase();
                if (tagName.contains(str.toLowerCase()) && str.length() != 0) {
                    listSearchedRecipes.add(new Recipe(object.getId(),tagName));
                    listSearchedTitles.add(tagName);
                }
            }
        }

        RecipeSearchRecAdapter searchRecyclerAdapter = new RecipeSearchRecAdapter(listSearchedTitles);
        recyclerView.setAdapter(searchRecyclerAdapter);

    }
}