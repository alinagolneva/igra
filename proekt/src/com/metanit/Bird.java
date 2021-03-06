package com.metanit;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.metanit.FlappyBird.c;
import static com.metanit.FlappyBird.score;

public class Bird extends Pane {
    public Point2D velocity;
    Rectangle rect;
    public Label Label = new Label("");
    public Bird() {
        rect = new Rectangle(20,20, Color.RED);
        velocity = new Point2D(0,0);
        setTranslateY(300);
        setTranslateX(100);
        getChildren().addAll(rect);
        getChildren().addAll(Label);

    }

    public boolean MoveY(int value)  {
        boolean moveDown = value > 0 ? true : false;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Wall w : FlappyBird.walls) {
                if (this.getBoundsInParent().intersects(w.getBoundsInParent())) {
                    return false;

                }

            }
            if (getTranslateY() < 0) {
                setTranslateY(0);
            }

            if (getTranslateY() > 580) {
                setTranslateY(580);
            }
            setTranslateY(getTranslateY() + (moveDown ? 1 : -1)); //к текущей коорд прибавить или отнять единицу
        }
        return true;
    }

    public boolean MoveX(int value)
    {
        for (int i=0; i<value; i++)
        {
            for (Wall w: FlappyBird.walls) {
                if (this.getBoundsInParent().intersects(w.getBoundsInParent()))
                {
                    if (getTranslateX()+20 == w.getTranslateX()) {
                        setTranslateX(getTranslateX() - 1);
                        score--;
                        return false;
                    }
                }
                if (getTranslateX()+20 == w.getTranslateX())
                {
                    score++;
                }
            }
            setTranslateX(getTranslateX()+1);

        }
        return true;
    }

    public void jump() throws MouseException {
        velocity = new Point2D(3, -10);
        if (c>0)
            throw new MouseException();
    }
}
