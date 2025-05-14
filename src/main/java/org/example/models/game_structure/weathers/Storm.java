package org.example.models.game_structure.weathers;

import java.util.ArrayList;
import java.util.*;

import org.example.models.App;
import org.example.models.enums.TileType;
import org.example.models.game_structure.Tile;

public class Storm extends Weather {
    double weatherEffectingEnergy ;
    double fishChance ;

    public double getWeatherEffectingEnergy() {
        return weatherEffectingEnergy;
    }
    public double getFishChance() {
        return fishChance;
    }

    public Storm(double weatherEffectingEnergy, double fishChance) {
        this.weatherEffectingEnergy = weatherEffectingEnergy;
        this.fishChance = fishChance;
    }

    public void waterAllTiles() {
        for(int i = 0 ; i < 140 ; i++){
            for(int j = 0 ; j < 160 ; j++){
                Tile tile = App.getCurrentGame().getMap().findTileByXY(i , j);
                if(tile.getTileType().equals(TileType.FARM)){
                    tile.setWatered(true);
                }
            }
        }
    }

    public void randomThunder() {
        int[][] x = new int[4][5];
        int[][] y = new int[4][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == 0) {
                    x[i][j] = (int) (Math.random() * 70);
                    y[i][j] = (int) (Math.random() * 50);
                } else if (i == 1) {
                    x[i][j] = (int) (Math.random() * 70) + 70;
                    y[i][j] = (int) (Math.random() * 50);
                } else if (i == 2) {
                    x[i][j] = (int) (Math.random() * 70);
                    y[i][j] = (int) (Math.random() * 50) + 110;
                } else if (i == 3) {
                    x[i][j] = (int) (Math.random() * 70) + 70;
                    y[i][j] = (int) (Math.random() * 50) + 110;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                App.getCurrentGame().getWeather().thunder(x[i][j] , y[i][j]);
            }
        }
    }

    public String getName() {
        return "Storm";
    }

    public String getCurrentWeather() { return "Storm"; }
}
