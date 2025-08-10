package com.StardewValley.server.controllers;

import com.StardewValley.client.AppClient;
import com.StardewValley.client.Main;
import com.StardewValley.client.views.GameMenuView;
import com.StardewValley.client.views.GameView;
import com.StardewValley.client.views.MainMenuView;
import com.StardewValley.models.*;
import com.StardewValley.models.builders.Director;
import com.StardewValley.models.builders.concrete_builders.WholeGameBuilder;
import com.StardewValley.models.builders.concrete_builders.WholeMapBuilder;
import com.StardewValley.models.enums.Menu;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.enums.WeatherType;
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
import com.StardewValley.server.AppServer;
import com.StardewValley.server.ClientHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

import java.util.List;
import java.util.*;

import static java.lang.Math.abs;

public class GameMenuController extends Controller {
    public ArrayList<Player> players;
    public ArrayList<Tile> tiles;
    public ArrayList<Farm> farms;
    public Game game;
    private Director director;

    private ClientHandler clientHandler;

    public GameMenuController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public Message handleMessage(Message message) {
        if (message.getType() == Message.Type.command) {
            switch ((String) message.getFromBody("function")) {
                case "getLabies" -> {
                    String search = message.getFromBody("arguments");
                    ArrayList<Labi> sendingLabies = new ArrayList<>();
                    if (search.equals("")) {
                        for (Labi labi : AppServer.getLabies()) {
                            if (labi.isVisible())
                                sendingLabies.add(labi);
                        }
                    }
                    else {
                        for (Labi labi : AppServer.getLabies()) {
                            if (labi.getID() == Integer.parseInt(search))
                                sendingLabies.add(labi);
                        }
                    }

                    ArrayList<Labi> finalSendingLabies = sendingLabies;
                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", finalSendingLabies);
                    }}, Message.Type.response);
                }
                case "enterLabi" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    User user = null;
                    for (User onlineUser : AppServer.getOnlineUsers()) {
                        if (onlineUser.getUsername().equals(arguments.get(1))) {
                            user = onlineUser;
                            break;
                        }
                    }
                    for (Labi labi : AppServer.getLabies()) {
                        if (labi.getID() == Integer.parseInt(arguments.get(0))) {
                            labi.getUsers().add(user);
                            return new Message(new HashMap<>() {{
                                put("success", true);
                                put("message", labi);
                            }}, Message.Type.response);
                        }
                    }
                }
                case "startGame" -> {
                    Integer labiID = message.getIntFromBody("arguments");
                    Labi selectedLabi = null;
                    for (Labi labi : AppServer.getLabies()) {
                        if (labi.getID() == labiID) {
                            selectedLabi = labi;
                            break;
                        }
                    }

                    AppServer.getLabies().remove(selectedLabi);
                    AppServer.getWaitingLabies().add(new Pair<>(selectedLabi, new ArrayList<>()));
                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", "");
                    }}, Message.Type.response);
                }
                case "exitLabi" -> {
                    return exitLabi(message);
                }
                case "updateLabi" -> {
                    for (Labi labi : AppServer.getLabies()) {
                        if (labi.getID() == message.getIntFromBody("arguments")) {
                            return new Message(new HashMap<>() {{
                                put("success", true);
                                put("message", labi);
                            }}, Message.Type.response);
                        }
                    }

                    for (Pair<Labi, ArrayList<Pair<User, Integer>>> waitingLabi : AppServer.getWaitingLabies()) {
                        if (waitingLabi.first().getID() == message.getIntFromBody("arguments")) {
                            return new Message(new HashMap<>() {{
                                put("success", true);
                                put("message", "choice farm");
                            }}, Message.Type.response);
                        }
                    }
                }
                case "createNewLabi" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    Labi newLabi = new Labi(
                        arguments.get(1),
                        getLabiRandomID(),
                        Boolean.parseBoolean(arguments.get(2)),
                        arguments.get(3),
                        Boolean.parseBoolean(arguments.get(4)),
                        findAppOnlineUser(arguments.get(0))
                    );

                    AppServer.getLabies().add(newLabi);
                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", newLabi);
                    }}, Message.Type.response);
                }
                case "choiceFarm" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    for (Pair<Labi, ArrayList<Pair<User, Integer>>> waitingLabi : AppServer.getWaitingLabies()) {
                        if (waitingLabi.first().getID() == Integer.parseInt(arguments.get(1))) {
                            User user = findAppOnlineUser(arguments.get(0));
                            waitingLabi.second().add(new Pair<>(user, Integer.parseInt(arguments.get(2))));
                            return new Message(new HashMap<>() {{
                                put("success", true);
                                put("message", "");
                            }}, Message.Type.response);
                        }
                    }
                }
                case "updateWait" -> {
                    int LabiID = message.getIntFromBody("arguments");
                    Pair<Labi, ArrayList<Pair<User, Integer>>> selectedLabi = null;
                    for (Pair<Labi, ArrayList<Pair<User, Integer>>> waitingLabi : AppServer.getWaitingLabies()) {
                        if (waitingLabi.first().getID() == LabiID) {
                            if (waitingLabi.second().size() < 4) {
                                return new Message(new HashMap<>() {{
                                    put("success", false);
                                    put("message", "");
                                }}, Message.Type.response);
                            }
                            else {
                                selectedLabi = waitingLabi;
                                break;
                            }
                        }
                    }
                    int gameID = 0;
                    if (selectedLabi != null) {
                        gameID = newGame(selectedLabi);
                        AppServer.getWaitingLabies().remove(selectedLabi);
                    }
                    else {
                        for (Game game : AppServer.getGames()) {
                            if (game.getGameID() == LabiID) {
                                gameID = game.getGameID();
                                break;
                            }
                        }
                    }

//                    for (Game userGame : AppServer.getGames()) {
//                        if (userGame.getGameID() == gameID) {
//                            clientHandler.setClientGame(userGame);
//                            for (Player player : userGame.getPlayers()) {
//                                if (player.getUsername().equals(clientHandler.getClientUser().getUsername())) {
//                                    clientHandler.setClientPlayer(player);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                    this.clientHandler.setCurrentController(new GameController(clientHandler));

                    int finalGameID = gameID;
                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", finalGameID);
                    }}, Message.Type.response);
                }
                case "getNewGame" -> {
                    ArrayList<String> arguments = message.getFromBody("arguments");
                    Game userGame = null;
                    for (Game game : AppServer.getGames()) {
                        if (game.getGameID() == Integer.parseInt(arguments.get(0))) {
                            userGame = game;
                            break;
                        }
                    }
                    clientHandler.setClientGame(userGame);
                    for (Player player : userGame.getPlayers()) {
                        if (player.getUsername().equals(arguments.get(1))) {
                            clientHandler.setClientPlayer(player);
                            break;
                        }
                    }

                    this.clientHandler.setCurrentController(new GameController(clientHandler));
                    Game finalUserGame = userGame;
                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", finalUserGame);
                    }}, Message.Type.response);

                }
            }
        }
        if (message.getType() == Message.Type.change) {
            switch ((String) message.getFromBody("field")) {
                case "controller" -> {
                    if (message.getFromBody("change").equals("MainMenuController")) {
                        this.clientHandler.setCurrentController(new MainMenuController(clientHandler));
                    }

                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", "");
                    }}, Message.Type.response);
                }
            }
        }


        return new Message(new HashMap<>() {{
            put("success", false);
            put("message", "");
        }}, Message.Type.response);
    }

    public Message exitLabi(Message message) {
        ArrayList<String> arguments = message.getFromBody("arguments");
        Labi selectedLabi = null;
        for (Labi labi : AppServer.getLabies()) {
            if (labi.getID() == Integer.parseInt(arguments.get(0))) {
                selectedLabi = labi;
                break;
            }
        }

        if (selectedLabi.getUsers().size() == 1) {
            AppServer.getLabies().remove(selectedLabi);
            return new Message(new HashMap<>() {{
                put("success", true);
                put("message", "labi has been eliminated!");
            }}, Message.Type.response);
        }

        User user = null;
        for (User labiUser : selectedLabi.getUsers()) {
            if (labiUser.getUsername().equals(arguments.get(1))) {
                user = labiUser;
                break;
            }
        }

        selectedLabi.getUsers().remove(user);
        if (user.getUsername().equals(selectedLabi.getAdminUser().getUsername())) {
            selectedLabi.setAdminUser(selectedLabi.getUsers().getFirst());
        }

        return new Message(new HashMap<>() {{
            put("success", true);
            put("message", "You exit the labi!");
        }}, Message.Type.response);
    }


//    private void addPlayerToNewGame() {
//        User user = findAppUser(view.getAddPlayerField().getText());
//        if (user == null) {
//            view.getNewLabiErrorLabel().setText("User not found");
//            return;
//        }
//
//        if (view.getPlayerUsernames().contains(user.getUsername())) {
//            view.getNewLabiErrorLabel().setText(user.getUsername() + " is already added to the game!");
//            return;
//        }
//        if (user.getPlaying()) {
//            view.getNewLabiErrorLabel().setText(user.getUsername() + " is already playing in other game!");
//            return;
//        }
//
//        view.getPlayerLabels().get(view.getPlayersPtr()).setText("Player " + (view.getPlayersPtr() + 1) + ":\n" +
//            user.getUsername());
//        view.getPlayerUsernames().remove(view.getPlayersPtr());
//        view.getPlayerUsernames().add(view.getPlayersPtr(), user.getUsername());
//        view.increasePlayersPtr();
//        for (int i = view.getPlayersPtr(); i < 4; i++) {
//            view.getPlayerUsernames().removeLast();
//        }
//        for (int i = view.getPlayersPtr(); i < 4; i++) {
//            view.getPlayerUsernames().add("Guest " + (i - view.getPlayersPtr() + 1));
//        }
//    }

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
    public int newGame(Pair<Labi, ArrayList<Pair<User, Integer>>> labi) {
        players = new ArrayList<>();
        farms = new ArrayList<>();
        director = new Director();
        tiles = createTiles();

        for (Pair<User, Integer> username : labi.second()) {
            players.add(new Player(username.first()));
            farms.add(new Farm(username.second(), farms.size(), tiles));
            players.getLast().setFarm(farms.getLast());
        }
        Player adminPlayer = players.getFirst();
        WholeGameBuilder wholeGameBuilder = new WholeGameBuilder();
        director.createNewGame(wholeGameBuilder, players, adminPlayer, labi.first().getID());
        game = wholeGameBuilder.getGame();

        WholeMapBuilder wholeMapBuilder = new WholeMapBuilder();
        director.createNewMap(wholeMapBuilder, farms, tiles);
        game.setMap(wholeMapBuilder.getMap());


        AppServer.getGames().add(game);
        for (Player player : players) {
            player.iniFriendships(players);
        }

        clientHandler.setClientGame(game);
//Goods generating
        game.getMap().generateRandomForagingCrops(93, clientHandler);
        game.getMap().generateRandomForagingSeed(93, clientHandler);
        game.getMap().generateRandomMinerals(93, clientHandler);
        game.getMap().generateRandomForagingTrees(93, clientHandler);
        game.getMap().generateRandomGrassTrees(93, clientHandler);

        return game.getGameID();
    }

    public Result loadGame() {
//        Game game = AppServer.findGame(AppClient.getCurrentUser());
//        if (game == null || !AppClient.getCurrentUser().getPlaying())
//            return new Result(false, "You have no game to load!");
//
//        AppClient.setCurrentGame(game);
//        for (Player player : game.getPlayers()) {
//            if (player.getUsername().equals(AppClient.getCurrentUser().getUsername()))
//                AppClient.getCurrentGame().setGameAdmin(player);
//        }
//
        return new Result(true, "Your game has successfully loaded!");
    }

    private int getLabiRandomID() {
        Random rand = new Random();
        boolean flag = true;
        int randomID = 0;
        while (flag) {
            randomID = rand.nextInt(1, 9);
            for (int i = 0; i < 8; i++) {
                randomID *= 10;
                randomID += rand.nextInt(0, 9);
            }

            flag = false;
            for (Labi labi : AppServer.getLabies()) {
                if (labi.getID() == randomID) {
                    flag = true;
                    break;
                }
            }
        }
        return randomID;
    }



}

