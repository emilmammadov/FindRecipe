package com.gilas.findrecipe.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(instance).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private RecipeDao recipeDao;

        public PopulateDbAsync(RecipeDb recipeDb) {
            this.recipeDao = recipeDb.recipeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recipeDao.insertRecipeTbl(new Recipe(0,"","","",0,0,0));
            return null;
        }
    }

}
