package com.nikhil.roy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {

    FirebaseAuth mAuth;
    
    private FirebaseFirestore firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      
      // 🔹 Firebase init
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
      
        // Inflate layout
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        TextView setname = view.findViewById(R.id.setname);
        
        
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
       Loading.hide(); 
    });
        
        

       

        return view;
    }
}