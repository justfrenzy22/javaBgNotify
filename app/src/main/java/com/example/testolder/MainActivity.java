package com.example.testolder;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public String checkValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {


                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }



        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationHelper.makeNotification(getApplicationContext(), 2);
            }
        });

        startService(new Intent(MainActivity.this, BgService.class));
    }



//    public void makeNotification (int checkValue) {
//
//        String channelID = "CHANNEL_ID_NOTIFICATION";
//        NotificationCompat.Builder bld = new NotificationCompat.Builder(getApplicationContext(), channelID);
//
//        bld
//                .setSmallIcon(R.drawable.notify_icon)
//                .setContentTitle("Notification Title")
//                .setContentText("Some text for notification here")
//                .setAutoCancel(true)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
//
//        intent
//                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                .putExtra("data", "some value for the name");
//
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
//
//        bld.setContentIntent(pendingIntent);
//
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationChannel channel = manager.getNotificationChannel(channelID);
//
//            if (channel == null) {
//                int importance = NotificationManager.IMPORTANCE_HIGH;
//                channel = new NotificationChannel(channelID, "Some text", importance);
//                channel.setLightColor(Color.GREEN);
//                channel.enableVibration(true);
//                manager.createNotificationChannel(channel);
//            }
//        }
//
//        manager.notify(0, bld.build());
//
//
//    }


}