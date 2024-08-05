/* Imports */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.Math;

/* Zombies and survivor class creations */
class Players <T extends Players<T>> {
    private int currentHealth;
    private int maxHealth;
    private int power;
    private String name;

    public Players(int maxHealth, int power) {
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
        this.power = power;
    }

    public boolean isAlive() {
        return this.currentHealth > 0;
    }

    public int getPower() {
        return power;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getcurrentHealth() {
        return this.currentHealth;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }
}

/* Extensions, creating player classes */
class Zombies extends Players<Zombies> {
    public Zombies(int maxHealth, int power) {
        super(maxHealth, power);
    }
}

class Survivors extends Players <Survivors> {
    public Survivors(int maxHealth, int power) {
        super(maxHealth, power);
    }
}

/* Zombie, common infected */
class CommonInfected extends Zombies {
    public CommonInfected() {
        super(30,5);
    }
}

/* Zombie, tank */
class Tank extends Zombies {
    public Tank() {
        super(150,20);
    }
}

/* Survivor, scientist */
class Scientist extends Survivors {
    public Scientist() {
        super(20,2);
    }
}

/* Survivor, civilian */
class Civilian extends Survivors {
    public Civilian() {
        super(50,5);
    }
}

/* Survivor, soldier */
class Soldier extends Survivors {
    public Soldier() {
        super(100,10);
    }
}

/* Class for war */
class ZombieWar {
    private List<CommonInfected> commonInfecteds = new ArrayList<>();
    private List<Tank> tanks = new ArrayList<>();
    private List<Scientist> scientists = new ArrayList<>();
    private List<Civilian> civilians = new ArrayList<>();
    private List<Soldier> soldiers = new ArrayList<>();

    private List<Zombies> zombies = new ArrayList<>();
    private List<Survivors> survivors = new ArrayList<>();

    public ZombieWar(int numSurvivors, int numZombies) {
        generatePlayers(numSurvivors, numZombies);
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
    private void generatePlayers(int numSurvivors, int numZombies) {
        Random rand = new Random();

        for (int i = 0; i < numSurvivors; ++i) {
            int randomSurvivorType = rand.nextInt(3);  // 0: Child, 1: Teacher, 2: Soldier

            if (randomSurvivorType == 0) {
                scientists.add(new Scientist());
                survivors.add(scientists.get(scientists.size() - 1));
            } else if (randomSurvivorType == 1) {
                civilians.add(new Civilian());
                survivors.add(civilians.get(civilians.size() - 1));
            } else {
                soldiers.add(new Soldier());
                survivors.add(soldiers.get(soldiers.size() - 1));
            }
        }

        for (int i = 0; i < numZombies; ++i) {
            int randomZombieType = rand.nextInt(2);  // 0: CommonInfected, 1: Tank

            if (randomZombieType == 0) {
                commonInfecteds.add(new CommonInfected());
                zombies.add(commonInfecteds.get(commonInfecteds.size() - 1));
            } else {
                tanks.add(new Tank());
                zombies.add(tanks.get(tanks.size() - 1));
            }
        }
        System.out.println("We have " + numSurvivors + " survivors trying to make it to safety. " + "(" + scientists.size() + " scientist, " + civilians.size() + " civilians, " + soldiers.size() + " soilders)");
        System.out.println("But there are " + numZombies + " zombies waiting for them (" + commonInfecteds.size() + " common infected, " + tanks.size() + " tanks)");
    }

    /* Attacks */
    private <T extends Players<T>> void attackPhase(List<? extends Players<?>> attackers, List<? extends Players<?>> defenders) {
        for (Players<?> attacker : attackers) {
            if (!attacker.isAlive()) 
            continue;
            attacker.setName(attacker.getClass().getSimpleName() + " " + attackers.indexOf(attacker));
            for (Players<?> defender : defenders) {
                if (!defender.isAlive()) 
                continue;
                defender.setName(defender.getClass().getSimpleName() + " " + defenders.indexOf(defender));
                attack(attacker, defender);
                if (!attacker.isAlive()) 
                break;
            }
            System.out.println();
        }
    }
    

    /* Prints for attacks */
    private void attack(Players<?> attacker, Players<?> defender) {
        if (!attacker.isAlive() || !defender.isAlive()) 
        return;
        defender.takeDamage(attacker.getPower());
        System.out.println(attacker.getName() + " has attacked and dealt " + attacker.getPower() + " damage to " + defender.getName() + " " + defender.getcurrentHealth() + "/" + defender.getMaxHealth() + " health remaining");
        if (!defender.isAlive()) {
            System.out.println(attacker.getName() + " has killed " + defender.getName());
        }
    }
    

    public void displayHPRemaning (List<? extends Players<?>> players) {
        for (Players<?> player : players) {
            System.out.println(player.getName() + ": " + player.getcurrentHealth() + "/" + player.getMaxHealth() + " health remaining");
        }
    }

    /* Check if zombies are alive */
    private boolean allZombiesDead() {
        for (Zombies zombie : zombies) {
            if (zombie.isAlive()) {
                return false;
            }
        }
        return true;
    }

    /* Check if survivors are alive */
    private boolean allSurvivorsDead() {
        for (Survivors survivor : survivors) {
            if (survivor.isAlive()) {
                return false;
            }
        }
        return true;
    }

    /* Print survivors */
    private void results() {
        int numSurvivorsMadeIt = 0;
        for (Survivors survivor : survivors) {
            if (survivor.isAlive()) {
                numSurvivorsMadeIt++;
            }
        }

        System.out.println("Initial Survivors: " + survivors.size());
        System.out.println("Initial Zombies: " + zombies.size());
        
        if (numSurvivorsMadeIt > 0) {
            displayHPRemaning(survivors);
            System.out.println("\nIt seems " + numSurvivorsMadeIt + " have made it to safety");
        } else {
            displayHPRemaning(zombies);
            System.out.println("\nNone of the survivors made it.");
        }
    }
}

/* Simulate */
public class Main {
    public static void main(String[] args) {
        ZombieWar apocalypse = new ZombieWar((int) (Math.random() * (20 - 1) + 1), (int) (Math.random() * (10 - 1) + 1));
        apocalypse.simulate();
    }
}

//Math.Random Code used from https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/random to generate random integers