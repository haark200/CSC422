package src.main.java.characters.zombies;

import src.main.java.characters.Player;
import src.main.java.weapons.Weapon;

public class Zombie extends Player {
    private Weapon weaponThatKilled;
    public Zombie(int health, int power) {
        super(health, power);
        weaponThatKilled = null;
    }

    public Weapon getweaponThatKilled() {
        return this.weaponThatKilled;
    }

    public void setWeaponThatKilled(Weapon weapon) {
        this.weaponThatKilled = weapon;
    }
}
