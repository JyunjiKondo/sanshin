
package jp.gr.java_conf.jyukon.sanshin.sanshinclient;

import roboguice.RoboGuice;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.future.usb.UsbAccessory;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.code.sanshin.sanshinclient.view.SanshinViewHardStringsHardPickUpImpl;
import com.google.code.sanshin.sanshinclient.view.SanshinViewSoftStringsSoftPickUpImpl;

public class EntryActivity extends Activity {
    private static final String TAG = EntryActivity.class.getSimpleName();

    static final String TARGET_PACKAGE = "com.google.code.sanshin.sanshinclient.view";
    static final String TARGET_CLASS = "com.google.code.sanshin.sanshinclient.view.SanshinViewHardStringsHardPickUpImpl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent;

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        OpenAccessory openAccessory = RoboGuice.getInjector(getApplicationContext()).getInstance(
                OpenAccessory.class);
        openAccessory.onCreate(this);
        UsbAccessory accessory = openAccessory.findAccessory();
        openAccessory.onDestroy();

        if ("android.hardware.usb.action.USB_ACCESSORY_ATTACHED".equals(getIntent().getAction())
                || accessory != null) {
            // if (true) {
            intent = new Intent(this, SanshinViewHardStringsHardPickUpImpl.class);
            Log.d(TAG, "onCreate: try to start SanshinViewHardStringsHardPickUpImpl");
        } else {
            intent = new Intent(this, SanshinViewSoftStringsSoftPickUpImpl.class);
            Log.d(TAG, "onCreate: try to start SanshinViewSoftStringsSoftPickUpImpl");
        }
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "unable to start " + TARGET_CLASS + " activity", e);
        }
        finish();
    }

}
