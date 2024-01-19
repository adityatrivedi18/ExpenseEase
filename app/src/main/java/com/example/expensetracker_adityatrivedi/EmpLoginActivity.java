package com.example.expensetracker_adityatrivedi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class EmpLoginActivity extends AppCompatActivity {

    // Assume Employee class with name and password properties
    private List<Employee> employeeList;

    private EditText editTextName;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_login);

        // Initialize employeeList with static employee details
        employeeList = getStaticEmployeeList();

        // Find views
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Set click listener for the login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });
    }

    // Sample method to create a static list of employees
    private List<Employee> getStaticEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Employee1", "password1"));
        employees.add(new Employee("Employee2", "password2"));
        employees.add(new Employee("Employee3", "password3"));
        employees.add(new Employee("Employee4", "password4"));
        employees.add(new Employee("Employee5", "password5"));
        return employees;
    }

    // Validate employee login
    private void validateLogin() {
        String enteredName = editTextName.getText().toString();
        String enteredPassword = editTextPassword.getText().toString();

        for (Employee employee : employeeList) {
            if (employee.getName().equals(enteredName) && employee.getPassword().equals(enteredPassword)) {
                // Successful login, send employee name to the dashboard activity
                Intent intent = new Intent(EmpLoginActivity.this, EmpDActivity.class);
                intent.putExtra("EMPLOYEE_NAME", enteredName);
                startActivity(intent);
                finish();  // Optional: finish this login activity
                return;
            }
        }

        // Invalid credentials
        Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
    }

}

