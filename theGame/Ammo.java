package theGame;

import javafx.scene.image.*;

/**
 * Abstract base class representing ammunition in the game.
 * Ammo defines projectile behavior, including damage, movement, recoil, lifespan, collision properties, and possible upgrades.
 * Concrete ammo types must implement their own upgrade logic and provide images for the icon and the ingame sprite.
 * @author Eric wang
 */
abstract class Ammo extends Item implements ICollidable, Cloneable {

    /** The damage dealt by the projectile. */
    protected int damage;

    /** The speed at which the projectile travels. */
    protected double projSpd;

    /** The recoil applied when the ammunition is fired. */
    protected double recoil;

    /** The name or identifier of the ammunition's upgrade path. */
    protected String upgradeable;

    /** The projectile's x-coordinate. */
    protected double xpos;

    /** The projectile's y-coordinate. */
    protected double ypos;

    /** The projectile's horizontal velocity. */
    protected double xvelocity;

    /** The projectile's vertical velocity. */
    protected double yvelocity;

    /** The remaining lifetime of the projectile. */
    protected double duration;

    /** The size of the projectile used for rendering and collisions. */
    protected double size;

    /** The delay between shots when using this ammunition. */
    protected int fireDelay;

    /** The class type considered friendly to this projectile. */
    protected Class<? extends ICollidable> friendly;

    /**
     * Upgrades this ammunition to its next level or form.
     */
    abstract void upgrade();

    /**
     * Returns the image used to render this ammunition in the game world.
     *
     * @return the projectile image
     */
    abstract Image getShape();

    /**
     * Returns the icon used to represent this ammunition in menus or inventories.
     *
     * @return the ammunition icon
     */
    abstract Image getIcon();

    /**
     * Creates and returns a copy of this ammunition object.
     *
     * @return a cloned copy of this ammunition, or null if cloning fails
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns the class type that is considered friendly to this projectile.
     *
     * @return the friendly collision class
     */
    @Override
    public Class<? extends ICollidable> getFriend() {
        return friendly;
    }

    /**
     * Returns the x-coordinate of the projectile.
     *
     * @return the x-coordinate
     */
    @Override
    public double getX() {
        return this.xpos;
    }

    /**
     * Returns the y-coordinate of the projectile.
     *
     * @return the y-coordinate
     */
    @Override
    public double getY() {
        return this.ypos;
    }

    /**
     * Returns the vertical velocity of the projectile.
     *
     * @return the y velocity
     */
    public double getYVelocity() {
        return yvelocity;
    }

    /**
     * Returns the horizontal velocity of the projectile.
     *
     * @return the x velocity
     */
    public double getXVelocity() {
        return xvelocity;
    }

    /**
     * Returns the damage dealt by this ammunition.
     *
     * @return the damage value
     */
    @Override
    public int getDmg() {
        return this.damage;
    }

    /**
     * Returns the projectile speed.
     *
     * @return the projectile speed
     */
    public double getProjSpd() {
        return this.projSpd;
    }

    /**
     * Returns the recoil generated when firing this ammunition.
     *
     * @return the recoil value
     */
    public double getRecoil() {
        return this.recoil;
    }

    /**
     * Returns the upgrade path identifier.
     *
     * @return the upgradeable identifier
     */
    public String getUpgradeable() {
        return this.upgradeable;
    }

    /**
     * Returns the projectile's remaining duration.
     *
     * @return the duration
     */
    public double getDuration() {
        return this.duration;
    }

    /**
     * Returns the size of the projectile.
     *
     * @return the projectile size
     */
    @Override
    public double getSize() {
        return this.size;
    }

    /**
     * Returns the firing delay associated with this ammunition.
     *
     * @return the fire delay
     */
    public int getFireDelay() {
        return this.fireDelay;
    }

    /**
     * Sets the damage dealt by this ammunition.
     *
     * @param damage the new damage value
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Sets the upgrade path identifier.
     *
     * @param upgradeable the new upgrade path
     */
    public void setUpgradeable(String upgradeable) {
        this.upgradeable = upgradeable;
    }

    /**
     * Sets the projectile speed.
     *
     * @param projSpd the new projectile speed
     */
    public void setProjSpd(double projSpd) {
        this.projSpd = projSpd;
    }

    /**
     * Sets the recoil value.
     *
     * @param recoil the new recoil value
     */
    public void setRecoil(double recoil) {
        this.recoil = recoil;
    }

    /**
     * Sets the x-coordinate of the projectile.
     *
     * @param xpos the new x-coordinate
     */
    public void setX(double xpos) {
        this.xpos = xpos;
    }

    /**
     * Sets the y-coordinate of the projectile.
     *
     * @param ypos the new y-coordinate
     */
    public void setY(double ypos) {
        this.ypos = ypos;
    }

    /**
     * Sets the horizontal velocity of the projectile.
     *
     * @param xvelocity the new x velocity
     */
    public void setXVelocity(double xvelocity) {
        this.xvelocity = xvelocity;
    }

    /**
     * Sets the vertical velocity of the projectile.
     *
     * @param yvelocity the new y velocity
     */
    public void setYVelocity(double yvelocity) {
        this.yvelocity = yvelocity;
    }

    /**
     * Sets the projectile's duration.
     *
     * @param duration the new duration
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Sets the projectile size.
     *
     * @param size the new size
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Sets the firing delay associated with this ammunition.
     *
     * @param fireDelay the new fire delay
     */
    public void setFireDelay(int fireDelay) {
        this.fireDelay = fireDelay;
    }

    /**
     * Sets the class type that is considered friendly to this projectile.
     *
     * @param friendly the friendly collision class
     */
    public void setFriend(Class<? extends ICollidable> friendly) {
        this.friendly = friendly;
    }
}
