package theGame;

import java.util.*;

/**
 * Represents the player character in the game.
 * <p>
 * The Player manages movement, health, combat, ammunition decks,
 * reloading, relic effects, and collision interactions. The player
 * uses a deck-based ammo system consisting of a reserve pile,
 * cartridge, and discard pile.
 */
public class Player implements ICollidable {

    /**body damage */
    private int thorns = 1;
    
    /**invulnerability frames */
    private int iframes = 0;

    /**target x position */
    private double targetx;

    /**target y position */
    private double targety;

    /**current x position */
    private double xpos;

    /**current y position */
    private double ypos;

    /** current reload cooldown */
    private int reloadCooldown = 0;

    /**current hp */
    private int currentHp;
    
    /**max hp */
    private int maxHp;
    
    /**current $$$ the player has */
    private int money;

    /**unimplemented, number of shield points the player has */
    private int shield;

    /**size of player */
    private double size;

    /**speed of player */
    private double speed;

    /**player's weapon */
    private Weapon weapon;

    /**inventory of all player's relics */
    private ArrayList<Relic> relicInv;

    /**the inventory of ammo that isn't used during a wave */
    private ArrayList<Ammo> ammoInv;

    /**the wave ammo reserve */
    private ArrayList<Ammo> reserve;
    
    /**the ammos that have been discarded during a wave*/
    private ArrayList<Ammo> discard;

    /**the speed modifier (percents*/
    private double speedMod = 0;

    /**the reload modifier (percents) */
    private double reloadMod = 0;

    /**the cooldown modifier (percents) */
    private double cooldownMod = 0;

    /**
     * Repository of all ammo types available in the game.
     */
    public final Ammo[] ALL_AMMO = ItemLoader.loadAll();

    /**
     * Constructs a new player with default stats, position, starting ammo deck, and weapon.
     */
    public Player() {
        this.targetx = 300;
        this.targety = 240;
        this.xpos = 300;
        this.ypos = 240;

        this.maxHp = 10;
        this.currentHp = this.maxHp;

        this.money = 100;

        this.size = 25;
        this.speed = 7.5;

        this.ammoInv = new ArrayList<>(DeckBuilder.testDeck(ALL_AMMO));

        this.discard = new ArrayList<>();
        this.reserve = new ArrayList<>();

        for (int i = 0; i < this.ammoInv.size(); i++) {
            this.reserve.add((Ammo) this.ammoInv.get(i).clone());
        }

        Collections.shuffle(this.reserve);

        this.weapon = new DefaultWeapon(reserve);
    }

    /**
     * Resets the player state for the next wave
     * Restores position, health modifiers, ammo piles, and reloads the player's weapon.
     */
    public void reset() {
        this.targetx = 300;
        this.targety = 240;
        this.xpos = 300;
        this.ypos = 240;

        this.currentHp = this.maxHp;

        this.speedMod = 0;
        this.reloadMod = 0;
        this.cooldownMod = 0;

        this.discard.clear();
        this.weapon.getCartridge().clear();
        this.reserve.clear();

        for (int i = 0; i < this.ammoInv.size(); i++) {
            this.reserve.add((Ammo) this.ammoInv.get(i).clone());
        }

        Collections.shuffle(this.reserve);
        this.weapon.reload(reserve);
    }

    /**
     * Handles collisions with another collidable object.
     * <p>
     * If the player is not currently invulnerable and the
     * object is hostile, damage is taken and invulnerability
     * frames are granted.
     *
     * @param col the colliding object
     */
    public void collide(ICollidable col) {
        if (this.iframes <= 0 && col.getFriend() != Player.class) {
            this.iframes = 30;
            this.takeDamage(col.getDmg());
        }
    }

    /**
     * Updates player movement and timers.
     */
    @Override
    public void move() {
        this.iframes--;
        this.reloadCooldown--;

        double dx = this.targetx - this.xpos;
        double dy = this.targety - this.ypos;

        this.xpos += dx * 0.1;
        this.ypos += dy * 0.1;
    }

    /**
     * Fires the currently loaded ammo.
     * Applies recoil, activates shoot effects,
     * updates reload cooldowns, and reloads if necessary.
     *
     * @param x target x-coordinate
     * @param y target y-coordinate
     * @return the fired ammo instance
     */
    public Ammo shoot(double x, double y) {
        Ammo ammo = this.weapon.shoot();

        ammo.setFriend(Player.class);

        this.discard.add(ammo);
        ammo.applyEffect(this, "Shoot");

        this.reloadCooldown = (int) (ammo.getFireDelay() * (1 + cooldownMod / 100));
        this.targetx -= Math.cos(Math.atan2(x - this.xpos, y - this.ypos) + Math.PI / 2) * ammo.getRecoil();
        this.targety -= Math.sin(-Math.atan2(x - this.xpos, y - this.ypos) + Math.PI / 2) * ammo.getRecoil();

        if (this.weapon.getCartridge().size() == 0) {
            this.reloadCooldown = (int) (this.weapon.getReloadSpd() * (1 + reloadMod / 100));

            if (!this.weapon.reload(reserve)) {
                this.refreshReserve();
                this.weapon.reload(reserve);
            }
        }

        return ammo;
    }

    /**
     * Returns the friendly class this object belongs to.
     *
     * @return Player.class
     */
    @Override
    public Class<? extends ICollidable> getFriend() {
        return Player.class;
    }

    /**
     * Activates the weapon's special ability.
     */
    public void special() {
        this.reloadCooldown = 10;
        this.weapon.special();
    }

    /**
     * Deals damage to the player.
     *
     * @param dmg amount of damage taken
     */
    public void takeDamage(int dmg) {
        this.currentHp -= dmg;
    }

    /**
     * Shuffles the discard pile back into the reserve pile.
     */
    public void refreshReserve() {
        this.reserve.addAll(this.discard);
        Collections.shuffle(this.reserve);
        this.discard.clear();
    }

    /**
     * Determines whether the player is immune to a collision.
     *
     * @param col the colliding object
     * @return true if friendly or invulnerable
     */
    @Override
    public boolean isImmune(ICollidable col) {
        if (col.getFriend() == Player.class) {
            return true;
        }
        return this.iframes > 0;
    }

    /**
     * Returns the reserve ammo pile.
     *
     * @return reserve pile
     */
    public ArrayList<Ammo> getReserve() {
        return this.reserve;
    }

    /**
     * Returns the discard pile.
     *
     * @return discard pile
     */
    public ArrayList<Ammo> getDiscard() {
        return this.discard;
    }

    /**
     * Returns the weapon's cartridge.
     *
     * @return loaded ammo list
     */
    public ArrayList<Ammo> getCartridge() {
        return this.weapon.getCartridge();
    }

    /**
     * Returns the number of ammo cards in the discard pile.
     *
     * @return discard pile size
     */
    public int getDiscardSize() {
        return this.discard.size();
    }

    /**
     * Returns the player's thorn damage.
     *
     * @return thorn damage value
     */
    public int getDmg() {
        return this.thorns;
    }

    /**
     * Returns the equipped weapon.
     *
     * @return current weapon
     */
    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * Returns the player's collision size.
     *
     * @return collision radius/size
     */
    @Override
    public double getSize() {
        return this.size;
    }

    /**Currently unimplemented
     * Right now, players are never automatically deleted.
     *
     * @return false
     */
    @Override
    public boolean isDeleted() {
        return false;
    }

    /**
     * Returns the player's current x-coordinate.
     *
     * @return x position
     */
    @Override
    public double getX() {
        return this.xpos;
    }

    /**
     * Returns the player's current y-coordinate.
     *
     * @return y position
     */
    @Override
    public double getY() {
        return this.ypos;
    }

    /** @return target y-coordinate */
    public double getTargetY() {
        return this.targety;
    }

    /** @return remaining reload cooldown */
    public double getReloadCooldown() {
        return this.reloadCooldown;
    }

    /** @return weapon reload speed */
    public double getReloadTime() {
        return this.weapon.getReloadSpd();
    }

    /** @return target x-coordinate */
    public double getTargetX() {
        return this.targetx;
    }

    /**
     * Returns the player's movement speed after modifiers.
     *
     * @return modified speed value
     */
    public double getSpeed() {
        return this.speed * (1 + this.speedMod / 100);
    }

    /**
     * Sets the player's collision size.
     *
     * @param size new size
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Sets the player's x-coordinate.
     *
     * @param xpos new x position
     */
    public void setX(double xpos) {
        this.xpos = xpos;
    }

    /**
     * Sets the player's y-coordinate.
     *
     * @param ypos new y position
     */
    public void setY(double ypos) {
        this.ypos = ypos;
    }

    /**
     * Sets the target y-coordinate.
     *
     * @param targety new target y position
     */
    public void setTargetY(double targety) {
        this.targety = targety;
    }

    /**
     * Sets the target x-coordinate.
     *
     * @param targetx new target x position
     */
    public void setTargetX(double targetx) {
        this.targetx = targetx;
    }

    /**
     * Adjusts the player's movement speed modifier.
     *
     * @param spdMod modifier amount
     */
    public void adjustSpdMod(double spdMod) {
        this.speedMod += spdMod;
    }

    /**
     * Adjusts the player's reload speed modifier.
     *
     * @param rldMod modifier amount
     */
    public void adjustRldMod(double rldMod) {
        this.reloadMod += rldMod;
    }

    /**
     * Adjusts the player's firing cooldown modifier.
     *
     * @param cdMod modifier amount
     */
    public void adjustCdMod(double cdMod) {
        this.cooldownMod += cdMod;
    }

    /**
     * Sets the number of invulnerability frames.
     *
     * @param iframes new iframe duration
     */
    public void setIFrames(int iframes) {
        this.iframes = iframes;
    }

    /**
     * Sets the player's shield value.
     *
     * @param shield new shield amount
     */
    public void setShield(int shield) {
        this.shield = shield;
    }
}