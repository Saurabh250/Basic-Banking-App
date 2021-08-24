package com.example.gogobank.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gogobank.DB.UserContract.UserEntry;

import java.util.Arrays;

public class UserHelper extends SQLiteOpenHelper {

    String TABLE_NAME = UserEntry.TABLE_NAME;

    /** Name of the database file */
    private static final String DATABASE_NAME = "User.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.*/
    private static final int DATABASE_VERSION = 1;

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_USER_TABLE =  "CREATE TABLE " + UserEntry.TABLE_NAME + " ("
                + UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " INTEGER, "
                + UserEntry.COLUMN_USER_NAME + " VARCHAR, "
                + UserEntry.COLUMN_USER_EMAIL + " VARCHAR, "
                + UserEntry.COLUMN_USER_IFSC_CODE + " VARCHAR, "
                + UserEntry.COLUMN_USER_PHONE_NO + " VARCHAR, "
                + UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " INTEGER NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_TABLE);

        // Insert Into Table
        for (String s : Arrays.asList(" values(7860,'Siddharth Pugalia', 'sidpug@gmail.com','7584','9433887672', 15000)",
                                        " values(5862,'Tanishq Parekh', 'gtaniskq@gmail.com','1258','8995641238', 5000)",
                                        " values(7895,'Surya Pratap', 'surya@gmail.com','8896','7595645896', 1000)",
                                        " values(1258,'Vikram Garasiya', 'vikram@gmail.com','7752','9995640038', 8000)",
                                        " values(7410,'Shivani Kumari', 'shivani@gmail.com','3669','9095648962', 7500)",
                                        " values(8529,'Piyush Joshi', 'piyush@gmail.com','9985','8855640238', 6500)", " values(3698,'Yash Pratap', 'yash@gmail.com','1207','8895640215', 4500)", " values(7853,'Khushi Jain', 'khushi@gmail.com','4522','9985021539', 2500)", " values(4562,'Ritik Sharma', 'ritik@gmail.com','6582','9309565238', 10500)", " values(2365,'Rohit Patidar', 'rohit@gmail.com','5450','8292591201', 9900)", " values(7854,'Anurag Sharma', 'anurag@gmail.com','2656','9015641200', 9800)", " values(3621,'Hitish Kumar', 'hitish@gmail.com','1203','9995641999', 11000)", " values(1122,'Naveen Chaturvedi', 'naveen@gmail.com','5566','9119541001', 5800)", " values(9512,'Gauri Parashar', 'gauri@gmail.com','2236','6254642205', 3500)", " values(7530,'Farhan Khan', 'farhan@gmail.com','6692','6893641266', 1010)")) {
            db.execSQL("insert into " + TABLE_NAME + s);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + UserEntry.TABLE_NAME, null);
    }

    public Cursor readParticularData (int accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + UserEntry.TABLE_NAME + " where " +
                                        UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo, null);
    }

    public void updateAmount(int accountNo, int amount) {
        Log.d ("TAG", "update Amount");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + UserEntry.TABLE_NAME + " set " + UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " = " + amount + " where " +
                UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo);
    }
}