package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Add_feedback_activity extends AppCompatActivity {

    EditText userFeedback;
    FirebaseDatabase mDatabase;
    DatabaseReference mRefFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);
        userFeedback=findViewById(R.id.userFeedback);
    }

    public void save(View view) {
        String feedback=userFeedback.getText().toString().trim();
        if(feedback.length()>0){
            saveDate(feedback);

        }else{
            Toast.makeText(Add_feedback_activity.this, "Add Feedback Properly", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveDate(String feedback){
        mDatabase = FirebaseDatabase.getInstance();
        mRefFeedback = mDatabase.getReference().child("Feedback");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());

        Feedback obj=new Feedback(date,feedback);

        int feedbackId = AllData.feedbackList.size();
        feedbackId = feedbackId + 1;

        mRefFeedback.child(feedbackId + "").setValue(obj, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null){
                    Toast.makeText(Add_feedback_activity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(Add_feedback_activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}