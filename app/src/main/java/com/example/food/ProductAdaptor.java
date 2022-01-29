package com.example.food;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
/*import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;*/
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;
import com.squareup.picasso.Transformation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProductAdaptor extends BaseAdapter {

    List<Product> productsList;
    LayoutInflater inflater;
    Context c;

    public ProductAdaptor(List<Product> productsList , Context c) {
        this.productsList = productsList;
        this.c=c;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v=inflater.inflate(R.layout.oneproduct,null);

        Product temp=productsList.get(i);

        TextView title=v.findViewById(R.id.title);
        TextView price=v.findViewById(R.id.price);
        TextView details=v.findViewById(R.id.detail);
        ImageView img=v.findViewById(R.id.img);

        title.setText(temp.getName());
        price.setText("Rs. "+temp.getPrice()+"");
        details.setText(temp.getDetails());



       Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.TRANSPARENT)
                .borderWidthDp(2)
                .cornerRadiusDp(15)
                .oval(false)
                .build();

        Picasso.get().load(temp.getImg()).transform(transformation).resize(getScreenWidth(),600).into(img);

        return v;
    }
}
