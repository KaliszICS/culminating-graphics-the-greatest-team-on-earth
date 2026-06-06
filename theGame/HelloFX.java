package theGame;

import javafx.application.Application;

import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.*;
import java.util.*;

public class HelloFX extends Application {
    final double BOARD_Y = 400;
    final double BOARD_X = 600;
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
    
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Player p = new Player();
        Circle circle = new Circle();
        circle.setRadius(p.getSize());
        circle.setCenterX(p.getX());
        circle.setCenterY(p.getY());
        Rectangle rectangle = new Rectangle();
        Scene scene = new Scene(root, BOARD_X, BOARD_Y);
        root.getChildren().add(circle);
        root.getChildren().add(rectangle);
        rectangle.setWidth(p.getSize()+15);
        rectangle.setHeight(p.getSize()*0.4);
        ArrayList<IMovable> movables = new ArrayList<IMovable>();
        ArrayList<ICollidable> collidables = new ArrayList<ICollidable>();
        ArrayList<Node> sprites = new ArrayList<Node>();
        Label coords = new Label("xcoord" + p.getX());
        Rectangle bag = new Rectangle(50, 50);
        bag.setX(BOARD_X - 65);
        bag.setY(15);
        Label bagNum = new Label("hi");
        bagNum.setTranslateX(BOARD_X-45);
        bagNum.setTranslateY(30);
        bagNum.setTextFill(Color.WHITE);
        bagNum.setScaleX(3);
        bagNum.setScaleY(3);
//        ImageView lol = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_2LPYRlundRD0JEWh_Hp5o5T4miDl42J2XQ&s");
        Rectangle disc = new Rectangle(50, 50);
        disc.setX(BOARD_X - 135);
        disc.setY(15);
        Label discNum = new Label("hi");
        discNum.setTranslateX(BOARD_X-115);
        discNum.setTranslateY(30);
        discNum.setTextFill(Color.WHITE);
        discNum.setScaleX(3);
        discNum.setScaleY(3);
        Enemy en = new MeleeEnemy();
        collidables.add(en);
        movables.add(en);
        sprites.add(en.getShape());
        root.getChildren().add(sprites.get(0));
        root.getChildren().add(bag);
        root.getChildren().add(bagNum);
        root.getChildren().add(disc);
        root.getChildren().add(discNum);
        root.getChildren().add(coords);
        // root.getChildren().add(lol);
        HashMap<Ammo, AmmoDisplayed> cartData = new HashMap<Ammo, AmmoDisplayed>();
        // new Relic(1, 1, "", 1, new ArrayList<>(List.of("Cd_-100", "Rld_-100", "Spd_+50")), "").applyEffect(p);
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
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                bagNum.setText(""+p.getReserve().size());
                discNum.setText(""+p.getDiscardSize());
                coords.setText("xcoord:" + p.getX() + " " + p.getY());
                
                for (int i = 0; i < p.getCartridge().size(); i++) {
                    if (cartData.get(p.getCartridge().get(i)) == null) {
                        cartData.put(p.getCartridge().get(i), new AmmoDisplayed(BOARD_X-60, 40, p.getCartridge().get(i), p.getReloadTime()));
                        root.getChildren().add(cartData.get(p.getCartridge().get(i)).getShape());
                    }
                    cartData.get(p.getCartridge().get(i)).setTargetX(BOARD_X - 60);
                    cartData.get(p.getCartridge().get(i)).setTargetY(15*i + 70);
                    cartData.get(p.getCartridge().get(i)).move();
                }

                if (firing && p.getReloadCooldown() <= 0) {
                    Ammo temp = p.shoot(mousex, mousey);
                    movables.add(new AmmoDisplayed(cartData.get(temp).getX(), cartData.get(temp).getY(), BOARD_X-130, 30));
                    sprites.add(((AmmoDisplayed)movables.get(movables.size()-1)).getShape());
                    root.getChildren().add(sprites.get(sprites.size()-1));
                    root.getChildren().remove(cartData.get(temp).getShape());
                    cartData.remove(temp);
                    Ammo shot = (Ammo)temp.clone();
                    shot.setX(p.getX());
                    shot.setY(p.getY());
                    shot.setXVelocity(shot.getProjSpd()*Math.cos(rotAngle));
                    shot.setYVelocity(shot.getProjSpd()*Math.sin(rotAngle));
                    movables.add(shot);
                    collidables.add(shot);
                    sprites.add(new Circle(shot.getSize()));
                    root.getChildren().add(sprites.get(sprites.size()-1));
                } else if (specialing && p.getReloadCooldown() <= 0) {
                    p.special();
                }

                // for (int i = cart.size(); i > p.getCartridge().size(); i--) {
                //     root.getChildren().remove(cart.remove(i-1));
                //     cartData.remove(i-1);
                // }

                // for (int i = cart.size(); i < p.getCartridge().size(); i++) {
                //     cartData.add(new AmmoDisplayed(BOARD_X-60, 40, p.getCartridge().get(i), p.getReloadTime()));
                //     cart.add(new Rectangle(40, 10));
                //     cartData.get(i).setTargetX(BOARD_X - 60);
                //     cartData.get(i).setTargetY(15*i + 70);
                //     root.getChildren().add(cart.get(i));
                // }


                // for (int i = 0; i < cart.size(); i++) {
                //     cartData.get(i).move();
                //     cart.get(i).setTranslateX(cartData.get(i).getX());
                //     cart.get(i).setTranslateY(cartData.get(i).getY());
                // }

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

                // double dx = targetX - circle.getCenterX();
                // double dy = targetY - circle.getCenterY();

                // circle.setCenterX(circle.getCenterX() + dx * 0.1);
                // circle.setCenterY(circle.getCenterY() + dy * 0.1);
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
                                collidables.get(i).collide(collidables.get(j));
                                collidables.get(j).collide(collidables.get(i));
                                if (collidables.get(i) instanceof Enemy) {
                                    DamageNumber num = new DamageNumber(collidables.get(i).getX(), collidables.get(i).getY(), collidables.get(j).getDmg());
                                    movables.add(num);
                                    sprites.add(num.getShape());
                                    root.getChildren().add(num.getShape());
                                } else if (collidables.get(j) instanceof Enemy) {
                                    DamageNumber num = new DamageNumber(collidables.get(j).getX(), collidables.get(j).getY(), collidables.get(i).getDmg());
                                    movables.add(num);
                                    sprites.add(num.getShape());
                                    root.getChildren().add(num.getShape());
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
                // lol.setX(p.getX());
                // lol.setY(p.getY());

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
            }
        }.start();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}