
package com.google.code.sanshin.sanshinclient.model;

public class SanshinModelImpl {
    final private int MinNoteNo = 0;
    final private int MaxNoteNo = 127;
    final private int MinVelocity = 1;
    final private int MaxVelocity = 127;

    private SanshinModelListener mListener = null;
    private int mMaleStringNote;
    private int mMiddleStringNote;
    private int mFemaleStringNote;

    public void addListener(SanshinModelListener listener) {
        mListener = listener;
    }

    public boolean setMaleStringNote(int noteNo) {
        if (noteNo < MinNoteNo || noteNo > MaxNoteNo) {
            return false;
        }
        mMaleStringNote = noteNo;
        return true;
    }

    public boolean setMiddleStringNote(int noteNo) {
        if (noteNo < MinNoteNo || noteNo > MaxNoteNo) {
            return false;
        }
        mMiddleStringNote = noteNo;
        return true;
    }

    public boolean setFemaleStringNote(int noteNo) {
        if (noteNo < MinNoteNo || noteNo > MaxNoteNo) {
            return false;
        }
        mFemaleStringNote = noteNo;
        return true;
    }

    public boolean pickMaleString(int velocity) {
        if (velocity < MinVelocity || velocity > MaxVelocity) {
            return false;
        }
        if (mListener != null) {
            mListener.play(mMaleStringNote, velocity);
        }
        return true;
    }

    public boolean pickMiddleString(int velocity) {
        if (velocity < MinVelocity || velocity > MaxVelocity) {
            return false;
        }
        if (mListener != null) {
            mListener.play(mMiddleStringNote, velocity);
        }
        return true;
    }

    public boolean pickFemaleString(int velocity) {
        if (velocity < MinVelocity || velocity > MaxVelocity) {
            return false;
        }
        if (mListener != null) {
            mListener.play(mFemaleStringNote, velocity);
        }
        return true;
    }

}
