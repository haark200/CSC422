package src.main.java.characters.humans;

import src.main.java.weapons.WeaponCache;

public class Soldier extends Survivor {

    public Soldier(WeaponCache weaponCache) {
        super(100, 10, weaponCache);
    }

    public Soldier(){
        super(100, 10);
    }

    @Override
    public String toString() {
        return "Soldier";
    }
}
