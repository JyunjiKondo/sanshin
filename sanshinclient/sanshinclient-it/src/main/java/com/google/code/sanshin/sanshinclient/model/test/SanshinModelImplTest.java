
package com.google.code.sanshin.sanshinclient.model.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

public class SanshinModelImplTest extends TestCase {
    private com.google.code.sanshin.sanshinclient.model.SanshinModelImpl mSanshinModelImpl;
    private int mPlayNoteNo;
    private int mPlayVelocity;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mSanshinModelImpl = new com.google.code.sanshin.sanshinclient.model.SanshinModelImpl();
        mSanshinModelImpl
                .addListener(new com.google.code.sanshin.sanshinclient.model.SanshinModelListener() {
                    public void play(int noteNo, int velocity) {
                        mPlayNoteNo = noteNo;
                        mPlayVelocity = velocity;
                    }
                });
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMaleString() {
        int testNoteValue = 60;
        int testVelocityValue = 100;
        mSanshinModelImpl.setMaleStringNote(testNoteValue);
        mSanshinModelImpl.pickMaleString(testVelocityValue);
        assertTrue(mPlayNoteNo == testNoteValue);
        assertTrue(mPlayVelocity == testVelocityValue);
    }

    public void testMiddleString() {
        int testNoteValue = 64;
        int testVelocityValue = 120;
        mSanshinModelImpl.setMiddleStringNote(testNoteValue);
        mSanshinModelImpl.pickMiddleString(testVelocityValue);
        assertTrue(mPlayNoteNo == testNoteValue);
        assertTrue(mPlayVelocity == testVelocityValue);
    }

    public void testFemaleString() {
        int testNoteValue = 72;
        int testVelocityValue = 127;
        mSanshinModelImpl.setFemaleStringNote(testNoteValue);
        mSanshinModelImpl.pickFemaleString(testVelocityValue);
        assertTrue(mPlayNoteNo == testNoteValue);
        assertTrue(mPlayVelocity == testVelocityValue);
    }

    public void testIllegalParameter() {
        assertFalse(mSanshinModelImpl.setMaleStringNote(-1));
        assertTrue(mSanshinModelImpl.setMaleStringNote(0));
        assertTrue(mSanshinModelImpl.setMaleStringNote(127));
        assertFalse(mSanshinModelImpl.setMaleStringNote(128));

        assertFalse(mSanshinModelImpl.setMiddleStringNote(-1));
        assertTrue(mSanshinModelImpl.setMiddleStringNote(0));
        assertTrue(mSanshinModelImpl.setMiddleStringNote(127));
        assertFalse(mSanshinModelImpl.setMiddleStringNote(128));

        assertFalse(mSanshinModelImpl.setFemaleStringNote(-1));
        assertTrue(mSanshinModelImpl.setFemaleStringNote(0));
        assertTrue(mSanshinModelImpl.setFemaleStringNote(127));
        assertFalse(mSanshinModelImpl.setFemaleStringNote(128));

        assertFalse(mSanshinModelImpl.pickMaleString(0));
        assertTrue(mSanshinModelImpl.pickMaleString(1));
        assertTrue(mSanshinModelImpl.pickMaleString(127));
        assertFalse(mSanshinModelImpl.pickMaleString(128));

        assertFalse(mSanshinModelImpl.pickMiddleString(0));
        assertTrue(mSanshinModelImpl.pickMiddleString(1));
        assertTrue(mSanshinModelImpl.pickMiddleString(127));
        assertFalse(mSanshinModelImpl.pickMiddleString(128));

        assertFalse(mSanshinModelImpl.pickFemaleString(0));
        assertTrue(mSanshinModelImpl.pickFemaleString(1));
        assertTrue(mSanshinModelImpl.pickFemaleString(127));
        assertFalse(mSanshinModelImpl.pickFemaleString(128));
    }

}
