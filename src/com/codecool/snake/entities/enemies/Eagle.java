package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import static com.codecool.snake.Utils.findSnakeHead;
import static com.codecool.snake.Utils.getShootByLaser;


public class Eagle extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private SnakeHead snakeHead;
    private static final int DAMAGE = -1;
    public static final int BONUS = 2;

    public Eagle(Pane pane, Double x, Double y) {
        super(pane);
        setImage(Globals.eagle);
        pane.getChildren().add(this);
        setX(x);
        setY(y);
        setSnakeHead(findSnakeHead());
        Globals.addEnemy(this);
    }

    public void setSnakeHead(SnakeHead snakeHead) {
        this.snakeHead = snakeHead;
    }

    private double getDirectionToSnake() {
        return  (Math.atan2(snakeHead.getY() - getY(),
                snakeHead.getX() - getX()) * 180 / Math.PI) + 90;
    }


    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        // make eagle follow the snake
        double dir = getDirectionToSnake();
        int speed = 1;
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        setRotate(dir);

        getShootByLaser(this);
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeLives(DAMAGE);
        destroy();

    }

    @Override
    public String getMessage() {
        return "Eaten by Eagle";
    }

}

