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


    public void thunder(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        if(App.getCurrentGame().getCurrentPlayer().getCoordinate().equals(coordinate)){
            System.out.println("You've Been Struck by Thunder!");
        }else {
            Tile tile = App.getCurrentGame().getMap().findTile(coordinate);
            for (Good good : tile.getGoods()) {
                if (good instanceof ForagingTree) {
                    tile.removeGoodFromTile(good);
                    tile.addGoodToTile(Good.newGood(ForagingMineralType.COAL));
                } else if (good instanceof FarmingTree) {
                    tile.removeGoodFromTile(good);
                    tile.addGoodToTile(Good.newGood(ForagingMineralType.COAL));
                } else if (good instanceof FarmingTreeSapling) {
                    tile.removeGoodFromTile(good);
                    tile.addGoodToTile(Good.newGood(ForagingMineralType.COAL));
                }
            }
        }
    }
    public abstract double getWeatherEffectingEnergy();
    public abstract double getFishChance();

    public abstract String getName();


}
