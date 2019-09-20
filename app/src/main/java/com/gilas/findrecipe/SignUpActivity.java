package com.gilas.findrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gilas.findrecipe.Database.DatabaseOperations;
import com.gilas.findrecipe.Database.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername, etPassword;
    Button btnRegister;
    public static String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsername = findViewById(R.id.etSignUpUsername);
        etPassword = findViewById(R.id.etSignUpPassword);


        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == btnRegister) {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            new DatabaseOperations().register(getApplicationContext(), username, password);

        }
    }
}
