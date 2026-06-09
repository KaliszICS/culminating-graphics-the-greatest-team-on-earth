package theGame;
import java.util.*;

public abstract class Weapon {
    protected String name;
    protected int capacity;
    protected int reloadSpd;
    protected ArrayList<Ammo> cartridge = new ArrayList<Ammo>();

    abstract Ammo shoot();
    abstract void special();
    abstract boolean reload(ArrayList<Ammo> reserve);

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getReloadSpd() {
        return this.reloadSpd;
    }

    public ArrayList<Ammo> getCartridge() {
        return this.cartridge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setReloadSpd(int reloadSpd) {
        this.reloadSpd = reloadSpd;
    }
}
