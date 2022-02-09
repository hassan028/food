package com.example.food;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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

        for(int i=0; i< AllData.categoryList.size(); i++){
            if(position == i){
                Bundle bundle = new Bundle();
                bundle.putString("Fragment", AllData.categoryList.get(i).getName());
                Fragment fragment = new DynamicFragment();
                fragment.setArguments(bundle);
                return fragment;
            }
        }
        return null;

       /* if(position == 0){

            Fragment fragmentOne = new DynmicFragment();
            return fragmentOne;
        }
        else if(position == 1){
            FragmentTwo fragmentTwo = new FragmentTwo();
            return fragmentTwo;
        }
        else{
            Fragment fragmentThree = new FragmentTwo();
            return fragmentThree;
        }*/


    }

    @Override
    public int getCount() {
        return AllData.categoryList.size();
    }   // slide count
}
