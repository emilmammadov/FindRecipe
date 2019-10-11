package com.gilas.findrecipe.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.data.Tag;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Tag>> listTags;
    private MutableLiveData<List<Recipe>> listRecipes;

    public MutableLiveData<List<Tag>> getListTags() {
        return listTags;
    }

    public void setListTags(MutableLiveData<List<Tag>> listTags) {
        this.listTags = listTags;
    }

    public MutableLiveData<List<Recipe>> getListRecipes() {
        return listRecipes;
    }

    public void setListRecipes(MutableLiveData<List<Recipe>> listRecipes) {
        this.listRecipes = listRecipes;
    }
}
