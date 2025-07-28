package com.nikhil.roy;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import android.view.View;
import android.util.Patterns;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupActivity extends AppCompatActivity {

    EditText email, password;
    TextView emailerror, passworderror;
    Button signupbutton;

    FirebaseAuth firebaseAuth; // 🔸 FirebaseAuth instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        // EditText
        email = findViewById(R.id.EmailInput);
        password = findViewById(R.id.PasswordInput);

        // TextView
        emailerror = findViewById(R.id.EmailError);
        passworderror = findViewById(R.id.PasswordError);

        // Button
        signupbutton = findViewById(R.id.SignupButton);

        // 🔹 Firebase init
        firebaseAuth = FirebaseAuth.getInstance();

        // Signup Button Click
        signupbutton.setOnClickListener(v -> {
            String Semail = email.getText().toString().trim();
            String Spassword = password.getText().toString().trim();

            boolean haserror = false;

            // Email validation
            if (Semail.isEmpty()) {
                email.requestFocus();
                emailerror.setText("ইমেইল দিন");
                emailerror.setVisibility(View.VISIBLE);
                haserror = true;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(Semail).matches()) {
                email.requestFocus();
                emailerror.setText("সঠিক ইমেইল দিন");
                emailerror.setVisibility(View.VISIBLE);
                haserror = true;
            } else {
                emailerror.setVisibility(View.GONE);
            }

            // Password validation
            if (Spassword.isEmpty()) {
                password.requestFocus();
                passworderror.setText("পাসওয়ার্ড দিন");
                passworderror.setVisibility(View.VISIBLE);
                haserror = true;
            } else if (Spassword.length() < 6) {
                password.requestFocus();
                passworderror.setText("কমপক্ষে ৬ অক্ষরের পাসওয়ার্ড দিন");
                passworderror.setVisibility(View.VISIBLE);
                haserror = true;
            } else {
                passworderror.setVisibility(View.GONE);
            }

            // Success
            if (!haserror) {
                signup(Semail, Spassword);
            }
        });
    }

    // 🔹 Firebase Signup Logic
    public void signup(String eemail, String password) {
        firebaseAuth.createUserWithEmailAndPassword(eemail, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "সাইনআপ সফল হয়েছে", Toast.LENGTH_SHORT).show();
                    // Success - এখান থেকে অন্য Activity তে যেতে পারেন
                } else {
                    Exception e = task.getException();

                    if (e instanceof FirebaseAuthUserCollisionException) {
                        email.requestFocus();
                        emailerror.setText("এই ইমেইলটি ইতিমধ্যে ব্যবহার হয়েছে");
                        emailerror.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(this, "সাইনআপ ব্যর্থ: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
}