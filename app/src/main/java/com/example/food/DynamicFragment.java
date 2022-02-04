package com.example.food;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DynamicFragment extends Fragment {
    ListView lvProduct;
    List<Product> popularProductList;
    FirebaseDatabase foodDatabase;
    DatabaseReference foodDbRef;
    private static final String TAG = "My Tag";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment, null);
        lvProduct = (ListView) root.findViewById(R.id.products);

        Bundle b = getArguments();

        popularProductList = new ArrayList<>();

        popularProductList = AllData.getCategoryProductList(b.getString("Fragment"));


        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProductDetails.class);
                intent.putExtra("index", position + "");
                intent.putExtra("list",b.getString("Fragment"));
                startActivity(intent);
            }
        });

        ProductAdaptor productAdaptor=new ProductAdaptor(popularProductList,getActivity());
        lvProduct.setAdapter(productAdaptor);

        foodDatabase = FirebaseDatabase.getInstance();
        foodDbRef = foodDatabase.getReference("Menu");
        foodDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                popularProductList = AllData.getCategoryProductList(b.getString("Fragment"));
                if(getActivity()==null){
                    Log.d(TAG,"onFailure: this is null " );
                }else {
                    Log.d(TAG,"onFailure:  " + getActivity());
                    ProductAdaptor productAdaptor = new ProductAdaptor(popularProductList, getActivity());
                    lvProduct.setAdapter(productAdaptor);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

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