package com.metanit;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class Wall extends Pane {
    Rectangle rect;
    public int height;
    public Wall(int height)
    {
        this.height = height;
        rect = new Rectangle (30, height);

        getChildren().add(rect);
    }

}
