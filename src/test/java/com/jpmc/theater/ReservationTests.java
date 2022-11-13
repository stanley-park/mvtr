package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTests {

    @Test
    void testTotalFee_firstSpecialMovieOutsideOfSpecialTime_firstSequenceDiscountOverridesSpecialDiscount() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true),
                1,
                LocalDateTime.of(2022, 11, 13, 18, 30)
        );
        assertEquals(28.5, new Reservation(customer, showing, 3).totalFee());
    }

    @Test
    void testTotalFee_firstSpecialMovieWithinSpecialTime_specialTimeOverridesAllDiscounts() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true),
                1,
                LocalDateTime.of(2022, 11, 13, 13, 30)
        );
        assertEquals(37.5, new Reservation(customer, showing, 4).totalFee());
    }

    @Test
    void testTotalFee_secondNonSpecialMovieOutsideOfSpecialTime() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, false),
                2,
                LocalDateTime.of(2022, 11, 13, 16, 1)
        );
        assertEquals(42.0, new Reservation(customer, showing, 4).totalFee());
    }
}
