package com.nikhil.roy;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {
  
  
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        
        if (savedInstanceState == null) {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainer, new HomeFragment()) // your default fragment
            .commit();
            
    }
        
    }
  
}