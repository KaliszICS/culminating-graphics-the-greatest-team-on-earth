package theGame;
import javafx.scene.shape.*;

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
}
