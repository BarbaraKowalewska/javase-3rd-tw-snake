package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

import com.codecool.snake.entities.Heart;

import javafx.scene.image.Image;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;
    public static final double SNAKE_SPAWN_X = Globals.WINDOW_WIDTH/2.0;
    public static final double SNAKE_SPAWN_Y = Globals.WINDOW_HEIGHT/2.0;
    public static final int MAX_LIVES = 3;
    public static final int SCORE_TEXT_X = 20;
    public static final int SCORE_TEXT_Y = 50;

    public static int lives = 3;
    public static int snakeLength = 8;
    public static int score = 0;

    public static Image snakeHead = new Image("snake_head1.png");
    public static Image drunkSnakeHead = new Image("snake_head2.png");
    public static Image laserSnakeHead = new Image("snake_head3.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image laser = new Image("laser.png");
    public static Image crab = new Image("crab.png");
    public static Image firstAid = new Image("first_aid.png");
    public static Image beer = new Image("beer.png");
    public static Image superMan = new Image("super_man.png");
    public static Image mouse = new Image("mouse.png");
    public static Image eagle = new Image("eagle.png");
    static Image background = new Image("sand.jpg");
    public static Image heart = new Image("heart.png");
    public static Image blackHeart = new Image("blackHeart.png");
    public static Image gameOver = new Image("gameover.png");
    public static Image pause = new Image("pause.png");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean SpaceKeyDown;
    public static boolean isGamePaused = false;
    public static List<GameEntity> gameObjects;
    static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.

    public static List<GameEntity> enemies;
    static List<GameEntity> newEnemies; // Holds enemies crated in this frame.
    static List<GameEntity> oldEnemies; // Holds enemies that will be destroyed this frame.

    public static GameLoop gameLoop;
    public static List<Heart> heartList;



    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
        enemies = new LinkedList<>();
        newEnemies = new LinkedList<>();
        oldEnemies = new LinkedList<>();
        heartList = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }

    public static void addEnemy(GameEntity toAdd) {
        newEnemies.add(toAdd);
    }

    public static void removeEnemy(GameEntity toRemove) {
        oldEnemies.add(toRemove);
    }

    public static List<GameEntity> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }
}
