package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.jpmc.theater.MovieNames.SPIDER_MAN_NO_WAY_HOME;
import static com.jpmc.theater.MovieNames.TURNING_RED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTests {
    @Test
    public void testGetShowingPrice_specialMovieOutsideOfSpecialTime_20PercentOff() {
        Movie spiderMan = new Movie(SPIDER_MAN_NO_WAY_HOME.getTitle(), Duration.ofMinutes(90),12.5, true);
        Showing spiderManShowing = new Showing(spiderMan, 5, LocalDateTime.of(2022, 11, 13, 10, 5));
        assertEquals(10, spiderManShowing.getShowingPrice(1));
    }

    @Test
    public void testGetShowingPrice_specialMovieDuringSpecialTime_25PercentOff() {
        Movie spiderMan = new Movie(SPIDER_MAN_NO_WAY_HOME.getTitle(), Duration.ofMinutes(90),12.5, true);
        Showing spiderManShowing = new Showing(spiderMan, 5, LocalDateTime.of(2022, 11, 13, 11, 5));
        assertEquals(9.375, spiderManShowing.getShowingPrice(1));
    }

    @Test
    public void testGetShowingPrice_regularMovieOn7th_1DollarOff() {
        Movie turningRed = new Movie(TURNING_RED.getTitle(), Duration.ofMinutes(90),11, false);
        Showing turningRedShowing = new Showing(turningRed, 5, LocalDateTime.of(2022, 11, 7, 10, 59));
        assertEquals(20, turningRedShowing.getShowingPrice(2));
    }
}
