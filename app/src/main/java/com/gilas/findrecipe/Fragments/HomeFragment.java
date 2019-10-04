package com.gilas.findrecipe.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.recycleclick.RecycleClick;
import com.gilas.findrecipe.Adapters.RecipeRecyclerAdapter;
import com.gilas.findrecipe.Adapters.SearchRecyclerAdapter;
import com.gilas.findrecipe.Adapters.TagFlexRecyclerAdapter;
import com.gilas.findrecipe.Database.DatabaseOperations;
import com.gilas.findrecipe.Database.Recipes;
import com.gilas.findrecipe.Database.Tags;
import com.gilas.findrecipe.R;
import com.gilas.findrecipe.RecipeActivity;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static String TAG = "HomeFragment";
    public static String RECIPE_OBJECT_EXTRA = "recipe_object";

    private static ArrayList<Tags> listTags, listSearchedTags, listSelectedTags;
    private static ArrayList<Recipes> listRecipes;
    private RecyclerView searchRecyclerView, flexBoxRecyclerView, recipeRecyclerView;
    private SearchView searchView;
    private View view;
    private Button btnSearchRecipe;
    private TextView tvTable;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listTags = new DatabaseOperations().getAllTags(getContext());

        view = inflater.inflate(R.layout.fragment_home, container, false);

        tvTable = view.findViewById(R.id.tvTable);
        searchRecyclerView = view.findViewById(R.id.searchRecyclerHome);
        flexBoxRecyclerView = view.findViewById(R.id.tagFlexRecyclerView);
        recipeRecyclerView = view.findViewById(R.id.recipeRecyclerHome);
        searchView = view.findViewById(R.id.searchViewHome);
        btnSearchRecipe = view.findViewById(R.id.btnSearch);
        searchView.setOnClickListener(this);
        btnSearchRecipe.setOnClickListener(this);

        listSelectedTags = new ArrayList<>();

        flexBox();

        searchRecycleClick();


        return view;
    }

    private void recipeRecycleClick(final ArrayList<Recipes> listRecipes) {
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

                if (!listSelectedTags.contains(listSearchedTags.get(i))) {
                    tvTable.setVisibility(View.INVISIBLE);
                    listSelectedTags.add(listSearchedTags.get(i));
                    flexBox();
                }

            }
        });
    }

    private void flexBox() {

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        flexBoxRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new TagFlexRecyclerAdapter(listSelectedTags, new TagFlexRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                listSelectedTags.remove(position);
                if (listSelectedTags.size() == 0) tvTable.setVisibility(View.VISIBLE);
                flexBox();
            }
        });
        flexBoxRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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
        listSearchedTags = new ArrayList<>();

        if (listTags != null) {
            for (Tags object : listTags) {
                String tagName = object.getName().toLowerCase();
                if (tagName.contains(str.toLowerCase()) && str.length() != 0) {
                    listSearchedTags.add(object);
                }
            }
        }

        SearchRecyclerAdapter searchRecyclerAdapter = new SearchRecyclerAdapter(listSearchedTags);
        searchRecyclerView.setAdapter(searchRecyclerAdapter);

    }

    @Override
    public void onClick(View view) {
        if (view == searchView) {
            searchView.onActionViewExpanded();
        }
        if (view == btnSearchRecipe) {
            searchView.clearFocus();

            ArrayList<Integer> listSelectedTagID = new ArrayList<>();
            for (Tags tag : listSelectedTags) {
                listSelectedTagID.add(tag.getId());
            }

            //listSelectedTagID.add(10);
            //listSelectedTagID.add(7);
            //listSelectedTagID.add(5);


            new DatabaseOperations().getRecipes(getContext(), listSelectedTagID, new DatabaseOperations.VolleyCallback() {
                @Override
                public void onSuccess(ArrayList<Recipes> result) {

                    listRecipes = result;
                    recipeRecycleClick(listRecipes);
                    RecipeRecyclerAdapter adapter = new RecipeRecyclerAdapter(result);
                    recipeRecyclerView.setAdapter(adapter);
                }
            });


        }


    }

}
