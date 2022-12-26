package com.vodafone.common.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Util {

    private Util() {

    }

    public static int generateRandomNumber(int range) throws NoSuchAlgorithmException {
        Random random = SecureRandom.getInstanceStrong();

        return random.nextInt(range);
    }

    public enum DeviceState {
        ACTIVE,
        READY,
        ;

        @Override
        public String toString() {
            switch (this) {
                case ACTIVE:
                    return "active";
                case READY:
                    return "ready";
                default:
                    return "";
            }
        }

        public boolean state() {
            return this == ACTIVE;
        }

    }

}
