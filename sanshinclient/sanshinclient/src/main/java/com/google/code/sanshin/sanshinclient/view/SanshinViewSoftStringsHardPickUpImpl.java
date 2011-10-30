
package com.google.code.sanshin.sanshinclient.view;

import jp.gr.java_conf.jyukon.sanshin.sanshinclient.EntryActivity;
import jp.gr.java_conf.jyukon.sanshin.sanshinclient.R;
import roboguice.RoboGuice;
import roboguice.inject.InjectView;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.google.code.sanshin.sanshinclient.openaccessory.AccessoryListener;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.code.sanshin.sanshinclient.presenter.SoftStringsHardPickUpPresenter;

public class SanshinViewSoftStringsHardPickUpImpl extends SanshinViewImpl implements
        AccessoryListener {
    private static final String TAG = SanshinViewSoftStringsHardPickUpImpl.class.getSimpleName();
    private static final int VELOCITY = 64;
    private static final int MALE_OPEN_NOTE = 60; // C4
    private static final int MALE_FRET1_NOTE = 62; // D4
    private static final int MALE_FRET2_NOTE = 64; // E4
    private static final int MIDDLE_OPEN_NOTE = 65; // F4
    private static final int MIDDLE_FRET1_NOTE = 67; // G4
    private static final int MIDDLE_FRET2_NOTE = 69; // A5
    private static final int MIDDLE_FRET3_NOTE = 71; // B5
    private static final int FEMALE_OPEN_NOTE = 72; // C5
    private static final int FEMALE_FRET1_NOTE = 74; // D5
    private static final int FEMALE_FRET2_NOTE = 76; // E5
    private static final int FEMALE_FRET3_NOTE = 77; // F5
    private static final int FEMALE_FRET4_NOTE = 79; // G5

    @InjectView(R.id.imageButtonMaleOpen)
    ImageButton imageButtonMaleOpen;
    @InjectView(R.id.imageButtonMaleFret1)
    ImageButton imageButtonMaleFret1;
    @InjectView(R.id.imageButtonMaleFret2)
    ImageButton imageButtonMaleFret2;
    @InjectView(R.id.imageButtonMaleFret3)
    ImageButton imageButtonMaleFret3;
    @InjectView(R.id.imageButtonMaleFret4)
    ImageButton imageButtonMaleFret4;

    @InjectView(R.id.imageButtonMiddleOpen)
    ImageButton imageButtonMiddleOpen;
    @InjectView(R.id.imageButtonMiddleFret1)
    ImageButton imageButtonMiddleFret1;
    @InjectView(R.id.imageButtonMiddleFret2)
    ImageButton imageButtonMiddleFret2;
    @InjectView(R.id.imageButtonMiddleFret3)
    ImageButton imageButtonMiddleFret3;
    @InjectView(R.id.imageButtonMiddleFret4)
    ImageButton imageButtonMiddleFret4;

    @InjectView(R.id.imageButtonFemaleOpen)
    ImageButton imageButtonFemaleOpen;
    @InjectView(R.id.imageButtonFemaleFret1)
    ImageButton imageButtonFemaleFret1;
    @InjectView(R.id.imageButtonFemaleFret2)
    ImageButton imageButtonFemaleFret2;
    @InjectView(R.id.imageButtonFemaleFret3)
    ImageButton imageButtonFemaleFret3;
    @InjectView(R.id.imageButtonFemaleFret4)
    ImageButton imageButtonFemaleFret4;

    private SoftStringsHardPickUpPresenter mPresenter;

    // @Inject
    // OpenAccessory mOpenAccessory;

    private OpenAccessory mOpenAccessory;

    public SanshinViewSoftStringsHardPickUpImpl() {
        mPresenter = new SoftStringsHardPickUpPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
        mOpenAccessory = RoboGuice.getInjector(getApplicationContext()).getInstance(
                OpenAccessory.class);
        mOpenAccessory.onCreate(getApplicationContext());
        mOpenAccessory.addListener(this);
        setContentView(R.layout.soft_strings);

        makeUnnecessaryImageButtonsInvisible();
        setImageButtonListeners();
        setInitialFingerPosition();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onDestroy() {
        mOpenAccessory.onDestroy();
        mPresenter.onDestroy();
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        mOpenAccessory.onPause();
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        mOpenAccessory.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        mOpenAccessory.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        mOpenAccessory.onStop();
        mPresenter.onStop();
        super.onStop();
        Log.d(TAG, "onStop");
    }

    // Messages from DemoKit:
    // 1 0 1 ... S1 pressed
    // 1 1 1 ... S2 pressed
    // 1 2 1 ... S3 pressed
    public void onAccessoryMessage(byte[] data, int length) {
        Log.d(TAG, "onAccessoryMessage:[" + length + "]" + data[0] + " " + data[1] + " " + data[2]);
        if (data[0] == 1) {
            switch (data[1]) {
                case 0:
                    mPickUpListener.onMaleStringPicked(VELOCITY);
                    break;
                case 1:
                    mPickUpListener.onMiddleStringPicked(VELOCITY);
                    break;
                case 2:
                    mPickUpListener.onFemaleStringPicked(VELOCITY);
                    break;
            }
        }
    }

    public void onAccessoryDetached() {
        Log.d(TAG, "onAccessoryDetached");
        Intent intent = new Intent(this, EntryActivity.class);
        Log.d(TAG, "try to start EntryActivity");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "unable to start EntryActivity", e);
        }
        finish();
    }

    private void makeUnnecessaryImageButtonsInvisible() {
        imageButtonMaleOpen.setVisibility(View.INVISIBLE);
        imageButtonMiddleOpen.setVisibility(View.INVISIBLE);
        imageButtonFemaleOpen.setVisibility(View.INVISIBLE);

        imageButtonMaleFret3.setVisibility(View.INVISIBLE);
        imageButtonMaleFret4.setVisibility(View.INVISIBLE);

        imageButtonMiddleFret4.setVisibility(View.INVISIBLE);
    }

    private void setImageButtonListeners() {

        imageButtonMaleFret1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMaleStringFingerPositionChanged(MALE_FRET1_NOTE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mFingerPositionListener.onMaleStringFingerPositionChanged(MALE_OPEN_NOTE);
                }
                return false;
            }
        });

        imageButtonMaleFret2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMaleStringFingerPositionChanged(MALE_FRET2_NOTE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mFingerPositionListener.onMaleStringFingerPositionChanged(MALE_OPEN_NOTE);
                }
                return false;
            }
        });

        imageButtonMiddleFret1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_FRET1_NOTE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_OPEN_NOTE);
                }
                return false;
            }
        });

        imageButtonMiddleFret2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_FRET2_NOTE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_OPEN_NOTE);
                }
                return false;
            }
        });

        imageButtonMiddleFret3.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_FRET3_NOTE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_OPEN_NOTE);
                }
                return false;
            }
        });

        imageButtonFemaleFret1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_FRET1_NOTE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_OPEN_NOTE);
                }
                return false;
            }
        });

        imageButtonFemaleFret2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_FRET2_NOTE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_OPEN_NOTE);
                }
                return false;
            }
        });

        imageButtonFemaleFret3.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_FRET3_NOTE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_OPEN_NOTE);
                }
                return false;
            }
        });

        imageButtonFemaleFret4.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_FRET4_NOTE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_OPEN_NOTE);
                }
                return false;
            }
        });
    }

    private void setInitialFingerPosition() {
        mFingerPositionListener.onMaleStringFingerPositionChanged(MALE_OPEN_NOTE);
        mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_OPEN_NOTE);
        mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_OPEN_NOTE);
    }

}
