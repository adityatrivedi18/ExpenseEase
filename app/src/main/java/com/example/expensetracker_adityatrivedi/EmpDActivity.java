package com.example.expensetracker_adityatrivedi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class EmpDActivity extends AppCompatActivity {

    private EmployeeDatabase employeeDatabase;
    private String empName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_dactivity);

        // Retrieve employee name from the intent
        empName = getIntent().getStringExtra("EMPLOYEE_NAME");

        // Initialize database helper
        employeeDatabase = new EmployeeDatabase(this);

        // Find views
        CardView btnAddExpense = findViewById(R.id.btnAddExpense);
        CardView btnShowExpense = findViewById(R.id.btnShowExpense);
        CardView btnContactUs = findViewById(R.id.btnContactUs);
        CardView btnSignOut = findViewById(R.id.btnSignOut);

        // Set click listeners for cards
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start a new activity for adding a new expense
                Intent intent1 = new Intent(EmpDActivity.this, AddExpenseActivity.class);
                intent1.putExtra("EMPLOYEE_NAME", empName); // Pass empName to AddExpenseActivity
                startActivity(intent1);
            }
        });

        btnShowExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start a new activity for showing expenses
                Intent intent2 = new Intent(EmpDActivity.this, ShowExpenseActivity.class);
                intent2.putExtra("EMPLOYEE_NAME", empName); // Pass empName to ShowExpenseActivity
                startActivity(intent2);
            }
        });

        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmpDActivity.this,ContactUsActivity.class));
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the home page (you need to replace HomeActivity.class with your home page activity)
                Intent intent = new Intent(EmpDActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Optional: finish this activity
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection when the activity is destroyed
        if (employeeDatabase != null) {
            employeeDatabase.close();
        }
    }
}
