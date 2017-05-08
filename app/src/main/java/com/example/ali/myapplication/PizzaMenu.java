package com.example.ali.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PizzaMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_menu);


    }


    public void menu(View v) {
        Intent intent = new Intent(getApplicationContext(), ListDisplay.class);
        finish();
        startActivity(intent);
    }


    public void offers(View v) {
        Intent intent = new Intent(getApplication(), Offers.class);
        finish();
        startActivity(intent);
    }

}
