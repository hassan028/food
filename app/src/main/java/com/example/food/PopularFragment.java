package com.example.food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends Fragment {
    ListView product;
    DatabaseReference popular;
    List<Product> popularProducts;
    Context c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_popular, null);
        product=(ListView) root.findViewById(R.id.products);

//        product.setNumColumns(2);

        popular = FirebaseDatabase.getInstance().getReference().child("Products").child("Paid");
        popularProducts= new ArrayList<>();

        String prodIt = popular.push().getKey();
        getData();

        product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent=new Intent(getActivity(),ProductDetails.class);
                intent.putExtra("index",position+"");
                startActivity(intent);
            }
        });
        return root;
    }

    public void getData(){
        popular.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    Product temp = snap.getValue(Product.class);
                    popularProducts.add(temp);
                }
                //Show data in BaseAdapter(LISTVIEW)
                AllData allData=new AllData();

                AllData.setPopularProduct(popularProducts);

//                allData.setPopularProduct(popularProducts);
                ProductAdaptor productAdaptor=new ProductAdaptor(popularProducts,getActivity());
                product.setAdapter(productAdaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

//              intent.putExtra("popularProducts", (Parcelable) popularProducts);
//                Bundle b = new Bundle();
//                b.putSerializable("popularProducts", (Serializable) popularProducts);
//                intent.putExtras(b);
//                Toast.makeText(getActivity(), "12", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "122", Toast.LENGTH_SHORT).show();