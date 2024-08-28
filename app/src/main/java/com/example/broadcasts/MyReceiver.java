package com.example.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(intent.getAction().equals("com.example.broadcasts"))
            Log.d("khaledassidi","khaled");
        Toast.makeText(context, "trigger costume broadcast", Toast.LENGTH_SHORT).show();
    }
}