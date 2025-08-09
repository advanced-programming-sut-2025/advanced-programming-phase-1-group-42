package com.StardewValley.models.game_structure.weathers;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.server.ClientHandler;

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
                Tile tile = AppClient.getCurrentGame().getMap().findTileByXY(i , j);
                if(tile.getTileType().equals(TileType.FARM)){
                    tile.setWatered(true);
                }
            }
        }
    }

    public void randomThunder(ClientHandler handler) {
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
                AppClient.getCurrentGame().getWeather().thunder(x[i][j] , y[i][j], handler);
            }
        }
    }

    public String getName() {
        return "Storm";
    }

    public String getCurrentWeather() { return "Storm"; }
}
