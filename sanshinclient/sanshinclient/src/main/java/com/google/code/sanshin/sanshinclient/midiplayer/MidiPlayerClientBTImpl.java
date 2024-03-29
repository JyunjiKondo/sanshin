
package com.google.code.sanshin.sanshinclient.midiplayer;

import net.clc.bt.Connection;
import net.clc.bt.Connection.OnConnectionLostListener;
import net.clc.bt.Connection.OnConnectionServiceReadyListener;
import net.clc.bt.Connection.OnMessageReceivedListener;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.inject.Singleton;

@Singleton
public class MidiPlayerClientBTImpl implements MidiPlayerClient {
    private static final String TAG = MidiPlayerClientBTImpl.class.getSimpleName();

    private static final int SERVER_LIST_RESULT_CODE = 42;

    private net.clc.bt.Connection mConnection;
    private String mServerDevice = "";
    private Activity mActivity;

    private OnMessageReceivedListener dataReceivedListener = new OnMessageReceivedListener() {
        public void OnMessageReceived(String device, String message) {
        }
    };

    private OnConnectionLostListener disconnectedListener = new OnConnectionLostListener() {
        public void OnConnectionLost(String device) {
        }
    };

    private OnConnectionServiceReadyListener serviceReadyListener = new OnConnectionServiceReadyListener() {
        public void OnConnectionServiceReady() {
            Intent serverListIntent = new Intent(mActivity, ServerListActivity.class);
            mActivity.startActivityForResult(serverListIntent, SERVER_LIST_RESULT_CODE);
        }
    };

    public MidiPlayerClientBTImpl() {
    }

    public void onCreate(Activity activity) {
        mActivity = activity;
        if (mConnection == null) {
            mConnection = new Connection(mActivity.getApplicationContext(), serviceReadyListener);
        }
    }

    public void onDestroy() {
        // if (mConnection != null) {
        // Log.d(TAG, "Connection.shutdown()");
        // mConnection.shutdown();
        // }
    }

    public void play(int noteNo, int velocity) {
        mConnection.sendMessage(mServerDevice, noteNo + " " + velocity);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((resultCode == Activity.RESULT_OK) && (requestCode == SERVER_LIST_RESULT_CODE)) {
            String device = data.getStringExtra(ServerListActivity.EXTRA_SELECTED_ADDRESS);
            int connectionStatus = mConnection.connect(device, dataReceivedListener,
                    disconnectedListener);
            if (connectionStatus != Connection.SUCCESS) {
                Toast.makeText(mActivity, "Unable to connect; please try again.", Toast.LENGTH_LONG)
                        .show();
            } else {
                mServerDevice = device;
            }
            return;
        }
    }
}
