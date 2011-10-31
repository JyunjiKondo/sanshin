
package com.google.code.sanshin.sanshinclient.openaccessory;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.Toast;

import com.android.future.usb.UsbAccessory;
import com.android.future.usb.UsbManager;

//@Singleton
public class OpenAccessoryImpl implements OpenAccessory, Runnable {
    private static final String TAG = OpenAccessoryImpl.class.getSimpleName();

    private static final String ACTION_USB_PERMISSION = "com.google.code.sanshin.sanshinclient.openaccessory.USB_PERMISSION";
    public static final String ACTION_USB_ACCESSORY_ATTACHED = "com.google.code.sanshin.sanshinclient.openaccessory.USB_ACESSORY_ATTACHED";

    // private UsbManager mUsbManager;
    private UsbAccessory mAccessory;
    private PendingIntent mPermissionIntent;
    private boolean mPermissionRequestPending;
    private Context mContext;
    private UsbManager mUsbManager;
    private ParcelFileDescriptor mFileDescriptor;
    private FileInputStream mInputStream;
    private FileOutputStream mOutputStream;
    boolean mForceTerminated = false;
    private AccessoryListener mListener = null;

    public OpenAccessoryImpl() {
    }

    public void onCreate(Context context) {
        mContext = context;
        mUsbManager = UsbManager.getInstance(mContext);
        mPermissionIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(
                ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        filter.addAction(OpenAccessoryImpl.ACTION_USB_ACCESSORY_ATTACHED);
        mContext.registerReceiver(mUsbReceiver, filter);
    }

    public void onDestroy() {
        if (mContext != null) {
            mContext.unregisterReceiver(mUsbReceiver);
            mContext = null;
        }
    }

    public void onPause() {
        closeAccessory();
    }

    public void onResume() {
        findAndOpenAccessory();
    }

    public void onStart() {
        // TODO Auto-generated method stub

    }

    public void onStop() {
        // TODO Auto-generated method stub

    }

    public void addListener(AccessoryListener listener) {
        mListener = listener;
    }

    public UsbAccessory findAccessory() {
        UsbAccessory[] accessories = mUsbManager.getAccessoryList();
        return (accessories == null ? null : accessories[0]);
    }

    private void openAccessory(UsbAccessory accessory) {
        mFileDescriptor = mUsbManager.openAccessory(accessory);
        if (mFileDescriptor != null) {
            mAccessory = accessory;
            FileDescriptor fd = mFileDescriptor.getFileDescriptor();
            mInputStream = new FileInputStream(fd);
            mOutputStream = new FileOutputStream(fd);
            new Thread(this).start();
        } else {
            Log.d(TAG, "openAccessory: mFileDescriptor == null");
        }
    }

    private void closeAccessory() {
        try {
            if (mFileDescriptor != null) {
                mForceTerminated = true;
                mOutputStream.write(new byte[] {
                        5, 6, 7
                }); // 5, 6, 7は、3バイトのダミーデータ。ADKはこれを受け取ると3バイトのデータを書き出す。
            }
        } catch (IOException e) {
            mForceTerminated = false;
            e.printStackTrace();
        }
        try {
            if (mFileDescriptor != null) {
                mFileDescriptor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mFileDescriptor = null;
            mAccessory = null;
        }
    }

    private void findAndOpenAccessory() {
        UsbAccessory accessory = findAccessory();
        if (accessory != null) {
            if (mUsbManager.hasPermission(accessory)) {
                openAccessory(accessory);
            } else {
                synchronized (mUsbReceiver) {
                    if (!mPermissionRequestPending) {
                        mUsbManager.requestPermission(accessory, mPermissionIntent);
                        mPermissionRequestPending = true;
                    }
                }
            }
        } else {

        }
    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbAccessory accessory = UsbManager.getAccessory(intent);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        openAccessory(accessory);
                    } else {
                    }
                    mPermissionRequestPending = false;
                }
            } else if (UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action)) {
                UsbAccessory accessory = UsbManager.getAccessory(intent);
                if (accessory != null && accessory.equals(mAccessory)) {
                    mListener.onAccessoryDetached();
                }
                Toast.makeText(context, "ACTION_USB_ACCESSORY_DETACHED", Toast.LENGTH_LONG).show();
            } else if (ACTION_USB_ACCESSORY_ATTACHED.equals(action)) {
                findAndOpenAccessory();
            } else {
            }
        }
    };

    public void run() {
        int length = 0;
        byte[] buffer = new byte[16384];

        while (mForceTerminated == false && length >= 0) {
            try {
                length = mInputStream.read(buffer);
            } catch (IOException e) {
                break;
            }
            mListener.onAccessoryMessage(buffer, length);
        }
        mForceTerminated = false;
    }

}
