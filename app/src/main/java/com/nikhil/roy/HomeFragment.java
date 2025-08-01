package com.nikhil.roy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.Toast;

import android.widget.AdapterView;
import android.widget.GridView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {

    FirebaseAuth mAuth;
    GridView gridView;
    RecyclerView recyclerView;
    List<String> itemList;

    
    String[] itemNames = {"Deposit", "Profile"};
    int[] itemIcons = {
        R.drawable.ic_profile,
        R.drawable.ic_profile,
        
    };

    
    private FirebaseFirestore firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      
      // 🔹 Firebase init
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
      
        // Inflate layout
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        TextView setname = view.findViewById(R.id.setname);
        gridView = view.findViewById(R.id.gridView);
        recyclerView = view.findViewById(R.id.recyclerView);
        // Sample Data
        itemList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            itemList.add("Bangla Song " + i);
        }

        // Set LayoutManager and Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        MyAdapter Radapter = new MyAdapter(itemList);
        recyclerView.setAdapter(Radapter);
        
        //GridView
        GridAdapter adapter = new GridAdapter(requireContext(), itemNames, itemIcons);
        gridView.setAdapter(adapter);
        
        String uid = mAuth.getCurrentUser().getUid();
        //Firestore Show Data
        Loading.show(requireContext(), "plz....");
        firestore.collection("Users").document(uid).get()
    .addOnSuccessListener(documentSnapshot -> {
        if (documentSnapshot.exists()) {
            String name = documentSnapshot.getString("name");
            setname.setText(name);
            
Loading.hide();
         
        } else {
            
        }
    })
    .addOnFailureListener(e -> {
      Alert.show(requireContext(), e.getMessage());
       Loading.hide(); 
    });
        
     gridView.setOnItemClickListener((parent, vieww, position, id) -> {
    String clickedItem = itemNames[position];

    Toast.makeText(requireContext(), "Clicked: " + clickedItem, Toast.LENGTH_SHORT).show();

  /*  switch (clickedItem) {
        case "Deposit":
            startActivity(new Intent(MainActivity.this, DepositActivity.class));
            break;
        case "Withdraw":
            startActivity(new Intent(MainActivity.this, WithdrawActivity.class));
            break;
        case "History":
            startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            break;
        case "Profile":
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            break; 
    } */
});   

       

        return view;
    }
}