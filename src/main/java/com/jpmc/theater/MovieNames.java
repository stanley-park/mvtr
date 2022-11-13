package com.jpmc.theater;

public enum MovieNames {
    SPIDER_MAN_NO_WAY_HOME("Spider-Man: No Way Home"),
    TURNING_RED("Turning Red"),
    THE_BATMAN("The Batman");

    private final String title;

    MovieNames(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
