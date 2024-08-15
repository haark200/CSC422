package src.main.java.characters;

import java.util.List;

// Player type must be a subclass of player
public class Player {
    private int currentHealth;
    private int maxHealth;
    private int power;
    private String name;

    public Player(int maxHealth, int power) {
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

    public void setPower(int power) {
        this.power = power;
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

    public void displayHPRemaning (List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getcurrentHealth() + "/" + player.getMaxHealth() + " health remaining");
        }
    }

    /* Prints for attacks */
    public void attack(Player attacker, Player defender) {
        if (!attacker.isAlive() || !defender.isAlive()) 
        return;
        defender.takeDamage(attacker.getPower());
        System.out.println(attacker.getName() + " has attacked and dealt " + attacker.getPower() + " damage to " + defender.getName() + " " + defender.getcurrentHealth() + "/" + defender.getMaxHealth() + " health remaining");
        if (!defender.isAlive()) {
            System.out.println(attacker.getName() + " has killed " + defender.getName());
        }
    }
}