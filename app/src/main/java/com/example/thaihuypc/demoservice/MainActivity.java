package com.example.thaihuypc.demoservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ARGUMENTS = "Thai Huy";

    private Button mButtonStart, mButtonStop;
    private boolean isCheckBound;
    private MyService mMyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mButtonStart = findViewById(R.id.button_start_service);
        mButtonStart.setOnClickListener(this);
        mButtonStop = findViewById(R.id.button_stop);
        mButtonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra(ARGUMENTS, "HAHA");
        switch (v.getId()) {
            case R.id.button_start_service:
                //                startService(intent);
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.button_stop:
                //stopService(intent);
                if (isCheckBound) {
                    unbindService(mServiceConnection);
                    isCheckBound = false;
                }
        }
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder localBinder = (MyService.LocalBinder) service;
            mMyService = localBinder.getService();
            Toast.makeText(MainActivity.this, "onServiceConnected " + mMyService.getCurrentTime(),
                    Toast.LENGTH_SHORT).show();
            isCheckBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "onServiceDisconnected", Toast.LENGTH_SHORT).show();
            isCheckBound = false;
        }
    };
}
