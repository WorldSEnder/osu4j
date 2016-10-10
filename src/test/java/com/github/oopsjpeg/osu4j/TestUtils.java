package com.github.oopsjpeg.osu4j;

public class TestUtils {

    public static String getAPIToken() {
        String token = System.getenv("osu.apitoken");
        if (token == null) {
            throw new IllegalStateException(
                    "API Token not set, make sure to set the environment variable osu.apitoken");
        }
        return token;
    }
}
