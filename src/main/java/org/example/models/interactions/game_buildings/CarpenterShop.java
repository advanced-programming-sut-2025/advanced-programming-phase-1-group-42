package org.example.models.interactions.game_buildings;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Game;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.products.Product;
import org.example.models.goods.products.ProductType;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.NPCs.NPCTypes;
import org.example.models.interactions.PlayerBuildings.FarmBuilding;
import org.example.models.interactions.PlayerBuildings.FarmBuildingTypes;

import java.util.ArrayList;
import java.util.List;

public class CarpenterShop extends GameBuilding {

    private ArrayList<FarmBuildingTypes> products = new ArrayList<>(
            List.of(FarmBuildingTypes.BARN, FarmBuildingTypes.BIG_BARN, FarmBuildingTypes.DELUXE_BARN,
                    FarmBuildingTypes.COOP, FarmBuildingTypes.BIG_COOP, FarmBuildingTypes.DELUXE_COOP)
    );

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, 5, 85);
    }

    public CarpenterShop(ArrayList<Tile> tiles) {
        super(tiles,
                "CarpenterShop",
                new NPC(NPCTypes.ROBIN),
                new Pair<>(9, 20),
                new Coordinate(5, 85),
                new Coordinate(25, 105));
    }

    @Override
    public String showAllProducts() {
        for (FarmBuildingTypes farmBuildingType : products) {
            System.out.println(farmBuildingType.getName() + "  " + farmBuildingType.getCost() + "G");
        }
        return null;
    }

    @Override
    public String showProducts() {
        return "";
    }

    @Override
    public Result purchase(String productName, String count) {
        int countInt = Integer.parseInt(count);
        ProductType type = null;
        if (productName.equals("Wood")) {
            type = ProductType.WOOD;
            if (!(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() > countInt * type.getSellPrice())) {
                return new Result(false, "You don't have enough money");
            } else {
                if (!App.getCurrentGame().getCurrentPlayer().getInventory().addGood((Good) new Product(ProductType.WOOD), countInt)) {
                    return new Result(false, "Your inventory is full");
                } else {
                    App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(countInt * type.getSellPrice());
                }
                return new Result(true, "You bought " + countInt + " Wood");
            }
        } else if (productName.equals("Stone")) {
            type = ProductType.WOOD;
            if (!(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() > countInt * type.getSellPrice())) {
                return new Result(false, "You don't have enough money");
            } else {
                if (!App.getCurrentGame().getCurrentPlayer().getInventory().addGood((Good) new Product(ProductType.WOOD), countInt)) {
                    return new Result(false, "Your inventory is full");
                } else {
                    App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(countInt * type.getSellPrice());
                }
                return new Result(true, "You bought " + countInt + " Stone");
            }
        } else {
            return new Result(false, "This item is not available in this shop");
        }
    }

    public ArrayList<FarmBuildingTypes> getProducts() {
        return products;
    }

    public FarmBuilding buildingFarmBuilding(FarmBuildingTypes farmBuildingType, Coordinate startCoordinate) {
        return new FarmBuilding(farmBuildingType, startCoordinate);
    }
}
