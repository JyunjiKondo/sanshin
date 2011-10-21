
package com.google.code.sanshin.sanshinclient.view;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import roboguice.RoboGuice;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.code.sanshin.sanshinclient.midiplayer.MidiPlayerClient;
import com.google.code.sanshin.sanshinclient.openaccessory.OpenAccessory;
import com.google.inject.AbstractModule;
import com.google.inject.util.Modules;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.internal.Implements;

@RunWith(RobolectricTestRunner.class)
public class SanshinViewHardStringsHardPickUpImplTest {
    protected Context context = new Activity();
    OpenAccessoryMock mOpenAccessoryMock = new OpenAccessoryMock();
    MidiPlayerClientMock mMidiPlayerClientMock = new MidiPlayerClientMock();

    @Before
    public void setUp() throws Exception {
        RoboGuice.setBaseApplicationInjector(
                Robolectric.application,
                RoboGuice.DEFAULT_STAGE,
                Modules.override(RoboGuice.newDefaultRoboModule(Robolectric.application)).with(
                        new MyTestModule()));
    }

    @After
    public void tearDown() throws Exception {
        RoboGuice.util.reset();
    }

    @Test
    public void receivingDataFromOpenAccessoryShouldCauseMidiPlayerToPlay() throws Exception {
        Robolectric.bindShadowClass(ShadowLog.class);
        final SanshinViewHardStringsHardPickUpImpl activity = RoboGuice.getInjector(context)
                .getInstance(SanshinViewHardStringsHardPickUpImpl.class);
        activity.onCreate(null);
        activity.onStart();
        activity.onResume();

        byte[] data = new byte[3];
        data[0] = OpenAccessory.STRING_PICKED;
        data[1] = 60; // noteNo
        data[2] = 100; // velocity
        mOpenAccessoryMock.sendMessage(data);
        assertThat(mMidiPlayerClientMock.getNoteNo(), equalTo((int) data[1]));
        assertThat(mMidiPlayerClientMock.getVelocity(), equalTo((int) data[2]));

        data[0] = OpenAccessory.STRING_PICKED;
        data[1] = 50; // noteNo
        data[2] = 120; // velocity
        mOpenAccessoryMock.sendMessage(data);
        assertThat(mMidiPlayerClientMock.getNoteNo(), equalTo((int) data[1]));
        assertThat(mMidiPlayerClientMock.getVelocity(), equalTo((int) data[2]));

        activity.onPause();
        activity.onStop();
        activity.onDestroy();
    }

    public class MyTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(OpenAccessory.class).toInstance(mOpenAccessoryMock);
            bind(MidiPlayerClient.class).toInstance(mMidiPlayerClientMock);
        }
    }

    @Implements(Log.class)
    public static class ShadowLog {
        public static int d(java.lang.String tag, java.lang.String msg) {
            System.out.println("[" + tag + "] " + msg);
            return 0;
        }
    }
}
