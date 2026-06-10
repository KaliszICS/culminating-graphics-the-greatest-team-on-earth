package theGame;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Represents a visual ammo icon displayed in the game UI.
 * AmmoDisplayed objects smoothly move toward a target position and can optionally expire with a spinning and shrinking animation. These are
 * Used to display cartridge ammos
 * @author Eric Wang
 */
public class AmmoDisplayed implements IMovable {
    /**the x position */
    private double xpos = 0;

    /**the y position */
    private double ypos = 0;

    /**the target x position */
    private double targetx = 0;

    /**the target y position */
    private double targety = 0;

    /**the timer for the expiring version */
    private int timer = 60;

    /**the travel time of the displayed ammos */
    private double reloadTime;

    /**the sprite of the ammo icon */
    private Node sprite;

    /**a toggle for if the ammo display is expiring */
    private boolean expiring = false;

    /**
     * Constructs a displayed ammo icon associated with an Ammo object.
     *
     * @param xpos       initial x-coordinate
     * @param ypos       initial y-coordinate
     * @param ammo       the ammo whose icon will be displayed
     * @param reloadTime the amount of time the weapon takes to reload, also used to time how long it should take for the bullet to reach the intended location so it looks good
     */
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

    /**
     * Constructs an expiring displayed ammo icon.
     * This version moves toward a target position and automatically disappears after a short duration while spinning and shrinking.
     *
     * @param xpos    initial x coordinate
     * @param ypos    initial y coordinate
     * @param icon    image to display
     * @param targetx target x coordinate
     * @param targety target y coordinate
     */
    public AmmoDisplayed(double xpos, double ypos, Image icon, double targetx, double targety) {

        this.xpos = xpos;
        this.ypos = ypos;
        this.targetx = targetx;
        this.targety = targety;
        this.reloadTime = 60;
        this.expiring = true;

        ImageView temp = new ImageView(icon);
        temp.setSmooth(true);
        temp.setFitHeight(10);
        temp.setFitWidth(40);

        this.sprite = temp;
    }

    /**
     * Returns the javafx node for the sprite (in this case it's an imageviewer)
     *
     * @return the sprite
     */
    public Node getShape() {
        return sprite;
    }

    /**
     * Determines whether this object is marked for deletion
     *
     * @return true if its has reached zero, false otherwise
     */
    @Override
    public boolean isDeleted() {
        return timer <= 0;
    }

    /**
     * Updates the position of the display
     * Shifts towards its target position, and if it's been initialized as the expiring version it'll spin and shrink until it dissapears
     */
    @Override
    public void move() {
        double dx = this.targetx - this.xpos;
        double dy = this.targety - this.ypos;

        this.xpos += dx * (1 - Math.pow(10, -2 / reloadTime));
        this.ypos += dy * (1 - Math.pow(10, -2 / reloadTime));

        this.sprite.setTranslateX(xpos);
        this.sprite.setTranslateY(ypos);

        if (this.expiring) {
            this.timer--;

            this.sprite.setRotate(timer * 10);

            if (this.timer <= 30) {
                this.sprite.setScaleX(this.timer / 30.0);
                this.sprite.setScaleY(this.timer / 30.0);
            }
        }
    }

    /**
     * Returns the current x position
     *
     * @return current x position
     */
    @Override
    public double getX() {
        return this.xpos;
    }

    /**
     * Returns the current y position
     *
     * @return current y position
     */
    @Override
    public double getY() {
        return this.ypos;
    }

    /**
     * Returns the target x-coordinate.
     *
     * @return target x position
     */
    public double getTargetX() {
        return this.targetx;
    }

    /**
     * Returns the target y position
     *
     * @return target y position
     */
    public double getTargetY() {
        return this.targety;
    }

    /**
     * Sets the target x position
     *
     * @param targetx new target x position
     */
    public void setTargetX(double targetx) {
        this.targetx = targetx;
    }

    /**
     * Sets the target y position
     *
     * @param targety new target y position
     */
    public void setTargetY(double targety) {
        this.targety = targety;
    }
}