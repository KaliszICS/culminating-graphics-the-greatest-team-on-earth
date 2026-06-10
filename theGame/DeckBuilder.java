package theGame;

import java.util.*;
/**A utility class with preloaded decks for easy use
 * @author Eric Wang
 */
public class DeckBuilder {

    /**A basic starter deck, archaic implementation
     * 
     * @return the starter deck with a variety of simple ammos (and one legendary)
     */
    public static ArrayList<Ammo> starterDeck() {
        Ammo[] stuff = ItemLoader.loadAll();
        ArrayList<Ammo> deck = new ArrayList<Ammo>();
        deck.add(stuff[0]);
        deck.add(new RegularAmmo(5, 15, -400, "Cd_-10", 10, 0, 20, 0));
        deck.add(new RegularAmmo(5, 15, -400, "Cd_-10", 10, 0, 20, 0));
        deck.add(new RegularAmmo(5, 15, 100, "Dmg_+5", 10, 30, 20, 0));
        deck.add(new RegularAmmo(15, 30, 400, "Dmg_+15", 30, 30, 50, 3));
        deck.add(new RegularAmmo(5, 30, 25, "Dmg_+5", 5, 10, 10, 0));
        deck.add(new RegularAmmo(5, 30, 25, "Dmg_+5", 5, 10, 10, 0));
        deck.add(new RegularAmmo(5, 30, 25, "Dmg_+5", 5, 10, 10, 0));
        deck.add(new RegularAmmo(5, 15, 100, "Shoot_Exhaust", 10, 30, 20, 0));
        deck.add(new RegularAmmo(5, 15, 100, "Shoot_Exhaust", 10, 30, 20, 0));
        deck.add(new RegularAmmo(5, 15, 100, "Shoot_Exhaust", 10, 30, 20, 0));
        deck.add(new RegularAmmo(5, 15, 100, "Shoot_Exhaust", 10, 30, 20, 0));
        Collections.shuffle(deck);
        return deck;
    }

    /**a test deck
     * 
     * @return the test deck
     */
    public static ArrayList<Ammo> idkIWasBored() {
        ArrayList<Ammo> deck = new ArrayList<Ammo>();
        Ammo[] stuff = ItemLoader.loadAll();
        deck.add(stuff[1]);
        return deck;
    }

    /**The only up to date deck with real implemented methods
     * 
     * @param ammopool the pool of every ammo in the game (gotten from itemloader class)
     * @return the deck
     */
    public static ArrayList<Ammo> testDeck(Ammo[] ammopool) {
        ArrayList<Ammo> deck = new ArrayList<Ammo>();
        deck.add((Ammo)ammopool[0].clone());
        deck.add((Ammo)ammopool[1].clone());
        deck.add((Ammo)ammopool[2].clone());
        deck.add((Ammo)ammopool[1].clone());
        deck.add((Ammo)ammopool[1].clone());
        deck.add((Ammo)ammopool[1].clone());
        deck.add((Ammo)ammopool[1].clone());
        return deck;
    }
}
