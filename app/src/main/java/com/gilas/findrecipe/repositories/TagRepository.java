package com.gilas.findrecipe.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.data.RecipeDb;
import com.gilas.findrecipe.data.Tag;
import com.gilas.findrecipe.data.TagDao;
import com.gilas.findrecipe.dboperations.DatabaseOperations;

import java.util.List;

public class TagRepository {
    private TagDao tagDao;
    private Application application;

    public TagRepository(Application application) {
        this.application = application;
        tagDao = RecipeDb.getDatabase(application).tagDao();
    }

    public List<Tag> getAllTags() {
        List<Tag> temp = new GetAllTagsAsync(tagDao).doInBackground();
        if (temp.size() == 0) {
            return new DatabaseOperations(application).getAllTags();
        }
        return temp;
    }


    private static class GetAllTagsAsync extends AsyncTask<Void, Void, List<Tag>> {
        private TagDao tagDao;

        GetAllTagsAsync(TagDao tagDao) {
            this.tagDao = tagDao;
        }

        @Override
        protected List<Tag> doInBackground(Void... voids) {
            return tagDao.getAllTags();
        }

    }
}
