
package com.google.code.sanshin.sanshinclient.view;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.code.sanshin.sanshinclient.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class SanshinViewHardStringsHardPickUpImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldInitializeNormally() throws Exception {
        new SanshinViewHardStringsHardPickUpImpl().onCreate(null);
    }

    @Test
    public void shouldHaveApplicationName() throws Exception {
        String appName = new SanshinViewHardStringsHardPickUpImpl().getResources().getString(
                R.string.app_name);
        assertThat(appName, equalTo("sanshinclient"));
    }
}
