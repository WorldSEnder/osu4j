package com.github.oopsjpeg.osu4j;

import java.io.IOException;

import com.github.oopsjpeg.osu4j.OsuMode;
import com.github.oopsjpeg.osu4j.Osu;
import com.github.oopsjpeg.osu4j.OsuScore;
import com.github.oopsjpeg.osu4j.OsuUser;
import com.github.oopsjpeg.osu4j.beatmap.OsuBeatmap;
import com.github.oopsjpeg.osu4j.util.OsuRateLimitException;

public class GetUserTest {
	
	private static final String USER = "oopsjpeg";
	private static final String KEY = "Your osu! API key"; // Replace this with your API key to test.
	private static final OsuMode MODE = OsuMode.STANDARD;
	private static final int TOP_SCORE_LIMIT = 3;
	
	public static void main(String[] args) throws OsuRateLimitException, IOException {
		// Create a new Osu object with an API key
		Osu osu = new Osu(KEY);
		
		// Get the user
		OsuUser user = osu.getUser(USER, MODE).withTopScores(TOP_SCORE_LIMIT);
		
		// Print basic information
		System.out.println(MODE.getName() + " Information for " + user.getUsername());
		System.out.println(user.getURL());
		System.out.println("Rank: #" + user.getRank());
		System.out.println("Performance Points: " + user.getPP() + "pp");
		System.out.println("Total Score: " + user.getTotalScore());
		
		// Print top scores
		for(int i = 0; i < user.getTopScores().size(); i++){
			OsuScore score = user.getTopScores().get(i);
			OsuBeatmap beatmap = score.getBeatmap();
			System.out.println("Top score " + (i+1) + ": " + beatmap.getArtist() + " - " + beatmap.getTitle());
		}
	}
}
