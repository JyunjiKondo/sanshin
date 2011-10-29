
package com.google.code.sanshin.sanshinclient.view;

import jp.gr.java_conf.jyukon.sanshin.sanshinclient.EntryActivity;
import roboguice.RoboGuice;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.code.sanshin.sanshinclient.openaccessory.AccessoryListener;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.code.sanshin.sanshinclient.presenter.SoftStringsHardPickUpPresenter;

public class SanshinViewSoftStringsHardPickUpImpl extends SanshinViewImpl implements
        AccessoryListener {
    private static final String TAG = SanshinViewSoftStringsHardPickUpImpl.class.getSimpleName();

    private SoftStringsHardPickUpPresenter mPresenter;

    // @Inject
    // OpenAccessory mOpenAccessory;
    private OpenAccessory mOpenAccessory;

    public SanshinViewSoftStringsHardPickUpImpl() {
        mPresenter = new SoftStringsHardPickUpPresenter(this);
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
        Log.d(TAG, "onDestroy");
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
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        mOpenAccessory.onStop();
        mPresenter.onStop();
        super.onStop();
        Log.d(TAG, "onStop");
    }

    public void onAccessoryMessage(byte[] data, int length) {
        Log.d(TAG, "onAccessoryMessage:[" + length + "]" + data[0] + " " + data[1] + " " + data[2]);
        // if (data[0] == OpenAccessory.STRING_PICKED) {
        mFingerPositionListener.onMaleStringFingerPositionChanged(data[1]);
        mPickUpListener.onMaleStringPicked(data[2]);
        // }
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