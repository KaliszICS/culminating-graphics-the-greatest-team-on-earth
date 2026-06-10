package theGame;
/**
 * An interface for objects that can move.
 * Objects must implement methods to get their coordinates, check if they are marked for deletion, and update the locations
 * @author Eric Wang
 */
public interface IMovable {
    /**A method that gets the x position of the object
     * 
     * @return the x position
     */
    double getX();

    /**A method that gets the y position of the object
     * 
     * @return the y position
     */
    double getY();

    /**A method that checks if the object is marked for deletion
     * 
     * @return if the object is marked for deletion
     */
    boolean isDeleted();

    /**A method that updates the locations of the movable object in one frame */
    void move();
}