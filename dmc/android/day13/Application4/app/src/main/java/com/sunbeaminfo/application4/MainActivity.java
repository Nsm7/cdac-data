package com.sunbeaminfo.application4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onSend(View v) {
        NotificationChannel channel = new NotificationChannel("1", "MyChannel", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("this is a test channel");

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("file downloaded");
        builder.setContentText("The file got downloaded on /sdcard. Please open the file manager to see the details....");
        builder.setSmallIcon(R.drawable.ic_my_logo);
        builder.setChannelId("1");

        Intent intent = new Intent(this, MyNotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(1, notification);
    }
}
