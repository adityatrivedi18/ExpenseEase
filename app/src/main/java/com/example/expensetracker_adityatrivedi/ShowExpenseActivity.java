package com.example.expensetracker_adityatrivedi;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expense);

        // Retrieve employee name from the intent
        String empname = getIntent().getStringExtra("EMPLOYEE_NAME");

        // Find the ListView in the layout
        ListView listViewExpenses = findViewById(R.id.listViewExpenses);

        // Check if empname is not null before querying the database
        if (empname != null) {
            // Retrieve all expense details for the employee
            EmployeeDatabase employeeDatabase = new EmployeeDatabase(this);
            Cursor cursor = employeeDatabase.getEmployeeExpenses(empname);

            // Check if there are expenses for the employee
            if (cursor.getCount() > 0) {
                // Transform cursor data to an array of strings
                String[] expenseDetails = transformCursorToArray(cursor);

                // Display expenses in the ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseDetails);
                listViewExpenses.setAdapter(adapter);
            } else {
                // Handle the case when there are no expenses for the employee
                listViewExpenses.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"No expenses for " + empname}));
            }

            // Close the cursor and database
            cursor.close();
            employeeDatabase.close();
        } else {
            // Handle the case when empname is null
            listViewExpenses.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"Invalid employee name"}));
        }
    }

    // Helper method to transform cursor data to an array of strings
    private String[] transformCursorToArray(Cursor cursor) {
        // Move the cursor to the first row
        cursor.moveToFirst();

        // Get the column index for each column you want to extract
        int empnameIndex = cursor.getColumnIndex("empname");
        int exptypeIndex = cursor.getColumnIndex("exptype");
        int expamtIndex = cursor.getColumnIndex("expamt");
        int expdescIndex = cursor.getColumnIndex("expdesc");

        // Create an array to hold expense details
        String[] expenseDetails = new String[cursor.getCount()];

        // Iterate through the cursor and extract data
        for (int i = 0; i < cursor.getCount(); i++) {
            // Concatenate data into a string
            String expenseDetail = "Employee: " + cursor.getString(empnameIndex) +
                    "\nType: " + cursor.getString(exptypeIndex) +
                    "\nAmount: " + cursor.getDouble(expamtIndex) +
                    "\nDescription: " + cursor.getString(expdescIndex);

            // Add the expense detail to the array
            expenseDetails[i] = expenseDetail;

            // Move to the next row
            cursor.moveToNext();
        }

        return expenseDetails;
    }
}
