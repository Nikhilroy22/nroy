package com.nikhil.roy;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.Snackbar.SnackbarLayout;

public class SnackbarUtil {

    public static void showCustomSnackbar(View parentView, String message, int iconResId) {
        Snackbar snackbar = Snackbar.make(parentView, "", Snackbar.LENGTH_INDEFINITE);

        SnackbarLayout snackbarLayout = (SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);

        LayoutInflater inflater = LayoutInflater.from(parentView.getContext());
        View customView = inflater.inflate(R.layout.snackbar, null);

        TextView textView = customView.findViewById(R.id.snackbar_text);
        ImageView iconView = customView.findViewById(R.id.snackbar_icon);
        ImageView cancelView = customView.findViewById(R.id.snackbar_cancel);

        textView.setText(message);
        iconView.setImageResource(iconResId);

        cancelView.setOnClickListener(v -> snackbar.dismiss());

        snackbarLayout.addView(customView, 0);

        // Snackbar Layout কে স্ক্রিনের center এ set করুন
       /* FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackbarLayout.getLayoutParams();
        params.gravity = Gravity.CENTER;
        snackbarLayout.setLayoutParams(params); */

        snackbar.show();
    }
}