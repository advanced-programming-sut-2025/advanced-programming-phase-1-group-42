package org.example.models.game_structure;

import org.example.models.App;

import java.util.ArrayList;
import java.util.Arrays;

public class Coordinate {
    private int x;
    private int y;
    public final static ArrayList<Coordinate> coordinates = new ArrayList<>(Arrays.asList(
            new Coordinate(0, -1),   // Up
            new Coordinate(1, -1),   // Up-Right
            new Coordinate(1, 0),    // Right
            new Coordinate(1, 1),    // Down-Right
            new Coordinate(0, 1),    // Down
            new Coordinate(-1, 1),   // Down-Left
            new Coordinate(-1, 0),   // Left
            new Coordinate(-1, -1)   // Up-Left
    ));

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate getDirection(String direction) {
        int newX = App.getCurrentGame().getCurrentPlayer().getCoordinate().getX();
        int newY = App.getCurrentGame().getCurrentPlayer().getCoordinate().getY();
        switch (direction) {
            case "up":
                newY -= 1;
                break;
            case "down":
                newY += 1;
                break;
            case "left":
                newX -= 1;
                break;
            case "right":
                newX += 1;
                break;
            case "up-right":
                newX += 1;
                newY -= 1;
                break;
            case "up-left":
                newX -= 1;
                newY -= 1;
                break;
            case "down-right":
                newX += 1;
                newY += 1;
                break;
            case "down-left":
                newX -= 1;
                newY += 1;
                break;
            default:
                return null;
        }
        return new Coordinate(newX, newY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate)obj;
            if(coordinate.getX() == x && coordinate.getY() == y)
                return true;
            return false;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "{x:" + x + " y:" + y + "}";
    }

    public int distance(Coordinate coordinate) {
        return Math.abs(x - coordinate.getX()) + Math.abs(y - coordinate.getY());

}
