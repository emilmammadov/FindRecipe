package com.gilas.findrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gilas.findrecipe.Database.DatabaseOperations;
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
    CheckBox bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        tvTitle = findViewById(R.id.titleRecipeActivity);
        tvBody = findViewById(R.id.bodyRecipeActivity);
        bookmark = findViewById(R.id.bkmrkCheckbox);

        Recipes recipeExtra = (Recipes) getIntent().getSerializableExtra(HomeFragment.RECIPE_OBJECT_EXTRA);

        tvTitle.setText(recipeExtra.getTitle());
        tvBody.setText(recipeExtra.getBody());

        bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    new DatabaseOperations().getFavRecipe(getApplicationContext(), 1);
                }else {

                }
            }
        });






    }
}
