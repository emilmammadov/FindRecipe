package com.gilas.findrecipe.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    @ColumnInfo(name = "sharer_id")
    private int sharerId;
    @Ignore
    private String timeSumMin;
    @Ignore
    private String personCountString;
    @Ignore
    private String prepTimeMin;
    @Ignore
    private String cookTimeMin;

    public Recipe(int id, int sharerId, String title, String ingredientList, String body, int personCount, int prepTimeSec, int cookTimeSec) {
        this.id = id;
        this.title = title;
        this.ingredientList = ingredientList;
        this.body = body;
        this.personCount = personCount;
        this.prepTimeSec = prepTimeSec;
        this.cookTimeSec = cookTimeSec;
        this.sharerId = sharerId;

        timeSumMin = ((prepTimeSec + cookTimeSec) / 60) + "";
        prepTimeMin = (prepTimeSec / 60) + "";
        cookTimeMin = (cookTimeSec / 60) + "";
        personCountString = personCount + "";

    }

    @Ignore
    public Recipe(int id, String title) {
        this.id = id;
        sharerId = 0;
        this.title = title;
        ingredientList = null;
        body = null;
        personCount = 0;
        prepTimeSec = 0;
        cookTimeSec = 0;
    }

    public String getPrepTimeMin() {
        return prepTimeMin;
    }

    public String getCookTimeMin() {
        return cookTimeMin;
    }

    public String getPersonCountString() {
        return personCountString;
    }

    public String getTimeSumMin() {
        return timeSumMin;
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

    public int getSharerId() {
        return sharerId;
    }

    public void setSharerId(int sharerId) {
        this.sharerId = sharerId;
    }
}
