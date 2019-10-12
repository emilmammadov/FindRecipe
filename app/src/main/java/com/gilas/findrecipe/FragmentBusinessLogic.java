package com.gilas.findrecipe;

import com.gilas.findrecipe.data.Tag;

import java.util.ArrayList;
import java.util.List;

public class FragmentBusinessLogic {

    public static List<Tag> search(List<Tag> listTags, String str) {

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
}
