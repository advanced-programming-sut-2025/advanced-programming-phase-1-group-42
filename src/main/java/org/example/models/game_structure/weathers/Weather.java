package org.example.models.game_structure.weathers;

import org.example.models.App;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.farmings.FarmingTree;
import org.example.models.goods.farmings.FarmingTreeSapling;
import org.example.models.goods.foragings.ForagingMineralType;
import org.example.models.goods.foragings.ForagingTree;

public abstract class Weather {
    double weatherEffectingEnergy;


    public void Thunder(int x , int y) {
        Coordinate c = new Coordinate(x , y);
        Tile tile = App.getCurrentGame().getMap().findTile(c);
        for (Good good : tile.getGoods()) {
            if(good instanceof ForagingTree){
                tile.deleteGood(good);
                tile.addGood(Good.newGood(ForagingMineralType.COAL));
            } else if(good instanceof FarmingTree){
                tile.deleteGood(good);
                tile.addGood(Good.newGood(ForagingMineralType.COAL));
            } else if(good instanceof FarmingTreeSapling){
                tile.deleteGood(good);
                tile.addGood(Good.newGood(ForagingMineralType.COAL));
            }
        }

    }



    public abstract String getName();
}
