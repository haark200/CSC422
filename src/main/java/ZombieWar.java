package src.main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.main.java.characters.Player;
import src.main.java.characters.PlayerPool;
import src.main.java.characters.humans.*;
import src.main.java.characters.zombies.*;
import src.main.java.weapons.Weapon;
import src.main.java.weapons.WeaponCache;

public class ZombieWar {
    private List<Zombie> zombies = new ArrayList<>();
    private List<Survivor> survivors = new ArrayList<>();
    private int soldiers;
    private int scientists;
    private int civilians;
    private int commonInfecteds;
    private int tanks;

    public ZombieWar(int numSurvivors, int numZombies, int numWeapons) {
        generatePlayers(numSurvivors, numZombies, numWeapons);
    }

    public int getSurvivorCount() {
        return survivors.size();
    }

    public int getZombieCount() {
        return zombies.size();
    }

    /* Simulate attacks metheod */
    public void simulate() {
        int turnCounter = 0;
        while (!allSurvivorsDead() && !allZombiesDead()) {
            System.out.println("Turn: " + turnCounter);
            attackPhase(survivors, zombies);
            attackPhase(zombies, survivors);
            if (!allSurvivorsDead() && !allSurvivorsDead()) {
                displayHPRemaning(survivors);
                displayHPRemaning(zombies);
                System.out.println();
            }
            turnCounter++;
        }
        results();
    }

    /* Generate players */
    private void generatePlayers(int numSurvivors, int numZombies, int numberOfWeapons) {
        scientists = 0;
        soldiers = 0;
        civilians = 0;
        tanks = 0;
        commonInfecteds = 0;
        
        Random rand = new Random();
        WeaponCache weaponCache = new WeaponCache(numberOfWeapons);

        for (int i = 0; i < numSurvivors; ++i) {
            int randomSurvivorType = rand.nextInt(3);  // 0: Child, 1: Teacher, 2: Soldier

            if (randomSurvivorType == 0) {
                survivors.add(new Scientist(weaponCache));
            } else if (randomSurvivorType == 1) {
                survivors.add(new Civilian(weaponCache));
            } else {
                survivors.add(new Soldier(weaponCache));
            }
        }

        for (int i = 0; i < numZombies; ++i) {
            int randomZombieType = rand.nextInt(2);  // 0: CommonInfected, 1: Tank

            if (randomZombieType == 0) {
                zombies.add(new CommonInfected());
            } else {
                zombies.add(new Tank());
            }
        }

        for (Survivor survivor : survivors) {
            if (survivor instanceof Civilian){
                civilians++;
            } else if (survivor instanceof Soldier) {
                soldiers++;
            } else scientists++;
        }

        for (Zombie zombie : zombies) {
            if (zombie instanceof Tank) {
                tanks++;
            } else commonInfecteds++;
        }
        System.out.println("We have " + numSurvivors + " survivors trying to make it to safety. " + "(" + scientists + " scientist, " + civilians + " civilians, " + soldiers + " soilders)");
        System.out.println("But there are " + numZombies + " zombies waiting for them (" + commonInfecteds+ " common infected, " + tanks + " tanks)");
    }

    /* Attacks */
    private void attackPhase(List<? extends Player> attackers, List<? extends Player> defenders) {
        for (Player attacker : attackers) {
            if (!attacker.isAlive()) 
            continue;
            attacker.setName(attacker.getClass().getSimpleName() + " " + attackers.indexOf(attacker));
            for (Player defender : defenders) {
                if (!defender.isAlive()) 
                continue;
                defender.setName(defender.getClass().getSimpleName() + " " + defenders.indexOf(defender));
                if (attacker instanceof Survivor) {
                ((Survivor) attacker).attack(attacker, defender);
                } else attacker.attack(attacker, defender);
                if (!attacker.isAlive()) 
                break;
            }
            System.out.println();
        }
    }
    

    public void displayHPRemaning (List<? extends Player> players) {
        System.out.printf("%20s %10s\n","Name", "HP");
        for (Player player : players) {
            System.out.printf("%20s %10s\n",player.getName(),(player.getcurrentHealth() + "/" + player.getMaxHealth()));
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
        int soilders = 0;
        int scientists = 0;
        int civilians = 0;

        for (Survivor survivor : survivors) {
            if (survivor.isAlive()) {
                numSurvivorsMadeIt++;
            }
        }

        System.out.println("Initial Survivors: " + survivors.size());
        System.out.println("Initial Zombies: " + zombies.size());
        
        if (numSurvivorsMadeIt > 0) {
            displayHPRemaning(survivors);
            System.out.println("\nIt seems " + numSurvivorsMadeIt + "survivors have made it to safety");
        } else {
            displayHPRemaning(zombies);
            System.out.println("\nNone of the survivors made it.");
        }
    }
}