package com.example.food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    FirebaseDatabase foodDatabase;
    DatabaseReference foodDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splah_screen);

        loadData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
            }
        }, 6000);
    }
    public void loadData(){
        AllData allData = new AllData();
        loadFirebaseToList("Menu");
        loadFirebaseToList("Category");
        loadFirebaseToList("Order");
    }
    public void loadFirebaseToList(String table){

        foodDatabase = FirebaseDatabase.getInstance();
        foodDbRef = foodDatabase.getReference(table);
        foodDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(table.equals("Menu")) {
                    AllData.menuList.clear();
                }
                else if(table.equals("Category")){
                    AllData.categoryList.clear();

                }
                else if(table.equals("Order")){
                    AllData.orderList.clear();
                }
                for (DataSnapshot snap : snapshot.getChildren()) {
                    if(table.equals("Menu")) {
                        Product tempProduct = snap.getValue(Product.class);
                        AllData.menuList.add(tempProduct);
                    }
                    else if(table.equals("Category")){
                        Category tempCategory = snap.getValue(Category.class);
                        AllData.categoryList.add(tempCategory);
                    }
                    else if(table.equals("Order")) {
                        Order tempOrder = snapshot.getValue(Order.class);
                        AllData.orderList.add(tempOrder);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SplashActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}