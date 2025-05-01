package org.example.models.game_structure;

import org.example.models.App;

public class Cordinate{
    private int x;
    private int y;

    public Cordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Cordinate getDirection(String direction) {
        int newX = App.getCurrentGame().getCurrentPlayer().getCordinate().getX();
        int newY = App.getCurrentGame().getCurrentPlayer().getCordinate().getY();
        switch (direction) {
            case "up":
                newY += 1;
                break;
            case "down":
                newY -= 1;
                break;
            case "left":
                newX -= 1;
                break;
            case "right":
                newX += 1;
                break;
            case "up-right":
                newX += 1;
                newY += 1;
                break;
            case "up-left":
                newX -= 1;
                newY += 1;
                break;
            case "down-right":
                newX += 1;
                newY -= 1;
                break;
            case "down-left":
                newX -= 1;
                newY -= 1;
                break;
            default:
                return null;
        }
        return new Cordinate(newX, newY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cordinate) {
            Cordinate cordinate = (Cordinate)obj;
            if(cordinate.getX() == x && cordinate.getY() == y)
                return true;
            return false;
        }
        else
            return false;
    }
}
