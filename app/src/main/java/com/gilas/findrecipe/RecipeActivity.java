package com.gilas.findrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.gilas.findrecipe.Fragments.HomeFragment;

public class RecipeActivity extends AppCompatActivity {

    String title, body;
    int id;
    TextView tvTitle, tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Bundle extras = getIntent().getExtras();
        title = extras.getString(HomeFragment.HOME_RECIPE_TITLE_EXTRA);
        body = extras.getString(HomeFragment.HOME_RECIPE_BODY_EXTRA);
        id = extras.getInt(HomeFragment.HOME_RECIPE_ID_EXTRA);

        tvTitle = findViewById(R.id.titleRecipeActivity);
        tvBody = findViewById(R.id.bodyRecipeActivity);
        tvTitle.setText(title);
        tvBody.setText(body);
    }
}
