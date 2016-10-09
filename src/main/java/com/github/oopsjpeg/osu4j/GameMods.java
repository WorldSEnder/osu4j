package com.github.oopsjpeg.osu4j;

import java.util.EnumSet;

public class GameMods {
    private static GameMod[] ALL_MODS = GameMod.values();

    private EnumSet<GameMod> enabledMods;

    private GameMods(EnumSet<GameMod> mods) {
        this.enabledMods = mods;
    }

    public static GameMods parse(int modes) {
        EnumSet<GameMod> mods = EnumSet.noneOf(GameMod.class);
        for (GameMod mod : ALL_MODS) {
            int modMask = mod.getBitMask();
            if ((modes & modMask) == modMask) {
                mods.add(mod);
            }
        }
        return new GameMods(mods);
    }

    public int asInt() {
        int i = 0;
        for (GameMod mod : enabledMods) {
            i &= mod.getBitMask();
        }
        return i;
    }
}
