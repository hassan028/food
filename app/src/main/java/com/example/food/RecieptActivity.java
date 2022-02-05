package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecieptActivity extends AppCompatActivity {

    ListView listItemsReciept;
    TextView subTotal,gst,totalBill;

    FirebaseDatabase mDatabase;
    DatabaseReference mRefSales,mRefMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciept);
        listItemsReciept = findViewById(R.id.listReciept);

        CartAdaptor cartAdaptor=new CartAdaptor(AllData.cartList,RecieptActivity.this,1);
        listItemsReciept .setAdapter(cartAdaptor);

        subTotal =findViewById(R.id.subTotalReciept);
        gst = findViewById(R.id.gstReciept);
        totalBill =findViewById(R.id.totalBillReciept);

        double subtotalValue = AllData.getCartSubtotal();
        subTotal.setText(subtotalValue + "");

        double gstValue = 0.17 * subtotalValue;
        gst.setText(gstValue + "");

        double TotalBillValue = subtotalValue + gstValue;
        totalBill.setText(TotalBillValue + "");


        mDatabase = FirebaseDatabase.getInstance();
        mRefSales = mDatabase.getReference().child("Sales");


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());

        int orderNumber = (int) AllData.orderList.get(0).getOrderByDay();

        Sales objSales = new Sales(2,orderNumber,TotalBillValue,date,AllData.cartList);


        mRefSales.child("2").setValue(objSales, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null){
                    Toast.makeText(RecieptActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RecieptActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRefMenu = mDatabase.getReference().child("Menu");

        for(CartData cart :  AllData.cartList){
            for(Product p : AllData.menuList){
                if(p.getId() == cart.getMenuId()) {
                    changeOrderCount(p.getId() + "", (p.getOrderCount() + 1));

                }
            }

        }


    }
    public void changeOrderCount(String menuId,int orderCount){
        mRefMenu.child(menuId).child("orderCount").setValue(orderCount, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null){
                    Toast.makeText(RecieptActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RecieptActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        AllData.cartList.clear();
        Intent i = new Intent(RecieptActivity.this,MainActivity.class);
        startActivity(i);

    }
    /*public int getIncrementedSaleId(){
        mRef = mDatabase.getReference().child("Sales");
        int id;
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() == null) {
                    // The child doesn't exist
                    Toast.makeText(RecieptActivity.this, snapshot.getValue() , Toast.LENGTH_SHORT).show();
                    id = 0;
                }
                else{
                    Toast.makeText(RecieptActivity.this, snapshot.getKey(), Toast.LENGTH_SHORT).show();
                    id = Integer.parseInt(snapshot.getKey());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return id;


    }*/
}