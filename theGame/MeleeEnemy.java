package theGame;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MeleeEnemy extends Enemy {
    private double realspd;
    private int damageTimer = 0;
    private ArrayList<ICollidable> immunityList = new ArrayList<ICollidable>();

    public MeleeEnemy() {
        super.setHp(500000);
        super.setSpd(10);
        this.realspd = super.getSpd();
        super.setSize(30);
        super.setShape(new Circle(super.getSize()));
    }

    @Override
    public void collide(ICollidable col) {
        realspd = 0;
        this.damageTimer = 10;
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
        if (this.realspd < super.getSpd()) {
            this.realspd += 0.1;
        }
        if (damageTimer >= 8) {
            super.getShape().setFill(Color.WHITE);
        } else 
            if (damageTimer >= 0) {
            super.getShape().setFill(Color.RED);
        } else {
            super.getShape().setFill(Color.BLACK);
        }
        damageTimer--;
        double rotAngle = -Math.atan2(super.getTargetX()-super.getX(), super.getTargetY()-super.getY())+Math.PI/2;
        super.setX(this.realspd*Math.cos(rotAngle)+super.getX());
        super.setY(this.realspd*Math.sin(rotAngle)+super.getY());
    }
}
