package com.sirazad.codingchallenge;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FallingSand extends Application {
    private final Pane root = new Pane();
    private Rectangle rect;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.show();
    }

    private Parent createContent() {
        root.setPrefSize(600, 600);
        rect = new Rectangle(40, 40);
        root.getChildren().add(rect);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
        return root;
    }

    private void update() {
    }


}
