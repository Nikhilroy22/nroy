package com.nikhil.roy;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import android.content.Intent;
import android.util.Patterns;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button signupnav, loginbutton;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // EditText
        email = findViewById(R.id.EmailInput);
        password = findViewById(R.id.PasswordInput);

        // Button
        signupnav = findViewById(R.id.SignupLink);
        loginbutton = findViewById(R.id.LoginButton);

        // Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        // Signup Navigation
        signupnav.setOnClickListener(v -> {
            startActivity(new Intent(this, SignupActivity.class));
        });

        // Login Button
        loginbutton.setOnClickListener(v -> {
            String Semail = email.getText().toString().trim();
            String Spassword = password.getText().toString().trim();

            // Validation
            if (!Patterns.EMAIL_ADDRESS.matcher(Semail).matches()) {
                email.setError("সঠিক ইমেইল দিন");
                email.requestFocus();
                return;
            }
            if (Spassword.length() < 6) {
                password.setError("পাসওয়ার্ড ৬ অক্ষরের বেশি হওয়া উচিত");
                password.requestFocus();
                return;
            }

            // Login function call
            login(Semail, Spassword);
        });
    }

    // Login Function
    public void login(String email, String password) {
    firebaseAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "লগইন সফল", Toast.LENGTH_SHORT).show();
            } else {
                String error = task.getException().getMessage();

                if (error.contains("The supplied")) {
                  Alert.show(LoginActivity.this, "Details", "Invalid Email/Password");
                  
                  
                } else {
                    Toast.makeText(this, "লগইন ব্যর্থ: " + error, Toast.LENGTH_LONG).show();
                }
            }
        });
}

    // Auto login check
   /* @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }*/
}