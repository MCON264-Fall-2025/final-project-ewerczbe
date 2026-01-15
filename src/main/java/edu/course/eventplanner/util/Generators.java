package edu.course.eventplanner.util;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generators {

    private static final Random rand = new Random();

    public static List<Venue> generateVenues() {
        List<Venue> venues = new ArrayList<>();
        venues.add(new Venue("Grand Hall", 120, 100, 10, 10));
        venues.add(new Venue("Community Center", 80, 60, 8, 8));
        venues.add(new Venue("Banquet Room", 100, 80, 9, 10));
        venues.add(new Venue("Outdoor Pavilion", 60, 40, 6, 6));
        venues.add(new Venue("Small Lounge", 40, 20, 4, 5));
        return venues;
    }

    public static List<Guest> GenerateGuests(int n) {
        List<Guest> guests = new ArrayList<>();
        String[] tags = {"family", "friends", "coworkers", "neighbors"};

        for (int i = 1; i <= n; i++) {
            String name = "Guest" + i;
            String tag = tags[rand.nextInt(tags.length)];
            guests.add(new Guest(name, tag));
        }

        return guests;
    }
}