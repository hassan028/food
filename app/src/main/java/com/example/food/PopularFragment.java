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
        getMenuCategory();

        product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),ProductDetails.class);
                intent.putExtra("index",position+"");
                startActivity(intent);
            }
        });
        return root;
    }

    public void getMenu(){
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    Product temp = snap.getValue(Product.class);
                    Menu.add(temp);

                }
                AllData.setMenu(Menu);
                AllData.setPopularProduct();

//                Toast.makeText(getActivity(), "Popular size "+AllData.getPopularProduct().size()+"", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "Menu size "+AllData.getMenu().size()+"", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "MenuCat size "+AllData.getMenuCategory().size()+"", Toast.LENGTH_SHORT).show();

                ProductAdaptor productAdaptor=new ProductAdaptor(AllData.getPopularProduct(),getActivity());
                product.setAdapter(productAdaptor);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void getCategory(){
        category.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    Category temp = snap.getValue(Category.class);
                    Category.add(temp);
                }
                AllData.setCategory(Category);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void getMenuCategory(){
        menuCategory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    MenuCategory temp = snap.getValue(MenuCategory.class);
                    MenuCategory.add(temp);
                }
                AllData.setMenuCategory(MenuCategory);
                getMenu();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getData(){
    }
}
//////////////////////////////////////////////////////////
//usman tera data ha ya
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




//////////////////////////////////////






//              intent.putExtra("popularProducts", (Parcelable) popularProducts);
//                Bundle b = new Bundle();
//                b.putSerializable("popularProducts", (Serializable) popularProducts);
//                intent.putExtras(b);
//                Toast.makeText(getActivity(), "12", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "122", Toast.LENGTH_SHORT).show();