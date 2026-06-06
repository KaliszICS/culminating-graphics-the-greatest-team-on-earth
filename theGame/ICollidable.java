package theGame;
public interface ICollidable extends IMovable {
    double getX();
    double getY();
    double getSize();
    int getDmg();
    void collide(ICollidable a);
}