package com.nikhil.roy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.*;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.badge.BadgeDrawable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
  
  static boolean isFirstLoad = true; // ✅ প্রথমবার ট্র্যাক কর

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private GridView gridView;
    private RecyclerView recyclerView;
    private TextView setname, notification;

    private List<String> itemList;

    // GridView data
    private final String[] itemNames = {"Deposit", "Profile"};
    private final int[] itemIcons = {
        R.drawable.ic_profile,   // Change to correct icon if needed
        R.drawable.ic_profile
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        // Inflate layout
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Initialize views
        setname = view.findViewById(R.id.setname);
        notification = view.findViewById(R.id.notificationCount);
        gridView = view.findViewById(R.id.gridView);
        recyclerView = view.findViewById(R.id.recyclerView);

        // RecyclerView setup
        itemList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            itemList.add("Bangla Song " + i);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        MyAdapter rAdapter = new MyAdapter(itemList);
        recyclerView.setAdapter(rAdapter);

        // GridView setup
        GridAdapter adapter = new GridAdapter(requireContext(), itemNames, itemIcons);
        gridView.setAdapter(adapter);

        // Fetch name from Firestore
        String uid = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : null;
        if (uid != null) {
          if(isFirstLoad){
          Loading.show(requireContext(), "Please wait...");}
          FirestoreViewModel viewModel = new ViewModelProvider(requireActivity()).get(FirestoreViewModel.class);

viewModel.getUserNameLiveData().observe(getViewLifecycleOwner(), name -> {
    setname.setText(name != null ? name : "User");  // রিয়েলটাইমে UI আপডেট
    if (isFirstLoad) {
     //   Loading.hide(); // 👈 এটাও দরকার
        isFirstLoad = false;
    }
});

viewModel.startListening(uid);
          viewModel.getUpayCountLiveData().observe(getViewLifecycleOwner(), count -> {
            if (count != null && count > 0) {
    notification.setVisibility(View.VISIBLE);
    notification.setText(String.valueOf(count));
} else {
    notification.setVisibility(View.GONE); // লুকিয়ে ফেলো যদি null বা 0 হয়
}
          });
          
          
         /*   Loading.show(requireContext(), "Please wait...");
            firestore.collection("Users").document(uid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String name = documentSnapshot.getString("name");
                        setname.setText(name != null ? name : "User");
                    }
                    Loading.hide();
                })
                .addOnFailureListener(e -> {
                    Alert.show(requireContext(), "Error: " + e.getMessage(), () -> {
                      
                    });
                    Loading.hide();
                }); */
        }

        // Grid item click
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            String clickedItem = itemNames[position];
            Toast.makeText(requireContext(), "Clicked: " + clickedItem, Toast.LENGTH_SHORT).show();

            switch (clickedItem) {
                case "Deposit":
                    startActivity(new Intent(requireContext(), SignupActivity.class));
                    break;
              /* case "Profile":
                    startActivity(new Intent(requireContext(), ProfileActivity.class));
                    break; */
                // Add more cases if you add more items
            } 
        });

        return view;
    }
}