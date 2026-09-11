package org.saadmeddiche.pixelworld.util;

public class TimeSpentUtil {

    public static long inNano(Runnable runnable) {

        long start = System.nanoTime();

        runnable.run();

        long end = System.nanoTime();

        return end - start;

    }

    public static double inMillis(Runnable runnable) {

        long nanoTime = inNano(runnable);

        return nanoTime / 1e6;

    }

    public static double inSeconds(Runnable runnable) {

        double millisTime = inMillis(runnable);

        return millisTime / 1e3;

    }

}