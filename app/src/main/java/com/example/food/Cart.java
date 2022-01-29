package com.example.food;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cart extends AppCompatActivity {
    ListView list;
    int rowNumber=-1;
    TextView tvSubtotal,tvTotalBill;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        list=findViewById(R.id.list);
        tvSubtotal=findViewById(R.id.tvSubtotal);
        tvTotalBill=findViewById(R.id.tvTotalBill);
        cartAdaptor();
    }

    public void increaseQuantity(View view) {
        LinearLayout parentRow = (LinearLayout) view.getParent();
        rowNumber= list.getPositionForView((LinearLayout)view.getParent());

        TextView quantityView = (TextView) parentRow.findViewById(R.id.tvQuantity);
        int quantity = Integer.parseInt(quantityView.getText().toString());
        quantity++;
        AllData.cartList.get(rowNumber).setQuantity(quantity);
        quantityView.setText(quantity+"");

        cartAdaptor();
    }

    public void decreaseQuantity(View view) {
        rowNumber= list.getPositionForView((LinearLayout)view.getParent());
        LinearLayout parentRow = (LinearLayout) view.getParent();

        TextView quantityView = (TextView) parentRow.findViewById(R.id.tvQuantity);
        int quantity = Integer.parseInt(quantityView.getText().toString());
        quantity--;
        if(quantity==0){
            AllData.cartList.remove(rowNumber);
            cartAdaptor();
        }
        if(quantity>0){
            AllData.cartList.get(rowNumber).setQuantity(quantity);
            quantityView.setText(quantity+"");
            cartAdaptor();
        }
        if(AllData.cartList.size()==0){
            finish();
        }
    }

    public void cartAdaptor(){
        double subTotal=0;
        int quantity;
        double price;
        for(int i=0;i<AllData.cartList.size();i++){
            quantity=AllData.cartList.get(i).getQuantity();
            price=AllData.cartList.get(i).getSubTotal();
            subTotal=(quantity*price)+subTotal;
        }
        tvSubtotal.setText(subTotal+"");
        subTotal+=(0.17 * subTotal);
        tvTotalBill.setText(subTotal+"");

        CartAdaptor cartAdaptor=new CartAdaptor(AllData.cartList,Cart.this);
        list.setAdapter(cartAdaptor);
    }
}
