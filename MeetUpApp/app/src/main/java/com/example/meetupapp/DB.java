package com.example.meetupapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DB extends SQLiteOpenHelper {
    private static final String DB_name = "Meetup";
    private static final String table_name = "Meeting";
    public DB(Context context) {
        super(context,DB_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE " + table_name +
                "(MeetingID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Name TEXT(100)," +
                " Description TEXT(100)," +
                " Times time," +
                " Dates date," +
                " Amount INTEGER," +
                " Category TEXT(20)," +
                " Location TEXT(100));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
    }

    public long Insert_Data(String name, String description, String time, String date,String amount,String spin, String lo) {
        // TODO Auto-generated method stub

        try {
            SQLiteDatabase DB;
            DB = this.getWritableDatabase(); // Write Data

            ContentValues Val = new ContentValues();
            Val.put("Name", name);
            Val.put("Description", description);
            Val.put("Times", time);
            Val.put("Dates", date);
            Val.put("Amount", amount);
            Val.put("Category", spin);
            Val.put("Location", lo);



            long rows = DB.insert(table_name, null, Val);

            DB.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }

    }
    public String[][] SelectAllData() {
        // TODO Auto-generated method stub

        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  * FROM " + table_name;
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }

    }
}
