package com.example.expensetracker_adityatrivedi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactUsActivity extends AppCompatActivity {

    Button mail,call,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        mail=findViewById(R.id.mail);
        call=findViewById(R.id.call);
        back=findViewById(R.id.back);

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid="support@expensease.com";
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+emailid)));
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = "8169219561";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(phoneNumber))));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactUsActivity.this, EmpDActivity.class));
            }
        });
    }
}