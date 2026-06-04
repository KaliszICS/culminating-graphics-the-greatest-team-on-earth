package theGame;
public interface ICollidable {
    double getX();
    double getY();
    void collide(ICollidable a);
}