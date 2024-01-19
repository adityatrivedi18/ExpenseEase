package com.example.expensetracker_adityatrivedi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculateSumActivity extends AppCompatActivity {

    private EmployeeDatabase employeeDatabase;
    private Spinner spinnerEmployees;
    private TextView textViewTotalExpense;
    Button bck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_sum);
        Log.d("CalculateSumActivity", "onCreate called");
        employeeDatabase = new EmployeeDatabase(this);

        bck=findViewById(R.id.backbtn);
        spinnerEmployees = findViewById(R.id.spinnerEmployees);
        textViewTotalExpense = findViewById(R.id.textViewTotalExpense);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalculateSumActivity.this,AdminDActivity.class));
            }
        });

        // Populate the spinner with employee names
        populateSpinner();

        // Set listener for spinner item selection
        spinnerEmployees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                calculateTotalExpense();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }

    private void populateSpinner() {
        String[] employeeNames = {"All Employees", "Employee1", "Employee2", "Employee3", "Employee4", "Employee5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, employeeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployees.setAdapter(adapter);
    }

    private void calculateTotalExpense() {
        String selectedEmployee = spinnerEmployees.getSelectedItem().toString();
        double totalExpense;

        if (selectedEmployee.equals("All Employees")) {
            totalExpense = employeeDatabase.getTotalExpenseAmountForAllEmployees();
        } else {
            totalExpense = employeeDatabase.getTotalExpenseAmountForEmployee(selectedEmployee);
        }

        textViewTotalExpense.setText("Total expense amount: " + totalExpense);
    }


}
