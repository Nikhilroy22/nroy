package com.nikhil.roy; // নিজের প্যাকেজ অনুযায়ী আপডেট করুন

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.EventListener;

public class FirestoreViewModel extends ViewModel {

    private final MutableLiveData<String> userNameLiveData = new MutableLiveData<>();
    private ListenerRegistration registration;

    public LiveData<String> getUserNameLiveData() {
        return userNameLiveData;
    }

    public void startListening(String udd) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        registration = db.collection("Users")
                .document(udd)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(DocumentSnapshot snapshot, FirebaseFirestoreException e) {
                      Loading.hide();
                        if (e != null || snapshot == null || !snapshot.exists()) return;

                        String name = snapshot.getString("name");
                        userNameLiveData.setValue(name);
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (registration != null) {
            registration.remove(); // memory leak রোধ করতে
        }
    }
}