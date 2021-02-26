package com.example.cloudmessagingexample;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class FirebaseService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        Log.d("TAG", "El token es: " + s);
        //sendRegistrationToServer(token);
    }
}
