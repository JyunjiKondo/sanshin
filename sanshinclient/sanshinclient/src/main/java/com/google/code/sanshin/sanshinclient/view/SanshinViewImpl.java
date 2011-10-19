
package com.google.code.sanshin.sanshinclient.view;

import android.app.Activity;
import android.os.Bundle;

public abstract class SanshinViewImpl extends Activity implements SanshinView {
    private FingerPositionListener mFingerPositionListener = null;
    private PickUpListener mPickUpListener = null;

    public SanshinViewImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    public void addFingerPositionListener(FingerPositionListener listener) {
        mFingerPositionListener = listener;
    }

    public void removeFingerPositionListener(FingerPositionListener listener) {
        mFingerPositionListener = null;
    }

    public void addPickUpListener(PickUpListener listener) {
        mPickUpListener = listener;
    }

    public void removePickUpListener(PickUpListener listener) {
        mPickUpListener = null;
    }

    public void play(int noteNo, int velocity) {
        // TODO Auto-generated method stub

    }

}
