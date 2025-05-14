package org.example.models.game_structure.weathers;

import org.example.models.App;
import org.example.models.enums.TileType;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.farmings.FarmingCrop;
import org.example.models.goods.farmings.FarmingTree;
import org.example.models.goods.farmings.FarmingTreeSapling;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foragings.ForagingMineralType;
import org.example.models.goods.foragings.ForagingTree;

import java.util.Iterator;

public abstract class Weather {
    double weatherEffectingEnergy;
    double fishChance;


    public void thunder(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
//        if(App.getCurrentGame().getCurrentPlayer().getCoordinate().equals(coordinate)){
//            System.out.println("You've Been Struck by Thunder!");
//        }
//        else
        {
            Tile tile = App.getCurrentGame().getMap().findTile(coordinate);
            System.out.println("Thunder has been struck");
            if(!(tile.getTileType().equals(TileType.GREEN_HOUSE))) {
                Iterator<Good> iterator = tile.getGoods().iterator();
                while (iterator.hasNext()) {
                    Good good = iterator.next();
                    if (good instanceof ForagingTree) {
                        iterator.remove();
                        tile.addGoodToTile(Good.newGood(ForagingMineralType.COAL));
                        System.out.println("A Tree has been Fallen By Thor");
                    } else if (good instanceof FarmingTree) {
                        iterator.remove();
                        tile.addGoodToTile(Good.newGood(ForagingMineralType.COAL));
                        System.out.println("A Tree has been Fallen By Thor");
                    } else if (good instanceof FarmingTreeSapling) {
                        iterator.remove();
                        tile.addGoodToTile(Good.newGood(ForagingMineralType.COAL));
                        System.out.println("A Tree has been Fallen By Thor");
                    }
                }
            }
        }
    }
    public abstract double getWeatherEffectingEnergy();
    public abstract double getFishChance();

    public abstract String getName();


}
