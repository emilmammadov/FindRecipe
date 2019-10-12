package com.gilas.findrecipe.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.recycleclick.RecycleClick;
import com.gilas.findrecipe.FragmentBusinessLogic;
import com.gilas.findrecipe.RecipeActivity;
import com.gilas.findrecipe.adapters.RecipeRecyclerAdapter;
import com.gilas.findrecipe.adapters.SearchRecyclerAdapter;
import com.gilas.findrecipe.adapters.TagFlexRecyclerAdapter;
import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.data.Tag;
import com.gilas.findrecipe.databinding.FragmentHomeBinding;
import com.gilas.findrecipe.dboperations.DatabaseOperations;
import com.gilas.findrecipe.viewmodels.HomeViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener {

    //    private static String TAG = "HomeFragment";
    public static String RECIPE_OBJECT_EXTRA = "recipe_object";

    private static List<Tag> listTags, listSearchedTags;
    private static List<Recipe> listRecipes;
    private RecyclerView searchRecyclerView, flexBoxRecyclerView, recipeRecyclerView;
    private SearchView searchView;
    private Button btnSearchRecipe;
    private TextView tvTable;
    private HomeViewModel homeViewModel;
    private TagFlexRecyclerAdapter flexRecyclerAdapter;
    private SearchRecyclerAdapter searchRecyclerAdapter;
    private RecipeRecyclerAdapter recipeRecyclerAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listTags = new DatabaseOperations().getAllTags(getContext());

        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        tvTable = binding.tvTable;
        searchRecyclerView = binding.searchRecyclerHome;
        flexBoxRecyclerView = binding.tagFlexRecyclerView;
        recipeRecyclerView = binding.recipeRecyclerHome;
        searchView = binding.searchViewHome;
        btnSearchRecipe = binding.btnSearch;

        btnSearchRecipe.setOnClickListener(this);

        recycler();


        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        homeViewModel.getListTags().observe(getViewLifecycleOwner(), new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                Toast.makeText(getContext(), "deneme", Toast.LENGTH_SHORT).show();
                flexRecyclerAdapter.setTags(tags);
                flexRecyclerAdapter.notifyDataSetChanged();
            }
        });

        homeViewModel.getIsFlexEmpty().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                tvTable.setVisibility(((aBoolean) ? View.VISIBLE : View.INVISIBLE));
            }
        });

        homeViewModel.getListRecipes().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipeList) {
                recipeRecyclerAdapter.setRecipes(recipeList);
                recipeRecyclerAdapter.notifyDataSetChanged();
            }
        });

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    listSearchedTags = FragmentBusinessLogic.search(listTags, s);
                    searchRecyclerAdapter.setTags(listSearchedTags);
                    searchRecyclerAdapter.notifyDataSetChanged();
                    return true;
                }
            });
        }

        flexBox();

        searchRecycleClick();

    }

    private void recycler(){
        searchRecyclerAdapter = new SearchRecyclerAdapter(new ArrayList<Tag>());
        searchRecyclerView.setAdapter(searchRecyclerAdapter);

        recipeRecyclerAdapter = new RecipeRecyclerAdapter(new ArrayList<Recipe>());
        recipeRecyclerView.setAdapter(recipeRecyclerAdapter);
    }

    private void recipeRecycleClick(final List<Recipe> listRecipes) {
        RecycleClick.addTo(recipeRecyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                Intent intent = new Intent(getContext(), RecipeActivity.class);
                intent.putExtra(RECIPE_OBJECT_EXTRA, listRecipes.get(i));
                startActivity(intent);

                searchView.clearFocus();
            }
        });
    }

    private void searchRecycleClick() {
        RecycleClick.addTo(searchRecyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                homeViewModel.addListTags(listSearchedTags.get(i));
                flexRecyclerAdapter.setTags(homeViewModel.getListTags().getValue());
                flexRecyclerAdapter.notifyDataSetChanged();

            }
        });
    }

    private void flexBox() {

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        flexBoxRecyclerView.setLayoutManager(layoutManager);

        flexRecyclerAdapter = new TagFlexRecyclerAdapter(homeViewModel.getListTags().getValue(), new TagFlexRecyclerAdapter.OnDeleteListener() {
            @Override
            public void onItemClick(int position) {
                homeViewModel.removeListTags(position);
            }
        });
        flexBoxRecyclerView.setAdapter(flexRecyclerAdapter);
        flexRecyclerAdapter.notifyDataSetChanged();

    }

    private void searchButtonClick() {
        searchView.clearFocus();

        List<Integer> listSelectedTagID = new ArrayList<>();
            for (Tag tag : homeViewModel.getListTags().getValue()) {
                listSelectedTagID.add(tag.getId());
            }

//        listSelectedTagID.add(10);
//        listSelectedTagID.add(7);
//        listSelectedTagID.add(5);


        new DatabaseOperations().getRecipes(getContext(), listSelectedTagID, new DatabaseOperations.VolleyCallback() {
            @Override
            public void onSuccess(List<Recipe> result) {

                homeViewModel.setListRecipes(result);
                recipeRecycleClick(result);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == btnSearchRecipe) {
            searchButtonClick();
        }
    }

}
