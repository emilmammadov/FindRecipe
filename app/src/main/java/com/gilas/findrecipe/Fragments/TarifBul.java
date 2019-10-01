package com.gilas.findrecipe.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.gilas.findrecipe.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TarifBul extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tabHome, tabRecipe, tabFav;
    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif_bul);

        tabLayout = findViewById(R.id.tablayout);
        tabHome = findViewById(R.id.tabHome);
        tabRecipe = findViewById(R.id.tabRecipe);
        tabFav = findViewById(R.id.tabFav);
        viewPager = findViewById(R.id.viewPager);


        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(fragmentPageAdapter);

        tabLayoutListener();
    }

    private void tabLayoutListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}