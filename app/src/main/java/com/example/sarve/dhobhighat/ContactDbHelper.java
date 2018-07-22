package com.example.sarve.dhobhighat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sarve on 23-04-2018.
 */

public class ContactDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "laundry_db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE = "create table "+ContactContract.ContactEntry.TABLE_NAME+"("+ ContactContract.ContactEntry.EMAIL+" text,"+
            ContactContract.ContactEntry.NAME+" text,"+ ContactContract.ContactEntry.PASS+" text);";

    public ContactDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operations", "Database Created");
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.d("Database Operations", "Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(String email, String name, String pass, SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContactEntry.EMAIL, email);
        contentValues.put(ContactContract.ContactEntry.NAME, name);
        contentValues.put(ContactContract.ContactEntry.PASS, pass);

        database.insert(ContactContract.ContactEntry.TABLE_NAME, null, contentValues);
        Log.d("Database Operations", "User Row inserted");
    }
}
