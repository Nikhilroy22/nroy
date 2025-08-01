package com.nikhil.roy;

import android.content.Context;
import android.view.View;
import android.widget.*;
import android.app.Dialog;
import android.view.LayoutInflater;


public class Alert {
  
    public interface BtnConfirm {
        void oonConfirm();
        
    }
    
    public static Dialog customDialog;
    
    
     public static void show(Context context, String message, BtnConfirm confirmbtn) {
        customDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert, null);
        Button okbutton = view.findViewById(R.id.okButton);
        TextView des = view.findViewById(R.id.dialogMessage);
        
        des.setText(message);
      okbutton.setOnClickListener(v -> {
        hide();
        if (confirmbtn != null) {confirmbtn.oonConfirm();}
      });

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
