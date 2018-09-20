package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;


public class Spawner {

    private SnakeHead snakeHead;

    public Spawner(Pane pane, Class<?> entityClass, double time, int max) {
        spawnObject(pane, entityClass, time, max);
    }

    private void spawnObject(Pane pane, Class<?> entityClass, double time, int max) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), ev -> {

            boolean snakeHeadPresent = false;
            int count = 0;

            count = countObjectsOnGameBoard(entityClass, count);
            snakeHeadPresent = isSnakeHeadPresent(snakeHeadPresent);
            findSpawnPoint(pane, entityClass, max, snakeHeadPresent, count);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void findSpawnPoint(Pane pane, Class<?> entityClass, int max, boolean snakeHeadPresent, int count) {
        if (snakeHeadPresent && !Globals.isGamePaused && count < max) {
            Random rnd = new Random();
            double x, y, distance;
            int distanceFromEdge = 100;
            do {
                x = distanceFromEdge + (Globals.WINDOW_WIDTH - (distanceFromEdge*2)) * rnd.nextDouble();
                y = distanceFromEdge + (Globals.WINDOW_HEIGHT - (distanceFromEdge*2)) * rnd.nextDouble();
                distance = (Math.sqrt(Math.pow(snakeHead.getX()-x,2) + Math.pow(snakeHead.getY()-y,2)));
            } while (distance < 200);

            spawnAnObject(pane, entityClass, x, y);
        }
    }

    private void spawnAnObject(Pane pane, Class<?> entityClass, double x, double y) {
        try {
            Class[] arguments = new Class[]{Pane.class, Double.class, Double.class};
            entityClass.getDeclaredConstructor(arguments).newInstance(pane, x, y);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private boolean isSnakeHeadPresent(boolean snakeHeadPresent) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof SnakeHead) {
                snakeHeadPresent = true;
                snakeHead = (SnakeHead) gameObject;
                break;
            }
        }
        return snakeHeadPresent;
    }

    private int countObjectsOnGameBoard(Class<?> entityClass, int count) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject.getClass() == entityClass) {
                count++;
            }
        }
        return count;
    }
}
