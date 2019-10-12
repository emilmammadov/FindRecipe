package com.gilas.findrecipe.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.data.Tag;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private static MutableLiveData<List<Tag>> listTags;
    private static MutableLiveData<List<Recipe>> listRecipes;
    private static MutableLiveData<Boolean> isFlexEmpty;


    public HomeViewModel() {
        listTags = new MutableLiveData<>();
        listRecipes = new MutableLiveData<>();
        isFlexEmpty = new MutableLiveData<>();
        isFlexEmpty.setValue(true);
        if (listTags.getValue() == null) listTags.setValue(new ArrayList<Tag>());
        if (listRecipes.getValue() == null) listRecipes.setValue(new ArrayList<Recipe>());
    }

    public LiveData<List<Tag>> getListTags() {
        return listTags;
    }

    public LiveData<Boolean> getIsFlexEmpty(){
        return isFlexEmpty;
    }

    public void addListTags(Tag input) {
        List<Tag> temp = new ArrayList<>(listTags.getValue());
        if (!temp.contains(input)) {
            temp.add(input);
            listTags.setValue(temp);
            isFlexEmpty.setValue(false);
        }
    }

    public void removeListTags(int position) {
        List<Tag> temp = new ArrayList<>(listTags.getValue());
        temp.remove(position);
        listTags.setValue(temp);
        isFlexEmpty.setValue(isTagListEmpty());
    }

    public boolean isTagListEmpty() {
        return listTags.getValue().size() == 0;
    }

    public LiveData<List<Recipe>> getListRecipes() {
        return listRecipes;
    }

    public void setListRecipes(List<Recipe> input) {
        this.listRecipes.setValue(input);
    }
}
