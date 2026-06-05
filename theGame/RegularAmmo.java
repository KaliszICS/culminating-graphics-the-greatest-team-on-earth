package theGame;

public class RegularAmmo extends Ammo {
    private int timer;

    public RegularAmmo(double size) {
        super.setDamage(5);
        super.setProjSpd(15);
        super.setRecoil(250);
        super.setUpgradeable("dmg 5");
        super.setXVelocity(0);
        super.setYVelocity(0);
        super.setX(0);
        super.setY(0);
        super.setSize(size);
        super.setDuration(30);
        super.setFireDelay(10);
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

    public void timerDown() {
        timer--;
    }

    public void resetTimer() {
        timer = (int)super.getDuration();
    }
}
