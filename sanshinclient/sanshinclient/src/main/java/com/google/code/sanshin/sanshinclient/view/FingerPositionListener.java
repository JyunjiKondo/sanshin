
package com.google.code.sanshin.sanshinclient.view;

public interface FingerPositionListener {
    public void onMaleStringFingerPositionChanged(int noteNo);

    public void onMiddleStringFingerPositionChanged(int noteNo);

    public void onFemaleStringFingerPositionChanged(int noteNo);
}
