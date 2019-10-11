package com.gilas.findrecipe.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tagrelationtbl")
public class TagRelation {

    @PrimaryKey
    private int id;
    private int recipe_id;
    private int tag_id;

    public TagRelation(int id, int recipe_id, int tag_id) {
        this.id = id;
        this.recipe_id = recipe_id;
        this.tag_id = tag_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
}
