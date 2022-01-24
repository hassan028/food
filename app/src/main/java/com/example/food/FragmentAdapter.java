package com.example.food;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    int noOfFragments;
    public FragmentAdapter(@NonNull FragmentManager fm, int numberOfFragments) {

        super(fm);
        this.noOfFragments = numberOfFragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) { //position default value is zero
        if(position == 0){
            PopularFragment fragmentOne = new PopularFragment();
            return fragmentOne;
        }
//        else if(position == 1){
//            Fragment fragmentTwo = new BestFragment();
//            return fragmentTwo;
//        }
        else{
            Fragment fragmentTwo = new BestFragment();
            return fragmentTwo;
        }

    }

    @Override
    public int getCount() {
        return 5;
    }   // slide count
}
