package com.nikhil.roy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        // Initialize logout button
        logout = view.findViewById(R.id.logout);

        // Handle logout click
        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut(); // Firebase logout
            Toast.makeText(requireContext(), "Logout সফল হয়েছে", Toast.LENGTH_SHORT).show();

            // Go back to LoginActivity
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return view;
    }
}