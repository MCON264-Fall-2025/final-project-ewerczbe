package edu.course.eventplanner.service;

import edu.course.eventplanner.Main;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testLoadSampleDataDoesNotThrow() {
        Scanner kb = new Scanner("5\n");
        assertDoesNotThrow(() -> Main.handleLoadSampleData(kb));
    }

    @Test
    void testAddGuestDoesNotThrow() {
        Scanner kb = new Scanner("Alice\nfamily\n");
        assertDoesNotThrow(() -> Main.handleAddGuest(kb));
    }

    @Test
    void testRemoveGuestDoesNotThrow() {
        Scanner kbLoad = new Scanner("3\n");
        Main.handleLoadSampleData(kbLoad);

        Scanner kbRemove = new Scanner("1\n");
        assertDoesNotThrow(() -> Main.handleRemoveGuest(kbRemove));
    }

    @Test
    void testSelectVenueDoesNotThrow() {
        Scanner kbLoad = new Scanner("3\n");
        Main.handleLoadSampleData(kbLoad);

        Scanner kbVenue = new Scanner("200\n3\n");
        assertDoesNotThrow(() -> Main.handleSelectVenue(kbVenue));
    }

    @Test
    void testGenerateSeatingDoesNotThrow() {
        Scanner kbLoad = new Scanner("3\n");
        Main.handleLoadSampleData(kbLoad);

        Scanner kbVenue = new Scanner("200\n3\n");
        Main.handleSelectVenue(kbVenue);

        assertDoesNotThrow(Main::handleGenerateSeating);
    }

    @Test
    void testAddTaskDoesNotThrow() {
        Scanner kb = new Scanner("Decorate\n");
        assertDoesNotThrow(() -> Main.handleAddTask(kb));
    }

    @Test
    void testExecuteTaskDoesNotThrow() {
        Scanner kb = new Scanner("Cook\n");
        Main.handleAddTask(kb);

        assertDoesNotThrow(Main::handleExecuteTask);
    }

    @Test
    void testUndoTaskDoesNotThrow() {
        Scanner kb = new Scanner("Clean\n");
        Main.handleAddTask(kb);
        Main.handleExecuteTask();

        assertDoesNotThrow(Main::handleUndoTask);
    }

    @Test
    void testPrintSummaryDoesNotThrow() {
        assertDoesNotThrow(Main::handlePrintSummary);
    }
}