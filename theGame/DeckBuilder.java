package theGame;

import java.util.*;

public class DeckBuilder {
    
    public static ArrayList<Ammo> starterDeck() {
        ArrayList<Ammo> deck = new ArrayList<Ammo>();
        deck.add(new RegularAmmo(5, 15, -400, "Cd_-10", 10, 0, 20));
        deck.add(new RegularAmmo(5, 15, -400, "Cd_-10", 10, 0, 20));
        deck.add(new RegularAmmo(5, 15, 100, "Dmg_+5", 10, 30, 20));
        deck.add(new RegularAmmo(15, 30, 400, "Dmg_+15", 30, 30, 50));
        deck.add(new RegularAmmo(5, 30, 25, "Dmg_+5", 5, 10, 10));
        deck.add(new RegularAmmo(5, 30, 25, "Dmg_+5", 5, 10, 10));
        deck.add(new RegularAmmo(5, 30, 25, "Dmg_+5", 5, 10, 10));
        deck.add(new RegularAmmo(5, 15, 100, "Dmg_+5", 10, 30, 20));
        deck.add(new RegularAmmo(5, 15, 100, "Dmg_+5", 10, 30, 20));
        deck.add(new RegularAmmo(5, 15, 100, "Dmg_+5", 10, 30, 20));
        deck.add(new RegularAmmo(5, 15, 100, "Dmg_+5", 10, 30, 20));
        Collections.shuffle(deck);
        return deck;
    }

    public static ArrayList<Ammo> idkIWasBored() {
        ArrayList<Ammo> deck = new ArrayList<Ammo>();
        for (int i = 0; i < 10000; i++) {
            deck.add(new RegularAmmo(0, 0, -400, null, 0, 0, 20));
        }
        return deck;
    }
}
