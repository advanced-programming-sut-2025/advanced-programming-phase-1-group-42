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
    private ClientHandler clientHandler;

    public GameMenuController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public Message handleMessage(Message message) {
        if (message.getType() == Message.Type.command) {
            switch ((String) message.getFromBody("function")) {
                case "getLabies" -> {
                    return new Message(new HashMap<>() {{
                        put("success", true);
                        put("message", AppServer.getLabies());
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
                    //TODO
                }
                case "exitLabi" -> {
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
                case "updateLabi" -> {
                    for (Labi labi : AppServer.getLabies()) {
                        if (labi.getID() == Integer.parseInt(message.getFromBody("arguments"))) {
                            return new Message(new HashMap<>() {{
                                put("success", true);
                                put("message", labi);
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
//    public void newGame(ArrayList<String> usernames) {
//        players = new ArrayList<>();
//
//        for (String username : usernames) {
//            User user = findAppUser(username);
//
//            if (user == null) {
//                players.add(new Player(new User(username, null, username,
//                    null, Gender.MALE, 0, null)));
//            } else {
//                players.add(new Player(user));
//            }
//        }
//        Player adminPlayer = players.getFirst();
//
//
//        director = new Director();
//        WholeGameBuilder wholeGameBuilder = new WholeGameBuilder();
//        director.createNewGame(wholeGameBuilder, players, adminPlayer);
//        game = wholeGameBuilder.getGame();
//        tiles = createTiles();
//        ptr = 0;
//        farms = new ArrayList<>();
//
//        view.getNewLabiWindow().setVisible(false);
//        view.initChoiceFarmWindow(players.getFirst().getPlayerUsername());
//    }
//
//    public void newGamePhase2() {
//        WholeMapBuilder wholeMapBuilder = new WholeMapBuilder();
//        director.createNewMap(wholeMapBuilder, farms, tiles);
//        game.setMap(wholeMapBuilder.getMap());
//
//        AppClient.setCurrentGame(game);
//        AppServer.getGames().add(game);
//        for (Player player : players) {
//            player.getUser().setPlaying(true);
//            player.iniFriendships(players);
//        }
//
////Goods generating
//        AppClient.getCurrentGame().getMap().generateRandomForagingCrops(93);
//        AppClient.getCurrentGame().getMap().generateRandomForagingSeed(93);
//        AppClient.getCurrentGame().getMap().generateRandomMinerals(93);
//        AppClient.getCurrentGame().getMap().generateRandomForagingTrees(93);
//        AppClient.getCurrentGame().getMap().generateRandomGrassTrees(93);
//
//        Main.getMain().getScreen().dispose();
//        Main.getMain().setScreen(new GameView(new GameController(clientHandler), Assets.getInstance().getSkin()));
//    }

    public Result loadGame() {
        Game game = AppServer.findGame(AppClient.getCurrentUser());
        if (game == null || !AppClient.getCurrentUser().getPlaying())
            return new Result(false, "You have no game to load!");

        AppClient.setCurrentGame(game);
        for (Player player : game.getPlayers()) {
            if (player.getUser().getUsername().equals(AppClient.getCurrentUser().getUsername()))
                AppClient.getCurrentGame().setGameAdmin(player);
        }

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

