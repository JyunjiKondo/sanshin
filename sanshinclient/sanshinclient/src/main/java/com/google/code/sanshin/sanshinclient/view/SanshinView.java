
package com.google.code.sanshin.sanshinclient.view;

public interface SanshinView {
    public void addFingerPositionListener(FingerPositionListener listener);

    public void removeFingerPositionListener(FingerPositionListener listener);

    public void addPickUpListener(PickUpListener listener);

    public void removePickUpListener(PickUpListener listener);

    public void play(int noteNo, int velocity);
}
