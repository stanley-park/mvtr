package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.jpmc.theater.MovieNames.SPIDER_MAN_NO_WAY_HOME;
import static com.jpmc.theater.MovieNames.THE_BATMAN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MovieTests {
    @Test
    public void testGetRunningTime() {
        Movie spiderMan = new Movie(SPIDER_MAN_NO_WAY_HOME.getTitle(), Duration.ofMinutes(90),12.5, true);
        assertEquals("(1 hour 30 minutes)", spiderMan.getRunningTime());
    }

    @Test
    public void testGetIsSpecial() {
        Movie spiderMan = new Movie(SPIDER_MAN_NO_WAY_HOME.getTitle(), Duration.ofMinutes(90),12.5, false);
        assertFalse(spiderMan.isSpecial());
    }

    @Test
    public void testGetSetDescription() {
        Movie spiderMan = new Movie(SPIDER_MAN_NO_WAY_HOME.getTitle(), Duration.ofMinutes(120),12.5, true);
        assertNull(spiderMan.getDescription());
        spiderMan.setDescription("Spider-Man: No Way Home is a 2021 American superhero film based on the Marvel " +
                "Comics character Spider-Man, co-produced by Columbia Pictures and Marvel Studios and distributed " +
                "by Sony Pictures Releasing.");
        assertNotNull(spiderMan.getDescription());
    }

    @Test
    public void testMovieToString_nonPlural() {
        Movie theBatman = new Movie(THE_BATMAN.getTitle(), Duration.ofMinutes(121),14.75, false);
        assertEquals("The Batman (2 hours 1 minute) $14.75", theBatman.toString());
    }

    @Test
    public void testMovieToString_plural() {
        Movie theBatman = new Movie(THE_BATMAN.getTitle(), Duration.ofMinutes(120),14.75, false);
        assertEquals("The Batman (2 hours 0 minutes) $14.75", theBatman.toString());
    }
}
