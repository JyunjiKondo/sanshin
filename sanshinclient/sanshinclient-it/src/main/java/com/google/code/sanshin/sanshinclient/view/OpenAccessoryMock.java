
package com.google.code.sanshin.sanshinclient.view;

import android.app.Application;
import android.util.Log;

import com.google.code.sanshin.sanshinclient.openaccessory.AccessoryListener;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class OpenAccessoryMock implements OpenAccessory {

    @Inject
    Application mApplication;

    public void addListener(AccessoryListener listener) {
        // TODO Auto-generated constructor stub
        Log.d("OpenAccessoryMock", "addListener");
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

}
