package com.nikhil.roy;

// 🔽 প্রথমে import গুলো দিন:
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
ImageView selectedImageView;
    // 🔁 File Picker Launcher
    private ActivityResultLauncher<Intent> filePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // আপনার layout ফাইল
selectedImageView = findViewById(R.id.selectedImage);
        // 🔁 Launcher initialize
        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri fileUri = result.getData().getData();
                        Toast.makeText(this, "Selected: " + fileUri, Toast.LENGTH_LONG).show();
                        // 🔁 fileUri দিয়ে Firebase upload বা preview করা যাবে
                        selectedImageView.setImageURI(fileUri);
                    }
                }
        );

        // 🔘 Button press করলে Picker খুলবে
        Button pickBtn = findViewById(R.id.pickFileBtn); // XML layout এর বাটন
        pickBtn.setOnClickListener(v -> pickFile());
    }

    // 🔍 Picker function
    private void pickFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // সব ফাইল টাইপ
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        filePickerLauncher.launch(intent);
    }
}