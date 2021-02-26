package com.example.cloudmessagingexample.Remote;

import com.example.cloudmessagingexample.Model.MyResponse;
import com.example.cloudmessagingexample.Model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAD_28CNA:APA91bGJYMs1tR1IbPJUG1atn_YB9sik9hum6zG-As1mlxEsg6k9kpkfItmbndvoBmisZpS6mb7T8AuGjBVDwP-hb1ztgCfdaqsxtiQXgnIzquvubcJicvHWz4ncZhkkmhswrzBuu59i"
    })
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
