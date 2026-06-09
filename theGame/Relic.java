package theGame;

import java.util.*;

public class Relic extends Item {
    private String condition;

    public Relic(int rarity, String type, String name, int id, ArrayList<String> effects, String condition) {
        super.setRarity(rarity);
        super.setType(type);
        super.setName(name);
        super.setId(id);
        super.setEffects(effects);
        this.condition = condition;
    }

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

    public boolean checkActive(String trigger) {
        return condition.equals(trigger);
    }

    public String getCondition() {
        return condition;
    }
}
