package org.example.models.interactions;

import org.example.models.game_structure.Cordinate;

public abstract class Building {
    protected Cordinate startCordinate;
    protected Cordinate endCordinate;
    protected Cordinate size;

    public Cordinate getStartCordinate() {
        return startCordinate;
    }

    public void setStartCordinate(Cordinate startCordinate) {
        this.startCordinate = startCordinate;
    }

    public Cordinate getEndCordinate() {
        return endCordinate;
    }

    public void setEndCordinate(Cordinate endCordinate) {
        this.endCordinate = endCordinate;
    }

    public Cordinate getSize() {
        return size;
    }

    public void setSize(Cordinate size) {
        this.size = size;
    }

    public boolean isInsideBuilding(Cordinate cordinate) {
        if(cordinate.getX() >= this.startCordinate.getX() && cordinate.getY() >= this.startCordinate.getY() &&
        cordinate.getX() <= this.endCordinate.getX() && cordinate.getY() <= this.endCordinate.getY())
            return true;
        return false;
    }
}
