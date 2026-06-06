package theGame;

import javafx.scene.control.Label;
import javafx.scene.Node;

public class DamageNumber implements IMovable {
    private double xpos;
    private double ypos;
    private double targetx;
    private double targety;
    private int number;
    private int timer;
    private Label text;

    public DamageNumber(double xpos, double ypos, int number) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.number = number;
        this.text = new Label(""+number);
        this.text.setTranslateX(xpos);
        this.text.setTranslateY(ypos);
        this.text.setScaleX(3);
        this.text.setScaleY(3);
        this.timer = 60;
        double angle = 90*(Math.random()-0.5);
        this.targetx = xpos+100*Math.sin(angle/180*Math.PI);
        this.targety = ypos-100*Math.cos(angle/180*Math.PI);
    }
    
    @Override
    public void move() {
        timer--;
        text.setRotate(10*Math.sin(timer/4));
        double dx = this.targetx - this.xpos;
        double dy = this.targety - this.ypos;
        this.xpos += dx*0.1;
        this.ypos += dy*0.1;
    }

    @Override
    public double getY() {
        return ypos;
    }

    @Override
    public double getX() {
        return xpos;
    }

    public Node getShape() {
        return this.text;
    }

    @Override
    public boolean isDeleted() {
        return timer <= 0;
    }
}