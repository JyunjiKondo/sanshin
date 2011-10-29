
package com.google.code.sanshin.sanshinclient.view;

import roboguice.RoboGuice;
import android.os.Bundle;
import android.util.Log;

import com.google.code.sanshin.sanshinclient.openaccessory.AccessoryListener;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.code.sanshin.sanshinclient.presenter.HardStringsHardPickUpPresenter;

public class SanshinViewHardStringsHardPickUpImpl extends SanshinViewImpl implements
        AccessoryListener {
    private static final String TAG = SanshinViewHardStringsHardPickUpImpl.class.getSimpleName();

    private HardStringsHardPickUpPresenter mPresenter;

    // @Inject
    // OpenAccessory mOpenAccessory;
    private OpenAccessory mOpenAccessory;

    public SanshinViewHardStringsHardPickUpImpl() {
        mPresenter = new HardStringsHardPickUpPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
        mOpenAccessory = RoboGuice.getInjector(getApplicationContext()).getInstance(
                OpenAccessory.class);
        mOpenAccessory.onCreate(getApplicationContext());
        mOpenAccessory.addListener(this);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onDestroy() {
        mOpenAccessory.onDestroy();
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        mOpenAccessory.onPause();
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        mOpenAccessory.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        mOpenAccessory.onStart();
    }

    @Override
    protected void onStop() {
        mOpenAccessory.onStop();
        mPresenter.onStop();
        super.onStop();
    }

    public void onAccessoryMessage(byte[] data, int length) {
        Log.d(TAG, "onAccessoryMessage:[" + length + "]" + data[0] + " " + data[1] + " " + data[2]);
        if (data[0] == OpenAccessory.STRING_PICKED) {
            mFingerPositionListener.onMaleStringFingerPositionChanged(data[1]);
            mPickUpListener.onMaleStringPicked(data[2]);
        }
    }

    public void onAccessoryDetached() {
        Log.d(TAG, "onAccessoryDetached");
        Intent intent = new Intent(this, EntryActivity.class);
        Log.d(TAG, "try to start EntryActivity");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "unable to start EntryActivity", e);
        }
        finish();
    }
}
