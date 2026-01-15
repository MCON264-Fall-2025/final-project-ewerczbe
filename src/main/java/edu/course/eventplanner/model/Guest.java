package edu.course.eventplanner.model;

import java.util.UUID;

public class Guest {

    private final UUID id;
    private final String name;
    private final String groupTag;

    public Guest(String name, String groupTag) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.groupTag = groupTag;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroupTag() {
        return groupTag;
    }
}