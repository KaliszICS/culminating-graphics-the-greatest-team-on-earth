package theGame;
public class Player implements IMovable {
    private double xpos;
    private double ypos;
    private int hp;
    private int money;
    private double size;
    private double speed;

    public Player() {
        this.xpos = 300;
        this.ypos = 240;
        this.hp = 10;
        this.money = 100;
        this.size = 25;
        this.speed = 7.5;
    }

    public double getSize() {
        return this.size;
    }

    public double getX() {
        return this.xpos;
    }

    public double getY() {
        return this.ypos;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void move(int x, int y) {

    }
}
