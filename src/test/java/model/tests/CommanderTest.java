package model.tests;

import model.Attribute;
import model.Commander;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommanderTest {
    private Commander cmdr;

    @BeforeEach
    void setUp() {
        cmdr = new Commander("LOGIN", "TheMind", Map.of(
                Attribute.AGILITY, 5,
                Attribute.CHARISMA, 5,
                Attribute.INTELLIGENCE, 3
        ), 10);
    }

    @Test
    void editPoints() {
        assertEquals(10, cmdr.getPoints());

        assertThrows(IllegalArgumentException.class, () -> cmdr.setPoints(-5));
        cmdr.setPoints(3);
        assertEquals(3, cmdr.getPoints());

        assertThrows(IllegalArgumentException.class, () -> cmdr.addPoints(-5));
        cmdr.addPoints(-3);
        assertEquals(0, cmdr.getPoints());
        cmdr.addPoints(54);
        assertEquals(54, cmdr.getPoints());
        cmdr.addPoints(12);
        assertEquals(66, cmdr.getPoints());
        cmdr.addPoints(-2);
        assertEquals(64, cmdr.getPoints());
    }

    @Test
    void testChangeCredentials() {
        assertEquals("TheMind", cmdr.getRealName());

        cmdr.setRealName(null);
        assertEquals(cmdr.getCommanderName(), cmdr.getRealName());

        cmdr.setRealName("Ryan Ward");
        assertEquals("Ryan Ward", cmdr.getRealName());

        cmdr.setRealName("       ");
        assertEquals(cmdr.getCommanderName(), cmdr.getRealName());
    }
}