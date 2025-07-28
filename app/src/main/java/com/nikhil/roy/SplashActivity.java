package com.nikhil.roy;

import android.widget.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import java.lang.Thread;



public class SplashActivity extends AppCompatActivity {
  
   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        
       
        
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
          
          startActivity(new Intent(this, SignupActivity.class));
            finish();
          
          
        }, 2500);
        }
        
        
        
   }
  