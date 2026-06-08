package theGame;
import javafx.scene.shape.*;
import java.util.*;

abstract class Enemy implements ICollidable {
    private Shape sprite;
    private String name;
    private int value;
    private int hp;
    private int dmg;
    private double xpos;
    private double ypos;
    private double targetX;
    private double targetY;
    private double spd;
    private double size;
    private ArrayList<ICollidable> immunityList = new ArrayList<ICollidable>();
    private HashMap<ICollidable, Integer> immunityTimers = new HashMap<ICollidable, Integer>();
    private double realspd;
    private int damageTimer = 0;

    @Override
    public void collide(ICollidable col) {
        if (!this.immunityList.contains(col)) {
            this.realspd = 0;
            this.damageTimer = 10;
            this.takeDamage(col.getDmg());
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
        return this.hp <= 0;
    }

    @Override
    public Class<? extends ICollidable> getFriend () {
        return Enemy.class;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setX(double xpos) {
        this.xpos = xpos;
    }

    public void setY(double ypos) {
        this.ypos = ypos;
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    public void setSpd(double spd) {
        this.spd = spd;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setShape(Shape sprite) {
        this.sprite = sprite;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public int getHp() {
        return this.hp;
    }

    @Override
    public int getDmg() {
        return this.dmg;
    }

    @Override
    public double getX() {
        return this.xpos;
    }

    @Override
    public double getY() {
        return this.ypos;
    }

    public double getTargetX() {
        return this.targetX;
    }

    public double getTargetY() {
        return this.targetY;
    }

    public double getSpd() {
        return this.spd;
    }

    @Override
    public double getSize() {
        return this.size;
    }

    public Shape getShape() {
        return this.sprite;
    }

    public double getRealSpeed() {
        return this.realspd;
    }

    public void setRealSpeed(double realspd) {
        this.realspd = realspd;
    }

    public ArrayList<ICollidable> getImmunityList() {
        return this.immunityList;
    }

    public HashMap<ICollidable, Integer> getImmunityTimers() {
        return this.immunityTimers;
    }

    public int getDamageTimer() {
        return this.damageTimer;
    }

    public void decreaseDamageTimer() {
        this.damageTimer--;
    }
}
