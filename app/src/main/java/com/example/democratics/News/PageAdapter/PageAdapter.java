package com.example.democratics.News.PageAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.democratics.News.Fragment.BusinessFragment;
import com.example.democratics.News.Fragment.TopFragment;
import com.example.democratics.News.Fragment.PoliticsFragment;
import com.example.democratics.News.Fragment.ScienceFragment;
import com.example.democratics.News.Fragment.TechFragment;
import com.example.democratics.News.Fragment.HealthFragment;

public class PageAdapter extends FragmentPagerAdapter {

    int tabCount;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new TopFragment();
            }
            case 1: {
                return new PoliticsFragment();
            }
            case 2: {
                return new HealthFragment();
            }
            case 3: {
                return new BusinessFragment();
            }
            case 4: {
                return new ScienceFragment();
            }
            case 5: {
                return new TechFragment();
            }
            default:
                return new TopFragment();
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
