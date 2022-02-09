package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Starting_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
    }


    public void proceedToAdmin(View view) {
        AllData.Mode = 1;
        Intent intent=new Intent(Starting_Activity.this,Admin_login_Activity.class);
        startActivity(intent);
    }

    public void proceedToGuest(View view) {
        AllData.Mode = 2;
        Intent intent=new Intent(Starting_Activity.this,MainActivity.class);
        startActivity(intent);
    }

}