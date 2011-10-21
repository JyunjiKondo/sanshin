
package com.google.code.sanshin.sanshinclient.view;

import android.os.Bundle;
import android.util.Log;

import com.google.code.sanshin.sanshinclient.openaccessory.AccessoryListener;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.code.sanshin.sanshinclient.presenter.HardStringsHardPickUpPresenter;
import com.google.inject.Inject;

public class SanshinViewHardStringsHardPickUpImpl extends SanshinViewImpl implements
        AccessoryListener {
    private HardStringsHardPickUpPresenter mPresenter;

    @Inject
    OpenAccessory mOpenAccessory;

    public SanshinViewHardStringsHardPickUpImpl() {
        mPresenter = new HardStringsHardPickUpPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
        mOpenAccessory.addListener(this);
        Log.d("SanshinViewHardStringsHardPickUpImpl", "onCreate");
    }

    @Override
    protected void onDestroy() {
        mOpenAccessory.onDestroy();
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mOpenAccessory.onPause();
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
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

    public void onAccessoryMessage(byte[] data) {
        // TODO Auto-generated method stub

    }

}
