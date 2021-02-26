package com.example.cloudmessagingexample;

import com.example.cloudmessagingexample.Remote.APIService;
import com.example.cloudmessagingexample.Remote.RetrofitClient;

public class Common {
    public static String currentToken = "";
    private static String baseURL = "https://fcm.googleapis.com/";
    public static APIService getFCMClient() {
        return RetrofitClient.getClient(baseURL).create(APIService.class);
    }
}
