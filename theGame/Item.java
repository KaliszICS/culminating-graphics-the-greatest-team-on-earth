package theGame;
import java.util.*;
abstract class Item implements IEffectable {
    private int rarity;
    private String type;
    private String name;
    private int id;
    private ArrayList<String> effects;

    public int getRarity() {
        return this.rarity;
    }

    public String getType() {
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

    public void setType(String type) {
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