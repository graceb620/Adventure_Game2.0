/** 
* @author Grace Bero  
* Creates the wizard type that inherits traits from character 
 */

import java.util.ArrayList;
import java.util.List;

public class Wizard extends Character implements CharacterInterface {
    protected ArrayList<Spell> spellList = new ArrayList<Spell>();
    protected int healthPotions;
    protected int lives;

    /**
     * Constructor for Wizard Class
     * @param n, string for name
     */
    public Wizard(String n) {
        super(n, 8);
        healthPotions = 0;
        lives = 3;
    }

    /**
     * Mutators for wizard
     * @param hp, int for health potions
     * @param l, int for lives
     * @param s, Spell for spell
     */
    public void setHealthPotions(int hp) {
        healthPotions = hp;
    }
    public void setLives(int l) {
        lives = l;
    }
    public void addSpell(Spell s) {
        spellList.add(s);
    }

    /**
     * Accessors for wizard
     * @return healthPotions, lives, spellList
     */
    public int getHealthPotions() {
        return healthPotions;
    }
    public int getLives() {
        return lives;
    }
    public List<Spell> getSpellList() {
        return spellList;
    }

    /**
     * Attack method for wizard
     * @param s, Spell for spell
     */
    public boolean attack(Spell s) {
        if (stamina >= s.getStaminaCost()) { //checks to see if wizard has enough mana to cast spell 
            stamina -= s.getStaminaCost();  //if so, subtracts mana cost from mana
            damage = s.getDamage(); //sets damage to damage of spelld
            //System.out.println("You cast " + s.getSpellName() + " dealing " + s.getDamage() + " damage!");
            return true; //returns true if spell is casted
        } else { 
            //throw new StaminaException(); //throws exception if not enough mana
            return false; //returns false if spell is not casted
        }
    }

    /**
     * Stamina regen method for wizard
     */
    public void staminaRegen() {
        if (stamina < 8) {
            stamina += 2;
            if (stamina > 8) { //stamina cannot exceed 8
                stamina = 8;
            }
        }
    }

    /**
     * loseLife method for wizard
     */
    public void loseLife() {
        if (lives > 0) {
            lives -= 1;
            health = 100;
        }
        else if (lives <= 0) {
            lives = 0;
        }
    }

    /**
     * gainHealth methods for wizard
     * @param h, int for health
     */
    public void gainHealth(int h) {
        health += h;
        if (health > 100) { //health cannot exceed 100
            health = 20;
        }
    }

    /**
     * loseHealth method for wizard
     * @param damage, int for damage
     */
    public void loseHealth(int damage) {
        if (health > 0) {
            health -= damage;
        }
        else if (health <= 0) { //if health is less than or equal to 0, wizard loses a life
            health = 0;
            loseLife();
        }
    }

    /**
     * Reset stats method for wizard
     */
    public void resetStats() {
        health = 100;
        stamina = 8;
        healthPotions = 0;
    }

    /**
     * Health potion used method
     * If wizard has health potions, health is increased by 50 and health potions decrease by 1
     */
    public void healthPotion() {
        if (healthPotions > 0) {
            gainHealth(50);
            healthPotions--;
        }
        else {
            //System.out.println("You don't have any health potions!");
        }
    }

    /**
     * Wizard gets hypnotized method
     * If this happens, wizard loses all lives and game ends
     */
    public void getHypnotized() {
        lives = 0;
        if (lives == 0) {
            System.out.println("You have been hypnotized and are now a slave to Vlad! \nGAME OVER!");
            System.exit(0);
        }
    }

    /**
     * toString method for Wizard class
     * @return String representation of Wizard
     */
    public String toString() {
        String wizardString = "";
        wizardString += "Name: " + getName() + "\n";
        wizardString += "Current Health: " + getHealth() + "\n";
        wizardString += "Current Stamina: " + getStamina() + "\n";
        wizardString += "Current Lives: " + getLives() + "\n";
        wizardString += "Health Potions: " + healthPotions + "\n";
        return wizardString;
    }

}