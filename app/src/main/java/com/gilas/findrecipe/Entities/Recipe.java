package com.gilas.findrecipe.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "recipetbl")
public class Recipe implements Serializable {

    @PrimaryKey
    private int id;
    private String title;
    @ColumnInfo(name = "ingredient_list")
    private String ingredientList;
    private String body;
    @ColumnInfo(name = "person_count")
    private int personCount;
    @ColumnInfo(name = "prep_time")
    private int prepTimeSec;
    @ColumnInfo(name = "cook_time")
    private int cookTimeSec;

    public Recipe(int id, String title, String ingredientList, String body, int personCount, int prepTimeSec, int cookTimeSec) {
        this.id = id;
        this.title = title;
        this.ingredientList = ingredientList;
        this.body = body;
        this.personCount = personCount;
        this.prepTimeSec = prepTimeSec;
        this.cookTimeSec = cookTimeSec;
    }

    public Recipe(int id, String title) {
        this.id = id;
        this.title = title;
        ingredientList = null;
        body = null;
        personCount = 0;
        prepTimeSec = 0;
        cookTimeSec = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredientList() {
        return ingredientList;
    }

    public String getBody() {
        return body;
    }

    public int getPersonCount() {
        return personCount;
    }

    public int getPrepTimeSec() {
        return prepTimeSec;
    }

    public int getCookTimeSec() {
        return cookTimeSec;
    }
}
