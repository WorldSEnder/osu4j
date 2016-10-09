package com.github.oopsjpeg.osu4j.backend;

public class RateLimiter {
    private static final long MILLIS_AS_NANOS = 1000 * 1000;
    private static final long MINUTES_AS_NANOS = 60 * 1000 * MILLIS_AS_NANOS;

    private final Object mutex = this;
    private final long nanoSecondsPerRequest;
    private long nextRequestPermit;

    public RateLimiter(int rateLimitPerMinute) {
        nanoSecondsPerRequest = MINUTES_AS_NANOS / rateLimitPerMinute;
    }

    public void getOrWaitForTicket() {
        synchronized (mutex) {
            long now = System.nanoTime();
            long waitTime = nextRequestPermit - now;
            nextRequestPermit = now + nanoSecondsPerRequest;
            if (waitTime < 0) {
                return;
            }
            try {
                Thread.sleep(waitTime / MILLIS_AS_NANOS, (int) (waitTime % MILLIS_AS_NANOS));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
