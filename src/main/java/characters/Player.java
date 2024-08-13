package src.main.java.characters;

public class Player {
    private int health;
    private int power;
    public static final boolean DEBUG = false;

    public Player(int health, int power) {
        this.health = health;
        this.power = power;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getPower() {
        return power;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public void attack(Player defender) {
        if (defender.isAlive()) {
            defender.takeDamage(power);
            if (DEBUG) {
                System.out.println("Player " + this + " attacks " + defender);
                if (!defender.isAlive()) {
                    System.out.println(defender + " is dead");
                } else {
                    System.out.println(defender + " has " + defender.getHealth() + " health left");
                }
            }
            
        }
    }
}
