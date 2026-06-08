package theGame;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

import java.util.*;

public class GameRound {
    private int wave;
    private int difficulty;
    private int time;
    private int currentTime;
    private int totalPoints;
    private Label timer;
    private ArrayList<Enemy> enemies;
    private ArrayList<Integer> timers;


    public GameRound() {
        this.wave = 10;
        this.difficulty = 5;
        this.time = 21*60+60*this.wave*this.difficulty;
        this.currentTime = 20*60+60*this.wave;
        this.totalPoints = 10+5*this.wave*this.difficulty;
        this.timer = new Label();
        this.timer.setTranslateY(30);
        this.timer.setTranslateX(500);
        this.timer.setScaleX(5);
        this.timer.setScaleY(5);
        this.generateWave();
        this.generateSpawnTimers(enemies.size());
    }

    public void spawnEnemies(Pane root, ArrayList<Node> sprites, ArrayList<IMovable> movables, ArrayList<ICollidable> collidables) {
        this.currentTime--;
        this.timer.setText(""+(int)Math.floor(currentTime/60));
        for (int i = 0; i < this.timers.size(); i++) {
            if (this.currentTime == this.timers.get(i)) {
                collidables.add(this.enemies.get(i));
                movables.add(this.enemies.get(i));
                sprites.add(this.enemies.get(i).getShape());
                root.getChildren().add(this.enemies.get(i).getShape());
            }
        }
    }

    public Label getTimer() {
        return timer;
    }

    public void generateSpawnTimers(int num) {
        ArrayList<Integer> probabilities = new ArrayList<Integer>();
        for (int i = 0; i < this.time; i++) {
            // for (int j = 0; j < 5 * Math.sin(3*(((double)i/this.time)*(9*Math.PI/2))) + 5; j++) {
            //     probabilities.add(i);
            // }
            probabilities.add(i);
        }
        this.timers = new ArrayList<Integer>();
        for (int i = 0; i < num; i++) {
            this.timers.add(probabilities.get((int)(Math.random()*probabilities.size())));
        }
    }

    public void generateWave() {
        this.enemies = new ArrayList<Enemy>();
        int currentPoints = this.totalPoints;
        while (currentPoints > 0) {
            // int enemypts = (int)Math.min(currentPoints, Math.ceil(this.totalPoints*(0.5/(128*Math.random()+2))));
            int enemypts = (int)(this.totalPoints*(((double)60/this.time)-((double)3/this.time))*Math.random()+this.totalPoints*((double)3/this.time));
            currentPoints -= enemypts;
            this.enemies.add(this.generateEnemy(enemypts));
        }
    }

    public Enemy generateEnemy(int points) {
        int[] pointDistribution = new int[4];
        for (int i = 0; i < points; i++) {
            pointDistribution[(int)(Math.random()*pointDistribution.length)]++;
        }
        int dmg = 5+3*pointDistribution[0];
        int hp = 10+5*pointDistribution[1];
        double spd = 1+0.2*pointDistribution[2];
        double size = 30*Math.pow(1.05, pointDistribution[3]);
        Enemy en = new MeleeEnemy(dmg, hp, spd, size);
        en.setX(Math.random()*1000);
        en.setY(Math.random()*600);
        return en;
    }


}
