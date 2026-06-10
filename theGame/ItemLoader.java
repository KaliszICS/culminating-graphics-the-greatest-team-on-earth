package theGame;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javafx.scene.image.Image;
/**A utility class that loads items from a text file
 * Every item comes with its own format across 2 lines, the first being universal traits and the second being specifics to its item type
 * @author Eric Wang
 */
public class ItemLoader {
    private static int totalAmmos = 7;
    private static int totalRelics = 1;

    /**Loads every item in the text file according to the number of total items
     * 
     * @return an ammo arraylist of all items (i didn't have the time to implement more than regular ammos)
     */
    public static Ammo[] loadAll() {
        Ammo[] stuff = new Ammo[totalAmmos];
        File file = new File("/workspaces/culminating-graphics-the-greatest-team-on-earth/theGame/AmmoPresets.txt");
        try {
            Scanner in = new Scanner(file);
            for (int i = 0; i < totalAmmos; i++) {
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

    /**
     * Loads every relic from a text file
     * 
     * @return the loaded relics
     */
    public static Relic[] loadAllRelics() {
        Relic[] stuff = new Relic[totalRelics];
        File file = new File("/workspaces/culminating-graphics-the-greatest-team-on-earth/theGame/RelicPresets.txt");
        try {
            Scanner in = new Scanner(file);
            for (int i = 0; i < totalRelics; i++) {
                String[] l1 = in.nextLine().split(",");
                stuff[i] = readRelic(l1);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("what the fuck are you doing");
        }
        return stuff;
    }

    /**Reads an individual ammo item across 2 lines
     * only supports regular ammo for now
     * line1: id name type rarity effects sprite
     * line2: damage projspd recoil size duration reloadtime pierce
     *
     * @param l1 the first line
     * @param l2 the second line
     * @return the Ammo item interpreted from the two lines
     */
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

    /**
     * Loads one relic based off a line
     * 
     * @param l1 the line
     * @return returns the relic loaded
     */
    public static Relic readRelic(String[] l1) {
        // line1: id name type rarity condition effects sprite
        int id = Integer.parseInt(l1[0]);
        String name = l1[1];
        String type = l1[2];
        int rarity = Integer.parseInt(l1[3]);
        String condition = l1[4];
        String[] effects = l1[5].split(" ");
        String sprite = l1[6];
        return new Relic(rarity, type, name, id, new ArrayList<String>(List.of(effects)), condition, new Image(sprite));
    }
}
