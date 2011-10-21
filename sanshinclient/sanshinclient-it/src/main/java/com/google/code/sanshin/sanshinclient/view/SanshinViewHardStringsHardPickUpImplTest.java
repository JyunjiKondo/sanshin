
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

import com.google.code.sanshin.sanshinclient.R;
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
    public void shouldInitializeNormally() throws Exception {
        Robolectric.bindShadowClass(ShadowLog.class);
        final SanshinViewHardStringsHardPickUpImpl activity = RoboGuice.getInjector(context)
                .getInstance(SanshinViewHardStringsHardPickUpImpl.class);
        activity.onCreate(null);
    }

    @Test
    public void shouldHaveApplicationName() throws Exception {
        String appName = new SanshinViewHardStringsHardPickUpImpl().getResources().getString(
                R.string.app_name);
        assertThat(appName, equalTo("sanshinclient"));
    }

    public class MyTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(OpenAccessory.class).toInstance(mOpenAccessoryMock);
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
