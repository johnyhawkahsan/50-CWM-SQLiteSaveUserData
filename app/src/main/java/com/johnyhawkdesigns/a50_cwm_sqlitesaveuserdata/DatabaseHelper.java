package com.johnyhawkdesigns.a50_cwm_sqlitesaveuserdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ahsan on 7/20/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "people.db";
    public static final String TABLE_NAME = "people_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "EMAIL";
    public static final String COL4 = "TVSHOW";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT, EMAIL TEXT, TVSHOW TEXT)";
        db.execSQL(createTable);
        Log.d(TAG, "createTable: " + createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * @retrun Boolean
     * Add Data to Database
     */
    public Boolean addData(String name, String email, String tvShow){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, email);
        contentValues.put(COL4, tvShow);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        } else {
            return true;
        }
    }


    /**
     * @retrun Cursor
     * View Data by searching all Table
     */
    public Cursor viewData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;

    }

    /**
     * @return Boolean
     * Update data based on id argument
     */
    public boolean updateData(String id, String name, String email, String tvShow){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, name);
        contentValues.put(COL3, email);
        contentValues.put(COL4, tvShow);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id}); //Where Clause is "ID = ?" while where args is an array form id
        return true;

    }


    /**
     * @return Integer
     * Delete data from database based on id
     */
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }


}
