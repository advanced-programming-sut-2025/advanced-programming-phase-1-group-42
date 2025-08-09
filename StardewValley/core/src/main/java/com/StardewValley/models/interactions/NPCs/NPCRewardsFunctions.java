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
import com.StardewValley.models.interactions.Player;
import com.StardewValley.server.ClientHandler;

import java.util.ArrayList;

public class NPCRewardsFunctions {

    public static void sebastianRewards(int index, NPCFriendship npcFriendship, ClientHandler clientHandler) {
        Player player = clientHandler.getClientPlayer();
        //some types are not completed
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();
        if (npcFriendship.getAvailableQuests().contains(1) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            int rewardCount = 2;
            player.getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            System.out.println("Quest Finished, You received " + rewardCount
                    + " Diamonds");
            player.getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.DIAMOND), rewardCount),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(1));
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(1).first()) == 1) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            int rewardCount = 5000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() +
                    " Golds");
            player.getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.GOLD_ORE), rewardCount * npcFriendship.getFriendshipLevel()),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(2));
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(2).first()) == 150) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            int rewardCount = 50;
            System.out.println("Quest Finished, You received " + rewardCount + " Quartz");
            player.getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.QUARTZ), rewardCount * npcFriendship.getFriendshipLevel()),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(3));
        } else {
            System.out.println("You don't have enough items to finish the quest");
        }

    }

    public static void abigailRewards(int index, NPCFriendship npcFriendship, ClientHandler clientHandler) {
        Player player = clientHandler.getClientPlayer();
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            npcFriendship.setFriendshipLevel();
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(1));
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            int rewardCount = 500;
            System.out.println("Quest Finished, You received " + rewardCount + " Pumpkins");
            player.getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.GOLD_ORE), rewardCount),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());

            npcFriendship.getAvailableQuests().remove(Integer.valueOf(2));
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            System.out.println("Quest Finished, You received a " + CraftingType.IRIDIUM_SPRINKLER.getName());
            Crafting crafting = new Crafting(CraftingType.IRIDIUM_SPRINKLER);
            player.getInventory().addGood(crafting, 1, clientHandler.getClientGame(), clientHandler.getClientPlayer());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(3));
        } else {
            System.out.println("You don't have enough items to finish the quest");
        }
    }

    public static void harveyRewards(int index, NPCFriendship npcFriendship, ClientHandler clientHandler) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();
        Player player = clientHandler.getClientPlayer();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            for (ArrayList<Good> goods : player.getInventory().getList()) {
                for (Good good : goods) {
                    if (good instanceof FarmingCrop) {
                        int count = player.getInventory()
                                .howManyInInventoryByType(good.getType());
                        if (count <= requests.getFirst().second()) {
                            System.out.println("You don't have enough items to finish the quest");
                            return;
                        } else {
                            int rewardCount = 750;
                            System.out.println("Quest Finished, You received " + rewardCount + " Golds");
                            player.getInventory()
                                    .addGood(Good.newGoods((ForagingMineralType.GOLD_ORE), rewardCount),
                                        clientHandler.getClientGame(), clientHandler.getClientPlayer());

                            npcFriendship.getAvailableQuests().remove(Integer.valueOf(1));
                        }
                    }
                }
            }

        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            npcFriendship.setFriendshipLevel();
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(2));
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            int rewardCount = 5;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Salads");
            Food food = new Food(FoodType.SALAD);
            player.getInventory()
                    .addGood(food, rewardCount * npcFriendship.getFriendshipLevel(),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(3));
        } else {
            System.out.println("You don't have enough items to finish the quest");
        }
    }

    public static void leahRewards(int index, NPCFriendship npcFriendship, ClientHandler clientHandler) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();
        Player player = clientHandler.getClientPlayer();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            int rewardCount = 500;
            System.out.println("Quest Finished, You received " + rewardCount + " Golds");
            player.getInventory()
                    .addGood(Good.newGoods((ProductType.GOLD_BAR), rewardCount),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());

            npcFriendship.getAvailableQuests().remove(Integer.valueOf(1));
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            System.out.println("Quest Finished, You received " + CookingRecipeType.SALMON_DINNER.getGoodType().getName());
            player.getCookingRecipes()
                    .add(new CookingRecipe(CookingRecipeType.SALMON_DINNER));
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(2));
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            Crafting crafting = new Crafting(CraftingType.DELUXE_SCARECROW);
            System.out.println("Quest Finished, You received " + 3 * npcFriendship.getFriendshipLevel() + " " + crafting.getName());
            player.getInventory()
                    .addGood(crafting, 3 * npcFriendship.getFriendshipLevel(),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(3));
        } else {
            System.out.println("You don't have enough items to finish the quest");
        }
    }

    public static void robinRewards(int index, NPCFriendship npcFriendship, ClientHandler clientHandler) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();
        Player player = clientHandler.getClientPlayer();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            int rewardCount = 1000;
            System.out.println("Quest Finished, You received " + rewardCount + " Golds");
            player.getInventory()
                    .addGood(Good.newGoods(ProductType.GOLD_BAR,rewardCount),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(1));
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            Crafting crafting = new Crafting(CraftingType.BEE_HOUSE);
            System.out.println("Quest Finished, You received " + 3 * npcFriendship.getFriendshipLevel() + " " + crafting.getName());
            player.getInventory()
                    .addGood(crafting, 3 * npcFriendship.getFriendshipLevel(),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());
            npcFriendship.getAvailableQuests().remove(Integer.valueOf(2));
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                player.getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            player.getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            int rewardCount = 25000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Golds");
            player.getInventory()
                    .addGood(Good.newGoods((ProductType.GOLD_BAR), rewardCount * npcFriendship.getFriendshipLevel()),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());

            npcFriendship.getAvailableQuests().remove(Integer.valueOf(3));
        } else {
            System.out.println("You don't have enough items to finish the quest");
        }
    }


}
