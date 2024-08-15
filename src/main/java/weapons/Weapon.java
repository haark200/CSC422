package src.main.java.weapons;

import src.main.java.characters.Player;

public class Weapon {
    private int damage;
    private int accuracy;

    public Weapon(int damage, int accuracy) {
        this.damage = damage;
        this.accuracy = accuracy;
    }

    public int getDamage() {
        return damage;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void use(Player defender) {
        if (defender.isAlive()) {
            if (this.accuracy >= 100) {
                defender.takeDamage(this.damage);
            } else {
                int random = (int) (Math.random() * 100);
                if (random <= this.accuracy) {
                    defender.takeDamage(this.damage);
                }
            }
        }
    }
}
