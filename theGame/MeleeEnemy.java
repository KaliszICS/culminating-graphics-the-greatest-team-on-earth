package theGame;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A close range enemy that moves directly toward its target.
 * @author Eric Wang
 */
public class MeleeEnemy extends Enemy {

    /**
     * Constructs a melee enemy with the given combat stats and size.
     *
     * @param dmg  the damage this enemy deals on contact
     * @param hp   the health points of the enemy
     * @param spd  the movement speed of the enemy
     * @param size the radius of the enemy's visual representation
     */
    public MeleeEnemy(int dmg, int hp, double spd, double size) {
        this.dmg = dmg;
        this.hp = hp;
        this.spd = spd;
        this.realspd = spd;
        this.size = size;
        this.sprite = new Circle(size);
    }

    /**
     * Updates the enemy's movement, animation state, and status effects.
     * Accelerates the enemy, plays damage effects, and updates the list of immunities
     */
    @Override
    public void move() {

        // Acceleration up to real speed
        if (this.realspd < this.spd) {
            this.realspd += 0.1;
        }

        // Damage effect
        if (this.damageTimer >= 8) {
            this.sprite.setFill(Color.WHITE);
        } else if (this.damageTimer >= 0) {
            this.sprite.setFill(Color.RED);
        } else {
            this.sprite.setFill(Color.BLUEVIOLET);
        }

        this.damageTimer--;

        // Update immunity timers and remove anything that's hit zero
        for (int i = 0; i < this.immunityList.size(); i++) {
            this.immunityTimers.replace(
                this.immunityList.get(i),
                this.immunityTimers.get(this.immunityList.get(i)) - 1
            );

            if (this.immunityTimers.get(this.immunityList.get(i)) <= 0) {
                this.immunityTimers.remove(this.immunityList.get(i));
                this.immunityList.remove(i);
            }
        }

        // Move toward target
        double rotAngle = -Math.atan2(
            this.targetX - this.xpos,
            this.targetY - this.ypos
        ) + Math.PI / 2;

        this.xpos += this.realspd * Math.cos(rotAngle);
        this.ypos += this.realspd * Math.sin(rotAngle);
    }
}