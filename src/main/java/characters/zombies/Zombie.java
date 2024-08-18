package src.main.java.characters.zombies;

import src.main.java.characters.Player;
import src.main.java.weapons.Weapon;

public class Zombie extends Player {
    private Weapon weaponThatKilled = null;

    public Zombie(int health, Weapon weapon, String name) {
        super(health, weapon, name);
    }

    @Override
    public void equipWeapon(Weapon weapon) throws UnsupportedOperationException {
        // Zombies can't equip weapons
        throw new UnsupportedOperationException("Zombies can't equip weapons");
    }

    public Weapon getWeaponThatKilled() {
        return this.weaponThatKilled;
    }

    public void setWeaponThatKilled(Weapon weapon) {
        this.weaponThatKilled = weapon;
    }
}
