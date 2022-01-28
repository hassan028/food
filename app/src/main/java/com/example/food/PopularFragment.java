package com.example.food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.logging.Handler;

public class PopularFragment extends Fragment {
    ListView product;
    FirebaseDatabase mDatabase;
    DatabaseReference menu;
    DatabaseReference category;
    DatabaseReference menuCategory;
//    DatabaseReference mRef;

    List<Product> Menu;
    List<Category> Category;
    List<MenuCategory> MenuCategory;
    List<Product> popular;

    Context c;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_popular, null);
        product=(ListView) root.findViewById(R.id.products);
//
//        mDatabase = FirebaseDatabase.getInstance();
//        menu = mDatabase.getReference("Category");

        // mRef = mDatabase.getReference(); // Refrence of parent node
//        mRef = mDatabase.getReference("Menu"); // Refrence of chil node
//
//        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot snap : snapshot.getChildren()) {
//                    Toast.makeText(getActivity(), snap.getKey(), Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        product.setNumColumns(2);
//        popular = FirebaseDatabase.getInstance().getReference().child("Products").child("Paid");

        menu = FirebaseDatabase.getInstance().getReference().child("Menu");
        category = FirebaseDatabase.getInstance().getReference().child("Category");
        menuCategory = FirebaseDatabase.getInstance().getReference().child("MenuCategory");

        Menu= new ArrayList<>();
        Category=new ArrayList<>();
        MenuCategory=new ArrayList<>();
        popular=new ArrayList<>();
        AllData allData=new AllData();
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
///////////////////////////////////////////////
        category.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    Category temp = snap.getValue(Category.class);
                    Category.add(temp);
                }
                AllData.setCategory(Category);
                Toast.makeText(getActivity(), "Cat "+Category.size()+"", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
/////////////////////////////////////////////////
        menuCategory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    MenuCategory temp = snap.getValue(MenuCategory.class);
                    MenuCategory.add(temp);
                }

                Toast.makeText(getActivity(), "MENUCAT = "+MenuCategory.size()+"", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    Product temp = snap.getValue(Product.class);
                    Menu.add(temp);
                }
                AllData.setMenu(Menu);
//                Toast.makeText(getActivity(), "Menu size = "+Menu.size()+"", Toast.LENGTH_SHORT).show();
//
//                long menuId;
//
//                for(int j=0;j<MenuCategory.size();j++){
//                    Toast.makeText(getActivity(), "For", Toast.LENGTH_SHORT).show();
//                    if(((int)(MenuCategory.get(j).getCatId()))==1){
//                        menuId=MenuCategory.get(j).getMenuId();
//                        popular.add(Menu.get((int) menuId));
//                    }
//                }
//
//
//                Toast.makeText(getActivity(), "jhjjh", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), popular.size()+"", Toast.LENGTH_SHORT).show();

                ProductAdaptor productAdaptor=new ProductAdaptor(Menu,getActivity());
                product.setAdapter(productAdaptor);


//                List<Product> a=new ArrayList<>();
//
//                List<Product> me=AllData.getMenu();
//                Toast.makeText(getActivity(), "Menu size = "+me.size()+"", Toast.LENGTH_SHORT).show();
//
//                List<Category> ca=AllData.getCategory();
//                Toast.makeText(getActivity(), "Cat = "+ca.size()+"", Toast.LENGTH_SHORT).show();



            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        /*String name="ssnsn";
        int price=100;`
        String detail="nsnnsnsms";

        Product p=new Product("Burgerhh","ndnn","Hello wold",0,10,32.01);

        menu.child(name).setValue(p, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null){
                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });*/

//        menu.addListenerForSingleValueEvent(new ValueEventListener(){
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (!snapshot.hasChildren()) {
//                    // run some code
//                    Toast.makeText(getActivity(),"Child Exist", Toast.LENGTH_SHORT).show();
//                }
//                for(DataSnapshot snap : snapshot.getChildren()){
//                    Toast.makeText(getActivity(), snap.getKey(), Toast.LENGTH_SHORT).show();
//                    for(DataSnapshot snap2 : snap.getChildren()){
//                        Toast.makeText(getActivity(), snap2.getKey(), Toast.LENGTH_SHORT).show();
//                    }
//                  //  Product temp = snap.getValue(Product.class);
//                    //Menu.add(temp);
//                   // Toast.makeText(getActivity(), temp.getName(), Toast.LENGTH_SHORT).show();
//                }
//          /*      Toast.makeText(getActivity(), snapshot.hasChildren()+"", Toast.LENGTH_SHORT).show();
//                //Show data in BaseAdapter(LISTVIEW)
//                AllData allData=new AllData();
//                AllData.setMenu(Menu);
//                AllData.setPopularProduct();
//                Toast.makeText(getActivity(), AllData.getMenu().size()+"", Toast.LENGTH_SHORT).show();
//
//                ProductAdaptor productAdaptor=new ProductAdaptor(AllData.getPopularProduct(),getActivity());
//                product.setAdapter(productAdaptor);*/
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}

//              intent.putExtra("popularProducts", (Parcelable) popularProducts);
//                Bundle b = new Bundle();
//                b.putSerializable("popularProducts", (Serializable) popularProducts);
//                intent.putExtras(b);
//                Toast.makeText(getActivity(), "12", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "122", Toast.LENGTH_SHORT).show();