package src.main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.main.java.characters.Player;
import src.main.java.characters.humans.*;
import src.main.java.characters.zombies.*;

public class ZombieWar {
    private List<Zombie> zombies = new ArrayList<>();
    private List<Survivor> survivors = new ArrayList<>();

    public ZombieWar(int numSurvivors, int numZombies) {
        generatePlayers(numSurvivors, numZombies);
    }

    /* Simulate attacks metheod */
    public void simulate() {
        while (!allSurvivorsDead() && !allZombiesDead()) {
            attackPhase(survivors, zombies);
            attackPhase(zombies, survivors);
        }
        results();
    }

    /* Generate players */
    private void generatePlayers(int numSurvivors, int numZombies) {
        Random rand = new Random();

        for (int i = 0; i < numSurvivors; ++i) {
            int randomSurvivorType = rand.nextInt(3); // 0: Child, 1: Teacher, 2: Soldier

            if (randomSurvivorType == 0) {
                survivors.add(new Scientist());
            } else if (randomSurvivorType == 1) {
                survivors.add(new Civilian());
            } else {
                survivors.add(new Soldier());
            }
        }

        for (int i = 0; i < numZombies; ++i) {
            int randomZombieType = rand.nextInt(2); // 0: CommonInfected, 1: Tank

            if (randomZombieType == 0) {
                zombies.add(new CommonInfected());
            } else {
                zombies.add(new Tank());
            }
        }
    }

    /* Attacks */
    private void attackPhase(List<? extends Player> attackers, List<? extends Player> defenders) {
        for (Player attacker : attackers) {
            for (Player defender : defenders) {
                attack(attacker, defender);
            }
        }
    }

    /* Prints for attacks */
    private void attack(Player attacker, Player defender) {
        if (defender.isAlive()) {
            defender.takeDamage(attacker.getPower());
        }
    }

    /* Check if zombies are alive */
    private boolean allZombiesDead() {
        for (Zombie zombie : zombies) {
            if (zombie.isAlive()) {
                return false;
            }
        }
        return true;
    }

    /* Check if survivors are alive */
    private boolean allSurvivorsDead() {
        for (Survivor survivor : survivors) {
            if (survivor.isAlive()) {
                return false;
            }
        }
        return true;
    }

    /* Print survivors */
    private void results() {
        int numSurvivorsMadeIt = 0;
        for (Survivor survivor : survivors) {
            if (survivor.isAlive()) {
                ++numSurvivorsMadeIt;
            }
        }

        System.out.println("We have " + survivors.size() + " survivors trying to make it to safety.");
        System.out.println("But there are " + zombies.size() + " zombies waiting for them.");

        if (numSurvivorsMadeIt > 0) {
            System.out.println("It seems " + numSurvivorsMadeIt + " have made it to safety.");
        } else {
            System.out.println("None of the survivors made it.");
        }
    }
}
