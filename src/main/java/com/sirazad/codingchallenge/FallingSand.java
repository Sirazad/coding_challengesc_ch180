package com.sirazad.codingchallenge;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FallingSand extends Application {
    public static final int CANVAS_SIZE = 600;
    public static final int CELL_SIZE = 10;
    public static final Color BASE_COLOR = Color.WHITE;
    private final Pane root = new Pane();
    private Cell[][] cells;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        scene.setOnMouseDragged(event -> {
            int i = (int) Math.floor(event.getX()/CELL_SIZE);
            int j = (int) Math.floor(event.getY()/CELL_SIZE);
            Cell cell = cells[i][j];
            if (!cell.exists) {
                cell.setFill(randomColor());
                cell.setExists(true);
            }
        });
        stage.show();
    }

    private Parent createContent() {
        root.setPrefSize(CANVAS_SIZE, CANVAS_SIZE);
        cells = initCells(cells);
        cells[1][1].setFill(randomColor());
        cells[1][1].setExists(true);
        cells[3][2].setFill(randomColor());
        cells[3][2].setExists(true);

        AnimationTimer timer = new AnimationTimer() {
            private static final double FRAME_RATE = 4;
            private static final double FRAME_LENGTH = 1_000_000_000 / FRAME_RATE;
            private long lastUpdate = 0;

            @SneakyThrows
            @Override
            public void handle(long now) {
                if (now - lastUpdate > FRAME_LENGTH) {
                    update();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
        return root;
    }

    private Cell[][] initCells(Cell[][] grid) {
        grid = new Cell[CANVAS_SIZE/CELL_SIZE][CANVAS_SIZE/CELL_SIZE];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Cell singleCell = new Cell(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, false);
                grid[i][j] = singleCell;
                root.getChildren().add(singleCell);
            }
        }
        return grid;
    }

    private void update() {
        List<Pair<Integer, Integer>> toBeChanged = new ArrayList<>();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length-1; j++) {
                if(cells[i][j].exists) {
                    if(cells[i][j+1].exists) continue;
                    toBeChanged.add(new Pair<>(i, j));
                }
            }
        }

        for (Pair<Integer, Integer> element : toBeChanged) {
            moveCell(element.getKey(), element.getValue());
        }
    }

    private void moveCell(int i, int j) {
        Paint oldColor = cells[i][j].getFill();
        cells[i][j].exists = false;
        cells[i][j].setFill(BASE_COLOR);
        cells[i][j+1].setFill(oldColor);
        cells[i][j+1].setExists(true);
    }

    private Color randomColor() {
        List<Color> colors = List.of(Color.BLUE, Color.CORNFLOWERBLUE, Color.DARKSLATEBLUE, Color.DARKTURQUOISE);
        return colors.get(new Random().nextInt(colors.size()));
    }
}
