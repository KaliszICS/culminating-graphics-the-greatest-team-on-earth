package theGame;

public class RegularAmmo extends Ammo {
    public RegularAmmo() {
        super.setDamage(5);
        super.setProjSpd(15);
        super.setRecoil(250);
        super.setUpgradeable("dmg 5");
        super.setXVelocity(0);
        super.setYVelocity(0);
        super.setX(0);
        super.setY(0);
        super.setSize(10);
        super.setDuration(30);
        super.setFireDelay(10);
    }

    @Override
    public void move() {
        
    }

    public void collide(ICollidable col) {

    }

    @Override
    public void upgrade() {

    }
}
