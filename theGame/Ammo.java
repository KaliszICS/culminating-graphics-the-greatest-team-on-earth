package theGame;

abstract class Ammo implements IMovable, ICollidable {
    private int damage;
    private double projSpd;
    private double recoil;
    private String upgradeable;
    private double xpos;
    private double ypos;
    private double duration;
    private double size;

    abstract void upgrade();

    @Override
    public double getX() {
        return this.xpos;
    }

    @Override
    public double getY() {
        return this.ypos;
    }

    public int getDamage() {
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

    public double getSize() {
        return this.size;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setUpgradeable(String upgradeable) {
        this.upgradeable = upgradeable;
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

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setSize(double size) {
        this.size = size;
    }
}

