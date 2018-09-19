package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Eagle;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.Beer;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.powerups.Mouse;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Game extends Pane {
    public static int frame = 0;
    public static int time = 0;


    public Game() {
        new Controls(this, 10, 10);
        SnakeHead head = new SnakeHead(this, 500, 500);
        initializeSpawners();
    }

    private void initializeSpawners() {

        new Spawner(this, SimpleEnemy.class, 2.0);
        new Spawner(this, Mouse.class, 2.5);
        new Spawner(this, Eagle.class, 4);
        new Beer(this);
        new SimplePowerup(this);
    }

    public void start() {
        Scene scene = getScene();
        setBackground();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    Globals.leftKeyDown = true;
                    break;
                case RIGHT:
                    Globals.rightKeyDown = true;
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:
                    Globals.leftKeyDown = false;
                    break;
                case RIGHT:
                    Globals.rightKeyDown = false;
                    break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();

    }

    public void setBackground() {
        setBackground(new Background(new BackgroundImage(Globals.grass,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public void restart() {
        this.getChildren().clear();
        new SnakeHead(this, 500, 500);
        new Controls(this, 10, 10);

        Globals.gameLoop.stop();
        Globals.gameObjects.clear();

        start();
    }

    public void pause() {
        if (Globals.isGamePaused) {
            Globals.gameLoop.start();
            Globals.isGamePaused = false;
        } else {
            Globals.gameLoop.stop();
            Globals.isGamePaused = true;
        }
    }
}
