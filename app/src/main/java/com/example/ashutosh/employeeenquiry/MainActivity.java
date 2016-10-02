package com.example.ashutosh.employeeenquiry;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashutosh.employeeenquiry.data.EmployeeContract;
import com.example.ashutosh.employeeenquiry.data.EmployeeDbHelper;
import com.example.ashutosh.employeeenquiry.model.EmpInfo;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declare  all visible and invisible  component
    //visible component

    EditText empName, empRegId, empSalary, empPost;
    Button submitBtn;
    ListView empListView;
    //Invisible Component
    private ArrayList<EmpInfo> empArrayList ;
    private MyAdapter myAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize Visible Commponent
        empName = (EditText) findViewById(R.id.empName);
        empRegId = (EditText) findViewById(R.id.empRegId);
        empSalary = (EditText) findViewById(R.id.empSalary);
        empPost = (EditText) findViewById(R.id.empDesignation);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        empListView = (ListView) findViewById(R.id.empListView);

        //Initialize invisible Component
        myAdapter = new MyAdapter();
        empArrayList = new ArrayList<EmpInfo>();
        //Establish  Link between DESTINATION and ADAPTER
        empListView.setAdapter(myAdapter);

        empListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
                EmpInfo empInfo = empArrayList.get(position);
                checkBox.setChecked(!checkBox.isChecked());
                empInfo.setSelected(checkBox.isChecked());
                Toast.makeText(MainActivity.this, "" + empInfo.getEmployeeName() + "" + empInfo.isSelected(), Toast.LENGTH_SHORT).show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeDbHelper helper = new EmployeeDbHelper(MainActivity.this);
                ContentValues contentValues=new ContentValues();
                contentValues.put(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_NAME, "Amit");
                contentValues.put(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_SALARY,50000);
                contentValues.put(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_DESIGNATION,"Manager");
                //Opening Connection to database
                SQLiteDatabase database=helper.getWritableDatabase();
                //Insert Database
                database.insert(EmployeeContract.EmployeeEntry.TABLE_NAME,null,contentValues);
                //close Database Connection
                database.close();
                helper.close();


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    private void readFromDatabaseAndUpdateListView()
    {
        EmployeeDbHelper helper=new EmployeeDbHelper(this);
        //open connection
        SQLiteDatabase database=helper.getReadableDatabase();
        String[] projections={EmployeeContract.EmployeeEntry._ID, EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_NAME, EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_SALARY, EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_DESIGNATION};
        String selection= EmployeeContract.EmployeeEntry._ID+"> ? AND"+ EmployeeContract.EmployeeEntry._ID+"< ?";
        String[] selectionArgs={"1","5"};
        Cursor c=database.query(EmployeeContract.EmployeeEntry.TABLE_NAME,projections,selection,selectionArgs,null,null,null);
        if(c.moveToFirst())
        {
            do {
                int id = c.getInt(0);
                String name=c.getString(1);
                int salary=c.getInt(2);
                String designation=c.getString(3);
                EmpInfo e=new EmpInfo(""+id,name,""+salary,designation);
                empArrayList.add(e);
            }
            while (c.moveToNext());
        }
    }


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return empArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return empArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            EmpInfo empInfo = empArrayList.get(position);
           /* View v = getLayoutInflater().inflate(R.layout.emp_row, null);

            TextView textViewId = (TextView) v.findViewById(R.id.textViewId);
            TextView textViewName = (TextView) v.findViewById(R.id.textViewName);
            TextView textViewPost = (TextView) v.findViewById(R.id.textViewPost);
            TextView textViewSalary = (TextView) v.findViewById(R.id.textViewSalary);

            textViewId.setText("" + enquiry.getSerial());
            textViewName.setText(enquiry.getEmployeeName());
            textViewPost.setText(enquiry.getEmployeePost());
            textViewSalary.setText(enquiry.getEmployeeSalary());
            */
            View emp_row= getLayoutInflater().inflate(R.layout.emp_row,null);
            TextView textViewId,textViewName,textViewSalary,textViewPost;
            CheckBox checkBox = (CheckBox)emp_row.findViewById(R.id.checkBox);
            textViewId=(TextView)emp_row.findViewById(R.id.textViewId);
            textViewName=(TextView)emp_row.findViewById(R.id.textViewName);
            textViewSalary=(TextView)emp_row.findViewById(R.id.textViewSalary);
            textViewPost=(TextView)emp_row.findViewById(R.id.textViewPost);

            //Getting data from the source and filling in emp_row.xml
            textViewId.setText(empInfo.getId());
            textViewName.setText(empInfo.getEmployeeName());
            textViewPost.setText(empInfo.getEmployeePost());
            textViewSalary.setText(empInfo.getEmployeeSalary());

            return emp_row;
        }
    }
}