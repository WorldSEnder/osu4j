package com.github.oopsjpeg.osu4j.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.GameMods;
import com.github.oopsjpeg.osu4j.OsuMode;
import com.github.oopsjpeg.osu4j.abstractbackend.LazilyLoaded;
import com.github.oopsjpeg.osu4j.backend.EndpointBeatmaps;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;

public class OsuMatchGame {
    private int gameID;
    private String startTime;
    private String endTime;
    private int beatmapID;
    private LazilyLoaded<OsuBeatmap> beatmap;
    private OsuMode playMode;
    private int matchType;
    private ScoringType scoringType;
    private TeamType teamType;
    private GameMods mods;
    private List<OsuMatchScore> allScores;
    private Map<Team, List<OsuMatchScore>> scoresByTeam;
    private Map<Integer, OsuMatchScore> scoreBySlot;

    public OsuMatchGame(Osu osu, JSONObject json) {
        gameID = Integer.parseInt(json.getString("game_id"));
        startTime = json.getString("start_time");
        endTime = json.getString("end_time");
        beatmapID = Integer.parseInt(json.getString("beatmap_id"));
        beatmap = osu.beatmaps.getAsQuery(new EndpointBeatmaps.ArgumentsBuilder().setBeatmapID(beatmapID).build())
                .asLazilyLoaded().map(list -> list.get(0));
        playMode = OsuMode.getByID(Integer.parseInt(json.getString("play_mode")));
        matchType = Integer.parseInt(json.getString("match_type"));
        scoringType = ScoringType.getById(Integer.parseInt(json.getString("scoring_type")));
        teamType = TeamType.getById(Integer.parseInt(json.getString("team_type")));
        mods = GameMods.parse(Integer.parseInt(json.getString("mods")));

        List<OsuMatchScore> scores = new ArrayList<>();
        JSONArray scoresJson = json.getJSONArray("scores");
        for (Object scoreObj : scoresJson) {
            scores.add(new OsuMatchScore(osu, (JSONObject) scoreObj));
        }
        allScores = Collections.unmodifiableList(scores);
        Map<Team, List<OsuMatchScore>> scoresByTeam = new EnumMap<>(Team.class);
        Map<Integer, OsuMatchScore> scoreBySlot = new HashMap<>();
        for (OsuMatchScore score : allScores) {
            scoresByTeam.computeIfAbsent(score.getTeam(), t -> new ArrayList<>()).add(score);
            Integer slot = Integer.valueOf(score.getSlot());
            if (scoreBySlot.containsKey(slot)) {
                throw new IllegalArgumentException("Two scores set in the same slot: " + slot);
            }
            scoreBySlot.put(slot, score);
        }
        this.scoresByTeam = new HashMap<>();
        scoresByTeam.forEach((team, scs) -> this.scoresByTeam.put(team, Collections.unmodifiableList(scs)));
        this.scoresByTeam = Collections.unmodifiableMap(this.scoresByTeam);
        this.scoreBySlot = Collections.unmodifiableMap(scoreBySlot);
    }

    public int getGameID() {
        return gameID;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getBeatmapID() {
        return beatmapID;
    }

    public LazilyLoaded<OsuBeatmap> getBeatmap() {
        return beatmap;
    }

    public OsuMode getMode() {
        return playMode;
    }

    public int getMatchTypeID() {
        return matchType;
    }

    public ScoringType getScoringType() {
        return scoringType;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public GameMods getMods() {
        return mods;
    }

    public List<OsuMatchScore> getAllScores() {
        return allScores;
    }

    public List<OsuMatchScore> getScores(Team team) {
        return scoresByTeam.getOrDefault(team, Collections.emptyList());
    }

    public Set<Team> getParticipatingTeams() {
        return scoresByTeam.keySet();
    }

    public OsuMatchScore getScore(int slot) {
        return scoreBySlot.get(Integer.valueOf(slot));
    }

    public Set<Integer> getUsedSlots() {
        return scoreBySlot.keySet();
    }
}
