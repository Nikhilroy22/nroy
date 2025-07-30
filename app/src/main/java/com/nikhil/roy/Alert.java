package com.nikhil.roy;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nikhil.roy.R;

public class Alert {

    public static void show(Activity activity) {
       new AlertDialog.Builder(activity)
        .setTitle("নোটিশ")
        .setMessage("আপনি কি সত্যিই বের হতে চান?")
        .setPositiveButton("হ্যাঁ", (dialog, which) -> {
            // Yes চাপলে যা হবে
            activity.finish();
        })
        .setNegativeButton("না", (dialog, which) -> {
            dialog.dismiss();
        })
        .show();
    }
}