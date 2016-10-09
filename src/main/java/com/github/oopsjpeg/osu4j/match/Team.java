package com.github.oopsjpeg.osu4j.match;

public enum Team {
    NONE,
    BLUE,
    RED;

    public static Team getById(int parsedInt) {
        switch (parsedInt) {
        case 0:
            return NONE;
        case 1:
            return Team.BLUE;
        case 2:
            return Team.RED;
        default:
            throw new IllegalArgumentException("Unknown id: " + parsedInt);
        }
    }
}
