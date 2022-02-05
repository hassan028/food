package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class New_Menu_Item_Activity extends AppCompatActivity {
    ImageView menuImage;
    Spinner category;

    EditText name,detail,price;
    int menuId,categoryId;
    String selectedCategory,url;
    Uri uri;

    DatabaseReference foodDbRef;
    FirebaseDatabase foodDatabase;
    StorageReference reference= FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_menu_item);
        loadFirebaseToList("Menu");

        menuImage=findViewById(R.id.menuImage);
        category=findViewById(R.id.spinner);
        name=findViewById(R.id.name);
        detail=findViewById(R.id.detail);
        price=findViewById(R.id.price);

        //Populating items in spinner
        String[] items = new String[]{"Burger", "Pizza"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        category.setAdapter(adapter);

        //Get the selected spinner category
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>arg0, View view, int arg2, long arg3) {
                selectedCategory=category.getSelectedItem().toString();
                categoryId=getCategoryId(items);
                Toast.makeText(getApplicationContext(), selectedCategory , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        foodDatabase = FirebaseDatabase.getInstance();
        foodDbRef = foodDatabase.getReference("Menu");
    }

    public void storeInFireBase(View view){
        StorageReference storageReference=  reference.child(System.currentTimeMillis()+"."+getFileExtension());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        url=uri.toString();
                        String menuName=name.getText().toString().trim();
                        String menuDetail=detail.getText().toString().trim();
                        double menuPrice=Double.parseDouble(price.getText().toString().trim());

                        int size=AllData.menuList.size();
                        size--;
                        menuId = AllData.menuList.get(size).getId();
                        menuId++;

                        Product p=new Product(menuName,url,menuDetail,menuId,categoryId,0,menuPrice);

                        foodDbRef.child(menuId+"").setValue(p, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if(error == null){
                                    AllData.menuList.add(p);
                                    Toast.makeText(New_Menu_Item_Activity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(New_Menu_Item_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                Toast.makeText(New_Menu_Item_Activity.this, "Progress", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(New_Menu_Item_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getFileExtension(){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    public int getCategoryId(String items[]){
        int id = 0;
        for(int i=0;i<items.length;i++){
            if(items[i].equals(selectedCategory)){
                id=i+2;
                break;
            }
        }
        Toast.makeText(New_Menu_Item_Activity.this, id+" category id", Toast.LENGTH_SHORT).show();
        return id;
    }

    //Methods Used to select Image From Gallery
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

    //Load menu
    public void loadFirebaseToList(String table){

        foodDatabase = FirebaseDatabase.getInstance();
        foodDbRef = foodDatabase.getReference(table);
        foodDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AllData.menuList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Product tempProduct = snap.getValue(Product.class);
                    AllData.menuList.add(tempProduct);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(New_Menu_Item_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}