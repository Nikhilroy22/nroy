package com.nikhil.roy;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import android.view.View;
import android.util.Patterns;
import android.content.Intent;


public class LoginActivity extends AppCompatActivity {
  
  Button signupnav, loginbutton;
  
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        
        //Button
        signupnav = findViewById(R.id.SignupLink);
        loginbutton = findViewById(R.id.LoginButton);
        //Signup Navigation
        signupnav.setOnClickListener(v -> {
          startActivity(new Intent(this, SignupActivity.class));
            
          
        });
        //FIREBASE LOGIN Button
        loginbutton.setOnClickListener(v -> {
          login();
          
        });
        
    }
    //LOGIN FUNCTION
    public void login(){
      
      Toast.makeText(this, "সাইনআপ ব্যর্থ: " , Toast.LENGTH_LONG).show();
    }
}