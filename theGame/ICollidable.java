package theGame;
public interface ICollidable {
    double getX();
    double getY();
    double getSize();
    int getDmg();
    void collide(ICollidable a);
}