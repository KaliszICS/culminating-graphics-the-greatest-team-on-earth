package theGame;
import java.util.*;
abstract class Item {
    private int rarity;
    private int type;
    private String name;
    private int id;
    private ArrayList<String> effects;

    public int getRarity() {
        return this.rarity;
    }

    public int getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<String> getEffects() {
        return this.effects;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEffects(ArrayList<String> effects) {
        this.effects = effects;
    }
}