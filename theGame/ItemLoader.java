package theGame;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ItemLoader {
    private static int totalItems = 4;

    public static Ammo[] loadAll() {
        Ammo[] stuff = new Ammo[totalItems];
        File file = new File("/workspaces/culminating-graphics-the-greatest-team-on-earth/theGame/AmmoPresets.txt");
        try {
            Scanner in = new Scanner(file);
            for (int i = 0; i < totalItems; i++) {
                String[] l1 = in.nextLine().split(",");
                String[] l2 = in.nextLine().split(" ");
                stuff[i] = readAmmo(l1, l2);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("what the fuck are you doing");
        }
        return stuff;
    }

    public static Ammo readAmmo(String[] l1, String[] l2) {
        // line1: id name type rarity effects sprite
        // line2: item specifics
        // damage projspd recoil size duration reloadtime pierce
        int id = Integer.parseInt(l1[0]);
        String name = l1[1];
        String type = l1[2];
        int rarity = Integer.parseInt(l1[3]);
        String effects = l1[4];
        String sprite = l1[5];
        String icon = l1[6];
        int damage = Integer.parseInt(l2[0]);
        double projSpd = Double.parseDouble(l2[1]);
        double recoil = Double.parseDouble(l2[2]);
        double size = Double.parseDouble(l2[3]);
        double duration = Double.parseDouble(l2[4]);
        int reloadTime = Integer.parseInt(l2[5]);
        int pierce = Integer.parseInt(l2[6]);
        return new RegularAmmo(id, name, type, rarity, effects, sprite, icon, damage, projSpd, recoil, size, duration, reloadTime, pierce);
    }
}
