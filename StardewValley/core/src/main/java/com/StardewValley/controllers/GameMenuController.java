package com.StardewValley.controllers;

import com.StardewValley.Main;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
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
import com.StardewValley.models.interactions.NPCs.NPCFriendship;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuilding;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuildingTypes;
import com.StardewValley.models.interactions.User;
import com.StardewValley.models.interactions.game_buildings.*;
import com.StardewValley.views.GameMenuView;
import com.StardewValley.views.GameView;
import com.StardewValley.views.MainMenuView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
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
import java.util.regex.Matcher;

import static java.awt.SystemColor.window;
import static java.lang.Math.abs;

public class GameMenuController extends Controller {
    public ArrayList<Player> players;
    public int ptr;
    public ArrayList<Tile> tiles;
    public ArrayList<Farm> farms;
    public Game game;
    private Director director;

    private WorldController worldController;
    private PlayerController playerController;
    private InventoryController inventoryController;
    private FriendshipController friendshipController;

    private GameMenuView view;
    private GameView gameView;
    private CookingController cookingController;
    private CraftingController craftingController;

    public void setView(GameMenuView view) {
        this.view = view;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
        worldController = new WorldController();
        playerController = new PlayerController();
        inventoryController = new InventoryController(gameView);
//        fridgeController = new FridgeController(gameView);
        cookingController = new CookingController();
        craftingController = new CraftingController();
        friendshipController = new FriendshipController(gameView);
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public CookingController getCookingController() {
        return cookingController;
    }

    public CraftingController getCraftingController() {
        return craftingController;
    }

    public void handleGameMenu() {
        if (view == null)
            return;

        if (view.getBackButton().isChecked()) {
            view.getBackButton().setChecked(false);

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), Assets.getInstance().getSkin()));
        } else if (view.getLoadGameButton().isChecked()) {
            view.getLoadGameButton().setChecked(false);

            Result res = loadGame();
            if (!res.success()) {
                view.getErrorLabel().setText(res.message());
                return;
            }

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameMenuController(), Assets.getInstance().getSkin()));
        } else if (view.getNewGameButton().isChecked()) {
            view.getNewGameButton().setChecked(false);

            view.getMenuWindow().setVisible(false);
            view.initNewGameWindow();
        } else if (view.getBackNewGameButton().isChecked()) {
            view.getBackNewGameButton().setChecked(false);

            view.getMenuWindow().setVisible(true);
            view.getNewGameWindow().setVisible(false);
        } else if (view.getAddPlayerButton().isChecked()) {
            view.getAddPlayerButton().setChecked(false);

            if (view.getPlayersPtr() >= 4) {
                view.getNewGameErrorLabel().setText("At most 4 players can play the game!");
                return;
            }

            addPlayerToNewGame();
        } else if (view.getStartNewGameButton().isChecked()) {
            view.getStartNewGameButton().setChecked(false);

            view.getNewGameWindow().setVisible(false);
            newGame(view.getPlayerUsernames());
        }
    }

    public void handleGame() {
        handleInput();
        worldController.updateWorld();
        playerController.updatePlayer();
        inventoryController.updateInventory();
//        fridgeController.updateFridge();
        friendshipController.update();

        refreshLeaderBoard();
    }



    public void handleInput() {
        if (gameView.getCheatWindow() != null)
            return;
        if (gameView.isTradeHasWindowOpened()){
            return;
        }

        if (gameView.getInMarnieRanch() == true)
            return;

        Player player = App.getCurrentGame().getCurrentPlayer();
        player.setPlayerDirection(-1);

        if(Gdx.input.isKeyJustPressed(Input.Keys.BACKSLASH)){
            if (gameView.getChatRoomWindow() == null)
                gameView.chatWindow();
            else {
                gameView.getChatRoomWindow().remove();
                gameView.setChatRoomWindow(null);
            }

        }

        if (gameView.getChatRoomWindow()==null) {

            if(Gdx.input.isKeyJustPressed(Input.Keys.U)) {
                App.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(1000);
                cheatAddItem("Wood" , String.valueOf(500));
                cheatAddItem("Stone" , String.valueOf(500));
                cheatAddItem("Sunflower" , String.valueOf(10));
                cheatAddItem("Wedding_Ring" , String.valueOf(10));

            }

        boolean flag = false;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (tileValidity(App.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX(), player.getCoordinate().getY() + 1))) {
                player.setLastCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY()));
                player.setCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY() + 1));
                player.setPlayerDirection(0);
                player.getInHandGoodSprite().setPosition(player.getCoordinate().getX() * gameView.getScaledSize(),
                    player.getCoordinate().getY() * gameView.getScaledSize() + 23);
            }
            flag = true;
            App.getCurrentGame().getMap().updateMap();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (tileValidity(App.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX() - 1, player.getCoordinate().getY()))) {
                player.setLastCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY()));
                player.setCoordinate(new Coordinate(player.getCoordinate().getX() - 1, player.getCoordinate().getY()));
                player.setPlayerDirection(1);
                player.getInHandGoodSprite().setPosition(player.getCoordinate().getX() * gameView.getScaledSize() - 20,
                    player.getCoordinate().getY() * gameView.getScaledSize() + 23);
                if (!player.getInHandGoodSprite().isFlipX())
                    player.getInHandGoodSprite().flip(true, false);
            }
            flag = true;
            App.getCurrentGame().getMap().updateMap();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (tileValidity(App.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX(), player.getCoordinate().getY() - 1))) {
                player.setLastCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY()));
                player.setCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY() - 1));
                player.setPlayerDirection(2);
                player.getInHandGoodSprite().setPosition(player.getCoordinate().getX() * gameView.getScaledSize(),
                    player.getCoordinate().getY() * gameView.getScaledSize() + 23);
            }
            flag = true;
            App.getCurrentGame().getMap().updateMap();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (tileValidity(App.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX() + 1, player.getCoordinate().getY()))) {
                player.setLastCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY()));
                player.setCoordinate(new Coordinate(player.getCoordinate().getX() + 1, player.getCoordinate().getY()));
                player.setPlayerDirection(3);
                player.getInHandGoodSprite().setPosition(player.getCoordinate().getX() * gameView.getScaledSize() + 20,
                    player.getCoordinate().getY() * gameView.getScaledSize() + 23);
                if (player.getInHandGoodSprite().isFlipX())
                    player.getInHandGoodSprite().flip(true, false);
            }
            flag = true;
            App.getCurrentGame().getMap().updateMap();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            nextTurn();
            inventoryController.playerChangedInventory();
        }
        //TESTES COMMANDS //////////////////////////////////////////////////
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
                gameView.showThunder();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            App.getCurrentGame().getDateTime().timeFlow();
        }
        /// ///////////////////////////////////////////////////////////////
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            gameView.touchDown(Gdx.input.getX(), Gdx.input.getY(), 0, Input.Buttons.LEFT);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            gameView.touchDown(Gdx.input.getX(), Gdx.input.getY(), 0, Input.Buttons.LEFT);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            ArrayList<Window> inventoryWindows = createWindows();
            inventoryController.setInventoryWindows(inventoryWindows);
            if (gameView.getMainTable() == null)
                gameView.initMainTable(0);
            else
                gameView.closeMainTable();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            if (gameView.getJournalWindow() == null)
                gameView.initJournalWindow();
            else
                gameView.closeJournalWindow();
        }



            if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
                ArrayList<Window> inventoryWindows = createWindows();
                inventoryController.setInventoryWindows(inventoryWindows);
                if (gameView.getMainTable() == null)
                    gameView.initMainTable(3);
                else
                    gameView.closeMainTable();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
                gameView.setTabClicked(!gameView.isTabClicked());
                if (gameView.isTabClicked()) {
                    for (Quadruple<ImageButton, Image, Label, Label> inventoryElement : inventoryController.getInventoryElements()) {
                        inventoryElement.a.setChecked(true);
                    }
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
                if (gameView.getToolsWindow() == null)
                    gameView.initToolsWindow();
                else
                    gameView.closeToolsWindow();
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
                if (gameView.getEmojiWindow() == null)
                    gameView.initPopupReactionWindow();
                else
                    gameView.closePopupReactionWindow();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
                //TODO
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if (gameView.getCheatWindow() == null)
                    gameView.initCheatWindow();
                else
                    gameView.closeCheatWindow();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
                for (FarmBuilding building : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
                    for (Animal animal : building.getAnimals()) {
                        if ((abs(animal.getCoordinate().getX() -
                            App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()) <= 2) &&
                            (abs(animal.getCoordinate().getY() -
                                App.getCurrentGame().getCurrentPlayer().getCoordinate().getY()) <= 2)) {
                            animal.petAnimal();
                            gameView.buildMessage();
                            gameView.getTextFieldMessage().setText("You petted " + animal.getName());
                        }
                    }
                }
                gameView.buildMessage();
                gameView.getTextFieldMessage().setText("Please approach an animal to pet");
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
                Tile selectedTile = App.getCurrentGame().getMap().findTileByXY(App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()
                    , App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());

                if (selectedTile.getTileType() == TileType.PLAYER_BUILDING) {
                    if (!gameView.getFridgeOpen()) {
                        gameView.initFridgeWindow();
                    } else {
                        gameView.getFridgeWindow().remove();
                    }
                    gameView.setFridgeOpen(!gameView.getFridgeOpen());
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
                Tile selectedTile = App.getCurrentGame().getMap().findTileByXY(App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()
                    , App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
                if (selectedTile.getTileType() == TileType.PLAYER_BUILDING) {
                    if (!gameView.getCookingOpen()) {
                        gameView.initCookingWindow();
                    } else {
                        gameView.getCookingWindow().remove();
                    }
                    gameView.setCookingOpen(!gameView.getCookingOpen());
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
                Tile selectedTile = App.getCurrentGame().getMap().findTileByXY(App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()
                    , App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
                if (selectedTile.getTileType() == TileType.PLAYER_BUILDING) {
                    if (!gameView.getIsCraftingOpen()) {
                        gameView.initCraftingWindow();
                    } else {
                        gameView.getCraftingWindow().remove();
                    }
                    gameView.setIsCraftingOpen(!gameView.getIsCraftingOpen());
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.DEL)) {
                if (gameView.getQuestWindow()==null) {
                    gameView.initQuestWindow();
                } else {
                    gameView.getQuestWindow().remove();
                    gameView.setQuestWindow(null);
                }
            }

            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(
                8, 9, 10, 11, 12, 13, 14, 15, 16, 7, 69, 70
            ));
            for (int i = 0; i < arr.size(); i++) {
                if (Gdx.input.isKeyJustPressed(arr.get(i))) {
                    App.getCurrentGame().getCurrentPlayer().setInHandGood(
                        App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i));
                    break;
                }
            }

            if (flag)
                App.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(0.25);
        }

    }



    private ArrayList<Good> cursorGoods = null;
    private Image cursorImage = new Image();

    private ArrayList<Window> createWindows() {
        ArrayList<Window> inventoryWindows = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            final int index = i;
            Skin skin = Assets.getInstance().getSkin();
            Window window = new Window("", skin);
            int width = 1000;
            int height = 800;
            window.setSize(width, height);

            // Add content based on index
            switch (index) {
                case 0:
                    window.add(new Label("Inventory", skin)).left().padBottom(10);
                    window.row();

                    Table inventoryTable = new Table();
                    Texture lockedSlotTexture = new Texture("GameAssets/Inventory_Table/locked.png");
                    Texture trashTexture = new Texture("GameAssets/Inventory_Table/Trash_Can_Gold.png");
                    TextureRegionDrawable drawableSlot = Assets.getInstance().getDrawableSlot();
                    TextureRegionDrawable drawableHighlight = Assets.getInstance().getDrawableHighlight();

                    int inventorySize = App.getCurrentGame().getCurrentPlayer().getInventory().getSize();
                    ArrayList<ArrayList<Good>> inventoryData = App.getCurrentGame().getCurrentPlayer().getInventory().getList();
                    ArrayList<Quadruple<ImageButton, Image, Label, Label>> inventoryElements = new ArrayList<>();
                    final int[] selectedSlotIndex = {-1};

                    int totalSlots = 36; // 3 rows * 12 columns
                    int itemIndex = 0;

                    for (int row = 0; row < 3; row++) {
                        for (int col = 0; col < 12; col++) {
                            Table slotContainer = new Table();

                            if (itemIndex < inventorySize) {
                                ArrayList<Good> goods = inventoryData.get(itemIndex);
                                ImageButton imageButtonBackground = new ImageButton(drawableSlot, drawableSlot, drawableHighlight);

                                Texture goodTexture = new Texture("GameAssets/null.png");
                                if (!goods.isEmpty()) {
                                    goodTexture = new Texture(goods.getFirst().getType().imagePath());
                                }

                                Image itemImage = new Image(new TextureRegion(goodTexture));

                                itemImage.setTouchable(Touchable.disabled); // Allow background button to receive clicks
                                itemImage.setScaling(Scaling.fit); // Optional: make icon fit better
                                itemImage.setSize(32, 32);// Optional: define icon size

                                final int currentIndex = itemIndex;
                                Image finalImage = itemImage;

                                imageButtonBackground.addListener(new ClickListener() {
                                    @Override
                                    public void clicked(InputEvent event, float x, float y) {
                                        selectedSlotIndex[0] = currentIndex; // Update selected slot here

                                        if (cursorGoods==null) {
                                            // Pick up the good if slot not empty
                                            if (!goods.isEmpty()) {
                                                cursorGoods = new ArrayList<>(goods);  // copy all goods from slot to cursor
                                                goods.clear();
                                                // Set cursor image to represent this good type (you might pick the first)
                                                App.setCursorFromImage(cursorGoods.get(0).getType().imagePath());
                                                rebuildInventoryUI(inventoryTable, inventoryElements);
                                            }
                                        } else {
                                            // Drop or swap goods
                                            if (goods.isEmpty()) {
                                                goods.addAll(cursorGoods);  // drop all goods held on cursor into slot
                                                cursorGoods.clear();
                                                cursorGoods = null;
                                                App.setCursor();  // reset cursor
                                                rebuildInventoryUI(inventoryTable, inventoryElements);
                                            } else {
                                                // Swap goods: swap whole stacks
                                                ArrayList<Good> temp = new ArrayList<>(goods);
                                                goods.clear();
                                                goods.addAll(cursorGoods);

                                                cursorGoods.clear();
                                                cursorGoods.addAll(temp);

                                                App.setCursorFromImage(cursorGoods.get(0).getType().imagePath());
                                                rebuildInventoryUI(inventoryTable, inventoryElements);
                                            }
                                        }
                                    }
                                });

                                Label indexLabel = new Label(String.valueOf(itemIndex + 1), skin);
                                indexLabel.setFontScale(0.4f);

                                Label quantityLabel = new Label(goods.isEmpty() ? "" : String.valueOf(goods.size()), skin, "Bold");
                                quantityLabel.setFontScale(0.4f);

                                inventoryElements.add(new Quadruple<>(imageButtonBackground, itemImage, indexLabel, quantityLabel));

                                Stack slotStack = new Stack();
                                slotStack.add(imageButtonBackground);
                                slotStack.add(itemImage);

                                slotContainer.add(slotStack).size(48, 48).row();
                                slotContainer.add(quantityLabel);
                            } else {
                                Image lockedImage = new Image(lockedSlotTexture);
                                slotContainer.add(lockedImage).size(48, 48);
                            }

                            inventoryTable.add(slotContainer).pad(4);
                            itemIndex++;
                        }
                        inventoryTable.row();
                    }

                    // Add trash can
                    Image trashCan = new Image(trashTexture);
                    trashCan.setSize(48, 48);
                    trashCan.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            if (cursorGoods != null && !cursorGoods.isEmpty()) {
                                int totalPrice = cursorGoods.getLast().getSellPrice()*cursorGoods.size();
                                int finalPrice = ToolFunctions.useTrashCan(App.getCurrentGame().getCurrentPlayer().getTrashCan(), totalPrice);
                                App.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(finalPrice);
                                cursorGoods.clear();
                                cursorGoods = null;
                                App.setCursor(); // Reset to default cursor
                                rebuildInventoryUI(inventoryTable, inventoryElements);
                            }
                        }
                    });

                    Table container = new Table();
                    container.add(inventoryTable).left().padRight(20);
                    container.add(trashCan).top().left().padTop(20);

                    ScrollPane scrollPane = new ScrollPane(container, skin);
                    scrollPane.setScrollingDisabled(true, false);
                    scrollPane.setFadeScrollBars(false);

                    window.add(scrollPane).expand().fill().colspan(2).padTop(10);
                    break;
                case 1:

                    window.add(new Label("Social", skin)).colspan(2).left();
                    window.setSize(width, height);
                    window.row().padTop(10);

                    Table socialContainer = new Table();
                    socialContainer.padLeft(44);
                    Table leftSideTable = new Table().top().left();

                    for (NPC npc : App.getCurrentGame().getNPCs()) {
                        leftSideTable.row().padBottom(12);
                        Table row = new Table().left();

                        Texture npcTxr = new Texture("GameAssets\\Main_Inventory\\" + npc.getType().getName() + ".png");
                        Image npc_img = new Image(npcTxr);

                        Texture hearts = new Texture("GameAssets\\Main_Inventory\\" + npc.getFriendship().getFriendshipLevel() + ".png");
                        Image hearts_img = new Image(hearts);
                        int newWidth = hearts.getWidth() * 2;
                        int newHeight = hearts.getHeight() * 2;

                        row.add(npc_img).padRight(10).size(40, 40).left();

                        String name = npc.getType().getName();
                        int basePadding = 180;
                        Label nameLabel = new Label(name, skin);
                        float adjustedPadding = Math.max(0, basePadding - nameLabel.getWidth());
                        row.add(nameLabel).padRight(adjustedPadding).left();
                        row.add(hearts_img).size(newWidth, newHeight).left();

                        leftSideTable.add(row).left();

                    }

                    Table rightSideTable = new Table().top().right();
                    for (Player p : App.getCurrentGame().getPlayers()) {
                        if (!p.equals(App.getCurrentGame().getCurrentPlayer())) {
                            rightSideTable.row().padBottom(12);
                            Table row = new Table().right();

                            Texture playerTxr = new Texture("GameAssets\\Main_Inventory\\Player.png");
                            Image npc_img = new Image(playerTxr);

                            Pair<Integer, Integer> friendship = p.getFriendShips().get(App.getCurrentGame().getCurrentPlayer());
                            int friendshipLevel = friendship != null ? friendship.second() : 0;

                            Texture hearts = new Texture("GameAssets\\Main_Inventory\\" + friendshipLevel + ".png");
                            Image hearts_img = new Image(hearts);
                            int newWidth = hearts.getWidth() * 2;
                            int newHeight = hearts.getHeight() * 2;

                            row.add(npc_img).padRight(10).size(40, 40).left();

                            String name = p.getUser().getNickname();
                            int basePadding = 180;
                            Label nameLabel = new Label(name, skin);
                            float adjustedPadding = Math.max(0, basePadding - nameLabel.getWidth());
                            Table nameAndHeart = new Table();
                            nameAndHeart.add(nameLabel).left();
                            nameAndHeart.row().padTop(2);
                            nameAndHeart.add(hearts_img).size(newWidth, newHeight).left();

                            row.add(nameAndHeart).left();

                            rightSideTable.add(row).right();

                        }
                    }

                    socialContainer.add(leftSideTable).expandX().left();
                    socialContainer.add(rightSideTable).expandX().left();

                    window.add(socialContainer).colspan(2).expand().fill().padTop(10);


                    break;
                case 2:
                    window.add(new Label("Skills", skin)).left().padBottom(10);
                    window.row();

                    Table skillsTable = new Table().top().left();
                    Skill skill = App.getCurrentGame().getCurrentPlayer().getSkill();
                    String[] skillNames = {"Farming", "Mining", "Cooking", "Foraging", "Fishing"};
                    String[] skillDescription  = {"Gives Higher Quality Goods\nLess Energy Per Hoe Swing",
                        "Can Mine More Minerals with each Swing\nLess Energy Per Pickaxe Swing" , "Unlocks More Recipes To Cook", "Better Chance of\ngetting Better Goods\nLess Energy Per Picking Ups" ,
                        "Higher Level Fishes\nUnlocks Legendary Fishes\nLess Energy Per Fish caught",
                    };
                    int[] skillLevels = {skill.getFarmingLevel(),
                        skill.getMiningLevel(),
                        skill.getFishingLevel(),
                        skill.getForagingLevel(),
                        skill.getCookingLevel()};
//                    int[] skillLevels = {3, 5, 7, 4, 9};

                    TooltipManager manager = TooltipManager.getInstance();
                    manager.initialTime = 0f;
                    manager.subsequentTime = 0f;
                    manager.resetTime = 0.5f;
                    manager.hideAll();

                    for (int b = 0; b < skillNames.length; b++) {
                        Table skillRow = new Table().left();

                        // Skill icon
                        Texture iconTexture = new Texture("GameAssets\\Main_Inventory\\" + skillNames[b] + "_Skill_Icon.png");
                        Image skillIcon = new Image(iconTexture);
                        skillIcon.setSize(40, 40);

                        // Tooltip text

                        Tooltip<Label> tooltip = new Tooltip<>(new Label("Skill: " + skillNames[b] + "\nLevel: "
                            + skillLevels[b] + "\nDescription: " + skillDescription[b], skin));
                        skillIcon.addListener(tooltip);

                        skillRow.add(skillIcon).padRight(10);

                        // Level indicators (e.g., hearts)
                        for (int lvl = 1; lvl <= 10; lvl++) {
                            String levelImg = lvl <= skillLevels[b] ? "_Full.png" : "_Empty.png";
                            Texture lvlTx = new Texture("GameAssets\\Main_Inventory\\" + skillNames[b] + levelImg);
                            Image lvlImg = new Image(lvlTx);
                            lvlImg.setSize(20, 20);
                            skillRow.add(lvlImg).padRight(2);
                        }
                        Label label = new Label("Level: " + skillLevels[b], skin);
                        skillRow.add(label).padLeft(40);
                        skillsTable.add(skillRow).left().padBottom(50);
                        skillsTable.row();
                    }

                    ScrollPane MapscrollPane = new ScrollPane(skillsTable, skin);
                    MapscrollPane.setScrollingDisabled(true, false);
                    MapscrollPane.setFadeScrollBars(false);

                    window.add(MapscrollPane).expand().fill().colspan(2).padTop(10);
                    break;
                case 3:
                    window.clear();
                    window.add(new Label("Map", skin)).left().padBottom(10);
                    window.row();

                    ScrollPane mapPane = App.getCurrentGame().getMap().createGraphicalMap();
                    window.add(mapPane).expand().fill().colspan(2);
                    window.row();

                    break;
                case 4:
                    window.add(new Label("Leader Board", skin)).expandY().expandX().left().top().row();
                    Table leaderboardTable = new Table();
                    this.leaderboardTable = leaderboardTable;
                    // Create sorting buttons
                    Label sortBy = new Label("Sort By:", skin);
                    TextButton sortByUsernameBtn = new TextButton("Name", skin);
                    TextButton sortByBalanceBtn = new TextButton("Money", skin);
                    TextButton sortByPointsBtn = new TextButton("Points", skin);
                    TextButton sortBySkillLevelBtn = new TextButton("Skill", skin);

                    // Add buttons to the UI
                    window.add(sortBy).expandY().expandX().left().top();
                    window.add(sortByUsernameBtn).expandY().expandX().left().top().pad(5).width(150);
                    window.add(sortByBalanceBtn).expandY().expandX().left().top().pad(5).width(150);
                    window.add(sortByPointsBtn).expandY().expandX().left().top().pad(5).width(150);
                    window.add(sortBySkillLevelBtn).expandY().expandX().left().top().pad(5).width(150);
                    window.row();

                    // Add the leaderboard table to the UI
                    window.add(leaderboardTable).expandY().expandX().left().top().colspan(4).padTop(10);

                    // 📍 Now, here is where you add the listeners:
                    sortByUsernameBtn.addListener(new ClickListener() {
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentSortType = LeaderboardSortType.USERNAME;
                            refreshLeaderboardTable();
                        }
                    });

                    sortByBalanceBtn.addListener(new ClickListener() {
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentSortType = LeaderboardSortType.BALANCE;
                            refreshLeaderboardTable();
                        }
                    });

                    sortByPointsBtn.addListener(new ClickListener() {
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentSortType = LeaderboardSortType.POINTS;
                            refreshLeaderboardTable();
                        }
                    });

                    sortBySkillLevelBtn.addListener(new ClickListener() {
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentSortType = LeaderboardSortType.SKILL;
                            refreshLeaderboardTable();
                        }
                    });

                    // Initial table build
                    refreshLeaderboardTable();
                    break;
                case 5:
                    window.setSize(width, height);
                    gameView.initExitMenu(window);

                    break;
                default:
                    window.add(new Label("Empty", skin));
            }

            inventoryWindows.add(window);
        }
        return inventoryWindows;
    }

    private void rebuildInventoryUI(Table inventoryTable, ArrayList<Quadruple<ImageButton, Image, Label, Label>> inventoryElements) {
        inventoryTable.clear();
        inventoryElements.clear();

        int inventorySize = App.getCurrentGame().getCurrentPlayer().getInventory().getSize();
        ArrayList<ArrayList<Good>> inventoryData = App.getCurrentGame().getCurrentPlayer().getInventory().getList();

        int itemIndex = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 12; col++) {
                Table slotContainer = new Table();

                if (itemIndex < inventorySize) {
                    ArrayList<Good> goods = inventoryData.get(itemIndex);
                    ImageButton imageButtonBackground = new ImageButton(
                        Assets.getInstance().getDrawableSlot(),
                        Assets.getInstance().getDrawableSlot(),
                        Assets.getInstance().getDrawableHighlight()
                    );

                    Texture goodTexture = new Texture("GameAssets/null.png");
                    if (!goods.isEmpty()) {
                        goodTexture = new Texture(goods.get(0).getType().imagePath());
                    }
                    Image itemImage = new Image(new TextureRegion(goodTexture));
                    itemImage.setTouchable(Touchable.disabled);
                    itemImage.setScaling(Scaling.fit);
                    itemImage.setSize(32, 32);

                    final int currentIndex = itemIndex;

                    imageButtonBackground.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            // cursorGoods is ArrayList<Good> instead of cursorGood
                            if (cursorGoods == null || cursorGoods.isEmpty()) {
                                // Pick up entire stack from slot if any
                                if (!goods.isEmpty()) {
                                    cursorGoods = new ArrayList<>(goods);
                                    goods.clear();
                                    App.setCursorFromImage(cursorGoods.get(0).getType().imagePath());
                                    rebuildInventoryUI(inventoryTable, inventoryElements);
                                }
                            } else {
                                // Drop or swap entire stack
                                if (goods.isEmpty()) {
                                    // Place cursor stack into empty slot
                                    goods.addAll(cursorGoods);
                                    cursorGoods.clear();
                                    cursorGoods = null;
                                    App.setCursor();
                                    rebuildInventoryUI(inventoryTable, inventoryElements);
                                } else {
                                    // Swap stacks
                                    ArrayList<Good> temp = new ArrayList<>(goods);
                                    goods.clear();
                                    goods.addAll(cursorGoods);
                                    cursorGoods.clear();
                                    cursorGoods.addAll(temp);
                                    App.setCursorFromImage(cursorGoods.get(0).getType().imagePath());
                                    rebuildInventoryUI(inventoryTable, inventoryElements);
                                }
                            }
                        }
                    });

                    Label quantityLabel = new Label(goods.isEmpty() ? "" : String.valueOf(goods.size()), Assets.getInstance().getSkin(), "Bold");
                    quantityLabel.setFontScale(0.4f);

                    inventoryElements.add(new Quadruple<>(imageButtonBackground, itemImage, null, quantityLabel));

                    Stack slotStack = new Stack();
                    slotStack.add(imageButtonBackground);
                    slotStack.add(itemImage);

                    slotContainer.add(slotStack).size(48, 48).row();
                    slotContainer.add(quantityLabel);

                } else {
                    Image lockedImage = new Image(new Texture("GameAssets/Inventory_Table/locked.png"));
                    slotContainer.add(lockedImage).size(48, 48);
                }

                inventoryTable.add(slotContainer).pad(4);
                itemIndex++;
            }
            inventoryTable.row();
        }
    }
    private boolean tileValidity(Tile tile) {
        if (tile.getTileType() == TileType.STONE_WALL ||
            tile.getTileType() == TileType.WATER ||
            tile.getTileType() == TileType.GAME_BUILDING ||
            (tile.getTileType() == TileType.GREEN_HOUSE && !App.getCurrentGame().getCurrentPlayer().getFarm().getGreenHouse().isAvailable()))
            return false;
        return true;
    }

    private void addPlayerToNewGame() {
        User user = findAppUser(view.getAddPlayerField().getText());
        if (user == null) {
            view.getNewGameErrorLabel().setText("User not found");
            return;
        }

        if (view.getPlayerUsernames().contains(user.getUsername())) {
            view.getNewGameErrorLabel().setText(user.getUsername() + " is already added to the game!");
            return;
        }
        if (user.getPlaying()) {
            view.getNewGameErrorLabel().setText(user.getUsername() + " is already playing in other game!");
        }

        view.getPlayerLabels().get(view.getPlayersPtr()).setText("Player " + (view.getPlayersPtr() + 1) + ":\n" +
            user.getUsername());
        view.getPlayerUsernames().remove(view.getPlayersPtr());
        view.getPlayerUsernames().add(view.getPlayersPtr(), user.getUsername());
        view.increasePlayersPtr();
        for (int i = view.getPlayersPtr(); i < 4; i++) {
            view.getPlayerUsernames().removeLast();
        }
        for (int i = view.getPlayersPtr(); i < 4; i++) {
            view.getPlayerUsernames().add("Guest " + (i - view.getPlayersPtr() + 1));
        }
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
    public void newGame(ArrayList<String> usernames) {
        players = new ArrayList<>();

        for (String username : usernames) {
            User user = findAppUser(username);

            if (user == null) {
                players.add(new Player(new User(username, null, username,
                    null, Gender.MALE, 0, null)));
            } else {
                players.add(new Player(user));
            }
        }
        Player adminPlayer = players.getFirst();


        director = new Director();
        WholeGameBuilder wholeGameBuilder = new WholeGameBuilder();
        director.createNewGame(wholeGameBuilder, players, adminPlayer);
        game = wholeGameBuilder.getGame();
        tiles = createTiles();
        ptr = 0;
        farms = new ArrayList<>();

        view.getNewGameWindow().setVisible(false);
        view.initChoiceFarmWindow(players.getFirst().getPlayerUsername());
    }

    public void newGamePhase2() {
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

        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new GameView(new GameMenuController(), Assets.getInstance().getSkin()));
    }

    public Result loadGame() {
        Game game = App.findGame(App.getCurrentUser());
        if (game == null || !App.getCurrentUser().getPlaying())
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
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), Assets.getInstance().getSkin()));
            return new Result(true, "You have successfully exited the game!");
        }
    }

    public Result forceTerminate() {
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<Boolean> poll = new ArrayList<>();
//        poll.add(true);
//        for (int i = 1; i < App.getCurrentGame().getPlayers().size(); i++) {
//            System.out.println(App.getCurrentGame().getPlayers().get(i).getUser().getUsername() +
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

        for (Player player : App.getCurrentGame().getPlayers()) {
            player.getUser().setPlaying(false);
            player.getUser().setGame(null);
            player.getUser().increaseEarnedPoints(player.getPoints());
            player.getUser().maxMaxPoints(player.getPoints());
            player.getUser().increaseGamePlay(App.getCurrentGame().getDateTime().getDays());
        }

        App.getGames().remove(App.getCurrentGame());
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
//            for (Player player : App.getCurrentGame().getPlayers()) {
//                filter = Filters.eq("username",player.getUser().getUsername());
//                Bson update = Updates.set("setPlaying", false);
//                UpdateResult result = collection.updateOne(filter, update);
//
//            }
//        } catch (Exception e) {
//            System.out.println("Error while setting is Playing  user.");
//        }
        App.setCurrentGame(null);
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(), Assets.getInstance().getSkin()));
        return new Result(true, "Game terminated successfully!");

    }

    public Result nextTurn() {
        App.getCurrentGame().nextPlayer();
        return new Result(true, "");
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
    public Result walk(String x, String y) {
        Scanner scanner = new Scanner(System.in);
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
        int ptr = 0;
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getFirst().getName().equals(toolName) && goods.size() >= 1) {
                App.getCurrentGame().getCurrentPlayer().setInHandGood(goods);
                flag = true;
                break;
            }
            if (!flag && toolName.equals("Trash_Can")) {
                App.getCurrentGame().getCurrentPlayer().setInHandGood(new ArrayList<>(Arrays.asList(
                    App.getCurrentGame().getCurrentPlayer().getTrashCan()
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
        if (App.getCurrentGame().getCurrentPlayer().getInHandGood().getLast() instanceof Tool) {
            return new Result(true, "Your current tool: " + App.getCurrentGame().getCurrentPlayer().getInHandGood().getLast().getName());
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
        if (game.getCurrentPlayer().getInHandGood().getLast() instanceof Tool) {
            Blacksmith blacksmith = (Blacksmith) game.getMap().getBlackSmith();
            if (((ToolType) game.getCurrentPlayer().getInHandGood().getLast().getType()).getLevel().getLevelNumber() == 4)
                return new Result(true, "Your tool is already in the highest level!");

            if (blacksmith.upgradeTool((Tool) game.getCurrentPlayer().getInHandGood().getLast())) {
                return new Result(false, "Your tool has successfully upgraded!");
            } else
                return new Result(false, "You don't have enough money & \ningredients to upgrade "
                    + game.getCurrentPlayer().getInHandGood().getLast().getName() + "!");
        } else
            return new Result(false, "You don't have tool in your hand!");
    }

    public Result toolsUse(Coordinate coordinate) {
        if (App.getCurrentGame().getCurrentPlayer().getInHandGood().getLast() instanceof Tool) {
            Tool tool = (Tool) App.getCurrentGame().getCurrentPlayer().getInHandGood().getLast();
            coordinate = new Coordinate(coordinate.getX() + App.getCurrentGame().getCurrentPlayer().getCoordinate().getX(),
                coordinate.getY() + App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
            if (coordinate == null)
                return new Result(false, "Direction not recognized");

            if (App.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft() < tool.getType().getEnergy())
                return new Result(false, "You don't have enough energy to use " + tool.getName() + "!");
            if (App.getCurrentGame().getMap().findTile(coordinate) == null)
                return new Result(false, "Tile not found");

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

        Coordinate coordinate = Coordinate.fromString(direction);
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

        Coordinate coordinate = Coordinate.fromString(direction);
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
        Tool tool = (Tool) App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ToolType.WATERING_CAN.getName()).getFirst();
        return new Result(true, "Your watering can have capacity:" + tool.capacity);
    }


    // Nader
    // crafting methods
    public Result showCraftingRecipes() {

        for (CraftingRecipe craftingRecipe : App.getCurrentGame().getCurrentPlayer().getCraftingRecipes()) {
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
        for (CraftingRecipe craftingRecipe : App.getCurrentGame().getCurrentPlayer().getCraftingRecipes()) {
            if (craftingRecipe.getName().equals(itemName)) {
                CraftingFunctions craftingFunctions = new CraftingFunctions();
                Result result;
                result = craftingFunctions.checkCraftingFunctions((CraftingRecipeType) craftingRecipe.getType());
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

        Coordinate coordinate = Coordinate.fromString(direction);
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
//        foodName = foodName.trim();
        Good food = App.getCurrentGame().getCurrentPlayer().getInHandGood().removeLast();
//        for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
//            Iterator<Good> iterator = goodArrayList.iterator();
//            while (iterator.hasNext()) {
//                food = iterator.next();
//                if (food.getName().equals(foodName)) {
//                    if (food instanceof Food) {
//                        iterator.remove();
//                        break;
//                    }
//                }
//            }
//        }
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


    // Animals & Fishing methods
    public Result buildBuilding(String buildingName, String x, String y) {
        Coordinate coordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();
        Tile tile = App.getCurrentGame().getMap().findTile(coordinate);
//        if (tile.getTileType() != TileType.GAME_BUILDING)
//            return new Result(false, "You should be in a game building to show all products!");
//        if (!App.getCurrentGame().getMap().findGameBuilding(coordinate).isInWorkingHours()) {
//            return new Result(false, App.getCurrentGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
//        }

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
                    Coordinate startCoordinate = new Coordinate((int) Integer.parseInt(x) - targetType.getSize().first() / 2,
                        (int) Integer.parseInt(y) - targetType.getSize().second() / 2);

                    boolean validSpace = true;
                    for (int sX = 0; sX < targetType.getSize().first(); sX++) {
                        for (int sY = 0; sY < targetType.getSize().second(); sY++) {
                            Tile tempTile = App.getCurrentGame().getMap().findTileByXY(sX + startCoordinate.getX(), sY + startCoordinate.getY());
                            if (!tempTile.getTileType().equals(TileType.FARM)) {
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

    public boolean isValidBuilding(Coordinate coordinate, FarmBuildingTypes targetType) {
        boolean validSpace = true;
        for (int sX = 0; sX < targetType.getSize().first(); sX++) {
            for (int sY = 0; sY < targetType.getSize().second(); sY++) {
                Tile tempTile = App.getCurrentGame().getMap().findTileByXY(sX + coordinate.getX(), sY + coordinate.getY());
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

    public Result buyAnimal(AnimalTypes animalType, String animalName) {
        Coordinate coordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();

//        if (!App.getCurrentGame().getMap().findGameBuilding(coordinate).isInWorkingHours()) {
//            return new Result(false, App.getCurrentGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
//        }

        animalName = animalName.trim();

        MarnieRanch marnieRanch = (MarnieRanch) App.getCurrentGame().getMap().getMarnieRanch();
        if (!marnieRanch.isInWorkingHours()) {
            return new Result(false, "Store is not Open!\nWorking Time: " + marnieRanch.getHours().first()
                + " ~ " + (marnieRanch.getHours().second()));
        }

        if (App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() < animalType.getPrice()) {
            return new Result(false, "You don't have enough Money!");
        }

        FarmBuilding suitableBuilding = null;
        for (FarmBuilding farmBuilding : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
            if (farmBuilding.getType().equals(animalType.getFarmBuildingTypes())) {
                suitableBuilding = farmBuilding;
                break;
            }
        }
        if (suitableBuilding == null) {
            return new Result(false, "You don't have a suitable building for this animal!");
        }

        Animal animal = marnieRanch.buildAnimal(animalType, animalName);
        if (!suitableBuilding.addAnimal(animal)) {
            return new Result(false, suitableBuilding.getName() + " is full");
        }

        App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(animalType.getPrice());
        animal.setLocatedPLace(suitableBuilding);
        App.getCurrentGame().getMap().allAnimals().add(animal);
        int minX = (int) suitableBuilding.getStartCordinate().getX();
        int maxX = (int) suitableBuilding.getEndCordinate().getX();

        int minY = (int) suitableBuilding.getStartCordinate().getY();
        int maxY = (int) suitableBuilding.getEndCordinate().getY();

        int x = minX + (int)(Math.random() * (maxX - minX + 1));
        int y = minY + (int)(Math.random() * (maxY - minY + 1));

        animal.setCoordinate(new Coordinate(x, y));

        return new Result(true, "A " + animalType + " named " + animalName + " has been added to your farm!");
    }

    public Result petAnimal(String animalName) {
        animalName = animalName.trim();
        Animal animal = null;
        for (FarmBuilding b : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
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
        if (!gameBuilding.isInWorkingHours()) {
            return "Store is not Open!\nWorking Time: " + gameBuilding.getHours().first()
                + " ~ " + (gameBuilding.getHours().second());
        } else {
            return "yes";
        }
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

        Animal animal = findAnimal(animalName);

        if (animal.getProducts() != null) {
            for (AnimalProduct product : animal.getProducts()) {
                App.getCurrentGame().getCurrentPlayer().getInventory().addGoodByObject(product);
            }
            System.out.println("You collected animal Products.");
            animal.getProducts().clear();
        }
        return new Result(true, "No product found");
    }

    public Result sellAnimal(String animalName) {
        animalName = animalName.trim();

        FarmBuilding place = null;
        Animal animal = null;
        for (FarmBuilding building : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
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
        App.getCurrentGame().getMap().allAnimals().remove(animal);

        App.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(animal.getAnimalSellPrice());

        return new Result(true, "You sold " + animal.getAnimalSellPrice());
    }

    public Animal findAnimal(String animalName) {
        Animal animal;
        for (FarmBuilding building : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
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
        return new Result(true, "");
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
        return new Result(true, "");
    }

    public Result purchase(String productName, String count, Coordinate coordinate) {
        productName = productName.trim();
        count = count.trim();


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
            return new Result(false, "You don't have enough number \nof this good in your inventory!");

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
            return new Result(true, quantity + " number of " + productName + " \nhas been added to ShippingBin!");
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
            "\t<" + App.getCurrentGame().getCurrentPlayer().getPlayerUsername() + "> " + dateTime().message() + ": " + message
        ));

        App.getCurrentGame().getCurrentPlayer().getTalkHistory().add(new Pair<>(
            player,
            "\t<" + App.getCurrentGame().getCurrentPlayer().getPlayerUsername() + "> " + dateTime().message() + ": " + message
        ));

        try {
            if (App.getCurrentGame().getCurrentPlayer().getIsInteracted().get(player).equals(true)) {

            }
        } catch (Exception e) {
            return new Result(false, "You are not interacted!");
        }

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
                list.append(talk.second()).append("\n");
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
            return new Result(false, "You should be neighbor to " + username + " for sending gift!");

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
                " \nhave been given to you! Your rate : " + giftRate + " !"));

        gift.first().getGiftHistory().add(new Pair<>(App.getCurrentGame().getCurrentPlayer(),
            "A gift with " + gift.second().getList().size() +
                " amount of " + gift.second().getList().getFirst().getName() +
                " \nhave been given to " + App.getCurrentGame().getCurrentPlayer().getUser().getUsername() + " from you! " +
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


        StringBuilder list = new StringBuilder();
        if (!App.getCurrentGame().getCurrentPlayer().getIsInteracted().get(player)) {
            gameView.showHug(App.getCurrentGame().getCurrentPlayer(), player);
            if (App.getCurrentGame().getCurrentPlayer().getMarried() == player) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                list.append("You and your partner got 50 extra energy!\n");
            } else {
                App.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(player,
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + 60));
                player.getFriendShips().computeIfPresent(App.getCurrentGame().getCurrentPlayer(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + 60));

                player.updateFriendShips(App.getCurrentGame().getCurrentPlayer());
                App.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
                list.append("Your friendship value with " + username + " is increased to " +
                    App.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).second() + "\n");
            }

            App.getCurrentGame().getCurrentPlayer().getIsInteracted().put(player, true);
            player.getIsInteracted().put(App.getCurrentGame().getCurrentPlayer(), true);

        }

        list.append("You hugged " + username + "!");
        return new Result(true, list.toString());
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

        StringBuilder list = new StringBuilder();
        if (!flag)
            return new Result(false, "Your don't have any flower in your inventory to give to someone!");
        else if (!App.getCurrentGame().getCurrentPlayer().getIsInteracted().get(player)) {
            if (App.getCurrentGame().getCurrentPlayer().getMarried() == player) {
                App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                list.append("You and your partner got 50 extra energy!\n");
            } else {
                App.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(player,
                    (k, pair) -> new Pair<>(pair.first() + 1, pair.second()));
                player.getFriendShips().computeIfPresent(App.getCurrentGame().getCurrentPlayer(),
                    (k, pair) -> new Pair<>(pair.first() + 1, pair.second()));

                player.updateFriendShips(App.getCurrentGame().getCurrentPlayer());
                App.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
                list.append("Your friendship level with " + username + " has been increased to 3!\n");
            }

            App.getCurrentGame().getCurrentPlayer().getIsInteracted().put(player, true);
            player.getIsInteracted().put(App.getCurrentGame().getCurrentPlayer(), true);
        }
        gameView.showFlower(App.getCurrentGame().getCurrentPlayer(), player);
        list.append("Your have given a flower to " + username + "!");
        return new Result(true, list.toString());
    }

    public Result askMarriage(String username) {
        username = username.trim();

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

        if (mainPlayer.getInHandGood().isEmpty() ||
            !mainPlayer.getInHandGood().getLast().getName().equals("Wedding_Ring"))
            return new Result(false, "Please select a Wedding_Ring from your inventory!");


        ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ProductType.WEDDING_RING);
        if (goods == null)
            return new Result(false, "Your should have wedding ring in your inventory to ask marriage!");

        player.getNews().add(mainPlayer.getUser().getUsername() + " asks you to marry him with Wedding_Ring!");
        player.getMarriageList().put(mainPlayer, goods.getLast());
        goods.removeLast();

        gameView.showPropose(App.getCurrentGame().getCurrentPlayer(),player);
        return new Result(true, "Your have asked marriage from " + username + " with Wedding_Ring!");
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

            gameView.showPropose(App.getCurrentGame().getCurrentPlayer(),player);
            return new Result(true, "Your have accepted your marriage from " + username + "! Now you can live with him!");
        } else if (status.matches("\\s*-reject\\s*")) {
            mainPlayer.getFriendShips().computeIfPresent(player,
                (k, pair) -> new Pair<>(0, pair.second()));
            player.getFriendShips().computeIfPresent(mainPlayer,
                (k, pair) -> new Pair<>(0, pair.second()));

            player.updateFriendShips(App.getCurrentGame().getCurrentPlayer());
            App.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
            player.setRejectionBuff(new Buff(BuffType.REJECT_BUFF, 7, 100));
            player.getInventory().addGood(new ArrayList<>(Arrays.asList(mainPlayer.getMarriageList().get(player))));
            player.getNews().add(mainPlayer.getUser().getUsername() + " has rejected your marriage!");
            mainPlayer.getFriendShips().remove(player);

            gameView.showRejection(App.getCurrentGame().getCurrentPlayer(), player);
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
                if (isCloseEnough(npcName)) {
                    npc.getFriendship();
                    String talk = npc.npcDialogs();
                    return new Result(true, talk);
                }
            }
        }
        return new Result(true, "Too far away. Approach the NPC to speak.");
    }

    public Boolean isCloseEnough(String npcName) {
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                if ((abs(npc.getType().getCoordinate().getX() -
                    App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()) == 1 ||
                    (abs(npc.getType().getCoordinate().getX() -
                        App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()) == 0))
                    &&
                    (abs(npc.getType().getCoordinate().getY() -
                        App.getCurrentGame().getCurrentPlayer().getCoordinate().getY()) == 1 ||
                        abs(npc.getType().getCoordinate().getY() -
                            App.getCurrentGame().getCurrentPlayer().getCoordinate().getY()) == 0)) {
                    return true;
                }
            }
        }
        return false;
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
                return new Result(true, "You sent a " + itemName + " to " + npcName);

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

    public String getQuests(String npcName) {
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                if (npc.getFriendship().getAvailableQuests().contains(1)) {
                    return (npc.getType().getRequests().getFirst().first().getName() + " Count: " +
                        npc.getType().getRequests().getFirst().second());
                }
                if (npc.getFriendship().getAvailableQuests().contains(2)) {
                    return (npc.getType().getRequests().get(1).first().getName() + " " +
                        npc.getType().getRequests().get(1).second());
                }
                if (npc.getFriendship().getAvailableQuests().contains(3)) {
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
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (isCloseEnough(npc.getType().getName())) {
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

    private Table leaderboardTable;
    private LeaderboardSortType currentSortType = LeaderboardSortType.POINTS;
    private float refreshTimer = 0f;

    private void refreshLeaderboardTable() {
        ArrayList<Player> players = new ArrayList<>(App.getCurrentGame().getPlayers());
        Skin skin = Assets.getInstance().getSkin();

        // Sort by current type
        switch (currentSortType) {
            case USERNAME -> players.sort(Comparator.comparing(p -> p.getUser().getUsername()));
            case BALANCE -> players.sort(Comparator.comparingInt((Player p) -> p.getWallet().getBalance()).reversed());
            case POINTS -> players.sort(Comparator.comparingInt(Player::getPoints).reversed());
            case SKILL -> players.sort(Comparator.comparingInt((Player p) -> p.getSkill().getOverallSkillLevel()).reversed());
        }

        leaderboardTable.clear();

        // Header row (all Labels)
        leaderboardTable.add(new Label("Rank", skin)).padRight(10);
        leaderboardTable.add(new Label("Username", skin)).padRight(30);
        leaderboardTable.add(new Label("Balance", skin)).padRight(30);
        leaderboardTable.add(new Label("Points", skin)).padRight(30);
        leaderboardTable.add(new Label("Skill", skin)).padRight(30);
        leaderboardTable.row();

        // Player rows
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            int rank = i + 1;
            String name = p.getUser().getUsername();

            Color rowColor = switch (rank) {
                case 1 -> Color.GOLD;
                case 2 -> Color.LIGHT_GRAY;
                case 3 -> new Color(0.8f, 0.5f, 0.2f, 1); // bronze-ish
                default -> App.getCurrentGame().getCurrentPlayer().equals(p) ? Color.SKY : Color.WHITE;
            };

            Label.LabelStyle style = new Label.LabelStyle(skin.get(Label.LabelStyle.class).font, rowColor);

            leaderboardTable.add(new Label(String.valueOf(rank), style)).padRight(10);
            leaderboardTable.add(new Label(name, style)).padRight(30);
            leaderboardTable.add(new Label(String.valueOf(p.getWallet().getBalance()), style)).padRight(30);
            leaderboardTable.add(new Label(String.valueOf(p.getPoints()), style)).padRight(30);
            leaderboardTable.add(new Label(String.valueOf(p.getSkill().getOverallSkillLevel()), style)).padRight(30);
            leaderboardTable.row();
        }
    }
    private int currentTab = -1;
    private void refreshLeaderBoard(){
        refreshTimer += Gdx.graphics.getDeltaTime();
        if (currentTab == 4 && refreshTimer >= 1f) { // refresh every second
            refreshLeaderboardTable();
            refreshTimer = 0f;
        }
    }

    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
    }

    public GameView getGameView() {
        return gameView;
    }
}

