package theGame;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MeleeEnemy extends Enemy {
    private double realspd;
    private int damageTimer = 0;
    private ArrayList<ICollidable> immunityList = new ArrayList<ICollidable>();
    HashMap<ICollidable, Integer> immunityTimers = new HashMap<ICollidable, Integer>();

    public MeleeEnemy(int dmg, int hp, double spd, double size) {
        super.setDmg(dmg);
        super.setHp(hp);
        super.setSpd(spd);
        this.realspd = super.getSpd();
        super.setSize(size);
        super.setShape(new Circle(super.getSize()));
    }

    @Override
    public void collide(ICollidable col) {
        if (!immunityList.contains(col)) {
            this.realspd = 0;
            this.damageTimer = 10;
            super.takeDamage(col.getDmg());
            this.immunityList.add(col);
            this.immunityTimers.put(col, 30);
        }
    }

    @Override
    public boolean isImmune(ICollidable col) {
        if (col.getFriend() == Enemy.class) {
            return true;
        }
        return this.immunityList.contains(col);
    }

    @Override
    public boolean isDeleted() {
        return super.getHp() <= 0;
    }

    @Override
    public void move() {
        if (this.realspd < super.getSpd()) {
            this.realspd += 0.1;
        }
        if (this.damageTimer >= 8) {
            super.getShape().setFill(Color.WHITE);
        } else 
            if (this.damageTimer >= 0) {
            super.getShape().setFill(Color.RED);
        } else {
            super.getShape().setFill(Color.BLACK);
        }
        this.damageTimer--;
        for (int i = 0; i < this.immunityList.size(); i++) {
            this.immunityTimers.replace(this.immunityList.get(i), this.immunityTimers.get(this.immunityList.get(i))-1);
            if (this.immunityTimers.get(this.immunityList.get(i)) <= 0) {
                this.immunityTimers.remove(this.immunityList.get(i));
                this.immunityList.remove(i);
            }
        }
        double rotAngle = -Math.atan2(super.getTargetX()-super.getX(), super.getTargetY()-super.getY())+Math.PI/2;
        super.setX(this.realspd*Math.cos(rotAngle)+super.getX());
        super.setY(this.realspd*Math.sin(rotAngle)+super.getY());
    }
}
