package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import static com.codecool.snake.Utils.findSnakeHead;

public class Laser extends GameEntity implements Animatable, Interactable {
    private Point2D heading;
    private SnakeHead snakeHead;
    private boolean isSuperPowerOn;
    private double dir;
    private int speed;


    // isSuperPowerOn
    private GameEntity target;          // enemy that the laser follows
    private GameEntity secondTarget;    // enemy that the 2nd laser follows
    private double lastDirection;


    Laser(Pane pane, boolean isSuperPowerOn, boolean secondLaser) {
        super(pane);
        setSpeed(isSuperPowerOn? 20 : 10); // projectile speed with superPower and without
        setSuperPower(isSuperPowerOn);
        setImage(Globals.laser);
        pane.getChildren().add(this);
        setSnakeHead(findSnakeHead());
        setDir(snakeHead.getRotate());
        setX(snakeHead.getX()+15);
        setY(snakeHead.getY()+8);
        lastDirection = getDir();

        // isSuperPowerOn

        double closestDistanceToTarget = Math.sqrt(Math.pow(Globals.WINDOW_HEIGHT, 2) + Math.pow(Globals.WINDOW_WIDTH, 2));
        // diagonal of game window, just to have the biggest possible distance in the game

        double secondClosestDistanceToTarget = closestDistanceToTarget;
        for (GameEntity enemy : Globals.enemies) {
            double currentDistanceToTarget = Math.sqrt(Math.pow(enemy.getX() - getX(), 2) + Math.pow(enemy.getY() - getY(), 2));
            if (currentDistanceToTarget < closestDistanceToTarget) {
                secondClosestDistanceToTarget = closestDistanceToTarget;
                closestDistanceToTarget = currentDistanceToTarget;
                target = enemy;
            } else if (currentDistanceToTarget < secondClosestDistanceToTarget && currentDistanceToTarget > closestDistanceToTarget) {
                secondClosestDistanceToTarget = currentDistanceToTarget;
                secondTarget = enemy;
            }
        }
        target = secondLaser? secondTarget : target;
    }

    public void setSnakeHead(SnakeHead snakeHead) {
        this.snakeHead = snakeHead;
    }

    private void setSuperPower(boolean isSuperPowerOn) {
        this.isSuperPowerOn = isSuperPowerOn;
    }

    private double getDir() {
        return dir;
    }

    private void setDir(double dir) {
        this.dir = dir;
    }

    private void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        // shoot lasers from snakeHead at the target
        if (isSuperPowerOn && Globals.getEnemies().contains(target)) {
            setDir((Math.atan2(target.getY() - getY(), target.getX() - getX()) * 180 / Math.PI) + 90);
            lastDirection = getDir();
        } else {
            setDir(lastDirection);
        }

        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
    }

    @Override
    public String getMessage() {
        return "Pew pew!";
    }

}

