
package com.google.code.sanshin.sanshinclient.view;

import android.os.Bundle;

import com.google.code.sanshin.sanshinclient.presenter.HardStringsHardPickUpPresenter;

public class SanshinViewHardStringsHardPickUpImpl extends SanshinViewImpl {
    private HardStringsHardPickUpPresenter mPresenter;

    public SanshinViewHardStringsHardPickUpImpl() {
        mPresenter = new HardStringsHardPickUpPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

}
