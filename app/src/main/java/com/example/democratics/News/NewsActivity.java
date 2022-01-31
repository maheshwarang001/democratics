package com.example.democratics.News;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.democratics.News.PageAdapter.PageAdapter;
import com.example.democratics.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class NewsActivity extends AppCompatActivity {

    //ActivityNews2Binding binding;
    TabLayout tabLayout;
    TabItem iTop, iHealth, iPolitics, iBusiness, iTech, iScience;
    PagerAdapter pagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  binding = ActivityNews2Binding.inflate(getLayoutInflater());
       // View view = binding.getRoot();
      //  setContentView(view);
        setContentView(R.layout.activity_news);

        iTop = findViewById(R.id.top);
        iHealth = findViewById(R.id.health);
        iPolitics = findViewById(R.id.politics);
        iBusiness = findViewById(R.id.business);
        iTech = findViewById(R.id.technology);
        iScience = findViewById(R.id.science);

        ViewPager viewpager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab_layout);



        pagerAdapter = new PageAdapter(getSupportFragmentManager(),6);
        viewpager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0 || tab.getPosition() == 1 ||tab.getPosition() == 2 ||tab.getPosition() == 3 ||tab.getPosition() == 4 ||tab.getPosition() == 5 ||tab.getPosition() ==6){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }


}