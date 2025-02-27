package com.example.broadcasts;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    MediaPlayer mediaPlayer;
    private static final String CHANEL_ID = "chanel_id";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this,R.raw.music);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopSelf();
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(1,displaynotification(), ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK);
        }else{
            startForeground(1,displaynotification());

        }
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }


        Log.d("Service Life Cycle","OnStart Service");
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Notification displaynotification() {
        Intent intent = new Intent(this, MyService.class);
        Intent intent1 = new Intent(this, MyService.class);

        PendingIntent pi = null;
        PendingIntent pi1 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int flags = PendingIntent.FLAG_IMMUTABLE;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                flags |= PendingIntent.FLAG_UPDATE_CURRENT;
            }
            pi = PendingIntent.getService(getApplicationContext(), 0, intent,flags);
            pi1 = PendingIntent.getService(getApplicationContext(), 0, intent1,flags);

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANEL_ID, "CHANNEL display name", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("my channel description");
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), CHANEL_ID);
        builder.setContentTitle("Marwan khoury")
                .setContentText("Ew3edene")
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(0, "resume", pi)
                .addAction(0, "stop", pi1);

        return builder.build();


    }
}