package org.example.models.goods.recipes;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.game_structure.Game;
import org.example.models.goods.Good;
import org.example.models.goods.craftings.Crafting;
import org.example.models.goods.craftings.CraftingType;

import java.util.ArrayList;
import java.util.Iterator;

public class CraftingFunctions {

    public void checkCraftingFunctions(CraftingRecipeType type) {

        switch (type) {
            case CHERRY_BOMB -> cherryBombFunc();
            case BOMB -> bombFunc();
            case MEGA_BOMB -> megaBombFunc();
            case SPRINKLER -> sprinklerFunc();
            case QUALITY_SPRINKLER -> qualitySprinklerFunc();
            case IRIDIUM_SPRINKLER -> iridiumSprinklerFunc();
            case CHARCOAL_KILN -> charcoalKlinFunc();
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

        int currentA = 0;
        int currentB = 0;

        for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            for (Good good : goodArrayList) {
                if (good.getName().equals(A)) currentA++;
                else if (good.getName().equals(B)) currentB++;
            }
        }

        if (!skill.equals("null")) {

            switch (skill) {
                case "farming":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getFarmingLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
                case "cooking":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getCookingLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
                case "foraging":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getForagingLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
                case "mining":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
            }
        }

        if (currentA >= requiredA &&
                currentB >= requiredB) {

            int aRemoved = 0;
            int bRemoved = 0;

            for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
                Iterator<Good> iterator = goodArrayList.iterator();
                while (iterator.hasNext() && (aRemoved < requiredA || bRemoved < requiredB)) {
                    Good good = iterator.next();
                    if (good.getName().equals(A) && aRemoved < requiredA) {
                        iterator.remove();
                        aRemoved++;
                    } else if (good.getName().equals(B) && bRemoved < requiredB) {
                        iterator.remove();
                        bRemoved++;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkIsEnough3Item(String A,
                                      String B,
                                      String C,
                                      int requiredA,
                                      int requiredB,
                                      int requiredC,
                                      String skill,
                                      int skillLevel) {

        int currentA = 0;
        int currentB = 0;
        int currentC = 0;

        for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            for (Good good : goodArrayList) {
                if (good.getName().equals(A)) currentA++;
                else if (good.getName().equals(B)) currentB++;
                else if (good.getName().equals(C)) currentC++;
            }
        }

        if (!skill.equals("null")) {

            switch (skill) {
                case "farming":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getFarmingLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
                case "cooking":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getCookingLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
                case "foraging":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getForagingLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
                case "mining":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
            }
        }

        if (currentA >= requiredA &&
                currentB >= requiredB &&
                currentC >= requiredC) {

            int aRemoved = 0;
            int bRemoved = 0;
            int cRemoved = 0;

            for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
                Iterator<Good> iterator = goodArrayList.iterator();
                while (iterator.hasNext() && (aRemoved < requiredA || bRemoved < requiredB || cRemoved < requiredC)) {
                    Good good = iterator.next();
                    if (good.getName().equals(A) && aRemoved < requiredA) {
                        iterator.remove();
                        aRemoved++;
                    } else if (good.getName().equals(B) && bRemoved < requiredB) {
                        iterator.remove();
                        bRemoved++;
                    } else if (good.getName().equals(C) && cRemoved < requiredC) {
                        iterator.remove();
                        cRemoved++;
                    }
                }
            }
        }
        return true;
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

        int currentA = 0;
        int currentB = 0;
        int currentC = 0;
        int currentD = 0;

        for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            for (Good good : goodArrayList) {
                if (good.getName().equals(A)) currentA++;
                else if (good.getName().equals(B)) currentB++;
                else if (good.getName().equals(C)) currentC++;
                else if (good.getName().equals(D)) currentD++;
            }
        }

        if (!skill.equals("null")) {

            switch (skill) {
                case "farming":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getFarmingLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
                case "cooking":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getCookingLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
                case "foraging":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getForagingLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
                case "mining":
                    if (App.getCurrentGame().getCurrentPlayer().getSkill().getMiningLevel() == skillLevel) {
                        break;
                    } else {
                        return false;
                    }
            }
        }

        if (currentA >= requiredA &&
                currentB >= requiredB &&
                currentC >= requiredC &&
                currentD >= requiredD) {

            int aRemoved = 0;
            int bRemoved = 0;
            int cRemoved = 0;
            int dRemoved = 0;

            for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
                Iterator<Good> iterator = goodArrayList.iterator();
                while (iterator.hasNext() && (aRemoved < requiredA || bRemoved < requiredB || cRemoved < requiredC ||
                        dRemoved < requiredD)) {
                    Good good = iterator.next();
                    if (good.getName().equals(A) && aRemoved < requiredA) {
                        iterator.remove();
                        aRemoved++;
                    } else if (good.getName().equals(B) && bRemoved < requiredB) {
                        iterator.remove();
                        bRemoved++;
                    } else if (good.getName().equals(C) && cRemoved < requiredC) {
                        iterator.remove();
                        cRemoved++;
                    } else if (good.getName().equals(D) && dRemoved < requiredD) {
                        iterator.remove();
                        dRemoved++;
                    }
                }
            }
        }
        return true;
    }


    public void cherryBombFunc() {

        if (checkIsEnough2Item("copper ore", "coal", 4, 1, "mining", 1)) {
            Crafting crafting = new Crafting(CraftingType.CHERRY_BOMB);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }

    }

    public void bombFunc() {

        if (checkIsEnough2Item("iron ore", "coal", 4, 1, "mining", 2)) {
            Crafting crafting = new Crafting(CraftingType.BOMB);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }

    }

    public void megaBombFunc() {

        if (checkIsEnough2Item("gold ore", "coal", 4, 1, "mining", 3)) {
            Crafting crafting = new Crafting(CraftingType.MEGA_BOMB);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }

    }

    public void sprinklerFunc() {

        if (checkIsEnough2Item("copper bar", "iron bar", 1, 1, "farming", 1)) {
            Crafting crafting = new Crafting(CraftingType.SPRINKLER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }

    }

    public void qualitySprinklerFunc() {

        if (checkIsEnough2Item("iron bar", "gold bar", 4, 1, "farming", 2)) {
            Crafting crafting = new Crafting(CraftingType.QUALITY_SPRINKLER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }

        } else {
            System.out.println("You don't have enough items to craft");
        }
    }

    public void iridiumSprinklerFunc() {

        if (checkIsEnough2Item("gold bar", "iridium bar", 4, 1, "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.IRIDIUM_SPRINKLER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }
    }

    public void charcoalKlinFunc() {

        if (checkIsEnough2Item("wood", "copper bar", 20, 2, "foraging", 1)) {
            Crafting crafting = new Crafting(CraftingType.CHARCOAL_KILN);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }
    }

    public void furnaceFunc() {

        if (checkIsEnough2Item("copper ore", "stone", 20, 25, "null", 0)) {
            Crafting crafting = new Crafting(CraftingType.FURNACE);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }
    }

    public void scarecrowFunc() {

        if (checkIsEnough3Item("wood", "coal", "fibre", 50, 1, 20,
                "null", 0)) {
            Crafting crafting = new Crafting(CraftingType.SCARECROW);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }
    }

    public void deluxScarecrowFunc() {

        if (checkIsEnough4Item("wood", "coal", "fibre", "iridium", 50, 1, 20,
                1, "null", 0)) {
            Crafting crafting = new Crafting(CraftingType.DELUXE_SCARECROW);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }
    }

    public void beeHouseFunc() {

        if (checkIsEnough3Item("wood", "coal", "iron bar", 40, 8, 1,
                "farming", 1)) {
            Crafting crafting = new Crafting(CraftingType.BEE_HOUSE);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }
    }

    public void cheesePressFunc() {

        if (checkIsEnough3Item("wood", "stone", "copper bar", 45, 45, 1,
                "farming", 2)) {
            Crafting crafting = new Crafting(CraftingType.CHEESE_PRESS);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }
    }

    public void kegFunc() {

        if (checkIsEnough3Item("wood", "copper bar", "iron bar", 30, 1, 1,
                "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.KEG);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }
    }

    public void loomFunc() {

        if (checkIsEnough2Item("wood", "fiber", 60, 30,
                "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.LOOM);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }
    }

    public void mayonnaiseMachineFunc() {

        if (checkIsEnough3Item("wood", "stone", "copper bar", 15, 15, 1,
                "null", 3)) {
            Crafting crafting = new Crafting(CraftingType.MAYONNAISE_MACHINE);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }

    }

    public void oilMakerFunc() {

        if (checkIsEnough3Item("wood", "gold bar", "iron bar", 100, 1, 1,
                "farming", 3)) {
            Crafting crafting = new Crafting(CraftingType.OIL_MAKER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }

    }

    public void preservesJar() {

        if (checkIsEnough3Item("wood", "stone", "coal", 50, 40, 8,
                "farming", 2)) {
            Crafting crafting = new Crafting(CraftingType.PRESERVES_JAR);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }

    }

    public void dehydratorFunc() {

        if (checkIsEnough3Item("wood", "stone", "fiber", 30, 20, 30,
                "null", 3)) {
            Crafting crafting = new Crafting(CraftingType.DEHYDRATOR);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }

    }

    public void fishSmokerFunc() {

        if (checkIsEnough3Item("wood", "iron bar", "coal", 50, 3, 10,
                "null", 3)) {
            Crafting crafting = new Crafting(CraftingType.FISH_SMOKER);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }

    }

    public void mysticTreeSeedFunc() {

        if (checkIsEnough4Item("acorn", "maple seed", "pine cone", "mahogany seed", 5,
                5, 5, 5,
                "foraging", 4)) {
            Crafting crafting = new Crafting(CraftingType.MYSTIC_TREE_SEED);
            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting,1)){
                System.out.println(crafting.getName()+" added to inventory");
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(2);
            } else {
                System.out.println("Your inventory is full");
            }
        } else {
            System.out.println("You don't have enough items to craft");
        }

    }


}
