package org.example.models.interactions.NPCs;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.game_structure.Inventory;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.craftings.Crafting;
import org.example.models.goods.craftings.CraftingType;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.recipes.CookingRecipe;
import org.example.models.goods.recipes.CookingRecipeType;

import java.util.ArrayList;

public class NPCRewardsFunctions {

    public static void sebastianRewards(int index, NPCFriendship npcFriendship) {
        //some types are not completed
        ArrayList<Pair<GoodType,Integer>> requests =npcFriendship.getNpc().getType().getRequests();
        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).getFirst()) >=  requests.get(0).getSecond()) {
            int rewardCount = 2;
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).getFirst() , requests.get(0).getSecond());
            System.out.println("Quest Finished, You received" + rewardCount * npcFriendship.getFriendshipLevel()
                    + "Diamonds"); //? diamonds type
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(null, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).getFirst()) == 1) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).getFirst() , requests.get(1).getSecond());
            int rewardCount = 5000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() +
                    " Golds");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(null, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).getFirst()) == 150){
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).getFirst() , requests.get(2).getSecond());
            int rewardCount = 50;
            System.out.println("Quest Finished, You received " + rewardCount + " Quartz");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(null, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(3);
        }

    }

    public static void abigailRewards(int index, NPCFriendship npcFriendship) {
        ArrayList<Pair<GoodType, Integer>> requests = npcFriendship.getNpc().getType().getRequests();

        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(0).getFirst()) >= requests.get(0).getSecond()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).getFirst(), requests.get(0).getSecond());
            npcFriendship.setFriendshipLevel();
            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).getFirst()) >= requests.get(1).getSecond()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).getFirst(), requests.get(1).getSecond());
            int rewardCount = 500;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Pumpkins");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(null, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).getFirst()) >= requests.get(2).getSecond()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).getFirst(), requests.get(2).getSecond());
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
                        (requests.get(0).getFirst()) >= requests.get(0).getSecond()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).getFirst(), requests.get(0).getSecond());
            int rewardCount = 750;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Golds");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(null, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).getFirst()) >= requests.get(1).getSecond() &&
                good.getType().equals(FoodType.SALMON_DINNER)) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).getFirst(), requests.get(1).getSecond());
            npcFriendship.setFriendshipLevel();
            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).getFirst()) >= requests.get(2).getSecond()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).getFirst(), requests.get(2).getSecond());
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
                        (requests.get(0).getFirst()) >= requests.get(0).getSecond()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).getFirst(), requests.get(0).getSecond());
            int rewardCount = 500;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Golds");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(null, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).getFirst()) >= requests.get(1).getSecond() &&
                good.getType().equals(FoodType.SALMON_DINNER)) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).getFirst(), requests.get(1).getSecond());
            System.out.println("Quest Finished, You received " + CookingRecipeType.SALMON_DINNER.getFoodType().getName());
            App.getCurrentGame().getCurrentPlayer().getCookingRecipes()
                    .add(new CookingRecipe(CookingRecipeType.SALMON_DINNER));
            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).getFirst()) >= requests.get(2).getSecond()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).getFirst(), requests.get(2).getSecond());
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
                        (requests.get(0).getFirst()) >= requests.get(0).getSecond()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(0).getFirst(), requests.get(0).getSecond());
            int rewardCount = 1000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Golds");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(null, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(1).getFirst()) >= requests.get(1).getSecond()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(1).getFirst(), requests.get(1).getSecond());
            Crafting crafting = new Crafting(CraftingType.BEE_HOUSE);
            System.out.println("Quest Finished, You received " + 3 * npcFriendship.getFriendshipLevel() + " " + crafting.getName());
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(crafting, 3 * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType
                        (requests.get(2).getFirst()) >= requests.get(2).getSecond()) {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(
                    requests.get(2).getFirst(), requests.get(2).getSecond());
            Crafting crafting = new Crafting(CraftingType.DELUXE_SCARECROW);
            int rewardCount = 25000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() + " Golds");
            App.getCurrentGame().getCurrentPlayer().getInventory()
                    .addGood(crafting, 3 * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(3);
        }
    }


}