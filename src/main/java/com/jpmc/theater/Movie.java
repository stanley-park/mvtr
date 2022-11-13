package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Movie {
    private final String title;
    private String description;
    private final Duration runningTime;
    private final double ticketPrice;
    private final boolean isSpecial;

    public Movie(String title, Duration runningTime, double ticketPrice, boolean isSpecial) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.isSpecial = isSpecial;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRunningTime() {
        return humanReadableFormat(runningTime);
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        return value == 1 ? "" : "s";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(isSpecial, movie.isSpecial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, isSpecial);
    }

    @Override
    public String toString() {
        return this.getTitle() + " " + this.getRunningTime() + " $" + this.getTicketPrice();
    }
}
