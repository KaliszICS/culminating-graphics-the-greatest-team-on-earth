package theGame;

import javafx.scene.image.ImageView;
import javafx.scene.Node;

class SpawnIndicator implements IMovable {
    private ImageView indicator;
    private int timer;
    private double xpos;
    private double ypos;

    public SpawnIndicator(double xpos, double ypos, double size) {
        this.indicator = new ImageView("https://static.vecteezy.com/system/resources/previews/017/178/088/non_2x/red-hazard-warning-sign-on-transparent-background-free-png.png");
        this.indicator.setFitHeight(size/2);
        this.indicator.setFitWidth(size/2);
        this.timer = 60;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    @Override
    public void move() {
        this.indicator.setScaleX(Math.log(10-timer/6)+1);
        this.indicator.setScaleY(Math.log(10-timer/6)+1);
        timer--;
    }

    @Override
    public double getX() {
        return this.xpos;
    }

    @Override
    public double getY() {
        return this.ypos;
    }

    @Override
    public boolean isDeleted() {
        return timer < 0;
    }

    public Node getShape() {
        return indicator;
    }
}