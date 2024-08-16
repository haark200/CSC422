package src.main.java.characters;

import src.main.java.characters.zombies.Zombie;
import src.main.java.weapons.Weapon;

public class Player {
    protected int health;
    protected Weapon weapon;
    protected boolean DEBUG = false;
    protected String name;

    public Player(int health, Weapon weapon, String name) {
        this.health = health;
        this.weapon = weapon;
        this.name = name;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getPower() {
        return weapon.getDamage();
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(Weapon weapon) {
        if (weapon.use()) {
            health -= weapon.getDamage();
        }
        if (health < 0) {
            health = 0;
        }
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void attack(Player defender) {
        if (defender.isAlive()) {
            defender.takeDamage(this.weapon);
            if (DEBUG) {
                System.out.println("\t\t" + this.name + " attacks " + defender.name + " with a(n) " + this.weapon + ": " + defender.name + " has " + defender.health + " health left");
            }
            if (!defender.isAlive()) {
                if (defender instanceof Zombie) {
                    ((Zombie) defender).setWeaponThatKilled(this.weapon);
                    System.out.println("\t" + this.name + " killed " + defender.name + " with a(n) " + this.weapon);
                } else {
                    System.out.println("\t" + this.name + " killed " + defender.name);
                }
                
            }
        }
    }
}
