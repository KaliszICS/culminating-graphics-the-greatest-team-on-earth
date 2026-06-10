package theGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.image.*;
import java.util.*;

/**The class that runs the game and handles graphics
 * @author Eric Wang
 */

public class HelloFX extends Application {
    final double BOARD_Y = 600;
    final double BOARD_X = 1000;
    double targetX = BOARD_X/2;
    double targetY = BOARD_Y/2;
    boolean movingUp = false;
    boolean movingDown = false;
    boolean movingLeft = false;
    boolean movingRight = false;
    double mousex = 0;
    double mousey = 0;
    double rotAngle = 0;
    boolean firing = false;
    boolean specialing = false;
    int wave = 1;
    int diff = 1;
    int titleTimer = 0;
    Player p;
    
    /**The game, basically */
    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        Pane titlePane = new Pane();
        Scene titleScene = new Scene(titlePane, BOARD_X, BOARD_Y);
        Pane root = new Pane();
        Scene scene = new Scene(root, BOARD_X, BOARD_Y);
        Pane shopPane = new Pane();
        Scene shopScene = new Scene(shopPane, BOARD_X, BOARD_Y);
        Pane deathPane = new Pane();
        Scene deathScene = new Scene(deathPane, BOARD_X, BOARD_Y);
        p = new Player();
        
        /*
        * THE TITLE SCREEN
        */
        Label theText = new Label("A game by the \"greatest team on earth\"");
        titlePane.getChildren().add(theText);
        theText.setTranslateX(BOARD_X/2-100);
        theText.setTranslateY(BOARD_Y/2-150);
        Button newGame = new Button("New Game");
        newGame.setFocusTraversable(false);
        newGame.setTranslateX(BOARD_X/2-100);
        newGame.setTranslateY(BOARD_Y/2-50);
        AnimationTimer titleAnim = new AnimationTimer() {
            @Override
            public void handle(long now) {
                titleTimer++;
                if (titleTimer < 120) {
                    theText.setScaleX(titleTimer/40.0);
                    theText.setScaleY(titleTimer/40.0);
                } else if (titleTimer == 180) {
                    theText.setText("By Eric Wang and Lukas Herljevic");
                } else if (titleTimer == 240) {
                    theText.setText("Brotato if it was bad");
                } else if (titleTimer == 300) {
                    titlePane.getChildren().add(newGame);
                }
            }
        };

        /* 
         * The Shop
         */
        Button nextWave = new Button("Next Wave");
        shopPane.getChildren().add(nextWave);

        titleAnim.start();
        scene.setOnMousePressed(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                firing = true;
            } else if (e.getButton().equals(MouseButton.SECONDARY)) {
                specialing = true;
            }
        });

        scene.setOnMouseReleased(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                firing = false;
            } else if (e.getButton().equals(MouseButton.SECONDARY)) {
                specialing = false;
            }
        });

        scene.setOnMouseMoved(e -> {
            mousex = e.getX();
            mousey = e.getY();
        });

        scene.setOnMouseDragged(e -> {
            mousex = e.getX();
            mousey = e.getY();
        });

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                movingUp = true;
            }
            if (e.getCode() == KeyCode.RIGHT) {
                movingRight = true;
            }
            if (e.getCode() == KeyCode.DOWN) {
                movingDown = true;
            }
            if (e.getCode() == KeyCode.LEFT) {
                movingLeft = true;
            }
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.UP) {
                movingUp = false;
            }
            if (e.getCode() == KeyCode.RIGHT) {
                movingRight = false;
            }
            if (e.getCode() == KeyCode.DOWN) {
               movingDown = false;
           }
            if (e.getCode() == KeyCode.LEFT) {
                movingLeft = false;
            }
        });
        EventHandler<ActionEvent> gameAction = (e -> {
            root.getChildren().clear();
            p.reset();
            movingUp = false;
            movingDown = false;
            movingLeft = false;
            movingRight = false;
            mousex = 0;
            mousey = 0;
            rotAngle = 0;
            firing = false;
            specialing = false;

            Circle circle = new Circle();
            circle.setRadius(p.getSize());
            circle.setCenterX(p.getX());
            circle.setCenterY(p.getY());
            Rectangle rectangle = new Rectangle();
            root.getChildren().add(circle);
            root.getChildren().add(rectangle);
            rectangle.setWidth(p.getSize()+15);
            rectangle.setHeight(p.getSize()*0.4);
            ArrayList<IMovable> movables = new ArrayList<IMovable>();
            ArrayList<ICollidable> collidables = new ArrayList<ICollidable>();
            collidables.add(p);
            ArrayList<Node> sprites = new ArrayList<Node>();
            // Label coords = new Label("xcoord" + p.getX());
            Rectangle bag = new Rectangle(50, 50);
            bag.setX(BOARD_X - 65);
            bag.setY(15);
            Label bagNum = new Label("hi");
            bagNum.setTranslateX(BOARD_X-45);
            bagNum.setTranslateY(30);
            bagNum.setTextFill(Color.WHITE);
            bagNum.setScaleX(3);
            bagNum.setScaleY(3);
            Rectangle disc = new Rectangle(50, 50);
            disc.setX(BOARD_X - 135);
            disc.setY(15);
            Label discNum = new Label("hi");
            discNum.setTranslateX(BOARD_X-115);
            discNum.setTranslateY(30);
            discNum.setTextFill(Color.WHITE);
            discNum.setScaleX(3);
            discNum.setScaleY(3);
            Rectangle hpBar = new Rectangle(100, 40);
            Label hpNum = new Label();
            hpNum.setTextFill(Color.WHITE);
            hpNum.setScaleY(2);
            hpNum.setScaleX(2);
            hpNum.setTranslateX(50);
            GameRound round = new GameRound(wave, diff);
            root.getChildren().add(hpBar);
            root.getChildren().add(hpNum);
            root.getChildren().add(round.getTimer());
            root.getChildren().add(bag);
            root.getChildren().add(bagNum);
            root.getChildren().add(disc);
            root.getChildren().add(discNum);
            // root.getChildren().add(coords);
            ArrayList<Ammo> currentCarts = new ArrayList<Ammo>();
            HashMap<Ammo, AmmoDisplayed> cartData = new HashMap<Ammo, AmmoDisplayed>();
            // new Relic(1, "", "", 1, new ArrayList<>(List.of("Cd_-100", "Rld_-100", "Spd_+50")), "Start").applyEffect(p, "Start");
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    hpBar.setScaleX(5.0*(((double)p.getCurrentHp())/p.getMaxHp()));
                    hpNum.setText(p.getCurrentHp() + "/" + p.getMaxHp());
                    bagNum.setText(""+p.getReserve().size());
                    discNum.setText(""+p.getDiscardSize());
                    // coords.setText("xcoord:" + p.getX() + " " + p.getY());
                    round.spawnEnemies(root, sprites, movables, collidables);
                    
                    for (int i = 0; i < p.getCartridge().size(); i++) {
                        if (cartData.get(p.getCartridge().get(i)) == null) {
                            cartData.put(p.getCartridge().get(i), new AmmoDisplayed(BOARD_X-60, 40, p.getCartridge().get(i), p.getReloadTime()));
                            currentCarts.add(p.getCartridge().get(i));
                            root.getChildren().add(cartData.get(p.getCartridge().get(i)).getShape());
                        }
                        cartData.get(p.getCartridge().get(i)).setTargetX(BOARD_X - 60);
                        cartData.get(p.getCartridge().get(i)).setTargetY(15*i + 70);
                        cartData.get(p.getCartridge().get(i)).move();
                    }

                    if (firing && p.getReloadCooldown() <= 0) {
                        Ammo temp = p.shoot(mousex, mousey);
                        // movables.add(new AmmoDisplayed(cartData.get(temp).getX(), cartData.get(temp).getY(), BOARD_X-130, 30));
                        // sprites.add(((AmmoDisplayed)movables.get(movables.size()-1)).getShape());
                        // root.getChildren().add(sprites.get(sprites.size()-1));
                        // root.getChildren().remove(cartData.get(temp).getShape());
                        // cartData.remove(temp);
                        Ammo shot = (Ammo)temp.clone();
                        shot.setX(p.getX());
                        shot.setY(p.getY());
                        shot.setXVelocity(shot.getProjSpd()*Math.cos(rotAngle));
                        shot.setYVelocity(shot.getProjSpd()*Math.sin(rotAngle));
                        movables.add(shot);
                        collidables.add(shot);
                        Circle img = new Circle(shot.getSize());
                        img.setFill(new ImagePattern(shot.getShape()));
                        sprites.add(img);
                        root.getChildren().add(sprites.get(sprites.size()-1));
                    } else if (specialing && p.getReloadCooldown() <= 0) {
                        p.special();
                    }

                    for (int i = 0; i < currentCarts.size(); i++) {
                        if (!p.getCartridge().contains(currentCarts.get(i))) {
                            if (p.getReserve().contains(currentCarts.get(i))) {
                                movables.add(new AmmoDisplayed(cartData.get(currentCarts.get(i)).getX(), cartData.get(currentCarts.get(i)).getY(), currentCarts.get(i).getIcon(), BOARD_X-130, 30));
                            } else if (p.getDiscard().contains(currentCarts.get(i))) {
                                movables.add(new AmmoDisplayed(cartData.get(currentCarts.get(i)).getX(), cartData.get(currentCarts.get(i)).getY(), currentCarts.get(i).getIcon(), BOARD_X-130, 30));
                            } else {
                                movables.add(new AmmoDisplayed(cartData.get(currentCarts.get(i)).getX(), cartData.get(currentCarts.get(i)).getY(), currentCarts.get(i).getIcon(), BOARD_X+250, 250));
                            }
                            sprites.add(((AmmoDisplayed)movables.get(movables.size()-1)).getShape());
                            root.getChildren().add(sprites.get(sprites.size()-1));
                            root.getChildren().remove(cartData.get(currentCarts.get(i)).getShape());
                            cartData.remove(currentCarts.get(i));
                            currentCarts.remove(i);
                            i--;
                        }
                    }

                    rotAngle = -Math.atan2(mousex-p.getX(), mousey-p.getY())+Math.PI/2;
                    rectangle.setRotate(rotAngle/Math.PI*180);

                    if ((p.getTargetX() <= p.getSize() && p.getX() <= p.getSize() + 5)) {
                        p.setTargetX(2 * p.getSize() - p.getTargetX());
                    }
                    if ((p.getTargetX() >= BOARD_X - p.getSize() && p.getX() >= BOARD_X - p.getSize() - 5)) {
                        p.setTargetX(2*BOARD_X - 2*p.getSize() - p.getTargetX()); 
                    }
                    if ((p.getTargetY() <= p.getSize() && p.getY() <= p.getSize() + 5)) {
                        p.setTargetY(2 * p.getSize() - p.getTargetY());
                    }
                    if ((p.getTargetY() >= BOARD_Y - p.getSize() && p.getY() >= BOARD_Y - p.getSize() - 5)) {
                        p.setTargetY(2*BOARD_Y - 2*p.getSize() - p.getTargetY()); 
                    }
                    for (int i = 0; i < movables.size(); i++) {
                        movables.get(i).move();
                        if (movables.get(i) instanceof RegularAmmo) {
                            ((RegularAmmo)movables.get(i)).timerDown();
                        } else if (movables.get(i) instanceof MeleeEnemy) {
                            ((MeleeEnemy)movables.get(i)).setTargetX(p.getX());
                            ((MeleeEnemy)movables.get(i)).setTargetY(p.getY());
                        }
                        sprites.get(i).setTranslateX(movables.get(i).getX());
                        sprites.get(i).setTranslateY(movables.get(i).getY());
                        if (movables.get(i).isDeleted()) {
                            collidables.remove(movables.get(i));
                            root.getChildren().remove(sprites.get(i));
                            movables.remove(i);
                            sprites.remove(i);
                            i--;
                        }
                    }
                    for (int i = 0; i < collidables.size(); i++) {
                        for (int j = i+1; j < collidables.size(); j++) {
                            if ((collidables.get(i).getClass()) != (collidables.get(j).getClass())) {
                                if (
                                    collidables.get(i).getSize() + collidables.get(j).getSize() >=
                                    Math.sqrt(
                                        Math.pow(collidables.get(i).getX()-collidables.get(j).getX(), 2) +
                                        Math.pow(collidables.get(i).getY()-collidables.get(j).getY(), 2)
                                    )
                                ) {
                                    if (!collidables.get(i).isImmune(collidables.get(j)) && !collidables.get(j).isImmune(collidables.get(i))) {
                                        if (!(collidables.get(i) instanceof Ammo)) {
                                            DamageNumber num = new DamageNumber(collidables.get(i).getX(), collidables.get(i).getY(), collidables.get(j).getDmg());
                                            movables.add(num);
                                            sprites.add(num.getShape());
                                            root.getChildren().add(num.getShape());
                                        }
                                        collidables.get(i).collide(collidables.get(j));
                                        if (!(collidables.get(j) instanceof Ammo)) {
                                            DamageNumber num = new DamageNumber(collidables.get(j).getX(), collidables.get(j).getY(), collidables.get(i).getDmg());
                                            movables.add(num);
                                            sprites.add(num.getShape());
                                            root.getChildren().add(num.getShape());
                                        }
                                        collidables.get(j).collide(collidables.get(i));
                                    }
                                }
                            }
                        }
                    }
                    p.move();

                    rectangle.setX(p.getX() + Math.cos(rotAngle)*rectangle.getWidth()/2 - rectangle.getWidth()/2);
                    rectangle.setY(p.getY() - 0.5 * rectangle.getHeight() + Math.sin(rotAngle)*rectangle.getWidth()/2);
                    circle.setCenterX(p.getX());
                    circle.setCenterY(p.getY());

                    double vert = 0;
                    double horiz = 0;
                    if (movingUp) {
                        vert -= p.getSpeed();
                    }
                    if (movingDown) {
                        vert += p.getSpeed();
                    }
                    if (movingLeft) {
                        horiz -= p.getSpeed();
                    }
                    if (movingRight) {
                        horiz += p.getSpeed();
                    }
                    if (vert != 0 && horiz != 0) {
                        vert /= Math.sqrt(2);
                        horiz /= Math.sqrt(2);
                    }

                    p.setTargetX(p.getTargetX() + horiz);
                    p.setTargetY(p.getTargetY() + vert);
                    if (round.getCurrentTime() <= 0) {
                        wave += 10;
                        stage.setScene(shopScene);
                        this.stop();
                    }
                }
            }.start();
            stage.setScene(scene);
        });

        newGame.setOnAction(gameAction);
        nextWave.setOnAction(gameAction);
        stage.setScene(titleScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}