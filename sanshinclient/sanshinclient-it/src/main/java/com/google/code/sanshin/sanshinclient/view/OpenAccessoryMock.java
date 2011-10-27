
package com.google.code.sanshin.sanshinclient.view;

import android.content.Context;
import android.util.Log;

import com.android.future.usb.UsbAccessory;
import com.google.code.sanshin.sanshinclient.openaccessory.AccessoryListener;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.inject.Singleton;

@Singleton
public class OpenAccessoryMock implements OpenAccessory {
    private AccessoryListener mListener;

    public void onCreate(Context context) {

    }

    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d("OpenAccessoryMock", "onDestroy");
    }

    public void onPause() {
        // TODO Auto-generated method stub
        Log.d("OpenAccessoryMock", "onPause");
    }

    public void onResume() {
        // TODO Auto-generated method stub
        Log.d("OpenAccessoryMock", "onResume");
    }

    public void onStart() {
        // TODO Auto-generated method stub
        Log.d("OpenAccessoryMock", "onStart");
    }

    public void onStop() {
        // TODO Auto-generated method stub
        Log.d("OpenAccessoryMock", "onStop");
    }

    public void addListener(AccessoryListener listener) {
        mListener = listener;
        Log.d("OpenAccessoryMock", "addListener");
    }

    public UsbAccessory findAccessory() {
        // TODO Auto-generated method stub
        return null;
    }

    public void sendMessage(byte[] data, int length) {
        mListener.onAccessoryMessage(data, length);
    }
}
