package com.github.oopsjpeg.osu4j.beatmap;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;

import org.json.JSONObject;

import com.github.oopsjpeg.osu4j.ApprovalMode;
import com.github.oopsjpeg.osu4j.OsuMode;
import com.github.oopsjpeg.osu4j.OsuUser;
import com.github.oopsjpeg.osu4j.abstractbackend.LazilyLoaded;
import com.github.oopsjpeg.osu4j.backend.EndpointUsers;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.util.Utility;

public class OsuBeatmap {
    protected ApprovalMode approved;
    protected ZonedDateTime approvedDate;
    protected ZonedDateTime lastUpdate;
    protected String artist;
    protected int beatmapID;
    protected int beatmapSetID;
    protected double bpm;
    protected String creatorName;
    protected LazilyLoaded<OsuUser> creator;
    protected double difficultyRating;
    protected double diffSize;
    protected double diffOverall;
    protected double diffApproach;
    protected double diffDrain;
    protected double hitLength;
    protected String source;
    protected Genre genreID;
    protected Language languageID;
    protected String title;
    protected double totalLength;
    protected String version;
    protected String fileMd5;
    protected OsuMode mode;
    protected String[] tags;
    protected long favouriteCount;
    protected long playCount;
    protected long passCount;
    protected long maxCombo;

    public OsuBeatmap(Osu osu, JSONObject json) {
        approved = ApprovalMode.getById(Utility.getAsInt(json, "approved"));
        approvedDate = Utility.getAsDate(json, "approved_date");
        lastUpdate = Utility.getAsDate(json, "last_update");
        artist = json.getString("artist");
        beatmapID = Utility.getAsInt(json, "beatmap_id");
        beatmapSetID = Utility.getAsInt(json, "beatmapset_id");
        bpm = Utility.getAsDouble(json, "bpm");
        creatorName = json.getString("creator");
        creator = osu.users.getAsQuery(new EndpointUsers.ArgumentsBuilder(creatorName).build()).asLazilyLoaded();
        difficultyRating = Utility.getAsDouble(json, "difficultyrating");
        diffSize = Utility.getAsDouble(json, "diff_size");
        diffOverall = Utility.getAsDouble(json, "diff_overall");
        diffApproach = Utility.getAsDouble(json, "diff_approach");
        diffDrain = Utility.getAsDouble(json, "diff_drain");
        hitLength = Utility.getAsDouble(json, "hit_length");
        source = json.getString("source");
        genreID = Genre.getById(Utility.getAsInt(json, "genre_id"));
        languageID = Language.getById(Utility.getAsInt(json, "language_id"));
        title = json.getString("title");
        totalLength = Utility.getAsDouble(json, "total_length");
        version = json.getString("version");
        fileMd5 = json.getString("file_md5");
        mode = OsuMode.getByID(Utility.getAsInt(json, "mode"));
        tags = json.getString("tags").split(" ");
        favouriteCount = Utility.getAsLong(json, "favourite_count");
        playCount = Utility.getAsLong(json, "playcount");
        passCount = Utility.getAsLong(json, "passcount");
        maxCombo = Utility.getAsLong(json, "max_combo");
    }

    public ApprovalMode getApprovalMode() {
        return approved;
    }

    public ZonedDateTime getApprovedDate() {
        return approvedDate;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public String getArtist() {
        return artist;
    }

    public int getBeatmapID() {
        return beatmapID;
    }

    public int getBeatmapSetID() {
        return beatmapSetID;
    }

    public long getBPM() {
        return Math.round(bpm);
    }

    public double getBPMRaw() {
        return bpm;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public LazilyLoaded<OsuUser> getCreator() {
        return creator;
    }

    public double getDifficultyRating() {
        return difficultyRating;
    }

    public double getCS() {
        return diffSize;
    }

    public double getOD() {
        return diffOverall;
    }

    public double getAR() {
        return diffApproach;
    }

    public double getHP() {
        return diffDrain;
    }

    public double getHitLength() {
        return hitLength;
    }

    public String getSource() {
        return source;
    }

    public Genre getGenreID() {
        return genreID;
    }

    public Language getLanguageID() {
        return languageID;
    }

    public String getTitle() {
        return title;
    }

    public double getTotalLength() {
        return totalLength;
    }

    public String getVersion() {
        return version;
    }

    public String getFileMD5() {
        return fileMd5;
    }

    public OsuMode getMode() {
        return mode;
    }

    public String[] getTags() {
        return tags;
    }

    public long getFavouriteCount() {
        return favouriteCount;
    }

    public long getPlayCount() {
        return playCount;
    }

    public long getPassCount() {
        return passCount;
    }

    public long getMaxCombo() {
        return maxCombo;
    }

    public URL getURL() throws MalformedURLException {
        return new URL("https://osu.ppy.sh/b/" + beatmapSetID);
    }
}
