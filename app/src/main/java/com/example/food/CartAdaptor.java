package com.example.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CartAdaptor extends BaseAdapter {

    List<CartData> cartDataList;
    LayoutInflater inflater;
    Context c;
    int check;

    public CartAdaptor(List<CartData> cartDataList , Context c,int ch) {
        this.cartDataList = cartDataList;
        this.c=c;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.check = ch;
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
        if(check==0) {
            View v = inflater.inflate(R.layout.cart_row, null);
            CartData temp = cartDataList.get(position);

            TextView tvQuantity = v.findViewById(R.id.tvQuantity);
            TextView tvMenuName = v.findViewById(R.id.tvMenuName);
            TextView tvMenuSubtotal = v.findViewById(R.id.tvMenuSubtotal);

            tvQuantity.setText(temp.getQuantity() + "");
            tvMenuName.setText(temp.getName() + "");
            tvMenuSubtotal.setText(" Rs. " + (temp.getSubTotal() * temp.getQuantity()) + "");
            return v;
        }
        else{
            View v = inflater.inflate(R.layout.reciept_row, null);
            CartData temp = cartDataList.get(position);

            TextView tvQuantity = v.findViewById(R.id.tvQty);
            TextView tvName = v.findViewById(R.id.tvItem);

            TextView price = v.findViewById(R.id.totalPrice);

            tvQuantity.setText(temp.getQuantity() + "");
            tvName.setText(temp.getName()+"");

            price.setText(" Rs. " + (temp.getSubTotal() * temp.getQuantity()) + "");
            return v;
        }


    }
}
