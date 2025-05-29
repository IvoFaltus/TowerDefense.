import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

public class UnitTests {

    private Tower tower;
    private Knight knight;

    @BeforeEach
    public void setUp() {
        tower = new Tower();
        knight = new Knight(100);
        knight.setPosition(5,5);
        tower.setPosition(5,5);
        tower.setActive(true);
    }

    @Test
    public void testTowerInitialPosition() {
        assertEquals(5, tower.getX(), "Tower X coordinate should be 2.");
        assertEquals(5, tower.getY(), "Tower Y coordinate should be 2.");
    }

    @Test
    public void testTowerActivation() {
        assertTrue(tower.isActive(), "Tower should be active after creation.");
        tower.setActive(false);
        assertFalse(tower.isActive(), "Tower should be inactive after deactivation.");
    }

    @Test
    public void testKnightInitialPosition() {
        assertEquals(5, knight.getX(), "Knight X should be 5.");
        assertEquals(5, knight.getY(), "Knight Y should be 5.");
    }

    @Test
    public void testKnightTakeDamage() {
        int initialHP = knight.getHealth();
        knight.setHealth(initialHP-30);
        assertEquals(initialHP - 30, knight.getHealth(), "Knight should lose 30 HP.");
    }

    @Test
    public void testKnightIsAlive() {
        knight.setHealth(knight.getHealth()-100);
        assertFalse(knight.isAlive(), "Knight should not be alive after massive damage.");
    }
}
