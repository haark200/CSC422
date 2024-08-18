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

    public boolean takeDamage(Weapon weapon) {
        boolean damageTaken = false;
        if (weapon.use()) {
            health -= weapon.getDamage();
            damageTaken = true;
        }
        if (health < 0) {
            health = 0;
        }
        return damageTaken;
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void attack(Player defender) {
        if (defender.isAlive()) {
            boolean tookDamage = defender.takeDamage(this.weapon);
            if (DEBUG) {
                if (tookDamage) {
                    System.out.println("\t\t" + this.name + " attacks " + defender.name + " with a(n) " + this.weapon + ": " + defender.name + " has " + defender.health + " health left");
                } else {
                    System.out.println("\t\t" + this.name + " has missed with the " + this.weapon + " against " + defender.name);
                }
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
