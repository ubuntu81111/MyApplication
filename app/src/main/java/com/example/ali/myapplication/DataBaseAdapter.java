package com.example.ali.myapplication;

/**
 * Created by ali on 27-Apr-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseAdapter extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "datastore";

    // Contacts table name
    private static final String TABLE_CONTACTS = "datatable";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String DESCRIPTION = "Description";
    private static final String TITLE = "title";
    private static final String IMGRESID = "imgResId";

    public DataBaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + DESCRIPTION + " TEXT,"
                + TITLE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
    }/*
    //getContact()
    // Getting single contact
    public  getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        , }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }*/
    //getContactsCount()
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    //addContact()
    // Adding new contact
    public void insertingListdata(int id,String description,String title) {
        SQLiteDatabase db = this.getWritableDatabase();
ListData ld=new ListData();
        ContentValues values = new ContentValues();
        values.put(TITLE, ld.getTitle()); // Contact Name
        values.put(DESCRIPTION, ld.getDescription()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    //getContact()
    // Getting single contact
    public String getTitle(int id) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, null, "id=?", new String[]{KEY_ID}, null, null, null);
        if (cursor.getCount() < 1)//UserName Not Exist
        {
            cursor.close();
            return "Not Exist";
        }
        cursor.moveToFirst();

        String titleInDB = cursor.getString(cursor.getColumnIndex(TITLE));
        cursor.close();
        return titleInDB;
    }

}