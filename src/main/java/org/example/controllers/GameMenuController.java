package org.example.controllers;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.builders.*;
import org.example.models.builders.concrete_builders.WholeGameBuilder;
import org.example.models.builders.concrete_builders.WholeMapBuilder;
import org.example.models.enums.*;
import org.example.models.game_structure.Game;
import org.example.models.game_structure.Tile;

import org.example.models.game_structure.*;
import org.example.models.game_structure.weathers.Weather;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.artisans.Artisan;
import org.example.models.goods.artisans.ArtisanFunctions;
import org.example.models.goods.farmings.FarmingCrop;
import org.example.models.goods.farmings.FarmingCropType;
import org.example.models.goods.farmings.FarmingTree;
import org.example.models.goods.farmings.FarmingTreeSapling;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foragings.*;
import org.example.models.goods.products.ProductType;
import org.example.models.goods.recipes.*;
import org.example.models.goods.recipes.CraftingFunctions;
import org.example.models.goods.recipes.CraftingRecipe;
import org.example.models.interactions.Animals.Animal;
import org.example.models.interactions.Animals.AnimalProduct;
import org.example.models.interactions.Animals.AnimalProductsType;
import org.example.models.interactions.Animals.AnimalTypes;
import org.example.models.interactions.Gender;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.goods.tools.Tool;
import org.example.models.goods.tools.ToolFunctions;
import org.example.models.goods.tools.ToolType;
import org.example.models.interactions.Player;
import org.example.models.interactions.PlayerBuildings.FarmBuilding;
import org.example.models.interactions.PlayerBuildings.FarmBuildingTypes;
import org.example.models.interactions.User;
import org.example.models.interactions.game_buildings.Blacksmith;
import org.example.models.interactions.game_buildings.CarpenterShop;
import org.example.models.interactions.game_buildings.GameBuilding;
import org.example.models.interactions.game_buildings.MarnieRanch;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.util.regex.Matcher;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class GameMenuController extends Controller {

    private ArrayList<Farm> farmGame(ArrayList<Player> players, Scanner scanner, ArrayList<Tile> tiles) {
        ArrayList<Farm> farms = new ArrayList<>();
        String input = "";
        Matcher matcher;
        int ptr = 0;


        for (Player player : players) {
            System.out.println(player.getUser().getUsername());
            System.out.println("Please enter the number of farm you want to play:");
            System.out.println("1. Normal Farm");
            System.out.println("2. Aquatic Farm");
            while (true) {
                input = scanner.nextLine();
                if ((matcher = GameMenuCommands.GAME_MAP.matcher(input)) == null ||
                        !matcher.group("farmNumber").matches("\\s*\\d\\s*") ||
                        Integer.parseInt(matcher.group("farmNumber")) > 2 ||
                        Integer.parseInt(matcher.group("farmNumber")) < 1) {
                    System.out.println("Invalid command for choosing your map, Please try again!");
                    continue;
                }

                int mapNumber = Integer.parseInt(matcher.group("farmNumber"));
                Farm farm = new Farm(mapNumber - 1, ptr, tiles);
                player.setFarm(farm);
                farms.add(farm);
                break;
            }
            ptr++;
            if (ptr == 4) {
                break;
            }
        }
        return farms;
    }

    private GoodType findCraft(String craftName) {
        craftName = craftName.trim();

        for (FarmingCropType value : FarmingCropType.values()) {
            if (value.getName().equals(craftName)) {
                return value;
            }
        }

        for (ForagingCropType value : ForagingCropType.values()) {
            if (value.getName().equals(craftName)) {
                return value;
            }
        }
        return null;
    }

    private ArrayList<Tile> createTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 160; j++) {
                Tile tile = new Tile(new Coordinate(i, j));
                //Plain
                tile.setTileType(TileType.PLAIN);
                if (j >= 80 && j < 110 && i >= 110 && i < 140)
                    tile.setTileType(TileType.BEACH);

                //Sea
                if (i >= 140 && i < 150 && j >= 0 && j < 160)
                    tile.setTileType(TileType.WATER);

                // Road
                if ((j >= 50 && j < 110 && (i >= 30 && i < 35 || i >= 105 && i < 110)) ||
                        (j >= 77 && j < 83 && i >= 0 && i < 110))
                    tile.setTileType(TileType.ROAD);

                //Square
                if (j >= 77 && j < 83 && ((i >= 30 && i < 35) || (i >= 105 && i < 110)))
                    tile.setTileType(TileType.SQUARE);

                tiles.add(tile);
            }
        }


        return tiles;
    }

    // Nader
    //game setting methods
    public Result newGame(ArrayList<String> usernames, Scanner scanner) {
        if (usernames.isEmpty() || usernames.size() > 3)
            return new Result(false, "You have to add 1 to 3 Users to play with you in new game!");

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(App.getCurrentUser()));
        Player adminPlayer = players.getFirst();
//        System.out.println(usernames.size());
        for (String username : usernames) {
            if (username.isEmpty())
                continue;

            boolean found = false;
            for (User user : App.getUsers()) {
                if (username.equals(user.getUsername())) {
                    if (user.getPlaying().equals(true))
                        return new Result(false, username + " is already playing in other game!");

                    Player player = new Player(user);
                    players.add(player);
                    user.setPlaying(true);
                    found = true;
                    break;
                }
            }
            if (!found)
                return new Result(false, "No player with username " + username + " found!");
        }

        for (int i = 0; i < 3 - usernames.size(); i++) {
            players.add(new Player(new User("Guest" + i, null, "Guest" + i,
                    null, null, 0, null)));
        }

        Director director = new Director();
        WholeGameBuilder wholeGameBuilder = new WholeGameBuilder();
        director.createNewGame(wholeGameBuilder, players, adminPlayer);
        Game game = wholeGameBuilder.getGame();

        ArrayList<Tile> tiles = createTiles();
        ArrayList<Farm> farms = farmGame(players, scanner, tiles);

        WholeMapBuilder wholeMapBuilder = new WholeMapBuilder();
        director.createNewMap(wholeMapBuilder, farms, tiles);
        game.setMap(wholeMapBuilder.getMap());

        App.setCurrentGame(game);
        App.getGames().add(game);
        for (Player player : players) {
            player.getUser().setGame(game);
            player.getUser().setPlaying(true);
            player.iniFriendships(players);
        }

        //Goods generating
        App.getCurrentGame().getMap().generateRandomForagingCrops(93);
        App.getCurrentGame().getMap().generateRandomForagingSeed(93);
        App.getCurrentGame().getMap().generateRandomMinerals(93);
        App.getCurrentGame().getMap().generateRandomForagingTrees(93);
        App.getCurrentGame().getMap().generateRandomGrassTrees(93);


        return new Result(true, "New game has successfully created & loaded!\n");
    }

    public Result loadGame() {
        Game game = App.findGame(App.getCurrentUser());
        if (game == null)
            return new Result(false, "You have no game to load!");

        App.setCurrentGame(game);
        for (Player player : game.getPlayers()) {
            if (player.getUser().getUsername().equals(App.getCurrentUser().getUsername()))
                App.getCurrentGame().setGameAdmin(player);
            player.getUser().setGame(game);
        }

        return new Result(true, "Your game has successfully loaded!");
    }

    public Result exitGame() {
        if (App.getCurrentGame().getGameAdmin() != App.getCurrentGame().getCurrentPlayer()) {
            return new Result(false, "Just game admin can exit the game!");
        } else {
            App.setCurrentGame(null);
            App.setCurrentMenu(Menu.MainMenu);
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
            if (input.matches("\\s*y\\s*"))
                poll.add(true);
            else
                poll.add(false);
        }

        for (Boolean p : poll) {
            if (!p)
                return new Result(false, "All players do not agree to terminate the game!");
        }

        for (Player player : App.getCurrentGame().getPlayers()) {
            player.getUser().setPlaying(false);
            player.getUser().setGame(null);
            player.getUser().increaseEarnedPoints(player.getPoints());
            player.getUser().maxMaxPoints(player.getPoints());
            player.getUser().increaseGamePlay(App.getCurrentGame().getDateTime().getDays());
        }

        App.getGames().remove(App.getCurrentGame());
        App.setCurrentGame(null);
        return new Result(true, "Game terminated successfully!");

    }

    public Result nextTurn() {
        App.getCurrentGame().nextPlayer();

        StringBuilder news = new StringBuilder();
        news.append("Current player: ").append(App.getCurrentGame().getCurrentPlayer().getUser().getUsername() + "\nNews:\n");
        int ctr = 1;
        for (String s : App.getCurrentGame().getCurrentPlayer().getNews()) {
            news.append("\t").append(ctr++).append(". ").append(s);
        }

        App.getCurrentGame().getCurrentPlayer().getNews().clear();


        return new Result(true, news.toString());
    }


    // Nader
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
        int hourInt = Integer.parseInt(hour);
        for (int i = 0; i < hourInt; i++) {
            App.getCurrentGame().getDateTime().timeFlow();
        }
        return new Result(true, "You have cheat advance time for " + hourInt + " hours!");
    }

    public Result cheatAdvanceDate(String date) {
        int dateInt = Integer.parseInt(date);
        for (int i = 0; i < dateInt * 13; i++) {
            App.getCurrentGame().getDateTime().timeFlow();
        }
        return new Result(true, "You have cheat advance date for " + dateInt + " days!");
    }


    // Parsa
    //Weather methods
    public Result cheatThunder(String x, String y) {
        x = x.trim();
        y = y.trim();

        int xInt = Integer.parseInt(x);
        int yInt = Integer.parseInt(y);
        App.getCurrentGame().getWeather().thunder(xInt, yInt);
        return new Result(true, "");
    }

    public Result weather() {
        return new Result(true, App.getCurrentGame().getWeatherName());
    }

    public Result weatherForecast() {
        return new Result(true, App.getCurrentGame().getTomorrow().weatherForecast().getName());
    }

    public Result cheatWeatherSet(String weather) {
        weather = weather.trim();

        switch (weather) {
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
        return new Result(true, "Weather set to " + weather);
    }

    public Result greenHouseBuild() {
        Game game = App.getCurrentGame();
        if (game.getCurrentPlayer().getWallet().getBalance() < 1000) {
            return new Result(false, "You have not enough money to build green house!");
        }

        boolean flag = false;
        for (ArrayList<Good> goods : game.getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getFirst().getName().equals("Wood") && Inventory.decreaseGoods(goods, 500)) {
                flag = true;
                break;
            }
        }
        if (!flag)
            return new Result(false, "You have not enough Wood to build green house!");
        game.getCurrentPlayer().getWallet().decreaseBalance(1000);

        game.getCurrentPlayer().getFarm().getGreenHouse().setAvailable(true);
        return new Result(true, "Your green house has been successfully built!");
    }


    //Parsa
    //Map methods
    public Result walk(String x, String y, Scanner scanner) {
        x = x.trim();
        y = y.trim();

        if (!x.matches("-?\\d+") || !y.matches("-?\\d+"))
            return new Result(false, "Invalid Coordinate input!");

        Coordinate goal = new Coordinate(Integer.parseInt(x), Integer.parseInt(y));
        Tile tile = App.getCurrentGame().getMap().findTile(goal);
        if (tile == null)
            return new Result(false, "Goal tile not found!");

        if (tile.getTileType() == TileType.GAME_BUILDING &&
                !App.getCurrentGame().getMap().findGameBuilding(goal).isInWorkingHours()) {
            return new Result(false, App.getCurrentGame().getMap().findGameBuilding(goal).getName() + " hours have ended for today!");
        }


        ArrayList<Pair<Integer, Coordinate>> path = AStar.findPath(App.getCurrentGame().getMap(),
                App.getCurrentGame().getCurrentPlayer().getCoordinate(), goal);

        if (path == null)
            return new Result(false, "No path " + goal + " found!");

        int energyConsumed = path.getLast().first() / 20;
        int playerEnergy = App.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft();
        System.out.println("Energy needed to walk to " + goal + " location is " + energyConsumed + " (Your Energy: " +
                playerEnergy
                + ")\n.Do you want to continue? (y/n)");
        String input = scanner.nextLine();
        if (!input.matches("\\s*y\\s*")) {
            return new Result(true, "You didn't walked!");
        }

        Coordinate coordinate = null;
        for (Pair<Integer, Coordinate> pair : path) {
            if ((pair.first() / 20) < playerEnergy)
                coordinate = pair.second();
        }

        if (energyConsumed > playerEnergy) {
            App.getCurrentGame().getCurrentPlayer().setCoordinate(coordinate);
            App.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(playerEnergy);
            App.getCurrentGame().getCurrentPlayer().getEnergy().setAwake(false);
            return new Result(true, "Your energy was enough to walk to " + goal +
                    " location! Now your are fainted & your daily energy is 0!");
        } else {
            App.getCurrentGame().getCurrentPlayer().setCoordinate(goal);
            App.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(energyConsumed);
            return new Result(true, "Your are now in " + goal + " location!");
        }
    }

    public Result printMap(String x, String y, String size) {
        x = x.trim();
        y = y.trim();
        size = size.trim();

        int IntX = Integer.parseInt(x);
        int IntY = Integer.parseInt(y);
        int IntSize = Integer.parseInt(size);
        App.getCurrentGame().getMap().printMap(IntX, IntY, IntSize);
        return new Result(true, "");
    }

    public Result helpReadingMap() {
        return new Result(true, " " + " -> " + "Tile don't exist\n" +
                "Green" + " -> " + "Farm\n" +
                "Cyan" + " -> " + "Water\n" +
                "White" + " -> " + "Green House\n" +
                "Blown T" + " -> " + "Tree\n" +
                "Yellow" + " -> " + "Building\n" +
                "Gray" + " -> " + "Quarry\n" +
                "Gray with Purple -" + " -> " + "Road\n" +
                "Green with Red -" + " -> " + "Plain\n");
    }

    // Parsa
    //inventory & Energy methods
    public Result energyShow() {
        if (App.getCurrentGame().
                getCurrentPlayer().getEnergy().getDayEnergyLeft() > 3000) {
            return new Result(true, ("INFINITE"));
        }
        return new Result(true, (App.getCurrentGame().
                getCurrentPlayer().getEnergy()).getDayEnergyLeft() + "\n");
    }

    public Result cheatEnergySet(String value) {
        value = value.trim();

        int valueInt = Integer.parseInt(value);
        App.getCurrentGame().getCurrentPlayer().getEnergy().setDayEnergyLeft(valueInt);
        App.getCurrentGame().getCurrentPlayer().getEnergy().setTurnValueLeft(50);
        return new Result(true, "Your energy been set to " + valueInt);
    }

    public Result cheatEnergyUnlimited() {
        App.getCurrentGame().getCurrentPlayer().getEnergy().setMaxDayEnergy(Integer.MAX_VALUE);
        App.getCurrentGame().getCurrentPlayer().getEnergy().setMaxTurnEnergy(Integer.MAX_VALUE);
        App.getCurrentGame().getCurrentPlayer().getEnergy().setTurnValueLeft(Integer.MAX_VALUE);
        App.getCurrentGame().getCurrentPlayer().getEnergy().setDayEnergyLeft(Integer.MAX_VALUE);
        return new Result(true, "Energy set to Unlimited");
    }

    public Result inventoryTrashItem(String itemName, String number) {
        itemName = itemName.trim();
        number = number.trim();

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(itemName);
        if (goods == null)
            return new Result(false, "No good with name " + itemName + " found in your inventory!");

        int numberInt = -1;
        if (number.matches(""))
            numberInt = goods.size();
        else if (number.matches("-?\\d+"))
            numberInt = Integer.parseInt(number);
        else
            return new Result(false, "Invalid number format!");

        if (numberInt > goods.size())
            return new Result(false, "Your don't have enough number of this good!");

        int totalPrice = 0;
        for (int i = 0; i < numberInt; i++) {
            totalPrice += goods.getLast().getSellPrice();
            goods.removeLast();
        }

        int finalPrice = ToolFunctions.useTrashCan(App.getCurrentGame().getCurrentPlayer().getTrashCan(), totalPrice);
        App.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(finalPrice);
        return new Result(true, "Your earned " + finalPrice + " coins from putting your good into trash can!");
    }

    public Result inventoryShow() {
        StringBuilder inventoryList = new StringBuilder();
        for (ArrayList<Good> good : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!good.isEmpty()) {
                inventoryList.append(good.getFirst().getName()).append(" ").append(good.size()).append("\n");
            }
        }
        return new Result(true, inventoryList.toString());
    }

    // Arani
    // Tools
    public Result toolsEquipment(String toolName) {
        toolName = toolName.trim();

        boolean flag = false;
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getFirst().getName().equals(toolName) && goods.size() == 1) {
                App.getCurrentGame().getCurrentPlayer().setInHandGood((Tool) goods.getFirst());
                flag = true;
                break;
            }
            if(!flag && toolName.equals("Trash_Can")) {
                App.getCurrentGame().getCurrentPlayer().setInHandGood(App.getCurrentGame().getCurrentPlayer().getTrashCan());
                flag = true;
            }
        }
        if (!flag)
            return new Result(false, "You don't have " + toolName + "to use!");

        return new Result(true, "Now you can use " + toolName);
    }

    public Result toolsShowCurrent() {
        if (App.getCurrentGame().getCurrentPlayer().getInHandGood() instanceof Tool) {
            return new Result(true, "Your current tool: " + App.getCurrentGame().getCurrentPlayer().getInHandGood().getName());
        } else {
            return new Result(true, "You don't have tool in your hand!");
        }
    }

    public Result toolsShowAvailable() {
        StringBuilder toolsList = new StringBuilder();
        toolsList.append("List of available tools:\n");
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getFirst() instanceof Tool) {
                toolsList.append("\t").append(goods.getFirst().getName()).append("\n");
            }
        }

        return new Result(true, toolsList.toString());
    }

    public Result toolsUpgrade(String toolName) {
        toolName = toolName.trim();

        Game game = App.getCurrentGame();
        if (!game.getMap().getBlackSmith().isInsideBuilding(game.getCurrentPlayer().getCoordinate()))
            return new Result(false, "You are not inside the BlackSmith Shop!");
        if (game.getCurrentPlayer().getInHandGood() instanceof Tool) {
            Blacksmith blacksmith = (Blacksmith) game.getMap().getBlackSmith();
            if (((ToolType) game.getCurrentPlayer().getInHandGood().getType()).getLevel().getLevelNumber() == 4)
                return new Result(true, "Your tool is already in the highest level!");

            if (blacksmith.upgradeTool((Tool) game.getCurrentPlayer().getInHandGood())) {
                return new Result(false, "Your tool has successfully upgraded!");
            } else
                return new Result(false, "You don't have enough money & ingredients to upgrade "
                        + game.getCurrentPlayer().getInHandGood().getName() + "!");
        } else
            return new Result(false, "You don't have tool in your hand!");
    }

    public Result toolsUse(String direction) {
        direction = direction.trim();

        if (App.getCurrentGame().getCurrentPlayer().getInHandGood() instanceof Tool) {
            Tool tool = (Tool) App.getCurrentGame().getCurrentPlayer().getInHandGood();
            Coordinate coordinate = Coordinate.getDirection(direction);
            if (coordinate == null)
                return new Result(false, "Direction not recognized");

            if (App.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft() < tool.getType().getEnergy())
                return new Result(false, "You don't have enough energy to use " + tool.getName() + "!");
            if (App.getCurrentGame().getMap().findTile(coordinate) == null)
                return new Result(false, "Tile not found");

            App.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(tool.getType().getEnergy());
            App.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(tool.getType().getEnergy());

            return ToolFunctions.tooluse(tool, coordinate);
        } else
            return new Result(false, "You don't have tool in your hand!");
    }

    // Arani
    // Craft Info
    public Result craftInfo(String craftName) {
        craftName = craftName.trim();

        GoodType craft = findCraft(craftName);
        if (craft == null)
            return new Result(false, "Craft not found");

        return new Result(true, "Craft Info:\n" + craft.toString());
    }


    // Arani
    // Planting
    public Result plantSeed(String seed, String direction) {
        seed = seed.trim();
        direction = direction.trim();

        Coordinate coordinate = Coordinate.getDirection(direction);
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate, App.getCurrentGame().getCurrentPlayer());
        if (tile == null)
            return new Result(false, "You don't have access to this tile!");

        if (tile.getTileType() != TileType.PLOWED_FARM && tile.getTileType() != TileType.GREEN_HOUSE)
            return new Result(false, "Selected Tile is not Plowed or GreenHouse for planting!");
        if (tile.getTileType().equals(TileType.GREEN_HOUSE)) {
            if (App.getCurrentGame().getCurrentPlayer().getFarm().getGreenHouse().isAvailable()) {
                return new Result(false, "You haven't built your Green House yet");
            }
        }

        ArrayList<Good> seeds = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(seed);
        if (seeds == null)
            return new Result(false, "You don't have " + seed + " in your inventory!");


        for (Good good : tile.getGoods()) {
            if (good instanceof FarmingTreeSapling || good instanceof FarmingTree ||
                    good instanceof ForagingSeed || good instanceof ForagingTree || good instanceof ForagingMineral ||
                    good instanceof ForagingMixedSeed)
                return new Result(true, "A seed already planted in this tile!");
        }

        boolean growable = false;
        if (tile.getTileType() != TileType.GREEN_HOUSE) {
            if (seeds.getFirst().getType() instanceof ForagingSeedType) {
                for (Season season : ((ForagingSeedType) seeds.getFirst().getType()).getSeason()) {
                    if (season.equals(App.getCurrentGame().getDateTime().getSeasonOfYear())) {
                        growable = true;
                    }
                }
                if (!growable) {
                    return new Result(false, "You are not inside the Foraging Season of this seed!");
                }
            }
        }


        //mixed seed
        try {
            Good good = seeds.getLast();
            if (good instanceof ForagingMixedSeed) {
                ForagingMixedSeedType type = (ForagingMixedSeedType) good.getType();
                int random = (int) (Math.random() * type.getPossibleCrops().size());
                ForagingSeedType crop = (ForagingSeedType) type.getPossibleCrops().get(random);
                ((ForagingMixedSeed) good).setForagingSeedType(crop);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Plant Seed problem :(");
        }

        tile.getGoods().add(seeds.getLast());
        seeds.removeLast();

        return new Result(true, "A plant seed has been planted on tile " + coordinate + "!");
    }

    public Result showPlant(String x, String y) {
        x = x.trim();
        y = y.trim();

        if (!x.matches("-?\\d+") || !y.matches("-?\\d+"))
            return new Result(false, "Invalid location format!");

        Coordinate coordinate = new Coordinate(Integer.parseInt(x), Integer.parseInt(y));
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate,
                App.getCurrentGame().getCurrentPlayer());

        if (tile == null)
            return new Result(false, "You don't have access to this tile!");

        Good plant = null;
        for (Good good : tile.getGoods()) {
            if (good instanceof FarmingTreeSapling || good instanceof FarmingTree ||
                    good instanceof ForagingSeed || good instanceof ForagingTree) {
                plant = good;
                break;
            }
        }
        if (plant == null)
            return new Result(false, "There is no plant in this location!");

        return new Result(true, "Plant Info:\n" + plant.toString());
    }

    public Result fertilize(String fertilizerName, String direction) {

        fertilizerName = fertilizerName.trim();
        direction = direction.trim();

        Coordinate coordinate = Coordinate.getDirection(direction);
        Tile tile = App.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate,
                App.getCurrentGame().getCurrentPlayer());

        if (tile == null)
            return new Result(false, "You don't have access to this tile!");

        ArrayList<Good> fertilizer = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(fertilizerName);
        if (fertilizer == null)
            return new Result(false, "You don't have " + fertilizerName + " in your inventory!");

        tile.getGoods().add(fertilizer.getLast());
        fertilizer.removeLast();

        boolean isThereAPlant = false;
        for (Good good : tile.getGoods()) {
            if (good instanceof FarmingTreeSapling) {
                isThereAPlant = true;
            }   else if (good instanceof FarmingTree) {
                isThereAPlant = true;
            }   else if (good instanceof ForagingSeed) {
                isThereAPlant = true;
            }   else if (good instanceof ForagingTree) {
                isThereAPlant = true;
            }   else if (good instanceof ForagingMixedSeed) {
                isThereAPlant = true;
            }
        }
        if(!isThereAPlant){
            return new Result(false, "There is no plant in this location!");
        }
        return new Result(true, "You have fertilized tile in location " + coordinate + " with " + fertilizer.getFirst().getName() + "!");
    }

    public Result howMuchWater() {
        Tool tool = (Tool) App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ToolType.WATERING_CAN.getName()).getFirst();
        return new Result(true, "Your watering can have capacity:" + tool.capacity);
    }


    // Nader
    // crafting methods
    public Result showCraftingRecipes() {

        for (CraftingRecipe craftingRecipe : App.getCurrentGame().getCurrentPlayer().getCraftingRecipes()) {
            System.out.println(craftingRecipe.getName());
            System.out.println("\t"+((CraftingRecipeType) craftingRecipe.getType()).getIngredients());
            if (((CraftingRecipeType) craftingRecipe.getType()).getSource() != null) {
                System.out.println("\t"+((CraftingRecipeType) craftingRecipe.getType()).getSource());
            }
            System.out.println("-------------------------------------------");
        }
        return new Result(true, "");
    }

    public Result craftingCraft(String itemName) {
        itemName = itemName.trim();
        for (CraftingRecipe craftingRecipe : App.getCurrentGame().getCurrentPlayer().getCraftingRecipes()) {
            if (craftingRecipe.getName().equals(itemName)) {
                CraftingFunctions craftingFunctions = new CraftingFunctions();
                craftingFunctions.checkCraftingFunctions((CraftingRecipeType) craftingRecipe.getType());
                return new Result(true, "");
            }
        }
        return new Result(false, "You don't have " + itemName + " recipe!");
    }

    public Result placeItem(String itemName, String direction) {
        itemName = itemName.trim();
        direction = direction.trim();

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

        Coordinate coordinate = Coordinate.getDirection(direction);
        switch (coordinate) {
            case null:
                return new Result(false, "Direction not recognized");
            default:
                newX = coordinate.getX();
                newY = coordinate.getY();
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
        itemName = itemName.trim();
        count = count.trim();

        GoodType goodType = Good.newGoodType(itemName);
        if (goodType == null)
            return new Result(false, "An item with name '" + itemName + "' not found!\n");

        if (!count.matches("-?\\d+"))
            return new Result(false, "Invalid number format!\n");

        Player player = App.getCurrentGame().getCurrentPlayer();
        if (player.getInventory().isInInventory(goodType) == null && player.getInventory().isFull())
            return new Result(false, "Your inventory is full!\n");

        ArrayList<Good> newGoods = Good.newGoods(goodType, Integer.parseInt(count));
        player.getInventory().addGood(newGoods);

        return new Result(true, "You have added (" + count + ") " + itemName + " to the inventory!\n");
    }


    // Nader
    // cooking methods
    public Result cookingRefrigerator(String status, String itemName) {

        status = status.trim();
        itemName = itemName.trim();

        TileType tileType = App.getCurrentGame().getMap().findTileType(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        if (!tileType.equals(TileType.PLAYER_BUILDING)) {
            return new Result(false, "You are not in Home!");
        }

        Fridge fridge = App.getCurrentGame().getCurrentPlayer().getFridge();
        Inventory inventory = App.getCurrentGame().getCurrentPlayer().getInventory();
        GoodType type = Good.newGoodType(itemName);
        Good good = Good.newGood(type);

        if (type == null) {
            return new Result(false, "This item is invalid!");
        }

        if (!(good instanceof Food || good instanceof Artisan || good instanceof AnimalProduct
              || good instanceof FarmingCrop)) {
            return new Result(false, "Hey! You can't add this item to your fridge!");
        }

        if (status.equals("pick")) {
            int count = fridge.howManyInFridge(type);
            if (count == 0) {
                return new Result(false, "There is no " + itemName + " in the fridge!");
            } else {
                fridge.removeItemsFromFridge(type, 1);
                boolean added = inventory.addGood(good, 1);
                if (added) {
                    return new Result(true, "You have added " + itemName + " to the inventory!");
                }
            }
            return new Result(false, "Inventory is full");

        } else if (status.equals("put")) {
            int count = inventory.howManyInInventory(good);
            if (count == 0) {
                return new Result(false, "There is no " + itemName + " in the inventory!");
            } else {
                inventory.removeItemsFromInventory(type, 1);
                fridge.addItemToFridge(good);
                return new Result(true, "You have added " + itemName + " to the fridge!");
            }

        }

        return new Result(false, "Invalid operation");
    }

    public Result showCookingRecipes() {
        for (CookingRecipe cookingRecipe : App.getCurrentGame().getCurrentPlayer().getCookingRecipes()) {
            System.out.println(cookingRecipe.getName() + " ---");
            for (Pair<GoodType, Integer> ingredient : cookingRecipe.getType().getIngredients()) {
                System.out.println(ingredient.first().getName() + " " + ingredient.second());
            }
        }
        return new Result(true, "< COOKING RECIPES >");
    }

    public Result cookingPrepare(String recipeName) {
        recipeName = recipeName.trim();

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

        for (Pair<GoodType, Integer> ingredient : recipe.getType().getIngredients()) {

            GoodType ingredientType = (GoodType) ingredient.first();
            int requiredAmount = ingredient.second();
            if (App.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(ingredientType) < requiredAmount) {
                return new Result(false, "Not enough " + ingredientType.getName() +
                        " (needed: " + requiredAmount + ")");
            }
        }

        for (Pair<GoodType, Integer> ingredient : recipe.getType().getIngredients()) {
            GoodType ingredientType = (GoodType) ingredient.first();
            int requiredAmount = ingredient.second();
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ingredientType, requiredAmount);
        }
        Good food = Good.newGood(recipe.getType().getGoodType());
        App.getCurrentGame().getCurrentPlayer().getSkill().increaseCookingPoints(10);
        App.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(3);
        if (App.getCurrentGame().getCurrentPlayer().getInventory().addGood(Good.newGoods(recipe.getType().getGoodType(), 1))) {
            return new Result(true, "You put " + food.getName() + " into the inventory");
        }
        return new Result(false, "Your inventory is full");

    }


    public Result eat(String foodName) {
        foodName = foodName.trim();
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

        if (food instanceof FarmingCrop) {
            App.getCurrentGame().getCurrentPlayer().eat(food);
        } else if (food instanceof Artisan) {
            App.getCurrentGame().getCurrentPlayer().eat(food);
        } else if (food instanceof Food) {
            App.getCurrentGame().getCurrentPlayer().eat(food);
        } else {
            return new Result(false, "NO NO! What are you trying to eat mmd Jan?");
        }
        return new Result(true, "Khosmaz, Yum Yum!");
    }


    // Parsa
    // Animals & Fishing methods
    public Result buildBuilding(String buildingName, String x, String y) {
        buildingName = buildingName.trim();
        x = x.trim();
        y = y.trim();

        CarpenterShop carpenterShop = (CarpenterShop) App.getCurrentGame().getMap().getCarpenterShop();
        if (carpenterShop.isInWorkingHours()) {
            FarmBuildingTypes targetType = null;
            for (FarmBuildingTypes type : carpenterShop.getProducts()) {
                if (type.getName().equals(buildingName)) {
                    targetType = type;
                    break;
                }
            }
            if (targetType == null) {
                return new Result(false, "This building is invalid");
            }
            if (App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() > targetType.getCost()) {
                if (targetType.getWood() < App.getCurrentGame().getCurrentPlayer().getInventory()
                        .howManyInInventoryByType(ProductType.WOOD) &&
                        targetType.getStone() < App.getCurrentGame().getCurrentPlayer().getInventory()
                                .howManyInInventoryByType(ProductType.STONE)) {
                    Coordinate coordinate = new Coordinate(Integer.parseInt(x), Integer.parseInt(y));
                    Coordinate startCoordinate = new Coordinate((int) Integer.parseInt(x) - targetType.getSize().first() / 2,
                            (int) Integer.parseInt(y) - targetType.getSize().second() / 2);

                    boolean validSpace = true;
                    for (int sX = 0; sX < targetType.getSize().first(); sX++) {
                        for (int sY = 0; sY < targetType.getSize().second(); sY++) {
                            Tile tempTile = App.getCurrentGame().getMap().findTileByXY(sX + startCoordinate.getX(), sY + startCoordinate.getY());
                            if (tempTile.getTileType().equals(TileType.GAME_BUILDING) ||
                                    tempTile.getTileType().equals(TileType.BEACH) ||
                                    tempTile.getTileType().equals(TileType.PLAYER_BUILDING)) {
                                validSpace = false;
                            }
                        }
                    }
                    if (!validSpace) {
                        return new Result(false, "You can't build this building here!");
                    } else {
                        App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ProductType.WOOD, targetType.getWood());
                        App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ProductType.STONE, targetType.getStone());
                        App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(targetType.getCost());
                        FarmBuilding newBuilding = carpenterShop.buildingFarmBuilding(targetType, startCoordinate);
                        App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings().add(newBuilding);
                        for (int sX = 0; sX < targetType.getSize().first(); sX++) {
                            for (int sY = 0; sY < targetType.getSize().second(); sY++) {
                                App.getCurrentGame().getMap()
                                        .findTileByXY(sX + startCoordinate.getX(), sY +
                                                startCoordinate.getY()).setTileType(TileType.PLAYER_BUILDING);
                            }
                        }
                        return new Result(true, "You build a " + buildingName + "!");

                    }


                } else return new Result(false, "You don't have enough Wood/Stone");

            } else {
                return new Result(false, "You don't have enough Money");
            }
        }
        return new Result(false, "Store is not Open!\nWorking Time: " + carpenterShop.getHours().first()
                + " ~ " + (carpenterShop.getHours().second()));
    }

    public Result buyAnimal(String animalType, String animalName) {
        if (animalType == null || animalName == null || animalType.trim().isEmpty() || animalName.trim().isEmpty()) {
            return new Result(false, "Invalid animal type or name");
        }
        animalType = animalType.trim();
        animalName = animalName.trim();

        MarnieRanch marnieRanch = (MarnieRanch) App.getCurrentGame().getMap().getMarnieRanch();
        if (!marnieRanch.isInWorkingHours()) {
            return new Result(false, "Store is not Open!\nWorking Time: " + marnieRanch.getHours().first()
                    + " ~ " + (marnieRanch.getHours().second()));
        }
        AnimalTypes animalTypeEnum = null;
        for (AnimalTypes type : marnieRanch.animals) {
            if (type.getName().equals(animalType)) {
                animalTypeEnum = type;
                break;
            }
        }
        if (animalTypeEnum == null) {
            return new Result(false, "Animal type not found: " + animalType);
        }
        if (App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() < animalTypeEnum.getPrice()) {
            return new Result(false, "You don't have enough Money!");
        }

        FarmBuilding suitableBuilding = null;
        for (FarmBuilding farmBuilding : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
            if (farmBuilding.getType().equals(animalTypeEnum.getFarmBuildingTypes())) {
                suitableBuilding = farmBuilding;
                break;
            }
        }
        if (suitableBuilding == null) {
            return new Result(false, "You don't have a suitable building for this animal!");
        }

        Animal animal = marnieRanch.buildAnimal(animalTypeEnum, animalName);
        if (!suitableBuilding.addAnimal(animal)) {
            return new Result(false, suitableBuilding.getName() + " is full");
        }

        App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(animalTypeEnum.getPrice());
        animal.setLocatedPLace(suitableBuilding);
        App.getCurrentGame().getMap().allAnimals().add(animal);
        int x = (int) (suitableBuilding.getStartCordinate().getX() + Math.random() * 2);
        int y = (int) (suitableBuilding.getEndCordinate().getY() - Math.random() * 2);
        animal.setCoordinate(new Coordinate(x, y));

        return new Result(true, "A " + animalType + " named " + animalName + " has been added to your farm!");
    }

    public Result petAnimal(String animalName) {
        animalName = animalName.trim();

        Animal animal = App.getCurrentGame().getMap().findAnimalByName(animalName);
        if (animal == null) {
            return new Result(false, "Animal not found: " + animalName);
        }
        if (abs(App.getCurrentGame().getCurrentPlayer().getCoordinate().getX() - animal.getCoordinate().getX()) <= 1 &&
                abs(App.getCurrentGame().getCurrentPlayer().getCoordinate().getY() - animal.getCoordinate().getY()) <= 1) {
            animal.petAnimal();
            return new Result(true, "You petted " + animalName);
        }
        return new Result(false, "You are not close enough to this animal to pet");

    }

    public Result animalList() {
        App.getCurrentGame().getCurrentPlayer().showAnimals();
        return new Result(true, "");
    }

    public Result cheatSetAnimalFriendship(String animalName, String amount) {
        animalName = animalName.trim();
        animalName = animalName.trim();

        int amountInt = Integer.parseInt(amount);
        Animal animal = App.getCurrentGame().getMap().findAnimalByName(animalName);
        if (animal == null) {
            return new Result(false, "Animal not found");
        }
        animal.setFriendship(Math.min(1000, amountInt));
        return new Result(true, "You set your friendship with " + animalName + " to " + Math.min(1000, amountInt));
    }

    public Result shepherdAnimal(String animalName, String x, String y) {
        animalName = animalName.trim();
        x = x.trim();
        y = y.trim();

        Animal animal = App.getCurrentGame().getMap().findAnimalByName(animalName);
        if (animal == null) {
            return new Result(false, "Animal not found");
        }
        animal.shepherdAnimal(new Coordinate(Integer.parseInt(x), Integer.parseInt(y)));
        return new Result(true, "You shepherd " + animalName);
    }

    public Result feedHay(String animalName) {
        animalName = animalName.trim();

        Animal animal = App.getCurrentGame().getMap().findAnimalByName(animalName);
        if (!App.getCurrentGame().getCurrentPlayer().getInventory().isInInventoryBoolean(ProductType.HAY)) {
            return new Result(false, "You don't have enough Hay");
        } else {
            App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ProductType.HAY, 1);
            animal.setFed(true);
        }
        return new Result(true, "You fed " + animalName);
    }

    public Result animalProductionList() {
        for (FarmBuilding building : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
            if (!building.getName().equals("Home")) {
                System.out.println(building.getName() + " >");
                for (Animal animal : building.getAnimals()) {
                    animal.showProducts();
                }
            }
        }
        return new Result(true, "");
    }

    public Result collectProduct(String animalName) {
        animalName = animalName.trim();

        Animal animal = App.getCurrentGame().getMap().findAnimalByName(animalName);
        if (animal == null) {
            return new Result(false, "Animal not found");
        }

        if (animal.getProducts() != null) {
            for (AnimalProduct product : animal.getProducts()) {
                App.getCurrentGame().getCurrentPlayer().getInventory().addGoodByObject(product);
            }
            System.out.println("You collected animal Products.");
            animal.getProducts().clear();
        }
        return new Result(true, "");
    }

    public Result sellAnimal(String animalName) {
        animalName = animalName.trim();

        Animal animal = App.getCurrentGame().getMap().findAnimalByName(animalName);
        if (animal == null) {
            return new Result(false, "Animal not found");
        }
        FarmBuilding building = animal.getLocatedPLace();
        building.getAnimals().remove(animal);

        //remove from animals
        App.getCurrentGame().getMap().allAnimals().remove(animal);

        App.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(animal.getAnimalSellPrice());

        return new Result(true, "You sold " + animal.getAnimalSellPrice());
    }

    public Result fishing(String fishingPole) {
        fishingPole = fishingPole.trim();

        ToolType fishingPoleGood = ToolType.getTool(fishingPole);
        if (App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(fishingPole) == null) {
            return new Result(true, "You don't have this fishing pole");
        }
        for (Coordinate coordinate : Coordinate.coordinates) {
            Coordinate c = Coordinate.checkAround(App.getCurrentGame().getCurrentPlayer().getCoordinate(), coordinate);
            Tile tile = App.getCurrentGame().getMap().findTile(c);
            if (tile.getTileType() == TileType.WATER) {
                Weather weather = App.getCurrentGame().getWeather();
                Skill skill = App.getCurrentGame().getCurrentPlayer().getSkill();
                double chance = Math.random();

                double numberOfFishes = Math.min(weather.getFishChance() * chance * (2 + skill.getFishingLevel()), 6);


                double poleRarityChange = 0;
                switch (fishingPoleGood) {
                    case ToolType.IRIDIUM_FISHING_POLE:
                        poleRarityChange = 1.2;
                        break;
                    case ToolType.TRAINING_FISHING_POLE:
                        poleRarityChange = 0.1;
                        break;
                    case ToolType.BAMBOO_FISHING_POLE:
                        poleRarityChange = 0.5;
                        break;
                    case ToolType.FIBERGLASS_FISHING_POLE:
                        poleRarityChange = 0.9;
                        break;
                    default:
                        break;
                }

                double rarityChance = Math.random();
                double fishRarity = Math.min(((rarityChance * (2 + skill.getFishingLevel()) * poleRarityChange) / (7 - weather.getFishChance())), 4);


                ToolFunctions.fish(fishingPoleGood, numberOfFishes, fishRarity);

                return new Result(true, "Better luck next time");
            }
        }
        return new Result(true, "You are not close to water source");
    }


    // Nader
    // artisan methods
    public Result artisanUse(String artisanName, String artisanIngredient1, String artisanIngredient2) {
        artisanName = artisanName.trim();
        ArrayList<String> ourIngredients = new ArrayList<>();
        if (!artisanIngredient1.isEmpty())
            ourIngredients.add(artisanIngredient1);
        if (!artisanIngredient2.isEmpty())
            ourIngredients.add(artisanIngredient2);

        return ArtisanFunctions.useArtisan(artisanName, ourIngredients);
    }

    public Result artisanGet(String artisanName) {
        artisanName = artisanName.trim();
        //TODO
        return new Result(true, "");
    }

    // Nader
    // buy & sell methods
    public Result showAllProducts() {
        Coordinate coordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();
        Tile tile = App.getCurrentGame().getMap().findTile(coordinate);
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to show all products!");
        if (!App.getCurrentGame().getMap().findGameBuilding(coordinate).isInWorkingHours()) {
            return new Result(false, App.getCurrentGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        GameBuilding building = App.getCurrentGame().getMap().findGameBuilding(coordinate);
        return new Result(true, building.showAllProducts());
    }

    public Result showAllAvailableProducts() {
        Coordinate coordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to show all available products!");
        if (!App.getCurrentGame().getMap().findGameBuilding(coordinate).isInWorkingHours()) {
            return new Result(false, App.getCurrentGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        GameBuilding building = App.getCurrentGame().getMap().findGameBuilding(coordinate);
        return new Result(true, building.showProducts());
    }

    public Result purchase(String productName, String count) {
        productName = productName.trim();
        count = count.trim();

        Coordinate coordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();
        Tile tile = App.getCurrentGame().getMap().findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to purchase a product!");
        if (!App.getCurrentGame().getMap().findGameBuilding(coordinate).isInWorkingHours()) {
            return new Result(false, App.getCurrentGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        GameBuilding building = App.getCurrentGame().getMap().findGameBuilding(coordinate);
        return building.purchase(productName, count);
    }

    public Result cheatAddDollars(String count) {
        count = count.trim();

        int amount = Integer.parseInt(count);
        App.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(amount);
        return new Result(true, count + "G added to your wallet!");
    }

    public Result sell(String productName, String count) {
        productName = productName.trim();
        count = count.trim();

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(productName);
        if (goods == null)
            return new Result(false, "You don't have this good in your inventory!");

        if (!count.matches("-?\\d+") && !count.isEmpty())
            return new Result(false, "Invalid Quantity format!");

        int quantity = (count.isEmpty()) ? goods.size() : Integer.parseInt(count);
        if (quantity > goods.size())
            return new Result(false, "You don't have enough number of this good in your inventory!");

        boolean flag = false;
        for (int i = 0; i < 8; i++) {
            Coordinate coordinate = new Coordinate(
                    App.getCurrentGame().getCurrentPlayer().getCoordinate().getX() + Coordinate.coordinates.get(i).getX(),
                    App.getCurrentGame().getCurrentPlayer().getCoordinate().getY() + Coordinate.coordinates.get(i).getY());

            Tile tile = App.getCurrentGame().getMap().findTile(coordinate);
            if (tile != null && tile.findGood("ShippingBin") != null) {
                ArrayList<Good> newGoods = new ArrayList<>(goods);
                for (int j = 0; j < quantity; j++) {
                    newGoods.add(goods.getLast());
                    goods.removeLast();
                }

                ShippingBin shippingBin = (ShippingBin) tile.findGood("ShippingBin");
                shippingBin.addGood(newGoods, App.getCurrentGame().getCurrentPlayer());
                flag = true;
                break;
            }
        }

        if (flag)
            return new Result(true, quantity + " number of " + productName + " has been added to ShippingBin!");
        else
            return new Result(false, "No ShippingBin found around you!");
    }

    // Arani
    // Friendships methods
    public Result friendships() {
        StringBuilder list = new StringBuilder();
        list.append("FriendShips:\n");
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (App.getCurrentGame().getCurrentPlayer().getFriendShips().get(player) == null)
                continue;

            list.append("\t").append(player.getUser().getUsername()).append(": \n");
            list.append("\t\tLevel: ").append(App.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).first()).append("\n");
            list.append("\t\tValue: ").append(App.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).second()).append("\n");
        }
        return new Result(true, list.toString());
    }

    public Result talk(String username, String message) {
        username = username.trim();
        message = message.trim();

        Player player = App.getCurrentGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (App.getCurrentGame().getCurrentPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " for talking!");

        player.getTalkHistory().add(new Pair<>(
                App.getCurrentGame().getCurrentPlayer(),
                dateTime().message() + ": " + message
        ));

        App.getCurrentGame().getCurrentPlayer().getTalkHistory().add(new Pair<>(
                player,
                dateTime().message() + ": " + message
        ));

        if (App.getCurrentGame().getCurrentPlayer().getIsInteracted().get(player).equals(false)) {

            // If they are couple
            if (App.getCurrentGame().getCurrentPlayer().getMarried() == player) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                System.out.println("You and your partner got 50 extra energy!");
            } else {
                player.getFriendShips().computeIfPresent(App.getCurrentGame().getCurrentPlayer(),
                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 20));
                App.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(player,
                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 20));

                player.updateFriendShips(App.getCurrentGame().getCurrentPlayer());
                App.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
                System.out.println("Your friendship value with " + username + " is increased to " +
                        App.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).second());
            }

            App.getCurrentGame().getCurrentPlayer().getIsInteracted().put(player, true);
            player.getIsInteracted().put(App.getCurrentGame().getCurrentPlayer(), true);
        }

        return new Result(true, "You talked with " + username + "!");
    }

    public Result talkHistory(String username) {
        username = username.trim();

        StringBuilder list = new StringBuilder();
        list.append("Talk History:\n");
        for (Pair<Player, String> talk : App.getCurrentGame().getCurrentPlayer().getTalkHistory()) {
            if (talk.first().getPlayerUsername().equals(username)) {
                list.append("\t<").append(talk.first().getUser().getUsername()).append("> ")
                        .append(talk.second()).append("\n");
            }
        }

        return new Result(true, list.toString());
    }

    public Result gift(String username, String item, String amount) {
        username = username.trim();
        amount = amount.trim();

        Player player = App.getCurrentGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (App.getCurrentGame().getCurrentPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " for talking!");

        if (App.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).first() < 1)
            return new Result(false, "Your friendship level with " + username + " should be more than 0");

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(item);
        if (goods == null)
            return new Result(false, "Your don't have " + item + " in your inventory!");
        if (goods.size() < Integer.parseInt(amount))
            return new Result(false, "Your don't have enough number of " + item + " in your inventory!");

        int number = Integer.parseInt(amount);
        ArrayList<Good> giftGoods = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            giftGoods.add(goods.getLast());
            goods.removeLast();
        }

        Gift gift = new Gift(giftGoods);
        player.getGiftList().add(new Pair<>(App.getCurrentGame().getCurrentPlayer(), gift));
        player.getNews().add("A new gift has been added to your gift list from " + username + "!");

        return new Result(true, "Your gift has been sent to " + username + "!");
    }

    public Result giftList() {
        StringBuilder list = new StringBuilder();
        list.append("Gifts List:\n");
        int ptr = 1;
        for (Pair<Player, Gift> playerGiftPair : App.getCurrentGame().getCurrentPlayer().getGiftList()) {
            list.append("\t").append(ptr).append(". From <").append(playerGiftPair.first().getUser().getUsername()).append("> Good Name: ").
                    append(playerGiftPair.second().getList().getFirst().getName()).append(", Amount : ").
                    append(playerGiftPair.second().getList().size()).append("\n");
            ptr++;
        }

        return new Result(true, list.toString());
    }

    public Result giftRate(String giftNum, String rate) {
        giftNum = giftNum.trim();
        rate = rate.trim();

        int giftRate = Integer.parseInt(rate);
        int giftNumber = Integer.parseInt(giftNum);

        if (giftRate < 1 || giftRate > 5)
            return new Result(false, "Your gift rate should be an integer from 1 to 5!");
        if (giftNumber <= 0 || giftNumber > App.getCurrentGame().getCurrentPlayer().getGiftList().size())
            return new Result(false, "There is no gift with that number in your gift list!");

        giftNumber--;
        Pair<Player, Gift> gift = App.getCurrentGame().getCurrentPlayer().getGiftList().get(giftNumber);
        App.getCurrentGame().getCurrentPlayer().getGiftList().remove(gift);
        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(gift.second().getList());
        App.getCurrentGame().getCurrentPlayer().getGiftHistory().add(new Pair<>(gift.first(),
                "A gift from " + gift.first().getUser().getUsername()
                        + " with " + gift.second().getList().size() + " amount of " + gift.second().getList().getFirst().getName() +
                        " have been given to you! Your rate : " + giftRate + " !"));

        gift.first().getGiftHistory().add(new Pair<>(App.getCurrentGame().getCurrentPlayer(),
                "A gift with " + gift.second().getList().size() +
                        " amount of " + gift.second().getList().getFirst().getName() +
                        " have been given to " + App.getCurrentGame().getCurrentPlayer().getUser().getUsername() + " from you! " +
                        App.getCurrentGame().getCurrentPlayer().getUser().getUsername() + "'s rate : " + giftRate + " !"));
        gift.first().getNews().add(App.getCurrentGame().getCurrentPlayer().getUser().getUsername() + " has rated your gift with amount " +
                giftRate + " !");

        if (App.getCurrentGame().getCurrentPlayer().getIsInteracted().get(gift.first()).equals(false)) {
            if (App.getCurrentGame().getCurrentPlayer().getMarried() == gift.first()) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(50);
                gift.first().getEnergy().increaseTurnEnergyLeft(50);

                System.out.println("You and your partner got 50 extra energy!");
            } else {
                int value = (giftRate - 3) * 30 + 15;
                App.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(gift.first(),
                        (k, pair) -> new Pair<>(pair.first(), pair.second() + value));
                gift.first().getFriendShips().computeIfPresent(App.getCurrentGame().getCurrentPlayer(),
                        (k, pair) -> new Pair<>(pair.first(), pair.second() + value));

                gift.first().updateFriendShips(App.getCurrentGame().getCurrentPlayer());
                App.getCurrentGame().getCurrentPlayer().updateFriendShips(gift.first());
                System.out.println("Your friendship value with " + gift.first().getUser().getUsername() + " is increased to " +
                        App.getCurrentGame().getCurrentPlayer().getFriendShips().get(gift.first()).second());
            }

            App.getCurrentGame().getCurrentPlayer().getIsInteracted().put(gift.first(), true);
            gift.first().getIsInteracted().put(App.getCurrentGame().getCurrentPlayer(), true);

        }


        return new Result(true, "Your have rated gift with number " + giftNum + " with rate " + rate + "!");
    }

    public Result giftHistory(String username) {
        username = username.trim();

        StringBuilder list = new StringBuilder();
        list.append("Gifts History:\n");
        int ptr = 1;
        for (Pair<Player, String> giftHistory : App.getCurrentGame().getCurrentPlayer().getGiftHistory()) {
            if (giftHistory.first().getUser().getUsername().equals(username)) {
                list.append("\t").append(ptr++).append(". ").append(giftHistory.second()).append("\n");
            }
        }

        return new Result(true, list.toString());
    }

    public Result hug(String username) {
        username = username.trim();

        Player player = App.getCurrentGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (App.getCurrentGame().getCurrentPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " to hug!");


        if (!App.getCurrentGame().getCurrentPlayer().getIsInteracted().get(player)) {
            if (App.getCurrentGame().getCurrentPlayer().getMarried() == player) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                System.out.println("You and your partner got 50 extra energy!");
            } else {
                App.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(player,
                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 60));
                player.getFriendShips().computeIfPresent(App.getCurrentGame().getCurrentPlayer(),
                        (k, pair) -> new Pair<>(pair.first(), pair.second() + 60));

                player.updateFriendShips(App.getCurrentGame().getCurrentPlayer());
                App.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
                System.out.println("Your friendship value with " + username + " is increased to " +
                        App.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).second());
            }

            App.getCurrentGame().getCurrentPlayer().getIsInteracted().put(player, true);
            player.getIsInteracted().put(App.getCurrentGame().getCurrentPlayer(), true);

        }

        return new Result(true, "You hugged " + username + "!");
    }

    public Result flower(String username) {
        username = username.trim();

        Player player = App.getCurrentGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (App.getCurrentGame().getCurrentPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " to give flower!");

        if (App.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).first() != 2 ||
                player.getFriendShips().get(App.getCurrentGame().getCurrentPlayer()).first() != 2)
            return new Result(false, "You and " + username + " should have friendship level of 2!");

        boolean flag = false;
        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(FarmingCropType.SUNFLOWER);
        if (goods != null) {
            Good good = goods.getLast();
            goods.removeLast();
            player.getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
            flag = true;
        }

        goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(FarmingCropType.CAULIFLOWER);
        if (!flag && goods != null) {
            Good good = goods.getLast();
            goods.removeLast();
            player.getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
            flag = true;
        }

        if (!flag)
            return new Result(false, "Your don't have any flower in your inventory to give to someone!");
        else if (!App.getCurrentGame().getCurrentPlayer().getIsInteracted().get(player)) {
            if (App.getCurrentGame().getCurrentPlayer().getMarried() == player) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                System.out.println("You and your partner got 50 extra energy!");
            } else {
                App.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(player,
                        (k, pair) -> new Pair<>(pair.first() + 1, pair.second()));
                player.getFriendShips().computeIfPresent(App.getCurrentGame().getCurrentPlayer(),
                        (k, pair) -> new Pair<>(pair.first() + 1, pair.second()));

                player.updateFriendShips(App.getCurrentGame().getCurrentPlayer());
                App.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
                System.out.println("Your friendship level with " + username + " has been increased to 3!");

            }

            App.getCurrentGame().getCurrentPlayer().getIsInteracted().put(player, true);
            player.getIsInteracted().put(App.getCurrentGame().getCurrentPlayer(), true);
        }

        return new Result(true, "Your have given a flower to " + username + "!");
    }

    public Result askMarriage(String username, String ring) {
        username = username.trim();
        ring = ring.trim();

        Player player = App.getCurrentGame().findPlayer(username);
        Player mainPlayer = App.getCurrentGame().getCurrentPlayer();

        //Errors
        if (player == null)
            return new Result(false, "Player not found!");
        if (mainPlayer.getUser().getGender() == Gender.FEMALE)
            return new Result(false, "Your gender should be male to ask marriage!");
        if (mainPlayer.getFriendShips().get(player).first() != 3 ||
                player.getFriendShips().get(mainPlayer).first() != 3)
            return new Result(false, "You and " + username + " should have friendship level of 3!");
        if (mainPlayer.getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " to ask marriage!");
        if (mainPlayer.getUser().getGender() == player.getUser().getGender())
            return new Result(false, "Your gender should be opposite to " + username + " to ask marriage!");
        if (player.getMarriageList().get(mainPlayer) != null)
            return new Result(false, "Your marriage is already in progress!");
        if (player.getMarried() != null)
            return new Result(false, username + " is already married with " +
                    player.getMarried().getUser().getUsername() + "!");

        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ProductType.WEDDING_RING);
        if (goods == null)
            return new Result(false, "Your should have wedding ring in your inventory to ask marriage!");

        player.getNews().add(mainPlayer.getUser().getUsername() + " asks you to marry him with " + ring + "!");
        player.getMarriageList().put(mainPlayer, goods.getLast());
        goods.removeLast();

        return new Result(true, "Your have asked marriage from " + username + " with " + ring + "!");
    }

    public Result respond(String status, String username) {
        status = status.trim();
        username = username.trim();

        Player player = App.getCurrentGame().findPlayer(username);
        Player mainPlayer = App.getCurrentGame().getCurrentPlayer();
        if (player == null)
            return new Result(false, "Player not found!");
        if (mainPlayer.getUser().getGender() == Gender.MALE)
            return new Result(false, "Your gender should be female to respond!");
        if (mainPlayer.getMarriageList().get(player) == null)
            return new Result(false, username + " has not been asked you to marry him!");

        if (status.matches("\\s*-accept\\s*")) {
            mainPlayer.getInventory().addGood(new ArrayList<>(Arrays.asList(mainPlayer.getMarriageList().get(player))));

            // Friendship level increased to 4
            mainPlayer.getFriendShips().computeIfPresent(player,
                    (k, pair) -> new Pair<>(4, pair.second()));
            player.getFriendShips().computeIfPresent(mainPlayer,
                    (k, pair) -> new Pair<>(4, pair.second()));

            player.updateFriendShips(App.getCurrentGame().getCurrentPlayer());
            App.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
            // The Wallets are shared!
            mainPlayer.getWallet().increaseBalance(player.getWallet().getBalance());
            player.setWallet(mainPlayer.getWallet());

            // Now they are married
            mainPlayer.setMarried(player);
            player.setMarried(mainPlayer);

            player.getNews().add(mainPlayer.getUser().getUsername() + " has accepted your marriage! Now you can live with her!");
            mainPlayer.getMarriageList().remove(player);

            for (Player gamePlayer : App.getCurrentGame().getPlayers()) {
                if (mainPlayer.getMarriageList().get(gamePlayer) != null) {
                    gamePlayer.getInventory().addGood(new ArrayList<>(Arrays.asList(mainPlayer.getMarriageList().get(gamePlayer))));
                    gamePlayer.getNews().add(mainPlayer.getUser().getUsername() + " has rejected your marriage and married with " + player.getUser().getUsername() + "!");
                    gamePlayer.setBuff(new Buff(BuffType.REJECT_BUFF, 7, 100));
                }
            }
            mainPlayer.getMarriageList().clear();
            return new Result(true, "Your have accepted your marriage from " + username + "! Now you can live with him!");
        } else if (status.matches("\\s*-reject\\s*")) {
            mainPlayer.getFriendShips().computeIfPresent(player,
                    (k, pair) -> new Pair<>(0, pair.second()));
            player.getFriendShips().computeIfPresent(mainPlayer,
                    (k, pair) -> new Pair<>(0, pair.second()));

            player.updateFriendShips(App.getCurrentGame().getCurrentPlayer());
            App.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
            player.setBuff(new Buff(BuffType.REJECT_BUFF, 7, 100));
            player.getInventory().addGood(new ArrayList<>(Arrays.asList(mainPlayer.getMarriageList().get(player))));
            player.getNews().add(mainPlayer.getUser().getUsername() + " has rejected your marriage!");
            mainPlayer.getFriendShips().remove(player);
            return new Result(true, "You have rejected your marriage from " + username + "!");
        } else
            return new Result(false, "Invalid respond to marriage ask!");
    }


    // Parsa
    // Trading methods
    public Result startTrade() {
        App.setCurrentMenu(Menu.TradeMenu);

        System.out.println("Players: ");
        for (Player player : App.getCurrentGame().getPlayers()) {
            System.out.println(player.getUser().getUsername());
        }
        System.out.println("____________________________");
        System.out.println("You are now in Trade Menu!");

        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        List<Trade> trades = TradeManager.getTradesFor(currentPlayer);

        if (trades.isEmpty()) {
            return new Result(true, "No trades found.");
        }
        boolean hasSomething = false;
        for (Trade trade : trades) {
            if (!trade.isShown()) {
                hasSomething = true;
                trade.setShown(true);
                System.out.println(trade);
            }
        }
        if (!hasSomething) {
            System.out.println("No new Trade found.!");
        }

        return new Result(true, "");
    }

    // Nader
    // NPC methods
    public Result meetNPC(String npcName) {
        npcName = npcName.trim();

        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                if (abs(npc.getType().getCoordinate().getX() -
                        App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()) == 1 &&
                        abs(npc.getType().getCoordinate().getY() -
                                App.getCurrentGame().getCurrentPlayer().getCoordinate().getY()) == 1) {
                    npc.getFriendship();
                    npc.npcDialogs();
                    return new Result(true, "");
                }
            }
        }
        return new Result(true, "Too far away. Approach the NPC to speak.");
    }

    public Result giftNPC(String npcName, String itemName) {
        npcName = npcName.trim();
        itemName = itemName.trim();

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
            if (npc.getType().getName().equals("Harvey") ||
                    npc.getType().getName().equals("Abigail") ||
                    npc.getType().getName().equals("Leah") ||
                    npc.getType().getName().equals("Robin") ||
                    npc.getType().getName().equals("Sebastian")) {

                System.out.println(npc.getType().getName());
                if (npc.getFriendship().getAvailableQuests().contains(1)) {
                    System.out.println(npc.getType().getRequests().getFirst().first().getName() + " Count: " +
                            npc.getType().getRequests().getFirst().second());
                }
                if (npc.getFriendship().getAvailableQuests().contains(2)) {
                    System.out.println(npc.getType().getRequests().get(1).first().getName() + " " +
                            npc.getType().getRequests().get(1).second());
                }
                if (npc.getFriendship().getAvailableQuests().contains(3)) {
                    System.out.println(npc.getType().getRequests().get(2).first().getName() + " " +
                            npc.getType().getRequests().get(2).second());
                }
                System.out.println("-----------------");

            }

        }

        return new Result(true, "------------------------------");
    }

    public Result questsFinish(String index) {
        index = index.trim();

        int indexInt = Integer.parseInt(index);
        NPC targetNPC = null;
        boolean found = false;
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (abs(npc.getType().getCoordinate().getX() -
                    App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()) == 1 &&
                    abs(npc.getType().getCoordinate().getY() -
                            App.getCurrentGame().getCurrentPlayer().getCoordinate().getY()) == 1) {

            }
        }
        if (!found) {
            return new Result(true, "Too far away. Approach the NPC to speak.");
        }
        targetNPC.finishQuest(indexInt);
        //  no next line
        return new Result(true, "");
    }

    public Result showCurrentMenu() {
        return new Result(true, "Current Menu : Game Menu");
    }


    //Additional Functions
    public Result showPlayerCoordinate() {
        return new Result(true, "Coordinate: " +
                App.getCurrentGame().getCurrentPlayer().getCoordinate().getX() + ", " +
                " " + App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
    }

    public Result showBalance() {
        return new Result(true, "Balance: " + App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() + " g");
    }

    public Result test() {
        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(Good.newGood(Good.newGoodType("Omelet")), 12);

        GoodType goodType = Good.newGoodType("Omelet");
        ArrayList<Good> newGoods = Good.newGoods(goodType, 12);
        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(newGoods);

        return new Result(true, "test");
    }
}
