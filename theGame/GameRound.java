package theGame;

import javafx.scene.effect.Light.Point;

public class GameRound {
    private int wave;
    private int difficulty;
    private int time;
    private int points;


    public GameRound() {
        this.wave = 1;
        this.difficulty = 1;
        this.time = 20+this.wave*this.difficulty;
        this.points = 10+this.wave*this.difficulty;
    }

    public Enemy generateEnemy() {
        int[] pointDistribution = new int[4];
        for (int i = 0; i < points; i++) {
            pointDistribution[(int)(Math.random()*pointDistribution.length)]++;
        }
        int dmg = 5+3*pointDistribution[0];
        int hp = 10+5*pointDistribution[1];
        double spd = 1+0.1*pointDistribution[2];
        double size = 30*Math.pow(0.95, pointDistribution[3]);
        Enemy en = new MeleeEnemy(dmg, hp, spd, size);
        en.setX(Math.random()*1000);
        en.setY(Math.random()*600);
        return en;
    }


}
