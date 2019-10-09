package com.gilas.findrecipe.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.gilas.findrecipe.Entities.Recipe;
import com.gilas.findrecipe.room.RecipeDao;
import com.gilas.findrecipe.room.RecipeDb;

import java.util.List;

public class RecipeRepository {
    private RecipeDao recipeDao;
    private LiveData<List<Recipe>> allRecipes;

    public RecipeRepository(Application application) {
        RecipeDb recipeDb = RecipeDb.getInstance(application);
        recipeDao = recipeDb.recipeDao();
        //allRecipes = recipeDao.getAllRecipes();
    }

    public void deleteRecipe(int id) {
        new DeleteRecipeAync(recipeDao).doInBackground(id);
    }

    public boolean isRecipeExists(int id) {
        return new IsRecipeExistsAync(recipeDao).doInBackground(id);
    }

    public Recipe getRecipe(int id) {
        return new GetRecipeAync(recipeDao).doInBackground(id);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return new GetAllRecipeAync(recipeDao).doInBackground();
    }

    public void insertRecipeTbl(Recipe recipe) {
        new InsertRecipeAync(recipeDao).doInBackground(recipe);
    }




    private static class DeleteRecipeAync extends AsyncTask<Integer, Void, Void> {
        private RecipeDao recipeDao;

        public DeleteRecipeAync(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            recipeDao.deleteRecipe(integers[0]);
            return null;
        }
    }

    private static class IsRecipeExistsAync extends AsyncTask<Integer, Void, Boolean> {
        private RecipeDao recipeDao;

        public IsRecipeExistsAync(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }


        @Override
        protected Boolean doInBackground(Integer... integers) {
            return recipeDao.isRecipeExists(integers[0]);
        }
    }

    private static class GetRecipeAync extends AsyncTask<Integer, Void, Recipe> {
        private RecipeDao recipeDao;

        public GetRecipeAync(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Recipe doInBackground(Integer... integers) {

            return recipeDao.getRecipe(integers[0]);
        }
    }

    private static class GetAllRecipeAync extends AsyncTask<Void, Void, LiveData<List<Recipe>>> {
        private RecipeDao recipeDao;

        public GetAllRecipeAync(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected LiveData<List<Recipe>> doInBackground(Void... voids) {

            return recipeDao.getAllRecipes();
        }
    }


    private static class InsertRecipeAync extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao recipeDao;

        public InsertRecipeAync(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.insertRecipeTbl(recipes[0]);
            return null;
        }
    }
}
