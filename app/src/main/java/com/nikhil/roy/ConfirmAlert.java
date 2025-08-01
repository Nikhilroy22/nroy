package com.nikhil.roy; // ← তোমার প্যাকেজ নাম এখানে দাও

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmAlert {

    public interface ConfirmCallback {
        void onConfirm();
        void onCancel();
    }

    public static void showConfirm(Context context, String message, final ConfirmCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_confirm_dialog, null);

        TextView dialogMessage = view.findViewById(R.id.dialogMessage);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnConfirm = view.findViewById(R.id.btnConfirm);

        dialogMessage.setText(message);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false); // optional

        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
            if (callback != null) callback.onCancel();
        });

        btnConfirm.setOnClickListener(v -> {
            dialog.dismiss();
            if (callback != null) callback.onConfirm();
        });

        dialog.show();
    }
}