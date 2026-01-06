import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.service.GuestListManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuestListManagerTest {

    @Test
    void testAddGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Alice", "Friends"));
        assertEquals(1, manager.getGuestCount());
    }

    @Test
    void testRemoveGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Bob", "Family"));

        boolean removed = manager.removeGuest("Bob");

        assertTrue(removed);
        assertEquals(0, manager.getGuestCount());
    }

    @Test
    void testLookupGuest() {
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Charlie", "Work"));

        Guest found = manager.findGuest("Charlie");

        assertNotNull(found);
        assertEquals("Charlie", found.getName());
    }
}
