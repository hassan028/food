package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    ListView lvProduct;
    EditText etSeaechBar;
    ProductAdaptor productAdaptor;
    List<Product> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lvProduct = (ListView) findViewById(R.id.products);
        etSeaechBar = findViewById(R.id.etSearchBar);

        list = AllData.menuList;



       productAdaptor =new ProductAdaptor(AllData.menuList,SearchActivity.this);
        lvProduct.setAdapter(productAdaptor);

        etSeaechBar.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }

    private void filter(String text) {
        List<Product> filterList = new ArrayList<>();
        for(Product items : list){
            if(items.getName().toLowerCase().contains(text.toLowerCase()) || Double.toString(items.getPrice()).contains(text) ){
                filterList.add(items);
            }

        }
        productAdaptor.filterList(filterList);
        lvProduct.setAdapter(productAdaptor);
    }

}