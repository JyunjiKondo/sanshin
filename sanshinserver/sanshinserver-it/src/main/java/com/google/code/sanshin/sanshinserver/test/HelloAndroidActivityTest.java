package com.google.code.sanshin.sanshinserver.test;

import android.test.ActivityInstrumentationTestCase2;
import com.google.code.sanshin.sanshinserver.*;

public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<HelloAndroidActivity> {

    public HelloAndroidActivityTest() {
        super(HelloAndroidActivity.class); 
    }

    public void testActivity() {
        HelloAndroidActivity activity = getActivity();
        assertNotNull(activity);
    }
}

