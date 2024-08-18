package src.main.java.weapons;

public class Fists extends Weapon {
    public Fists(int damage) {
        super(damage, 100);
    }

    @Override
    public String toString() {
        return "Fists";
    }
}
