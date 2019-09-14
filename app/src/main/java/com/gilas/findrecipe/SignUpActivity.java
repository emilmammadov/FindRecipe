package com.gilas.findrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gilas.findrecipe.Database.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername,etPassword;
    Button btnRegister;
    public static String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsername = findViewById(R.id.etSignUpUsername);
        etPassword = findViewById(R.id.etSignUpPassword);


        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);


    }

    public void writeNewUser(DatabaseReference ref, String username, String password){
        User user = new User(username, password);

        ref.child("Users").child(user.username).setValue(user);
    }

    @Override
    public void onClick(View view) {
        if (view == btnRegister) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            writeNewUser(myRef, etUsername.getText().toString(),etPassword.getText().toString());
        }
    }
}
