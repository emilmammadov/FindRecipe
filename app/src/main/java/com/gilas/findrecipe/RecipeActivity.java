package com.gilas.findrecipe;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.databinding.ActivityRecipeBinding;
import com.gilas.findrecipe.dboperations.DatabaseOperations;
import com.gilas.findrecipe.fragments.HomeFragment;
import com.gilas.findrecipe.viewmodels.RecipeViewModel;

public class RecipeActivity extends AppCompatActivity {

    private int id;
    CheckBox bookmark;
    ActivityRecipeBinding binding;
    RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe);

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        final Recipe recipeExtra = (Recipe) getIntent().getSerializableExtra(HomeFragment.RECIPE_OBJECT_EXTRA);
        binding.setRecipe(recipeExtra);

        id = recipeExtra.getId();

        bookmark = binding.bkmrkCheckbox;

        if (recipeViewModel.isRecipeExists(id)) {
            bookmark.setChecked(true);
        }


        bookmarkListener();

    }

    private void bookmarkListener() {
        bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                    new DatabaseOperations(getApplication()).getRecipe(id, new DatabaseOperations.RecipeCallback() {
                        @Override
                        public void onSuccess(Recipe result) {
                            recipeViewModel.insertRecipeTbl(result);
                            bookmark.animate();
                        }
                    });

                } else {
                    recipeViewModel.deleteRecipe(id);

                }
            }
        });

    }
}
