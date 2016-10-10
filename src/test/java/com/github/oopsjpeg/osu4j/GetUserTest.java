package com.github.oopsjpeg.osu4j;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.oopsjpeg.osu4j.backend.EndpointUsers;
import com.github.oopsjpeg.osu4j.backend.EndpointUsers.Arguments;
import com.github.oopsjpeg.osu4j.backend.Osu;
import com.github.oopsjpeg.osu4j.exception.OsuAPIException;

@RunWith(JUnit4.class)
public class GetUserTest {

    private static final String USER = "oopsjpeg";
    private static final OsuMode MODE = OsuMode.STANDARD;

    private Osu backend;

    @Before
    public void setup() {
        backend = Osu.getAPI(TestUtils.getAPIToken());
    }

    @Test
    public void testUserByName() throws OsuAPIException {
        Arguments arguments = new EndpointUsers.ArgumentsBuilder(USER).setMode(MODE).build();
        OsuUser user = backend.users.query(arguments);
        Assert.assertEquals(2926649, user.getUserID());
    }
}
