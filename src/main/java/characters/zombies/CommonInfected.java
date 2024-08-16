package src.main.java.characters.zombies;

import src.main.java.weapons.Fists;

public class CommonInfected extends Zombie {
    public CommonInfected(int id) {
        super(30, new Fists((5)), "Common Infected " + id);
    }

    @Override
    public String toString() {
        return "Common Infected";
    }
}
