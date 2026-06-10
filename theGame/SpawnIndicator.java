package theGame;

import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.image.*;

/**
 * Visual indicator used to mark where an enemy will spawn.
 * The indicator appears at a fixed position and gradually increases in size over time. After 60 frames, it is marked for deletion.
 * @author Eric Wang
 */
class SpawnIndicator implements IMovable {

    /** The visual representation of the spawn indicator. */
    private ImageView indicator;

    /** Remaining lifetime of the indicator in update ticks. */
    private int timer;

    /** The x-coordinate of the indicator. */
    private double xpos;

    /** The y-coordinate of the indicator. */
    private double ypos;

    /**
     * Creates a spawn indicator at the specified location.
     *
     * @param xpos the x-coordinate where the indicator is displayed
     * @param ypos the y-coordinate where the indicator is displayed
     * @param size the base size used to determine the indicator's dimensions
     */
    public SpawnIndicator(double xpos, double ypos, double size, Image sprite) {
        this.indicator = new ImageView(sprite);
        this.indicator.setFitHeight(size / 2);
        this.indicator.setFitWidth(size / 2);
        this.timer = 60;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    /**
     * Updates the indicator's size and decreases its remaining lifetime.
     * The indicator scales according to a logarithmic function, creating a visual warning effect before it disappears.
     */
    @Override
    public void move() {
        this.indicator.setScaleX(Math.log(10 - timer / 6) + 1);
        this.indicator.setScaleY(Math.log(10 - timer / 6) + 1);
        timer--;
    }

    /**
     * Returns the x-coordinate of the indicator.
     *
     * @return the x-coordinate
     */
    @Override
    public double getX() {
        return this.xpos;
    }

    /**
     * Returns the y-coordinate of the indicator.
     *
     * @return the y-coordinate
     */
    @Override
    public double getY() {
        return this.ypos;
    }

    /**
     * Determines whether the indicator has expired.
     *
     * @return true if the timer has elapsed; false otherwise
     */
    @Override
    public boolean isDeleted() {
        return timer < 0;
    }

    /**
     * Returns the JavaFX node used to render the indicator.
     *
     * @return the indicator's visual node
     */
    public Node getShape() {
        return indicator;
    }
}