package com.gilas.findrecipe.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gilas.findrecipe.Entities.Recipe;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RecipeDao {

    @Query("DELETE FROM recipetbl WHERE id=:id")
    void deleteRecipe(int id);

    @Query("select count(*) != 0 from recipetbl where id=:id")
    boolean isRecipeExists(int id);

    @Query("SELECT * FROM recipetbl WHERE id=:id")
    Recipe getRecipe(int id);

    @Query("SELECT * FROM recipetbl")
    LiveData<List<Recipe>> getAllRecipes();

    @Insert
    void insertRecipeTbl(Recipe recipe);

}
