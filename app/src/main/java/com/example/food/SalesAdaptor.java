package com.example.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SalesAdaptor extends BaseAdapter {

    List<Sales> salesList;
    Context c;
    LayoutInflater inflater;

    public SalesAdaptor(List<Sales> salesList, Context c) {
        this.salesList=salesList;
        this.c=c;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return salesList.size();
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
        View v=inflater.inflate(R.layout.sales_row,null);
        Sales temp=salesList.get(position);

        TextView date=v.findViewById(R.id.date);
        TextView orderNumber=v.findViewById(R.id.orderNumber);
        TextView totalBill=v.findViewById(R.id.totalBill);

        date.setText(temp.getDate());
        orderNumber.setText("Rs. "+temp.getOrderNumber());
        totalBill.setText(temp.getTotalBill()+"");

        return v;
    }
}
