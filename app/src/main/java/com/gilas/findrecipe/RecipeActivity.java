package com.gilas.findrecipe;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.dboperations.DBHelper;
import com.gilas.findrecipe.dboperations.DatabaseOperations;
import com.gilas.findrecipe.fragments.HomeFragment;
import com.gilas.findrecipe.viewmodels.RecipeViewModel;

public class RecipeActivity extends AppCompatActivity {

    private int id;

    TextView tvTitle, tvIng, tvBody;
    TextView tvPerson, tvPrep, tvCook;
    CheckBox bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        final Recipe recipeExtra = (Recipe) getIntent().getSerializableExtra(HomeFragment.RECIPE_OBJECT_EXTRA);
        id = recipeExtra.getId();
        String personText = recipeExtra.getPersonCount() + " " + getResources().getString(R.string.person);
        String prepText = (recipeExtra.getPrepTimeSec() / 60) + " " + getResources().getString(R.string.minute);
        String cookText = (recipeExtra.getCookTimeSec() / 60) + " " + getResources().getString(R.string.minute);

        tvPerson = findViewById(R.id.personCountRecipeActivity);
        tvPrep = findViewById(R.id.prepTimeRecipeActivity);
        tvCook = findViewById(R.id.cookTimeRecipeActivity);
        tvTitle = findViewById(R.id.titleRecipeActivity);
        tvIng = findViewById(R.id.ingredientRecipeActivity);
        tvBody = findViewById(R.id.bodyRecipeActivity);
        bookmark = findViewById(R.id.bkmrkCheckbox);

        tvTitle.setText(recipeExtra.getTitle());
        tvIng.setText(recipeExtra.getIngredientList());
        tvBody.setText(recipeExtra.getBody());
        tvPerson.setText(personText);
        tvPrep.setText(prepText);
        tvCook.setText(cookText);

        if (new DBHelper(getApplicationContext()).isRecipeExists(id)) {
            bookmark.setChecked(true);
        }


        bookmarkListener();

    }

    private void bookmarkListener() {
        bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                    new DatabaseOperations().getFavRecipe(getApplicationContext(), id, new DatabaseOperations.RecipeCallback() {
                        @Override
                        public void onSuccess(Recipe result) {
                            //new DBHelper(getApplicationContext()).insertRecipeTbl(result);
                            new RecipeViewModel(getApplication()).insertRecipeTbl(result);
                        }
                    });

                } else {

                    //new DBHelper(getApplicationContext()).deleteRecipe(id);
                    new RecipeViewModel(getApplication()).deleteRecipe(id);

                }
            }
        });

    }
}
