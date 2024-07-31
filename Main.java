/* Imports */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* Zombies and survivor class creations */
class Players {
    private int health;
    private int power;

    public Players(int health, int power) {
        this.health = health;
        this.power = power;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getPower() {
        return power;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
}

/* Extensions, creating player classes */
class Zombies extends Players {
    public Zombies(int health, int power) {
        super(health, power);
    }
}

class Survivors extends Players {
    public Survivors(int health, int power) {
        super(health, power);
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
    }

    /* Attacks */
    private void attackPhase(List<? extends Players> attackers, List<? extends Players> defenders) {
        for (Players attacker : attackers) {
            for (Players defender : defenders) {
                attack(attacker, defender);
            }
            System.out.println();
        }
    }

    /* Prints for attacks */
    private void attack(Players attacker, Players defender) {
        if (defender.isAlive()) {
            defender.takeDamage(attacker.getPower());
            System.out.println(attacker.getPower() + " damage dealt.");
            if (!defender.isAlive()) {
                System.out.println("Defender killed.");
            }
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
                ++numSurvivorsMadeIt;
            }
        }

        System.out.println("Initial Survivors: " + survivors.size());
        System.out.println("Initial Zombies: " + zombies.size());
        
        if (numSurvivorsMadeIt > 0) {
            System.out.println("Survivors left: " + numSurvivorsMadeIt);
        } else {
            System.out.println("None of the survivors made it.");
        }
    }
}

/* Simulate */
public class Main {
    public static void main(String[] args) {
        ZombieWar apocalypse = new ZombieWar(2, 1);
        apocalypse.simulate();
    }
}