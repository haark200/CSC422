package src.main.java.characters.humans;

import src.main.java.characters.Player;
import src.main.java.weapons.Weapon;

public class Survivor extends Player {
    private Weapon weapon = null;

    public Survivor(int health, int power) {
        super(health, power);
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void attack(Player defender) {
        if (defender.isAlive()) {
            if (weapon != null) {
                weapon.use(defender);
            } else {
                defender.takeDamage(getPower());
            }
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
