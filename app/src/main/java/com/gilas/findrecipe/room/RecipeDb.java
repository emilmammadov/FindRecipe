package com.gilas.findrecipe.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gilas.findrecipe.Entities.Recipe;
import com.gilas.findrecipe.Entities.Tag;
import com.gilas.findrecipe.Entities.TagRelation;

@Database(entities = {Recipe.class, Tag.class, TagRelation.class}, exportSchema = false, version = 1)
public abstract class RecipeDb extends RoomDatabase {

    private static RecipeDb instance;

    public abstract RecipeDao recipeDao();

    public static synchronized RecipeDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecipeDb.class, "recipedb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
