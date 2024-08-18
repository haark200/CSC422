package src.main.java.characters.humans;

import src.main.java.weapons.Fists;

public class Scientist extends Survivor {
    public Scientist(int id) {
        super(20, new Fists(2), "Scientist " + id);
    }

    @Override
    public String toString() {
        return "Scientist";
    }
}
