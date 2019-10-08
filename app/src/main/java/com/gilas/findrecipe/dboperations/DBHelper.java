package com.gilas.findrecipe.dboperations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gilas.findrecipe.Entities.Recipes;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recipedb";
    private static final String TABLE_RECIPE = "recipetbl";
    private static final String TABLE_TAG = "tagtbl";
    private static final String TABLE_RELATION = "tagrelationtbl";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public boolean deleteRecipe (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_RECIPE, "id = " + id, null)>0;
    }

    public boolean isRecipeExists(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String Query = "Select * from "+ TABLE_RECIPE +" where id = " + id;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Recipes getRecipe(int id) {
        Recipes recipe = new Recipes();

        SQLiteDatabase db = this.getWritableDatabase();

         String sqlQuery = "SELECT  * FROM " + TABLE_RECIPE + " where id=" + id;
         Cursor cursor = db.rawQuery(sqlQuery, null);


        while (cursor.moveToNext()) {
                recipe = new Recipes(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2),cursor.getString(3), cursor.getInt(4),
                    cursor.getInt(5), cursor.getInt(6));
        }
        return recipe;
    }

    public ArrayList<Recipes> getAllRecipes() {
        ArrayList<Recipes> recipes = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        // String sqlQuery = "SELECT  * FROM " + TABLE_COUNTRIES;
        // Cursor cursor = db.rawQuery(sqlQuery, null);

        Cursor cursor = db.query(TABLE_RECIPE, new String[]{"id", "title", "ingredient_list",
                        "body", "person_count", "prep_time", "cook_time"},
                null, null, null, null, null);


        while (cursor.moveToNext()) {
            Recipes recipe = new Recipes(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2),cursor.getString(3), cursor.getInt(4),
                    cursor.getInt(5), cursor.getInt(6));

            recipes.add(recipe);
        }

        return recipes;
    }

    public void insertRecipeTbl(Recipes recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", recipe.getId());
        values.put("title", recipe.getTitle());
        values.put("ingredient_list", recipe.getIngredientList());
        values.put("body", recipe.getBody());
        values.put("person_count", recipe.getPersonCount());
        values.put("prep_time", recipe.getPrepTimeSec());
        values.put("cook_time", recipe.getCookTimeSec());


        db.insert(TABLE_RECIPE, null, values);
        db.close();
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String createRecipeTable = "CREATE TABLE 'recipetbl'" +
                "  ('id' INTEGER NOT NULL," +
                "  'title' TEXT COLLATE NOCASE NOT NULL," +
                "  'ingredient_list' TEXT COLLATE NOCASE NOT NULL," +
                "  'body' TEXT COLLATE NOCASE NOT NULL," +
                "  'person_count' INTEGER NOT NULL," +
                "  'prep_time' INTEGER NOT NULL," +
                "  'cook_time' INTEGER NOT NULL" +
                "); ";

        String createTagRelationTable = "CREATE TABLE 'tagrelationtbl' (" +
                "  'id' INTEGER NOT NULL," +
                "  'recipe_id' INTEGER NOT NULL," +
                "  'tag_id' INTEGER NOT NULL" +
                ");";

        String createTagTable = "CREATE TABLE 'tagtbl' (" +
                "  'id' INTEGER NOT NULL," +
                "  'name' TEXT COLLATE NOCASE NOT NULL" +
                ");";


        sqLiteDatabase.execSQL(createRecipeTable);
        sqLiteDatabase.execSQL(createTagRelationTable);
        sqLiteDatabase.execSQL(createTagTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RELATION);
        onCreate(sqLiteDatabase);
    }
}
