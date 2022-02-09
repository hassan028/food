package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    FirebaseDatabase foodDatabase;
    DatabaseReference foodDbRef;
    String currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splah_screen);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = sdf.format(new Date());

        loadData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this,Starting_Activity.class);
                startActivity(i);
            }
        }, 5000);
    }
    public void loadData(){
        AllData allData = new AllData();
        loadFirebaseToList("Menu");
        loadFirebaseToList("Category");
        loadFirebaseToList("Order");
        loadFirebaseToList("Sales");
        loadFirebaseToList("Feedback");
    }
    public void loadFirebaseToList(String table){

        foodDatabase = FirebaseDatabase.getInstance();
        foodDbRef = foodDatabase.getReference(table);
        DatabaseReference foodDbRefOrder = foodDatabase.getReference();
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
                else if(table.equals("Sales")){
                    AllData.salesList.clear();
                }
                else if(table.equals("Feedback")){
                    AllData.salesList.clear();
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
                    else if(table.equals("Feedback")){
                        Feedback tempFeedback = snap.getValue(Feedback.class);
                        AllData.feedbackList.add(tempFeedback);
                    }
                    else if(table.equals("Order")) {
                        Order tempOrder = snapshot.getValue(Order.class);
                        if(!tempOrder.getCurrentDate().equals(currentDate)){
                            tempOrder.setCurrentDate(currentDate);
                            tempOrder.setOrderByDay(0);

                            foodDbRefOrder.child("Order").setValue(tempOrder, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    if(error == null){
                                        Toast.makeText(SplashActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(SplashActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        AllData.orderList.add(tempOrder);

                    }
                    else if(table.equals("Sales")) {
                        Sales tempSales = snap.getValue(Sales.class);
                        AllData.salesList.add(tempSales);

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