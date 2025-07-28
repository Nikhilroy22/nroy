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
  
  email = findViewById(R.id.EmailInput);
  
    }
}