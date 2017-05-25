package com.example.ali.myapplication;

/**
 * Created by ali on 20-May-17.
 */

public class Count {
    private static int count = 0;


    public static int incsetcount() {
        return count++;
    }

    public static int decsetcount() {
        return count--;
    }

    public static int getCount() {
        return count;
    }
}
