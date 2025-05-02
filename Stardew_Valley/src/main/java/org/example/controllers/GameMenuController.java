package org.example.controllers;

import org.example.models.App;
import org.example.models.DBInteractor;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.builders.*;
import org.example.models.builders.concrete_builders.WholeGameBuilder;
import org.example.models.builders.concrete_builders.WholeMapBuilder;
import org.example.models.enums.GameMenuCommands;
import org.example.models.enums.TileType;
import org.example.models.enums.WeatherType;
import org.example.models.game_structure.Game;
import org.example.models.game_structure.Tile;

import org.example.models.game_structure.*;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.farmings.FarmingCropType;
import org.example.models.goods.farmings.FarmingTree;
import org.example.models.goods.farmings.FarmingTreeSapling;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foragings.*;
import org.example.models.goods.recipes.*;
import org.example.models.goods.recipes.CraftingFunctions;
import org.example.models.goods.recipes.CraftingRecipe;
import org.example.models.interactions.Animals.Animal;
import org.example.models.interactions.Animals.AnimalProduct;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.NPCs.NPCFriendship;
import org.example.models.goods.tools.Tool;
import org.example.models.goods.tools.ToolFunctions;
import org.example.models.goods.tools.ToolType;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.Player;
import org.example.models.interactions.PlayerBuildings.FarmBuilding;
import org.example.models.interactions.User;
import org.example.models.interactions.game_buildings.Blacksmith;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.util.regex.Matcher;

public class GameMenuController extends Controller {

    private ArrayList<Farm> farmsGame(ArrayList<Player> players, Scanner scanner) {
        ArrayList<Farm> farms = new ArrayList<>();
        String input = "";
        Matcher matcher;
        for (Player player : players) {
            System.out.println("Please enter the number of farm you want to play:");
            System.out.println("1. Normal Farm");
            System.out.println("2. Aquatic Farm");
            while(true) {
                input = scanner.nextLine();
                if((matcher = GameMenuCommands.GAME_MAP.matcher(input)) == null ||
                        !matcher.group("map_number").matches("\\d") ||
                        Integer.parseInt(matcher.group("map_number")) > 2 ||
                        Integer.parseInt(matcher.group("map_number")) < 1)
                    System.out.println("Invalid command for choosing your map, Please try again!");

                int mapNumber = Integer.parseInt(matcher.group("map_number"));
                Farm farm = DBInteractor.getFarmFromDB(mapNumber);
                player.setFarm(farm);
                farms.add(farm);
            }
        }
        return farms;
    }

    private GoodType findCraft(String craftName) {
        for (FarmingCropType value : FarmingCropType.values()) {
            if(value.getName().equals(craftName)) {
                return value;
            }
        }

        for (ForagingMineral.ForagingCropType value : ForagingMineral.ForagingCropType.values()) {
            if(value.getName().equals(craftName)) {
                return value;
            }
        }
        return null;
    }

    //TODO: Nader
    //game setting methods
    public Result newGame(ArrayList<String> usernames, Scanner scanner) {
        //TODO

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(App.getCurrentUser()));
        for (String username : usernames) {
            if(username == null)
                continue;

            for (User user : App.getUsers()) {
                if(username.equals(user.getUsername()) && user.getPlaying().equals(true))
                    return new Result(false, username + " is already playing in other game!");
                players.add(new Player(user));
            }
        }

        Director director = new Director();
        WholeGameBuilder wholeGameBuilder = new WholeGameBuilder();
        director.createNewGame(wholeGameBuilder, players);
        Game game = wholeGameBuilder.getGame();

        ArrayList<Farm> farms = farmsGame(players, scanner);
        WholeMapBuilder wholeMapBuilder = new WholeMapBuilder();
        director.createNewMap(wholeMapBuilder, farms);
        game.setCurrentMap(wholeMapBuilder.getMap());

        DBInteractor.saveGameToDB(game);
        this.loadGame();
        return new Result(true, "New game has successfully created & loaded!");
    }

    public Result loadGame() {
        Game game = DBInteractor.loadGameToDB(App.getCurrentUser().getUsername());
        App.setCurrentGame(game);

        for (Player player : game.getPlayers()) {
            if(player.getUser().getUsername().equals(App.getCurrentUser().getUsername()))
                App.getCurrentGame().setGameAdmin(player);
        }

        return new Result(true, "Your game has successfully loaded!");
    }

    public Result exitGame() {
        if (App.getCurrentGame().getGameAdmin() != App.getCurrentGame().getCurrentPlayer()) {
            return new Result(false, "Just game admin can exit the game!");
        }
        else {
            DBInteractor.saveGameToDB(App.getCurrentGame());
            App.setCurrentGame(null);
            return new Result(true, "You have successfully exited the game!");
        }
    }

    public Result forceTerminate(Scanner scanner) {
        ArrayList<Boolean> poll = new ArrayList<>();
        poll.add(true);
        for (int i = 1; i < App.getCurrentGame().getPlayers().size(); i++) {
            System.out.println(App.getCurrentGame().getPlayers().get(i).getUser().getUsername() +
                    ", Please give your vote to terminate the game: (y/n)");

            String input = scanner.nextLine();
            if(input.matches("\\s*y\\s*"))
                poll.add(true);
            else
                poll.add(false);
        }

        for (Boolean p : poll) {
            if(!p)
                return new Result(false, "All players do not agree to terminate the game!");
        }

        DBInteractor.removeGameFromDB(App.getCurrentGame());
        App.setCurrentGame(null);
        return new Result(true, "Game terminated successfully!");

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
        return new Result(true, App.getCurrentGame()
                .getDateTime().getDayOfWeek());
    }

    public Result showSeason() {
        return new Result(true, App.getCurrentGame().getDateTime()
                .getSeasonOfYear().getName());
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
        int xInt = Integer.parseInt(x);
        int yInt = Integer.parseInt(y);
        App.getCurrentGame().getWeather().Thunder(xInt , yInt, /*TODO*/);
        return new Result(true, "");
    }

    public Result weather() {
        return new Result(true, App.getCurrentGame().getWeatherName());
    }

    public Result weatherForecast() {
        return new Result(true, App.getCurrentGame().getTomorrow().weatherForecast().getName());
    }

    public Result cheatWeatherSet(String weather) {
        switch(weather){
            case "Sunny":
                App.getCurrentGame().cheatSetWeather(WeatherType.Sunny.getWeather());
                break;
            case "Rain":
                App.getCurrentGame().cheatSetWeather(WeatherType.Rain.getWeather());
                break;
            case "Storm":
                App.getCurrentGame().cheatSetWeather(WeatherType.Storm.getWeather());
                break;
            case "Snow":
                App.getCurrentGame().cheatSetWeather(WeatherType.Snow.getWeather());
                break;
        }
        return new Result(true, "");
    }

    public Result greenHouseBuild() {
        Game game = App.getCurrentGame();
        if(game.getCurrentPlayer().getWallet().getBalance() < 1000)
            return new Result(false, "You have not enough money to build green house!");


        boolean flag = false;
        for (ArrayList<Good> goods : game.getCurrentPlayer().getInventory().getList()) {
            if(!goods.isEmpty() && goods.getFirst().getName().equals("Wood") && Inventory.decreaseGoods(goods, 500)) {
                flag = true;
                break;
            }
        }
        if(!flag)
            return new Result(false, "You have not enough Wood to build green house!");
        game.getCurrentPlayer().getWallet().decreaseBalance(1000);

        game.getCurrentPlayer().getFarm().getGreenHouse().setAvailable(true);
        return new Result(true, "Your green house has been successfully built!");
    }


    //TODO: Parsa
    //Map methods
    public Result walk(String x, String y) {
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

        return new Result(true, (App.getCurrentGame().
                getCurrentPlayer().getEnergy()).getDayEnergyLeft() + "");
    }

    public Result cheatEnergySet(String value) {
        //TODO
        int valueInt = Integer.parseInt(value);
        App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(valueInt);
        return new Result(true, "");
    }

    public Result cheatEnergyUnlimited() {
        App.getCurrentGame().getCurrentPlayer().getEnergy().setMaxDayEnergy(Integer.MAX_VALUE);
        App.getCurrentGame().getCurrentPlayer().getEnergy().setMaxTurnEnergy(Integer.MAX_VALUE);
        return new Result(true, "");
    }

    public Result inventoryTrashItem(String itemName, String number) {
        //TODO
        int numberInt = Integer.parseInt(number);
        return new Result(true, "");
    }

    public Result inventoryShow() {
        StringBuilder inventoryList = new StringBuilder();
        for (ArrayList<Good> good : App.getCurrentGame().getCurrentPlayer().getInventory().getList()){
            if(!good.isEmpty()){
                inventoryList.append(good.getFirst().getName()).append(" ").append(good.size()).append("\n");
            }
        }
        return new Result(true, inventoryList.toString());
    }

    //TODO: Arani
    // Tools
    public Result toolsEquipment(String toolName) {
        boolean flag = false;
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if(!goods.isEmpty() && goods.getFirst().getName().equals(toolName) && goods.size() == 1) {
                App.getCurrentGame().getCurrentPlayer().setInHandGood((Tool) goods.getFirst());
                flag = true;
                break;
            }
        }
        if(!flag)
            return new Result(false, "You don't have " + toolName + "to use!");

        return new Result(true, "Now you can use " + toolName);
    }

    public Result toolsShowCurrent() {
        if(App.getCurrentGame().getCurrentPlayer().getInHandGood() instanceof Tool) {
            return new Result(true, "Your current tool: " + App.getCurrentGame().getCurrentPlayer().getInHandGood().getName());
        }
        else {
            return new Result(true, "You don't have tool in your hand!");
        }
    }

    public Result toolsShowAvailable() {
        StringBuilder toolsList = new StringBuilder();
        toolsList.append("List of available tools:\n");
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if(!goods.isEmpty() && goods.getFirst() instanceof Tool) {
                toolsList.append("\t").append(goods.getFirst().getName()).append("\n");
            }
        }

        return new Result(true, toolsList.toString());
    }

    public Result toolsUpgrade(String toolName) {
        Game game = App.getCurrentGame();
        if(!game.getMap().getBlackSmith().isInsideBuilding(game.getCurrentPlayer().getCordinate()))
            return new Result(false, "You are not inside the BlackSmith Shop!");
        if(game.getCurrentPlayer().getInHandGood() instanceof Tool) {
            Blacksmith blacksmith = (Blacksmith) game.getMap().getBlackSmith();
            if(((ToolType) game.getCurrentPlayer().getInHandGood().getType()).getLevel().getLevelNumber() == 4)
                return new Result(true, "Your tool is already in the highest level!");

            if(blacksmith.upgradeTool((Tool) game.getCurrentPlayer().getInHandGood())) {
                return new Result(false, "Your tool has successfully upgraded!");
            }
            else
                return new Result(false, "You don't have enough money & ingredients to upgrade"
                        + game.getCurrentPlayer().getInHandGood().getName() + "!");
        }
        else
            return new Result(false, "You don't have tool in your hand!");
    }

    public Result toolsUse(String direction) {
        if(App.getCurrentGame().getCurrentPlayer().getInHandGood() instanceof Tool) {
            Tool tool = (Tool) App.getCurrentGame().getCurrentPlayer().getInHandGood();
            Cordinate cordinate = Cordinate.getDirection(direction);
            if(cordinate == null)
                return new Result(false, "Direction not recognized");

            if(App.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft() < tool.getType().getEnergy())
                return new Result(false, "You don't have enough energy to use " + tool.getName() + "!");
            if(App.getCurrentGame().getMap().findTile(cordinate) == null)
                return new Result(false, "Tile not found");

            App.getCurrentGame().getCurrentPlayer().getEnergy().decreaseDayEnergyLeft(tool.getType().getEnergy());

            return ToolFunctions.tooluse(tool, cordinate);
        }
        else
            return new Result(false, "You don't have tool in your hand!");
    }

    //TODO: Arani
    // Craft Info
    public Result craftInfo(String craftName) {
        GoodType craft = findCraft(craftName);
        if(craft == null)
            return new Result(false, "Craft not found");

        return new Result(true, "Craft Info:\n" + craft.toString());
    }


    //TODO: Arani
    // Planting
    public Result plantSeed(String seed, String direction) {
        Cordinate cordinate = Cordinate.getDirection(direction);
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate);
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm!");

        if(tile.getTileType() != TileType.PLOWED_FARM)
            return new Result(false, "Selected Tile is not plowed for planting!");

        ArrayList<Good> seeds = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(seed);
        if(seeds == null)
            return new Result(false, "You don't have " + seed + " in your inventory!");
        for(Good good : tile.getGoods()) {
            if(good instanceof FarmingTreeSapling || good instanceof FarmingTree ||
            good instanceof ForagingSeed || good instanceof ForagingTree || good instanceof ForagingMineral)
                return new Result(true, "A seed already planted in this tile!");
        }

        tile.getGoods().add(seeds.getLast());
        seeds.removeLast();

        return new Result(true, "A plant seed has been planted on tile " + cordinate + "!");
    }

    public Result showPlant(String direction) {
        //TODO
        Cordinate cordinate = Cordinate.getDirection(direction);
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(cordinate);
        if(tile == null)
            return new Result(false, "Selected Tile should be in your farm!");

        Good plant = null;
        for (Good good : tile.getGoods()) {
            if(good instanceof FarmingTreeSapling || good instanceof FarmingTree ||
                    good instanceof ForagingSeed || good instanceof ForagingTree) {
                plant = good;
                break;
            }
        }
        if(plant == null)
            return new Result(false, "There is no plant in this location!");

        return new Result(true, "Plant Info:\n" + plant.toString());
    }

    public Result fertilize(String fertilizer, String direction) {
        //TODO
        return new Result(true, "");
    }

    public Result howMuchWater() {
        Tool tool = (Tool) App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ToolType.WATERING_CAN.getName()).getFirst();
        return new Result(true, "Your watering can have capacity:" + tool.capacity);
    }


    //TODO: Nader
    // crafting methods
    public Result showCraftingRecipes() {
        for (CraftingRecipe craftingRecipe : App.getCurrentGame().getCurrentPlayer().getCraftingRecipes()) {
            System.out.println(craftingRecipe.getName());
            System.out.println(((CraftingRecipeType) craftingRecipe.getType()).getIngredients());
            System.out.println("-------------------------------------------");
        }
        return new Result(true, "");
    }

    public Result craftingCraft(String itemName) {
        for (CraftingRecipe craftingRecipe : App.getCurrentGame().getCurrentPlayer().getCraftingRecipes()) {
            if (craftingRecipe.getName().equals(itemName)) {
                CraftingFunctions craftingFunctions = new CraftingFunctions();
                craftingFunctions.checkCraftingFunctions((CraftingRecipeType) craftingRecipe.getType());
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
        for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
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

        Cordinate cordinate = Cordinate.getDirection(direction);
        switch (cordinate) {
            case null:
                return new Result(false, "Direction not recognized");
            default:
                newX = cordinate.getX();
                newY = cordinate.getY();
        }

        for (Tile tile : App.getCurrentGame().getMap().getTiles()) {
            if (tile.getCordinate().getX() == newX && tile.getCordinate().getY() == newY) {
                tile.getGoods().add(goodTemp);
                return new Result(true, "Item has been dropped!");
            }
        }
        return new Result(true, "Tile not found to drop item!");

    }


    public Result cheatAddItem(String itemName, String count) {
        //TODO
        return new Result(true, "");
    }


    //TODO: Nader
    // cooking methods
    public Result cookingRefrigerator(String status, String itemName) {
        Fridge fridge = App.getCurrentGame().getCurrentPlayer().getFridge();
        Inventory inventory = App.getCurrentGame().getCurrentPlayer().getInventory();
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

            if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(item,1)) {
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

            if (App.getCurrentGame().getCurrentPlayer().getFridge().addItemToFridge(item)) {
                return new Result(true, item.getName() + " added to the fridge");
            }
            return new Result(false, "Fridge is full");

        }

        return new Result(false, "Invalid operation");
    }

    public Result showCookingRecipes() {
        for (CookingRecipe cookingRecipe : App.getCurrentGame().getCurrentPlayer().getCookingRecipes()) {
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

        for (CookingRecipe cookingRecipe : App.getCurrentGame().getCurrentPlayer().getCookingRecipes()) {
            if (cookingRecipe.getName().equals(recipeName)) {
                recipe = cookingRecipe;
                found = true;
                break;
            }
        }
        if (!found) {
            return new Result(false, "You don't have this cooking recipe");
        }

        for (Pair<FoodType, Integer> ingredient : recipe.getType().getIngredients()) {

            FoodType ingredientType = ingredient.getFirst();
            int requiredAmount = ingredient.getSecond();
            if (!checkCanCook(ingredientType, requiredAmount)) {
                return new Result(false, "Not enough " + ingredientType.getName() +
                        " (needed: " + requiredAmount + ")");
            }
        }

        Food food = new Food((FoodType) ((CookingRecipeType) recipe.getType()).getGoodType());

        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
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
        for (ArrayList<Good> good : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (good.getFirst().getName().equalsIgnoreCase(foodType.getName())) {
                return true;
            }
        }
        for (ArrayList<Food> foods : App.getCurrentGame().getCurrentPlayer().getFridge().getInFridgeItems()) {
            if (foods.getFirst().getName().equalsIgnoreCase(foodType.getName())) {
                return true;
            }
        }
        return false;

    }

    public Result eat(String foodName) {
        Good food = null;
        for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
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
        App.getCurrentGame().getCurrentPlayer().eat((Food) food);
        return new Result(true, "Khosmaz, Yum Yum!");
    }


    //TODO: Parsa
    // Animals & Fishing methods
    public Result buildBuilding(String buildingName, String x, String y) {
        //?
        return new Result(true, "");
    }

    public Result buyAnimal(String animalType, String animalName) {
        //?
        return new Result(true, "");
    }

    public Result petAnimal(String animalName) {
        //?
        return new Result(true, "");
    }

    public Result animalList() {
        for (FarmBuilding building : App.getCurrentGame().getCurrentPlayer().getFarmBuildings()) {
            for (Animal animal : building.getAnimals()){
                System.out.println(building.getName() + " " + animal.getName() + " " + animal.getFriendship()
                + " is fed:" + animal.isFed() + " " + " is petted:" + animal.isPetted());
                System.out.println("------------------------------");
            }
        }
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
        for (FarmBuilding building : App.getCurrentGame().getCurrentPlayer().getFarmBuildings()) {
            for (Animal animal : building.getAnimals()){
                System.out.print(building.getAnimals() + " " + animal.getName());
                for (AnimalProduct animalProduct : animal.getProducts()){
                    System.out.print(" " + animalProduct.getName() + " " + animalProduct.getQuality());
                }
                System.out.println();
                System.out.println("------------------------------");
            }
        }
        return new Result(true, "");
    }

    public Result collectProduct(String animalName) {
        //TODO
        return new Result(true, "");
    }

    public Result sellAnimal(String animalName) {
        //?
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
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                if (Math.abs(npc.getCordinate().getX() -
                        App.getCurrentGame().getCurrentPlayer().getCordinate().getX()) == 1 &&
                        Math.abs(npc.getCordinate().getY() -
                                App.getCurrentGame().getCurrentPlayer().getCordinate().getY()) == 1) {
                    npc.getFriendship();
                    npc.npcDialogs();
                    return new Result(true, "");
                } else {
                    return new Result(true, "Too far away. Approach the NPC to speak.");
                }
            }
        }
        return new Result(true, "");
    }

    public Result giftNPC(String npcName, String itemName) {
        Good good = null;
        boolean found = false;
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getFirst().getName().equals(itemName)) {
                good = goods.get(goods.size() - 1);
                found = true;
                goods.remove(good);
                break;
            }
        }
        if (!found) {
            return new Result(false, "You don't have this item in your inventory.");
        }
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                npc.getGift(good);
                return new Result(true, "You sent a gift to " + npcName);

            }
        }
        return new Result(false, "NPC not found.");
    }

    public Result friendshipNPCList() {

        for (NPC npc : App.getCurrentGame().getNPCs()) {
            System.out.println("------------------------------");
            System.out.println("NPC Name: " + npc.getType().getName());
            System.out.println("Friendship Level: " + npc.getFriendship().getFriendshipLevel());
            System.out.println("Friendship points: " + npc.getFriendship().getFriendshipPoints());
        }
        return new Result(true, "------------------------------");
    }

    public Result questsList() {

        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (npc.getFriendship().getAvailableQuests().contains(1)) {
                System.out.print(npc.getType().getRequests().get(0).getFirst() + " ");
                System.out.println(npc.getType().getRequests().get(0).getSecond());
            }
            if (npc.getFriendship().getAvailableQuests().contains(2)) {
                System.out.print(npc.getType().getRequests().get(1).getFirst() + " ");
                System.out.println(npc.getType().getRequests().get(1).getSecond());
            }
            if (npc.getFriendship().getAvailableQuests().contains(3)) {
                System.out.print(npc.getType().getRequests().get(2).getFirst() + " ");
                System.out.println(npc.getType().getRequests().get(2).getSecond());
            }

        }

        return new Result(true, "------------------------------");
    }

    public Result questsFinish(String index) {

        int indexInt = Integer.parseInt(index);
        NPC targetNPC = null;
        boolean found = false;
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (Math.abs(npc.getCordinate().getX() -
                    App.getCurrentGame().getCurrentPlayer().getCordinate().getX()) == 1 &&
                    Math.abs(npc.getCordinate().getY() -
                            App.getCurrentGame().getCurrentPlayer().getCordinate().getY()) == 1) {
                targetNPC = npc;
                found = true;
                break;
            }
        }
        if (!found) {
            return new Result(true, "Too far away. Approach the NPC to speak.");
        }
        targetNPC.finishQuest(indexInt);
        //  no next line
        return new Result(true, "");
    }


}
