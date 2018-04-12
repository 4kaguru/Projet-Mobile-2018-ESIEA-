package com.lorenzo.abruti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by PC on 10/04/2018.
 */

public class Nuove extends BroadcastReceiver {
    Listener listener;

    Nuove(Listener listener){
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, R.string.download_complete, Toast.LENGTH_LONG).show();
        listener.onFinish();
    }
}
