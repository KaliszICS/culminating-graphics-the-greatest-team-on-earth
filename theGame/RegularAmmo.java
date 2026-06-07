package theGame;

import java.util.ArrayList;
import java.util.HashMap;

public class RegularAmmo extends Ammo {
    private int timer;
    private int pierce = 0;
    private ArrayList<ICollidable> immunityList = new ArrayList<ICollidable>();
    HashMap<ICollidable, Integer> immunityTimers = new HashMap<ICollidable, Integer>();

    public RegularAmmo(int damage, double projSpd, double recoil, String upgradeable, double size, double duration, int fireDelay) {
        super.setDamage(damage);
        super.setProjSpd(projSpd);
        super.setRecoil(recoil);
        super.setUpgradeable(upgradeable);
        super.setXVelocity(0);
        super.setYVelocity(0);
        super.setX(0);
        super.setY(0);
        super.setSize(size);
        super.setDuration(duration);
        super.setFireDelay(fireDelay);
        this.timer = (int)super.getDuration();
    }

    @Override
    public boolean isDeleted() {
        if (timer <= 0) {
            resetTimer();
            return true;
        }
        return pierce <= -1;
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
    public void applyEffect(Player player) {

    }

    public void timerDown() {
        this.timer--;
    }

    public void resetTimer() {
        this.timer = (int)super.getDuration();
    }
}
