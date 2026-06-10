package theGame;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

import java.util.*;
/**A class that handles the rounds of the game
 * It handles the spawning of enemies and indicators according to the wave and difficulty of the round
 * @author Eric Wang
 */
public class GameRound {
    /**The wave number of the round */
    private int wave;

    /**the difficulty of the round */
    private int difficulty;

    /**the time of the round (in frames) */
    private int time;

    /**The current time left in the round */
    private int currentTime;

    /**the total number of enemy points that can be allocated to spawning */
    private int totalPoints;

    /**a visible timer to show the amount of time left */
    private Label timer;

    /**the arraylist of enemies */
    private ArrayList<Enemy> enemies;

    /**An arraylist of timers to determine when to spawn the enemies */
    private ArrayList<Integer> timers;

    /**An image for the spawn indicator */
    private Image indicatorIcon = new Image("https://static.vecteezy.com/system/resources/previews/017/178/088/non_2x/red-hazard-warning-sign-on-transparent-background-free-png.png");

    /** A constructor that takes in a wave and difficulty value, and sets up the timer, and generates a wave of enemies according to the amount of avaliable enemy points
     * 
     * @param wave The wave of the round
     * @param diff The difficulty of the round
     */
    public GameRound(int wave, int diff) {
        this.wave = wave;
        this.difficulty = diff;
        this.time = 5*60+30*wave;
        this.currentTime = this.time;
        this.totalPoints = 10+(int)(10*Math.pow(1+((double)this.wave/5), 2)*diff);
        this.timer = new Label();
        this.timer.setTranslateY(30);
        this.timer.setTranslateX(500);
        this.timer.setScaleX(5);
        this.timer.setScaleY(5);
        this.generateWave();
        this.generateSpawnTimers(this.enemies.size());
    }


    /**Handles the spawning of enemies frame by frame, also updates the timer
     * 
     * @param root          The visual Pane that the sprites are put on
     * @param sprites       An arraylist of sprites to store them for updating later
     * @param movables      An arraylist of movables to add the enemies to
     * @param collidables   An arraylist of collidables to add the enemies to
     */
    public void spawnEnemies(Pane root, ArrayList<Node> sprites, ArrayList<IMovable> movables, ArrayList<ICollidable> collidables) {
        this.currentTime--;
        this.timer.setText(""+currentTime/60);
        for (int i = 0; i < this.timers.size(); i++) {
            if (this.currentTime == this.timers.get(i) + 60) {
                double spawnx = Math.random()*1000;
                double spawny = Math.random()*600;
                SpawnIndicator temp = new SpawnIndicator(spawnx, spawny, this.enemies.get(i).getSize(), this.indicatorIcon);
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

    /**Gets the visual timer
     * 
     * @return getTimer();
     */
    public Label getTimer() {
        return timer;
    }

    /**Gets the amount of time left in frames
     * 
     * @return the current time left
     */
    public int getCurrentTime() {
        return this.currentTime;
    }

    /** A method that generates when the enemies will be spawned in the wave
     * 
     * @param num
     */
    public void generateSpawnTimers(int num) {
        ArrayList<Integer> probabilities = new ArrayList<Integer>();
        for (int i = 0; i < this.time - 60; i++) {
            probabilities.add(i);
        }
        this.timers = new ArrayList<Integer>();
        for (int i = 0; i < num; i++) {
            this.timers.add(probabilities.get((int)(Math.random()*probabilities.size())));
        }
    }

    /**The method that generates all the enemies according to the avaliable points
     * Point allocation is limited in scope from a maximum of 1 second worth of points or a minimum of 0.05 seconds worth of points
     */
    public void generateWave() {
        this.enemies = new ArrayList<Enemy>();
        int currentPoints = this.totalPoints;
        while (currentPoints > 0) {
            // int enemypts = (int)Math.min(currentPoints, Math.ceil(this.totalPoints*(0.5/(128*Math.random()+2))));
            int enemypts = (int)Math.ceil(
                this.totalPoints*((60.0/this.time) - (3.0/this.time))
                 * Math.random()
                 + this.totalPoints * (3.0/this.time)
                );
            currentPoints -= enemypts;
            this.enemies.add(this.generateEnemy(enemypts));
        }
    }

    /**Generates an enemy with randomly distributed stats based off a specific number of enemy points
     * 
     * @param points the number of enemy points to be allocated
     * @return the enemy generated
     */
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
