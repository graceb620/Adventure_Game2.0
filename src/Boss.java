/**
 * @author Grace Bero
 * Creates the boss type that inherits traits from character
 */

import java.util.Random; 

public class Boss extends Character implements CharacterInterface {
    protected int bloodlust;
    protected String attackLine;

    /**
     * Constructor for Boss Class
     * @param n, string for name
     */
    public Boss(String n) {
        super(n, 6);
        health = 200;
        bloodlust = 0;
        attackLine = "";
    }

    /** 
     * Mutators for boss
     * @param b, int for bloodlust
     */
    public void setBloodlust(int b) {
        bloodlust = b;
    }

    /**
     * Accessors for boss
     */
    public int getBloodlust() {
        return bloodlust;
    }
    public String getAttackLine() {
        return attackLine;
    }

    /**
     * Boss bloodlust regen method
     */
    public void bloodlustRegen() {
        if (bloodlust < 5) {
            bloodlust += 1;
        }
    }

    /**
     * Stamina Regen for Boss method
     * Regen a random amount of stamina, stamina can't go over 6
     */
    public void staminaRegen() {
        if (stamina < 6) {
            int regenChance = new Random().nextInt(7-stamina);
            stamina += regenChance;
        }
    }

    /**
     * Boss gets attacked and loses health method
     */
    public void loseHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println("You have defeated Vlad!");
        }
    }

    public void gainHealth(int damage) {
        health += damage;
    }


    /**
     * Boss Attack pattern
     * @param w, wizard to be attacked
     */
    public void attack() {
        if (bloodlust == 5) {
            damage = 50;
            bloodlust = 0;
            gainHealth(25);
            attackLine = "Vlad used Bloodlust, dealing 50 dmg and gaining 25 health!";
        } else if (stamina >= 4) {
            stamina -= 4;
            damage = 30;
            attackLine = "Vlad used Blood Cyphon, dealing 30 dmg!";
        } else {
            stamina -= 2;
            damage = 15;
            attackLine = "Vlad used Bite, dealing 15 dmg!";
        }
    }

    /**
     * Vlad gets put to sleep method
     * If this happens, Vlad loses all bloodlust
     */
    public void goSleep() {
        bloodlust = 0;
        attackLine = "Vlad has been put to sleep, he can't attack!";
    }

    /**
     * ToString for boss
     * @return output, string with boss name, health, and bloodlust
     */
    public String toString() {
        String output = "";
        output += "Boss: " + name + "\n";
        output += "Health: " + health + "\n";
        output += "Bloodlust: " + bloodlust + "\n";
        return output;
    }

}

