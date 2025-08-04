package com.StardewValley.models.game_structure.weathers;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.Tile;

public class Rain extends Weather {
    double weatherEffectingEnergy;
    double fishChance ;

    public Rain(double effectingEnergy, double fishChance) {
        this.weatherEffectingEnergy = effectingEnergy;
        this.fishChance = fishChance;
    }
    public double getWeatherEffectingEnergy() {
        return weatherEffectingEnergy;
    }
    public double getFishChance() {
        return fishChance;
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

    public String getName(){
        return "Rain";
    }
    public String getCurrentWeather() { return "Rain"; }
}
