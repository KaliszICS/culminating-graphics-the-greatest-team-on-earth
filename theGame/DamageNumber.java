package theGame;

import javafx.scene.control.Label;
import javafx.scene.Node;

/**
 * A temporary floating damage indicator
 * @author Eric Wang
 */
public class DamageNumber implements IMovable {

    /**the current x position */
    private double xpos;

    /**the current y position */
    private double ypos;

    /**the current target x position */
    private double targetx;

    /**the current target y position */
    private double targety;

    /**not used, the current damage value */
    private int number;

    /**the remaining time on the lifespan in frames */
    private int timer;

    /**the text object that is the damage number */
    private Label text;

    /**
     * Constructs a DamageNumber at the given position with the specified value.
     * The number will animate toward a randomized offset target position and disappear after a short duration.
     *
     * @param xpos   initial x position
     * @param ypos   initial y position
     * @param number damage value to display
     */
    public DamageNumber(double xpos, double ypos, int number) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.number = number;

        this.text = new Label("" + number);
        this.text.setTranslateX(xpos);
        this.text.setTranslateY(ypos);
        this.text.setScaleX(3);
        this.text.setScaleY(3);

        this.timer = 60;

        double angle = 90 * (Math.random() - 0.5);
        this.targetx = xpos + 100 * Math.sin(angle / 180 * Math.PI);
        this.targety = ypos - 100 * Math.cos(angle / 180 * Math.PI);
    }

    /**
     * Updates the position and rotation of the damage number
     * It has a little shake effect based on the remaining timer
     */
    @Override
    public void move() {
        timer--;
        text.setRotate(10.0 * Math.sin(timer / 4.0));

        double dx = this.targetx - this.xpos;
        double dy = this.targety - this.ypos;

        this.xpos += dx * 0.1;
        this.ypos += dy * 0.1;
    }

    /**
     * Returns the current y position of the damage number.
     *
     * @return the y position
     */
    @Override
    public double getY() {
        return ypos;
    }

    /**
     * Returns the current x position of the damage number.
     *
     * @return the x position
     */
    @Override
    public double getX() {
        return xpos;
    }

    /**
     * Returns the JavaFX node representing this damage number.
     *
     * @return the label of the damagenumber
     */
    public Node getShape() {
        return this.text;
    }

    /**
     * Determines whether this damage number is marked for deletion
     *
     * @return true if its lifetime has expired, false otherwise
     */
    @Override
    public boolean isDeleted() {
        return timer <= 0;
    }
}