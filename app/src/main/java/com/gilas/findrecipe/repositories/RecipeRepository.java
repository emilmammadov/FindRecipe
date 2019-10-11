package com.gilas.findrecipe.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.data.RecipeDao;
import com.gilas.findrecipe.data.RecipeDb;

import java.util.List;

public class RecipeRepository {
    private RecipeDao recipeDao;

    public RecipeRepository(Application application) {
        recipeDao = RecipeDb.getDatabase(application).recipeDao();
    }

    public void deleteRecipe(int id) {
        new DeleteRecipeAsync(recipeDao).execute(id);
    }

    public boolean isRecipeExists(int id) {
        return new IsRecipeExistsAsync(recipeDao).doInBackground(id);
    }

    public Recipe getRecipe(int id) {
        return new GetRecipeAsync(recipeDao).doInBackground(id);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return new GetAllRecipeAsync(recipeDao).doInBackground();
    }

    public void insertRecipeTbl(Recipe recipe) {
        new InsertRecipeAsync(recipeDao).execute(recipe);
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