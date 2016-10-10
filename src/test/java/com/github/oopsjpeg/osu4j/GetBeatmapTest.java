package com.github.oopsjpeg.osu4j;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.oopsjpeg.osu4j.backend.EndpointBeatmaps;
import com.github.oopsjpeg.osu4j.backend.EndpointBeatmaps.Arguments;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.exception.OsuAPIException;

@RunWith(JUnit4.class)
public class GetBeatmapTest {

    private static final int BEATMAP_ID = 131891;
    private static final OsuMode MODE = OsuMode.STANDARD;

    private Osu backend;

    @Before
    public void setup() {
        backend = Osu.getAPI(TestUtils.getAPIToken());
    }

    @Test
    public void testBeatmapById() throws OsuAPIException {
        // Get the beatmap
        Arguments arguments = new EndpointBeatmaps.ArgumentsBuilder().setBeatmapID(BEATMAP_ID).setMode(MODE).build();
        List<OsuBeatmap> beatmaps = backend.beatmaps.getAsQuery(arguments).resolve();
        Assert.assertTrue("Expected exactly one beatmap per id", beatmaps.size() == 1);
        OsuBeatmap beatmap = beatmaps.get(0);
        // TODO: should all go into their own tests
        Assert.assertEquals("The Quick Brown Fox", beatmap.getArtist());
        Assert.assertEquals("The Big Black", beatmap.getTitle());
        Assert.assertEquals("Blue Dragon", beatmap.getCreatorName());
    }
}
