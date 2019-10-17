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
    private RecyclerView searchRecyclerView, flexBoxRecyclerView,
            justRecyclerView, maybeRecyclerView;
    private SearchView searchView;
    private Button btnSearchRecipe;
    private TextView tvTable, tvJustExp, tvMaybeExp;
    private HomeViewModel homeViewModel;
    private TagFlexRecyclerAdapter flexRecyclerAdapter;
    private SearchRecyclerAdapter searchRecyclerAdapter;
    private RecipeRecyclerAdapter justRecyclerAdapter, maybeRecyclerAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        tvTable = binding.tvTable;
        tvJustExp = binding.justExp;
        tvMaybeExp = binding.maybeExp;
        searchRecyclerView = binding.searchRecyclerHome;
        flexBoxRecyclerView = binding.tagFlexRecyclerView;
        justRecyclerView = binding.justRecipeRecyclerHome;
        maybeRecyclerView = binding.maybeRecipeRecyclerHome;
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

        listTags = homeViewModel.getAllTags();

        homeViewModel.getListTags().observe(getViewLifecycleOwner(), new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
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

        homeViewModel.getJustRecipes().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> justRecipes) {
                if (justRecipes.size() != 0) tvJustExp.setVisibility(View.VISIBLE);
                else tvJustExp.setVisibility(View.GONE);
                justRecyclerAdapter.setRecipes(justRecipes);
                justRecyclerAdapter.notifyDataSetChanged();
                recipeRecycleClick(justRecipes, justRecyclerView);
            }
        });

        homeViewModel.getMaybeRecipes().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> maybeRecipes) {
                if (maybeRecipes.size() != 0) tvMaybeExp.setVisibility(View.VISIBLE);
                else tvMaybeExp.setVisibility(View.GONE);
                maybeRecyclerAdapter.setRecipes(maybeRecipes);
                maybeRecyclerAdapter.notifyDataSetChanged();
                recipeRecycleClick(maybeRecipes, maybeRecyclerView);
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
                    listSearchedTags = FragmentBusinessLogic.searchTag(listTags, s);
                    searchRecyclerAdapter.setTags(listSearchedTags);
                    searchRecyclerAdapter.notifyDataSetChanged();
                    return true;
                }
            });
        }

        flexBox();

        searchRecycleClick();

    }

    private void recycler() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        searchRecyclerView.setLayoutManager(layoutManager);
        searchRecyclerAdapter = new SearchRecyclerAdapter(new ArrayList<Tag>());
        searchRecyclerView.setAdapter(searchRecyclerAdapter);

        justRecyclerAdapter = new RecipeRecyclerAdapter(new ArrayList<Recipe>());
        justRecyclerView.setAdapter(justRecyclerAdapter);

        maybeRecyclerAdapter = new RecipeRecyclerAdapter(new ArrayList<Recipe>());
        maybeRecyclerView.setAdapter(maybeRecyclerAdapter);
    }

    private void recipeRecycleClick(final List<Recipe> listRecipes, RecyclerView recyclerView) {
        RecycleClick.addTo(recyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
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

        homeViewModel.setHomeRecipeList(listSelectedTagID);

    }

    @Override
    public void onClick(View view) {
        if (view == btnSearchRecipe) {
            searchButtonClick();
        }
    }

}
