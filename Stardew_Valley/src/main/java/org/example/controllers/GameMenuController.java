package org.example.controllers;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.game_structure.*;
import org.example.models.game_structure.Map;
import org.example.models.goods.Good;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.recipes.*;
import org.example.models.interactions.Player;
import org.example.models.interactions.User;

import java.util.*;

public class GameMenuController extends Controller {

    //TODO: Nader
    //game setting methods
    public Result newGame(String username_1, String username_2, String username_3) {
        //TODO
        if (username_1 == null || username_2 == null || username_3 == null) {
            return new Result(false, "You need to add 3 players to start the game");
        }
        Player mainPlayer = new Player(App.getCurrentUser());
        Player player1 = null;
        Player player2 = null;
        Player player3 = null;
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username_1)) {
                if (user.getPlaying().equals(true)) {
                    return new Result(false, "This user is playing in another game");
                } else {
                    player1 = new Player(user);
                }
            } else if (user.getUsername().equals(username_2)) {
                if (user.getPlaying().equals(true)) {
                    return new Result(false, "This user is playing in another game");
                } else {
                    player2 = new Player(user);
                }
            } else if (user.getUsername().equals(username_3)) {
                if (user.getPlaying().equals(true)) {
                    return new Result(false, "This user is playing in another game");
                } else {
                    player3 = new Player(user);
                }
            }
        }

        if (player1 == null || player2 == null || player3 == null) {
            return new Result(false, "Added users are unavailable");
        }

        Game game = new Game(App.getCurrentUser());
        App.setCurrentGame(game);


        return new Result(true, "Users have been added to the game successfully!");
    }

    public Result mapGame(String mapNumber) {
        //TODO
        int mapNumberInt = Integer.parseInt(mapNumber.trim());
        for (Map map : App.maps) {
            if (map.getMapNumber() == mapNumberInt) {
                App.getCurrentGame().setCurrentMap(map);
                return new Result(true, "Map number " + mapNumber + " has been selected");
            }
        }
        return new Result(false, "Map number is unavailable");
    }

    public Result loadGame() {
        //TODO
        return new Result(true, "");
    }

    public Result exitGame() {
        if (App.getCurrentGame().getGameCreator() != App.getCurrentUser()) {
            return new Result(false, "Just game creator can exit the game!");
        }
        return new Result(true, "");
    }

    public Result nextTurn() {
        App.getCurrentGame().nextPlayer();
        return new Result(true, "");
    }


    //TODO: Nader
    // date & time methods
    public Result time() {
        return new Result(true, App.getCurrentGame().getDateTime().getTime() + ":00");
    }

    public Result date() {
        return new Result(true,
                App.getCurrentGame().getDateTime().getYear() + "/" +
                        App.getCurrentGame().getDateTime().getSeasonOfYearInt() + "/" +
                        App.getCurrentGame().getDateTime().getDayOfSeason());
    }

    public Result dateTime() {
        return new Result(true,
                App.getCurrentGame().getDateTime().getYear() + "/" +
                        App.getCurrentGame().getDateTime().getSeasonOfYearInt() + "/" +
                        App.getCurrentGame().getDateTime().getDayOfSeason() + "/" +
                        App.getCurrentGame().getDateTime().getTime() + ":00");
    }

    public Result dayOfTheWeek() {
        return new Result(true, App.getCurrentGame().getDateTime().getDayOfWeek());
    }

    public Result showSeason() {
        return new Result(true, App.getCurrentGame().getDateTime().getSeasonOfYear());
    }

    public Result cheatAdvanceTime(String hour) {
        //TODO
        return new Result(true, "");
    }

    public Result cheatAdvanceDate(String date) {
        //TODO
        return new Result(true, "");
    }


    //TODO: Parsa
    //Weather methods
    public Result cheatThunder(String x, String y) {
        //TODO
        return new Result(true, "");
    }

    public Result weather() {
        //TODO
        return new Result(true, "");
    }

    public Result weatherForecast() {
        //TODO
        return new Result(true, "");
    }

    public Result cheatWeatherSet(String weather) {
        //TODO
        return new Result(true, "");
    }

    public Result greenHouseBuild() {
        //TODO
        return new Result(true, "");
    }


    //TODO: Parsa
    //Map methods
    public Result walk(String x,        //TODO
                       String y) {
        //TODO
        return new Result(true, "");
    }

    public Result printMap(String x, String y, String size) {
        //TODO
        return new Result(true, "");
    }

    public Result helpReadingMap() {
        //TODO
        return new Result(true, "");
    }

    //TODO: Parsa
    //inventory & Energy methods
    public Result energyShow() {
        //TODO
        return new Result(true, "");
    }

    public Result cheatEnergySet(String value) {
        //TODO
        return new Result(true, "");
    }

    public Result cheatEnergyUnlimited() {
        //TODO
        return new Result(true, "");
    }

    public Result inventoryTrashItem(String itemName, String number) {
        //TODO
        return new Result(true, "");
    }

    public Result inventoryShow() {
        //TODO
        return new Result(true, "");
    }

    //TODO: Arani
    // Tools
    public Result toolsEquipment(String toolName) {

        return new Result(true, "");
    }

    public Result toolsShowCurrent() {
        //TODO
        return new Result(true, "");
    }

    public Result toolsShowAvailable() {
        //TODO
        return new Result(true, "");
    }

    public Result toolsUpgrade(String toolName) {
        //TODO
        return new Result(true, "");
    }

    public Result toolsUse(String direction) {
        //TODO
        return new Result(true, "");
    }

    //TODO: Arani
    // Craft Info
    public Result craftInfo(String craftName) {
        //TODO
        return new Result(true, "");
    }


    //TODO: Arani
    // Planting
    public Result plantSeed(String seed, String direction) {
        //TODO
        return new Result(true, "");
    }

    public Result showPlant(String direction) {
        //TODO
        return new Result(true, "");
    }

    public Result fertilize(String fertilizer, String direction) {
        //TODO
        return new Result(true, "");
    }

    public Result howMuchWater() {
        //TODO
        return new Result(true, "");
    }


    //TODO: Nader
    // crafting methods
    public Result showCraftingRecipes() {
        for (CraftingRecipe craftingRecipe : App.getCurrentGame().getCurrentPlayingPlayer().getCraftingRecipes()) {
            System.out.println(craftingRecipe.getName());
            System.out.println(craftingRecipe.getType().getIngredients());
            System.out.println("-------------------------------------------");
        }
        return new Result(true, "");
    }

    public Result craftingCraft(String itemName) {
        for (CraftingRecipe craftingRecipe : App.getCurrentGame().getCurrentPlayingPlayer().getCraftingRecipes()) {
            if (craftingRecipe.getName().equals(itemName)) {
                CraftingFunctions craftingFunctions = new CraftingFunctions();
                craftingFunctions.checkCraftingFunctions(craftingRecipe.getType());
                return new Result(true, "");
            }
        }
        return new Result(false, "");
    }

    public Result placeItem(String itemName, String direction) {

        Good goodTemp = null;
        boolean found = false;
        int newX = 0;
        int newY = 0;
        for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayingPlayer().getInventory().getList()) {
            Iterator<Good> iterator = goodArrayList.iterator();
            while (iterator.hasNext()) {
                Good good = iterator.next();
                if (good.getName().equals(itemName)) {
                    goodTemp = good;
                    iterator.remove();
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        if (!found) {
            return new Result(false, "Item " + itemName + " not found");
        }

        switch (direction) {
            case "up":
                newY = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getY() + 1;
                break;
            case "down":
                newY = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getY() - 1;
                break;
            case "left":
                newX = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getX() - 1;
                break;
            case "right":
                newX = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getX() + 1;
                break;
            case "up-right":
                newX = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getX() + 1;
                newY = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getY() + 1;
                break;
            case "up-left":
                newX = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getX() - 1;
                newY = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getY() + 1;
                break;
            case "down-right":
                newX = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getX() + 1;
                newY = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getY() - 1;
                break;
            case "down-left":
                newX = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getX() - 1;
                newY = App.getCurrentGame().getCurrentPlayingPlayer().getCordinate().getY() - 1;
                break;
            default:
                return new Result(false, "Direction not recognized");
        }

        for (Tile tile : App.getCurrentGame().getMap().getTiles()) {
            if (tile.getCordinate().getX() == newX && tile.getCordinate().getY() == newY) {
                tile.getGoods().add(goodTemp);
                return new Result(true, "Item has been dropped!");
            }
        }
        return new Result(true, "");

    }


    public Result cheatAddItem(String itemName, String count) {
        //TODO
        return new Result(true, "");
    }


    //TODO: Nader
    // cooking methods
    public Result cookingRefrigerator(String status, String itemName) {
        Fridge fridge = App.getCurrentGame().getCurrentPlayingPlayer().getFridge();
        Inventory inventory = App.getCurrentGame().getCurrentPlayingPlayer().getInventory();
        Food item = null;
        boolean found = false;

        if (status.equals("pick")) {

            for (ArrayList<Food> fridgeList : fridge.getInFridgeItems()) {
                Iterator<Food> iterator = fridgeList.iterator();
                while (iterator.hasNext()) {
                    Food food = iterator.next();
                    if (food.getName().equalsIgnoreCase(itemName)) {
                        item = food;
                        iterator.remove();
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }

            if (!found) {
                return new Result(false, "Item is not available in the fridge");
            }

            if (App.getCurrentGame().getCurrentPlayingPlayer().getInventory().addGood(item)) {
                return new Result(true, item.getName() + " added to the inventory");
            }
            return new Result(false, "Inventory is full");

        } else if (status.equals("put")) {
            for (ArrayList<Good> inventoryList : inventory.getList()) {
                Iterator<Good> iterator = inventoryList.iterator();
                while (iterator.hasNext()) {
                    Good good = iterator.next();
                    if (good instanceof Food && good.getName().equalsIgnoreCase(itemName)) {
                        item = (Food) good;
                        iterator.remove();
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }

            if (!found) {
                return new Result(false, "Item is not available in the Inventory");
            }

            if (App.getCurrentGame().getCurrentPlayingPlayer().getFridge().addItemToFridge(item)) {
                return new Result(true, item.getName() + " added to the fridge");
            }
            return new Result(false, "Fridge is full");

        }

        return new Result(false, "Invalid operation");
    }

    public Result showCookingRecipes() {
        for (CookingRecipe cookingRecipe : App.getCurrentGame().getCurrentPlayingPlayer().getCookingRecipes()) {
            System.out.println(cookingRecipe.getName());
        }
        return new Result(true, "");
    }

    public Result cookingPrepare(String recipeName) {
        CookingRecipe recipe = null;
        boolean found = false;
        boolean valid = false;

        for (CookingRecipeType type : CookingRecipeType.values()) {
            if (type.name().equalsIgnoreCase(recipeName)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            return new Result(false, "This recipe is invalid");
        }

        for (CookingRecipe cookingRecipe : App.getCurrentGame().getCurrentPlayingPlayer().getCookingRecipes()) {
            if (cookingRecipe.getName().equals(recipeName)) {
                recipe = cookingRecipe;
                found = true;
                break;
            }
        }
        if (!found) {
            return new Result(false, "You don't have this cooking recipe");
        }

        for (HashMap<FoodType, Integer> ingredient : recipe.getType().getIngredients()) {

            FoodType ingredientType = ingredient.keySet().iterator().next();
            int requiredAmount = ingredient.get(ingredientType);
            if (!checkCanCook(ingredientType, requiredAmount)) {
                return new Result(false, "Not enough " + ingredientType.getName() +
                        " (needed: " + requiredAmount + ")");
            }
        }

        Food food = new Food(recipe.getType().getFoodType());

        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayingPlayer().getInventory().getList()) {
            if (goods.getFirst().getName().equalsIgnoreCase(recipeName)) {
                goods.add(food);
                return new Result(true, "You put " + food.getName() + " into the inventory");
            } else if (goods.isEmpty()) {
                goods.add(food);
                return new Result(true, "You put " + food.getName() + " into the inventory");
            }
        }

        return new Result(false, "Your inventory is full");


    }

    public boolean checkCanCook(FoodType foodType, int requiredAmount) {
        for (ArrayList<Good> good : App.getCurrentGame().getCurrentPlayingPlayer().getInventory().getList()) {
            if (good.getFirst().getName().equalsIgnoreCase(foodType.getName())) {
                return true;
            }
        }
        for (ArrayList<Food> foods : App.getCurrentGame().getCurrentPlayingPlayer().getFridge().getInFridgeItems()) {
            if (foods.getFirst().getName().equalsIgnoreCase(foodType.getName())) {
                return true;
            }
        }
        return false;

    }

    public Result eat(String foodName) {
        Good food = null;
        for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayingPlayer().getInventory().getList()) {
            Iterator<Good> iterator = goodArrayList.iterator();
            while (iterator.hasNext()) {
                food = iterator.next();
                if (food.getName().equals(foodName)) {
                    if (food instanceof Food) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        if (food == null) {
            return new Result(false, "This item is not eatable!");
        }
        App.getCurrentGame().getCurrentPlayingPlayer().eat((Food) food);
        return new Result(true, "Khosmaz, Yum Yum!");
    }


    //TODO: Parsa
    // Animals & Fishing methods
    public Result buildBuilding(String buildingName, String x, String y) {
        //TODO
        return new Result(true, "");
    }

    public Result buyAnimal(String animalType, String animalName) {
        //TODO
        return new Result(true, "");
    }

    public Result petAnimal(String animalName) {
        //TODO
        return new Result(true, "");
    }

    public Result animalList() {
        //TODO
        return new Result(true, "");
    }

    public Result cheatSetAnimalFriendship(String animalName, String amount) {
        //TODO
        return new Result(true, "");
    }

    public Result shepherdAnimal(String animalName, String x, String y) {
        //TODO
        return new Result(true, "");
    }

    public Result feedHay(String animalName) {
        //TODO
        return new Result(true, "");
    }

    public Result animalProductionList() {
        //TODO
        return new Result(true, "");
    }

    public Result collectProduct(String animalName) {
        //TODO
        return new Result(true, "");
    }

    public Result sellAnimal(String animalName) {
        //TODO
        return new Result(true, "");
    }

    public Result fishing(String fishingPole) {
        //TODO
        return new Result(true, "");
    }


    //TODO: Nader
    // artisan methods
    public Result artisanUse(String artisanName) {
        //TODO
        return new Result(true, "");
    }

    public Result artisanGet(String artisanName) {
        //TODO
        return new Result(true, "");
    }

    //TODO: Nader
    // buy & sell methods
    public Result showAllProducts() {
        //TODO
        return new Result(true, "");
    }

    public Result showAllAvailableProducts() {
        //TODO
        return new Result(true, "");
    }

    public Result purchase(String productName, String count) {
        //TODO
        return new Result(true, "");
    }

    public Result cheatAddDollars(String count) {
        //TODO
        return new Result(true, "");
    }

    public Result sell(String productName, String count) {
        //TODO
        return new Result(true, "");
    }

    //TODO: Arani
    // Friendships methods
    public Result friendships() {
        //TODO
        return new Result(true, "");
    }

    public Result talk(String username, String message) {
        //TODO
        return new Result(true, "");
    }

    public Result talkHistory(String username) {
        //TODO
        return new Result(true, "");
    }

    public Result gift(String username, String item, String amount) {
        //TODO
        return new Result(true, "");
    }

    public Result giftList() {
        //TODO
        return new Result(true, "");
    }

    public Result giftRate(String giftNumber, String rate) {
        //TODO
        return new Result(true, "");
    }

    public Result giftHistory(String username) {
        //TODO
        return new Result(true, "");
    }

    public Result hug(String username) {
        //TODO
        return new Result(true, "");
    }

    public Result flower(String username) {
        //TODO
        return new Result(true, "");
    }

    public Result askMarriage(String username, String ring) {
        //TODO
        return new Result(true, "");
    }

    public Result respond(String status, String username) {
        //TODO
        return new Result(true, "");
    }


    //TODO: Parsa
    // Trading methods
    public Result startTrade() {
        //TODO
        return new Result(true, "");
    }

    public Result tradeWithMoney(String receiver, String tradeType, String Item,
                                 String amount, String Price) {
        //TODO
        return new Result(true, "");
    }

    public Result tradeWithGoods(String receiver, String tradeType, String Item,
                                 String amount, String targetItem, String targetAmount) {
        //TODO
        return new Result(true, "");
    }

    public Result tradeList() {
        //TODO
        return new Result(true, "");
    }

    public Result tradeResponse(String response, String tradeID) {
        //TODO
        return new Result(true, "");
    }

    public Result tradeHistory() {
        //TODO
        return new Result(true, "");
    }

    //TODO: Nader
    // NPC methods
    public Result meetNPC(String npcName) {
        //TODO
        return new Result(true, "");
    }

    public Result giftNPC(String npcName, String itemName) {
        //TODO
        return new Result(true, "");
    }

    public Result friendshipNPCList() {
        //TODO
        return new Result(true, "");
    }

    public Result questsList() {
        //TODO
        return new Result(true, "");
    }

    public Result questsFinish(String index) {
        //TODO
        return new Result(true, "");
    }


}
