package org.example.models.interactions.NPCs;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.craftings.Crafting;
import org.example.models.goods.craftings.CraftingType;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foragings.ForagingMineralType;
import org.example.models.goods.products.ProductType;
import org.example.models.goods.recipes.CookingRecipe;
import org.example.models.goods.recipes.CookingRecipeType;

import java.util.ArrayList;

public class NPCRewardsFunctions {

    public static void sebastianRewards(int index, NPCFriendship npcFriendship) {
        //some types are not completed
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();
        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            int rewardCount = 2;
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            System.out.println("Quest Finished, You received" + rewardCount * npcFriendship.getFriendshipLevel()
                    + "Diamonds"); //? diamonds type
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.DIAMOND), rewardCount * npcFriendship.getFriendshipLevel()));
            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).first()) == 1) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            int rewardCount = 5000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() +
                    " Golds");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.GOLD), rewardCount * npcFriendship.getFriendshipLevel()));
            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).first()) == 150) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            int rewardCount = 50;
            System.out.println("Quest Finished, You received " + rewardCount + " Quartz");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.QUARTZ), rewardCount * npcFriendship.getFriendshipLevel()));
            npcFriendship.getAvailableQuests().remove(3);
        }

    }

    public static void abigailRewards(int index, NPCFriendship npcFriendship) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            npcFriendship.setFriendshipLevel();
            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            int rewardCount = 500;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Pumpkins");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.GOLD), rewardCount * npcFriendship.getFriendshipLevel()));

            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            System.out.println("Quest Finished, You received a " + CraftingType.IRIDIUM_SPRINKLER.getName());
            Crafting crafting = new Crafting(CraftingType.IRIDIUM_SPRINKLER);
            App.getCurrentGame().getCurrentPlayer().getInventory().addGood(crafting, 1);
            npcFriendship.getAvailableQuests().remove(3);
        }
    }

    public static void harveyRewards(int index, NPCFriendship npcFriendship) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            int rewardCount = 750;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Golds");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.GOLD), rewardCount * npcFriendship.getFriendshipLevel()));

            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            npcFriendship.setFriendshipLevel();
            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            int rewardCount = 5;
            System.out.println("Quest Finished, You received " + rewardCount + " Salads");
            Food food = new Food(FoodType.SALAD);
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(food, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(3);
        }
    }

    public static void leahRewards(int index, NPCFriendship npcFriendship) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            int rewardCount = 500;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Golds");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ProductType.HARD_WOOD), rewardCount * npcFriendship.getFriendshipLevel()));

            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            System.out.println("Quest Finished, You received " + CookingRecipeType.SALMON_DINNER.getGoodType().getName());
            App.getCurrentGame().getCurrentPlayer().getCookingRecipes()
                    .add(new CookingRecipe(CookingRecipeType.SALMON_DINNER));
            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            Crafting crafting = new Crafting(CraftingType.DELUXE_SCARECROW);
            System.out.println("Quest Finished, You received " + 3 * npcFriendship.getFriendshipLevel() + " " + crafting.getName());
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(crafting, 3 * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(3);
        }
    }

    public static void robinRewards(int index, NPCFriendship npcFriendship) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).first()) >= requests.get(0).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).first(), requests.get(0).second());
            int rewardCount = 1000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Golds");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(null, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).first()) >= requests.get(1).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).first(), requests.get(1).second());
            Crafting crafting = new Crafting(CraftingType.BEE_HOUSE);
            System.out.println("Quest Finished, You received " + 3 * npcFriendship.getFriendshipLevel() + " " + crafting.getName());
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(crafting, 3 * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).first()) >= requests.get(2).second()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).first(), requests.get(2).second());
            int rewardCount = 25000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Golds");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(Good.newGoods((ForagingMineralType.GOLD), rewardCount * npcFriendship.getFriendshipLevel()));

            npcFriendship.getAvailableQuests().remove(3);
        }
    }


}