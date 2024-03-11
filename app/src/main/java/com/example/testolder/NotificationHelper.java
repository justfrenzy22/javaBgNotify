package com.example.testolder;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationHelper extends AppCompatActivity {
    public static Notification makeNotification(Context context, String name, int checkValue) {
        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder bld = new NotificationCompat.Builder(context, channelID);

        int notifyId = generateNotifyId();

        Uri soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.alarm);


                bld
                .setSmallIcon(R.drawable.notify_icon)
                .setContentTitle("🤯 Alert 🤯" + name)
                .setContentText("Number door : " + checkValue)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(soundUri);

        Intent intent = new Intent(context, NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data", "some value for the name");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE);
        bld.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes. CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes. USAGE_ALARM)
                    .build();

            NotificationChannel channel = manager.getNotificationChannel(channelID);

//            if (channel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                channel = new NotificationChannel(channelID, "Some text", importance);

                channel.enableLights( true );
                channel.setLightColor( Color.GREEN );
                channel.enableVibration( true );
                channel.setVibrationPattern(new long []{100, 200, 300, 400, 500, 400, 300, 200, 400});
                channel.setSound(soundUri, audioAttributes);

                manager.createNotificationChannel(channel);

//                channel.setLightColor(Color.GREEN);
//                channel.enableVibration(true);
//                manager.createNotificationChannel(channel);
//            }
        }

        manager.notify(notifyId, bld.build());


        return bld.build();
    }

    private static int generateNotifyId () {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        return Integer.parseInt(timestamp.hashCode() + "");
    }
}