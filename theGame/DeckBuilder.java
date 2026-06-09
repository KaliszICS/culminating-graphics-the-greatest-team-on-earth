package theGame;

import java.util.*;

public class DeckBuilder {

    public static ArrayList<Ammo> starterDeck() {
        Ammo[] stuff = ItemLoader.loadAll();
        ArrayList<Ammo> deck = new ArrayList<Ammo>();
        deck.add(stuff[0]);
        // deck.add(new RegularAmmo(20, 1, 400, "Shoot_Consume_1_3_Dmg_5_Size_30_Dura_60", 30, 30, 180, 100));
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

    public static ArrayList<Ammo> idkIWasBored() {
        ArrayList<Ammo> deck = new ArrayList<Ammo>();
        for (int i = 0; i < 1; i++) {
            deck.add(new RegularAmmo(1, 15, 0, null, 30, 30, 20, 0));
        }
        return deck;
    }

    public static ArrayList<Ammo> testDeck() {
        Ammo[] stuff = ItemLoader.loadAll();
        ArrayList<Ammo> deck = new ArrayList<Ammo>();
        deck.add(stuff[0]);
        return deck;
    }
}
