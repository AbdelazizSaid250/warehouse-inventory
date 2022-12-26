package com.vodafone.common.util;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void testGenerateRandomNumber() throws NoSuchAlgorithmException {
        assertEquals(0, Util.generateRandomNumber(1));
    }


    @Test
    void testDeviceStateToString() {
        assertEquals("active", Util.DeviceState.ACTIVE.toString());
        assertEquals("ready", Util.DeviceState.READY.toString());
    }


    @Test
    void testDeviceStateState() {
        assertTrue(Util.DeviceState.ACTIVE.state());
        assertFalse(Util.DeviceState.READY.state());
    }
}

