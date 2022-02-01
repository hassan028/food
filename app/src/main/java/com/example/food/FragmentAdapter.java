package com.example.food;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    int noOfFragments;
    //private static final String TAG = "My Tag";

    public FragmentAdapter(@NonNull FragmentManager fm, int numberOfFragments) {

        super(fm);
        this.noOfFragments = numberOfFragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) { //position default value is zero
//        Log.d(TAG,"onFailure: "+ position);
        if(position == 0){
            Fragment fragmentOne = new PopularFragment();
            return fragmentOne;
        }
        else if(position == 1){
            FragmentTwo fragmentTwo = new FragmentTwo();
            return fragmentTwo;
        }
        else{
            Fragment fragmentThree = new FragmentTwo();
            return fragmentThree;
        }


    }

    @Override
    public int getCount() {
        return 3;
    }   // slide count
}
