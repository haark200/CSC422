package src.main.java.weapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeaponCache {
    private List<Weapon> weapons = new ArrayList<Weapon>();

    public WeaponCache(int numberOfWeapons) {
        generateWeapons(numberOfWeapons);
    }

    /*
     * Generate a random number of weapons and add them to the cache
     * @param numberOfWeapons
     */
    private void generateWeapons(int numberOfWeapons) {
        Random rand = new Random();
        for (int i = 0; i < numberOfWeapons; i++) {
            /*
             * 0: FryingPan
             * 1: Pistol
             * 2: Shotgun
             * 3: SubmachineGun
             * 4: AssualtRifle
             * 5: Axe
             * 6: Crowbar
             * 7: RocketLauncher
             */
            int randomWeaponType = rand.nextInt(8);

            switch (randomWeaponType) {
                case 0:
                    weapons.add(new FryingPan());
                    break;
                case 1:
                    weapons.add(new Pistol());
                    break;
                case 2:
                    weapons.add(new Shotgun());
                    break;
                case 3:
                    weapons.add(new SubmachineGun());
                    break;
                case 4:
                    weapons.add(new AssualtRifle());
                    break;
                case 5:
                    weapons.add(new Axe());
                    break;
                case 6:
                    weapons.add(new Crowbar());
                    break;
                case 7:
                    weapons.add(new RocketLauncher());
                    break;
                default:
                    break;
            }
        }
    }

    /*
     * Get the number of weapons in the cache
     * @return int
     */
    public int getNumberOfWeapons() {
        return weapons.size();
    }

    /*
     * Randomly select a weapon from the cache and remove it
     * @return Weapon
     */
    public Weapon takeWeapon() {
        Random rand = new Random();
        return weapons.remove(rand.nextInt(weapons.size()));
    }
}
