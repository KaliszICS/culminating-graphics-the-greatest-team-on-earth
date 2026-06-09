package theGame;

import javafx.scene.image.*;

abstract class Ammo extends Item implements ICollidable, Cloneable {
    protected int damage;
    protected double projSpd;
    protected double recoil;
    protected String upgradeable;
    protected double xpos;
    protected double ypos;
    protected double xvelocity;
    protected double yvelocity;
    protected double duration;
    protected double size;
    protected int fireDelay;
    protected Class<? extends ICollidable> friendly;

    abstract void upgrade();

    abstract Image getShape();

    abstract Image getIcon();

    @Override
    public Object clone(){  
        try{  
            return super.clone();  
        }catch(Exception e){ 
            return null; 
        }
    }

    @Override
    public Class<? extends ICollidable> getFriend () {
        return friendly;
    }

    @Override
    public double getX() {
        return this.xpos;
    }

    @Override
    public double getY() {
        return this.ypos;
    }

    public double getYVelocity() {
        return yvelocity;
    }

    public double getXVelocity() {
        return xvelocity;
    }

    @Override
    public int getDmg() {
        return this.damage;
    }

    public double getProjSpd() {
        return this.projSpd;
    }

    public double getRecoil() {
        return this.recoil;
    }

    public String getUpgradeable() {
        return this.upgradeable;
    }

    public double getDuration() {
        return this.duration;
    }

    @Override
    public double getSize() {
        return this.size;
    }

    public int getFireDelay() {
        return this.fireDelay;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setUpgradeable(String upgradeable) {
        this.upgradeable = upgradeable;
    }

    public void setProjSpd(double projSpd) {
        this.projSpd = projSpd;
    }

    public void setRecoil(double recoil) {
        this.recoil = recoil;
    }

    public void setX(double xpos) {
        this.xpos = xpos;
    }

    public void setY(double ypos) {
        this.ypos = ypos;
    }

    public void setXVelocity(double xvelocity) {
        this.xvelocity = xvelocity;
    }

    public void setYVelocity(double yvelocity) {
        this.yvelocity = yvelocity;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setFireDelay(int fireDelay) {
        this.fireDelay = fireDelay;
    } 

    public void setFriend(Class<? extends ICollidable> friendly) {
        this.friendly = friendly;
    }
}

