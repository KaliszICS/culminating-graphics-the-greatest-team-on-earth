package theGame;
/**An interface for collidable objects
 * Objects that use this interface must implement all the methods from IMovable, as well as methods to get the damage values of the object, check for immunities, and collide with other collidables
 */
public interface ICollidable extends IMovable {
    /** Gets the x position
     * 
     * @return the x position
     */
    double getX();

    /**Gets the y position
     * 
     * @return the x position
     */
    double getY();

    /**gets the size
     * 
     * @return the size
     */
    double getSize();

    /**gets the damage
     * 
     * @return the damage
     */
    int getDmg();

    /**Implementation of a collision with another collidable
     * 
     * @param a the other collidable
     */
    void collide(ICollidable a);

    /**Gets the class tagged as friendly
     * 
     * @return the class tagged as friendly
     */
    Class<? extends ICollidable> getFriend();

    /**Gets its immunity status to another collidable
     * 
     * @param col the other collidable
     * @return if this object is immune to that collidable
     */
    boolean isImmune(ICollidable col);
}