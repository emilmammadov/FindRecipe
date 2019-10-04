package com.gilas.findrecipe;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gilas.findrecipe.Database.DatabaseOperations;
import com.gilas.findrecipe.Database.Recipes;
import com.gilas.findrecipe.Fragments.HomeFragment;
import com.gilas.findrecipe.SQLite.DBHelper;

public class RecipeActivity extends AppCompatActivity {

    private int id;
//    private String title;
//    private String ingredientList;
//    private String body;
//    private int personCount = -1;
//    private int prepTimeSec = -1;
//    private int cookTimeSec = -1;

    TextView tvTitle, tvBody;
    TextView tvPerson, tvPrep, tvCook;
    CheckBox bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        final Recipes recipeExtra = (Recipes) getIntent().getSerializableExtra(HomeFragment.RECIPE_OBJECT_EXTRA);
        id = recipeExtra.getId();
        String personText = recipeExtra.getPersonCount() + " " + getResources().getString(R.string.person);
        String prepText = recipeExtra.getPrepTimeSec() + " " + getResources().getString(R.string.minute);
        String cookText = recipeExtra.getCookTimeSec() + " " + getResources().getString(R.string.minute);

        tvPerson = findViewById(R.id.personCountRecipeActivity);
        tvPrep = findViewById(R.id.prepTimeRecipeActivity);
        tvCook = findViewById(R.id.cookTimeRecipeActivity);
        tvTitle = findViewById(R.id.titleRecipeActivity);
        tvBody = findViewById(R.id.bodyRecipeActivity);
        bookmark = findViewById(R.id.bkmrkCheckbox);

        tvTitle.setText(recipeExtra.getTitle());
        tvBody.setText(recipeExtra.getBody());
        tvPerson.setText(personText);
        tvPrep.setText(prepText);
        tvCook.setText(cookText);

        if (new DBHelper(getApplicationContext()).isRecipeExists(id)) {
            bookmark.setChecked(true);
        }


        bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                    new DatabaseOperations().getFavRecipe(getApplicationContext(), id, new DatabaseOperations.RecipeCallback() {
                        @Override
                        public void onSuccess(Recipes result) {
                            new DBHelper(getApplicationContext()).insertRecipeTbl(result);
                        }
                    });

                } else {

                    new DBHelper(getApplicationContext()).deleteRecipe(id);


                }
            }
        });


    }
}
