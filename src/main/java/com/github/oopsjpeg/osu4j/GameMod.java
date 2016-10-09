package com.github.oopsjpeg.osu4j;

public enum GameMod {
    NONE(0x0),
    NO_FAIL(0x1),
    EASY(0x2),
    NO_VIDEO(0x4),
    HIDDEN(0x8),
    HARD_ROCK(0x10),
    SUDDEN_DEATH(0x20),
    DOUBLE_TIME(0x40),
    RELAX(0x80),
    HALF_TIME(0x100),
    // Only set if double-time is set, too
    NIGHTCORE(0x200),
    FLASHLIGHT(0x400),
    AUTOPLAY(0x800),
    SPUN_OUT(0x1000),
    KINO(0x2000),
    PERFECT(0x4000),
    KEY1(0x4000000),
    KEY2(0x10000000),
    KEY3(0x8000000),
    KEY4(0x8000),
    KEY5(0x10000),
    KEY6(0x20000),
    KEY7(0x40000),
    KEY8(0x80000),
    KEY9(0x1000000),
    KEY10(0x2000000),
    FADE_IN(0x100000),
    RANDOM(0x200000),
    LAST_MOD(0x400000);
    // keyMod = Key4 | Key5 | Key6 | Key7 | Key8,
    // FreeModAllowed = NoFail | Easy | Hidden | HardRock | SuddenDeath | Flashlight | FadeIn | Relax | Relax2 | SpunOut
    // | keyMod
    ;

    private final int bitMask;

    private GameMod(int mask) {
        this.bitMask = mask;
    }

    public int getBitMask() {
        return bitMask;
    }
}
