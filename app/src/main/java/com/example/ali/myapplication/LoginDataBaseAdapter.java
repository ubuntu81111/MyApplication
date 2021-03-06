package com.example.ali.myapplication;

/**
 * Created by ali on 27-Apr-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import static android.R.attr.description;
import static android.R.attr.id;

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 2;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.

    static final String DATABASE_CREATE = "create table " + "LOGIN" +
            "( " + "ID" + " integer primary key autoincrement," + "USERNAME  text,PASSWORD text,SPINNERQUESTION text,SPINNERANSWER text); ";


    /*
        static final String DATABASE_CREATE1 = "create table " + "LOGIN" +
                "( " + "ID" + " integer primary key autoincrement," + "description text,title text,image integer);";
        */
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String userName, String password, String spinnerQuestion, String spinnerAnswer) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);
        newValues.put("SPINNERQUESTION", spinnerQuestion);
        newValues.put("SPINNERANSWER", spinnerAnswer);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String UserName) {
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{UserName});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public String getUserName(String userName) {
        Cursor cursor = db.query("LOGIN", null, "USERNAME=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1)//UserName Not Exist
        {
            cursor.close();
            return "Not Exist";
        }
        cursor.moveToFirst();
        String userNameInDB = cursor.getString(cursor.getColumnIndex("USERNAME"));
        cursor.close();
        return userNameInDB;
    }

    public void updateEntry(String description, String title, int imgResId) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row./*
        //updatedValues.put("USERNAME", userName);
        //  updatedValues.put("PASSWORD", password);

        updatedValues.put("description", description);
        updatedValues.put("title", title);
        updatedValues.put("imgResId", imgResId);

        /*String where = "USERNAME = ?";
        db.update("Data", updatedValues, where, new String[]{userName});*/
    }

    public void passwordReset(String userName, String password, String spinnerAnswer, String spinnerQuestion) {

        ContentValues updatedValues = new ContentValues();

        updatedValues.put("PASSWORD", password);
        updatedValues.put("SPINNERQUESTION", spinnerQuestion);
        updatedValues.put("SPINNERANSWER", spinnerAnswer);
        updatedValues.put("USERNAME", userName);

        String where = "USERNAME = ?";

        db.update("LOGIN", updatedValues, where, new String[]{userName});
    }

    public String getSpinner(String userName) {

        Cursor cursor = db.query("LOGIN", null, "USERNAME=? ", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1)//UserName Not Exist
        {
            cursor.close();
            return "Not Exist";
        }
        cursor.moveToFirst();
        String spinnerInDb = cursor.getString(cursor.getColumnIndex("SPINNERQUESTION"));
        cursor.close();
        return spinnerInDb;
    }

    public String getSpinneranswer(String userName) {

        Cursor cursor = db.query("LOGIN", null, "SPINNERANSWER=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1)//UserName Not Exist
        {
            cursor.close();
            return "Not Exist";
        }
        cursor.moveToFirst();
        String spinneranswerInDb = cursor.getString(cursor.getColumnIndex("SPINNERANSWER"));
        cursor.close();
        return spinneranswerInDb;
    }
}
