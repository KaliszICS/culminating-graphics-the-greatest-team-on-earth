import javafx.application.Application;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HelloFX extends Application {
    double targetX = 300;
    double targetY = 240;
    boolean movingUp = false;
    boolean movingDown = false;
    boolean movingLeft = false;
    boolean movingRight = false;
    double speed = 10.0;
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Circle circle = new Circle();
        circle.setRadius(25.0);
        Scene scene = new Scene(root, 640, 480);
        root.getChildren().add(circle);
        scene.setOnMouseClicked(e -> {
            targetX = circle.getCenterX()-(e.getX()-circle.getCenterX());
            targetY = circle.getCenterY()-(e.getY()-circle.getCenterY());
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
                double dx = targetX - circle.getCenterX();
                double dy = targetY - circle.getCenterY();

                circle.setCenterX(circle.getCenterX() + dx * 0.1);
                circle.setCenterY(circle.getCenterY() + dy * 0.1);

                double vert = 0;
                double horiz = 0;
                if (movingUp) {
                    vert -= speed;
                }
                if (movingDown) {
                    vert += speed;
                }
                if (movingLeft) {
                    horiz -= speed;
                }
                if (movingRight) {
                    horiz += speed;
                }
                if (vert != 0 && horiz != 0) {
                    vert /= Math.sqrt(2);
                    horiz /= Math.sqrt(2);
                }

                targetX += horiz;
                targetY += vert;
            }
        }.start();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}