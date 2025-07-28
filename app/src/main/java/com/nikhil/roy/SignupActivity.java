package com.nikhil.roy;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;




public class SignupActivity extends AppCompatActivity {
  
  EditText email, password;
  TextView emailerror, passworderror;
  Button signupbutton;
  
  
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
  //EditText
  email = findViewById(R.id.EmailInput);
  password = findViewById(R.id.PasswordInput);
  
  //TextView
  emailerror = findViewById(R.id.EmailError);
  passworderror = findViewById(R.id.PasswordError);
  
  //Button
  signupbutton = findViewById(R.id.SignupButton);
  
  //Signup Button Click
  signupbutton.setOnClickListener(v -> {
    
    Toast.makeText(this, "বার্তা লিখুন", Toast.LENGTH_LONG).show();
    
    
  });
  
  
  
  
    }
}