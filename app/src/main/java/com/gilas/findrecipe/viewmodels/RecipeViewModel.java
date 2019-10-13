package com.gilas.findrecipe.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.repositories.RecipeRepository;

public class RecipeViewModel extends AndroidViewModel {

    private RecipeRepository repository;

    public RecipeViewModel(@NonNull Application application) {
        super(application);

        repository = new RecipeRepository(application);
    }

    public void deleteRecipe(int id) {
        repository.deleteRecipe(id);
    }

    public void insertRecipeTbl(Recipe recipe) {
        repository.insertRecipeTbl(recipe);
    }

    public boolean isRecipeExists(int id) {
        return repository.isRecipeExists(id);
    }

}
