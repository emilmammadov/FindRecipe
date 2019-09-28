package com.gilas.findrecipe.Database;

import java.io.Serializable;

public class Recipes implements Serializable {
    private int id;
    private String title;
    private String ingredientList;
    private String body;
    private int personCount;
    private int prepTimeSec;
    private int cookTimeSec;


    public Recipes(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(String ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getPrepTimeSec() {
        return prepTimeSec;
    }

    public void setPrepTimeSec(int prepTimeSec) {
        this.prepTimeSec = prepTimeSec;
    }

    public int getCookTimeSec() {
        return cookTimeSec;
    }

    public void setCookTimeSec(int cookTimeSec) {
        this.cookTimeSec = cookTimeSec;
    }
}
