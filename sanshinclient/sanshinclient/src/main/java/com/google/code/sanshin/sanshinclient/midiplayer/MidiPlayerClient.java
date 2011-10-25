
package com.google.code.sanshin.sanshinclient.midiplayer;

import android.app.Activity;
import android.content.Intent;

public interface MidiPlayerClient {
    public void onCreate(Activity activity);

    public void onDestroy();

    public void play(int noteNo, int velocity);

    public void onActivityResult(int requestCode, int resultCode, Intent data);
}
