package src.main.java.characters.humans;

import src.main.java.weapons.Fists;

public class Soldier extends Survivor {
    public Soldier(int id) {
        super(100, new Fists((10)), "Soldier " + id);
    }

    @Override
    public String toString() {
        return "Soldier";
    }
}
