package com.nikhil.roy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import com.google.firebase.firestore.FirebaseFirestore;
import java.util.*;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
  
  private FirebaseFirestore firestore;


    ImageView selectedImageView;
    private ActivityResultLauncher<Intent> filePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        firestore = FirebaseFirestore.getInstance();

        selectedImageView = findViewById(R.id.selectedImage);
        Button pickBtn = findViewById(R.id.pickFileBtn);

        initCloudinary();

        filePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri fileUri = result.getData().getData();
                    selectedImageView.setImageURI(fileUri);
                    String filePath = FileUtils.getPath(this, fileUri); // Helper to get real path
                    uploadImage(filePath);
                }
            }
        );

        pickBtn.setOnClickListener(v -> pickFile());
    }

    private void pickFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        filePickerLauncher.launch(intent);
    }

    private void initCloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dyibhriii");
        config.put("api_key", "416983738847882");
        config.put("api_secret", "uVNP0P8De-8kUFb46rUXpaW2GWw");

        MediaManager.init(this, config);
    }

    private void uploadImage(String filePath) {
        MediaManager.get().upload(filePath)
            .callback(new UploadCallback() {
                @Override
                public void onStart(String requestId) {
                    Toast.makeText(MainActivity.this, "Uploading started...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onProgress(String requestId, long bytes, long totalBytes) {}

                @Override
                public void onSuccess(String requestId, Map resultData) {
                    String url = resultData.get("secure_url").toString();
                    
                    //FirebaseFirestore
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("path", url);
                    
                    
                    firestore.collection("upload")
                        .add(userMap)
                        .addOnSuccessListener(aVoid ->
                            Toast.makeText(MainActivity.this, "Uploaded: " + url, Toast.LENGTH_LONG).show())
                        .addOnFailureListener(e ->
                            Toast.makeText(MainActivity.this, "Firestore Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                    
                    
                    
                }

                @Override
                public void onError(String requestId, ErrorInfo error) {
                    Toast.makeText(MainActivity.this, "Error: " + error.getDescription(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onReschedule(String requestId, ErrorInfo error) {}
            }).dispatch();
    }
}