package com.demo.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.demo.aidldemo.IMyAidlInterface;

/**
 * Created by Administrator on 2016/9/27 0027.
 */
public class MyService extends Service
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return iBinder;
    }

    private IMyAidlInterface.Stub iBinder = new IMyAidlInterface.Stub()
    {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public double add(double numOne, double numTwo) throws RemoteException {
            return numOne + numTwo;
        }
    };
}
