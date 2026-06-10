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
    //relicloader isnt ready yet so uh yeah
    private int allrelicssize = 0;

    public Shop(Player p) {
        allAmmo = p.getAllAmmo();
        int totalSize = allrelicssize + allAmmo.length;
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
        int totalSize = allrelicssize + this.allAmmo.length; 
        for (int i = 0; i < this.SLOT_NUMBER; i++) {
            int index = (int)(Math.random() * totalSize);
            if (index <= allrelicssize-1) {
                //implement relic load later
            } else {
                this.stock[i] = this.allAmmo[index-allrelicssize].clone();
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
