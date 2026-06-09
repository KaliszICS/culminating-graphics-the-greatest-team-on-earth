package theGame;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MeleeEnemy extends Enemy {

    public MeleeEnemy(int dmg, int hp, double spd, double size) {
        super.setDmg(dmg);
        super.setHp(hp);
        super.setSpd(spd);
        super.setRealSpeed(super.getSpd());
        super.setSize(size);
        super.setShape(new Circle(super.getSize()));
    }

    @Override
    public void move() {
        if (super.getRealSpeed() < super.getSpd()) {
            super.setRealSpeed(super.getRealSpeed()+0.1);
        }
        if (super.getDamageTimer() >= 8) {
            super.getShape().setFill(Color.WHITE);
        } else if (super.getDamageTimer() >= 0) {
            super.getShape().setFill(Color.RED);
        } else {
            super.getShape().setFill(Color.BLUEVIOLET);
        }
        super.decreaseDamageTimer();
        for (int i = 0; i < super.getImmunityList().size(); i++) {
            super.getImmunityTimers().replace(super.getImmunityList().get(i), super.getImmunityTimers().get(super.getImmunityList().get(i))-1);
            if (super.getImmunityTimers().get(super.getImmunityList().get(i)) <= 0) {
                super.getImmunityTimers().remove(super.getImmunityList().get(i));
                super.getImmunityList().remove(i);
            }
        }
        double rotAngle = -Math.atan2(super.getTargetX()-super.getX(), super.getTargetY()-super.getY())+Math.PI/2;
        super.setX(super.getRealSpeed()*Math.cos(rotAngle)+super.getX());
        super.setY(super.getRealSpeed()*Math.sin(rotAngle)+super.getY());
    }
}
