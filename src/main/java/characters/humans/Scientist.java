package src.main.java.characters.humans;

import src.main.java.weapons.WeaponCache;

public class Scientist extends Survivor {
    public Scientist(WeaponCache weaponCache) {
        super(20,2, weaponCache);
    }

    @Override
    public String toString() {
        return "Scientist";
    }
}
