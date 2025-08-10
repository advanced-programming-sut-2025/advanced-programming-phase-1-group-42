package com.StardewValley.server.controllers;

import com.StardewValley.client.Main;
import com.StardewValley.client.views.GameMenu;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Message;
import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.builders.Director;
import com.StardewValley.models.builders.concrete_builders.WholeGameBuilder;
import com.StardewValley.models.builders.concrete_builders.WholeMapBuilder;
import com.StardewValley.models.enums.*;
import com.StardewValley.models.game_structure.*;
import com.StardewValley.models.game_structure.weathers.Weather;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.artisans.Artisan;
import com.StardewValley.models.goods.artisans.ArtisanFunctions;
import com.StardewValley.models.goods.farmings.*;
import com.StardewValley.models.goods.foods.Food;
import com.StardewValley.models.goods.foragings.*;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.goods.recipes.*;
import com.StardewValley.models.goods.tools.Tool;
import com.StardewValley.models.goods.tools.ToolFunctions;
import com.StardewValley.models.goods.tools.ToolType;
import com.StardewValley.models.interactions.Animals.Animal;
import com.StardewValley.models.interactions.Animals.AnimalProduct;
import com.StardewValley.models.interactions.Animals.AnimalTypes;
import com.StardewValley.models.interactions.Gender;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuilding;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuildingTypes;
import com.StardewValley.models.interactions.User;
import com.StardewValley.models.interactions.game_buildings.*;
import com.StardewValley.client.views.GameMenuView;
import com.StardewValley.client.views.GameView;
import com.StardewValley.client.views.MainMenuView;
import com.StardewValley.server.AppServer;
import com.StardewValley.server.ClientHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.ServerApi;
//import com.mongodb.ServerApiVersion;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.model.Updates;
//import com.mongodb.client.result.UpdateResult;
//import org.bson.Document;
//import org.bson.conversions.Bson;



import java.util.*;
import java.util.List;

import static java.lang.Math.abs;

public class GameController extends Controller {
    private ClientHandler clientHandler;

    private CookingController cookingController;
    private CraftingController craftingController;
    private TradeMenuController tradeController;
    private boolean isControllerEnded = false;

    public GameController(ClientHandler clientHandler) {
        cookingController = new CookingController();
        craftingController = new CraftingController(clientHandler);
        tradeController = new TradeMenuController();

        this.clientHandler = clientHandler;

        Thread timeFlowThread = new Thread(() -> {
            while(!isControllerEnded) {
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                clientHandler.getClientGame().getDateTime().timeFlow(clientHandler);
            }
        });
        timeFlowThread.start();
    }

    @Override
    public Message handleMessage(Message message) {
        if (message.getType() == Message.Type.command) {
            switch ((String) message.getFromBody("function")) {
                case "removeItemsFromFridge" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    clientHandler.getClientPlayer().getFridge().removeItemsFromFridge(
                            Good.newGoodType(arguments.get(0)), Integer.parseInt(arguments.get(1))
                    );
                    return sendResultMessage(new Result(true, ""));
                }
                case "addGoodByObject" -> {
                    String arguments = message.getFromBody("arguments");
                    clientHandler.getClientPlayer().getInventory().addGoodByObject(
                            Good.newGood(Good.newGoodType(arguments)),
                            clientHandler.getClientGame(),
                            clientHandler.getClientPlayer()
                    );
                    return sendResultMessage(new Result(true, ""));
                }
                case "cheat" -> {
                    GameMenu gameMenu = new GameMenu(this);
                    gameMenu.check(message.getFromBody("arguments"));
                    return sendResultMessage(new Result(true, ""));
                }
                case "toolsUse" -> {
                    Coordinate coordinate = Coordinate.fromString(message.getFromBody("arguments"));
                    return sendResultMessage(toolsUse(coordinate));
                }
                case "sellAnimal" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(sellAnimal(name));
                }
                case "feedHay" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(feedHay(name));
                }
                case "collectProduct" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(collectProduct(name));
                }
                case "buyAnimal" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(buyAnimal(arguments.get(0), arguments.get(1)));
                }
                case "purchase" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(purchase(arguments.get(0), arguments.get(1), Coordinate.fromString(arguments.get(2))));
                }
                case "toolsUpgrade" -> {
                    String toolName = message.getFromBody("arguments");
                    return sendResultMessage(toolsUpgrade(toolName));
                }
                case "meetNPC" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(meetNPC(name));
                }
                case "isCloseEnough" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(isCloseEnough(name));
                }
                case "giftNPC" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(giftNPC(arguments.get(0), arguments.get(1)));
                }
                case "getQuests" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(new Result(true, getQuests(name)));
                }
                case "getUnlockedRecipes" -> {
                    String controller = message.getFromBody("arguments");
                    if (controller.equals("CookingController")) {
                        ArrayList<CookingRecipeType> recipeTypes = cookingController.getUnlockedRecipes(clientHandler);
                        return new Message(new HashMap<>() {{
                            put("success", true);
                            put("message", recipeTypes);
                        }}, Message.Type.response);
                    }
                    if (controller.equals("CraftingController")) {
                        ArrayList<CraftingRecipeType> recipeTypes = craftingController.getUnlockedRecipes();
                        return new Message(new HashMap<>() {{
                            put("success", true);
                            put("message", recipeTypes);
                        }}, Message.Type.response);
                    }
                }
                case "getAllRecipes" -> {
                    String controller = message.getFromBody("arguments");
                    if (controller.equals("CookingController")) {
                        CookingRecipeType[] cookingRecipeTypes = cookingController.getAllRecipes();
                        return new Message(new HashMap<>() {{
                            put("success", true);
                            put("message", cookingRecipeTypes);
                        }}, Message.Type.response);
                    }
                    if (controller.equals("CraftingController")) {
                        CraftingRecipeType[] craftingRecipeTypes = craftingController.getAllRecipes();
                        return new Message(new HashMap<>() {{
                            put("success", true);
                            put("message", craftingRecipeTypes);
                        }}, Message.Type.response);
                    }
                }
                case "cookingPrepare" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(cookingPrepare(name));
                }
                case "craftingCraft" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(craftingCraft(name));
                }
                case "greenHouseBuild" -> {
                    return sendResultMessage(greenHouseBuild());
                }
                case "tradeWithGoods" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(tradeController.tradeWithGoods(
                        arguments.get(0),
                        arguments.get(1),
                        arguments.get(2),
                        arguments.get(3),
                        arguments.get(4),
                        arguments.get(5),
                        clientHandler
                    ));
                }
                case "tradeWithMoney" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(tradeController.tradeWithMoney(
                        arguments.get(0),
                        arguments.get(1),
                        arguments.get(2),
                        arguments.get(3),
                        arguments.get(4),
                        clientHandler
                    ));
                }
                case "tradeResponse" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(tradeController.tradeResponse(
                        arguments.get(0),
                        arguments.get(1),
                        clientHandler
                    ));
                }
                case "gift" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(gift(arguments.get(0), arguments.get(1), arguments.get(2)));
                }
                case "giftRate" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(giftRate(arguments.get(0), arguments.get(1)));
                }
                case "hug" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(hug(name));
                }
                case "flower" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(flower(name));
                }
                case "askMarriage" -> {
                    String name = message.getFromBody("arguments");
                    return sendResultMessage(askMarriage(name));
                }
                case "respond" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(respond(arguments.get(0), arguments.get(1)));
                }
                case "artisanUse" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(artisanUse(
                        arguments.get(0),
                        arguments.get(1),
                        arguments.get(1)
                    ));
                }
                case "sell" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    return sendResultMessage(sell(arguments.get(0), arguments.get(1)));
                }
                case "addItemToFridge" -> {
                    String ptr = message.getFromBody("arguments");
                    clientHandler.getClientPlayer().getFridge().addItemToFridge(
                        clientHandler.getClientPlayer().getInventory().getList().get(Integer.parseInt(ptr)).getFirst()
                    );
                    return sendResultMessage(new Result(true, ""));
                }
                case "setInHandGood" -> {
                    String ptr = message.getFromBody("arguments");
                    clientHandler.getClientPlayer().setInHandGood(
                        clientHandler.getClientPlayer().getInventory().getList().get(Integer.parseInt(ptr))
                    );
                    return sendResultMessage(new Result(true, ""));
                }
                case "setPlayerDirection" -> {
                    String ptr = message.getFromBody("arguments");
                    clientHandler.getClientPlayer().setPlayerDirection(Integer.parseInt(ptr));
                    return sendResultMessage(new Result(true, ""));
                }
                case "increaseBalance" -> {
                    String ptr = message.getFromBody("arguments");
                    clientHandler.getClientPlayer().getWallet().increaseBalance(Integer.parseInt(ptr));
                    return sendResultMessage(new Result(true, ""));
                }
                case "petAnimal" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    boolean flag = false;
                    for (FarmBuilding farmBuilding : clientHandler.getClientPlayer().getFarm().getFarmBuildings()) {
                        if (farmBuilding.getName().equals(arguments.get(1))) {
                            for (Animal animal : farmBuilding.getAnimals()) {
                                if (animal.getName().equals(arguments.get(0))) {
                                    flag = true;
                                    animal.petAnimal();
                                    break;
                                }
                            }
                        }
                        if (flag)
                            break;
                    }
                    return sendResultMessage(new Result(flag, ""));
                }
                case "decreaseTurnEnergyLeft" -> {
                    String ptr = message.getFromBody("arguments");
                    clientHandler.getClientPlayer().getEnergy().decreaseTurnEnergyLeft(Double.parseDouble(ptr), clientHandler);
                    return sendResultMessage(new Result(true, ""));
                }
                case "setCoordinate" -> {
                    String coordinate = message.getFromBody("arguments");
                    clientHandler.getClientPlayer().setCoordinate(Coordinate.fromString(coordinate));
                    return sendResultMessage(new Result(true, ""));
                }
                case "setLastCoordinate" -> {
                    String coordinate = message.getFromBody("arguments");
                    clientHandler.getClientPlayer().setLastCoordinate(Coordinate.fromString(coordinate));
                    return sendResultMessage(new Result(true, ""));
                }
                case "useTrashCan" -> {
                    String price = message.getFromBody("arguments");
                    int finalPrice = ToolFunctions.useTrashCan(
                        clientHandler.getClientPlayer().getTrashCan(), Integer.parseInt(price)
                    );
                    return sendResultMessage(new Result(true, String.valueOf(finalPrice)));
                }
                case "getUpdatedGame" -> {
                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", clientHandler.getClientGame());
                    }}, Message.Type.response);
                }
            }
        }

        return new Message(new HashMap<>() {{
            put("success", false);
            put("message", "");
        }}, Message.Type.response);
    }

    public Message sendResultMessage(Result result) {
        return new Message(new HashMap<>() {{
            put("success", result.success());
            put("message", result.message());
        }}, Message.Type.response);
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

        for (FarmingTreeSaplingType value : FarmingTreeSaplingType.values()) {
            if (value.getName().equals(craftName)) {
                return value;
            }
        }


        return null;
    }

    public Result forceTerminate() {
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<Boolean> poll = new ArrayList<>();
//        poll.add(true);
//        for (int i = 1; i < clientHandler.getClientGame().getPlayers().size(); i++) {
//            System.out.println(clientHandler.getClientGame().getPlayers().get(i).getUser().getUsername() +
//                ", Please give your vote to terminate the game: (y/n)");
//
//            String input = scanner.nextLine();
//            if (input.matches("\\s*y\\s*"))
//                poll.add(true);
//            else
//                poll.add(false);
//        }
//
//        for (Boolean p : poll) {
//            if (!p)
//                return new Result(false, "All players do not agree to terminate the game!");
//        }

//        for (Player player : clientHandler.getClientGame().getPlayers()) {
//            player.setPlaying(false);
//            player.increaseEarnedPoints(player.getPoints());
//            player.maxMaxPoints(player.getPoints());
//            player.increaseGamePlay(clientHandler.getClientGame().getDateTime().getDays());
//        }
        //TODO

        AppServer.getGames().remove(clientHandler.getClientGame());
//        String connectionString = "mongodb+srv://namoder123:passme@cluster01.unmuffl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster01";
//
//        ServerApi serverApi = ServerApi.builder()
//                .version(ServerApiVersion.V1)
//                .build();
//
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(connectionString))
//                .serverApi(serverApi)
//                .build();
//
//        try (MongoClient mongoClient = MongoClients.create(settings)) {
//            MongoDatabase database = mongoClient.getDatabase("Game");
//            MongoCollection<Document> collection = database.getCollection("USERS");
//
//            Bson filter ;
//            for (Player player : clientHandler.getClientGame().getPlayers()) {
//                filter = Filters.eq("username",player.getUser().getUsername());
//                Bson update = Updates.set("setPlaying", false);
//                UpdateResult result = collection.updateOne(filter, update);
//
//            }
//        } catch (Exception e) {
//            System.out.println("Error while setting is Playing  user.");
//        }
        clientHandler.setClientGame(null);
//        Main.getMain().getScreen().dispose();
//        Main.getMain().setScreen(new MainMenuView(Assets.getInstance().getSkin()));
        return new Result(true, "Game terminated successfully!");

    }


    // Nader
    // date & time methods
    public Result time() {
        return new Result(true, clientHandler.getClientGame().getDateTime().getTime() + ":00");
    }

    public Result date() {
        return new Result(true,
            clientHandler.getClientGame().getDateTime().getYear() + "/" +
                clientHandler.getClientGame().getDateTime().getSeasonOfYearInt() + "/" +
                clientHandler.getClientGame().getDateTime().getDayOfSeason());
    }

    public Result dateTime() {
        return new Result(true,
            clientHandler.getClientGame().getDateTime().getYear() + "/" +
                clientHandler.getClientGame().getDateTime().getSeasonOfYearInt() + "/" +
                clientHandler.getClientGame().getDateTime().getDayOfSeason() + "/" +
                clientHandler.getClientGame().getDateTime().getTime() + ":00");
    }

    public Result dayOfTheWeek() {
        return new Result(true, clientHandler.getClientGame()
            .getDateTime().getDayOfWeek());
    }

    public Result showSeason() {
        return new Result(true, clientHandler.getClientGame().getDateTime()
            .getSeasonOfYear().getName());
    }

    public Result cheatAdvanceTime(String hour) {
        int hourInt = Integer.parseInt(hour);
        for (int i = 0; i < hourInt; i++) {
            clientHandler.getClientGame().getDateTime().timeFlow(clientHandler);
        }
        return new Result(true, "You have cheat advance time for " + hourInt + " hours!");
    }

    public Result cheatAdvanceDate(String date) {
        int dateInt = Integer.parseInt(date);
        for (int i = 0; i < dateInt * 13; i++) {
            clientHandler.getClientGame().getDateTime().timeFlow(clientHandler);
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
        // TODO parsa
//        clientHandler.getClientGame().getWeather().thunder(xInt, yInt, clientHandler);
        return new Result(true, "");
    }

    public Result weather() {
        return new Result(true, clientHandler.getClientGame().getWeatherName());
    }

    public Result weatherForecast() {
        return new Result(true, clientHandler.getClientGame().getTomorrow().weatherForecast().getName());
    }

    public Result cheatWeatherSet(String weather) {
        weather = weather.trim();

        switch (weather) {
            case "Sunny":
                clientHandler.getClientGame().cheatSetWeather(WeatherType.Sunny.getWeather());
                break;
            case "Rain":
                clientHandler.getClientGame().cheatSetWeather(WeatherType.Rain.getWeather());
                break;
            case "Storm":
                clientHandler.getClientGame().cheatSetWeather(WeatherType.Storm.getWeather());
                break;
            case "Snow":
                clientHandler.getClientGame().cheatSetWeather(WeatherType.Snow.getWeather());
                break;
        }
        return new Result(true, "Weather set to " + weather);
    }

    public Result greenHouseBuild() {
        Game game = clientHandler.getClientGame();
        if (clientHandler.getClientPlayer().getWallet().getBalance() < 1000) {
            return new Result(false, "You have not enough money to build green house!");
        }

        boolean flag = false;
        for (ArrayList<Good> goods : clientHandler.getClientPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getFirst().getName().equals("Wood") && Inventory.decreaseGoods(goods, 500)) {
                flag = true;
                break;
            }
        }
        if (!flag)
            return new Result(false, "You have not enough Wood to build green house!");
        clientHandler.getClientPlayer().getWallet().decreaseBalance(1000);

        clientHandler.getClientPlayer().getFarm().getGreenHouse().setAvailable(true);
        return new Result(true, "Your green house has been successfully built!");
    }


    //Parsa
    //Map methods
    public Result walk(String x, String y) {
        Scanner scanner = new Scanner(System.in);
        x = x.trim();
        y = y.trim();

        if (!x.matches("-?\\d+") || !y.matches("-?\\d+"))
            return new Result(false, "Invalid Coordinate input!");

        Coordinate goal = new Coordinate(Integer.parseInt(x), Integer.parseInt(y));
        Tile tile = clientHandler.getClientGame().getMap().findTile(goal, clientHandler.getClientGame());
        if (tile == null)
            return new Result(false, "Goal tile not found!");

        if (tile.getTileType() == TileType.GAME_BUILDING &&
            !clientHandler.getClientGame().getMap().findGameBuilding(goal).isInWorkingHours(clientHandler)) {
            return new Result(false, clientHandler.getClientGame().getMap().findGameBuilding(goal).getName() + " hours have ended for today!");
        }


        ArrayList<Pair<Integer, Coordinate>> path = AStar.findPath(clientHandler.getClientGame().getMap(),
            clientHandler.getClientPlayer().getCoordinate(), goal);

        if (path == null)
            return new Result(false, "No path " + goal + " found!");

        int energyConsumed = path.getLast().first() / 20;
        int playerEnergy = clientHandler.getClientPlayer().getEnergy().getDayEnergyLeft();
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
            clientHandler.getClientPlayer().setCoordinate(coordinate);
            clientHandler.getClientPlayer().getEnergy().decreaseTurnEnergyLeft(playerEnergy, clientHandler);
            clientHandler.getClientPlayer().getEnergy().setAwake(false);
            return new Result(true, "Your energy was enough to walk to " + goal +
                " location! Now your are fainted & your daily energy is 0!");
        } else {
            clientHandler.getClientPlayer().setCoordinate(goal);
            clientHandler.getClientPlayer().getEnergy().decreaseTurnEnergyLeft(energyConsumed, clientHandler);
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
//        clientHandler.getClientGame().getMap().printMap(IntX, IntY, IntSize);
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
        if (clientHandler.
            getClientPlayer().getEnergy().getDayEnergyLeft() > 3000) {
            return new Result(true, ("INFINITE"));
        }
        return new Result(true, (clientHandler.getClientPlayer().
            getEnergy()).getDayEnergyLeft() + "\n");
    }

    public Result cheatEnergySet(String value) {
        value = value.trim();

        int valueInt = Integer.parseInt(value);
        clientHandler.getClientPlayer().getEnergy().setDayEnergyLeft(valueInt);
        clientHandler.getClientPlayer().getEnergy().setTurnValueLeft(50);
        return new Result(true, "Your energy been set to " + valueInt);
    }

    public Result cheatEnergyUnlimited() {
        clientHandler.getClientPlayer().getEnergy().setMaxDayEnergy(Integer.MAX_VALUE);
        clientHandler.getClientPlayer().getEnergy().setMaxTurnEnergy(Integer.MAX_VALUE);
        clientHandler.getClientPlayer().getEnergy().setTurnValueLeft(Integer.MAX_VALUE);
        clientHandler.getClientPlayer().getEnergy().setDayEnergyLeft(Integer.MAX_VALUE);
        return new Result(true, "Energy set to Unlimited");
    }

    public Result inventoryTrashItem(String itemName, String number) {
        itemName = itemName.trim();
        number = number.trim();

        ArrayList<Good> goods = clientHandler.getClientPlayer().getInventory().isInInventory(itemName);
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

        int finalPrice = ToolFunctions.useTrashCan(clientHandler.getClientPlayer().getTrashCan(), totalPrice);
        clientHandler.getClientPlayer().getWallet().increaseBalance(finalPrice);
        return new Result(true, "Your earned " + finalPrice + " coins from putting your good into trash can!");
    }

    public Result inventoryShow() {
        StringBuilder inventoryList = new StringBuilder();
        for (ArrayList<Good> good : clientHandler.getClientPlayer().getInventory().getList()) {
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
        int ptr = 0;
        for (ArrayList<Good> goods : clientHandler.getClientPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getFirst().getName().equals(toolName) && goods.size() >= 1) {
                clientHandler.getClientPlayer().setInHandGood(goods);
                flag = true;
                break;
            }
            if (!flag && toolName.equals("Trash_Can")) {
                clientHandler.getClientPlayer().setInHandGood(new ArrayList<>(Arrays.asList(
                    clientHandler.getClientPlayer().getTrashCan()
                )));
                flag = true;
            }
            ptr++;
        }
        if (!flag)
            return new Result(false, "You don't have " + toolName + " to use!");

        return new Result(true, "Now you can use " + toolName);
    }

    public Result toolsShowCurrent() {
        if (clientHandler.getClientPlayer().getInHandGood().getLast() instanceof Tool) {
            return new Result(true, "Your current tool: " + clientHandler.getClientPlayer().getInHandGood().getLast().getName());
        } else {
            return new Result(true, "You don't have tool in your hand!");
        }
    }

    public Result toolsShowAvailable() {
        StringBuilder toolsList = new StringBuilder();
        toolsList.append("List of available tools:\n");
        for (ArrayList<Good> goods : clientHandler.getClientPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getFirst() instanceof Tool) {
                toolsList.append("\t").append(goods.getFirst().getName()).append("\n");
            }
        }

        return new Result(true, toolsList.toString());
    }

    public Result toolsUpgrade(String toolName) {
        toolName = toolName.trim();

        Game game = clientHandler.getClientGame();
        if (!game.getMap().getBlackSmith().isInsideBuilding(clientHandler.getClientPlayer().getCoordinate()))
            return new Result(false, "You are not inside the BlackSmith Shop!");
        if (clientHandler.getClientPlayer().getInHandGood().getLast() instanceof Tool) {
            Blacksmith blacksmith = (Blacksmith) game.getMap().getBlackSmith();
            if (((ToolType) clientHandler.getClientPlayer().getInHandGood().getLast().getType()).getLevel().getLevelNumber() == 4)
                return new Result(true, "Your tool is already in the highest level!");

            if (blacksmith.upgradeTool((Tool) clientHandler.getClientPlayer().getInHandGood().getLast(), clientHandler)) {
                return new Result(false, "Your tool has successfully upgraded!");
            } else
                return new Result(false, "You don't have enough money & \ningredients to upgrade "
                    + clientHandler.getClientPlayer().getInHandGood().getLast().getName() + "!");
        } else
            return new Result(false, "You don't have tool in your hand!");
    }

    public Result toolsUse(Coordinate coordinate) {
        if (clientHandler.getClientPlayer().getInHandGood().getLast() instanceof Tool) {
            Tool tool = (Tool) clientHandler.getClientPlayer().getInHandGood().getLast();
            coordinate = new Coordinate(coordinate.getX() + clientHandler.getClientPlayer().getCoordinate().getX(),
                coordinate.getY() + clientHandler.getClientPlayer().getCoordinate().getY());
            if (coordinate == null)
                return new Result(false, "Direction not recognized");

            if (clientHandler.getClientPlayer().getEnergy().getDayEnergyLeft() < tool.getType().getEnergy())
                return new Result(false, "You don't have enough energy to use " + tool.getName() + "!");
            if (clientHandler.getClientGame().getMap().findTile(coordinate,
                clientHandler.getClientGame()) == null)
                return new Result(false, "Tile not found");

            clientHandler.getClientPlayer().getEnergy().decreaseTurnEnergyLeft(tool.getEnergy(
                clientHandler.getClientPlayer()), clientHandler);

            return ToolFunctions.tooluse(tool, coordinate, clientHandler);
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

        Coordinate coordinate = Coordinate.getDirection(direction, clientHandler.getClientPlayer());
        Tile tile = clientHandler.getClientPlayer().getFarm().checkInFarm(coordinate, clientHandler.getClientPlayer());
        if (tile == null)
            return new Result(false, "You don't have access to this tile!");

        if (tile.getTileType() != TileType.PLOWED_FARM && tile.getTileType() != TileType.GREEN_HOUSE)
            return new Result(false, "Selected Tile is not Plowed or GreenHouse for planting!");
        if (tile.getTileType().equals(TileType.GREEN_HOUSE)) {
            if (clientHandler.getClientPlayer().getFarm().getGreenHouse().isAvailable()) {
                return new Result(false, "You haven't built your Green House yet");
            }
        }

        ArrayList<Good> seeds = clientHandler.getClientPlayer().getInventory().isInInventory(seed);
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
                    if (season.equals(clientHandler.getClientGame().getDateTime().getSeasonOfYear())) {
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
        Tile tile = clientHandler.getClientPlayer().getFarm().checkInFarm(coordinate,
            clientHandler.getClientPlayer());

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

        Coordinate coordinate = Coordinate.getDirection(direction, clientHandler.getClientPlayer());
        Tile tile = clientHandler.getClientPlayer().getFarm().checkInFarm(coordinate,
            clientHandler.getClientPlayer());

        if (tile == null)
            return new Result(false, "You don't have access to this tile!");

        ArrayList<Good> fertilizer = clientHandler.getClientPlayer().getInventory().isInInventory(fertilizerName);
        if (fertilizer == null)
            return new Result(false, "You don't have " + fertilizerName + " in your inventory!");

        tile.getGoods().add(fertilizer.getLast());
        fertilizer.removeLast();

        boolean isThereAPlant = false;
        for (Good good : tile.getGoods()) {
            if (good instanceof FarmingTreeSapling) {
                isThereAPlant = true;
            } else if (good instanceof FarmingTree) {
                isThereAPlant = true;
            } else if (good instanceof ForagingSeed) {
                isThereAPlant = true;
            } else if (good instanceof ForagingTree) {
                isThereAPlant = true;
            } else if (good instanceof ForagingMixedSeed) {
                isThereAPlant = true;
            }
        }
        if (!isThereAPlant) {
            return new Result(false, "There is no plant in this location!");
        }
        return new Result(true, "You have fertilized tile in location " + coordinate + " with " + fertilizer.getFirst().getName() + "!");
    }

    public Result howMuchWater() {
        Tool tool = (Tool) clientHandler.getClientPlayer().getInventory().isInInventory(ToolType.WATERING_CAN.getName()).getFirst();
        return new Result(true, "Your watering can have capacity:" + tool.capacity);
    }


    // Nader
    // crafting methods
    public Result showCraftingRecipes() {

        for (CraftingRecipe craftingRecipe : clientHandler.getClientPlayer().getCraftingRecipes()) {
            System.out.println(craftingRecipe.getName());
            System.out.println("\t" + ((CraftingRecipeType) craftingRecipe.getType()).getIngredients());
            if (((CraftingRecipeType) craftingRecipe.getType()).getSource() != null) {
                System.out.println("\t" + ((CraftingRecipeType) craftingRecipe.getType()).getSource());
            }
            System.out.println("-------------------------------------------");
        }
        return new Result(true, "");
    }

    public Result craftingCraft(String itemName) {
        itemName = itemName.trim();
        for (CraftingRecipe craftingRecipe : clientHandler.getClientPlayer().getCraftingRecipes()) {
            if (craftingRecipe.getName().equals(itemName)) {
                CraftingFunctions craftingFunctions = new CraftingFunctions();
                Result result;
                result = craftingFunctions.checkCraftingFunctions((CraftingRecipeType) craftingRecipe.getType(),
                    clientHandler);
                return new Result(true, result.message());
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
        for (ArrayList<Good> goodArrayList : clientHandler.getClientPlayer().getInventory().getList()) {
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

        Coordinate coordinate = Coordinate.getDirection(direction, clientHandler.getClientPlayer());
        switch (coordinate) {
            case null:
                return new Result(false, "Direction not recognized");
            default:
                newX = coordinate.getX();
                newY = coordinate.getY();
        }

        for (Tile tile : clientHandler.getClientGame().getMap().getTiles()) {
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

        Player player = clientHandler.getClientPlayer();
        if (player.getInventory().isInInventory(goodType) == null && player.getInventory().isFull())
            return new Result(false, "Your inventory is full!\n");

        ArrayList<Good> newGoods = Good.newGoods(goodType, Integer.parseInt(count));
        player.getInventory().addGood(newGoods, clientHandler.getClientGame(), clientHandler.getClientPlayer());

        return new Result(true, "You have added (" + count + ") " + itemName + " to the inventory!\n");
    }


    // Nader
    // cooking methods
    public Result cookingRefrigerator(String status, String itemName) {

        status = status.trim();
        itemName = itemName.trim();

        TileType tileType = clientHandler.getClientGame().getMap().findTileType(clientHandler.getClientPlayer().getCoordinate());
        if (!tileType.equals(TileType.PLAYER_BUILDING)) {
            return new Result(false, "You are not in Home!");
        }

        Fridge fridge = clientHandler.getClientPlayer().getFridge();
        Inventory inventory = clientHandler.getClientPlayer().getInventory();
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
                boolean added = inventory.addGood(new ArrayList<>(Arrays.asList(good)), clientHandler.getClientGame(),
                    clientHandler.getClientPlayer());
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
        for (CookingRecipe cookingRecipe : clientHandler.getClientPlayer().getCookingRecipes()) {
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

        for (CookingRecipe cookingRecipe : clientHandler.getClientPlayer().getCookingRecipes()) {
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
            if (clientHandler.getClientPlayer().getInventory().howManyInInventoryByType(ingredientType) < requiredAmount) {
                return new Result(false, "Not enough " + ingredientType.getName() +
                    " (needed: " + requiredAmount + ")");
            }
        }

        for (Pair<GoodType, Integer> ingredient : recipe.getType().getIngredients()) {
            GoodType ingredientType = (GoodType) ingredient.first();
            int requiredAmount = ingredient.second();
            clientHandler.getClientPlayer().getInventory().removeItemsFromInventory(ingredientType, requiredAmount);
        }
        Good food = Good.newGood(recipe.getType().getGoodType());
        clientHandler.getClientPlayer().getSkill().increaseCookingPoints(10);
        clientHandler.getClientPlayer().getEnergy().decreaseTurnEnergyLeft(3, clientHandler);
        if (clientHandler.getClientPlayer().getInventory().addGood(Good.newGoods(recipe.getType().getGoodType(), 1),
            clientHandler.getClientGame(), clientHandler.getClientPlayer())) {
            return new Result(true, "You put " + food.getName() + " into the inventory");
        }
        return new Result(false, "Your inventory is full");

    }


    public Result eat(String foodName) {
        foodName = foodName.trim();
        Good food = null;
        for (ArrayList<Good> goodArrayList : clientHandler.getClientPlayer().getInventory().getList()) {
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
            clientHandler.getClientPlayer().eat(food, clientHandler);
        } else if (food instanceof Artisan) {
            clientHandler.getClientPlayer().eat(food, clientHandler);
        } else if (food instanceof Food) {
            clientHandler.getClientPlayer().eat(food, clientHandler);
        } else {
            return new Result(false, "NO NO! What are you trying to eat mmd Jan?");
        }
        return new Result(true, "Khosmaz, Yum Yum!");
    }


    // Parsa
    // Animals & Fishing methods
    public Result buildBuilding(String buildingName, String x, String y) {
        Coordinate coordinate = clientHandler.getClientPlayer().getCoordinate();
        Tile tile = clientHandler.getClientGame().getMap().findTile(coordinate, clientHandler.getClientGame());
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to show all products!");
        if (!clientHandler.getClientGame().getMap().findGameBuilding(coordinate).isInWorkingHours(clientHandler)) {
            return new Result(false, clientHandler.getClientGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        buildingName = buildingName.trim();
        x = x.trim();
        y = y.trim();

        CarpenterShop carpenterShop = (CarpenterShop) clientHandler.getClientGame().getMap().getCarpenterShop();
        if (carpenterShop.isInWorkingHours(clientHandler)) {
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
            if (clientHandler.getClientPlayer().getWallet().getBalance() > targetType.getCost()) {
                if (targetType.getWood() < clientHandler.getClientPlayer().getInventory()
                    .howManyInInventoryByType(ProductType.WOOD) &&
                    targetType.getStone() < clientHandler.getClientPlayer().getInventory()
                        .howManyInInventoryByType(ProductType.STONE)) {
                    Coordinate startCoordinate = new Coordinate((int) Integer.parseInt(x) - targetType.getSize().first() / 2,
                        (int) Integer.parseInt(y) - targetType.getSize().second() / 2);

                    boolean validSpace = true;
                    for (int sX = 0; sX < targetType.getSize().first(); sX++) {
                        for (int sY = 0; sY < targetType.getSize().second(); sY++) {
                            Tile tempTile = clientHandler.getClientGame().getMap().findTileByXY(sX + startCoordinate.getX(), sY + startCoordinate.getY());
                            if (!tempTile.getTileType().equals(TileType.FARM)) {
                                validSpace = false;
                            }
                        }
                    }
                    if (!validSpace) {
                        return new Result(false, "You can't build this building here!");
                    } else {
                        clientHandler.getClientPlayer().getInventory().removeItemsFromInventory(ProductType.WOOD, targetType.getWood());
                        clientHandler.getClientPlayer().getInventory().removeItemsFromInventory(ProductType.STONE, targetType.getStone());
                        clientHandler.getClientPlayer().getWallet().decreaseBalance(targetType.getCost());
                        FarmBuilding newBuilding = carpenterShop.buildingFarmBuilding(targetType, startCoordinate);
                        clientHandler.getClientPlayer().getFarm().getFarmBuildings().add(newBuilding);
                        for (int sX = 0; sX < targetType.getSize().first(); sX++) {
                            for (int sY = 0; sY < targetType.getSize().second(); sY++) {
                                clientHandler.getClientGame().getMap()
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

    public boolean isValidBuilding(Coordinate coordinate, FarmBuildingTypes targetType) {
        boolean validSpace = true;
        for (int sX = 0; sX < targetType.getSize().first(); sX++) {
            for (int sY = 0; sY < targetType.getSize().second(); sY++) {
                Tile tempTile = clientHandler.getClientGame().getMap().findTileByXY(sX + coordinate.getX(), sY + coordinate.getY());
                if (!tempTile.getTileType().equals(TileType.FARM)) {
                    validSpace = false;
                }
            }
        }
        if (!validSpace) {
            return false;
        }

        return true;
    }

    public Result buyAnimal(String animalType, String animalName) {
        Coordinate coordinate = clientHandler.getClientPlayer().getCoordinate();
        Tile tile = clientHandler.getClientGame().getMap().findTile(coordinate, clientHandler.getClientGame());
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to show all products!");
        if (!clientHandler.getClientGame().getMap().findGameBuilding(coordinate).isInWorkingHours(clientHandler)) {
            return new Result(false, clientHandler.getClientGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }


        if (animalType == null || animalName == null || animalType.trim().isEmpty() || animalName.trim().isEmpty()) {
            return new Result(false, "Invalid animal type or name");
        }
        animalType = animalType.trim();
        animalName = animalName.trim();

        MarnieRanch marnieRanch = (MarnieRanch) clientHandler.getClientGame().getMap().getMarnieRanch();
        if (!marnieRanch.isInWorkingHours(clientHandler)) {
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
        if (clientHandler.getClientPlayer().getWallet().getBalance() < animalTypeEnum.getPrice()) {
            return new Result(false, "You don't have enough Money!");
        }

        FarmBuilding suitableBuilding = null;
        for (FarmBuilding farmBuilding : clientHandler.getClientPlayer().getFarm().getFarmBuildings()) {
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

        clientHandler.getClientPlayer().getWallet().decreaseBalance(animalTypeEnum.getPrice());
        animal.setLocatedPLace(suitableBuilding);
        clientHandler.getClientGame().getMap().allAnimals().add(animal);
        int x = (int) (suitableBuilding.getStartCordinate().getX() + Math.random() * 2);
        int y = (int) (suitableBuilding.getEndCordinate().getY() - Math.random() * 2);
        animal.setCoordinate(new Coordinate(x, y));

        return new Result(true, "A " + animalType + " named " + animalName + " has been added to your farm!");
    }

    public Result petAnimal(String animalName) {
        animalName = animalName.trim();
        Animal animal = null;
        for (FarmBuilding b : clientHandler.getClientPlayer().getFarm().getFarmBuildings()) {
            for (Animal a : b.getAnimals()) {
                if (a.getName().equals(animalName)) {
                    animal = a;
                }
            }
        }
        animal.petAnimal();
        return new Result(true, "You petted " + animalName);


    }

    public String isStoreOpen(GameBuilding gameBuilding) {
        if (!gameBuilding.isInWorkingHours(clientHandler)) {
            return "Store is not Open!\nWorking Time: " + gameBuilding.getHours().first()
                + " ~ " + (gameBuilding.getHours().second());
        } else {
            return "yes";
        }
    }

    public Result animalList() {
        clientHandler.getClientPlayer().showAnimals();
        return new Result(true, "");
    }

    public Result cheatSetAnimalFriendship(String animalName, String amount) {
        animalName = animalName.trim();
        animalName = animalName.trim();

        int amountInt = Integer.parseInt(amount);
        Animal animal = clientHandler.getClientGame().getMap().findAnimalByName(animalName);
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

        Animal animal = clientHandler.getClientGame().getMap().findAnimalByName(animalName);
        if (animal == null) {
            return new Result(false, "Animal not found");
        }
        animal.shepherdAnimal(new Coordinate(Integer.parseInt(x), Integer.parseInt(y)));
        return new Result(true, "You shepherd " + animalName);
    }

    public Result feedHay(String animalName) {
        animalName = animalName.trim();

        Animal animal = clientHandler.getClientGame().getMap().findAnimalByName(animalName);
        if (!clientHandler.getClientPlayer().getInventory().isInInventoryBoolean(ProductType.HAY)) {
            return new Result(false, "You don't have enough Hay");
        } else {
            clientHandler.getClientPlayer().getInventory().removeItemsFromInventory(ProductType.HAY, 1);
            animal.setFed(true);
        }
        return new Result(true, "You fed " + animalName);
    }

    public Result animalProductionList() {
        for (FarmBuilding building : clientHandler.getClientPlayer().getFarm().getFarmBuildings()) {
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

        Animal animal = findAnimal(animalName);

        if (animal.getProducts(clientHandler) != null) {
            for (AnimalProduct product : animal.getProducts(clientHandler)) {
                clientHandler.getClientPlayer().getInventory().addGoodByObject(product, clientHandler.getClientGame(),
                    clientHandler.getClientPlayer());
            }
            System.out.println("You collected animal Products.");
            animal.getProducts(clientHandler).clear();
        }
        return new Result(true, "No product found");
    }

    public Result sellAnimal(String animalName) {
        animalName = animalName.trim();

        FarmBuilding place = null;
        Animal animal = null;
        for (FarmBuilding building : clientHandler.getClientPlayer().getFarm().getFarmBuildings()) {
            for (Animal a : building.getAnimals()) {
                if (a.getName().equals(animalName)) {
                    animal = a;
                    place = building;
                    break;
                }
            }
        }

        assert place != null;
        place.getAnimals().remove(animal);

        //remove from animals
        clientHandler.getClientGame().getMap().allAnimals().remove(animal);

        clientHandler.getClientPlayer().getWallet().increaseBalance(animal.getAnimalSellPrice());

        return new Result(true, "You sold " + animal.getAnimalSellPrice());
    }

    public Animal findAnimal(String animalName) {
        Animal animal;
        for (FarmBuilding building : clientHandler.getClientPlayer().getFarm().getFarmBuildings()) {
            for (Animal a : building.getAnimals()) {
                if (a.getName().equals(animalName)) {
                    animal = a;
                    return animal;
                }
            }
        }
        return null;
    }

    public Result fishing(String fishingPole) {
        fishingPole = fishingPole.trim();

        ToolType fishingPoleGood = ToolType.getTool(fishingPole);
        if (clientHandler.getClientPlayer().getInventory().isInInventory(fishingPole) == null) {
            return new Result(true, "You don't have this fishing pole");
        }
        for (Coordinate coordinate : Coordinate.coordinates) {
            Coordinate c = Coordinate.checkAround(clientHandler.getClientPlayer().getCoordinate(), coordinate);
            Tile tile = clientHandler.getClientGame().getMap().findTile(c, clientHandler.getClientGame());
            if (tile.getTileType() == TileType.WATER) {
                Weather weather = clientHandler.getClientGame().getWeather();
                Skill skill = clientHandler.getClientPlayer().getSkill();
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

        return ArtisanFunctions.useArtisan(artisanName, ourIngredients, clientHandler);
    }

    public Result artisanGet(String artisanName) {
        artisanName = artisanName.trim();
        return new Result(true, "");
    }

    // Nader
    // buy & sell methods
    public Result showAllProducts() {
        Coordinate coordinate = clientHandler.getClientPlayer().getCoordinate();
        Tile tile = clientHandler.getClientGame().getMap().findTile(coordinate, clientHandler.getClientGame());
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to show all products!");
        if (!clientHandler.getClientGame().getMap().findGameBuilding(coordinate).isInWorkingHours(clientHandler)) {
            return new Result(false, clientHandler.getClientGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        GameBuilding building = clientHandler.getClientGame().getMap().findGameBuilding(coordinate);
        return new Result(true, "");
    }

    public Result showAllAvailableProducts() {
        Coordinate coordinate = clientHandler.getClientPlayer().getCoordinate();
        Tile tile = clientHandler.getClientGame().getMap().findTile(clientHandler.getClientPlayer().getCoordinate(),
            clientHandler.getClientGame());
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to show all available products!");
        if (!clientHandler.getClientGame().getMap().findGameBuilding(coordinate).isInWorkingHours(clientHandler)) {
            return new Result(false, clientHandler.getClientGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        GameBuilding building = clientHandler.getClientGame().getMap().findGameBuilding(coordinate);
        return new Result(true, "");
    }

    public Result purchase(String productName, String count, Coordinate coordinate) {
        productName = productName.trim();
        count = count.trim();


        if (!clientHandler.getClientGame().getMap().findGameBuilding(coordinate).isInWorkingHours(clientHandler)) {
            return new Result(false, clientHandler.getClientGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        GameBuilding building = clientHandler.getClientGame().getMap().findGameBuilding(coordinate);
        return building.purchase(productName, count, clientHandler);
    }

    public Result cheatAddDollars(String count) {
        count = count.trim();

        int amount = Integer.parseInt(count);
        clientHandler.getClientPlayer().getWallet().increaseBalance(amount);
        return new Result(true, count + "G added to your wallet!");
    }

    public Result sell(String productName, String count) {
        productName = productName.trim();
        count = count.trim();

        ArrayList<Good> goods = clientHandler.getClientPlayer().getInventory().isInInventory(productName);
        if (goods == null)
            return new Result(false, "You don't have this good in your inventory!");

        if (!count.matches("-?\\d+") && !count.isEmpty())
            return new Result(false, "Invalid Quantity format!");

        int quantity = (count.isEmpty()) ? goods.size() : Integer.parseInt(count);
        if (quantity > goods.size())
            return new Result(false, "You don't have enough number \nof this good in your inventory!");

        boolean flag = false;
        for (int i = 0; i < 8; i++) {
            Coordinate coordinate = new Coordinate(
                clientHandler.getClientPlayer().getCoordinate().getX() + Coordinate.coordinates.get(i).getX(),
                clientHandler.getClientPlayer().getCoordinate().getY() + Coordinate.coordinates.get(i).getY());

            Tile tile = clientHandler.getClientGame().getMap().findTile(coordinate, clientHandler.getClientGame());
            if (tile != null && tile.findGood("ShippingBin") != null) {
                ArrayList<Good> newGoods = new ArrayList<>(goods);
                for (int j = 0; j < quantity; j++) {
                    newGoods.add(goods.getLast());
                    goods.removeLast();
                }

                ShippingBin shippingBin = (ShippingBin) tile.findGood("ShippingBin");
                shippingBin.addGood(newGoods, clientHandler.getClientPlayer());
                flag = true;
                break;
            }
        }

        if (flag)
            return new Result(true, quantity + " number of " + productName + " \nhas been added to ShippingBin!");
        else
            return new Result(false, "No ShippingBin found around you!");
    }

    // Arani
    // Friendships methods
    public Result friendships() {
        StringBuilder list = new StringBuilder();
        list.append("FriendShips:\n");
        for (Player player : clientHandler.getClientGame().getPlayers()) {
            if (clientHandler.getClientPlayer().getFriendShips().get(player.getPlayerUsername()) == null)
                continue;

            list.append("\t").append(player.getUsername()).append(": \n");
            list.append("\t\tLevel: ").append(clientHandler.getClientPlayer().getFriendShips().get(player.getUsername()).first()).append("\n");
            list.append("\t\tValue: ").append(clientHandler.getClientPlayer().getFriendShips().get(player.getUsername()).second()).append("\n");
        }
        return new Result(true, list.toString());
    }

    public Result talk(String username, String message) {
        username = username.trim();
        message = message.trim();

        Player player = clientHandler.getClientGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (clientHandler.getClientPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " for talking!");

        player.getTalkHistory().add(new Pair<>(
            clientHandler.getClientPlayer(),
            "\t<" + clientHandler.getClientPlayer().getPlayerUsername() + "> " + dateTime().message() + ": " + message
        ));

        clientHandler.getClientPlayer().getTalkHistory().add(new Pair<>(
            player,
            "\t<" + clientHandler.getClientPlayer().getPlayerUsername() + "> " + dateTime().message() + ": " + message
        ));

        try {
            if (clientHandler.getClientPlayer().getIsInteracted().get(player.getPlayerUsername()).equals(true)) {

            }
        } catch (Exception e) {
            return new Result(false, "You are not interacted!");
        }

        if (clientHandler.getClientPlayer().getIsInteracted().get(player.getPlayerUsername()).equals(false)) {

            // If they are couple
            if (clientHandler.getClientPlayer().getMarried() == player) {
                clientHandler.getClientPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                System.out.println("You and your partner got 50 extra energy!");
            } else {
                player.getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getPlayerUsername(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + 20));
                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(player.getPlayerUsername(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + 20));

                player.updateFriendShips(clientHandler.getClientPlayer());
                clientHandler.getClientPlayer().updateFriendShips(player);
                System.out.println("Your friendship value with " + username + " is increased to " +
                    clientHandler.getClientPlayer().getFriendShips().get(player.getPlayerUsername()).second());
            }

            clientHandler.getClientPlayer().getIsInteracted().put(player.getPlayerUsername(), true);
            player.getIsInteracted().put(clientHandler.getClientPlayer().getPlayerUsername(), true);
        }

        return new Result(true, "You talked with " + username + "!");
    }

    public Result talkHistory(String username) {
        username = username.trim();

        StringBuilder list = new StringBuilder();
        list.append("Talk History:\n");
        for (Pair<Player, String> talk : clientHandler.getClientPlayer().getTalkHistory()) {
            if (talk.first().getPlayerUsername().equals(username)) {
                list.append(talk.second()).append("\n");
            }
        }

        return new Result(true, list.toString());
    }

    public Result gift(String username, String item, String amount) {
        username = username.trim();
        amount = amount.trim();

        Player player = clientHandler.getClientGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (clientHandler.getClientPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " for sending gift!");

        if (clientHandler.getClientPlayer().getFriendShips().get(player.getPlayerUsername()).first() < 1)
            return new Result(false, "Your friendship level with " + username + " should be more than 0");

        ArrayList<Good> goods = clientHandler.getClientPlayer().getInventory().isInInventory(item);
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
        player.getGiftList().add(new Pair<>(clientHandler.getClientPlayer(), gift));
        player.getNews().add("A new gift has been added to your gift list from " + username + "!");

        return new Result(true, "Your gift has been sent to " + username + "!");
    }

    public Result giftList() {
        StringBuilder list = new StringBuilder();
        list.append("Gifts List:\n");
        int ptr = 1;
        for (Pair<Player, Gift> playerGiftPair : clientHandler.getClientPlayer().getGiftList()) {
            list.append("\t").append(ptr).append(". From <").append(playerGiftPair.first().getUsername()).append("> Good Name: ").
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
        if (giftNumber <= 0 || giftNumber > clientHandler.getClientPlayer().getGiftList().size())
            return new Result(false, "There is no gift with that number in your gift list!");

        giftNumber--;
        Pair<Player, Gift> gift = clientHandler.getClientPlayer().getGiftList().get(giftNumber);
        clientHandler.getClientPlayer().getGiftList().remove(gift);
        clientHandler.getClientPlayer().getInventory().addGood(gift.second().getList(), clientHandler.getClientGame(),
            clientHandler.getClientPlayer());
        clientHandler.getClientPlayer().getGiftHistory().add(new Pair<>(gift.first(),
            "A gift from " + gift.first().getUsername()
                + " with " + gift.second().getList().size() + " amount of " + gift.second().getList().getFirst().getName() +
                " \nhave been given to you! Your rate : " + giftRate + " !"));

        gift.first().getGiftHistory().add(new Pair<>(clientHandler.getClientPlayer(),
            "A gift with " + gift.second().getList().size() +
                " amount of " + gift.second().getList().getFirst().getName() +
                " \nhave been given to " + clientHandler.getClientPlayer().getUsername() + " from you! " +
                clientHandler.getClientPlayer().getUsername() + "'s rate : " + giftRate + " !"));
        gift.first().getNews().add(clientHandler.getClientPlayer().getUsername() + " has rated your gift with amount " +
            giftRate + " !");

        if (clientHandler.getClientPlayer().getIsInteracted().get(gift.first().getPlayerUsername()).equals(false)) {
            if (clientHandler.getClientPlayer().getMarried() == gift.first()) {
                clientHandler.getClientPlayer().getEnergy().increaseTurnEnergyLeft(50);
                gift.first().getEnergy().increaseTurnEnergyLeft(50);

                System.out.println("You and your partner got 50 extra energy!");
            } else {
                int value = (giftRate - 3) * 30 + 15;
                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(gift.first().getUsername(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + value));
                gift.first().getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + value));

                gift.first().updateFriendShips(clientHandler.getClientPlayer());
                clientHandler.getClientPlayer().updateFriendShips(gift.first());
                System.out.println("Your friendship value with " + gift.first().getUsername() + " is increased to " +
                    clientHandler.getClientPlayer().getFriendShips().get(gift.first().getPlayerUsername()).second());
            }

            clientHandler.getClientPlayer().getIsInteracted().put(gift.first().getPlayerUsername(), true);
            gift.first().getIsInteracted().put(clientHandler.getClientPlayer().getPlayerUsername(), true);

        }


        return new Result(true, "Your have rated gift with number " + giftNum + " with rate " + rate + "!");
    }

    public Result giftHistory(String username) {
        username = username.trim();

        StringBuilder list = new StringBuilder();
        list.append("Gifts History:\n");
        int ptr = 1;
        for (Pair<Player, String> giftHistory : clientHandler.getClientPlayer().getGiftHistory()) {
            if (giftHistory.first().getUsername().equals(username)) {
                list.append("\t").append(ptr++).append(". ").append(giftHistory.second()).append("\n");
            }
        }

        return new Result(true, list.toString());
    }

    public Result hug(String username) {
        username = username.trim();

        Player player = clientHandler.getClientGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (clientHandler.getClientPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " to hug!");


        StringBuilder list = new StringBuilder();
        if (!clientHandler.getClientPlayer().getIsInteracted().get(player.getPlayerUsername())) {

            if (clientHandler.getClientPlayer().getMarried() == player) {
                clientHandler.getClientPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                list.append("You and your partner got 50 extra energy!\n");
            } else {
                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(player.getUsername(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + 60));
                player.getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getUsername(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + 60));

                player.updateFriendShips(clientHandler.getClientPlayer());
                clientHandler.getClientPlayer().updateFriendShips(player);
                list.append("Your friendship value with " + username + " is increased to " +
                    clientHandler.getClientPlayer().getFriendShips().get(player.getPlayerUsername()).second() + "\n");
            }

            clientHandler.getClientPlayer().getIsInteracted().put(player.getPlayerUsername(), true);
            player.getIsInteracted().put(clientHandler.getClientPlayer().getPlayerUsername(), true);

        }

        list.append("You hugged " + username + "!");
        return new Result(true, list.toString());
    }

    public Result flower(String username) {
        username = username.trim();

        Player player = clientHandler.getClientGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (clientHandler.getClientPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " to give flower!");

        if (clientHandler.getClientPlayer().getFriendShips().get(player.getPlayerUsername()).first() != 2 ||
            player.getFriendShips().get(clientHandler.getClientPlayer().getPlayerUsername()).first() != 2)
            return new Result(false, "You and " + username + " should have friendship level of 2!");

        boolean flag = false;
        ArrayList<Good> goods = clientHandler.getClientPlayer().getInventory().isInInventory(FarmingCropType.SUNFLOWER);
        if (goods != null) {
            Good good = goods.getLast();
            goods.removeLast();
            player.getInventory().addGood(new ArrayList<>(Arrays.asList(good)), clientHandler.getClientGame(),
                clientHandler.getClientPlayer());
            flag = true;
        }

        goods = clientHandler.getClientPlayer().getInventory().isInInventory(FarmingCropType.CAULIFLOWER);
        if (!flag && goods != null) {
            Good good = goods.getLast();
            goods.removeLast();
            player.getInventory().addGood(new ArrayList<>(Arrays.asList(good)), clientHandler.getClientGame(),
                clientHandler.getClientPlayer());
            flag = true;
        }

        StringBuilder list = new StringBuilder();
        if (!flag)
            return new Result(false, "Your don't have any flower in your inventory to give to someone!");
        else if (!clientHandler.getClientPlayer().getIsInteracted().get(player.getPlayerUsername())) {
            if (clientHandler.getClientPlayer().getMarried() == player) {
                clientHandler.getClientPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                list.append("You and your partner got 50 extra energy!\n");
            } else {
                clientHandler.getClientPlayer().getFriendShips().computeIfPresent(player.getUsername(),
                    (k, pair) -> new Pair<>(pair.first() + 1, pair.second()));
                player.getFriendShips().computeIfPresent(clientHandler.getClientPlayer().getPlayerUsername(),
                    (k, pair) -> new Pair<>(pair.first() + 1, pair.second()));

                player.updateFriendShips(clientHandler.getClientPlayer());
                clientHandler.getClientPlayer().updateFriendShips(player);
                list.append("Your friendship level with " + username + " has been increased to 3!\n");
            }

            clientHandler.getClientPlayer().getIsInteracted().put(player.getPlayerUsername(), true);
            player.getIsInteracted().put(clientHandler.getClientPlayer().getPlayerUsername(), true);
        }


        list.append("Your have given a flower to " + username + "!");
        return new Result(true, list.toString());
    }

    public Result askMarriage(String username) {
        username = username.trim();

        Player player = clientHandler.getClientGame().findPlayer(username);
        Player mainPlayer = clientHandler.getClientPlayer();

        //Errors
        if (player == null)
            return new Result(false, "Player not found!");
        if (mainPlayer.getGender() == Gender.FEMALE)
            return new Result(false, "Your gender should be male to ask marriage!");
        if (mainPlayer.getFriendShips().get(player.getPlayerUsername()).first() != 3 ||
            player.getFriendShips().get(mainPlayer.getPlayerUsername()).first() != 3)
            return new Result(false, "You and " + username + " should have friendship level of 3!");
        if (mainPlayer.getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " to ask marriage!");
        if (mainPlayer.getGender() == player.getGender())
            return new Result(false, "Your gender should be opposite to " + username + " to ask marriage!");
        if (player.getMarriageList().get(mainPlayer) != null)
            return new Result(false, "Your marriage is already in progress!");
        if (player.getMarried() != null)
            return new Result(false, username + " is already married with " +
                player.getMarried().getUsername() + "!");

        if (mainPlayer.getInHandGood().isEmpty() ||
            !mainPlayer.getInHandGood().getLast().getName().equals("Wedding_Ring"))
            return new Result(false, "Please select a Wedding_Ring from your inventory!");


        ArrayList<Good> goods = clientHandler.getClientPlayer().getInventory().isInInventory(ProductType.WEDDING_RING);
        if (goods == null)
            return new Result(false, "Your should have wedding ring in your inventory to ask marriage!");

        player.getNews().add(mainPlayer.getUsername() + " asks you to marry him with Wedding_Ring!");
        player.getMarriageList().put(mainPlayer, goods.getLast());
        goods.removeLast();


        return new Result(true, "Your have asked marriage from " + username + " with Wedding_Ring!");
    }

    public Result respond(String status, String username) {
        status = status.trim();
        username = username.trim();

        Player player = clientHandler.getClientGame().findPlayer(username);
        Player mainPlayer = clientHandler.getClientPlayer();
        if (player == null)
            return new Result(false, "Player not found!");
        if (mainPlayer.getGender() == Gender.MALE)
            return new Result(false, "Your gender should be female to respond!");
        if (mainPlayer.getMarriageList().get(player) == null)
            return new Result(false, username + " has not been asked you to marry him!");

        if (status.matches("\\s*-accept\\s*")) {
            mainPlayer.getInventory().addGood(new ArrayList<>(Arrays.asList(mainPlayer.getMarriageList().get(player))),
                clientHandler.getClientGame(), clientHandler.getClientPlayer());

            // Friendship level increased to 4
            mainPlayer.getFriendShips().computeIfPresent(player.getUsername(),
                (k, pair) -> new Pair<>(4, pair.second()));
            player.getFriendShips().computeIfPresent(mainPlayer.getUsername(),
                (k, pair) -> new Pair<>(4, pair.second()));

            player.updateFriendShips(clientHandler.getClientPlayer());
            clientHandler.getClientPlayer().updateFriendShips(player);
            // The Wallets are shared!
            mainPlayer.getWallet().increaseBalance(player.getWallet().getBalance());
            player.setWallet(mainPlayer.getWallet());

            // Now they are married
            mainPlayer.setMarried(player);
            player.setMarried(mainPlayer);

            player.getNews().add(mainPlayer.getUsername() + " has accepted your marriage! Now you can live with her!");
            mainPlayer.getMarriageList().remove(player);

            for (Player gamePlayer : clientHandler.getClientGame().getPlayers()) {
                if (mainPlayer.getMarriageList().get(gamePlayer) != null) {
                    gamePlayer.getInventory().addGood(new ArrayList<>(Arrays.asList(mainPlayer.getMarriageList().get(gamePlayer))),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());
                    gamePlayer.getNews().add(mainPlayer.getUsername() + " has rejected your marriage and married with " + player.getUsername() + "!");
                    gamePlayer.setBuff(new Buff(BuffType.REJECT_BUFF, 7, 100));
                }
            }
            mainPlayer.getMarriageList().clear();


            return new Result(true, "Your have accepted your marriage from " + username + "! Now you can live with him!");
        } else if (status.matches("\\s*-reject\\s*")) {
            mainPlayer.getFriendShips().computeIfPresent(player.getUsername(),
                (k, pair) -> new Pair<>(0, pair.second()));
            player.getFriendShips().computeIfPresent(mainPlayer.getUsername(),
                (k, pair) -> new Pair<>(0, pair.second()));

            player.updateFriendShips(clientHandler.getClientPlayer());
            clientHandler.getClientPlayer().updateFriendShips(player);
            player.setRejectionBuff(new Buff(BuffType.REJECT_BUFF, 7, 100));
            player.getInventory().addGood(new ArrayList<>(Arrays.asList(mainPlayer.getMarriageList().get(player))),
                clientHandler.getClientGame(), clientHandler.getClientPlayer());
            player.getNews().add(mainPlayer.getUsername() + " has rejected your marriage!");
            mainPlayer.getFriendShips().remove(player.getPlayerUsername());


            return new Result(true, "You have rejected your marriage from " + username + "!");
        } else
            return new Result(false, "Invalid respond to marriage ask!");
    }


    // Parsa
    // Trading methods
    public Result startTrade() {
//        clientHandler.set(Menu.TradeMenu);

        System.out.println("Players: ");
        for (Player player : clientHandler.getClientGame().getPlayers()) {
            System.out.println(player.getUsername());
        }
        System.out.println("____________________________");
        System.out.println("You are now in Trade Menu!");

        Player currentPlayer = clientHandler.getClientPlayer();
        List<Trade> trades = TradeManager.getTradesFor(currentPlayer, clientHandler);

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

        for (NPC npc : clientHandler.getClientGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                if (isCloseEnough(npcName).success()) {
                    npc.getFriendship(clientHandler.getClientPlayer());
                    String talk = npc.npcDialogs(clientHandler);
                    return new Result(true, talk);
                }
            }
        }
        return new Result(true, "Too far away. Approach the NPC to speak.");
    }

    public Result isCloseEnough(String npcName) {
        for (NPC npc : clientHandler.getClientGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                if ((abs(npc.getType().getCoordinate().getX() -
                    clientHandler.getClientPlayer().getCoordinate().getX()) == 1 ||
                    (abs(npc.getType().getCoordinate().getX() -
                        clientHandler.getClientPlayer().getCoordinate().getX()) == 0))
                    &&
                    (abs(npc.getType().getCoordinate().getY() -
                        clientHandler.getClientPlayer().getCoordinate().getY()) == 1 ||
                        abs(npc.getType().getCoordinate().getY() -
                            clientHandler.getClientPlayer().getCoordinate().getY()) == 0)) {
                    return new Result(true, "");
                }
            }
        }
        return new Result(false, "");
    }

    public Result giftNPC(String npcName, String itemName) {
        npcName = npcName.trim();
        itemName = itemName.trim();

        Good good = null;
        boolean found = false;
        for (ArrayList<Good> goods : clientHandler.getClientPlayer().getInventory().getList()) {
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
        for (NPC npc : clientHandler.getClientGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                npc.getGift(good, clientHandler.getClientPlayer());
                return new Result(true, "You sent a " + itemName + " to " + npcName);

            }
        }
        return new Result(false, "NPC not found.");
    }

    public Result friendshipNPCList() {

        for (NPC npc : clientHandler.getClientGame().getNPCs()) {
            System.out.println("------------------------------");
            System.out.println("NPC Name: " + npc.getType().getName());
            System.out.println("Friendship Level: " + npc.getFriendship(clientHandler.getClientPlayer()).getFriendshipLevel());
            System.out.println("Friendship points: " + npc.getFriendship(clientHandler.getClientPlayer()).getFriendshipPoints());
        }
        return new Result(true, "------------------------------");
    }

    public Result questsList() {

        for (NPC npc : clientHandler.getClientGame().getNPCs()) {
            if (npc.getType().getName().equals("Harvey") ||
                npc.getType().getName().equals("Abigail") ||
                npc.getType().getName().equals("Leah") ||
                npc.getType().getName().equals("Robin") ||
                npc.getType().getName().equals("Sebastian")) {

                System.out.println(npc.getType().getName());
                if (npc.getFriendship(clientHandler.getClientPlayer()).getAvailableQuests().contains(1)) {
                    System.out.println(npc.getType().getRequests().getFirst().first().getName() + " Count: " +
                        npc.getType().getRequests().getFirst().second());
                }
                if (npc.getFriendship(clientHandler.getClientPlayer()).getAvailableQuests().contains(2)) {
                    System.out.println(npc.getType().getRequests().get(1).first().getName() + " " +
                        npc.getType().getRequests().get(1).second());
                }
                if (npc.getFriendship(clientHandler.getClientPlayer()).getAvailableQuests().contains(3)) {
                    System.out.println(npc.getType().getRequests().get(2).first().getName() + " " +
                        npc.getType().getRequests().get(2).second());
                }
                System.out.println("-----------------");

            }

        }

        return new Result(true, "------------------------------");
    }

    public String getQuests(String npcName) {
        for (NPC npc : clientHandler.getClientGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                if (npc.getFriendship(clientHandler.getClientPlayer()).getAvailableQuests().contains(1)) {
                    return (npc.getType().getRequests().getFirst().first().getName() + " Count: " +
                        npc.getType().getRequests().getFirst().second());
                }
                if (npc.getFriendship(clientHandler.getClientPlayer()).getAvailableQuests().contains(2)) {
                    return (npc.getType().getRequests().get(1).first().getName() + " " +
                        npc.getType().getRequests().get(1).second());
                }
                if (npc.getFriendship(clientHandler.getClientPlayer()).getAvailableQuests().contains(3)) {
                    return (npc.getType().getRequests().get(2).first().getName() + " " +
                        npc.getType().getRequests().get(2).second());
                }

            }
        }
        return "";
    }


    public Result questsFinish(String index) {
        index = index.trim();

        int indexInt = Integer.parseInt(index);
        NPC targetNPC = null;
        boolean found = false;
        for (NPC npc : clientHandler.getClientGame().getNPCs()) {
            if (isCloseEnough(npc.getType().getName()).success()) {
                targetNPC = npc;
                found = true;
                break;
            }
        }
        if (!found) {
            return new Result(true, "Too far away. Approach the NPC to speak.");
        }
        targetNPC.finishQuest(indexInt, clientHandler.getClientPlayer(), clientHandler);
        //  no next line
        return new Result(true, "");
    }

    public Result showCurrentMenu() {
        return new Result(true, "Current Menu : Game Menu");
    }


    //Additional Functions
    public Result showPlayerCoordinate() {
        return new Result(true, "Coordinate: " +
            clientHandler.getClientPlayer().getCoordinate().getX() + ", " +
            " " + clientHandler.getClientPlayer().getCoordinate().getY());
    }

    public Result showBalance() {
        return new Result(true, "Balance: " + clientHandler.getClientPlayer().getWallet().getBalance() + " g");
    }

    public Result test() {
        clientHandler.getClientPlayer().getInventory().addGood(Good.newGood(Good.newGoodType("Omelet")), 12,
            clientHandler.getClientGame(), clientHandler.getClientPlayer());

        GoodType goodType = Good.newGoodType("Omelet");
        ArrayList<Good> newGoods = Good.newGoods(goodType, 12);
        clientHandler.getClientPlayer().getInventory().addGood(newGoods, clientHandler.getClientGame(),
            clientHandler.getClientPlayer());

        return new Result(true, "test");
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }
}

