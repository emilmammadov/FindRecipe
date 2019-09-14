package com.gilas.findrecipe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.gilas.findrecipe.Fragments.PageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TarifBul extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tabHome, tabRecipe, tabFav;
    ViewPager viewPager;
    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif_bul);
    }
}