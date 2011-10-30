
package jp.gr.java_conf.jyukon.sanshin.sanshinclient;

import jp.gr.java_conf.jyukon.sanshin.sanshinclient.config.GoogleAnalyticsConfig;
import roboguice.RoboGuice;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.future.usb.UsbAccessory;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.code.sanshin.sanshinclient.view.SanshinViewHardStringsHardPickUpImpl;
import com.google.code.sanshin.sanshinclient.view.SanshinViewSoftStringsHardPickUpImpl;
import com.google.code.sanshin.sanshinclient.view.SanshinViewSoftStringsSoftPickUpImpl;

public class EntryActivity extends Activity {
    private static final String TAG = EntryActivity.class.getSimpleName();

    private GoogleAnalyticsTracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent;
        String accessoryModel = null;

        super.onCreate(savedInstanceState);

        OpenAccessory openAccessory = RoboGuice.getInjector(getApplicationContext()).getInstance(
                OpenAccessory.class);
        openAccessory.onCreate(this);
        UsbAccessory accessory = openAccessory.findAccessory();
        if (accessory != null) {
            accessoryModel = accessory.getModel();
        }
        openAccessory.onDestroy();

        if ("Sanshin Custom".equals(accessoryModel)) {
            intent = new Intent(this, SanshinViewHardStringsHardPickUpImpl.class);
            Log.d(TAG, "onCreate: try to start SanshinViewHardStringsHardPickUpImpl");
        } else if ("DemoKit".equals(accessoryModel)) {
            intent = new Intent(this, SanshinViewSoftStringsHardPickUpImpl.class);
            Log.d(TAG, "onCreate: try to start SanshinViewSoftStringsHardPickUpImpl");
        } else {
            // Accessory is not attached or unknown accessory.
            intent = new Intent(this, SanshinViewSoftStringsSoftPickUpImpl.class);
            Log.d(TAG, "onCreate: try to start SanshinViewSoftStringsSoftPickUpImpl");
        }
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "unable to start activity", e);
        }

        GoogleAnalyticsConfig config = RoboGuice.getInjector(getApplicationContext()).getInstance(
                GoogleAnalyticsConfig.class);
        String webPropertyId = config.getWebPropertyId();
        Log.d(TAG, "web property ID " + webPropertyId);
        if (webPropertyId != null) {
            mTracker = GoogleAnalyticsTracker.getInstance();
            mTracker.startNewSession(config.getWebPropertyId(), this);
            mTracker.trackPageView("/sanshinclient/entry");
        }

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTracker != null) {
            mTracker.stopSession();
            mTracker = null;
        }
    }

}
