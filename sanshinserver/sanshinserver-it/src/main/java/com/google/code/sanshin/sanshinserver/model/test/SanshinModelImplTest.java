
package com.google.code.sanshin.sanshinserver.model.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

public class SanshinModelImplTest extends TestCase {
    private com.google.code.sanshin.sanshinserver.model.SanshinModelImpl mSanshinModelImpl;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mSanshinModelImpl = new com.google.code.sanshin.sanshinserver.model.SanshinModelImpl();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddClient() {
        for (int i = 0; i < 16; ++i) {
            assertTrue(mSanshinModelImpl.addClient("client_id_" + i));
        }
        for (int i = 0; i < 16; ++i) {
            assertTrue(mSanshinModelImpl.getChannel("client_id_" + i) == i);
        }
    }

    public void testTooManyClient() {
        for (int i = 0; i < 16; ++i) {
            assertTrue(mSanshinModelImpl.addClient("client_id_" + i));
        }
        assertFalse(mSanshinModelImpl.addClient("client_id_16"));
    }

    public void testUnknownClient() {
        for (int i = 0; i < 16; ++i) {
            assertTrue(mSanshinModelImpl.addClient("client_id_" + i));
        }
        assertTrue(mSanshinModelImpl.getChannel("XXX") == -1);
    }
}
