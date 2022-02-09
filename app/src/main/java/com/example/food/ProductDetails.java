package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity  {
    List<Product> productList;
    TextView name,price,itemValue,detail;
    ImageView img;


    int index;
    BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        float dim_percentage = 75.0f;
        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = (dim_percentage / 100);
       getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        img=findViewById(R.id.img);
        itemValue=findViewById(R.id.itemValue);
        detail=findViewById(R.id.detail);


        Intent i = getIntent();
        int index=Integer.parseInt(i.getStringExtra("index"));
        this.index=index;

        productList=new ArrayList<>();

        if(i.getStringExtra("list").equals("Menu")){
            productList=AllData.menuList;
        }else if(i.getStringExtra("list").equals("Filter")){
            productList=AllData.filteredList;
        }else{
            productList=AllData.getCategoryProductList(i.getStringExtra("list"));
        }


        setSizeOfScreen(index);
        price.setText("Rs. "+productList.get(index).getPrice()+"");
        name.setText(productList.get(index).getName());
        detail.setText(productList.get(index).getDetails());


    }

    public void setSizeOfScreen(int index){
        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout(width,(int)(height*0.7));

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.BOTTOM;
        params.x=0;
        params.y=-20;

        getWindow().setAttributes(params);
        if(!productList.get(index).getImg().trim().equals(""))
        Picasso.get().load(productList.get(index).getImg()).into(img);
//        Picasso.get().load(productList.get(index).getImg()).resize((int)(width*0.5),(int)(height*0.2)).into(img);
        //        Transformation transformation = new RoundedTransformationBuilder()
//                .borderColor(Color.TRANSPARENT)
//                .borderWidthDp(2)
//                .cornerRadiusDp(20)
//                .oval(false)
//                .build();
    }

    public void increment(View view) {
        int val=Integer.parseInt(itemValue.getText().toString());
        val++;
        itemValue.setText(val+"");
    }

    public void decrement(View view) {
        int val=Integer.parseInt(itemValue.getText().toString());
        val--;
        if(val <= 0){
            return;
        }else{
            itemValue.setText(val+"");
        }
    }

    public void addToCart(View view) {
        boolean found=false;
        double subTotal=productList.get(index).getPrice();
        long menuId=productList.get(index).getId();
        int quantity=Integer.parseInt(itemValue.getText().toString());
        String name=productList.get(index).getName();
        CartData temp=new CartData(quantity,menuId,subTotal,name);

        int newQuantity;
        for(int i=0;i<AllData.cartList.size();i++){
            if(AllData.cartList.get(i).getMenuId()==menuId){
                found=true;
                newQuantity=AllData.cartList.get(i).getQuantity();
                AllData.cartList.get(i).setQuantity(newQuantity+quantity);
                AllData.cartList.get(i).setSubTotal(subTotal);
            }
        }
        if(!found){
            AllData.cartList.add(temp);
        }

        SharedPreferences myPref = getSharedPreferences("Storage",MODE_PRIVATE);
        AllData.setSharedPrefrence(myPref);

        finish();
    }

    public void closeWindow(View view) {
        finish();
    }


//        productList= (List<Product>) getIntent().getSerializableExtra("productLists");
//        productList= (List<Product>) i.getSerializableExtra("productLists");
//        name.setText(productList.get(Integer.parseInt(index)).getName());
//        AllData allData=new AllData();
//        Toast.makeText(ProductDetails.this, productList.size()+"", Toast.LENGTH_SHORT).show();
}