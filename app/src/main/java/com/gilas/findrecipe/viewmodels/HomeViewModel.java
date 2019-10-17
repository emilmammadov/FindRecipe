package com.gilas.findrecipe.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.data.Tag;
import com.gilas.findrecipe.repositories.RecipeRepository;
import com.gilas.findrecipe.repositories.TagRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private RecipeRepository recipeRepository;
    private TagRepository tagRepository;
    private static MutableLiveData<List<Tag>> flexListTags;
    private static MutableLiveData<List<Recipe>> justRecipes;
    private static MutableLiveData<List<Recipe>> maybeRecipes;
    private static MutableLiveData<Boolean> isFlexEmpty;

    public HomeViewModel(Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        tagRepository = new TagRepository(application);
        flexListTags = new MutableLiveData<>();
        justRecipes = new MutableLiveData<>();
        maybeRecipes = new MutableLiveData<>();
        isFlexEmpty = new MutableLiveData<>();
        isFlexEmpty.setValue(true);
        if (flexListTags.getValue() == null) flexListTags.setValue(new ArrayList<Tag>());
        if (justRecipes.getValue() == null) justRecipes.setValue(new ArrayList<Recipe>());
        if (maybeRecipes.getValue() == null) maybeRecipes.setValue(new ArrayList<Recipe>());
    }

    public LiveData<List<Tag>> getListTags() {
        return flexListTags;
    }

    public LiveData<Boolean> getIsFlexEmpty() {
        return isFlexEmpty;
    }

    public void addListTags(Tag input) {
        List<Tag> temp = new ArrayList<>(flexListTags.getValue());
        if (!temp.contains(input)) {
            temp.add(input);
            flexListTags.setValue(temp);
            isFlexEmpty.setValue(false);
        }
    }

    public void removeListTags(int position) {
        List<Tag> temp = new ArrayList<>(flexListTags.getValue());
        temp.remove(position);
        flexListTags.setValue(temp);
        isFlexEmpty.setValue(isFlexBoxEmpty());
    }

    private boolean isFlexBoxEmpty() {
        return flexListTags.getValue().size() == 0;
    }

    public LiveData<List<Recipe>> getJustRecipes() {
        return justRecipes;
    }

    private void setJustRecipes(List<Recipe> input) {
        justRecipes.setValue(input);
    }

    public LiveData<List<Recipe>> getMaybeRecipes() {
        return maybeRecipes;
    }

    private void setMaybeRecipes(List<Recipe> input) {
        maybeRecipes.setValue(input);
    }

    public List<Tag> getAllTags() {
        return tagRepository.getAllTags();
    }

    public void setHomeRecipeList(List<Integer> listSelectedTagID) {
        recipeRepository.getHomeRecipeList(listSelectedTagID, new RecipeRepository.CallbackHomeRecipe() {
            @Override
            public void onSuccess(List<Recipe> justRecipes, List<Recipe> maybeRecipes) {
                setJustRecipes(justRecipes);
                setMaybeRecipes(maybeRecipes);
            }
        });
    }
}
