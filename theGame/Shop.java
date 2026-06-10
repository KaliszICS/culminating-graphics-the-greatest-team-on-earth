package theGame;

/**
 * A class that represents the shop a player can buy items from
 * The shop holds 4 items, and can be refreshed.
 * @author Eric Wang
 */
public class Shop {
    private int refreshNo = 0;
    private final int SLOT_NUMBER = 4;
    private Item[] stock;
    private Ammo[] allAmmo;
    private Relic[] allRelic;
    //relicloader isnt ready yet so uh yeah

    public Shop(Player p) {
        this.allAmmo = p.getAllAmmo();
        this.allRelic = p.getAllRelic();
        this.stock = new Item[this.SLOT_NUMBER];
        this.refresh(p);
    }

    /**
     * Refreshes the shop, costing money proportional to the number of refreshes
     * 
     * @param p the player character
     */
    public void refresh(Player p) {
        p.changeMoney(-this.refreshNo*10);
        this.refreshNo++;
        int totalSize = this.allRelic.length + this.allAmmo.length; 
        for (int i = 0; i < this.SLOT_NUMBER; i++) {
            int index = (int)(Math.random() * totalSize);
            if (index <= this.allRelic.length-1) {
                this.stock[i] = this.allRelic[index];
            } else {
                this.stock[i] = this.allAmmo[index-allRelic.length].clone();
            }
        }
    }

    public Item[] getStock() {
        return this.stock;
    }

    public int getRefreshNumber() {
        return this.refreshNo*10;
    }
}
