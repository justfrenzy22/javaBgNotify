package com.example.testolder;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;


public class BgService extends Service {
    private final Handler handler = new Handler();
    private Runnable runnable;

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                String url = "http://192.168.0.101:8080/check";
//
//                new Thread(() -> {
//                    try {
//                        String response = http.get(url);
//
//                        JSONObject jsonObject = new JSONObject(response);
//
//                        int checkValue = jsonObject.getInt("check");
//                        if (checkValue == 5) {
//                            NotificationHelper.makeNotification(getApplicationContext(), checkValue);
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }).start();
//
//                handler.postDelayed(this, 2000);
//            }
//        };
//
//        handler.postDelayed(runnable, 2000);
//        return START_STICKY;
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PeriodicWorkRequest fetchReq = new PeriodicWorkRequest.Builder(fetchWorker.class, 2, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance(this).enqueue(fetchReq);

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
