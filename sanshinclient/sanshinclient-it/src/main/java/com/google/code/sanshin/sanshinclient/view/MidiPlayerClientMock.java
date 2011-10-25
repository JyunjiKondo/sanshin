
package com.google.code.sanshin.sanshinclient.view;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.code.sanshin.sanshinclient.midiplayer.MidiPlayerClient;

public class MidiPlayerClientMock implements MidiPlayerClient {
    private int mNoteNo = -1;
    private int mVelocity = -1;

    public void play(int noteNo, int velocity) {
        mNoteNo = noteNo;
        mVelocity = velocity;
        Log.d("MidiPlayerClientMock", mNoteNo + " " + mVelocity);
    }

    public void onDestroy() {
        // TODO Auto-generated method stub

    }

    public void onCreate(Activity activity) {
        // TODO Auto-generated method stub

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

    }

    public int getNoteNo() {
        return mNoteNo;
    }

    public int getVelocity() {
        return mVelocity;
    }
}
