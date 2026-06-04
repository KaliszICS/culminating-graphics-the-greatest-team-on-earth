package theGame;
import java.util.*;
public class Player implements IMovable {
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
        this.reserve = new ArrayList<Ammo>(List.of(new RegularAmmo(), new RegularAmmo(), new RegularAmmo(), new RegularAmmo(), new RegularAmmo(), new RegularAmmo()));
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
        this.reloadCooldown = ammo.getFireDelay();
        this.targetx -= Math.cos(-Math.atan2(x-this.xpos, y-this.ypos)+Math.PI/2)*ammo.getRecoil();
        this.targety -= Math.sin(-Math.atan2(x-this.xpos, y-this.ypos)+Math.PI/2)*ammo.getRecoil();
        if (this.weapon.getCartridge().size() == 0) {
            this.reloadCooldown = this.weapon.getReloadSpd();
            if (!this.weapon.reload(reserve)) {
                this.reserve.addAll(this.discard);
                this.weapon.reload(reserve);
            }
        }
        return ammo;
    }


    public double getSize() {
        return this.size;
    }

    public double getX() {
        return this.xpos;
    }

    public double getTargetY() {
        return this.targety;
    }

    public double getReloadCooldown() {
        return this.reloadCooldown;
    }

    public double getTargetX() {
        return this.targetx;
    }

    public double getY() {
        return this.ypos;
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

    public void setTargetY(double targety) {
        this.targety = targety;
    }

    public void setTargetX(double targetx) {
        this.targetx = targetx;
    }
}
