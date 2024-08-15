package src.main.java.characters.humans;

import src.main.java.weapons.WeaponCache;

public class Civilian extends Survivor {
    public Civilian(WeaponCache weaponCache) {
        super(50,5, weaponCache);
    }

    @Override
    public String toString() {
        return "Civilian";
    }
}
