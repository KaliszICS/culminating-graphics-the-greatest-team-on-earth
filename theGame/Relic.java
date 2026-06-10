package theGame;

import java.util.*;

import javafx.scene.image.Image;

/**
 * Represents a Relic item that applies conditional effects to a Player.
 * A Relic activates when a specific trigger condition matches its internal condition. When active, it applies a list of effects to the given Player.
 * @author Eric Wang
 */
public class Relic extends Item {

    private String condition;

    /**
     * Constructs a new Relic with the specified attributes.
     *
     * @param rarity    the rarity level of the relic
     * @param type      the type/category of the item
     * @param name      the name of the relic
     * @param id        the unique identifier of the relic
     * @param effects   list of encoded effect strings (e.g., "Spd_0.1")
     * @param condition the trigger condition required to activate this relic
     */
    public Relic(int rarity, String type, String name, int id, ArrayList<String> effects, String condition) {
        super.setRarity(rarity);
        super.setType(type);
        super.setName(name);
        super.setId(id);
        super.setEffects(effects);
        this.condition = condition;
    }

    /**
     * Applies this relic's effects to the player if the trigger condition matches.
     * Each effect is expected to be in the format "Effect_Value", where Effect determines the stat to modify and Value is a numeric modifier.
     *
     * @param player    the player receiving the effects
     * @param condition the trigger condition to check against this relic
     */
    @Override
    public void applyEffect(Player player, String condition) {
        if (!this.condition.equals(condition)) {
            return;
        }

        for (int i = 0; i < super.getEffects().size(); i++) {
            String[] eff = getEffects().get(i).split("_");

            if (eff[0].equals("Spd")) {
                player.adjustSpdMod(Double.parseDouble(eff[1]));
            } else if (eff[0].equals("Rld")) {
                player.adjustRldMod(Double.parseDouble(eff[1]));
            } else if (eff[0].equals("Cd")) {
                player.adjustCdMod(Double.parseDouble(eff[1]));
            }
        }
    }

    /**
     * Checks whether this relic is active for the given trigger condition.
     *
     * @param trigger the condition to test
     * @return true if the relic activates under this trigger, false otherwise
     */
    public boolean checkActive(String trigger) {
        return condition.equals(trigger);
    }

    @Override
    public Image getIcon() {
        return new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_2LPYRlundRD0JEWh_Hp5o5T4miDl42J2XQ&s");
    }

    /**
     * Returns the activation condition of this relic.
     *
     * @return the condition string that activates this relic
     */
    public String getCondition() {
        return condition;
    }
}