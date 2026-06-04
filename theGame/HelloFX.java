package theGame;

import javafx.application.Application;

import java.awt.Color;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.*;

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
    int timer = 0;
    
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
        rectangle.setFill(javafx.scene.paint.Color.RED);
        scene.setOnMouseClicked(e -> {
            
            // if (timer <= 0) {
            //     p.setTargetX(2*p.getX()-e.getX());
            //     p.setTargetY(2*p.getY()-e.getY());
            //     timer = 30;
            // }
            if (p.getReloadCooldown() <= 0) {
                p.shoot(e.getX(), e.getY());
            }
        });

        scene.setOnMouseMoved(e -> {
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
                rotAngle = -Math.atan2(mousex-p.getX(), mousey-p.getY())+Math.PI/2;
                rectangle.setRotate(rotAngle/Math.PI*180);

                timer -= 1;

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
            }
        }.start();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}