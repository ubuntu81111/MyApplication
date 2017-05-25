package com.example.ali.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

public class OrderDetails extends AppCompatActivity {

    private ListView orderDetails;
    List<CheckedItem> numberOfRecords;
    ArrayList myList = new ArrayList();
    Context context = OrderDetails.this;
    CustomAdapter myCustomAdapter;
    ListData ld = new ListData();
    RadioButton mRadioButtonsmall, mRadioButtonmedium, mRadioButtonLarge;
    private EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        // to populate last data
        ld = new ListData();


        orderDetails = (ListView) findViewById(R.id.orderList);

        //to delete any existing datafrom cursor
       /* Cursor curson = CheckedItem.getCursor(CheckedItem.class,null,null,null,null,null,null);*/
/*
        Cursor cursor = CheckedItem.getCursor(CheckedItem.class,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            CheckedItem.deleteAll(CheckedItem.class);

        }*/

/*
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {
                    case R.id.radiosmall:
                        price.setText(BasePrice.getPrice() + 10);
                        break;

                    case R.id.radiomedium:
                        price.setText(BasePrice.getPrice() + 20);
                        break;

                    case R.id.radiolarge:
                        price.setText(BasePrice.getPrice() + 20);
                        break;

                    default:
                        price.setText(BasePrice.getPrice());
                }
            }
        });
*/

  /*      RadioRealButtonGroup group = (RadioRealButtonGroup) findViewById(R.id.group);
*//*

// onClickButton listener detects any click performed on buttons by touch
        group.setOnClickedButtonListener(new RadioRealButtonGroup.OnClickedButtonListener() {
            @Override
            public void onClickedButton(RadioRealButton button, int position) {
                Toast.makeText(context, "Clicked! Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
*/

//*/ onPositionChanged listener detects if there is any change in position
/*
        group.setOnPositionChangedListener(new RadioRealButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(RadioRealButton button, int currentPosition, int lastPosition) {
                Toast.makeText(context, "Position Changed! Position: " + currentPosition, Toast.LENGTH_SHORT).show();
            }
        });
*/


        // insert data into the list before setting the adapter
        // otherwise it will generate NullPointerException  - Obviously
        getDataInOrderList();
        myCustomAdapter = new CustomAdapter(context, myList);
        orderDetails.setAdapter(myCustomAdapter);
    }

/*
    private void clickChange(View view) {
        switch (view.getId()) {
            case R.id.button21:
                price.setText(BasePrice.getPrice() + 10);
                break;

            case R.id.button22:
                price.setText(BasePrice.getPrice() + 20);
                break;

            case R.id.button23:
                price.setText(BasePrice.getPrice() + 20);
                break;

            default:
                price.setText(BasePrice.getPrice());
        }
    }
*/
/*
    public void getDataInOrderList() {
        for (int i = 0; i < Count.getCount(); i++) {
            // Create a new object for each list item
            ListData ld = new ListData();
            ld.setTitle(title[i]);
            ld.setDescription(desc[i]);
            ld.setImgResId(img[i]);
            // Add this object into the ArrayList myList
            myList.add(ld);
        }
    }*/


    public void getDataInOrderList() {

        ArrayList<String> arrTitle = new ArrayList<>();
        ArrayList<String> arrDesc = new ArrayList<>();
        List<CheckedItem> allCheckedItems = CheckedItem.listAll(CheckedItem.class);

        for (CheckedItem checkedItem : allCheckedItems) {
            arrTitle.add(checkedItem.title); // or arr.add(contact.name); if it's public
            arrDesc.add(checkedItem.description);
            Toast.makeText(getApplicationContext(), checkedItem.description, Toast.LENGTH_SHORT).show();
        }

        for (int i = 0; i < Count.getCount(); i++) {
            // Create a new object for each list item

            ld.setTitle(arrTitle.get(i));
            ld.setDescription(arrDesc.get(i));
            /*
             ld.setTitle("qqww");
            ld.setDescription("wq");*/
            //ld.setImgResId(img[i]);
            // Add this object into the ArrayList myList
            myList.add(ld);
        }
    }
}
