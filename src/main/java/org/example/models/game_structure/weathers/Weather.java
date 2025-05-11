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
    double fishChance;

    public static void thunder(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        Tile tile = App.getCurrentGame().getMap().findTile(coordinate);
        // TODO: method to turn Tree into Coal
        for (Good good : tile.getGoods()) {
            if(good instanceof ForagingTree){
                tile.removeGoodFromTile(good);
                tile.addGoodToTile(Good.newGood(ForagingMineralType.COAL));
            } else if(good instanceof FarmingTree){
                tile.removeGoodFromTile(good);
                tile.addGoodToTile(Good.newGood(ForagingMineralType.COAL));
            } else if(good instanceof FarmingTreeSapling){
                tile.removeGoodFromTile(good);
                tile.addGoodToTile(Good.newGood(ForagingMineralType.COAL));
            }
        }
    }
    public double getWeatherEffectingEnergy() {
        return weatherEffectingEnergy;
    }
    public double getFishChance() {
        return fishChance;
    }
    public abstract String getName();


}
