package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity  {
    List<Product> popularProduct;
    TextView name,price,itemValue,detail;
    ImageView img;
    int index;
    BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        img=findViewById(R.id.img);
        itemValue=findViewById(R.id.itemValue);
        detail=findViewById(R.id.detail);

        Intent i = getIntent();
        int index=Integer.parseInt(i.getStringExtra("index"));
        this.index=index;

        popularProduct=new ArrayList<>();

        if(i.getStringExtra("list").equals("Menu")){
            popularProduct=AllData.menuList;
        }else if(i.getStringExtra("list").equals("Filter")){
            popularProduct=AllData.filteredList;
        }else{
            popularProduct=AllData.getCategoryProductList(i.getStringExtra("list"));
        }


        setSizeOfScreen(index);
        price.setText("Rs. "+popularProduct.get(index).getPrice()+"");
        name.setText(popularProduct.get(index).getName());
        detail.setText(popularProduct.get(index).getDetails());


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
        Picasso.get().load(popularProduct.get(index).getImg()).resize((int)(width*0.5),(int)(height*0.2)).into(img);
//        Picasso.get().load(popularProduct.get(index).getImg()).resize((int)(width*0.5),(int)(height*0.2)).into(img);
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
        double subTotal=popularProduct.get(index).getPrice();
        long menuId=popularProduct.get(index).getId();
        int quantity=Integer.parseInt(itemValue.getText().toString());
        String name=popularProduct.get(index).getName();
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

        finish();
    }

    public void closeWindow(View view) {
        finish();
    }


//        popularProduct= (List<Product>) getIntent().getSerializableExtra("popularProducts");
//        popularProduct= (List<Product>) i.getSerializableExtra("popularProducts");
//        name.setText(popularProduct.get(Integer.parseInt(index)).getName());
//        AllData allData=new AllData();
//        Toast.makeText(ProductDetails.this, popularProduct.size()+"", Toast.LENGTH_SHORT).show();
}