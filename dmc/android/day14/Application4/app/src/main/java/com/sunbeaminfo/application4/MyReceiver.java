package com.sunbeaminfo.application4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("MyReceiver", "Message is received");

        String phone = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        if (phone != null) {
            if (phone.equals("+91123456789")) {
                Toast.makeText(context, "Got a phone from my friend", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Got a phone from my enemy", Toast.LENGTH_SHORT).show();
            }
        }

        Log.e("MyReceiver", "Got a call from: " + phone);

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage("+91234234", "+912334", "Hi! Happy Birthday", null, null);
    }
}
