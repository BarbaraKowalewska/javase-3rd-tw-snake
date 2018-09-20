package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class Beer extends GameEntity implements Interactable {

    public Beer(Pane pane, Double x, Double y) {
        super(pane);
        setImage(Globals.beer);
        pane.getChildren().add(this);
        setX(x);
        setY(y);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.intoxicateSnake(5);
        snakeHead.changeScore(20);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Don't drink and drive!";
    }
}
