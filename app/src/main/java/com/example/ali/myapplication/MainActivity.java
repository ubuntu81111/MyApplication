package com.example.ali.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ali on 09-May-17.
 */
public class MainActivity extends AppCompatActivity {

    ListView lvDetail;
    TextView mTextView;
    Context context = MainActivity.this;
    ArrayList myList = new ArrayList();
    MyBaseAdapter myBaseAdapter;
    FloatingActionButton mFloatingActionButton;
    LoginDataBaseAdapter loginDataBaseAdapter;
    static Button notifCount;
    static int mNotifCount = 0;
    int count = 0;
    private int mNotificationsCount = 0;
    private int hot_number = 0;
    private TextView ui_hot = null;
    CheckedItem checkedItem1;

    String[] title = new String[]{
            "Title 1", "Title 2", "Title 3", "Title 4",
            "Title 5", "Title 6", "Title 7", "Title 8"
    };
    String[] desc = new String[]{
            "Desc 1", "Desc 2", "Desc 3", "Desc 4",
            "Desc 5", "Desc 6", "Desc 7", "Desc 8"
    };
    int[] img = new int[]{
            R.drawable.star1, R.drawable.star2, R.drawable.star3, R.drawable.star4,
            R.drawable.star5, R.drawable.star6, R.drawable.star7, R.drawable.star8
    };
    private HashMap<Integer, Long> allSavedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();


        mTextView = (TextView) findViewById(R.id.textview);
        mTextView.setText(String.valueOf(count));
        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();


        //to Truncate the db
        Trancatedb.closedb(context);




        //adding floation button
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button_fab_with_listview);

        allSavedItems = new HashMap<Integer, Long>();
        lvDetail = (ListView) findViewById(R.id.lvCustomList);
        // insert data into the list before setting the adapter
        // otherwise it will generate NullPointerException  - Obviously
        getDataInList();
        myBaseAdapter = new MyBaseAdapter(context, myList);
        lvDetail.setAdapter(myBaseAdapter);

        lvDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView iVTick = (ImageView) view.findViewById(R.id.imageViewTick);

                mTextView.setText(String.valueOf(count));
                iVTick.setVisibility(View.VISIBLE);
                ListData oneItem = (ListData) myList.get(position);
                CheckedItem checkedItem = new CheckedItem(oneItem.getTitle(), oneItem.getDescription());
                Count.incsetcount();

                //setting cart
                mTextView.setText(String.valueOf(Count.getCount()));


                /*     count = count + 1;
                checkedItem1 = new CheckedItem(count);
                checkedItem1.save();*/
                Toast.makeText(getApplication(), oneItem.getTitle(), Toast.LENGTH_SHORT).show();
                long oneSavedItem = checkedItem.save();
                int oneSavedItemPos = position;
                allSavedItems.put(oneSavedItemPos, oneSavedItem);
            }
        });


        lvDetail.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (lvDetail.isItemChecked(position)) {
                    ImageView imageView = (ImageView) view.findViewById(R.id.imageViewTick);
                    Count.decsetcount();
                /*count = count - 1;
                checkedItem1 = new CheckedItem(count);
                checkedItem1.save();*/

                    //setting cart
                    mTextView.setText(String.valueOf(Count.getCount()));

                    imageView.setVisibility(View.INVISIBLE);
                    Long oneSavedItem = allSavedItems.get(position);

                    CheckedItem checkedItem = CheckedItem.findById(CheckedItem.class, oneSavedItem);
                    boolean value = checkedItem.delete();
                    if (value) {
                        Toast.makeText(getApplicationContext(), "item removed from db", Toast.LENGTH_SHORT).show();
                    }
                    allSavedItems.remove(position);
                    return true;


                } else {
                    Toast.makeText(getApplicationContext(), "do long chick on checked item please", Toast.LENGTH_SHORT).show();

                }

                return true;
            }
        });


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "floating butn pressed", Toast.LENGTH_SHORT).show();
                ListData ld = new ListData();
                /*loginDataBaseAdapter.updateEntry(ld.getDescription(),ld.getTitle(),ld.getImgResId());*/
                SparseBooleanArray checked = lvDetail.getCheckedItemPositions();
                for (int i = 0; i < lvDetail.getAdapter().getCount(); i++) {
                    if (checked.get(i)) {

                        Intent intent = new Intent(getApplicationContext(), OrderDetails.class);
                        finish();
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "checking out!!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle(R.string.toolbarTitle);
        setSupportActionBar(toolbar);
   /*     ImageView toolbarbtn= (ImageView) toolbar.findViewById(R.id.toobarbtn);
        toolbarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"jkl",Toast.LENGTH_SHORT).show();
            }
        });*/

        /*  toolbar.setNavigationIcon(R.drawable.toolbar_arrow);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "clicking the toolbar!", Toast.LENGTH_SHORT).show();
                    }
                }
        );*/
    }

    private void getDataInList() {
        for (int i = 0; i < title.length; i++) {
            // Create a new object for each list item
            ListData ld = new ListData();
            ld.setTitle(title[i]);
            ld.setDescription(desc[i]);
            ld.setImgResId(img[i]);
            // Add this object into the ArrayList myList
            myList.add(ld);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}