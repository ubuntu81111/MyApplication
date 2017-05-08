package com.example.ali.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ali on 08-May-17.
 */

class  SingleListItem extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_list_item_view);

        TextView txtProduct = (TextView) findViewById(R.id.product_label);

        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("product");
        // displaying selected product name
        txtProduct.setText(product);

    }
}
