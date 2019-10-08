package com.gilas.findrecipe.fragments;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

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
    View firstTab, secondTab, thirdTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif_bul);

        tabLayout = findViewById(R.id.tablayout);
        tabHome = findViewById(R.id.tabHome);
        tabRecipe = findViewById(R.id.tabRecipe);
        tabFav = findViewById(R.id.tabFav);
        viewPager = findViewById(R.id.viewPager);

        firstTab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(0);
        secondTab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(1);
        thirdTab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(2);


        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(fragmentPageAdapter);

        firstTab.setBackground(getDrawable(R.drawable.selected_tab_left));
        secondTab.setBackground(getDrawable(R.drawable.selected_tab));
        thirdTab.setBackground(getDrawable(R.drawable.selected_tab_right));

        tabLayoutListener();
    }

    private void tabLayoutListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                firstTab.setBackground(getDrawable(R.drawable.selected_tab_left));
                secondTab.setBackground(getDrawable(R.drawable.selected_tab));
                thirdTab.setBackground(getDrawable(R.drawable.selected_tab_right));

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