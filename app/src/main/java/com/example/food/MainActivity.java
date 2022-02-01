package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager pager;
    FragmentAdapter adapter;

    FirebaseDatabase foodDatabase;
    DatabaseReference foodDbRef;
    float tabWidth;

    private static final String TAG = "My Tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);
        float density  = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth  = outMetrics.widthPixels / density;

        tabWidth = (dpWidth - 45f) / 3;

        loadData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initViews();
            }
        }, 6000);


    }

    public void  initViews(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        pager = (ViewPager) findViewById(R.id.pager);


        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        tabLayout.addTab(tabLayout.newTab().setText("Burgers"));
        tabLayout.addTab(tabLayout.newTab().setText("Pizzas"));

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

    public void loadData(){
        AllData allData = new AllData();
        loadFirebaseToList("Menu");
        loadFirebaseToList("Category");
        loadFirebaseToList("MenuCategory");
        loadFirebaseToList("Order");
    }
    public void loadFirebaseToList(String table){

        foodDatabase = FirebaseDatabase.getInstance();
        foodDbRef = foodDatabase.getReference(table);
        foodDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    if(table.equals("Menu")) {
                        Product tempProduct = snap.getValue(Product.class);
                        AllData.menuList.add(tempProduct);
                    }
                    else if(table.equals("Category")){
                        Category tempCategory = snap.getValue(Category.class);
                        AllData.categoryList.add(tempCategory);

                    }
                    else if(table.equals("MenuCategory")){
                        MenuCategory tempMenuCategory = snap.getValue(MenuCategory.class);
                        AllData.menuCategoryList.add(tempMenuCategory);
                    }
                    else if(table.equals("Order")) {
                        Order tempOrder = snapshot.getValue(Order.class);
                        AllData.orderList.add(tempOrder);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

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
