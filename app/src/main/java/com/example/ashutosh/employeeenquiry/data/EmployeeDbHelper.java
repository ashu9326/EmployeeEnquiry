package com.example.ashutosh.employeeenquiry.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ashutosh on 04-09-2016.
 */
public class EmployeeDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="employees.db";
    public static final int DATABASE_VERSION=1;

    private static String SQL_CREATE_EMPLOYEE_TABLE = "CREATE TABLE "+ EmployeeContract.EmployeeEntry.TABLE_NAME+"("
            +   EmployeeContract.EmployeeEntry._ID +" INTEGER PRIMARY KEY,"
            +   EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_NAME + " TEXT NOT NULL,"
            +   EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_DESIGNATION + " TEXT DEFAULT 'Clerk',"
            +   EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_SALARY + " INTEGER DEFAULT 0);";

    private static String SQL_DROP_EMPLOYEE_TABLE = "DROP TABLE "+ EmployeeContract.EmployeeEntry.TABLE_NAME+";";
    public EmployeeDbHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EMPLOYEE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      if(oldVersion<newVersion)
          db.execSQL(SQL_DROP_EMPLOYEE_TABLE);
        onCreate(db);
    }
}
