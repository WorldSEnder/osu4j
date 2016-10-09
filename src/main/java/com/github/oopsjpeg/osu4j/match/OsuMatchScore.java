package com.github.oopsjpeg.osu4j.match;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.OsuUser;
import com.github.oopsjpeg.osu4j.abstractbackend.LazilyLoaded;
import com.github.oopsjpeg.osu4j.backend.EndpointUsers;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.util.Utility;

public class OsuMatchScore {
    private int slot;
    private Team team;
    private int userID;
    private LazilyLoaded<OsuUser> user;
    private int score;
    private int maxCombo;
    @SuppressWarnings("unused")
    private int rank; // Not used
    private int count50;
    private int count100;
    private int count300;
    private int countMiss;
    private int countGeki;
    private int countKatu;
    private boolean perfect;
    private boolean passed;

    public OsuMatchScore(Osu osu, JSONObject json) {
        slot = Utility.getAsInt(json, "slot");
        team = Team.getById(Utility.getAsInt(json, "team"));
        userID = Utility.getAsInt(json, "user_id");
        user = osu.users.getAsQuery(new EndpointUsers.ArgumentsBuilder(userID).build()).asLazilyLoaded();
        score = Utility.getAsInt(json, "score");
        maxCombo = Utility.getAsInt(json, "maxcombo");
        rank = Utility.getAsInt(json, "rank");
        count50 = Utility.getAsInt(json, "count50");
        count100 = Utility.getAsInt(json, "count100");
        count300 = Utility.getAsInt(json, "count300");
        countMiss = Utility.getAsInt(json, "countmiss");
        countGeki = Utility.getAsInt(json, "countgeki");
        countKatu = Utility.getAsInt(json, "countkatu");
        perfect = Utility.getAsBoolean(json, "perfect");
        passed = Utility.getAsBoolean(json, "pass");
    }

    public int getSlot() {
        return slot;
    }

    public Team getTeam() {
        return team;
    }

    public int getUserID() {
        return userID;
    }

    public LazilyLoaded<OsuUser> getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }

    public int getMaxCombo() {
        return maxCombo;
    }

    public int getCount50() {
        return count50;
    }

    public int getCount100() {
        return count100;
    }

    public int getCount300() {
        return count300;
    }

    public int getCountMiss() {
        return countMiss;
    }

    public int getCountGeki() {
        return countGeki;
    }

    public int getCountKatu() {
        return countKatu;
    }

    public boolean isPerfect() {
        return perfect;
    }

    public boolean isPassed() {
        return passed;
    }
}
