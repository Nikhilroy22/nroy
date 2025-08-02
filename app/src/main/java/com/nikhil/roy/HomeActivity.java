package com.nikhil.roy;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private BottomNavigationView bottomNavigationView;
BadgeDrawable badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        // Firebase Analytics instance
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Example: Logging a button click event
        Bundle bundle = new Bundle();
        bundle.putString("clicked_button", "login");
        mFirebaseAnalytics.logEvent("button_click", bundle);

        // Initialize BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomnavigation1);

        // Show a badge on the Message (chat) icon
        try{
         badge = bottomNavigationView.getOrCreateBadge(R.id.nav_chat);
        badge.setVisible(true);
        badge.setNumber(3); // Use badge.clearNumber() if you want only a dot
        }catch(Exception e){
          SnackbarUtil.showCustomSnackbar(findViewById(android.R.id.content),
        e.getMessage(), R.drawable.ic_success);
          
        }

        // Load default fragment only when activity is first created
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        // Handle bottom navigation item clicks
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_chat) {
                // Open chat page as a new activity
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }

            return true;
        });
    }

    // Load fragments dynamically
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit();
    }
}