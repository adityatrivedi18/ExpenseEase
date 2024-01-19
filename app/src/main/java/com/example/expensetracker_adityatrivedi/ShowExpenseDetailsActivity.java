package com.example.expensetracker_adityatrivedi;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ShowExpenseDetailsActivity extends AppCompatActivity {

    private EmployeeDatabase employeeDatabase;
    private Spinner spinnerEmployees;
    private ListView listViewExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expense_details);
        Log.d("ShowExpenseDetails", "onCreate called");
        employeeDatabase = new EmployeeDatabase(this);

        // Initialize views
        spinnerEmployees = findViewById(R.id.spinnerEmployees);
        listViewExpenses = findViewById(R.id.listViewExpenses);

        // Populate the spinner with employee names
        populateSpinner();

        // Set listener for spinner item selection
        spinnerEmployees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                showExpenseDetails();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }

    private void populateSpinner() {
        String[] employeeNames = {"Employee1", "Employee2", "Employee3", "Employee4", "Employee5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, employeeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployees.setAdapter(adapter);
    }

    private void showExpenseDetails() {
        String selectedEmployee = spinnerEmployees.getSelectedItem().toString();

        // Fetch expense details for the selected employee from the database
        Cursor cursor = employeeDatabase.getEmployeeExpenses(selectedEmployee);

        // Placeholder implementation to extract expense details from the cursor
        List<String> expenseDetailsList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                // Customize this part based on your database structure
                String expenseType = cursor.getString(cursor.getColumnIndex("exptype"));
                double expenseAmount = cursor.getDouble(cursor.getColumnIndex("expamt"));
                String expenseDescription = cursor.getString(cursor.getColumnIndex("expdesc"));

                // Create a string representing the expense details
                String expenseDetails = "Type: " + expenseType + "\nAmount: " + expenseAmount + "\nDescription: " + expenseDescription;
                expenseDetailsList.add(expenseDetails);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // Display the expense details in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseDetailsList);
        listViewExpenses.setAdapter(adapter);
    }
}
