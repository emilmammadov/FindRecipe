package com.gilas.findrecipe.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.gilas.findrecipe.R;
import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.data.RecipeDao;
import com.gilas.findrecipe.data.RecipeDb;
import com.gilas.findrecipe.dboperations.DatabaseOperations;

import java.util.List;

public class RecipeRepository {
    private RecipeDao recipeDao;
    private Application application;

    public RecipeRepository(Application application) {
        this.application = application;
        recipeDao = RecipeDb.getDatabase(application).recipeDao();
    }

    public void deleteRecipe(int id) {
        new DeleteRecipeAsync(recipeDao).execute(id);
    }

    public boolean isRecipeExists(int id) {
        return new IsRecipeExistsAsync(recipeDao).doInBackground(id);
    }

    public void getRecipe(int id, final CallbackRecipe callback) {
        if (isRecipeExists(id)) {
            callback.onSuccess(new GetRecipeAsync(recipeDao).doInBackground(id));
        } else {
            new DatabaseOperations(application).getRecipe(id, new DatabaseOperations.RecipeCallback() {
                @Override
                public void onSuccess(Recipe result) {
                    callback.onSuccess(result);
                }
            });
        }


    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return new GetAllRecipeAsync(recipeDao).doInBackground();
    }

    public void insertRecipeTbl(Recipe recipe) {
        new InsertRecipeAsync(recipeDao).execute(recipe);
    }

    public void getHomeRecipeList(List<Integer> listSelectedTagID, final CallbackHomeRecipe callback) {
        new DatabaseOperations(application).getRecipes(listSelectedTagID, new DatabaseOperations.RecipeWithTagCallback() {
            @Override
            public void onSuccess(List<Recipe> justRecipes, List<Recipe> maybeRecipes) {
                callback.onSuccess(justRecipes, maybeRecipes);
            }
        });
    }

    public void getAllRecipeTitles(final CallbackListRecipe callback) {
        new DatabaseOperations(application).getAllRecipeTitles(new DatabaseOperations.VolleyCallback() {
            @Override
            public void onSuccess(List<Recipe> result) {
                callback.onSuccess(result);
            }
        });
    }


    public interface CallbackListRecipe {
        void onSuccess(List<Recipe> result);
    }

    public interface CallbackHomeRecipe {
        void onSuccess(List<Recipe> justRecipes, List<Recipe> maybeRecipes);
    }

    public interface CallbackRecipe {
        void onSuccess(Recipe recipe);
    }


    private static class DeleteRecipeAsync extends AsyncTask<Integer, Void, Void> {
        private RecipeDao recipeDao;

        DeleteRecipeAsync(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            recipeDao.deleteRecipe(integers[0]);
            return null;
        }
    }

    private static class IsRecipeExistsAsync extends AsyncTask<Integer, Void, Boolean> {
        private RecipeDao recipeDao;

        IsRecipeExistsAsync(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }


        @Override
        protected Boolean doInBackground(Integer... integers) {
            return recipeDao.isRecipeExists(integers[0]);
        }
    }

    private static class GetRecipeAsync extends AsyncTask<Integer, Void, Recipe> {
        private RecipeDao recipeDao;

        GetRecipeAsync(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Recipe doInBackground(Integer... integers) {

            return recipeDao.getRecipe(integers[0]);
        }
    }

    private static class GetAllRecipeAsync extends AsyncTask<Void, Void, LiveData<List<Recipe>>> {
        private RecipeDao recipeDao;

        GetAllRecipeAsync(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected LiveData<List<Recipe>> doInBackground(Void... voids) {

            return recipeDao.getAllRecipes();
        }

    }


    private static class InsertRecipeAsync extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao recipeDao;

        InsertRecipeAsync(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.insertRecipeTbl(recipes[0]);
            return null;
        }
    }
}
