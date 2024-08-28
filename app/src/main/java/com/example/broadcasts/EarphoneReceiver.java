package com.example.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.widget.Toast;

public class EarphoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //bjeb data le bel intent le heye data mt3l2a bel action fa b2lba fe extra esmo state bjeble l state taba3 l earphone pluged 2aw la2
//        hay l state law rej3et 0 ya3ne howe mesh mashbok law 1 ya3ne sam3at mashboken
        //fa ana b2olo iza 1 sha8le song iza 0 tafele song
        PendingResult result=goAsync();
        int state= intent.getIntExtra("state",-1);
       if(state==1){
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               context.startForegroundService(new Intent(context,MyService.class));
           }
           Toast.makeText(context, "ear phone plugged on", Toast.LENGTH_SHORT).show();
               result.finish();


       } else if (state==0) {
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               context.stopService(new Intent(context,MyService.class));
           }
           Toast.makeText(context, "there phone plugged off", Toast.LENGTH_SHORT).show();
               result.finish();


       }else{
           Toast.makeText(context, "there are problem", Toast.LENGTH_SHORT).show();

       }
    }


}





