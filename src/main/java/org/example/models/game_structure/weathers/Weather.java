package org.example.models.game_structure.weathers;

import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;

public abstract class Weather {
    double weatherEffectingEnergy;


    public void Thunder(int x , int y, Tile tile) {
        // TODO: method to turn Tree into Coal
        for (Good good : tile.getGoods()) {
//            if()
        }

    }



    public abstract String getName();
}
