import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

/**
 * UnitTests class provides test cases for verifying the behavior of Tower and Knight classes.
 * <p>
 * It checks the initial positions, activation state of towers, knight health logic,
 * and whether a knight is considered alive after taking damage.
 */
public class UnitTests {

    private Tower tower;
    private Knight knight;

    /**
     * Sets up a new tower and knight instance before each test.
     * Initializes them to the same position and activates the tower.
     */
    @BeforeEach
    public void setUp() {
        tower = new Tower();
        knight = new Knight(100);
        knight.setPosition(5, 5);
        tower.setPosition(5, 5);
        tower.setActive(true);
    }

    /**
     * Tests whether the tower's initial position is set correctly.
     */
    @Test
    public void testTowerInitialPosition() {
        assertEquals(5, tower.getX(), "Tower X coordinate should be 5.");
        assertEquals(5, tower.getY(), "Tower Y coordinate should be 5.");
    }

    /**
     * Tests tower activation and deactivation logic.
     */
    @Test
    public void testTowerActivation() {
        assertTrue(tower.isActive(), "Tower should be active after creation.");
        tower.setActive(false);
        assertFalse(tower.isActive(), "Tower should be inactive after deactivation.");
    }

    /**
     * Tests whether the knight's position is initialized correctly.
     */
    @Test
    public void testKnightInitialPosition() {
        assertEquals(5, knight.getX(), "Knight X should be 5.");
        assertEquals(5, knight.getY(), "Knight Y should be 5.");
    }

    /**
     * Tests that the knight loses health correctly when taking damage.
     */
    @Test
    public void testKnightTakeDamage() {
        int initialHP = knight.getHealth();
        knight.setHealth(initialHP - 30);
        assertEquals(initialHP - 30, knight.getHealth(), "Knight should lose 30 HP.");
    }

    /**
     * Tests whether the knight is correctly marked as not alive after taking fatal damage.
     */
    @Test
    public void testKnightIsAlive() {
        knight.setHealth(knight.getHealth() - 100);
        assertFalse(knight.isAlive(), "Knight should not be alive after massive damage.");
    }



}
