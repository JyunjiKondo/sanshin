
package com.google.code.sanshin.sanshinclient.view;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import android.content.Intent;
import android.os.Bundle;

import com.google.code.sanshin.sanshinclient.midiplayer.MidiPlayerClient;

public abstract class SanshinViewImpl extends RoboActivity implements SanshinView {
    protected FingerPositionListener mFingerPositionListener = null;
    protected PickUpListener mPickUpListener = null;

    // @Inject
    // MidiPlayerClient mMidiPlayerClient;
    private MidiPlayerClient mMidiPlayerClient;

    public SanshinViewImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mMidiPlayerClient = RoboGuice.getInjector(getApplicationContext()).getInstance(
                MidiPlayerClient.class);
        mMidiPlayerClient.onCreate(this);
    }

    @Override
    protected void onDestroy() {
        mMidiPlayerClient.onDestroy();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mMidiPlayerClient.onActivityResult(requestCode, resultCode, data);
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
        mMidiPlayerClient.play(noteNo, velocity);
    }

}
