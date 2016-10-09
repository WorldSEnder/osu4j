package com.github.oopsjpeg.osu4j.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import org.json.JSONObject;

public class Utility {

    // peppy's server offset I'd guess
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder().parseCaseInsensitive()
            .appendPattern("u-M-d H:m:s").toFormatter().withZone(ZoneId.of("UTC+8"));

    public static ZonedDateTime parseDate(String dayFromApi) {
        if (dayFromApi == null) {
            // Actually bad practice, but we shall allow it for /api/get_match
            return null;
        }
        return ZonedDateTime.parse(dayFromApi, FORMATTER);
    }

    public static String toMySqlString(ZonedDateTime date) {
        if (date == null) {
            return null;
        }
        return date.format(FORMATTER);
    }

    public static String urlEncode(String argument) {
        try {
            return URLEncoder.encode(argument, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean getAsBoolean(JSONObject json, String attribute) {
        return getAsInt(json, attribute) == 1;
    }

    public static int getAsInt(JSONObject json, String attribute) {
        return Integer.parseInt(json.getString(attribute));
    }

    public static long getAsLong(JSONObject json, String attribute) {
        return Long.parseLong(json.getString(attribute));
    }

    public static double getAsDouble(JSONObject json, String attribute) {
        return Double.parseDouble(json.getString(attribute));
    }

    public static ZonedDateTime getAsDate(JSONObject json, String attribute) {
        return parseDate(json.getString(attribute));
    }

}
