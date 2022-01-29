package com.example.food;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends Fragment {
    ListView lvProduct;
    List<Product> popularProductList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment, null);
        lvProduct = (ListView) root.findViewById(R.id.products);

        popularProductList = new ArrayList<>();

        popularProductList = AllData.getCategoryProductList("Popular");


        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProductDetails.class);
                intent.putExtra("index", position + "");
                intent.putExtra("list","Popular");
                startActivity(intent);
            }
        });

        ProductAdaptor productAdaptor=new ProductAdaptor(popularProductList,getActivity());
        lvProduct.setAdapter(productAdaptor);

        return root;
    }

}

    /*public void getMenu(){
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    Product temp = snap.getValue(Product.class);
                    Menu.add(temp);

                }
                AllData.setMenu(Menu);
                getCategory();
                AllData.setPopularProduct();
//                Toast.makeText(getActivity(), "Popular size "+AllData.getPopularProduct().size()+"", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "Menu size "+AllData.getMenu().size()+"", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "MenuCat size "+AllData.getMenuCategory().size()+"", Toast.LENGTH_SHORT).show();

                ProductAdaptor productAdaptor=new ProductAdaptor(AllData.getPopularProduct(),getActivity());
                lvProduct.setAdapter(productAdaptor);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }*/
   /* public void getCategory(){
        category.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    Category temp = snap.getValue(Category.class);
                    Category.add(temp);
                }
                AllData.setCategory(Category);
                getOrder();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/




//////////////////////////////////////
//              intent.putExtra("popularProducts", (Parcelable) popularProducts);
//                Bundle b = new Bundle();
//                b.putSerializable("popularProducts", (Serializable) popularProducts);
//                intent.putExtras(b);
//                Toast.makeText(getActivity(), "12", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "122", Toast.LENGTH_SHORT).show();