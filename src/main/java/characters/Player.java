package src.main.java.characters;

public class Player {
    private int health;
    private int power;

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

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
}
