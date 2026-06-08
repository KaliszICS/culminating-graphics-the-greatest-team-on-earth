package theGame;

import javafx.scene.image.ImageView;
import javafx.scene.Node;

class SpawnIndicator implements IMovable {
    private ImageView indicator;
    private int timer;
    private double xpos;
    private double ypos;

    public SpawnIndicator(double xpos, double ypos) {
        this.indicator = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_2LPYRlundRD0JEWh_Hp5o5T4miDl42J2XQ&s");
        this.indicator.setFitHeight(40);
        this.indicator.setFitWidth(40);
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