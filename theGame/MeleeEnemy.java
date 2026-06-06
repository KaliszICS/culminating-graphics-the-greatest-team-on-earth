package theGame;

import java.util.*;

public class MeleeEnemy extends Enemy {
    private ArrayList<ICollidable> immunityList = new ArrayList<ICollidable>();

    public MeleeEnemy() {
        super.setHp(50);
        super.setSpd(2.5);
        super.setSize(10);
    }

    @Override
    public void collide(ICollidable col) {
        if (!immunityList.contains(col)) {
            super.takeDamage(col.getDmg());
        } else {
            immunityList.add(col);
        }
    }

    @Override
    public boolean isDeleted() {
        return super.getHp() <= 0;
    }

    @Override
    public void move() {
        double rotAngle = -Math.atan2(super.getTargetX()-super.getX(), super.getTargetY()-super.getY())+Math.PI/2;
        super.setX(super.getSpd()*Math.cos(rotAngle)+super.getX());
        super.setY(super.getSpd()*Math.sin(rotAngle)+super.getY());
    }
}
