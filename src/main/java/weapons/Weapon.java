package src.main.java.weapons;
public class Weapon {
    private int damage;
    private int accuracy;

    public Weapon(int damage, int accuracy) {
        this.damage = damage;
        this.accuracy = accuracy;
    }

    public int getDamage() {
        return damage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public boolean use() {
        if (this.accuracy >= 100) {
            return true;
        } else {
            int random = (int) (Math.random() * 100);
            if (random <= this.accuracy) {
                return true;
            }
        }
        return false;
    }
}
