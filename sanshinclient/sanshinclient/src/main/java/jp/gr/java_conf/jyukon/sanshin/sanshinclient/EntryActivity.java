
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

public class EntryActivity extends Activity {
    static final String TARGET_PACKAGE = "com.google.code.sanshin.sanshinclient.view";
    static final String TARGET_CLASS = "com.google.code.sanshin.sanshinclient.view.SanshinViewHardStringsHardPickUpImpl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OpenAccessory openAccessory = RoboGuice.getInjector(getApplicationContext()).getInstance(
                OpenAccessory.class);
        openAccessory.onCreate(this);
        UsbAccessory accessory = openAccessory.findAccessory();
        openAccessory.onDestroy();
        Log.d("EntryActivity", "onCreate 2");

        // if
        // ("android.hardware.usb.action.USB_ACCESSORY_ATTACHED".equals(getIntent().getAction())
        // || accessory != null) {
        if (true) {
            Intent intent = new Intent(this, SanshinViewHardStringsHardPickUpImpl.class);
            Log.d("testclientprj", "UsbAccessoryActivity onCreate -> start TestclientprjActivity");
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.e("EntryActivity", "unable to start " + TARGET_CLASS + " activity", e);
            }
        } else {

        }
        finish();
    }

}
