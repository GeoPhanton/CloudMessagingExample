package com.example.cloudmessagingexample.Service;

import android.util.Log;

import com.example.cloudmessagingexample.Common;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Common.currentToken = refreshedToken;
    }
}
