package com.StardewValley.models.interactions.NPCs;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.craftings.Crafting;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.farmings.FarmingCrop;
import com.StardewValley.models.goods.foods.Food;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.ForagingMineralType;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.goods.recipes.CookingRecipe;
import com.StardewValley.models.goods.recipes.CookingRecipeType;

import java.util.ArrayList;

public class NPCRewardsFunctions {

    public static void sebastianRewards(int index, NPCFriendship npcFriendship) {
        //some types are not completed
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();
        if (npcFriendship.getAvailableQuests().contains(1) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            int rewardCount = 2;
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            System.out.println("Quest Finished, You received " + rewardCount
                    + " Diamonds");
            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.DIAMOND), rewardCount));
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(1));
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).first()) == 1) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            int rewardCount = 5000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() +
                    " Golds");
            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.GOLD_ORE), rewardCount * npcFriendship.getFriendshipLevel()));
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(2));
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).first()) == 150) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            int rewardCount = 50;
            System.out.println("Quest Finished, You received " + rewardCount + " Quartz");
            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.QUARTZ), rewardCount * npcFriendship.getFriendshipLevel()));
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(3));
        } else {
            System.out.println("You don't have enough items to finish the quest");
        }

    }

    public static void abigailRewards(int index, NPCFriendship npcFriendship) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            npcFriendship.setFriendshipLevel();
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(1));
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            int rewardCount = 500;
            System.out.println("Quest Finished, You received " + rewardCount + " Pumpkins");
            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.GOLD_ORE), rewardCount));

            npcFriendship.getAvailableQuests().remove(Integer.valueOf(2));
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            System.out.println("Quest Finished, You received a " + CraftingType.IRIDIUM_SPRINKLER.getName());
            Crafting crafting = new Crafting(CraftingType.IRIDIUM_SPRINKLER);
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1);
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(3));
        } else {
            System.out.println("You don't have enough items to finish the quest");
        }
    }

    public static void harveyRewards(int index, NPCFriendship npcFriendship) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            for (ArrayList<Good> goods : AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
                for (Good good : goods) {
                    if (good instanceof FarmingCrop) {
                        int count = AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                                .howManyInInventoryByType(good.getType());
                        if (count <= requests.getFirst().second()) {
                            System.out.println("You don't have enough items to finish the quest");
                            return;
                        } else {
                            int rewardCount = 750;
                            System.out.println("Quest Finished, You received " + rewardCount + " Golds");
                            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                                    .addGood(Good.newGoods((ForagingMineralType.GOLD_ORE), rewardCount));

                            npcFriendship.getAvailableQuests().remove(Integer.valueOf(1));
                        }
                    }
                }
            }

        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            npcFriendship.setFriendshipLevel();
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(2));
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            int rewardCount = 5;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Salads");
            Food food = new Food(FoodType.SALAD);
            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(food, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(3));
        } else {
            System.out.println("You don't have enough items to finish the quest");
        }
    }

    public static void leahRewards(int index, NPCFriendship npcFriendship) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            int rewardCount = 500;
            System.out.println("Quest Finished, You received " + rewardCount + " Golds");
            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ProductType.GOLD_BAR), rewardCount));

            npcFriendship.getAvailableQuests().remove(Integer.valueOf(1));
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            System.out.println("Quest Finished, You received " + CookingRecipeType.SALMON_DINNER.getGoodType().getName());
            AppClient.getCurrentGame().getCurrentPlayer().getCookingRecipes()
                    .add(new CookingRecipe(CookingRecipeType.SALMON_DINNER));
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(2));
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            Crafting crafting = new Crafting(CraftingType.DELUXE_SCARECROW);
            System.out.println("Quest Finished, You received " + 3 * npcFriendship.getFriendshipLevel() + " " + crafting.getName());
            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(crafting, 3 * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(3));
        } else {
            System.out.println("You don't have enough items to finish the quest");
        }
    }

    public static void robinRewards(int index, NPCFriendship npcFriendship) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            int rewardCount = 1000;
            System.out.println("Quest Finished, You received " + rewardCount + " Golds");
            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods(ProductType.GOLD_BAR,rewardCount));
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(1));
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            Crafting crafting = new Crafting(CraftingType.BEE_HOUSE);
            System.out.println("Quest Finished, You received " + 3 * npcFriendship.getFriendshipLevel() + " " + crafting.getName());
            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(crafting, 3 * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(2));
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            int rewardCount = 25000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Golds");
            AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ProductType.GOLD_BAR), rewardCount * npcFriendship.getFriendshipLevel()));

            npcFriendship.getAvailableQuests().remove(Integer.valueOf(3));
        } else {
            System.out.println("You don't have enough items to finish the quest");
        }
    }


}
