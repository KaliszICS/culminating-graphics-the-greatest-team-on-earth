package theGame;

public class RegularAmmo extends Ammo {
    private int timer;

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
        return false;
    }

    @Override
    public void move() {
        super.setX(super.getX()+super.getXVelocity());
        super.setY(super.getY()+super.getYVelocity());
    }

    @Override
    public void collide(ICollidable col) {

    }

    @Override
    public void upgrade() {

    }

    @Override
    public void applyEffect(Player player) {

    }

    public void timerDown() {
        timer--;
    }

    public void resetTimer() {
        timer = (int)super.getDuration();
    }
}
