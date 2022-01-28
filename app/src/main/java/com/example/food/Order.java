package com.example.food;

import java.util.Date;

public class Order {
    String CurrentDate;
    long OrderByDay,TotalOrders;

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }

    public long getOrderByDay() {
        return OrderByDay;
    }

    public void setOrderByDay(long orderByDay) {
        OrderByDay = orderByDay;
    }

    public long getTotalOrders() {
        return TotalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        TotalOrders = totalOrders;
    }

    public Order() {
    }

    public Order(String currentDate, long orderByDay, long totalOrders) {
        CurrentDate = currentDate;
        OrderByDay = orderByDay;
        TotalOrders = totalOrders;
    }
}

//import java.util.Calendar;
//        import java.util.Date;
//        import java.text.DateFormat;
//        import java.text.SimpleDateFormat;
//
//class GFG {
//
//    // Function to convert date to string
//    public static String
//    convertDateToString(String date)
//    {
//        // Converts the string
//        // format to date object
//        DateFormat df = new SimpleDateFormat(date);
//
//        // Get the date using calendar object
//        Date today = Calendar.getInstance()
//                .getTime();
//
//        // Convert the date into a
//        // string using format() method
//        String dateToString = df.format(today);
//
//        // Return the result
//        return (dateToString);
//    }
//
//    // Driver Code
//    public static void main(String args[])
//    {
//
//        // Given Date
//        String date = "07-27-2020";
//
//        // Convert and print the result
//        System.out.print(
//                convertDateToString(date));
//    }
//}