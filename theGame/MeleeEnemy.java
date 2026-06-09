package theGame;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MeleeEnemy extends Enemy {

    public MeleeEnemy(int dmg, int hp, double spd, double size) {
        this.dmg = dmg;
        this.hp = hp;
        this.spd = spd;
        this.realspd = spd;
        this.size = size;
        this.sprite = new Circle(size);
    }

    @Override
    public void move() {
        if (this.realspd < this.spd) {
            this.realspd += 0.1;
        }
        if (this.damageTimer >= 8) {
            this.sprite.setFill(Color.WHITE);
        } else if (this.damageTimer >= 0) {
            this.sprite.setFill(Color.RED);
        } else {
            this.sprite.setFill(Color.BLUEVIOLET);
        }
        this.damageTimer--;
        for (int i = 0; i < this.immunityList.size(); i++) {
            this.immunityTimers.replace(this.immunityList.get(i), this.immunityTimers.get(this.immunityList.get(i))-1);
            if (this.immunityTimers.get(this.immunityList.get(i)) <= 0) {
                this.immunityTimers.remove(this.immunityList.get(i));
                this.immunityList.remove(i);
            }
        }
        double rotAngle = -Math.atan2(this.targetX-this.xpos, this.targetY-this.ypos)+Math.PI/2;
        this.xpos = this.realspd*Math.cos(rotAngle);
        this.ypos = this.realspd*Math.sin(rotAngle);
    }
}
