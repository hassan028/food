package com.example.food;

public class Feedback {
    String CurrentDate,feedback;

    public Feedback() {
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Feedback(String currentDate, String feedback) {
        CurrentDate = currentDate;
        this.feedback = feedback;
    }
}
