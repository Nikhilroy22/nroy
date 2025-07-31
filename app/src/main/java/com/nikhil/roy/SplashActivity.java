package com.nikhil.roy;

import android.widget.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.Thread;



public class SplashActivity extends AppCompatActivity {
  
   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        
        
       
        
      /*  new Handler(Looper.getMainLooper()).postDelayed(() -> {
          
          startActivity(new Intent(this, LoginActivity.class));
            finish();
          
          
        }, 2500);*/
        LottieAnimationView loader = findViewById(R.id.lottieLoader);

// Start animation
loader.playAnimation();
//Alert.show(this, "kala");
// Stop animation
//loader.cancelAnimation();
//loader.setVisibility(View.GONE);
        

    new Thread(() -> {
      boolean isWorking = Network.isInternetWorking();
        if (!isWorking) {
            // MB নাই, PING fail
            new Handler(Looper.getMainLooper()).post(() ->
                    Toast.makeText(this, "মোবাইল ডেটা শেষ হয়েছে ❌", Toast.LENGTH_LONG).show());
        } else {
            // MB আছে, সব ঠিক
            new Handler(Looper.getMainLooper()).post(() -> {
startActivity(new Intent(this, LoginActivity.class));
            finish();
            });
        }
    }).start();
}
        
   }
  