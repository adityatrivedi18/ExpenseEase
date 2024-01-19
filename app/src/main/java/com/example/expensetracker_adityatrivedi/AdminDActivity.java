package com.example.expensetracker_adityatrivedi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dactivity);

        // Display a welcome message to the admin
        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText("Hello Admin!");

        // Button to go to activity that calculates the sum of employee expenses
        Button btnCalculateSum = findViewById(R.id.btnCalculateSum);
        btnCalculateSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDActivity.this, CalculateSumActivity.class);
                startActivity(intent);
            }
        });

        // Button to go to activity that shows expense details for employees
        Button btnShowExpenseDetails = findViewById(R.id.btnShowExpenseDetails);
        btnShowExpenseDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDActivity.this, ShowExpenseDetailsActivity.class);
                startActivity(intent);
            }
        });

        // Button to sign out and redirect to the home page
        Button btnSignOut = findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Assuming you have a LoginActivity as the home page
                Intent intent = new Intent(AdminDActivity.this, MainActivity.class);
                // Add flags to clear the back stack and start a new task
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish(); // Finish the current activity to prevent going back to it from the home page
            }
        });
    }
}
