package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

public class Score extends GameEntity {

    private Text scoreText = new Text();

    public Score(Pane pane) {
        super(pane);
        scoreText.setX(Globals.SCORE_TEXT_X);
        scoreText.setY(Globals.SCORE_TEXT_Y);
        scoreText.setFont(Font.font("courier", FontWeight.BOLD, FontPosture.REGULAR, 40));
        scoreText.setFill(Color.BLACK);
//        scoreText.setStrokeWidth(1);
//        scoreText.setStroke(Color.BLACK);
        upgradeText();
        pane.getChildren().add(scoreText);
    }

    public void upgradeText(){
        scoreText.setText("SCORE: " + Globals.score);
    }
}
