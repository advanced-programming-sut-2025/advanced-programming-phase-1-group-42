package com.StardewValley.models.goods.recipes;

import com.StardewValley.models.App;
import com.StardewValley.models.Result;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.craftings.Crafting;
import com.StardewValley.models.goods.craftings.CraftingType;

public class CraftingFunctions {

    public Result checkCraftingFunctions(CraftingRecipeType type) {

        Result result = null;
        switch (type) {
            case CHERRY_BOMB -> result = cherryBombFunc();
            case BOMB -> result = bombFunc();
            case MEGA_BOMB -> result = megaBombFunc();
            case SPRINKLER -> result = sprinklerFunc();
            case QUALITY_SPRINKLER -> result = qualitySprinklerFunc();
            case IRIDIUM_SPRINKLER -> result = iridiumSprinklerFunc();
            case CHARCOAL_KILN -> result = charcoalKilnFunc();
            case FURNACE -> result = furnaceFunc();
            case SCARECROW -> result = scarecrowFunc();
            case DELUXE_SCARECROW -> result = deluxScarecrowFunc();
            case BEE_HOUSE -> result = beeHouseFunc();
            case CHEESE_PRESS -> result = cheesePressFunc();
            case KEG -> result = kegFunc();
            case LOOM -> result = loomFunc();
            case MAYONNAISE_MACHINE -> result = mayonnaiseMachineFunc();
            case OIL_MAKER -> result = oilMakerFunc();
            case PRESERVES_JAR -> result = preservesJar();
            case DEHYDRATOR -> result = dehydratorFunc();
            case FISH_SMOKER -> result = fishSmokerFunc();
            case MYSTIC_TREE_SEED -> result = mysticTreeSeedFunc();

        }
        return result;

    }

    public boolean checkIsEnough2Item(String A,
                                      String B,
                                      int requiredA,
                                      int requiredB,
                                      String skill,
                                      int skillLevel) {

        int currentA = App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(Good.newGoodType(A));
        int currentB = App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(Good.newGoodType(B));
        boolean found = false;

        if (!skill.equals("null")) {

            switch (skill) {
                case "farming":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getFarmingLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
                case "cooking":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getCookingLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
                case "foraging":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getForagingLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
                case "mining":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
            }
        }
        if (skill.equals("null")) {
            found = true;
        }
        if (!found) {
            return false;
        }

        if (currentA >= requiredA &&
            currentB >= requiredB) {

            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(Good.newGoodType(A), requiredA);
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(Good.newGoodType(B), requiredB);
            return true;
        }
        return false;

    }

    public boolean checkIsEnough3Item(String A,
                                      String B,
                                      String C,
                                      int requiredA,
                                      int requiredB,
                                      int requiredC,
                                      String skill,
                                      int skillLevel) {


        int currentA = App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(Good.newGoodType(A));
        int currentB = App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(Good.newGoodType(B));
        int currentC = App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(Good.newGoodType(C));

        boolean found = false;

        if (!skill.equals("null")) {

            switch (skill) {
                case "farming":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getFarmingLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
                case "cooking":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getCookingLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
                case "foraging":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getForagingLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
                case "mining":
                    System.out.println(App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel());
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
            }
        }
        if (skill.equals("null")) {
            found = true;
        }
        if (!found) {
            return false;
        }

        if (currentA >= requiredA &&
            currentB >= requiredB &&
            currentC >= requiredC) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(Good.newGoodType(A), requiredA);
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(Good.newGoodType(B), requiredB);
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(Good.newGoodType(C), requiredC);

            return true;
        }

        return false;
    }


    public boolean checkIsEnough4Item(String A,
                                      String B,
                                      String C,
                                      String D,
                                      int requiredA,
                                      int requiredB,
                                      int requiredC,
                                      int requiredD,
                                      String skill,
                                      int skillLevel) {

        int currentA = App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(Good.newGoodType(A));
        int currentB = App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(Good.newGoodType(B));
        int currentC = App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(Good.newGoodType(C));
        int currentD = App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(Good.newGoodType(D));


        boolean found = false;

        if (!skill.equals("null")) {

            switch (skill) {
                case "farming":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getFarmingLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
                case "cooking":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getCookingLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
                case "foraging":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getForagingLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
                case "mining":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() >= skillLevel) {
                        found = true;
                        break;
                    }
            }
        }
        if (skill.equals("null")) {
            found = true;
        }
        if (!found) {
            return false;
        }

        if (currentA >= requiredA &&
            currentB >= requiredB &&
            currentC >= requiredC &&
            currentD >= requiredD) {

            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(Good.newGoodType(A), requiredA);
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(Good.newGoodType(B), requiredB);
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(Good.newGoodType(C), requiredC);
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(Good.newGoodType(D), requiredD);

            return true;
        }
        return false;
    }

    public Result cherryBombFunc() {
        if (checkIsEnough2Item("Copper_Ore", "Coal", 4, 1, "mining", 1)) {
            Crafting crafting = new Crafting(CraftingType.CHERRY_BOMB);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result bombFunc() {
        if (checkIsEnough2Item("Iron_Ore", "Coal", 4, 1, "mining", 2)) {
            Crafting crafting = new Crafting(CraftingType.BOMB);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result megaBombFunc() {
        if (checkIsEnough2Item("Gold_Ore", "Coal", 4, 1, "mining", 3)) {
            Crafting crafting = new Crafting(CraftingType.MEGA_BOMB);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result sprinklerFunc() {
        if (checkIsEnough2Item("Copper_Bar", "Iron_Bar", 1, 1, "farming", 1)) {
            Crafting crafting = new Crafting(CraftingType.SPRINKLER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result qualitySprinklerFunc() {
        if (checkIsEnough2Item("Iron_Bar", "Gold_Bar", 4, 1, "farming", 2)) {
            Crafting crafting = new Crafting(CraftingType.QUALITY_SPRINKLER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result iridiumSprinklerFunc() {
        if (checkIsEnough2Item("Gold_Bar", "Iridium_Bar", 4, 1, "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.IRIDIUM_SPRINKLER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result charcoalKilnFunc() {
        if (checkIsEnough2Item("Wood", "Copper_Bar", 20, 2, "foraging", 1)) {
            Crafting crafting = new Crafting(CraftingType.CHARCOAL_KILN);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result furnaceFunc() {
        if (checkIsEnough2Item("Copper_Ore", "Stone", 20, 25, "null", 0)) {
            Crafting crafting = new Crafting(CraftingType.FURNACE);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result scarecrowFunc() {
        if (checkIsEnough3Item("Wood", "Coal", "Fiber", 50, 1, 20, "null", 0)) {
            Crafting crafting = new Crafting(CraftingType.SCARECROW);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result deluxScarecrowFunc() {
        if (checkIsEnough4Item("Wood", "Coal", "Fiber", "Iridium", 50, 1, 20, 1, "null", 0)) {
            Crafting crafting = new Crafting(CraftingType.DELUXE_SCARECROW);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result beeHouseFunc() {
        if (checkIsEnough3Item("Wood", "Coal", "Iron_Bar", 40, 8, 1, "farming", 1)) {
            Crafting crafting = new Crafting(CraftingType.BEE_HOUSE);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result cheesePressFunc() {
        if (checkIsEnough3Item("Wood", "Stone", "Copper_Bar", 45, 45, 1, "farming", 2)) {
            Crafting crafting = new Crafting(CraftingType.CHEESE_PRESS);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result kegFunc() {
        if (checkIsEnough3Item("Wood", "Copper_Bar", "Iron_Bar", 30, 1, 1, "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.KEG);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result loomFunc() {
        if (checkIsEnough2Item("Wood", "Fiber", 60, 30, "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.LOOM);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result mayonnaiseMachineFunc() {
        if (checkIsEnough3Item("Wood", "Stone", "Copper_Bar", 15, 15, 1, "null", 3)) {
            Crafting crafting = new Crafting(CraftingType.MAYONNAISE_MACHINE);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result oilMakerFunc() {
        if (checkIsEnough2Item("Wood", "Copper_Bar", 20, 1, "farming", 6)) {
            Crafting crafting = new Crafting(CraftingType.OIL_MAKER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result preservesJar() {
        if (checkIsEnough2Item("Wood", "Copper_Bar", 50, 1, "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.PRESERVES_JAR);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result dehydratorFunc() {
        if (checkIsEnough3Item("Wood", "Iron_Bar", "Coal", 50, 1, 10, "null", 5)) {
            Crafting crafting = new Crafting(CraftingType.DEHYDRATOR);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }

    public Result fishSmokerFunc() {
        if (checkIsEnough3Item("Wood", "Stone", "Coal", 50, 15, 1, "null", 6)) {
            Crafting crafting = new Crafting(CraftingType.FISH_SMOKER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
                return new Result(true, crafting.getName() + " added to inventory");
            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }
    }
    public Result mysticTreeSeedFunc() {

        if (checkIsEnough4Item("Acorn", "Maple_Seeds", "Pine_Cones", "Mahogany_Seeds", 5,
            5, 5, 5,
            "foraging", 4)) {
            Crafting crafting = new Crafting(CraftingType.MYSTIC_TREE_SEED);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);                System.out.println(crafting.getName() + " added to inventory");
                return new Result(true, crafting.getName() + " added to inventory");

            } else {
                return new Result(false, "Your inventory is full");
            }
        } else {
            return new Result(false, "You don't have enough items-skills to craft");
        }

    }


}
