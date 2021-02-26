package com.example.cloudmessagingexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cloudmessagingexample.Model.MyResponse;
import com.example.cloudmessagingexample.Model.Notification;
import com.example.cloudmessagingexample.Model.Sender;
import com.example.cloudmessagingexample.Remote.APIService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnSendMsg;
    Button btnSendMsgAll;
    EditText txtMsg;
    APIService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Common.currentToken = FirebaseInstanceId.getInstance().getToken();

        //Notificación a varios usuarios
        FirebaseMessaging.getInstance().subscribeToTopic("geophantom");

        mService = Common.getFCMClient();
        btnSendMsg = findViewById(R.id.btnSendMsg);
        btnSendMsgAll = findViewById(R.id.btnSendMsgAll);
        txtMsg = findViewById(R.id.txtMsg);

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Solo con token
                Notification notification = new Notification(txtMsg.getText().toString(), "Notificación Simple");
                Sender sender = new Sender(Common.currentToken, notification);
                mService.sendNotification(sender)
                .enqueue(new Callback<MyResponse>() {
                    @Override
                    public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                        if (response.body().success != 1) {
                            Toast.makeText(MainActivity.this, "Error al enviar el mensaje", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MyResponse> call, Throwable t) {
                        Log.e("ERROR", t.getMessage());
                    }
                });
            }
        });

        btnSendMsgAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para todos los que estén suscritos en un canal <geophantom>
                Notification notification = new Notification(txtMsg.getText().toString(), "Notificación de Geophantom");
                Sender sender = new Sender("/topics/geophantom", notification);
                mService.sendNotification(sender)
                        .enqueue(new Callback<MyResponse>() {
                            @Override
                            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {

                            }

                            @Override
                            public void onFailure(Call<MyResponse> call, Throwable t) {
                                Log.e("ERROR", t.getMessage());
                            }
                        });
            }
        });

        /*FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = "El token es: " + token;
                        Log.d("TAG", msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        FirebaseMessaging.getInstance().subscribeToTopic("geophantom")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Suscrito al mejor canal";
                        if (!task.isSuccessful()) {
                            msg = "Suscripción fallida";
                        }
                        Log.d("TAG", msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });*/
    }
}