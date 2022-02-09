package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class Feedback_activity extends AppCompatActivity {

    ListView feedbackList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedbackList=findViewById(R.id.feedbackList);
        FeedbackAdaptor feedbackAdaptor=new FeedbackAdaptor(AllData.feedbackList,Feedback_activity.this);
        feedbackList.setAdapter(feedbackAdaptor);
    }

    public void addFeedback(View view) {
        Intent intent =new Intent(Feedback_activity.this,Add_feedback_activity.class);
        startActivity(intent);
    }
}