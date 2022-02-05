package com.example.food;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Cart extends AppCompatActivity {
    ListView list;
    int rowNumber=-1;
    TextView tvSubtotal,tvTotalBill,tvGst;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        list=findViewById(R.id.list);
        tvSubtotal=findViewById(R.id.tvSubtotal);
        tvTotalBill=findViewById(R.id.tvTotalBill);
        tvGst= findViewById(R.id.tvGst);
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
        double subTotal=0,TotalBill;
        int quantity;
        double price,gst;

        for(int i=0;i<AllData.cartList.size();i++){
            quantity=AllData.cartList.get(i).getQuantity();
            price=AllData.cartList.get(i).getSubTotal();
            subTotal=(quantity*price)+subTotal;
        }
        tvSubtotal.setText(subTotal+"");
        gst = 0.17 * subTotal;
        tvGst.setText(gst+"");
        TotalBill = subTotal + gst;
        tvTotalBill.setText(TotalBill+"");

        CartAdaptor cartAdaptor=new CartAdaptor(AllData.cartList,Cart.this,0);
        list.setAdapter(cartAdaptor);
    }

    public void ProceedOrder(View view) {
        Intent i = new Intent(Cart.this,RecieptActivity.class);
        startActivity(i);
    }
}
