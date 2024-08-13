package src.main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.main.java.characters.Player;
import src.main.java.characters.PlayerPool;
import src.main.java.characters.humans.*;
import src.main.java.characters.zombies.*;
import src.main.java.weapons.WeaponCache;

public class ZombieWar {
    private PlayerPool zombies = new PlayerPool();
    private PlayerPool survivors = new PlayerPool();
    private WeaponCache weapons;

    public ZombieWar(int numSurvivors, int numZombies, int numWeapons) {
        weapons = new WeaponCache(numWeapons);
        generatePlayers(numSurvivors, numZombies);
    }

    /* Simulate attacks metheod */
    public void simulate() {
        while (!allSurvivorsDead() && !allZombiesDead()) {
            // Survivors attack first
            attackPhase(survivors.getAlive(), zombies.getAlive());

            // Zombies attack second
            attackPhase(zombies.getAlive(), survivors.getAlive());
        }
        results();
    }

    /* Generate players */
    private void generatePlayers(int numSurvivors, int numZombies) {
        Random rand = new Random();

        for (int i = 0; i < numSurvivors; ++i) {
            int randomSurvivorType = rand.nextInt(3); // 0: Child, 1: Teacher, 2: Soldier

            if (randomSurvivorType == 0) {
                survivors.addPlayer(new Scientist());
            } else if (randomSurvivorType == 1) {
                survivors.addPlayer(new Civilian());
            } else {
                survivors.addPlayer(new Soldier());
            }
            // Give each survivor a weapon
            if (weapons.getNumberOfWeapons() > 0) {
                int randomWeaponIndex = rand.nextInt(weapons.getNumberOfWeapons());
                Survivor survivor = (Survivor) survivors.getAlive().get(i);
                survivor.equipWeapon(weapons.getWeapon(randomWeaponIndex));
            }
        }

        for (int i = 0; i < numZombies; ++i) {
            int randomZombieType = rand.nextInt(2); // 0: CommonInfected, 1: Tank

            if (randomZombieType == 0) {
                zombies.addPlayer(new CommonInfected());
            } else {
                zombies.addPlayer(new Tank());
            }
        }
    }

    /* Attacks */
    private void attackPhase(List<? extends Player> attackers, List<? extends Player> defenders) {
        List<Player> dead = new ArrayList<>();
        for (Player attacker : attackers) {
            for (Player defender : defenders) {
                attacker.attack(defender);
                if (!defender.isAlive()) {
                    if (!dead.contains(defender)) {
                        dead.add(defender);
                    }
                }
            }

        }

        for (Player player : dead) {
            if (player instanceof Survivor) {
                survivors.removePlayer(player);
            } else {
                zombies.removePlayer(player);
            }
        }
    }

    /* Check if zombies are alive */
    private boolean allZombiesDead() {
        if (zombies.getAlive().size() == 0) {
            return true;
        }
        return false;
    }

    /* Check if survivors are alive */
    private boolean allSurvivorsDead() {
        if (survivors.getAlive().size() == 0) {
            return true;
        }
        return false;
    }

    /* Print survivors */
    private void results() {
        int numSurvivorsMadeIt = survivors.getAlive().size();
        int survivorTotal = survivors.getAlive().size() + survivors.getDead().size();
        int zombieTotal = zombies.getAlive().size() + zombies.getDead().size();        

        System.out.println("We have " + survivorTotal + " survivor(s) trying to make it to safety.");
        System.out.println("But there are " + zombieTotal + " zombie(s) waiting for them.");

        if (numSurvivorsMadeIt > 0) {
            System.out.println("It seems " + numSurvivorsMadeIt + " have made it to safety.");
        } else {
            System.out.println("None of the survivors made it.");
        }
    }
}
