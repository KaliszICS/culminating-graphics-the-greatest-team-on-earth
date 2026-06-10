package theGame;

import java.util.*;

/**
 * Abstract base class representing an item in the game.
 * Each item has a rarity, type, name, id, and an arraylist of effects. Concrete item implementations must provide behavior defined by the IEffectable interface.
 */
abstract class Item implements IEffectable {

    /** The rarity level of the item. */
    protected int rarity;

    /** The category or type of the item. */
    protected String type;

    /** The display name of the item. */
    protected String name;

    /** The unique identifier for the item. */
    protected int id;

    /** The list of effects associated with the item. */
    protected ArrayList<String> effects;

    /**
     * Returns the rarity level of the item.
     *
     * @return the item's rarity
     */
    public int getRarity() {
        return this.rarity;
    }

    /**
     * Returns the type of the item.
     *
     * @return the item type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the name of the item.
     *
     * @return the item name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the unique identifier of the item.
     *
     * @return the item ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the list of effects associated with the item.
     *
     * @return the item's effects
     */
    public ArrayList<String> getEffects() {
        return this.effects;
    }

    /**
     * Sets the rarity level of the item.
     *
     * @param rarity the new rarity value
     */
    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    /**
     * Sets the type of the item.
     *
     * @param type the new item type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the new item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the unique identifier of the item.
     *
     * @param id the new item ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the effects associated with the item.
     *
     * @param effects the new list of effects
     */
    public void setEffects(ArrayList<String> effects) {
        this.effects = effects;
    }
}