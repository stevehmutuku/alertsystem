package com.example.alertsystemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class profileAc extends AppCompatActivity {
    private Button Logout;
    private Button SUBMIT;
    private EditText DEPARTMENT;
    private EditText InputError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SUBMIT=(Button) findViewById(R.id.submit);
        DEPARTMENT=(EditText) findViewById(R.id.department);
        InputError=(EditText) findViewById(R.id.inputerror);


        SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(profileAc.this,Login.class);
                 String department=DEPARTMENT.getText().toString();
                 String inputerror=InputError.getText().toString();

                 intent.putExtra("keyname",department);
                 intent.putExtra("keyerror",inputerror);
                 startActivity(intent);

            }
        });

         Logout= (Button) findViewById(R.id.Signout);

         Logout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 FirebaseAuth.getInstance().signOut();
                 startActivity(new Intent(profileAc.this ,MainActivity.class));
             }
         });
    }
}