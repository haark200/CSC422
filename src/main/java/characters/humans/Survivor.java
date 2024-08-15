package src.main.java.characters.humans;

import java.util.ArrayList;

import src.main.java.ZombieWar;
import src.main.java.characters.Player;
import src.main.java.weapons.AssualtRifle;
import src.main.java.weapons.Axe;
import src.main.java.weapons.Crowbar;
import src.main.java.weapons.FryingPan;
import src.main.java.weapons.Pistol;
import src.main.java.weapons.Shotgun;
import src.main.java.weapons.SubmachineGun;
import src.main.java.weapons.Weapon;
import src.main.java.weapons.WeaponCache;

public class Survivor extends Player {
    private Weapon weapon;
    private double accuracy;

    public Survivor(int health, int power) {
        super(health, power);
    }

    public Survivor(int health, int power, WeaponCache weaponCache) {
        super(health, power);
        this.weapon = weaponCache.takeWeapon();
        if (this.weapon != null) {
            
        this.setPower(weapon.getDamage());
        this.accuracy = (weapon.getAccuracy()/100);
        } else this.accuracy = 1.00;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }


    @Override
    /* Prints for attacks */
    public void attack(Player attacker, Player defender) {
        if (!attacker.isAlive() || !defender.isAlive()) 
        return;
        if (this.accuracy >= Math.random()) {
            defender.takeDamage(attacker.getPower());
            System.out.println(attacker.getName() + " has attacked with a " + this.getWeapon() + " and dealt " + attacker.getPower() + " damage to " + defender.getName() + " " + defender.getcurrentHealth() + "/" + defender.getMaxHealth() + " health remaining");
            if (!defender.isAlive()) {
                System.out.println(attacker.getName() + " has killed " + defender.getName());
            } 
        } else 
            System.out.println(  attacker.getName() + " has missed the target with a " + this.getWeapon());
    }
}
