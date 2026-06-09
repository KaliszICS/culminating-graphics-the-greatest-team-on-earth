package theGame;

import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AmmoDisplayed implements IMovable {
    private double xpos = 0;
    private double ypos = 0;
    private double targetx = 0;
    private double targety = 0;
    private int timer = 60;
    private double reloadTime;
    private Node sprite;
    private boolean expiring = false;
    
    public AmmoDisplayed(double xpos, double ypos, Ammo ammo, double reloadTime) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.reloadTime = reloadTime;
        ImageView temp = new ImageView(ammo.getIcon());
        temp.setSmooth(true);
        temp.setFitHeight(10);
        temp.setFitWidth(40);
        this.sprite = temp;
    }

    public AmmoDisplayed(double xpos, double ypos, double targetx, double targety) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.targetx = targetx;
        this.targety = targety;
        this.reloadTime = 60;
        this.expiring = true;
        this.sprite = new Rectangle(40, 10);
    }

    public Node getShape() {
        return sprite;
    }

    @Override
    public boolean isDeleted() {
        return timer <= 0;
    }

    @Override
    public void move() {
        double dx = this.targetx - this.xpos;
        double dy = this.targety - this.ypos;
        this.xpos += dx*(1-Math.pow(10, -2/reloadTime));
        this.ypos += dy*(1-Math.pow(10, -2/reloadTime));
        this.sprite.setTranslateX(xpos);
        this.sprite.setTranslateY(ypos);
        if (this.expiring) {
            this.timer--;
            this.sprite.setRotate(timer*10);
            if (this.timer <= 30) {
                this.sprite.setScaleX(this.timer/30.0);
                this.sprite.setScaleY(this.timer/30.0);
            }
        }
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
        return this.targetx;
    }

    public double getTargetY() {
        return this.targety;
    }

    public void setTargetX(double targetx) {
        this.targetx = targetx;
    }

    public void setTargetY(double targety) {
        this.targety = targety;
    }
}
