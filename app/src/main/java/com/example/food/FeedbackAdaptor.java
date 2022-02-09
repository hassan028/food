package com.example.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FeedbackAdaptor extends BaseAdapter {

    List<Feedback> feedbackList;
    Context c;
    LayoutInflater inflater;

    public FeedbackAdaptor(List<Feedback> feedbackList, Context c) {
        this.feedbackList = feedbackList;
        this.c=c;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return feedbackList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.feedback_row, null);
        Feedback temp = feedbackList.get(position);

        TextView date = v.findViewById(R.id.date);
        TextView feedback = v.findViewById(R.id.feedback);
        date.setText(temp.getCurrentDate());
        feedback.setText(temp.getFeedback());
        return v;
    }
}
