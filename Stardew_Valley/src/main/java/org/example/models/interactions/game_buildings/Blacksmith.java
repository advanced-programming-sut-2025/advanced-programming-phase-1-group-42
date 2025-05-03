package org.example.models.interactions.game_buildings;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.game_structure.Inventory;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.foragings.ForagingMineral;
import org.example.models.goods.foragings.ForagingMineralType;
import org.example.models.goods.products.ProductType;
import org.example.models.goods.tools.Tool;
import org.example.models.goods.tools.ToolLevel;
import org.example.models.goods.tools.ToolType;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.NPCs.NPCTypes;
import org.example.models.interactions.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Blacksmith extends GameBuilding {
    private final ArrayList<Integer> upgradeToolCost = new ArrayList<>();
    private final ArrayList<Integer> upgradeTrashCanCost = new ArrayList<>();
    private final ArrayList<Pair<ForagingMineralType, Integer>> upgradeIngredients = new ArrayList<>();
    private final ArrayList<Integer> dailyToolUpgradeLimit = new ArrayList<>();
    private final ArrayList<Integer> dailyTrashCanUpgradeLimit = new ArrayList<>();
    private final ArrayList<Pair<GoodType, Integer>> stock = new ArrayList<>();

    public Blacksmith() {
        super(null,
                "Blacksmith",
                new NPC(NPCTypes.CLINT));

        upgradeToolCost.addAll(Arrays.asList(2000, 5000, 10000, 25000));
        upgradeTrashCanCost.addAll(Arrays.asList(1000, 2500, 5000, 12500));
        upgradeIngredients.addAll(Arrays.asList(new Pair<>(ForagingMineralType.COPPER, 5),
                new Pair<>(ForagingMineralType.IRON, 5), new Pair<>(ForagingMineralType.GOLD, 5),
                new Pair<>(ForagingMineralType.IRIDIUM, 5)));

        dailyToolUpgradeLimit.addAll(Arrays.asList(1, 1, 1, 1));
        dailyTrashCanUpgradeLimit.addAll(Arrays.asList(1, 1, 1, 1));

        stock.addAll(Arrays.asList(
            new Pair<>(ProductType.COPPER_ORE, Integer.MAX_VALUE),
            new Pair<>(ProductType.IRON_ORE, Integer.MAX_VALUE),
                new Pair<>(ProductType.COAL, Integer.MAX_VALUE),
                new Pair<>(ProductType.GOLD_ORE, Integer.MAX_VALUE)
        ));
    }

    public boolean upgradeTool(Tool tool) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        int nextLevel = ((ToolType) tool.getType()).getLevel().getLevelNumber();

        ArrayList<Good> goods = player.getInventory().isInInventory(upgradeIngredients.get(nextLevel).first());
        if(goods == null || goods.size() < (Integer) upgradeIngredients.get(nextLevel).second())
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

        Inventory.decreaseGoods(goods, (Integer) upgradeIngredients.get(nextLevel).second());
        ((ToolType) tool.getType()).setLevel(((ToolType) tool.getType()).getLevel().increaseGoodLevel());
        return true;
    }

    @Override
    public String showAllProducts() {
        StringBuilder list = new StringBuilder();
        list.append("Blacksmith All Products:\n");
        for (Pair<GoodType, Integer> goodTypeIntegerPair : stock) {
            list.append("\t>> Name : ").append(goodTypeIntegerPair.first().getName()).append(", Stock: ");
            if(goodTypeIntegerPair.second() == Integer.MAX_VALUE)
                list.append("Unlimited\n");
            else
                list.append(goodTypeIntegerPair.second()).append("\n");
        }

        list.append("\n");
        list.append("Blacksmith Tools Upgrade :\n");
        for (int i = 0; i < 4; i++) {
            list.append("\t>> ").append(ToolLevel.toolLevels.get(i + 1).getName()).append(" Tool, Cost : ").append(upgradeToolCost.get(i))
                    .append(", Ingredients : ").append(upgradeIngredients.get(i).first().getName()).append(" ")
                    .append(upgradeIngredients.get(i).second()).append("x ").append(", Daily Limit : ").
                    append(dailyToolUpgradeLimit.get(i)).append("\n");
        }

        list.append("\n");
        list.append("Blacksmith Trash_Can Upgrade :\n");
        for (int i = 0; i < 4; i++) {
            list.append("\t>> ").append(ToolLevel.toolLevels.get(i + 1).getName()).append(" Trash_Can , Cost : ").
                    append(upgradeTrashCanCost.get(i))
                    .append(", Ingredients : ").append(upgradeIngredients.get(i).first().getName()).append(" ")
                    .append(upgradeIngredients.get(i).second()).append("x , Daily Limit : ").
                    append(dailyTrashCanUpgradeLimit.get(i)).append("\n");
        }

        return list.toString();
    }

    @Override
    public String showProducts() {
        StringBuilder list = new StringBuilder();
        list.append("Blacksmith Available Products:\n");
        for (Pair<GoodType, Integer> goodTypeIntegerPair : stock) {
            list.append("\t>> Name : ").append(goodTypeIntegerPair.first().getName()).append(", Stock: ");
            if(goodTypeIntegerPair.second() == Integer.MAX_VALUE)
                list.append("Unlimited\n");
            else if(goodTypeIntegerPair.second() > 0)
                list.append(goodTypeIntegerPair.second()).append("\n");
        }
        return list.toString();
    }

    @Override
    public Result purchase(String productName, String count) {
        Pair<GoodType, Integer> productPair = null;
        for (Pair<GoodType, Integer> goodTypeIntegerPair : stock) {
            if(goodTypeIntegerPair.first().getName().equals(productName)) {
                productPair = goodTypeIntegerPair;
                break;
            }
        }

        if(productPair == null)
            return new Result(false, "There is no Good of this type in Blacksmith Shop!");
        if(!count.matches("-?\\d+") && !count.isEmpty())
            return new Result(false, "Invalid Quantity format!");

        int quantity = (count.isEmpty()) ? 1 : Integer.parseInt(count);
        if(productPair.second() < quantity)
            return new Result(false, productName + "'s stock is less than the quantity you want!");

        if(quantity * productPair.first().getSellPrice() > App.getCurrentGame().getCurrentPlayer().getWallet().getBalance()) {
            return new Result(false, "You have enough money in your wallet to purchase this product(s)!");
        }
        if(App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(productName) == null &&
        App.getCurrentGame().getCurrentPlayer().getInventory().isFull())
            return new Result(false, "Your inventory is full to purchase this product(s)!");

        int totalPrice = quantity * productPair.first().getSellPrice();
        App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(totalPrice);
        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(Good.newGoods(productPair.first(), quantity));

        productPair.setSecond(productPair.second() - quantity);
        return new Result(true, productName + " " + quantity + "x stock purchased!");
    }
}
