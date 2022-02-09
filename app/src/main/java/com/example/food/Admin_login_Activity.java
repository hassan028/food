package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Admin_login_Activity extends AppCompatActivity {

    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
    }

    public void login(View view) {
        String name=username.getText().toString().trim();
        String pass=password.getText().toString().trim();

        if(name.length()>4 && pass.length()>4){
            AllData.Mode = 1;
            Intent intent=new Intent(Admin_login_Activity.this,AdminPanelActivity.class);
            startActivity(intent);
        }

    }
}