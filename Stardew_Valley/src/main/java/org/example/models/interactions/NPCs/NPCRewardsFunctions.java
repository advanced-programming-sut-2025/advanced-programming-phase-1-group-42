package org.example.models.interactions.NPCs;

import org.example.models.App;
import org.example.models.game_structure.Inventory;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.artisans.ArtisanType;
import org.example.models.goods.craftings.Crafting;
import org.example.models.goods.craftings.CraftingType;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.recipes.CookingRecipe;
import org.example.models.goods.recipes.CookingRecipeType;

public class NPCRewardsFunctions {

    public static void sebastianRewards(Good good, NPCFriendship npcFriendship) {
        //some types are not completed
        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 50) {
            int rewardCount = 2;
            System.out.println("Quest Finished, You received" + rewardCount * npcFriendship.getFriendshipLevel()
                    + "Diamonds"); //? diamonds type
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(good, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(1);
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 1 &&
                good.getType().equals(FoodType.PUMPKIN_PIE)) {
            int rewardCount = 5000;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() +
                    " Golds");
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(good, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(2);
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 150){
            int rewardCount = 50;
            System.out.println("Quest Finished, You received " + rewardCount + " Quartz");
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(good, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(3);
        }

    }


    public static void abigailRewards(Good good, NPCFriendship npcFriendship) {
        //some types are not completed
        //gold
        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 1) {
            npcFriendship.setFriendshipLevel();
            npcFriendship.getAvailableQuests().remove(1);
            // pumpkin
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 1 &&
                good.getType().equals(FoodType.PUMPKIN_PIE)) {
            int rewardCount = 500;
            System.out.println("Quest Finished, You received " + rewardCount * npcFriendship.getFriendshipLevel() +
                    " Pumpkins");
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(good, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(2);
            // wheat
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 50){
            System.out.println("Quest Finished, You received a" + CraftingType.IRIDIUM_SPRINKLER.getName());
            Crafting crafting = new Crafting(CraftingType.IRIDIUM_SPRINKLER);
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory().addGood(crafting,1);
            npcFriendship.getAvailableQuests().remove(3);
        }
    }


    public static void harveyRewards(Good good, NPCFriendship npcFriendship) {
        //some types are not completed
        //any crops
        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 12) {
            int rewardCount = 750;
            System.out.println("Quest Finished, You received" + rewardCount * npcFriendship.getFriendshipLevel()
                    + "Golds"); //? diamonds type

            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(good, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(1);
            // pumpkin
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 1 &&
                good.getType().equals(FoodType.SALMON_DINNER)) {
            npcFriendship.setFriendshipLevel();
            npcFriendship.getAvailableQuests().remove(2);
            // salads
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 50){
            int rewardCount = 5;
            System.out.println("Quest Finished, You received " + rewardCount + " Salads");
            Food food = new Food(FoodType.SALAD);
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(food, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(3);
        }
    }

    public static void leahRewards(Good good, NPCFriendship npcFriendship) {
        //some types are not completed
        // rough woods
        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 10) {
            int rewardCount = 500;
            System.out.println("Quest Finished, You received" + rewardCount * npcFriendship.getFriendshipLevel()
                    + "Golds");
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(good, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(1);
            // salmon fish
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 1 &&
                good.getType().equals(FoodType.SALMON_DINNER)) {
            System.out.println("Quest Finished, You received" + CookingRecipeType.SALMON_DINNER.getFoodType().getName());
            App.getCurrentGame().getCurrentPlayingPlayer().getCookingRecipes()
                    .add(new CookingRecipe(CookingRecipeType.SALMON_DINNER));
            npcFriendship.getAvailableQuests().remove(2);
            // woods
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 200){
            Crafting crafting = new Crafting(CraftingType.DELUXE_SCARECROW);
            System.out.println("Quest Finished, You received" + 3 * npcFriendship.getFriendshipLevel()
                    + " " + crafting.getName());
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(crafting,3 * npcFriendship.getFriendshipLevel());

        }
    }

    public static void robinRewards(Good good, NPCFriendship npcFriendship) {
        //some types are not completed
        // woods
        if (npcFriendship.getAvailableQuests().contains(1) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 80) {
            int rewardCount = 1000;
            System.out.println("Quest Finished, You received" + rewardCount * npcFriendship.getFriendshipLevel()
                    + "Golds");
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(good, rewardCount * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(1);
            // iron bar
        } else if (npcFriendship.getAvailableQuests().contains(2) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 10 ) {
            Crafting crafting = new Crafting(CraftingType.BEE_HOUSE);
            System.out.println("Quest Finished, You received" + 3 * npcFriendship.getFriendshipLevel()
                    + " " + crafting.getName());
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(crafting,3 * npcFriendship.getFriendshipLevel());
            npcFriendship.getAvailableQuests().remove(2);
            // woods
        } else if (npcFriendship.getAvailableQuests().contains(3) &&
                App.getCurrentGame().getCurrentPlayingPlayer().getInventory().howManyInInventory(good) == 1000){
            Crafting crafting = new Crafting(CraftingType.DELUXE_SCARECROW);
            int rewardCount = 25000;
            System.out.println("Quest Finished, You received" + rewardCount * npcFriendship.getFriendshipLevel()+
                    " Golds");
            App.getCurrentGame().getCurrentPlayingPlayer().getInventory()
                    .addGood(crafting,3 * npcFriendship.getFriendshipLevel());

        }
    }


}
