
package com.google.code.sanshin.sanshinclient.view;

import android.os.Bundle;
import android.util.Log;

import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.code.sanshin.sanshinclient.presenter.SoftStringsSoftPickUpPresenter;

public class SanshinViewSoftStringsSoftPickUpImpl extends SanshinViewImpl {
    private static final String TAG = SanshinViewSoftStringsSoftPickUpImpl.class.getSimpleName();

    private SoftStringsSoftPickUpPresenter mPresenter;

    // @Inject
    // OpenAccessory mOpenAccessory;
    private OpenAccessory mOpenAccessory;

    public SanshinViewSoftStringsSoftPickUpImpl() {
        mPresenter = new SoftStringsSoftPickUpPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
        Log.d(TAG, "onStop");
    }

}
