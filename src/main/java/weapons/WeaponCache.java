package src.main.java.weapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeaponCache {
    private List<Weapon> weapons = new ArrayList<Weapon>();

    public WeaponCache(int numberOfWeapons) {
        generateWeapons(numberOfWeapons);
    }

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
             */
            int randomWeaponType = rand.nextInt(7);

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
                default:
                    break;
            }
        }
    }

    public int getNumberOfWeapons() {
        return weapons.size();
    }

    public Weapon getWeapon(int weaponIndex) {
        // Remove and return the weapon at the specified index
        return weapons.remove(weaponIndex);
    }
}
