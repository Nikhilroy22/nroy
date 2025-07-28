package com.nikhil.roy;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nikhil.roy.R;

public class Alert {

    public static void show(Activity activity, String title, String message) {
        View customView = LayoutInflater.from(activity).inflate(R.layout.alert, null);

        TextView dialogTitle = customView.findViewById(R.id.dialogTitle);
        TextView dialogMessage = customView.findViewById(R.id.dialogMessage);
      //  Button dialogButton = customView.findViewById(R.id.dialogButton);

        dialogTitle.setText(title);
        dialogMessage.setText(message);

        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setView(customView)
                .create();

       // dialogButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}