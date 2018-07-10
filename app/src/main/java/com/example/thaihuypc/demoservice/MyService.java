package com.example.thaihuypc.demoservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyService extends Service {

    public static final String TAG = "Service";
    private boolean isRunning;
    private IBinder mIBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    public String getCurrentTime() {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("HH:mm:ss MM/dd/yyy", Locale.ENGLISH);
        return (simpleDateFormat.format(new Date()));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        String name = null;
        if (intent != null) {
            name = intent.getStringExtra(MainActivity.ARGUMENTS);
        }
        Toast.makeText(this, "onStartCommand" + name, Toast.LENGTH_SHORT).show();
        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        Toast.makeText(this, "onBind", Toast.LENGTH_SHORT).show();
        return mIBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        Log.i(TAG, "onDestroy: ");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}
