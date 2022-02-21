package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewItem extends AppCompatActivity {
    int index;
    EditText name2,price2,details2,name,price,details;
    TextView category;
    ImageView menuImage;
    Button changeImage,Edit,delete,submit;
    Spinner categoryDropdown;

    List<Product> productList;

    int menuId,categoryId;
    String selectedCategory,url,oldImg;

    Uri uri;

    DatabaseReference foodDbRef;
    FirebaseDatabase foodDatabase;
    StorageReference reference= FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        name2 = findViewById(R.id.etName_2);
        price2 = findViewById(R.id.etPrice_2);
        details2 = findViewById(R.id.etDetail_2);
        category = findViewById(R.id.etCategory);
        menuImage = findViewById(R.id.menuImage);

        name= findViewById(R.id.etName);
        price = findViewById(R.id.etPrice);
        details = findViewById(R.id.etDetail);

        changeImage = findViewById(R.id.btnChangeImage);
        Edit = findViewById(R.id.btnEdit);
        delete = findViewById(R.id.btnDelate);
        submit = findViewById(R.id.btnSubmit);
        categoryDropdown = findViewById(R.id.spinnerCategory);

        changeImage.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        productList=new ArrayList<>();

        Intent i = getIntent();
        int index=Integer.parseInt(i.getStringExtra("index"));
        this.index=index;

        if(i.getStringExtra("list").equals("Menu")){
            productList=AllData.menuList;
        }else if(i.getStringExtra("list").equals("Filter")){
            productList=AllData.filteredList;
        }else{
            productList=AllData.getCategoryProductList(i.getStringExtra("list"));
        }

        name2.setText(productList.get(index).getName());
        price2.setText(productList.get(index).getPrice()+"");
        details2.setText(productList.get(index).getDetails());
        category.setText(AllData.getCategoryName(productList.get(index)));
        oldImg = productList.get(index).getImg();

        if(!productList.get(index).getImg().trim().equals(""))
            Picasso.get().load(productList.get(index).getImg()).into(menuImage);

        foodDatabase = FirebaseDatabase.getInstance();
        foodDbRef = foodDatabase.getReference("Menu");

    }

    public void selectImage(View view) {
        Intent i = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i , 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data !=null){
            uri = data.getData();
            menuImage.setImageURI(uri);
        }
    }

    public void deleteItem(View view) {
        menuId = productList.get(index).getId();
        foodDbRef.child(menuId + "").removeValue();
        super.onBackPressed();

    }

    public void editItem(View view) {
        name2.setVisibility(View.GONE);
        price2.setVisibility(View.GONE);
        details2.setVisibility(View.GONE);
        category.setVisibility(View.GONE);
        changeImage.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);



        name.setVisibility(View.VISIBLE);
        price.setVisibility(View.VISIBLE);
        details.setVisibility(View.VISIBLE);
        name.setText(name2.getText().toString());
        price.setText(price2.getText().toString());
        details.setText(details2.getText().toString());

        Edit.setVisibility(View.GONE);
        delete.setVisibility(View.GONE);

        uri = null;

        categoryDropdown.setVisibility(View.VISIBLE);
        List<String> categoryNameList = new ArrayList<>();
        for(Category objCat : AllData.categoryList){
            if(objCat.getName().equals("Popular"))
                continue;
            categoryNameList.add(objCat.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.spinner_item, categoryNameList);
        categoryDropdown.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(category.getText().toString());
        categoryDropdown.setSelection(spinnerPosition);
        //Get the selected spinner category
        categoryDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>arg0, View view, int arg2, long arg3) {
                selectedCategory = categoryDropdown.getSelectedItem().toString();
                for(Category objCat : AllData.categoryList){
                    if(objCat.getName().equals(selectedCategory)){
                        categoryId = (int) objCat.getId();
                        break;
                    }
                }
                Toast.makeText(getApplicationContext(),selectedCategory ,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

    }
    public void enableEditText(EditText b,boolean option){
        b.setFocusable(option);
        b.setEnabled(option);
    }

    public void submitItem(View view) {
        if(name.getText().toString().trim().equals("") ){
            Toast.makeText(ViewItem.this,"Name should not be empty", Toast.LENGTH_SHORT).show();
        }
        else if(price.getText().toString().trim().equals("")){
            Toast.makeText(ViewItem.this,"Price should not be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            storeInFireBase();

            ViewMode();
        }

    }
    public void storeInFireBase() {
        if(uri != null) {
            StorageReference storageReference = reference.child(System.currentTimeMillis() + "." + getFileExtension());
            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            url = uri.toString();
                            String menuName = name.getText().toString().trim();
                            String menuDetail = details.getText().toString().trim();
                            double menuPrice = Double.parseDouble(price.getText().toString().trim());


                            menuId = productList.get(index).getId();
                            int ordercount = productList.get(index).getOrderCount();


                            Product p = new Product(menuName, url, menuDetail, menuId, categoryId, ordercount, menuPrice);

                            foodDbRef.child(menuId + "").setValue(p, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    if (error == null) {
                                        Toast.makeText(ViewItem.this, "Data Saved", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(ViewItem.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    Toast.makeText(ViewItem.this, "Progress", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ViewItem.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{
            String menuName = name.getText().toString().trim();
            String menuDetail = details.getText().toString().trim();
            double menuPrice = Double.parseDouble(price.getText().toString().trim());


            menuId = productList.get(index).getId();
            int ordercount = productList.get(index).getOrderCount();

            Product p = new Product(menuName, oldImg, menuDetail, menuId, categoryId, ordercount, menuPrice);

            foodDbRef.child(menuId + "").setValue(p, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if (error == null) {

                        
                        Toast.makeText(ViewItem.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ViewItem.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void ViewMode(){
        name.setVisibility(View.GONE);
        price.setVisibility(View.GONE);
        details.setVisibility(View.GONE);


        categoryDropdown.setVisibility(View.GONE);
        category.setVisibility(View.VISIBLE);
        category.setText(selectedCategory);
        changeImage.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);

        name2.setVisibility(View.VISIBLE);
        price2.setVisibility(View.VISIBLE);
        details2.setVisibility(View.VISIBLE);
        Edit.setVisibility(View.VISIBLE);
        delete.setVisibility(View.VISIBLE);


        name2.setText(name.getText().toString());
        price2.setText(price.getText().toString());
        details2.setText(details.getText().toString());
    }

    public String getFileExtension(){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
}