/**
 * @author Grace Bero 
 * Creates a character class to be inherited from 
 */

 public class Character {
    protected String name;
    protected int health;
    protected int stamina;
    protected int damage;

    /**
     * Constructor for Character Class
     * @param n, string for name
     * @param s, int for stamina
     * @param d, int for damage
     */
     public Character(String n, int s) {
        name = n;
        stamina = s;
        health = 100;
        damage = 0;
     }

     /**
      * Mutators for name, health, and stamina
        * @param n, string for name
        * @param h, int for health
        * @param s, int for stamina
      */
        public void setName(String n) {
            name = n;
        }
        public void setStamina ( int s){
            stamina = s;
        }
        public void setHealth ( int h){
            health = h;
        }
        public void setDamage ( int d){
            damage = d;
        }

    /**
     * Accessors for name, health, and stamina
     * @return name, health, stamina
     */
        public String getName () {
            return name;
        }
        public int getStamina () {
            return stamina;
        }
        public int getHealth () {
            return health;
        }
        public int getDamage () {
            return damage;
        }
    /**
     * toString method for Character class
     * @return characterString, string with the character name, health, and stamina
     */
        public String toString () {
            String characterString = "Name: " + name + "\nHealth: " + health + "\nStamina: " + stamina;
            return characterString;
        }
 }
