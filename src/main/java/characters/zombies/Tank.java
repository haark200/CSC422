package src.main.java.characters.zombies;

import src.main.java.weapons.Fists;

public class Tank extends Zombie {
    public Tank(int id) {
        super(150, new Fists(20), "Tank " + id);
    }

    @Override
    public String toString() {
        return "Tank";
    }
}
