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
        Ammo[] stuff = ItemLoader.loadAll();
        deck.add(stuff[1]);
        return deck;
    }

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
