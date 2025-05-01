package org.example.models.interactions.game_buildings;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.game_structure.Inventory;
import org.example.models.goods.Good;
import org.example.models.goods.foragings.MineralType;
import org.example.models.goods.tools.Tool;
import org.example.models.goods.tools.ToolType;
import org.example.models.interactions.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Blacksmith extends GameBuilding {
    private final ArrayList<Integer> upgradeToolCost = new ArrayList<>();
    private final ArrayList<Integer> upgradeTrashCanCost = new ArrayList<>();
    private final ArrayList<Pair> upgradeIngredients = new ArrayList<>();

    public Blacksmith() {
        upgradeToolCost.addAll(Arrays.asList(2000, 5000, 10000, 25000));
        upgradeTrashCanCost.addAll(Arrays.asList(1000, 2500, 5000, 12500));
        upgradeIngredients.addAll(Arrays.asList(new Pair<>(MineralType.COPPER, 5), new Pair<>(MineralType.IRON, 5), new Pair<>(MineralType.GOLD, 5), new Pair<>(MineralType.IRIDIUM, 5)));
    }

    public boolean upgradeTool(Tool tool) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        int nextLevel = ((ToolType) tool.getType()).getLevel().getLevelNumber();

        ArrayList<Good> goods = player.getInventory().isInInventory((Good) upgradeIngredients.get(nextLevel).getFirst());
        if(goods == null || goods.size() < (Integer) upgradeIngredients.get(nextLevel).getSecond())
            return false;

        if(tool.getType() == ToolType.TRASH_CAN) {
            if(player.getWallet().getBalance() < upgradeTrashCanCost.get(nextLevel))
                return false;
            player.getWallet().decreaseBalance(upgradeTrashCanCost.get(nextLevel));
        }
        else {
            if(player.getWallet().getBalance() < upgradeToolCost.get(nextLevel))
                return false;
            player.getWallet().decreaseBalance(upgradeToolCost.get(nextLevel));
        }

        Inventory.decreaseGoods(goods, (Integer) upgradeIngredients.get(nextLevel).getSecond());
        ((ToolType) tool.getType()).setLevel(((ToolType) tool.getType()).getLevel().increaseGoodLevel());
        return true;
    }
}
