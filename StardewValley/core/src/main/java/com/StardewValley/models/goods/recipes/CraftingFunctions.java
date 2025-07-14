package com.StardewValley.models.goods.recipes;

import com.StardewValley.models.App;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.craftings.Crafting;
import com.StardewValley.models.goods.craftings.CraftingType;

public class CraftingFunctions {

    public void checkCraftingFunctions(CraftingRecipeType type) {

        switch (type) {
            case CHERRY_BOMB -> cherryBombFunc();
            case BOMB -> bombFunc();
            case MEGA_BOMB -> megaBombFunc();
            case SPRINKLER -> sprinklerFunc();
            case QUALITY_SPRINKLER -> qualitySprinklerFunc();
            case IRIDIUM_SPRINKLER -> iridiumSprinklerFunc();
            case CHARCOAL_KILN -> charcoalKilnFunc();
            case FURNACE -> furnaceFunc();
            case SCARECROW -> scarecrowFunc();
            case DELUXE_SCARECROW -> deluxScarecrowFunc();
            case BEE_HOUSE -> beeHouseFunc();
            case CHEESE_PRESS -> cheesePressFunc();
            case KEG -> kegFunc();
            case LOOM -> loomFunc();
            case MAYONNAISE_MACHINE -> mayonnaiseMachineFunc();
            case OIL_MAKER -> oilMakerFunc();
            case PRESERVES_JAR -> preservesJar();
            case DEHYDRATOR -> dehydratorFunc();
            case FISH_SMOKER -> fishSmokerFunc();
            case MYSTIC_TREE_SEED -> mysticTreeSeedFunc();

        }

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
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getFarmingLevel() == skillLevel) {
                        found = true;
                        break;
                    }
                case "cooking":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getCookingLevel() == skillLevel) {
                        found = true;
                        break;
                    }
                case "foraging":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getForagingLevel() == skillLevel) {
                        found = true;
                        break;
                    }
                case "mining":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() == skillLevel) {
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
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getFarmingLevel() == skillLevel) {
                        found = true;
                        break;
                    }
                case "cooking":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getCookingLevel() == skillLevel) {
                        found = true;
                        break;
                    }
                case "foraging":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getForagingLevel() == skillLevel) {
                        found = true;
                        break;
                    }
                case "mining":
                    System.out.println(App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel());
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() == skillLevel) {
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
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getFarmingLevel() == skillLevel) {
                        found = true;
                        break;
                    }
                case "cooking":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getCookingLevel() == skillLevel) {
                        found = true;
                        break;
                    }
                case "foraging":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getForagingLevel() == skillLevel) {
                        found = true;
                        break;
                    }
                case "mining":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() == skillLevel) {
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


    public void cherryBombFunc() {

        if (checkIsEnough2Item("Copper_Ore", "Coal", 4, 1, "mining", 1)) {
            Crafting crafting = new Crafting(CraftingType.CHERRY_BOMB);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }

    }

    public void bombFunc() {

        if (checkIsEnough2Item("Iron_Ore", "Coal", 4, 1, "mining", 2)) {
            Crafting crafting = new Crafting(CraftingType.BOMB);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }

    }

    public void megaBombFunc() {

        if (checkIsEnough2Item("Gold_Ore", "Coal", 4, 1, "mining", 3)) {
            Crafting crafting = new Crafting(CraftingType.MEGA_BOMB);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }

    }

    public void sprinklerFunc() {

        if (checkIsEnough2Item("Copper_Bar", "Iron_Bar", 1, 1, "farming", 1)) {
            Crafting crafting = new Crafting(CraftingType.SPRINKLER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }

    }

    public void qualitySprinklerFunc() {

        if (checkIsEnough2Item("Iron_Bar", "Gold_Bar", 4, 1, "farming", 2)) {
            Crafting crafting = new Crafting(CraftingType.QUALITY_SPRINKLER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }

        } else {
            System.out.println("You don't have enough items/skills to craft");
        }
    }

    public void iridiumSprinklerFunc() {

        if (checkIsEnough2Item("Gold_Bar", "Iridium_Bar", 4, 1, "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.IRIDIUM_SPRINKLER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }
    }

    public void charcoalKilnFunc() {

        if (checkIsEnough2Item("Wood", "Copper_Bar", 20, 2, "foraging", 1)) {
            Crafting crafting = new Crafting(CraftingType.CHARCOAL_KILN);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }
    }

    public void furnaceFunc() {

        if (checkIsEnough2Item("Copper_Ore", "Stone", 20, 25, "null", 0)) {
            Crafting crafting = new Crafting(CraftingType.FURNACE);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }
    }

    public void scarecrowFunc() {

        if (checkIsEnough3Item("Wood", "Coal", "Fiber", 50, 1, 20,
                "null", 0)) {
            Crafting crafting = new Crafting(CraftingType.SCARECROW);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }
    }

    public void deluxScarecrowFunc() {

        if (checkIsEnough4Item("Wood", "Coal", "Fiber", "Iridium", 50, 1, 20,
                1, "null", 0)) {
            Crafting crafting = new Crafting(CraftingType.DELUXE_SCARECROW);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }
    }

    public void beeHouseFunc() {

        if (checkIsEnough3Item("Wood", "Coal", "Iron_Bar", 40, 8, 1,
                "farming", 1)) {
            Crafting crafting = new Crafting(CraftingType.BEE_HOUSE);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }
    }

    public void cheesePressFunc() {

        if (checkIsEnough3Item("Wood", "Stone", "Copper_Bar", 45, 45, 1,
                "farming", 2)) {
            Crafting crafting = new Crafting(CraftingType.CHEESE_PRESS);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }
    }

    public void kegFunc() {

        if (checkIsEnough3Item("Wood", "Copper_Bar", "Iron_Bar", 30, 1, 1,
                "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.KEG);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }
    }

    public void loomFunc() {

        if (checkIsEnough2Item("Wood", "Fiber", 60, 30,
                "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.LOOM);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }
    }

    public void mayonnaiseMachineFunc() {

        if (checkIsEnough3Item("Wood", "Stone", "Copper_Bar", 15, 15, 1,
                "null", 3)) {
            Crafting crafting = new Crafting(CraftingType.MAYONNAISE_MACHINE);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }

    }

    public void oilMakerFunc() {

        if (checkIsEnough3Item("Wood", "Gold_Bar", "Iron_Bar", 100, 1, 1,
                "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.OIL_MAKER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }

    }

    public void preservesJar() {

        if (checkIsEnough3Item("Wood", "Stone", "Coal", 50, 40, 8,
                "farming", 2)) {
            Crafting crafting = new Crafting(CraftingType.PRESERVES_JAR);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }

    }

    public void dehydratorFunc() {

        if (checkIsEnough3Item("Wood", "Stone", "Fiber", 30, 20, 30,
                "null", 3)) {
            Crafting crafting = new Crafting(CraftingType.DEHYDRATOR);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }

    }

    public void fishSmokerFunc() {

        if (checkIsEnough3Item("Wood", "Iron_Bar", "Coal", 50, 3, 10,
                "null", 3)) {
            Crafting crafting = new Crafting(CraftingType.FISH_SMOKER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }

    }

    public void mysticTreeSeedFunc() {

        if (checkIsEnough4Item("Acorn", "Maple_Seeds", "Pine_Cones", "Mahogany_Seeds", 5,
                5, 5, 5,
                "foraging", 4)) {
            Crafting crafting = new Crafting(CraftingType.MYSTIC_TREE_SEED);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1)) {
                System.out.println(crafting.getName() + " added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items/skills to craft");
        }

    }


}
