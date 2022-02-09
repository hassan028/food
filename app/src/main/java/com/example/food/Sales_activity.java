package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class Sales_activity extends AppCompatActivity {
    ListView salesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        salesList=findViewById(R.id.salesList);
        SalesAdaptor salesAdaptor=new SalesAdaptor(AllData.salesList,Sales_activity.this);
        salesList.setAdapter(salesAdaptor);
    }
}