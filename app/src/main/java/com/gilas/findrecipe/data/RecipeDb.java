package com.gilas.findrecipe.data;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Recipe.class, Tag.class, TagRelation.class}, exportSchema = false, version = 1)
public abstract class RecipeDb extends RoomDatabase {

    private static RecipeDb instance;

    public abstract RecipeDao recipeDao();

    public static synchronized RecipeDb getDatabase(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application.getApplicationContext(),
                    RecipeDb.class, "recipedb")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


}
