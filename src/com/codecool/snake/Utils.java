package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.Crab;
import com.codecool.snake.entities.enemies.Eagle;
import com.codecool.snake.entities.snakes.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    public static void getShootByLaser(GameEntity objectBeingShoot){
        for (GameEntity entity : Globals.getGameObjects()) {
            if (objectBeingShoot.intersects(entity.getBoundsInParent())) {
                if (entity instanceof Laser) {
                    entity.destroy();
                    objectBeingShoot.destroy();
                    if (objectBeingShoot instanceof Eagle) {
                        Globals.score += Eagle.BONUS;
                    } else if (objectBeingShoot instanceof Crab){
                        Globals.score += Crab.BONUS;
                    }
                }
            }
        }
    }

    public static SnakeHead findSnakeHead() {
        SnakeHead snakeHead = null;
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof SnakeHead) {
                snakeHead = (SnakeHead) gameObject;
            }
        }
        return snakeHead;
    }
}
