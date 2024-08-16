package src.main.java.characters.humans;

import src.main.java.weapons.Fists;

public class Civilian extends Survivor {
    public Civilian(int id) {
        super(50,new Fists(5), "Civilian " + id);
    }

    @Override
    public String toString() {
        return "Civilian";
    }
}
