package com.example.alertsystemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Admin extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    private EditText editTextEMAIL,editTextPassword;

    private Button signin;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mAuth= FirebaseAuth.getInstance();

        signin=(Button) findViewById(R.id.signin);
        signin.setOnClickListener(this);

        editTextEMAIL=(EditText) findViewById(R.id.EMAIL);
        editTextPassword=(EditText) findViewById(R.id.password);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin:
                userLogin();
                break;
        }

    }

    private void userLogin() {
        String EMAIL=editTextEMAIL.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if (EMAIL.isEmpty()){
            editTextEMAIL.setError("User ID is required!");
            editTextEMAIL.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            editTextPassword.setError("Min password character should be more than 6!");
            editTextPassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(EMAIL).matches()) {
            editTextEMAIL.setError("please provide valid email");
            editTextEMAIL.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(EMAIL,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference("Admins");

                    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()){

                        //redirect to user profile
                        startActivity(new Intent(Admin.this,Login.class));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(Admin.this,"Check your email ton verify your account!",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(Admin.this,"Failed to Login please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}