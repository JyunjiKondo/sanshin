
package com.google.code.sanshin.sanshinclient.openaccessory;

public interface OpenAccessory {
    public static final int STRING_PICKED = 5;

    public void addListener(AccessoryListener listener);

    public void onDestroy();

    public void onPause();

    public void onResume();

    public void onStart();

    public void onStop();
}
