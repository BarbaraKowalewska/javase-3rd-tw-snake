package com.codecool.snake.entities.snakes;

import com.codecool.snake.Curtain;
import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Heart;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static float speed = 2;
    private static float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int drunkTimeEnd = -1;
    private int superPowerEnd = -1;
    private double shootFrameEnd = -1;
    private double dir;

    public SnakeHead(Pane pane, double xc, double yc) {
        super(pane);
        setX(xc);
        setY(yc);
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        addPart(Globals.snakeLength);
    }

    public void step() {
        dir = getRotate();

        // make snake squint when drunk
        setImage(Game.time < drunkTimeEnd ? Globals.drunkSnakeHead : Globals.snakeHead);

        // set turnRate with a correction on intoxication
        float actualTurnRate = Game.time < drunkTimeEnd ? -turnRate : turnRate;

        if (Globals.leftKeyDown) {
            dir = dir - actualTurnRate;
        }

        if (Globals.rightKeyDown) {
            dir = dir + actualTurnRate;
        }

        setRotationAndPosition();

        shooting();
        checkCollision();
        checkIfGameOver();
        checkIfSnakeIsOutOfBounds();
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            tail = new SnakeBody(pane, tail);
        }
    }

    public void changeLives(int diff) {
        if (Globals.lives  < Globals.MAX_LIVES && diff>0 || diff<0) {
            Globals.lives += diff;
        }
        System.out.println("LIVES " + Globals.lives);

        if (diff>0){
            Heart.switchOnBlackHeart();
        } else {
            Heart.switchOffRedHeart();
        }
    }

    public void changeScore(int diff) {
        Globals.score = Globals.score + diff;
        System.out.println("SCORE  " + Globals.score);
    }

    public void intoxicateSnake(int duration) {
        drunkTimeEnd = Game.time + duration;
    }

    public void superPower(int duration){
        drunkTimeEnd =- 5;
        superPowerEnd = Game.time + duration;
    }

    private void setRotationAndPosition() {
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    private void checkIfSnakeIsOutOfBounds() {
        if (isOutOfBounds()){
            changeLives(-1);
            Game.respawnSnake();}
    }

    private void checkIfGameOver() {
        if (Globals.lives <= 0) {
            System.out.println("Game Over");
            Globals.isGamePaused = true;
            Globals.gameLoop.stop();
            Curtain.set(pane, Globals.gameOver);
        }
    }

    private void checkCollision() {
        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }
    }

    private void shooting() {

        double shootFrameDelay = 15;
        if (Game.time < superPowerEnd && Game.time >= drunkTimeEnd) {
            setImage(Globals.laserSnakeHead);
            speed = 4;
            turnRate = 4;
            if (Globals.enemies.size() > 0) {
                if (Game.frame >= shootFrameEnd) {
                    shootFrameEnd = Game.frame + shootFrameDelay;
                    shoot(true, false);
                    shoot(true, true);
                }
            }
        } else {
            speed = 2;
            turnRate = 2;
            if (Globals.SpaceKeyDown && Game.time >= drunkTimeEnd) {
                setImage(Globals.laserSnakeHead);
                if (Game.frame >= shootFrameEnd) {
                    shootFrameEnd = Game.frame + shootFrameDelay;
                    shoot(false, false);
                }
            }
        }
    }

    private void shoot(boolean superPower, boolean secondLaser) {
        new Laser(pane, superPower, secondLaser);
    }
}
