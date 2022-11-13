package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {
    @Test
    void test_reserveValidSequence_specialTime() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        // 4 tickets of spider-man (12.5), but since it's at 11am, it's during special time so 25% off.
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 37.5);
    }

    @Test
    void test_reserveInvalidSequence_throwsException() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        // Only 9 movies are being shown in sequence.
        assertThrows(IllegalStateException.class, () -> theater.reserve(john, 10, 4));
    }

    @Test
    void test_printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }

    @Test
    void test_printMovieScheduleJson() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printScheduleJson();
    }
}
