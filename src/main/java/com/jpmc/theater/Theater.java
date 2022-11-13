package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.json.JSONObject;

import static com.jpmc.theater.MovieNames.SPIDER_MAN_NO_WAY_HOME;
import static com.jpmc.theater.MovieNames.THE_BATMAN;
import static com.jpmc.theater.MovieNames.TURNING_RED;

public class Theater {

    private final LocalDateProvider provider;
    private final List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie(SPIDER_MAN_NO_WAY_HOME.getTitle(), Duration.ofMinutes(90), 12.5, true);
        Movie turningRed = new Movie(TURNING_RED.getTitle(), Duration.ofMinutes(85), 11, false);
        Movie theBatMan = new Movie(THE_BATMAN.getTitle(), Duration.ofMinutes(95), 9, false);
        schedule = List.of(
            new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
            new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
            new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
            new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
            new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
            new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
            new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
            new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
            new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(System.out::println);
        System.out.println("===================================================");
    }

    public void printScheduleJson() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(showing -> {
            System.out.println(new JSONObject(showing).toString(4));
        });
        System.out.println("===================================================");
    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }
}
