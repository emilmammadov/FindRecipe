package com.gilas.findrecipe.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.repositories.RecipeRepository;

import java.util.List;

public class FavViewModel extends AndroidViewModel {

    private RecipeRepository repository;

    public FavViewModel(Application application) {
        super(application);

        repository = new RecipeRepository(application);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return repository.getAllRecipes();
    }
}
