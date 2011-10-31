
package com.google.code.sanshin.sanshinclient.presenter;

import com.google.code.sanshin.sanshinclient.model.SanshinModelImpl;
import com.google.code.sanshin.sanshinclient.model.SanshinModelListener;
import com.google.code.sanshin.sanshinclient.view.FingerPositionListener;
import com.google.code.sanshin.sanshinclient.view.PickUpListener;
import com.google.code.sanshin.sanshinclient.view.SanshinView;

public class SoftStringsSoftPickUpPresenter implements SanshinModelListener,
        FingerPositionListener, PickUpListener {

    private SanshinView mView;
    private SanshinModelImpl mModel;

    public SoftStringsSoftPickUpPresenter(SanshinView view) {
        mView = view;
    }

    public void onCreate() {
        mModel = new SanshinModelImpl();
        mModel.addListener(this);

        mView.addFingerPositionListener(this);
        mView.addPickUpListener(this);
    }

    public void onDestroy() {
        // TODO Auto-generated method stub

    }

    public void onPause() {
        // TODO Auto-generated method stub

    }

    public void onResume() {
        // TODO Auto-generated method stub

    }

    public void onStart() {
        // TODO Auto-generated method stub

    }

    public void onStop() {
        // TODO Auto-generated method stub

    }

    public void onMaleStringPicked(int velocity) {
        mModel.pickMaleString(velocity);
    }

    public void onMiddleStringPicked(int velocity) {
        mModel.pickMiddleString(velocity);
    }

    public void onFemaleStringPicked(int velocity) {
        mModel.pickFemaleString(velocity);
    }

    public void onMaleStringFingerPositionChanged(int noteNo) {
        mModel.setMaleStringNote(noteNo);
    }

    public void onMiddleStringFingerPositionChanged(int noteNo) {
        mModel.setMiddleStringNote(noteNo);
    }

    public void onFemaleStringFingerPositionChanged(int noteNo) {
        mModel.setFemaleStringNote(noteNo);
    }

    public void play(int noteNo, int velocity) {
        mView.play(noteNo, velocity);
    }

}
