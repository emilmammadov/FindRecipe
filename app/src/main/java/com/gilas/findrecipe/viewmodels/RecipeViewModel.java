package com.gilas.findrecipe.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gilas.findrecipe.Entities.Recipe;
import com.gilas.findrecipe.repositories.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {
    private RecipeRepository repository;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        repository = new RecipeRepository(application);
    }

    public void deleteRecipe(int id) {
        repository.deleteRecipe(id);
    }

    public boolean isRecipeExists(int id) {
        return repository.isRecipeExists(id);
    }

    public Recipe getRecipe(int id) {
        return repository.getRecipe(id);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return repository.getAllRecipes();
    }

    public void insertRecipeTbl(Recipe recipe) {
        repository.insertRecipeTbl(recipe);
    }
}
