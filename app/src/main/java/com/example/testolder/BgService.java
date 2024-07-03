package com.example.testolder;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;


public class BgService extends Service {
    private static  final int NOTIFICATION_ID = 123;
    private final Handler handler = new Handler();
    private Runnable runnable;



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification = NotificationHelper.makeNotification(getApplicationContext(), "stick", 1);

        startForeground(NOTIFICATION_ID, notification);

        int checkValue = intent.getIntExtra("checkValue", 0);
        // Start your periodic task here
        runnable = new Runnable() {
            @Override
            public void run() {
            }
        };
        handler.postDelayed(runnable, 2000);
                // Enqueue a OneTimeWorkRequest to execute the task in the background
                OneTimeWorkRequest fetchReq = new OneTimeWorkRequest.Builder(fetchWorker.class)
                        .build();
                WorkManager.getInstance(getApplicationContext()).enqueue(fetchReq);

                // Schedule the next execution after 2 seconds
                handler.postDelayed((Runnable) this, 2000);

        return START_STICKY;
    }



//    @Override
//    public void onDestroy () {
//        handler.removeCallbacks(runnable);
//        super.onDestroy();
//    }

    @Override
    public IBinder onBind (Intent intent) {
        return null;
    }
}


