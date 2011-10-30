
package com.google.code.sanshin.sanshinclient.view;

import jp.gr.java_conf.jyukon.sanshin.sanshinclient.R;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.code.sanshin.sanshinclient.presenter.SoftStringsSoftPickUpPresenter;

public class SanshinViewSoftStringsSoftPickUpImpl extends SanshinViewImpl {
    private static final String TAG = SanshinViewSoftStringsSoftPickUpImpl.class.getSimpleName();
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
    private static final int VIBRATOR_DURATION = 50; // milliseconds.

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

    private SoftStringsSoftPickUpPresenter mPresenter;
    private Vibrator mVibrator;

    // @Inject
    // OpenAccessory mOpenAccessory;
    private OpenAccessory mOpenAccessory;

    public SanshinViewSoftStringsSoftPickUpImpl() {
        mPresenter = new SoftStringsSoftPickUpPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
        setContentView(R.layout.soft_strings);

        makeUnnecessaryImageButtonsInvisible();
        setImageButtonListeners();
        setInitialFingerPosition();
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void play(int noteNo, int velocity) {
        mVibrator.vibrate(VIBRATOR_DURATION);
        super.play(noteNo, velocity);
    }

    private void makeUnnecessaryImageButtonsInvisible() {
        imageButtonMaleFret3.setVisibility(View.INVISIBLE);
        imageButtonMaleFret4.setVisibility(View.INVISIBLE);

        imageButtonMiddleFret4.setVisibility(View.INVISIBLE);
    }

    private void setImageButtonListeners() {

        imageButtonMaleOpen.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMaleStringFingerPositionChanged(MALE_OPEN_NOTE);
                    mPickUpListener.onMaleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonMaleFret1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMaleStringFingerPositionChanged(MALE_FRET1_NOTE);
                    mPickUpListener.onMaleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonMaleFret2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMaleStringFingerPositionChanged(MALE_FRET2_NOTE);
                    mPickUpListener.onMaleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonMiddleOpen.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_OPEN_NOTE);
                    mPickUpListener.onMiddleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonMiddleFret1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_FRET1_NOTE);
                    mPickUpListener.onMiddleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonMiddleFret2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_FRET2_NOTE);
                    mPickUpListener.onMiddleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonMiddleFret3.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onMiddleStringFingerPositionChanged(MIDDLE_FRET3_NOTE);
                    mPickUpListener.onMiddleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonFemaleOpen.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_OPEN_NOTE);
                    mPickUpListener.onFemaleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonFemaleFret1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_FRET1_NOTE);
                    mPickUpListener.onFemaleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonFemaleFret2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_FRET2_NOTE);
                    mPickUpListener.onFemaleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonFemaleFret3.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_FRET3_NOTE);
                    mPickUpListener.onFemaleStringPicked(VELOCITY);
                }
                return false;
            }
        });

        imageButtonFemaleFret4.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFingerPositionListener.onFemaleStringFingerPositionChanged(FEMALE_FRET4_NOTE);
                    mPickUpListener.onFemaleStringPicked(VELOCITY);
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
