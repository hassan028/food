package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
    }

    public void openItemMenu(View view) {
        Intent i  = new Intent(AdminPanelActivity.this,MainActivity.class);
        startActivity(i);
    }
}