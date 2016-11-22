package com.example.lasse.magretheskaal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 22-11-2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MagretheManager";

    // Contacts table name
    private final String TABLE_NAMES;

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    public DBHelper(Context context, String table_names) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        TABLE_NAMES = table_names;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAMES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMES);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addName(NamesFromDB contact) {   SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name

        // Inserting Row
        db.insert(TABLE_NAMES, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Contacts
    public ArrayList<String> getAllNames() {  ArrayList<String> contactList = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAMES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                // Adding contact to list
                contactList.add(name);
            } while (cursor.moveToNext());
        }
/*        List<NamesFromDB> contactList = new ArrayList<NamesFromDB>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAMES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NamesFromDB name = new NamesFromDB();
                name.setID(Integer.parseInt(cursor.getString(0)));
                name.setName(cursor.getString(1));
                // Adding contact to list
                contactList.add(name);
            } while (cursor.moveToNext());
        }
*/
        // return contact list
        return contactList;
    }

    // Getting contacts Count
    public int getNamesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAMES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
