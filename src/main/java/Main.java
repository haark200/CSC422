package src.main.java;

public class Main {
    public static void main(String[] args) {
        int survivors = (int) (Math.random() * (20 - 1) + 1);
        int zombies =  (int) (Math.random() * (10 - 1) + 1);
        int weapons = survivors;
        ZombieWar apocalypse = new ZombieWar(survivors, zombies, weapons);
        apocalypse.simulate();
    }
}