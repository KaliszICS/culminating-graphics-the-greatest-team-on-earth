package theGame;

import java.util.*;

/**
 * Abstract base class representing a weapon in the game.
 * A weapon has a name, ammunition capacity, reload speed, and a cartridge containing loaded ammunition. 
 * Concrete weapon types must implement shooting, reloading, and special ability behavior.
 * @Author Eric Wang
 */
public abstract class Weapon {

    /** The name of the weapon. */
    protected String name;

    /** The maximum amount of ammunition the weapon can hold. */
    protected int capacity;

    /** The reload speed of the weapon. */
    protected int reloadSpd;

    /** The ammunition currently loaded in the weapon. */
    protected ArrayList<Ammo> cartridge = new ArrayList<Ammo>();

    /**
     * Fires the weapon and removes the appropriate ammunition from the cartridge.
     *
     * @return the Ammo object that was fired, or null if the weapon could not shoot
     */
    abstract Ammo shoot();

    /**
     * Activates the weapon's special ability
     */
    abstract void special();

    /**
     * Reloads the weapon using ammunition from the reserve supply.
     *
     * @param reserve the available reserve ammunition to pull from
     * @return true if the reload was successful; false otherwise
     */
    abstract boolean reload(ArrayList<Ammo> reserve);

    /**
     * Returns the name of the weapon.
     *
     * @return the weapon name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the ammunition capacity of the weapon.
     *
     * @return the weapon capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Returns the reload speed of the weapon.
     *
     * @return the reload speed
     */
    public int getReloadSpd() {
        return this.reloadSpd;
    }

    /**
     * Returns the ammunition currently loaded in the weapon.
     *
     * @return the cartridge containing loaded ammunition
     */
    public ArrayList<Ammo> getCartridge() {
        return this.cartridge;
    }

    /**
     * Sets the weapon name.
     *
     * @param name the new weapon name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the weapon capacity.
     *
     * @param capacity the new ammunition capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Sets the weapon reload speed.
     *
     * @param reloadSpd the new reload speed
     */
    public void setReloadSpd(int reloadSpd) {
        this.reloadSpd = reloadSpd;
    }
}