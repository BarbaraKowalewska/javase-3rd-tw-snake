package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        Game.frame++;
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable) gameObject;
                animObject.step();
            }
        }

        if (Game.frame % 60 == 0) {
            Game.time++;
        }

        updateGameState();
    }

    private void updateGameState() {
        Globals.enemies.addAll(Globals.newEnemies);
        Globals.newEnemies.clear();

        Globals.enemies.removeAll(Globals.oldEnemies);
        Globals.oldEnemies.clear();

        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();

        Game.textScore.upgradeText();
    }
}
