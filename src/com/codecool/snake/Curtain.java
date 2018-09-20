package com.codecool.snake;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Curtain {

    private static ImageView curtain = new ImageView();

    public static void set(Pane game, Image image) {
        curtain.setImage(image);
        curtain.setFitWidth(Globals.WINDOW_WIDTH);
        curtain.setFitHeight(Globals.WINDOW_HEIGHT);
        game.getChildren().add(curtain);
    }

    static void remove(Game game) {
        //remove last element
        game.getChildren().remove(game.getChildren().size()-1);
    }
}
