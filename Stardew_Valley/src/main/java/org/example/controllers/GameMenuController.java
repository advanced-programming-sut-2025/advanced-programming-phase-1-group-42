package org.example.controllers;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.game_structure.Game;
import org.example.models.game_structure.Map;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.recipes.CraftingFunctions;
import org.example.models.goods.recipes.CraftingRecipe;
import org.example.models.goods.recipes.Recipe;
import org.example.models.interactions.Player;
import org.example.models.interactions.User;

import java.util.ArrayList;
import java.util.Iterator;

public class GameMenuController extends Controller {

    Game thisGame;

    public void setThisGame(Game thisGame) {
        this.thisGame = thisGame;
    }

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
    }

    public Result exitGame() {
        if (App.getCurrentGame().getGameCreator() != App.getCurrentUser()) {
            return new Result(false, "Just game creator can exit the game!");
        }
    }

    public Result nextTurn() {
        App.getCurrentGame().nextPlayer();
    }


    //TODO: Nader
    // date & time methods
    public Result time() {
        return new Result(true, App.getCurrentGame().getTime() + ":00");
    }

    public Result date() {
        return new Result(true,
                App.getCurrentGame().getYear() + "/" +
                        App.getCurrentGame().getSeasonOfYearInt() + "/" +
                        App.getCurrentGame().getDayOfSeason());
    }

    public Result dateTime() {
        return new Result(true,
                App.getCurrentGame().getYear() + "/" +
                        App.getCurrentGame().getSeasonOfYearInt() + "/" +
                        App.getCurrentGame().getDayOfSeason() + "/" +
                        App.getCurrentGame().getTime() + ":00");
    }

    public Result dayOfTheWeek() {
        return new Result(true, App.getCurrentGame().getDayOfWeek());
    }

    public Result showSeason() {
        return new Result(true, App.getCurrentGame().getSeasonOfYear());
    }

    public Result cheatAdvanceTime(String hour) {
        //TODO
    }

    public Result cheatAdvanceDate(String date) {
        //TODO
    }


    //TODO: Parsa
    //Weather methods
    public Result cheatThunder(String x, String y) {
        //TODO
    }

    public Result weather() {
        //TODO
    }

    public Result weatherForecast() {
        //TODO
    }

    public Result cheatWeatherSet(String weather) {
        //TODO
    }

    public Result greenHouseBuild() {
        //TODO
    }


    //TODO: Parsa
    //Map methods
    public Result walk(String x,        //TODO
                       String y) {
        //TODO
    }

    public Result printMap(String x, String y, String size) {
        //TODO
    }

    public Result helpReadingMap() {
        //TODO
    }

    //TODO: Parsa
    //inventory & Energy methods
    public Result energyShow() {
        //TODO
    }

    public Result cheatEnergySet(String value) {
    }

    public Result cheatEnergyUnlimited() {
        //TODO
    }

    public Result inventoryTrashItem(String itemName, String number) {
        //TODO
    }

    public Result inventoryShow() {
        //TODO
    }

    //TODO: Arani
    // Tools
    public Result toolsEquipment(String toolName) {
        //TODO
    }

    public Result toolsShowCurrent() {
        //TODO
    }

    public Result toolsShowAvailable() {
        //TODO
    }

    public Result toolsUpgrade(String toolName) {
        //TODO
    }

    public Result toolsUse(String direction) {
        //TODO
    }

    //TODO: Arani
    // Craft Info
    public Result craftInfo(String craftName) {
        //TODO
    }


    //TODO: Arani
    // Planting
    public Result plantSeed(String seed, String direction) {
        //TODO
    }

    public Result showPlant(String direction) {
        //TODO
    }

    public Result fertilize(String fertilizer, String direction) {
        //TODO
    }

    public Result howMuchWater() {
        //TODO
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
    }


    //TODO: Nader
    // cooking methods
    public Result cookingRefrigerator(String status, String itemName) {
        //TODO
    }

    public Result showCookingRecipes() {
        //TODO
    }

    public Result cookingPrepare(String recipeName) {
        //TODO
    }

    public Result eat(String foodName) {
        //TODO
    }


    //TODO: Parsa
    // Animals & Fishing methods
    public Result buildBuilding(String buildingName, String x, String y) {
        //TODO
    }

    public Result buyAnimal(String animalType, String animalName) {
        //TODO
    }

    public Result petAnimal(String animalName) {
        //TODO
    }

    public Result animalList() {
        //TODO
    }

    public Result cheatSetAnimalFriendship(String animalName, String amount) {
        //TODO
    }

    public Result shepherdAnimal(String animalName, String x, String y) {
        //TODO
    }

    public Result feedHay(String animalName) {
        //TODO
    }

    public Result animalProductionList() {
        //TODO
    }

    public Result collectProduct(String animalName) {
        //TODO
    }

    public Result sellAnimal(String animalName) {
        //TODO
    }

    public Result fishing(String fishingPole) {
        //TODO
    }


    //TODO: Nader
    // artisan methods
    public Result artisanUse(String artisanName) {
        //TODO
    }

    public Result artisanGet(String artisanName) {
        //TODO
    }

    //TODO: Nader
    // buy & sell methods
    public Result showAllProducts() {
        //TODO
    }

    public Result showAllAvailableProducts() {
        //TODO
    }

    public Result purchase(String productName, String count) {
        //TODO
    }

    public Result cheatAddDollars(String count) {
        //TODO
    }

    public Result sell(String productName, String count) {
        //TODO
    }

    //TODO: Arani
    // Friendships methods
    public Result friendships() {
        //TODO
    }

    public Result talk(String username, String message) {
        //TODO
    }

    public Result talkHistory(String username) {
        //TODO
    }

    public Result gift(String username, String item, String amount) {
        //TODO
    }

    public Result giftList() {
        //TODO
    }

    public Result giftRate(String giftNumber, String rate) {
        //TODO
    }

    public Result giftHistory(String username) {
        //TODO
    }

    public Result hug(String username) {
        //TODO
    }

    public Result flower(String username) {
        //TODO
    }

    public Result askMarriage(String username, String ring) {
        //TODO
    }

    public Result respond(String status, String username) {
        //TODO
    }


    //TODO: Parsa
    // Trading methods
    public Result startTrade() {
        //TODO
    }

    public Result tradeWithMoney(String receiver, String tradeType, String Item,
                                 String amount, String Price) {
        //TODO
    }

    public Result tradeWithGoods(String receiver, String tradeType, String Item,
                                 String amount, String targetItem, String targetAmount) {
        //TODO
    }

    public Result tradeList() {
        //TODO
    }

    public Result tradeResponse(String response, String tradeID) {
        //TODO
    }

    public Result tradeHistory() {
        //TODO
    }

    //TODO: Nader
    // NPC methods
    public Result meetNPC(String npcName) {
        //TODO
    }

    public Result giftNPC(String npcName, String itemName) {
        //TODO
    }

    public Result friendshipNPCList() {
        //TODO
    }

    public Result questsList() {
        //TODO
    }

    public Result questsFinish(String index) {
        //TODO
    }


}
