package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.DynamicDrawableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager pager;
    FragmentAdapter adapter;
    RelativeLayout cartpopup;

    SharedPreferences myPref;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    TextView tvCartCount,tvSubtotal;

    private static final String TAG = "My Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        cartpopup =findViewById(R.id.cartpopup);
        tvCartCount = findViewById(R.id.tvCartCount);
        tvSubtotal = findViewById(R.id.tvSubtotal);
       /* Gson gson = new Gson();
        String json = gson.toJson(AllData.cartList);*/
        SharedPreferences myPref = getSharedPreferences("Storage",MODE_PRIVATE);
        AllData.setSharedPrefrence(myPref);

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                if(key.equals("Size")){
                    int Size = myPref.getInt("Size",0);
                    Toast.makeText(MainActivity.this, String.valueOf(Size), Toast.LENGTH_SHORT).show();
                    if(Size == 0){
                        cartpopup.setVisibility(View.GONE);
                    }
                    else{
                        cartpopup.setVisibility(View.VISIBLE);
                        tvCartCount.setText(AllData.getTotalCart()+"");
                        tvSubtotal.setText("Rs. " + AllData.getCartSubtotal());
                    }


                }
            }
        };
        myPref.registerOnSharedPreferenceChangeListener(listener);


    }

    public void  initViews(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        pager = (ViewPager) findViewById(R.id.pager);


       for(Category objCat : AllData.categoryList){
            tabLayout.addTab(tabLayout.newTab().setText(objCat.getName()));
        }


       /* tabLayout.addTab(tabLayout.newTab().setText("Beverages"));
        tabLayout.addTab(tabLayout.newTab().setText("Desi"));*/

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);
//        pager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }




    public void search(View view) {
        Intent intent = new Intent(MainActivity.this,SearchActivity.class);
        startActivity(intent);

    }

    public void showCart(View view) {
        if(AllData.cartList.size()>0){
            Intent intent=new Intent(MainActivity.this,Cart.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }


}

    /*public void popular(View view) {
        frag(1);
    }

    public void food(View view) {
        frag(2);
    }

    public void frag(int val){
        Fragment pop;
        if(val==1){
            pop=new PopularFragment();
        }else if(val==2){
            pop=new BestFragment();
        }else{
            pop=new BestFragment();
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,pop);
        fragmentTransaction.commit(); // Save changes
    }
*/
