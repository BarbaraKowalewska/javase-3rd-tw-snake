package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

import static com.codecool.snake.Utils.getShootByLaser;

public class Crab extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int DAMAGE = -1;
    public static final int BONUS = 1;

    public Crab(Pane pane, Double x, Double y) {
        super(pane);
        setImage(Globals.crab);
        pane.getChildren().add(this);
        setX(x);
        setY(y);
        int speed = 1;
        double direction = setRandomDirection();
        heading = Utils.directionToVector(direction, speed);
        Globals.addEnemy(this);
    }

    private double setRandomDirection() {
        Random rnd = new Random();
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        return direction;
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        getShootByLaser(this);
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeLives(DAMAGE);
        destroy();
    }

    @Override
    public String getMessage() {
        return "-1 live";
    }

}
