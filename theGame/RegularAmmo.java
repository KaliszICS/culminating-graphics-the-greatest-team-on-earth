package theGame;

import java.util.*;

import javafx.scene.Node;
import javafx.scene.image.*;

public class RegularAmmo extends Ammo {
    private int timer;
    private int pierce;
    private Image smallSprite;
    private Image bigSprite;
    private ArrayList<ICollidable> immunityList = new ArrayList<ICollidable>();
    HashMap<ICollidable, Integer> immunityTimers = new HashMap<ICollidable, Integer>();

    public RegularAmmo(int damage, double projSpd, double recoil, String effects, double size, double duration, int fireDelay, int pierce) {
        super.setDamage(damage);
        super.setProjSpd(projSpd);
        super.setRecoil(recoil);
        super.setUpgradeable("");
        super.setXVelocity(0);
        super.setYVelocity(0);
        super.setX(0);
        super.setY(0);
        super.setSize(size);
        super.setDuration(duration);
        super.setFireDelay(fireDelay);
        this.pierce = pierce;
        this.timer = (int)super.getDuration();
        ArrayList<String> tempeff = new ArrayList<String>();
        tempeff.addAll(List.of(effects.split(" ")));
        super.setEffects(tempeff);
    }

    public RegularAmmo(int id, String name, String type, int rarity, String effects, String spriteLink, String iconLink, int damage, double projSpd, double recoil, double size, double duration, int fireDelay, int pierce) {
        super.setId(id);
        super.setName(name);
        super.setType(type);
        super.setRarity(rarity);
        this.bigSprite = new Image(spriteLink);
        this.smallSprite = new Image(iconLink);
        super.setDamage(damage);
        super.setProjSpd(projSpd);
        super.setRecoil(recoil);
        super.setUpgradeable("");
        super.setXVelocity(0);
        super.setYVelocity(0);
        super.setX(0);
        super.setY(0);
        super.setSize(size);
        super.setDuration(duration);
        super.setFireDelay(fireDelay);
        this.pierce = pierce;
        this.timer = (int)super.getDuration();
        ArrayList<String> tempeff = new ArrayList<String>();
        tempeff.addAll(List.of(effects.split(" ")));
        super.setEffects(tempeff);
    }

    @Override
    public Image getIcon() {
        return this.smallSprite;
    }

    @Override
    public Image getShape() {
        return this.bigSprite;
    }

    @Override
    public boolean isDeleted() {
        if (this.timer <= 0) {
            resetTimer();
            return true;
        }
        return this.pierce <= -1;
    }

    @Override
    public boolean isImmune(ICollidable col) {
        if (col.getClass() == (super.getFriend())) {
            return true;
        }
        if (this.immunityList.contains(col)) {
            return true;
        }
        return this.pierce < 0;
    }

    @Override
    public void move() {
        for (int i = 0; i < this.immunityList.size(); i++) {
            this.immunityTimers.replace(this.immunityList.get(i), this.immunityTimers.get(this.immunityList.get(i))-1);
            if (this.immunityTimers.get(this.immunityList.get(i)) <= 0) {
                this.immunityTimers.remove(this.immunityList.get(i));
                this.immunityList.remove(i);
            }
        }
        super.setX(super.getX()+super.getXVelocity());
        super.setY(super.getY()+super.getYVelocity());
    }

    @Override
    public void collide(ICollidable col) {
        if (!this.immunityList.contains(col) && this.pierce >= 0) {
            this.pierce--;
            this.immunityList.add(col);
            this.immunityTimers.put(col, 30);
        }
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void applyEffect(Player player, String condition) {
        for (int i = 0; i < super.getEffects().size(); i++) {
            String[] total = super.getEffects().get(i).split("_");
            String cond = total[0];
            if (condition.equals(cond)) {
                String eff = total[1];
                if (eff.equals("Draw")) {
                    double val = Double.parseDouble(total[2]);
                    for (int j = 0; j < val; j++) {
                        if (player.getReserve().size() != 0) {
                            player.getCartridge().add(player.getReserve().remove(0));
                        } else if (player.getDiscardSize() != 0) {
                            player.refreshReserve();
                            player.getCartridge().add(player.getReserve().remove(0));
                        }
                    }
                } else if (eff.equals("Exhaust")) {
                    player.getDiscard().remove(this);
                } else if (eff.equals("Consume")) {
                    double times = Double.parseDouble(total[2]);
                    int statnum = Integer.parseInt(total[3]);
                    for (int j = 0; j < times; j++) {
                        if (player.getCartridge().size() == 0) {
                            j = (int)times+1;
                            continue;
                        }
                        player.getCartridge().remove(0);
                        for (int z = 0; z < statnum; z++) {
                            String stat = total[4+2*z];
                            double val = Double.parseDouble(total[5+2*z]);
                            if (stat.equals("Dura")) {
                                this.timer += val;
                                // super.setDuration(super.getDuration()+val);
                            } else if (stat.equals("Size")) {
                                super.setSize(super.getSize()+val);
                            } else if (stat.equals("Dmg")) {
                                super.setDamage((int)(super.getDmg()+val));
                            }
                        }
                    }
                }
            }
        }
    }

    public void timerDown() {
        this.timer--;
    }

    public void resetTimer() {
        this.timer = (int)super.getDuration();
    }
}
