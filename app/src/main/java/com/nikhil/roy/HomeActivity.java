package com.nikhil.roy;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.firebase.analytics.FirebaseAnalytics;


public class HomeActivity extends AppCompatActivity {
  
  private FirebaseAnalytics mFirebaseAnalytics;


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

// Firebase Analytics instance
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        
        Bundle bundle = new Bundle();
bundle.putString("clicked_button", "login");
mFirebaseAnalytics.logEvent("button_click", bundle);
        
        bottomNavigationView = findViewById(R.id.bottomnavigation1);
        
        
        

        // Default fragment
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        // Bottom Navigation Click Listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_chat) {
              startActivity(new Intent(this, MainActivity.class));
                //selectedFragment = new MenuFragment(); // Ensure MenuFragment exists
            } else if (id == R.id.nav_profile) {
                selectedFragment = new ProfileFragment(); // Ensure DataFragment exists
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }

            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit();
    }
}