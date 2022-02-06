package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
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
    int length=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lvProduct = (ListView) findViewById(R.id.products);



        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(length>0){
                    Intent intent = new Intent(SearchActivity.this, ProductDetails.class);
                    intent.putExtra("index", position + "");
                    intent.putExtra("list","Filter");
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SearchActivity.this, ProductDetails.class);
                    intent.putExtra("index", position + "");
                    intent.putExtra("list","Menu");
                    startActivity(intent);
                }
            }
        });

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
                length=s.toString().trim().length();
                filter(s.toString().trim());
            }
        });
    }

    private void filter(String text) {
        List<Product> filterList = new ArrayList<>();
        for(Product items : list){

            if(items.getName().toLowerCase().contains(text.toLowerCase()) || Double.toString(items.getPrice()).contains(text) || items.getDetails().toLowerCase().contains(text.toLowerCase()) || AllData.getCategoryName(items).toLowerCase().contains(text.toLowerCase())){
                filterList.add(items);

            }
            if("Popular".toLowerCase().contains(text.toLowerCase())){
                if(items.getOrderCount() > 10) {
                    if(!filterList.contains(items))
                        filterList.add(items);
                }
            }

        }
        AllData.filteredList=filterList;
        productAdaptor.filterList(filterList);
        lvProduct.setAdapter(productAdaptor);
    }

    public void back(View view) {
        super.onBackPressed();
    }
}