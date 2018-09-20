package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.scene.layout.Pane;
import static com.codecool.snake.Globals.heartList;

public class Heart extends GameEntity {

    private boolean alive;

    public Heart(Pane pane, int x, int y) {
        super(pane);
        this.alive = true;
        setImage(Globals.heart);
        pane.getChildren().add(this);
        heartList.add(this);
        setX(x);
        setY(y);
    }

    private void switchHeartColor() {
        setImage(this.alive ? Globals.heart : Globals.blackHeart);
    }

    public static void switchOffRedHeart() {
        for (Heart heart : heartList) {
            if (heart.alive) {
                heart.alive = false;
                heart.switchHeartColor();
                break;
            }
        }
    }

    public static void switchOnBlackHeart() {
        for (int i = heartList.size() - 1; i >= 0; i--) {
            if (!heartList.get(i).alive) {
                heartList.get(i).alive = true;
                heartList.get(i).switchHeartColor();
                break;
            }
        }
    }
}






