package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.Eagle;
import com.codecool.snake.entities.enemies.Crab;
import com.codecool.snake.entities.powerups.SuperPower;
import com.codecool.snake.entities.powerups.Beer;
import com.codecool.snake.entities.powerups.FirstAid;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.powerups.Mouse;

import com.codecool.snake.entities.Heart;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Text;

import static com.codecool.snake.Globals.MAX_LIVES;
import static com.codecool.snake.Globals.heartList;

public class Game extends Pane {
    public static int frame = 0;
    public static int time = 0;
    public static Score textScore;
    public static SnakeHead snake;

    public Game() {
        snake = new SnakeHead(this, Globals.WINDOW_WIDTH/2.0, Globals.WINDOW_HEIGHT/2.0);
        initializeSpawners();
        initializeLives(Globals.lives);
        textScore = new Score(this);
        System.out.println(this.getChildren());
    }

    private void initializeLives(int number) {
        for (int i = 0; i < number; i++) {
            new Heart(this, 920 - (60 * i), 15);
        }
    }

    public static void reSpawnSnake(){
        for (GameEntity gameObject : Globals.gameObjects) {
            if (!(gameObject instanceof Heart)) {
                gameObject.destroy();
            }
        }
        new SnakeHead( Main.game, Globals.SNAKE_SPAWN_X, Globals.SNAKE_SPAWN_X);
    }

    private void initializeSpawners() {
        new Spawner(this, Crab.class, 1, 100);
        new Spawner(this, Mouse.class, 2.0, 10);
        new Spawner(this, Eagle.class, 3.0, 6);
        new Spawner(this, Beer.class, 5.0, 1);
        new Spawner(this, FirstAid.class, 11.0, 1);
        new Spawner(this, SuperPower.class, 13.0, 1);
    }

    void start() {
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
                case SPACE:
                    Globals.SpaceKeyDown = true;
                    break;
                case R:
                    restart();
                    break;
                case P:
                    pause();
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
                case SPACE:
                    Globals.SpaceKeyDown = false;
                    break;
            }
        });

        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    private void setBackground() {
        setBackground(new Background(new BackgroundImage(Globals.background,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    private void restart() {
        if (!Globals.isGamePaused) {
            this.getChildren().clear();
            heartList.clear();
            Globals.snakeLength = 8;
            Globals.gameLoop.stop();
            Globals.gameObjects.clear();
            Globals.enemies.clear();
            Globals.isGamePaused = false;
            Globals.lives = MAX_LIVES;
            new SnakeHead(this, Globals.SNAKE_SPAWN_X, Globals.SNAKE_SPAWN_Y);
            Globals.score = 0;
            initializeLives(Globals.lives);
            textScore = new Score(this);
            start();
        } else {
            pause();
            restart();
        }
    }

    private void pause() {
        if (Globals.isGamePaused) {
            Globals.gameLoop.start();
            Globals.isGamePaused = false;
            Curtain.remove(this);
        } else {
            Globals.gameLoop.stop();
            Globals.isGamePaused = true;
            Curtain.set(this, Globals.pause);
        }
    }
}
