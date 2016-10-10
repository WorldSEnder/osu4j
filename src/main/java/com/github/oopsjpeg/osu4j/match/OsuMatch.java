package com.github.oopsjpeg.osu4j.match;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.util.Utility;

public class OsuMatch {

    private final int matchID;
    private final String name;
    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime; // Not supported as of API v1.0, always null
    private final List<OsuMatchGame> games;

    public OsuMatch(Osu osu, JSONObject json) {
        JSONObject match = json.getJSONObject("match");
        matchID = Integer.parseInt(match.getString("match_id"));
        name = match.getString("name");
        startTime = Utility.parseDate(match.getString("start_time"));
        endTime = Utility.parseDate(match.getString("end_time"));
        JSONArray gamesArr = json.getJSONArray("games");
        games = new ArrayList<>();
        for (int i = 0; i < gamesArr.length(); i++) {
            games.add(new OsuMatchGame(osu, gamesArr.getJSONObject(i)));
        }
    }

    public int getMatchID() {
        return matchID;
    }

    public String getName() {
        return name;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public List<OsuMatchGame> getGames() {
        return games;
    }

    public OsuMatchGame getGame(int index) {
        return games.get(index);
    }

    public URL getURL() throws MalformedURLException {
        return new URL("https://osu.ppy.sh/mp/" + matchID);
    }

}
