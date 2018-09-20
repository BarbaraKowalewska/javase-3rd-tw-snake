package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

// a simple powerup that makes the snake grow TODO make other powerups
public class SuperPower extends GameEntity implements Interactable {

    public SuperPower(Pane pane, Double x, Double y) {
        super(pane);
        setImage(Globals.superMan);
        pane.getChildren().add(this);
        setX(x);
        setY(y);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.superPower(3);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Super power!";
    }
}
