package theGame;

import javafx.scene.shape.*;
import java.util.*;

/**
 * Abstract base class representing an enemy entity in the game.
 * Enemies are collidable and movable objects that target a location, and can also deal damage
 * @author Eric Wang
 */
abstract class Enemy implements ICollidable {

    /** The JavaFX shape used to render the enemy. */
    protected Shape sprite;

    /** The name of the enemy. */
    protected String name;

    /** The score or value awarded when the enemy is defeated. */
    protected int value;

    /** The current health points of the enemy. */
    protected int hp;

    /** The contact damage dealt by the enemy. */
    protected int dmg;

    /** The x-coordinate of the enemy. */
    protected double xpos;

    /** The y-coordinate of the enemy. */
    protected double ypos;

    /** The current target x-coordinate the enemy is moving toward. */
    protected double targetX;

    /** The current target y-coordinate the enemy is moving toward. */
    protected double targetY;

    /** The base movement speed of the enemy. */
    protected double spd;

    /** The size of the enemy for rendering and collision detection. */
    protected double size;

    /**
     * List of collidable objects this enemy is currently immune to
     * (used for temporary hit immunity).
     */
    protected ArrayList<ICollidable> immunityList = new ArrayList<>();

    /**
     * Map tracking remaining immunity time for each collidable source.
     */
    protected HashMap<ICollidable, Integer> immunityTimers = new HashMap<>();

    /** The current movement speed, which may be temporarily modified. */
    protected double realspd;

    /** Timer used for damage flash visual feedback. */
    protected int damageTimer = 0;

    /**
     * Handles collision with another collidable object.
     * If the enemy isn't immune to the other collidable, then this enemy takes damage and the collidable is put on the immunity list for 15 frames
     * @param col the collidable object that triggered the collision
     */
    @Override
    public void collide(ICollidable col) {
        if (!this.immunityList.contains(col)) {
            this.realspd = 0;
            this.damageTimer = 10;
            this.takeDamage(col.getDmg());
            this.immunityList.add(col);
            this.immunityTimers.put(col, 15);
        }
    }

    /**
     * Determines whether this enemy is immune to a given collidable object.
     * Enemies are always immune to other enemies, and temporarily immune
     * to objects stored in the immunity list.
     *
     * @param col the collidable object to check
     * @return {@code true} if immune; {@code false} otherwise
     */
    @Override
    public boolean isImmune(ICollidable col) {
        if (col.getFriend() == Enemy.class) {
            return true;
        }
        return this.immunityList.contains(col);
    }

    /**
     * Checks whether this enemy should be removed from the game.
     *
     * @return {@code true} if health is zero or below
     */
    @Override
    public boolean isDeleted() {
        return this.hp <= 0;
    }

    /**
     * Returns the class type considered friendly to enemies.
     *
     * @return {@code Enemy.class}
     */
    @Override
    public Class<? extends ICollidable> getFriend() {
        return Enemy.class;
    }

    /**
     * Reduces the enemy's health by a given amount.
     *
     * @param damage the amount of damage to apply
     */
    public void takeDamage(int damage) {
        this.hp -= damage;
    }

    /** Sets the enemy name. 
     * 
     * @param name the new name
    */
    public void setName(String name) {
        this.name = name;
    }

    /** Sets the enemy value
     * 
     * @param value the new value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /** 
     * 
     * @param hp the new hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /** Sets the enemy contact damage. 
     * 
     * @param dmg the new damage
    */
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    /** Sets the x position of the enemy.
     * 
     * @param xpos the new x position
     */
    public void setX(double xpos) {
        this.xpos = xpos;
    }

    /** Sets the y position of the enemy. 
     * 
     * @param ypos the new y position
    */
    public void setY(double ypos) {
        this.ypos = ypos;
    }

    /** Sets the target x position. 
     * 
     * @param targetX the new target x
    */
    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    /** Sets the target y position. 
     * 
     * @param targetY the new target y
    */
    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    /** Sets the base movement speed.
     * 
     * @param spd the new movement speed
     */
    public void setSpd(double spd) {
        this.spd = spd;
    }

    /** Sets the enemy size. 
     * 
     * @param size the new size
    */
    public void setSize(double size) {
        this.size = size;
    }

    /** Sets the sprite.
     * 
     * @param sprite the new sprite (shape object)
     */
    public void setShape(Shape sprite) {
        this.sprite = sprite;
    }

    /** Gets the enemy name 
     * 
     * @return the enemy name 
    */
    public String getName() {
        return this.name;
    }

    /** Gets the value of the enemy 
     * 
     * @return the enemy value (score reward) 
    */
    public int getValue() {
        return this.value;
    }

    /**Gets the health points of the enemy 
     * 
     * @return the enemy current health 
    */
    public int getHp() {
        return this.hp;
    }

    /**Gets the enemy damage 
     * 
     * @return the enemy damage 
     */
    @Override
    public int getDmg() {
        return this.dmg;
    }

    /** Gets the x position
     * 
     *  @return the x position 
    */
    @Override
    public double getX() {
        return this.xpos;
    }

    /** Gets the y position
     * 
     * @return the y position 
    */
    @Override
    public double getY() {
        return this.ypos;
    }

    /** Gets the target x position
     * 
     *  @return the target x
    */
    public double getTargetX() {
        return this.targetX;
    }

    /** Gets the target y position
     * 
     * @return the target y
     */
    public double getTargetY() {
        return this.targetY;
    }

    /** Gets the base movement speed
     * 
     *  @return the movement speed
    */
    public double getSpd() {
        return this.spd;
    }

    /** Gets the enemy size
     * 
     * @return the enemy size 
     */
    @Override
    public double getSize() {
        return this.size;
    }

    /** Gets the sprite (shape object) 
     * 
     * @return the sprite 
    */
    public Shape getShape() {
        return this.sprite;
    }

    /** Gets the current speed
     * 
     * @return the current movement speed 
     */
    public double getRealSpeed() {
        return this.realspd;
    }

    /** Sets the current movement speed. 
     * 
     * @param realSpd the new current movespeed
    */
    public void setRealSpeed(double realspd) {
        this.realspd = realspd;
    }

    /** Gets the list of immune collidables
     * 
     * @return list of objects this enemy is immune to */
    public ArrayList<ICollidable> getImmunityList() {
        return this.immunityList;
    }

    /** Gets the map of all the timers for the immunities
     * 
     * @return map of immunity timers */
    public HashMap<ICollidable, Integer> getImmunityTimers() {
        return this.immunityTimers;
    }

    /** Gets the timer for the damage ffect
     * 
     * @return current damage flash timer */
    public int getDamageTimer() {
        return this.damageTimer;
    }

    /** Decreases the damage flash timer by one tick. */
    public void decreaseDamageTimer() {
        this.damageTimer--;
    }
}