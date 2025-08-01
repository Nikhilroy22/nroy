package com.nikhil.roy;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import android.view.View;
import android.util.Patterns;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.*;

public class SignupActivity extends AppCompatActivity {

    EditText email, password, name;
    TextView emailerror, passworderror;
    Button signupbutton;
    ImageView backbtn;
   
    private FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth; // 🔸 FirebaseAuth instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        // EditText
        email = findViewById(R.id.EmailInput);
        password = findViewById(R.id.PasswordInput);
        name = findViewById(R.id.NameInput);
        //ImageView
        backbtn = findViewById(R.id.back);
        
      //FCM TOKEN
      FirebaseMessaging.getInstance().getToken()
    .addOnCompleteListener(task -> {
        if (!task.isSuccessful()) {
           
        }

        // ✅ Token পাওয়া গেছে
        String token = task.getResult();
        name.setText(token);
    });

        // TextView
        emailerror = findViewById(R.id.EmailError);
        passworderror = findViewById(R.id.PasswordError);

        // Button
        signupbutton = findViewById(R.id.SignupButton);

        // 🔹 Firebase init
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        // Back pressed dispatcher for new way
    getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
    @Override
    public void handleOnBackPressed() {
        finish(); // Activity বন্ধ
        overridePendingTransition(R.anim.bslide_in_left, R.anim.bslide_out_right); // অ্যানিমেশন
    }
});
//BackButton
backbtn.setOnClickListener(v ->{
  getOnBackPressedDispatcher().onBackPressed();
});


        // Signup Button Click
        signupbutton.setOnClickListener(v -> {
            String Semail = email.getText().toString().trim();
            String Spassword = password.getText().toString().trim();
            String Sname = name.getText().toString().trim();

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
              Loading.show(this, "wait....");
                signup(Semail, Spassword, Sname);
            }
        });
    }

    // 🔹 Firebase Signup Logic
    public void signup(String eemail, String password, String name) {
        firebaseAuth.createUserWithEmailAndPassword(eemail, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    String uid = firebaseAuth.getCurrentUser().getUid();
                    
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("name", name);
                    userMap.put("email", eemail);
                    
                    firestore.collection("Users").document(uid)
                        .set(userMap)
                        .addOnSuccessListener(aVoid ->{
                          SnackbarUtil.showCustomSnackbar(findViewById(android.R.id.content),
        "Signup Successfully", R.drawable.ic_success);
        Loading.hide(); 
                        }
                            )
                        .addOnFailureListener(e ->{
                        Loading.hide(); 
                            Toast.makeText(SignupActivity.this, "Firestore Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();});
                } else {
                    Exception e = task.getException();

                    if (e instanceof FirebaseAuthUserCollisionException) {
                        email.requestFocus();
                        emailerror.setText("এই ইমেইলটি ইতিমধ্যে ব্যবহার হয়েছে");
                        emailerror.setVisibility(View.VISIBLE);
                        Loading.hide(); 
                    } else {
                      Loading.hide(); 
                        Toast.makeText(this, "সাইনআপ ব্যর্থ: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
}