
package com.google.code.sanshin.sanshinclient.openaccessory;

import android.content.Context;

import com.android.future.usb.UsbAccessory;

public interface OpenAccessory {
    public static final int STRING_PICKED = 5;

    public void onCreate(Context context);

    public void onDestroy();

    public void onPause();

    public void onResume();

    public void onStart();

    public void onStop();

    public void addListener(AccessoryListener listener);

    public UsbAccessory findAccessory();
}
