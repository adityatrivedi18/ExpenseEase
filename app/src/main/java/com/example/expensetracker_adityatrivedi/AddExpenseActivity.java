package com.example.expensetracker_adityatrivedi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    private EmployeeDatabase employeeDatabase;
    private EditText editTextExpenseType;
    private EditText editTextExpenseAmount;
    private EditText editTextExpenseDescription;
    private Button btnAddExpense,bckbtn1;
    private String empName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        // Retrieve employee name from the intent
        empName = getIntent().getStringExtra("EMPLOYEE_NAME");

        // Initialize database helper
        employeeDatabase = new EmployeeDatabase(this);

        // Find views
        editTextExpenseType = findViewById(R.id.editTextExpenseType);
        editTextExpenseAmount = findViewById(R.id.editTextExpenseAmount);
        editTextExpenseDescription = findViewById(R.id.editTextExpenseDescription);
        btnAddExpense = findViewById(R.id.btnAddExpense);
        bckbtn1=findViewById(R.id.back1);
        // Set click listener for the add expense button
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addExpense();
            }
        });
        bckbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddExpenseActivity.this, EmpDActivity.class));
            }
        });
    }

    private void addExpense() {
        // Retrieve expense details from the EditText
        String expenseType = editTextExpenseType.getText().toString().trim();
        String expenseAmountStr = editTextExpenseAmount.getText().toString().trim();
        String expenseDescription = editTextExpenseDescription.getText().toString().trim();

        if (!expenseType.isEmpty() && !expenseAmountStr.isEmpty() && !expenseDescription.isEmpty()) {
            // Parse expense amount to double
            double expenseAmount = Double.parseDouble(expenseAmountStr);

            // Add expense to the database using empName as primary key
            if (employeeDatabase.addExpData(empName, expenseType, expenseAmount, expenseDescription)) {
                Toast.makeText(this, "Expense added successfully", Toast.LENGTH_SHORT).show();
                finish(); // Finish the activity to go back to EmpDActivity
            } else {
                Toast.makeText(this, "Failed to add expense", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter all expense details", Toast.LENGTH_SHORT).show();
        }
    }

}
