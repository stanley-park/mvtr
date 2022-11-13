package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {
    private final Movie movie;
    private final int sequenceOfTheDay;
    private final LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public double getShowingPrice(int audienceCount) {
        return (this.movie.getTicketPrice() - getDiscount(this.getSequenceOfTheDay())) * audienceCount;
    }

    private double getDiscount(int showSequence) {
        double discount = 0;

        // 20% discount for special movie
        if (this.movie.isSpecial()) {
            discount = Math.max(discount, this.movie.getTicketPrice() * 0.2);
        }

        // Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
        if ((this.showStartTime.getHour() >= 11)
                && ((this.showStartTime.getHour() < 16)
                || (this.showStartTime.getHour() == 16 && this.showStartTime.getMinute() == 0))) {
            discount = Math.max(discount, this.movie.getTicketPrice() * 0.25);
        }

        // Any movies showing on 7th, you'll get 1$ discount
        if (this.showStartTime.getDayOfMonth() == 7) {
            discount = Math.max(discount, 1);
        }

        // $3 discount for 1st show
        if (showSequence == 1) {
            discount = Math.max(discount, 3);
        } else if (showSequence == 2) {
            discount = Math.max(discount, 2); // $2 discount for 2nd show
        }

        // biggest discount wins
        return discount;
    }

    @Override
    public String toString() {
        return this.getSequenceOfTheDay() + ": " + this.getStartTime() + " " + this.getMovie();
    }
}
