package com.example.alertsystemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private TextView Department,Inputerror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Department=findViewById(R.id.value);
        Inputerror=findViewById(R.id.error);

        String department= getIntent().getStringExtra("keyname");
        String inputerror=getIntent().getStringExtra("keyerror");

        Department.setText(department);
        Inputerror.setText(inputerror);






    }
}