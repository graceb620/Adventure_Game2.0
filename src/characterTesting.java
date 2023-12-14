/**
 * characterTesting.java
 * File to test to ensure that character classes are working properly
 * @author Grace Bero
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class characterTesting {
    //Test to see if we can successfully get wizards name
    @Test
    public void testWizardName() {
        Wizard w = new Wizard("Grace");
        assertEquals("Grace", w.getName());
    }

    //Test to see if the health of boss is corect after taking damage
    @Test
    public void testBossHealth() {
        Boss b = new Boss("Vlad");
        b.loseHealth(50);
        assertEquals(150, b.getHealth());
    }

    //Test to see if the attackLine of the boss is correct
    @Test
    public void testBossAttackLine() {
        Boss b = new Boss("Vlad");
        b.setStamina(4);
        b.attack();
        assertEquals("Vlad used Blood Cyphon, dealing 30 dmg!", b.getAttackLine());
    }

    //Test to see if the 
}
