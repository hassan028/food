package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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
        popularProduct=new ArrayList<>();
        popularProduct=AllData.getPopularProduct();

        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        img=findViewById(R.id.img);
        itemValue=findViewById(R.id.itemValue);

        Intent i = getIntent();
        int index=Integer.parseInt(i.getStringExtra("index"));
        this.index=index;
        setSizeOfScreen(index);
        price.setText(popularProduct.get(index).getPrice());
        name.setText(popularProduct.get(index).getName());


    }

    public void setSizeOfScreen(int index){
        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout(width,(int)(height*0.9));

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.BOTTOM;
        params.x=0;
        params.y=-20;

        getWindow().setAttributes(params);

//        Transformation transformation = new RoundedTransformationBuilder()
//                .borderColor(Color.TRANSPARENT)
//                .borderWidthDp(2)
//                .cornerRadiusDp(20)
//                .oval(false)
//                .build();

        Picasso.get().load(popularProduct.get(index).getImg()).resize((int)(width*0.5),(int)(height*0.2)).into(img);
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
//        AllData.addDataInCart(popularProduct.get(index));
//        List<Product> cart;
//        cart=AllData.getCart();
//        Toast.makeText(ProductDetails.this, cart.size()+"", Toast.LENGTH_SHORT).show();
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