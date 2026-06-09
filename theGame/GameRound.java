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
    private double[][] spawnLocations;

    public GameRound() {
        this.wave = 1;
        this.difficulty = 1;
        this.time = 21*60+30*this.wave;
        this.currentTime = this.time;
        this.totalPoints = 10+(int)(10*Math.pow(1+((double)this.wave/5), 2)*this.difficulty);
        this.timer = new Label();
        this.timer.setTranslateY(30);
        this.timer.setTranslateX(500);
        this.timer.setScaleX(5);
        this.timer.setScaleY(5);
        this.generateWave();
        this.generateSpawnTimers(this.enemies.size());
        this.spawnLocations = new double[this.enemies.size()][2];
    }

    public void spawnEnemies(Pane root, ArrayList<Node> sprites, ArrayList<IMovable> movables, ArrayList<ICollidable> collidables) {
        this.currentTime--;
        this.timer.setText(""+(int)Math.floor(currentTime/60));
        for (int i = 0; i < this.timers.size(); i++) {
            if (this.currentTime == this.timers.get(i) + 60) {
                double spawnx = Math.random()*1000;
                double spawny = Math.random()*600;
                SpawnIndicator temp = new SpawnIndicator(spawnx, spawny, enemies.get(i).getSize());
                movables.add(temp);
                sprites.add(temp.getShape());
                root.getChildren().add(temp.getShape());
                this.enemies.get(i).setX(spawnx);
                this.enemies.get(i).setY(spawny);
            } else if (this.currentTime == this.timers.get(i)) {
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
        for (int i = 0; i < this.time - 60; i++) {
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
            int enemypts = (int)Math.ceil(this.totalPoints*(((double)60/this.time)-((double)3/this.time))*Math.random()+this.totalPoints*((double)3/this.time));
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
        en.setValue(points);
        return en;
    }


}
