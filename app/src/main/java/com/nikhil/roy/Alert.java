package com.nikhil.roy;

import android.content.Context;
import android.view.View;
import android.widget.*;
import android.app.Dialog;
import android.view.LayoutInflater;


public class Alert {
    
    
    public static Dialog customDialog;
    
    
     public static void show(Context context, String message) {
        customDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert, null);
       // TextView loadingText = view.findViewById(R.id.loading_text);
      //  loadingText.setText(message);

        customDialog.setContentView(view);
        customDialog.setCancelable(false);
        customDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        customDialog.show();
    }

    public static void hide() {
        if (customDialog != null && customDialog.isShowing()) {
            customDialog.dismiss();
        }
    }

    
    
    
    
    
}
