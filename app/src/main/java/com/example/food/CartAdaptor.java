package com.example.food;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class CartAdaptor extends BaseAdapter {

    List<CartData> cartDataList;
    LayoutInflater inflater;
    Context c;

    public CartAdaptor(List<CartData> cartDataList , Context c) {
        this.cartDataList = cartDataList;
        this.c=c;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return cartDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=inflater.inflate(R.layout.cart_row,null);
        CartData temp=cartDataList.get(position);

        TextView tvQuantity=v.findViewById(R.id.tvQuantity);
        TextView tvMenuName=v.findViewById(R.id.tvMenuName);
        TextView tvMenuSubtotal=v.findViewById(R.id.tvMenuSubtotal);

        tvQuantity.setText(temp.getQuantity()+"");
        tvMenuName.setText(temp.getName()+"");
        tvMenuSubtotal.setText(" Rs. "+(temp.getSubTotal()*temp.getQuantity())+"");

        return v;
    }
}
