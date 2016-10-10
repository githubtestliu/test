package com.demo.aidldemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
    private static final String TAG = "MainActivity";
    
    private EditText et_numOne, et_numTwo;
    private TextView tv_result;
    private Button btn;

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_numOne = (EditText) findViewById(R.id.et_numOne);
        et_numTwo = (EditText) findViewById(R.id.et_numTwo);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn = (Button) findViewById(R.id.btn);

        Intent intent = new Intent();
        intent.setClassName(this, "com.demo.aidlservice.MyService");
        intent.setPackage("com.demo.aidlservice");
        intent.setAction("com.demo.aidlservice.MyService");
        boolean bindService = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        Log.d(TAG, "onCreate() returned: " + bindService);
        Log.d(TAG, "onCreate() returned: " + "=======================");

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    double numOne = Double.parseDouble(et_numOne.getText().toString());
                    double numTwo = Double.parseDouble(et_numTwo.getText().toString());
                    double result = iMyAidlInterface.add(numOne, numTwo);
                    tv_result.setText(result + "");
                } catch (RemoteException e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) 
        {
            Log.d(TAG, "onServiceConnected() returned: " + "onServiceConnected");
            
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) 
        {

        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
