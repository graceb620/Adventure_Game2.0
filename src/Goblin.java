/** 
 * @author Grace Bero 
 * Creates the Goblin type that inherits traits from character
 */
import java.util.Random;

public class Goblin extends Character implements CharacterInterface {

    /**
     * Constructor for Goblin Class
     * @param n, string for name
     */
    private String attackLine;

    public Goblin(String n) {
        super(n, 8);
        health = 100;
        stamina = 6;
        damage = 0;
        attackLine = "";

    }
    
    /**
     * Public Accessor for attackLine
     * @return attackLine
     */
    public String getAttackLine() {
        return attackLine;
    }

    /**
     * Goblin attack pattern method
    */
    public void attack() {
        
        if (stamina >= 4) {
            damage = 20;
            stamina -= 4;
            attackLine = "The goblin used bite, dealing 20 dmg!";
        }
        else if (stamina >= 2) {
            damage = 10;
            stamina -= 2;
            attackLine = "The goblin used scratch, dealing 10 dmg!";
        }
        else {
            attackLine = "The goblin is too tired to attack!";
        }
    }

    /**
     * Stamina Regen for goblin method
     * Regen a random amount of stamina, stamina can't go over 6
     */
    public void staminaRegen() {
        if (stamina < 6) {
            int regenChance = new Random().nextInt(7-stamina);
            stamina += regenChance;
        }
    }

    /**
     * Goblin gets attacked and loses health method
     * @param damage, int for damage taken
     */
    public void loseHealth(int damage) {
        health -= damage;
    }

    /**
     * Goblin gains health method
     * @param damage, int for damage taken
     */
    public void gainHealth(int damage) {
        health += damage;
    }

    /**
     * Goblin toString method
     * 
     * @return goblinString, string with goblin name and health
     */
    public String toString() {
        String goblinString = "";
        goblinString += "Goblin: " + name + "\n";
        goblinString += "Current health: " + health + "\n";
        return goblinString;
    }
}