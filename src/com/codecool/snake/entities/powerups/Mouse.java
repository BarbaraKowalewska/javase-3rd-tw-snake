package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Heart;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import javafx.geometry.Point2D;

import static com.codecool.snake.Utils.getShootByLaser;


public class Mouse extends GameEntity implements Animatable, Interactable {


    private Point2D heading;
    private SnakeHead snakeHead;
    private double speed;

    public Mouse (Pane pane, Double x, Double y) {
        super(pane);
        setImage(Globals.mouse);
        pane.getChildren().add(this);
        setX(x);
        setY(y);

        setSpeed(0.33);

        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof SnakeHead) {
                setSnakeHead((SnakeHead) gameObject);
            }
        }
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(SnakeHead snakeHead) {
        this.snakeHead = snakeHead;
    }


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        // make mouse run away the snake
        double dir = (Math.atan2(snakeHead.getY() - getY(), snakeHead.getX() - getX()) * 180 / Math.PI) - 90;
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        setRotate(dir);

        getShootByLaser(this);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        Globals.snakeLength +=4;
        snakeHead.addPart(4);
        snakeHead.changeScore(4);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Ate Mouse:)";
    }

}
