package com.google.code.sanshin.sanshinclient.test;

import jp.gr.java_conf.jyukon.sanshin.sanshinclient.HelloAndroidActivity;
import android.test.ActivityInstrumentationTestCase2;
import com.google.code.sanshin.sanshinclient.*;

public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<HelloAndroidActivity> {

    public HelloAndroidActivityTest() {
        super(HelloAndroidActivity.class); 
    }

    public void testActivity() {
        HelloAndroidActivity activity = getActivity();
        assertNotNull(activity);
    }
}

