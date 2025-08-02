package com.nikhil.roy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;

public class FirestoreViewModel extends ViewModel {

    private final MutableLiveData<String> userNameLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> upayCountLiveData = new MutableLiveData<>();

    private ListenerRegistration userListener;
    private ListenerRegistration upayListener;

    public LiveData<String> getUserNameLiveData() {
        return userNameLiveData;
    }

    public LiveData<Integer> getUpayCountLiveData() {
        return upayCountLiveData;
    }

    public void startListening(String uid) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Listen to user name
        userListener = db.collection("Users")
                .document(uid)
                .addSnapshotListener((snapshot, e) -> {
                  Loading.hide();
                    if (e != null || snapshot == null || !snapshot.exists()) return;

                    String name = snapshot.getString("name");
                    userNameLiveData.setValue(name);
                });

        // Listen to Upay deposit count
        upayListener = db.collection("Users")
                .addSnapshotListener((queryDocumentSnapshots, error) -> {
                    if (error == null && queryDocumentSnapshots != null) {
                        int unreadCount = queryDocumentSnapshots.size();
                        upayCountLiveData.setValue(unreadCount);
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (userListener != null) {
            userListener.remove();
        }
        if (upayListener != null) {
            upayListener.remove();
        }
    }
}