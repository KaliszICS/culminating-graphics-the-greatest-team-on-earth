package theGame;

import java.util.*;

/**
 * A basic weapon implementation that serves as the default weapon.
 * The DefaultWeapon has:
 * A capacity of 6 rounds
 * A reload speed of 120 frames
 * A special ability that repositions a fired round within the cartridge
 * The weapon is automatically loaded from the provided reserve ammunition upon creation.
 * @author Eric Wang
 */
class DefaultWeapon extends Weapon {

    /**
     * Constructs a DefaultWeapon and loads it with ammunition from the reserve.
     *
     * @param reserve the reserve ammunition used to initially load the weapon
     */
    public DefaultWeapon(ArrayList<Ammo> reserve) {
        super.setName("Original");
        super.setCapacity(6);
        super.setReloadSpd(120);
        this.reload(reserve);
    }

    /**
     * Fires the first round currently loaded in the cartridge.
     *
     * @return the Ammo object that was fired
     */
    @Override
    public Ammo shoot() {
        return super.getCartridge().remove(0);
    }

    /**
     * Activates the weapon's special ability.
     * This ability fires a round reinserts it as the second slot, effectively moving the bullet one slot down
     */
    @Override
    public void special() {
        super.getCartridge().add(
            Math.min(super.getCartridge().size() - 1, 1),
            this.shoot()
        );
    }

    /**
     * Reloads the weapon from the reserve ammunition until the cartridge reaches capacity or the reserve is exhausted.
     * @param reserve the reserve ammunition supply
     * @return true if the weapon reached full capacity; false if the reserve ran out before the weapon was full
     */
    @Override
    public boolean reload(ArrayList<Ammo> reserve) {
        for (int i = super.getCartridge().size();
             i < super.getCapacity() && reserve.size() > 0; i++) {
            super.getCartridge().add(reserve.remove(0));
        }
        return !(reserve.size() == 0&& super.getCartridge().size() != super.getCapacity());
    }
}