package com.metanit;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends Application{
    public static Pane appRoot = new Pane(); //панель самого приложения
    public static Pane gameRoot = new Pane(); //панель игры, стенок
    public static int c=0;

    public static ArrayList<Wall> walls = new ArrayList<>(); //хранятся стены для проверки столкновений
    Bird bird = new Bird(); // сама птичка
    public static int score=0; // переменная, отвечающая за счёт
    public Label scoreLabel = new Label("Score: "+score);

    public Parent createContent()
    {
        gameRoot.setPrefSize(600,600);// ниже: создание столбцов

        for (int i=0; i< 100; i++) //создание стенки каждой итерации
        {
            int enter = (int)(Math.random()*100+80);//число в диапазоне от 50 до 150, ширина проема для птицы
            int height = new Random().nextInt(600-enter); //высота стенки
            Wall wall = new Wall(height);
            wall.setTranslateX(i*350+600);//через каждые 350пикс будет стена, отодвигаем на 600
            wall.setTranslateY(0);
            walls.add(wall); //добавляем стену в список стен

            Wall wall2 = new Wall(600-enter-height);
            wall2.setTranslateX(i*350+600);
            wall2.setTranslateY(height+enter);
            walls.add(wall2);

            gameRoot.getChildren().addAll(wall,wall2);

        }
        BackgroundImage myBI= new BackgroundImage(new Image("https://funart.pro/uploads/posts/2021-04/1618606994_13-funart_pro-p-oboi-fon-pikselnoe-nebo-13.png",500,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        scoreLabel.setFont(new Font("Broadway", 30));
        scoreLabel.setTextFill(Color.web("RED"));
        scoreLabel.setLayoutX(250);
        gameRoot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameRoot);
        appRoot.getChildren().addAll(scoreLabel);
        appRoot.setBackground(new Background(myBI));
        return appRoot; //т.к. это корневой узел, возвращаем его

    }

    public void update ()
    {
        if (bird.velocity.getY()<5) //гравитация
        {
            bird.velocity = bird.velocity.add(0,1);
        }
        if (bird.MoveY((int)bird.velocity.getY())) scoreLabel.setText("Score: "+ score/2);
                if (bird.MoveX((int) bird.velocity.getX()) == false || bird.MoveY((int) bird.velocity.getY()) == false) {
                    scoreLabel.setText("Game Over!");
                    c++;
                }

        bird.translateXProperty().addListener((obs,old,newValue)->{
            int offset = newValue.intValue();
            if (offset>200) gameRoot.setLayoutX(-(offset));
        });
    }



    @Override
    public void start(Stage stage) throws MouseException {

        Scene scene = new Scene(createContent());

            scene.setOnMouseClicked(event -> {
                try {
                    bird.jump();
                } catch (MouseException e) {
                    e.printStackTrace();
                }
            });


        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        timer.start();


    }
    public static void main(String[] args) {

        launch(args);
    }
}