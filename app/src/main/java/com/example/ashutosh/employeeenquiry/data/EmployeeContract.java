package com.example.ashutosh.employeeenquiry.data;

import android.provider.BaseColumns;

/**
 * Created by Ashutosh on 04-09-2016.
 */
public final class EmployeeContract {
    private EmployeeContract()
    {

    }
    public static class EmployeeEntry implements BaseColumns
    {
        public static final String TABLE_NAME="employee";
        public static final String COLUMN_EMPLOYEE_NAME="name";
        public static final String COLUMN_EMPLOYEE_SALARY="salary";
        public static final String COLUMN_EMPLOYEE_DESIGNATION="designation";
    }
}
