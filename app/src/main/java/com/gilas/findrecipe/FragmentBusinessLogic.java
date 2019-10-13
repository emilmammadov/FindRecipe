package com.gilas.findrecipe;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.data.Tag;

import java.util.ArrayList;
import java.util.List;

public class FragmentBusinessLogic {

    public static List<Tag> searchTag(List<Tag> listTags, String str) {

        List<Tag> listSearchedTags = new ArrayList<>();

        if (listTags != null) {
            for (Tag object : listTags) {
                String tagName = object.getName().toLowerCase();
                if (tagName.contains(str.toLowerCase()) && str.length() != 0) {
                    listSearchedTags.add(object);
                }
            }
        }

        return listSearchedTags;
    }

    public static List<Recipe> searchRecipe(List<Recipe> listRecipes, String str) {
        List<Recipe> listSearchedRecipes = new ArrayList<>();

        if (listRecipes != null) {
            for (Recipe object : listRecipes) {
                String tagName = object.getTitle().toLowerCase();
                if (tagName.contains(str.toLowerCase()) && str.length() != 0) {
                    listSearchedRecipes.add(new Recipe(object.getId(), tagName));
                }
            }
        }
        return listSearchedRecipes;
    }
}
