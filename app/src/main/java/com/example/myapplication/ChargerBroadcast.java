package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class ChargerBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean b=intent.getBooleanExtra(BatteryManager.ACTION_CHARGING,false);
        if (b){
            Toast.makeText(context,"Charger disconnect",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Charger connected Phone charging", Toast.LENGTH_SHORT).show();
        }
    }
}
