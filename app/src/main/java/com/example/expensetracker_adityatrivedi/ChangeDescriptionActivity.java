package com.example.expensetracker_adityatrivedi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeDescriptionActivity extends AppCompatActivity {

    private EmployeeDatabase employeeDatabase;
    private EditText editTextNewDescription;
    private Button btnUpdateDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_description);

        // Retrieve employee name from the intent
        String empName = getIntent().getStringExtra("EMPLOYEE_NAME");

        // Initialize database helper
        employeeDatabase = new EmployeeDatabase(this);

        // Find views
        editTextNewDescription = findViewById(R.id.editTextNewDescription);
        btnUpdateDescription = findViewById(R.id.btnUpdateDescription);

        // Set click listener for the update description button
        btnUpdateDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateExpenseDescription(empName);
            }
        });
    }

    private void updateExpenseDescription(String empName) {
        // Retrieve new description from the EditText
        String newDescription = editTextNewDescription.getText().toString().trim();

        if (!newDescription.isEmpty()) {
            // Fetch the latest expense for the logged-in employee
            Cursor cursor = employeeDatabase.getLatestExpenseForEmployee(empName);

            if (cursor.moveToFirst()) {
                String expenseType = cursor.getString(cursor.getColumnIndex("exptype"));
                double expenseAmount = cursor.getDouble(cursor.getColumnIndex("expamt"));

                // Update the expense description in the database
                if (employeeDatabase.updateExpData(empName, expenseType, expenseAmount, newDescription)) {
                    Toast.makeText(this, "Expense description updated successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Finish the activity to go back to EmpDActivity
                } else {
                    Toast.makeText(this, "Failed to update expense description", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No expenses found for the employee", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a new description", Toast.LENGTH_SHORT).show();
        }
    }

}
