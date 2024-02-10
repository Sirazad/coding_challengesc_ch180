package com.sirazad.codingchallenge;

import javafx.event.EventType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.*;

import static com.sirazad.codingchallenge.FallingSand.BASE_COLOR;

@Getter
@Setter
public class Cell extends Rectangle {
    public boolean exists;

    public Cell(int x, int y, int cellsize, boolean exists) {
        super(x, y, cellsize, cellsize);
        this.exists = exists;
        setStroke(Color.WHITESMOKE);
        setFill(BASE_COLOR);
    }



}
