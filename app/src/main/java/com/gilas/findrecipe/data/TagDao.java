package com.gilas.findrecipe.data;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TagDao {

    @Query("SELECT * FROM tagtbl")
    List<Tag> getAllTags();


}
