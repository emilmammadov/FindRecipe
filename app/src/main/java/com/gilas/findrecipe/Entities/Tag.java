package com.gilas.findrecipe.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tagtbl")
public class Tag {
    @PrimaryKey
    private int id;
    private String name;

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
