package theGame;
import java.util.*;
public class Player implements IMovable {
    private int iframes;
    private double targetx;
    private double targety;
    private double xpos;
    private double ypos;
    private int reloadCooldown = 0;
    private int hp;
    private int money;
    private double size;
    private double speed;
    private Weapon weapon;
    private ArrayList<Ammo> reserve;
    private ArrayList<Ammo> discard;
    private double speedMod = 0;
    private double reloadMod = 0;
    private double cooldownMod = 0;

    public Player() {
        this.targetx = 300;
        this.targety = 240;
        this.xpos = 300;
        this.ypos = 240;
        this.hp = 10;
        this.money = 100;
        this.size = 25;
        this.speed = 7.5;
        this.discard = new ArrayList<Ammo>();
        this.reserve = new ArrayList<Ammo>(DeckBuilder.starterDeck());
        this.weapon = new DefaultWeapon(reserve);
    }

    @Override
    public void move() {
        this.reloadCooldown--;
        double dx = this.targetx - this.xpos;
        double dy = this.targety - this.ypos;
        this.xpos += dx*0.1;
        this.ypos += dy*0.1;
    }

    public Ammo shoot(double x, double y) {
        Ammo ammo = this.weapon.shoot();
        discard.add(ammo);
        this.reloadCooldown = (int)(ammo.getFireDelay()*(1+cooldownMod/100));
        this.targetx -= Math.cos(-Math.atan2(x-this.xpos, y-this.ypos)+Math.PI/2)*ammo.getRecoil();
        this.targety -= Math.sin(-Math.atan2(x-this.xpos, y-this.ypos)+Math.PI/2)*ammo.getRecoil();
        if (this.weapon.getCartridge().size() == 0) {
            this.reloadCooldown = (int)(this.weapon.getReloadSpd()*(1+reloadMod/100));
            if (!this.weapon.reload(reserve)) {
                this.reserve.addAll(this.discard);
                Collections.shuffle(reserve);
                this.discard.clear();
                this.weapon.reload(reserve);
            }
        }
        return ammo;
    }

    public void special() {
        this.reloadCooldown = 10;
        this.weapon.special();
    }

    public ArrayList<Ammo> getReserve() {
        return this.reserve;
    }

    public ArrayList<Ammo> getCartridge() {
        return this.weapon.getCartridge();
    }

    public int getDiscardSize() {
        return this.discard.size();
    }

    public double getSize() {
        return this.size;
    }

    @Override
    public boolean isDeleted() {
        return false;
    }

    @Override
    public double getX() {
        return this.xpos;
    }

    @Override
    public double getY() {
        return this.ypos;
    }

    public double getTargetY() {
        return this.targety;
    }

    public double getReloadCooldown() {
        return this.reloadCooldown;
    }

    public double getReloadTime() {
        return this.weapon.getReloadSpd();
    }

    public double getTargetX() {
        return this.targetx;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setX(double xpos) {
        this.xpos = xpos;
    }

    public void setY(double ypos) {
        this.ypos = ypos;
    }

    public void setTargetY(double targety) {
        this.targety = targety;
    }

    public void setTargetX(double targetx) {
        this.targetx = targetx;
    }

    public void adjustSpdMod(double spdMod) {
        this.speedMod += spdMod;
    }

    public void adjustRldMod(double rldMod) {
        this.reloadMod += rldMod;
    }

    public void adjustCdMod(double cdMod) {
        this.cooldownMod += cdMod;
    }
}
