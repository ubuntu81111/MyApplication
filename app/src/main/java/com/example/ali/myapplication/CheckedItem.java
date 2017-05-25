package com.example.ali.myapplication;

import android.content.Context;

import com.orm.SugarRecord;

/**
 * Created by ali on 17-May-17.
 */

public class CheckedItem extends SugarRecord {

    String title;
    String description;
/*    int count;*/

    public CheckedItem() {
    }

    public CheckedItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

  /*  public CheckedItem(int count) {
        this.count = count;
    }
*/

}
