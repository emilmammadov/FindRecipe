package com.gilas.findrecipe;

import androidx.annotation.NonNull;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.data.Tag;

import java.util.ArrayList;
import java.util.List;

public class FragmentBusinessLogic {

    public static List<Tag> searchTag(@NonNull List<Tag> listTags, String str) {
        List<Tag> listSearchedTags = new ArrayList<>();
        str = toFormal(str);

        for (Tag object : listTags) {
            String tagName = toFormal(object.getName());
            if (tagName.contains(str) && str.length() != 0) {
                listSearchedTags.add(object);
            }
        }

        return listSearchedTags;
    }

    public static List<Recipe> searchRecipeTitle(@NonNull List<Recipe> listRecipes, String str) {
        List<Recipe> listSearchedRecipes = new ArrayList<>();
        str = toFormal(str);

        for (Recipe object : listRecipes) {
            String recipeTitle = toFormal(object.getTitle());
            if (recipeTitle.contains(str) && str.length() != 0) {
                listSearchedRecipes.add(new Recipe(object.getId(), object.getTitle()));
            }
        }
        return listSearchedRecipes;
    }


    private static String toFormal(String str) {
        str = str.toLowerCase();
        str = str.replaceAll("ç", "c")
                .replaceAll("ğ", "g")
                .replaceAll("ı", "i")
                .replaceAll("ö", "o")
                .replaceAll("ş", "s")
                .replaceAll("ü", "u");

        return str;
    }
}
