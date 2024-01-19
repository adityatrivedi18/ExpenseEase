package com.example.expensetracker_adityatrivedi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdmLoginActivity extends AppCompatActivity {

    // Replace with your admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    private EditText editTextAdminUsername;
    private EditText editTextAdminPassword;
    private Button buttonAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_login);

        // Find views
        editTextAdminUsername = findViewById(R.id.editTextAdminUsername);
        editTextAdminPassword = findViewById(R.id.editTextAdminPassword);
        buttonAdminLogin = findViewById(R.id.buttonAdminLogin);

        // Set click listener for the admin login button
        buttonAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAdminLogin();
            }
        });
    }

    // Validate admin login
    private void validateAdminLogin() {
        String enteredAdminUsername = editTextAdminUsername.getText().toString();
        String enteredAdminPassword = editTextAdminPassword.getText().toString();

        if (enteredAdminUsername.equals(ADMIN_USERNAME) && enteredAdminPassword.equals(ADMIN_PASSWORD)) {
            // Successful login, navigate to the admin dashboard or any other screen
            Intent intent = new Intent(AdmLoginActivity.this, AdminDActivity.class);
            startActivity(intent);
            finish();  // Optional: finish this login activity
        } else {
            // Invalid credentials
            Toast.makeText(this, "Invalid admin credentials. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
