package com.StardewValley.models.interactions.game_buildings;

import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Inventory;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.foragings.ForagingMineralType;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.goods.tools.Tool;
import com.StardewValley.models.goods.tools.ToolType;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.server.ClientHandler;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Arrays;

public class Blacksmith extends GameBuilding {
    private final ArrayList<Integer> upgradeToolCost = new ArrayList<>();
    private final ArrayList<Integer> upgradeTrashCanCost = new ArrayList<>();
    private final ArrayList<Pair<ProductType, Integer>> upgradeIngredients = new ArrayList<>();
    private final ArrayList<Integer> dailyToolUpgradeLimit = new ArrayList<>();
    private final ArrayList<Integer> dailyTrashCanUpgradeLimit = new ArrayList<>();
    private final ArrayList<Pair<GoodType, Integer>> stock = new ArrayList<>();
    private final static ArrayList<String> upgradeDescription = new ArrayList<>(
        Arrays.asList(
            "Copper Tool requirements:\nCopper_Bar: 5x",
            "Iron Tool requirements:\nIron_Bar: 5x",
            "Gold Tool requirements:\nGold_Bar: 5x",
            "Iridium Tool requirements:\nIridium_Bar: 5x"
        )
    );

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, new Coordinate(10, 60));
    }

    public Blacksmith(ArrayList<Tile> tiles, ArrayList<Player> players) {
        super(tiles,
                "Blacksmith",
                new NPC(NPCTypes.CLINT, players),
                new Pair<>(9, 16),
                new Coordinate(10, 60),
                new Coordinate(20, 70),
                (TileAssets.BLACKSMITH.getImagePath()));

        upgradeToolCost.addAll(Arrays.asList(2000, 5000, 10000, 25000));
        upgradeTrashCanCost.addAll(Arrays.asList(1000, 2500, 5000, 12500));
        upgradeIngredients.addAll(Arrays.asList(new Pair<>(ProductType.COPPER_BAR, 5),
                new Pair<>(ProductType.IRON_BAR, 5), new Pair<>(ProductType.GOLD_BAR, 5),
                new Pair<>(ProductType.IRIDIUM_BAR, 5)));

        dailyToolUpgradeLimit.addAll(Arrays.asList(1, 1, 1, 1));
        dailyTrashCanUpgradeLimit.addAll(Arrays.asList(1, 1, 1, 1));

        stock.addAll(Arrays.asList(
            new Pair<>(ForagingMineralType.COPPER_ORE, Integer.MAX_VALUE),
            new Pair<>(ForagingMineralType.IRON_ORE, Integer.MAX_VALUE),
                new Pair<>(ProductType.COAL, Integer.MAX_VALUE),
                new Pair<>(ForagingMineralType.GOLD_ORE, Integer.MAX_VALUE)
        ));
    }

    public boolean upgradeTool(Tool tool, ClientHandler clientHandler) {
        Player player = clientHandler.getClientPlayer();
        int nextLevel = ((ToolType) tool.getType()).getLevel().getLevelNumber();

        ArrayList<Good> goods = player.getInventory().isInInventory(upgradeIngredients.get(nextLevel).first());
        if(goods == null || goods.size() < upgradeIngredients.get(nextLevel).second())
            return false;

        if(tool.getType() == ToolType.TRASH_CAN) {
            if(player.getWallet().getBalance() < upgradeTrashCanCost.get(nextLevel) ||
            dailyTrashCanUpgradeLimit.get(nextLevel) == 0)
                return false;
            player.getWallet().decreaseBalance(upgradeTrashCanCost.get(nextLevel));
        }
        else {
            if(player.getWallet().getBalance() < upgradeToolCost.get(nextLevel) ||
            dailyToolUpgradeLimit.get(nextLevel) == 0)
                return false;
            player.getWallet().decreaseBalance(upgradeToolCost.get(nextLevel));
        }

        Inventory.decreaseGoods(goods, upgradeIngredients.get(nextLevel).second());
        ((ToolType) tool.getType()).setLevel(((ToolType) tool.getType()).getLevel().increaseGoodLevel());
        return true;
    }

    @Override
    public ArrayList<GoodType> showAllProducts() {
        return getWholeGoodType(stock);
//        StringBuilder list = new StringBuilder();
//        list.append("Blacksmith All Products:\n");
//        listPartStock(list, stock);
//        list.append("Blacksmith Tools Upgrade :\n");
//        for (int i = 0; i < 4; i++) {
//            list.append("\t>> ").append(ToolLevel.toolLevels.get(i + 1).getName()).append(" Tool, Cost : ").append(upgradeToolCost.get(i))
//                    .append(", Ingredients : ").append(upgradeIngredients.get(i).first().getName()).append(" ")
//                    .append(upgradeIngredients.get(i).second()).append("x ").append(", Daily Limit : ").
//                    append(dailyToolUpgradeLimit.get(i)).append("\n");
//        }
//
//        list.append("\n");
//        list.append("Blacksmith Trash_Can Upgrade :\n");
//        for (int i = 0; i < 4; i++) {
//            list.append("\t>> ").append(ToolLevel.toolLevels.get(i + 1).getName()).append(" Trash_Can , Cost : ").
//                    append(upgradeTrashCanCost.get(i))
//                    .append(", Ingredients : ").append(upgradeIngredients.get(i).first().getName()).append(" ")
//                    .append(upgradeIngredients.get(i).second()).append("x , Daily Limit : ").
//                    append(dailyTrashCanUpgradeLimit.get(i)).append("\n");
//        }
//
//        return new ArrayList<>();
    }

    @Override
    public ArrayList<GoodType> showProducts() {
        return getWholeGoodType(stock);
    }

    @Override
    public Result purchase(String productName, String count, ClientHandler clientHandler) {
        Pair<GoodType, Integer> productPair = null;
        for (Pair<GoodType, Integer> goodTypeIntegerPair : stock) {
            if(goodTypeIntegerPair.first().getName().equals(productName)) {
                productPair = goodTypeIntegerPair;
                break;
            }
        }

        if(productPair == null)
            return new Result(false, "There is no Good of this type in Blacksmith Shop!");
        return purchaseProduct(productName, count, productPair, clientHandler);
    }

    @Override
    public Pair<GoodType, Integer> findProduct(GoodType goodType) {
        for (Pair<GoodType, Integer> goodTypeIntegerPair : stock) {
            if (goodTypeIntegerPair.first().equals(goodType)) {
                return goodTypeIntegerPair;
            }
        }
        return null;
    }

    public static ArrayList<String> getUpgradeDescription() {
        return upgradeDescription;
    }
}
