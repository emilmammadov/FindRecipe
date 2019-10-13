package com.gilas.findrecipe.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private RecipeRepository repository;
    private static List<Recipe> recipeTitles;
    private static MutableLiveData<Recipe> selectedRecipe;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        repository = new RecipeRepository(application);
        selectedRecipe = new MutableLiveData<>();
        if (recipeTitles == null) recipeTitles = new ArrayList<>();
    }

    public void getAllRecipeTitles(final CallbackListRecipe callback) {
        repository.getAllRecipeTitles(new RecipeRepository.CallbackListRecipe() {
            @Override
            public void onSuccess(List<Recipe> recipes) {
                callback.onSuccess(recipes);
            }
        });
    }

    public void getRecipe(int id, final CallbackRecipe callback) {
        repository.getRecipe(id, new RecipeRepository.CallbackRecipe() {
            @Override
            public void onSuccess(Recipe recipe) {
                callback.onSuccess(recipe);
            }
        });
    }

    public interface CallbackListRecipe {
        void onSuccess(List<Recipe> recipes);
    }

    public interface CallbackRecipe {
        void onSuccess(Recipe recipe);
    }
}
