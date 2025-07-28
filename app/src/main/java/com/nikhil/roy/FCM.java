package com.nikhil.roy;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.widget.*;


import android.os.Handler;
import android.os.Looper;
import androidx.core.app.NotificationCompat;



public class FCM extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Notification data পেলে তা দেখানো
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

            // আপনার notification দেখানোর কোড এখানে
        }
    }

    @Override
public void onNewToken(String token) {
    super.onNewToken(token);

    
    // Toast দিয়ে টোকেন দেখানো
    new Handler(Looper.getMainLooper()).post(() -> {
      Toast.makeText(getApplicationContext(), "Token: ", Toast.LENGTH_LONG).show();});

    // চাইলে সার্ভারে পাঠাও
   // sendTokenToServer(token);
}



}