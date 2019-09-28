package com.gilas.findrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.gilas.findrecipe.Database.Recipes;
import com.gilas.findrecipe.Fragments.HomeFragment;

public class RecipeActivity extends AppCompatActivity {

//    private int id;
//    private String title;
//    private String ingredientList;
//    private String body;
//    private int personCount = -1;
//    private int prepTimeSec = -1;
//    private int cookTimeSec = -1;

    TextView tvTitle, tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        tvTitle = findViewById(R.id.titleRecipeActivity);
        tvBody = findViewById(R.id.bodyRecipeActivity);

        Recipes recipeExtra = (Recipes) getIntent().getSerializableExtra(HomeFragment.RECIPE_OBJECT_EXTRA);

        tvTitle.setText(recipeExtra.getIngredientList());
        tvBody.setText(recipeExtra.getBody());




    }
}
