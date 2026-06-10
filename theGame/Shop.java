package theGame;

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
        refresh();
    }
    //pass = xEavvjty5xLczcF

    public void refresh() {
        int totalSize = allrelicssize + allAmmo.length; 
        for (int i = 0; i < this.SLOT_NUMBER; i++) {
            int index = (int)(Math.random() * totalSize);
            if (index <= allrelicssize-1) {
                //implement relic load later
            } else {
                stock[i] = allAmmo[index-allrelicssize].clone();
            }
        }
    }
}
