package com.StardewValley.server.controllers;

import com.StardewValley.client.Main;
import com.StardewValley.client.AppClient;
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

    private WorldController worldController;
    private PlayerController playerController;
    private InventoryController inventoryController;
    private ClockController clockController;
    private FriendshipController friendshipController;

    private GameMenuView view;
    private GameView gameView;
    private FridgeController fridgeController;
    private CookingController cookingController;
    private CraftingController craftingController;

    public GameController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public Message handleMessage(Message message) {
        return null;
    }

    public void setView(GameMenuView view) {
        this.view = view;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
        worldController = new WorldController();
        playerController = new PlayerController();
        inventoryController = new InventoryController(gameView);
        clockController = new ClockController();
        fridgeController = new FridgeController(gameView);
        cookingController = new CookingController(gameView);
        craftingController = new CraftingController(gameView);
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

    public FridgeController getFridgeController() {
        return fridgeController;
    }

    public CookingController getCookingController() {
        return cookingController;
    }

    public CraftingController getCraftingController() {
        return craftingController;
    }

    public void handleGame() {
        handleInput();
        worldController.updateWorld();
        playerController.updatePlayer();
        inventoryController.updateInventory();
        clockController.update();
        fridgeController.updateFridge();
        friendshipController.update();
    }

    public void handleInput() {
        if (gameView.getCheatWindow() != null)
            return;

        Player player = AppClient.getCurrentGame().getCurrentPlayer();
        player.setPlayerDirection(-1);

        if(Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            AppClient.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(1000);
        }

        boolean flag = false;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (tileValidity(AppClient.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX(), player.getCoordinate().getY() + 1))) {
                player.setCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY() + 1));
                player.setPlayerDirection(0);
                player.getInHandGoodSprite().setPosition(player.getCoordinate().getX() * gameView.getScaledSize(),
                    player.getCoordinate().getY() * gameView.getScaledSize() + 23);
            }
            flag = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (tileValidity(AppClient.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX() - 1, player.getCoordinate().getY()))) {
                player.setCoordinate(new Coordinate(player.getCoordinate().getX() - 1, player.getCoordinate().getY()));
                player.setPlayerDirection(1);
                player.getInHandGoodSprite().setPosition(player.getCoordinate().getX() * gameView.getScaledSize() - 20,
                    player.getCoordinate().getY() * gameView.getScaledSize() + 23);
                if (!player.getInHandGoodSprite().isFlipX())
                    player.getInHandGoodSprite().flip(true, false);
            }
            flag = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (tileValidity(AppClient.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX(), player.getCoordinate().getY() - 1))) {
                player.setCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY() - 1));
                player.setPlayerDirection(2);
                player.getInHandGoodSprite().setPosition(player.getCoordinate().getX() * gameView.getScaledSize(),
                    player.getCoordinate().getY() * gameView.getScaledSize() + 23);
            }
            flag = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (tileValidity(AppClient.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX() + 1, player.getCoordinate().getY()))) {
                player.setCoordinate(new Coordinate(player.getCoordinate().getX() + 1, player.getCoordinate().getY()));
                player.setPlayerDirection(3);
                player.getInHandGoodSprite().setPosition(player.getCoordinate().getX() * gameView.getScaledSize() + 20,
                    player.getCoordinate().getY() * gameView.getScaledSize() + 23);
                if (player.getInHandGoodSprite().isFlipX())
                    player.getInHandGoodSprite().flip(true, false);
            }
            flag = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            nextTurn();
            inventoryController.playerChangedInventory();
        }
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
            for (FarmBuilding building : AppClient.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
                for (Animal animal : building.getAnimals()) {
                    if ((abs(animal.getCoordinate().getX() -
                        AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getX()) <= 2) &&
                        (abs(animal.getCoordinate().getY() -
                            AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getY()) <= 2)) {
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
            Tile selectedTile = AppClient.getCurrentGame().getMap().findTileByXY(AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getX()
                , AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getY());

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
            Tile selectedTile = AppClient.getCurrentGame().getMap().findTileByXY(AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getX()
                , AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
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
            Tile selectedTile = AppClient.getCurrentGame().getMap().findTileByXY(AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getX()
                , AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
            if (selectedTile.getTileType() == TileType.PLAYER_BUILDING) {
                if (!gameView.getIsCraftingOpen()) {
                    gameView.initCraftingWindow();
                } else {
                    gameView.getCraftingWindow().remove();
                }
                gameView.setIsCraftingOpen(!gameView.getIsCraftingOpen());
            }
        }

        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(
            8, 9, 10, 11, 12, 13, 14, 15, 16, 7, 69, 70
        ));
        for (int i = 0; i < arr.size(); i++) {
            if (Gdx.input.isKeyJustPressed(arr.get(i))) {
                AppClient.getCurrentGame().getCurrentPlayer().setInHandGood(
                    AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i));
                break;
            }
        }

        if (flag)
            AppClient.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(0.25);
    }



    private ArrayList<Good> cursorGoods = null;
    private Image cursorImage = new Image();

    private ArrayList<Window> createWindows() {
        ArrayList<Window> inventoryWindows = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
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

                    int inventorySize = AppClient.getCurrentGame().getCurrentPlayer().getInventory().getSize();
                    ArrayList<ArrayList<Good>> inventoryData = AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList();
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
                                                AppClient.setCursorFromImage(cursorGoods.get(0).getType().imagePath());
                                                rebuildInventoryUI(inventoryTable, inventoryElements);
                                            }
                                        } else {
                                            // Drop or swap goods
                                            if (goods.isEmpty()) {
                                                goods.addAll(cursorGoods);  // drop all goods held on cursor into slot
                                                cursorGoods.clear();
                                                cursorGoods = null;
                                                AppClient.setCursor();  // reset cursor
                                                rebuildInventoryUI(inventoryTable, inventoryElements);
                                            } else {
                                                // Swap goods: swap whole stacks
                                                ArrayList<Good> temp = new ArrayList<>(goods);
                                                goods.clear();
                                                goods.addAll(cursorGoods);

                                                cursorGoods.clear();
                                                cursorGoods.addAll(temp);

                                                AppClient.setCursorFromImage(cursorGoods.get(0).getType().imagePath());
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
                                cursorGoods.clear();
                                cursorGoods = null;
                                AppClient.setCursor(); // Reset to default cursor
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

                    for (NPC npc : AppClient.getCurrentGame().getNPCs()) {
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
                    for (Player p : AppClient.getCurrentGame().getPlayers()) {
                        if (!p.equals(AppClient.getCurrentGame().getCurrentPlayer())) {
                            rightSideTable.row().padBottom(12);
                            Table row = new Table().right();

                            Texture playerTxr = new Texture("GameAssets\\Main_Inventory\\Player.png");
                            Image npc_img = new Image(playerTxr);

                            Pair<Integer, Integer> friendship = p.getFriendShips().get(AppClient.getCurrentGame().getCurrentPlayer());
                            int friendshipLevel = friendship != null ? friendship.second() : 0;

                            Texture hearts = new Texture("GameAssets\\Main_Inventory\\" + friendshipLevel + ".png");
                            Image hearts_img = new Image(hearts);
                            int newWidth = hearts.getWidth() * 2;
                            int newHeight = hearts.getHeight() * 2;

                            row.add(npc_img).padRight(10).size(40, 40).left();

                            String name = p.getUsername();
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
                    Skill skill = AppClient.getCurrentGame().getCurrentPlayer().getSkill();
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

                    ScrollPane mapPane = AppClient.getCurrentGame().getMap().createGraphicalMap();
                    window.add(mapPane).expand().fill().colspan(2);
                    window.row();

                    break;
                case 4:
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

        int inventorySize = AppClient.getCurrentGame().getCurrentPlayer().getInventory().getSize();
        ArrayList<ArrayList<Good>> inventoryData = AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList();

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
                                    AppClient.setCursorFromImage(cursorGoods.get(0).getType().imagePath());
                                    rebuildInventoryUI(inventoryTable, inventoryElements);
                                }
                            } else {
                                // Drop or swap entire stack
                                if (goods.isEmpty()) {
                                    // Place cursor stack into empty slot
                                    goods.addAll(cursorGoods);
                                    cursorGoods.clear();
                                    cursorGoods = null;
                                    AppClient.setCursor();
                                    rebuildInventoryUI(inventoryTable, inventoryElements);
                                } else {
                                    // Swap stacks
                                    ArrayList<Good> temp = new ArrayList<>(goods);
                                    goods.clear();
                                    goods.addAll(cursorGoods);
                                    cursorGoods.clear();
                                    cursorGoods.addAll(temp);
                                    AppClient.setCursorFromImage(cursorGoods.get(0).getType().imagePath());
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
            (tile.getTileType() == TileType.GREEN_HOUSE && !AppClient.getCurrentGame().getCurrentPlayer().getFarm().getGreenHouse().isAvailable()))
            return false;
        return true;
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

//        for (Player player : AppClient.getCurrentGame().getPlayers()) {
//            player.setPlaying(false);
//            player.increaseEarnedPoints(player.getPoints());
//            player.maxMaxPoints(player.getPoints());
//            player.increaseGamePlay(AppClient.getCurrentGame().getDateTime().getDays());
//        }
        //TODO

        AppServer.getGames().remove(AppClient.getCurrentGame());
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
        AppClient.setCurrentGame(null);
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(Assets.getInstance().getSkin()));
        return new Result(true, "Game terminated successfully!");

    }

    public Result nextTurn() {
        AppClient.getCurrentGame().nextPlayer();
        return new Result(true, "");
    }


    // Nader
    // date & time methods
    public Result time() {
        return new Result(true, AppClient.getCurrentGame().getDateTime().getTime() + ":00");
    }

    public Result date() {
        return new Result(true,
            AppClient.getCurrentGame().getDateTime().getYear() + "/" +
                AppClient.getCurrentGame().getDateTime().getSeasonOfYearInt() + "/" +
                AppClient.getCurrentGame().getDateTime().getDayOfSeason());
    }

    public Result dateTime() {
        return new Result(true,
            AppClient.getCurrentGame().getDateTime().getYear() + "/" +
                AppClient.getCurrentGame().getDateTime().getSeasonOfYearInt() + "/" +
                AppClient.getCurrentGame().getDateTime().getDayOfSeason() + "/" +
                AppClient.getCurrentGame().getDateTime().getTime() + ":00");
    }

    public Result dayOfTheWeek() {
        return new Result(true, AppClient.getCurrentGame()
            .getDateTime().getDayOfWeek());
    }

    public Result showSeason() {
        return new Result(true, AppClient.getCurrentGame().getDateTime()
            .getSeasonOfYear().getName());
    }

    public Result cheatAdvanceTime(String hour) {
        int hourInt = Integer.parseInt(hour);
        for (int i = 0; i < hourInt; i++) {
            AppClient.getCurrentGame().getDateTime().timeFlow();
        }
        return new Result(true, "You have cheat advance time for " + hourInt + " hours!");
    }

    public Result cheatAdvanceDate(String date) {
        int dateInt = Integer.parseInt(date);
        for (int i = 0; i < dateInt * 13; i++) {
            AppClient.getCurrentGame().getDateTime().timeFlow();
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
        AppClient.getCurrentGame().getWeather().thunder(xInt, yInt);
        return new Result(true, "");
    }

    public Result weather() {
        return new Result(true, AppClient.getCurrentGame().getWeatherName());
    }

    public Result weatherForecast() {
        return new Result(true, AppClient.getCurrentGame().getTomorrow().weatherForecast().getName());
    }

    public Result cheatWeatherSet(String weather) {
        weather = weather.trim();

        switch (weather) {
            case "Sunny":
                AppClient.getCurrentGame().cheatSetWeather(WeatherType.Sunny.getWeather());
                break;
            case "Rain":
                AppClient.getCurrentGame().cheatSetWeather(WeatherType.Rain.getWeather());
                break;
            case "Storm":
                AppClient.getCurrentGame().cheatSetWeather(WeatherType.Storm.getWeather());
                break;
            case "Snow":
                AppClient.getCurrentGame().cheatSetWeather(WeatherType.Snow.getWeather());
                break;
        }
        return new Result(true, "Weather set to " + weather);
    }

    public Result greenHouseBuild() {
        Game game = AppClient.getCurrentGame();
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
        Tile tile = AppClient.getCurrentGame().getMap().findTile(goal);
        if (tile == null)
            return new Result(false, "Goal tile not found!");

        if (tile.getTileType() == TileType.GAME_BUILDING &&
            !AppClient.getCurrentGame().getMap().findGameBuilding(goal).isInWorkingHours()) {
            return new Result(false, AppClient.getCurrentGame().getMap().findGameBuilding(goal).getName() + " hours have ended for today!");
        }


        ArrayList<Pair<Integer, Coordinate>> path = AStar.findPath(AppClient.getCurrentGame().getMap(),
            AppClient.getCurrentGame().getCurrentPlayer().getCoordinate(), goal);

        if (path == null)
            return new Result(false, "No path " + goal + " found!");

        int energyConsumed = path.getLast().first() / 20;
        int playerEnergy = AppClient.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft();
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
            AppClient.getCurrentGame().getCurrentPlayer().setCoordinate(coordinate);
            AppClient.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(playerEnergy);
            AppClient.getCurrentGame().getCurrentPlayer().getEnergy().setAwake(false);
            return new Result(true, "Your energy was enough to walk to " + goal +
                " location! Now your are fainted & your daily energy is 0!");
        } else {
            AppClient.getCurrentGame().getCurrentPlayer().setCoordinate(goal);
            AppClient.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(energyConsumed);
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
        AppClient.getCurrentGame().getMap().printMap(IntX, IntY, IntSize);
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
        if (AppClient.getCurrentGame().
            getCurrentPlayer().getEnergy().getDayEnergyLeft() > 3000) {
            return new Result(true, ("INFINITE"));
        }
        return new Result(true, (AppClient.getCurrentGame().
            getCurrentPlayer().getEnergy()).getDayEnergyLeft() + "\n");
    }

    public Result cheatEnergySet(String value) {
        value = value.trim();

        int valueInt = Integer.parseInt(value);
        AppClient.getCurrentGame().getCurrentPlayer().getEnergy().setDayEnergyLeft(valueInt);
        AppClient.getCurrentGame().getCurrentPlayer().getEnergy().setTurnValueLeft(50);
        return new Result(true, "Your energy been set to " + valueInt);
    }

    public Result cheatEnergyUnlimited() {
        AppClient.getCurrentGame().getCurrentPlayer().getEnergy().setMaxDayEnergy(Integer.MAX_VALUE);
        AppClient.getCurrentGame().getCurrentPlayer().getEnergy().setMaxTurnEnergy(Integer.MAX_VALUE);
        AppClient.getCurrentGame().getCurrentPlayer().getEnergy().setTurnValueLeft(Integer.MAX_VALUE);
        AppClient.getCurrentGame().getCurrentPlayer().getEnergy().setDayEnergyLeft(Integer.MAX_VALUE);
        return new Result(true, "Energy set to Unlimited");
    }

    public Result inventoryTrashItem(String itemName, String number) {
        itemName = itemName.trim();
        number = number.trim();

        ArrayList<Good> goods = AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(itemName);
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

        int finalPrice = ToolFunctions.useTrashCan(AppClient.getCurrentGame().getCurrentPlayer().getTrashCan(), totalPrice);
        AppClient.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(finalPrice);
        return new Result(true, "Your earned " + finalPrice + " coins from putting your good into trash can!");
    }

    public Result inventoryShow() {
        StringBuilder inventoryList = new StringBuilder();
        for (ArrayList<Good> good : AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
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
        for (ArrayList<Good> goods : AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getFirst().getName().equals(toolName) && goods.size() >= 1) {
                AppClient.getCurrentGame().getCurrentPlayer().setInHandGood(goods);
                flag = true;
                break;
            }
            if (!flag && toolName.equals("Trash_Can")) {
                AppClient.getCurrentGame().getCurrentPlayer().setInHandGood(new ArrayList<>(Arrays.asList(
                    AppClient.getCurrentGame().getCurrentPlayer().getTrashCan()
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
        if (AppClient.getCurrentGame().getCurrentPlayer().getInHandGood().getLast() instanceof Tool) {
            return new Result(true, "Your current tool: " + AppClient.getCurrentGame().getCurrentPlayer().getInHandGood().getLast().getName());
        } else {
            return new Result(true, "You don't have tool in your hand!");
        }
    }

    public Result toolsShowAvailable() {
        StringBuilder toolsList = new StringBuilder();
        toolsList.append("List of available tools:\n");
        for (ArrayList<Good> goods : AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getFirst() instanceof Tool) {
                toolsList.append("\t").append(goods.getFirst().getName()).append("\n");
            }
        }

        return new Result(true, toolsList.toString());
    }

    public Result toolsUpgrade(String toolName) {
        toolName = toolName.trim();

        Game game = AppClient.getCurrentGame();
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
        if (AppClient.getCurrentGame().getCurrentPlayer().getInHandGood().getLast() instanceof Tool) {
            Tool tool = (Tool) AppClient.getCurrentGame().getCurrentPlayer().getInHandGood().getLast();
            coordinate = new Coordinate(coordinate.getX() + AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getX(),
                coordinate.getY() + AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
            if (coordinate == null)
                return new Result(false, "Direction not recognized");

            if (AppClient.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft() < tool.getType().getEnergy())
                return new Result(false, "You don't have enough energy to use " + tool.getName() + "!");
            if (AppClient.getCurrentGame().getMap().findTile(coordinate) == null)
                return new Result(false, "Tile not found");

            AppClient.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(tool.getType().getEnergy());

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
        Tile tile = AppClient.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate, AppClient.getCurrentGame().getCurrentPlayer());
        if (tile == null)
            return new Result(false, "You don't have access to this tile!");

        if (tile.getTileType() != TileType.PLOWED_FARM && tile.getTileType() != TileType.GREEN_HOUSE)
            return new Result(false, "Selected Tile is not Plowed or GreenHouse for planting!");
        if (tile.getTileType().equals(TileType.GREEN_HOUSE)) {
            if (AppClient.getCurrentGame().getCurrentPlayer().getFarm().getGreenHouse().isAvailable()) {
                return new Result(false, "You haven't built your Green House yet");
            }
        }

        ArrayList<Good> seeds = AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(seed);
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
                    if (season.equals(AppClient.getCurrentGame().getDateTime().getSeasonOfYear())) {
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
        Tile tile = AppClient.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate,
            AppClient.getCurrentGame().getCurrentPlayer());

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
        Tile tile = AppClient.getCurrentGame().getCurrentPlayer().getFarm().checkInFarm(coordinate,
            AppClient.getCurrentGame().getCurrentPlayer());

        if (tile == null)
            return new Result(false, "You don't have access to this tile!");

        ArrayList<Good> fertilizer = AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(fertilizerName);
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
        Tool tool = (Tool) AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ToolType.WATERING_CAN.getName()).getFirst();
        return new Result(true, "Your watering can have capacity:" + tool.capacity);
    }


    // Nader
    // crafting methods
    public Result showCraftingRecipes() {

        for (CraftingRecipe craftingRecipe : AppClient.getCurrentGame().getCurrentPlayer().getCraftingRecipes()) {
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
        for (CraftingRecipe craftingRecipe : AppClient.getCurrentGame().getCurrentPlayer().getCraftingRecipes()) {
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
        for (ArrayList<Good> goodArrayList : AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
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

        for (Tile tile : AppClient.getCurrentGame().getMap().getTiles()) {
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

        Player player = AppClient.getCurrentGame().getCurrentPlayer();
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

        TileType tileType = AppClient.getCurrentGame().getMap().findTileType(AppClient.getCurrentGame().getCurrentPlayer().getCoordinate());
        if (!tileType.equals(TileType.PLAYER_BUILDING)) {
            return new Result(false, "You are not in Home!");
        }

        Fridge fridge = AppClient.getCurrentGame().getCurrentPlayer().getFridge();
        Inventory inventory = AppClient.getCurrentGame().getCurrentPlayer().getInventory();
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
        for (CookingRecipe cookingRecipe : AppClient.getCurrentGame().getCurrentPlayer().getCookingRecipes()) {
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

        for (CookingRecipe cookingRecipe : AppClient.getCurrentGame().getCurrentPlayer().getCookingRecipes()) {
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
            if (AppClient.getCurrentGame().getCurrentPlayer().getInventory().howManyInInventoryByType(ingredientType) < requiredAmount) {
                return new Result(false, "Not enough " + ingredientType.getName() +
                    " (needed: " + requiredAmount + ")");
            }
        }

        for (Pair<GoodType, Integer> ingredient : recipe.getType().getIngredients()) {
            GoodType ingredientType = (GoodType) ingredient.first();
            int requiredAmount = ingredient.second();
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ingredientType, requiredAmount);
        }
        Good food = Good.newGood(recipe.getType().getGoodType());
        AppClient.getCurrentGame().getCurrentPlayer().getSkill().increaseCookingPoints(10);
        AppClient.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(3);
        if (AppClient.getCurrentGame().getCurrentPlayer().getInventory().addGood(Good.newGoods(recipe.getType().getGoodType(), 1))) {
            return new Result(true, "You put " + food.getName() + " into the inventory");
        }
        return new Result(false, "Your inventory is full");

    }


    public Result eat(String foodName) {
        foodName = foodName.trim();
        Good food = null;
        for (ArrayList<Good> goodArrayList : AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
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
            AppClient.getCurrentGame().getCurrentPlayer().eat(food);
        } else if (food instanceof Artisan) {
            AppClient.getCurrentGame().getCurrentPlayer().eat(food);
        } else if (food instanceof Food) {
            AppClient.getCurrentGame().getCurrentPlayer().eat(food);
        } else {
            return new Result(false, "NO NO! What are you trying to eat mmd Jan?");
        }
        return new Result(true, "Khosmaz, Yum Yum!");
    }


    // Parsa
    // Animals & Fishing methods
    public Result buildBuilding(String buildingName, String x, String y) {
        Coordinate coordinate = AppClient.getCurrentGame().getCurrentPlayer().getCoordinate();
        Tile tile = AppClient.getCurrentGame().getMap().findTile(coordinate);
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to show all products!");
        if (!AppClient.getCurrentGame().getMap().findGameBuilding(coordinate).isInWorkingHours()) {
            return new Result(false, AppClient.getCurrentGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        buildingName = buildingName.trim();
        x = x.trim();
        y = y.trim();

        CarpenterShop carpenterShop = (CarpenterShop) AppClient.getCurrentGame().getMap().getCarpenterShop();
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
            if (AppClient.getCurrentGame().getCurrentPlayer().getWallet().getBalance() > targetType.getCost()) {
                if (targetType.getWood() < AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                    .howManyInInventoryByType(ProductType.WOOD) &&
                    targetType.getStone() < AppClient.getCurrentGame().getCurrentPlayer().getInventory()
                        .howManyInInventoryByType(ProductType.STONE)) {
                    Coordinate startCoordinate = new Coordinate((int) Integer.parseInt(x) - targetType.getSize().first() / 2,
                        (int) Integer.parseInt(y) - targetType.getSize().second() / 2);

                    boolean validSpace = true;
                    for (int sX = 0; sX < targetType.getSize().first(); sX++) {
                        for (int sY = 0; sY < targetType.getSize().second(); sY++) {
                            Tile tempTile = AppClient.getCurrentGame().getMap().findTileByXY(sX + startCoordinate.getX(), sY + startCoordinate.getY());
                            if (!tempTile.getTileType().equals(TileType.FARM)) {
                                validSpace = false;
                            }
                        }
                    }
                    if (!validSpace) {
                        return new Result(false, "You can't build this building here!");
                    } else {
                        AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ProductType.WOOD, targetType.getWood());
                        AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ProductType.STONE, targetType.getStone());
                        AppClient.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(targetType.getCost());
                        FarmBuilding newBuilding = carpenterShop.buildingFarmBuilding(targetType, startCoordinate);
                        AppClient.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings().add(newBuilding);
                        for (int sX = 0; sX < targetType.getSize().first(); sX++) {
                            for (int sY = 0; sY < targetType.getSize().second(); sY++) {
                                AppClient.getCurrentGame().getMap()
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
                Tile tempTile = AppClient.getCurrentGame().getMap().findTileByXY(sX + coordinate.getX(), sY + coordinate.getY());
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
        Coordinate coordinate = AppClient.getCurrentGame().getCurrentPlayer().getCoordinate();
        Tile tile = AppClient.getCurrentGame().getMap().findTile(coordinate);
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to show all products!");
        if (!AppClient.getCurrentGame().getMap().findGameBuilding(coordinate).isInWorkingHours()) {
            return new Result(false, AppClient.getCurrentGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }


        if (animalType == null || animalName == null || animalType.trim().isEmpty() || animalName.trim().isEmpty()) {
            return new Result(false, "Invalid animal type or name");
        }
        animalType = animalType.trim();
        animalName = animalName.trim();

        MarnieRanch marnieRanch = (MarnieRanch) AppClient.getCurrentGame().getMap().getMarnieRanch();
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
        if (AppClient.getCurrentGame().getCurrentPlayer().getWallet().getBalance() < animalTypeEnum.getPrice()) {
            return new Result(false, "You don't have enough Money!");
        }

        FarmBuilding suitableBuilding = null;
        for (FarmBuilding farmBuilding : AppClient.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
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

        AppClient.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(animalTypeEnum.getPrice());
        animal.setLocatedPLace(suitableBuilding);
        AppClient.getCurrentGame().getMap().allAnimals().add(animal);
        int x = (int) (suitableBuilding.getStartCordinate().getX() + Math.random() * 2);
        int y = (int) (suitableBuilding.getEndCordinate().getY() - Math.random() * 2);
        animal.setCoordinate(new Coordinate(x, y));

        return new Result(true, "A " + animalType + " named " + animalName + " has been added to your farm!");
    }

    public Result petAnimal(String animalName) {
        animalName = animalName.trim();
        Animal animal = null;
        for (FarmBuilding b : AppClient.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
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
        AppClient.getCurrentGame().getCurrentPlayer().showAnimals();
        return new Result(true, "");
    }

    public Result cheatSetAnimalFriendship(String animalName, String amount) {
        animalName = animalName.trim();
        animalName = animalName.trim();

        int amountInt = Integer.parseInt(amount);
        Animal animal = AppClient.getCurrentGame().getMap().findAnimalByName(animalName);
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

        Animal animal = AppClient.getCurrentGame().getMap().findAnimalByName(animalName);
        if (animal == null) {
            return new Result(false, "Animal not found");
        }
        animal.shepherdAnimal(new Coordinate(Integer.parseInt(x), Integer.parseInt(y)));
        return new Result(true, "You shepherd " + animalName);
    }

    public Result feedHay(String animalName) {
        animalName = animalName.trim();

        Animal animal = AppClient.getCurrentGame().getMap().findAnimalByName(animalName);
        if (!AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventoryBoolean(ProductType.HAY)) {
            return new Result(false, "You don't have enough Hay");
        } else {
            AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ProductType.HAY, 1);
            animal.setFed(true);
        }
        return new Result(true, "You fed " + animalName);
    }

    public Result animalProductionList() {
        for (FarmBuilding building : AppClient.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
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
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().addGoodByObject(product);
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
        for (FarmBuilding building : AppClient.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
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
        AppClient.getCurrentGame().getMap().allAnimals().remove(animal);

        AppClient.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(animal.getAnimalSellPrice());

        return new Result(true, "You sold " + animal.getAnimalSellPrice());
    }

    public Animal findAnimal(String animalName) {
        Animal animal;
        for (FarmBuilding building : AppClient.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
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
        if (AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(fishingPole) == null) {
            return new Result(true, "You don't have this fishing pole");
        }
        for (Coordinate coordinate : Coordinate.coordinates) {
            Coordinate c = Coordinate.checkAround(AppClient.getCurrentGame().getCurrentPlayer().getCoordinate(), coordinate);
            Tile tile = AppClient.getCurrentGame().getMap().findTile(c);
            if (tile.getTileType() == TileType.WATER) {
                Weather weather = AppClient.getCurrentGame().getWeather();
                Skill skill = AppClient.getCurrentGame().getCurrentPlayer().getSkill();
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
        Coordinate coordinate = AppClient.getCurrentGame().getCurrentPlayer().getCoordinate();
        Tile tile = AppClient.getCurrentGame().getMap().findTile(coordinate);
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to show all products!");
        if (!AppClient.getCurrentGame().getMap().findGameBuilding(coordinate).isInWorkingHours()) {
            return new Result(false, AppClient.getCurrentGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        GameBuilding building = AppClient.getCurrentGame().getMap().findGameBuilding(coordinate);
        return new Result(true, "");
    }

    public Result showAllAvailableProducts() {
        Coordinate coordinate = AppClient.getCurrentGame().getCurrentPlayer().getCoordinate();
        Tile tile = AppClient.getCurrentGame().getMap().findTile(AppClient.getCurrentGame().getCurrentPlayer().getCoordinate());
        if (tile.getTileType() != TileType.GAME_BUILDING)
            return new Result(false, "You should be in a game building to show all available products!");
        if (!AppClient.getCurrentGame().getMap().findGameBuilding(coordinate).isInWorkingHours()) {
            return new Result(false, AppClient.getCurrentGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        GameBuilding building = AppClient.getCurrentGame().getMap().findGameBuilding(coordinate);
        return new Result(true, "");
    }

    public Result purchase(String productName, String count, Coordinate coordinate) {
        productName = productName.trim();
        count = count.trim();


        if (!AppClient.getCurrentGame().getMap().findGameBuilding(coordinate).isInWorkingHours()) {
            return new Result(false, AppClient.getCurrentGame().getMap().findGameBuilding(coordinate).getName() + " hours have ended for today!");
        }

        GameBuilding building = AppClient.getCurrentGame().getMap().findGameBuilding(coordinate);
        return building.purchase(productName, count);
    }

    public Result cheatAddDollars(String count) {
        count = count.trim();

        int amount = Integer.parseInt(count);
        AppClient.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(amount);
        return new Result(true, count + "G added to your wallet!");
    }

    public Result sell(String productName, String count) {
        productName = productName.trim();
        count = count.trim();

        ArrayList<Good> goods = AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(productName);
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
                AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getX() + Coordinate.coordinates.get(i).getX(),
                AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getY() + Coordinate.coordinates.get(i).getY());

            Tile tile = AppClient.getCurrentGame().getMap().findTile(coordinate);
            if (tile != null && tile.findGood("ShippingBin") != null) {
                ArrayList<Good> newGoods = new ArrayList<>(goods);
                for (int j = 0; j < quantity; j++) {
                    newGoods.add(goods.getLast());
                    goods.removeLast();
                }

                ShippingBin shippingBin = (ShippingBin) tile.findGood("ShippingBin");
                shippingBin.addGood(newGoods, AppClient.getCurrentGame().getCurrentPlayer());
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
        for (Player player : AppClient.getCurrentGame().getPlayers()) {
            if (AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().get(player) == null)
                continue;

            list.append("\t").append(player.getUsername()).append(": \n");
            list.append("\t\tLevel: ").append(AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).first()).append("\n");
            list.append("\t\tValue: ").append(AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).second()).append("\n");
        }
        return new Result(true, list.toString());
    }

    public Result talk(String username, String message) {
        username = username.trim();
        message = message.trim();

        Player player = AppClient.getCurrentGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " for talking!");

        player.getTalkHistory().add(new Pair<>(
            AppClient.getCurrentGame().getCurrentPlayer(),
            "\t<" + AppClient.getCurrentGame().getCurrentPlayer().getPlayerUsername() + "> " + dateTime().message() + ": " + message
        ));

        AppClient.getCurrentGame().getCurrentPlayer().getTalkHistory().add(new Pair<>(
            player,
            "\t<" + AppClient.getCurrentGame().getCurrentPlayer().getPlayerUsername() + "> " + dateTime().message() + ": " + message
        ));

        try {
            if (AppClient.getCurrentGame().getCurrentPlayer().getIsInteracted().get(player).equals(true)) {

            }
        } catch (Exception e) {
            return new Result(false, "You are not interacted!");
        }

        if (AppClient.getCurrentGame().getCurrentPlayer().getIsInteracted().get(player).equals(false)) {

            // If they are couple
            if (AppClient.getCurrentGame().getCurrentPlayer().getMarried() == player) {
                AppClient.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                System.out.println("You and your partner got 50 extra energy!");
            } else {
                player.getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + 20));
                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(player,
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + 20));

                player.updateFriendShips(AppClient.getCurrentGame().getCurrentPlayer());
                AppClient.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
                System.out.println("Your friendship value with " + username + " is increased to " +
                    AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).second());
            }

            AppClient.getCurrentGame().getCurrentPlayer().getIsInteracted().put(player, true);
            player.getIsInteracted().put(AppClient.getCurrentGame().getCurrentPlayer(), true);
        }

        return new Result(true, "You talked with " + username + "!");
    }

    public Result talkHistory(String username) {
        username = username.trim();

        StringBuilder list = new StringBuilder();
        list.append("Talk History:\n");
        for (Pair<Player, String> talk : AppClient.getCurrentGame().getCurrentPlayer().getTalkHistory()) {
            if (talk.first().getPlayerUsername().equals(username)) {
                list.append(talk.second()).append("\n");
            }
        }

        return new Result(true, list.toString());
    }

    public Result gift(String username, String item, String amount) {
        username = username.trim();
        amount = amount.trim();

        Player player = AppClient.getCurrentGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " for sending gift!");

        if (AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).first() < 1)
            return new Result(false, "Your friendship level with " + username + " should be more than 0");

        ArrayList<Good> goods = AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(item);
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
        player.getGiftList().add(new Pair<>(AppClient.getCurrentGame().getCurrentPlayer(), gift));
        player.getNews().add("A new gift has been added to your gift list from " + username + "!");

        return new Result(true, "Your gift has been sent to " + username + "!");
    }

    public Result giftList() {
        StringBuilder list = new StringBuilder();
        list.append("Gifts List:\n");
        int ptr = 1;
        for (Pair<Player, Gift> playerGiftPair : AppClient.getCurrentGame().getCurrentPlayer().getGiftList()) {
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
        if (giftNumber <= 0 || giftNumber > AppClient.getCurrentGame().getCurrentPlayer().getGiftList().size())
            return new Result(false, "There is no gift with that number in your gift list!");

        giftNumber--;
        Pair<Player, Gift> gift = AppClient.getCurrentGame().getCurrentPlayer().getGiftList().get(giftNumber);
        AppClient.getCurrentGame().getCurrentPlayer().getGiftList().remove(gift);
        AppClient.getCurrentGame().getCurrentPlayer().getInventory().addGood(gift.second().getList());
        AppClient.getCurrentGame().getCurrentPlayer().getGiftHistory().add(new Pair<>(gift.first(),
            "A gift from " + gift.first().getUsername()
                + " with " + gift.second().getList().size() + " amount of " + gift.second().getList().getFirst().getName() +
                " \nhave been given to you! Your rate : " + giftRate + " !"));

        gift.first().getGiftHistory().add(new Pair<>(AppClient.getCurrentGame().getCurrentPlayer(),
            "A gift with " + gift.second().getList().size() +
                " amount of " + gift.second().getList().getFirst().getName() +
                " \nhave been given to " + AppClient.getCurrentGame().getCurrentPlayer().getUsername() + " from you! " +
                AppClient.getCurrentGame().getCurrentPlayer().getUsername() + "'s rate : " + giftRate + " !"));
        gift.first().getNews().add(AppClient.getCurrentGame().getCurrentPlayer().getUsername() + " has rated your gift with amount " +
            giftRate + " !");

        if (AppClient.getCurrentGame().getCurrentPlayer().getIsInteracted().get(gift.first()).equals(false)) {
            if (AppClient.getCurrentGame().getCurrentPlayer().getMarried() == gift.first()) {
                AppClient.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(50);
                gift.first().getEnergy().increaseTurnEnergyLeft(50);

                System.out.println("You and your partner got 50 extra energy!");
            } else {
                int value = (giftRate - 3) * 30 + 15;
                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(gift.first(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + value));
                gift.first().getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + value));

                gift.first().updateFriendShips(AppClient.getCurrentGame().getCurrentPlayer());
                AppClient.getCurrentGame().getCurrentPlayer().updateFriendShips(gift.first());
                System.out.println("Your friendship value with " + gift.first().getUsername() + " is increased to " +
                    AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().get(gift.first()).second());
            }

            AppClient.getCurrentGame().getCurrentPlayer().getIsInteracted().put(gift.first(), true);
            gift.first().getIsInteracted().put(AppClient.getCurrentGame().getCurrentPlayer(), true);

        }


        return new Result(true, "Your have rated gift with number " + giftNum + " with rate " + rate + "!");
    }

    public Result giftHistory(String username) {
        username = username.trim();

        StringBuilder list = new StringBuilder();
        list.append("Gifts History:\n");
        int ptr = 1;
        for (Pair<Player, String> giftHistory : AppClient.getCurrentGame().getCurrentPlayer().getGiftHistory()) {
            if (giftHistory.first().getUsername().equals(username)) {
                list.append("\t").append(ptr++).append(". ").append(giftHistory.second()).append("\n");
            }
        }

        return new Result(true, list.toString());
    }

    public Result hug(String username) {
        username = username.trim();

        Player player = AppClient.getCurrentGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " to hug!");


        StringBuilder list = new StringBuilder();
        if (!AppClient.getCurrentGame().getCurrentPlayer().getIsInteracted().get(player)) {
            if (AppClient.getCurrentGame().getCurrentPlayer().getMarried() == player) {
                AppClient.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                list.append("You and your partner got 50 extra energy!\n");
            } else {
                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(player,
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + 60));
                player.getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                    (k, pair) -> new Pair<>(pair.first(), pair.second() + 60));

                player.updateFriendShips(AppClient.getCurrentGame().getCurrentPlayer());
                AppClient.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
                list.append("Your friendship value with " + username + " is increased to " +
                    AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).second() + "\n");
            }

            AppClient.getCurrentGame().getCurrentPlayer().getIsInteracted().put(player, true);
            player.getIsInteracted().put(AppClient.getCurrentGame().getCurrentPlayer(), true);

        }

        list.append("You hugged " + username + "!");
        return new Result(true, list.toString());
    }

    public Result flower(String username) {
        username = username.trim();

        Player player = AppClient.getCurrentGame().findPlayer(username);
        if (player == null) {
            return new Result(false, "Player not found!");
        }

        if (AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " to give flower!");

        if (AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().get(player).first() != 2 ||
            player.getFriendShips().get(AppClient.getCurrentGame().getCurrentPlayer()).first() != 2)
            return new Result(false, "You and " + username + " should have friendship level of 2!");

        boolean flag = false;
        ArrayList<Good> goods = AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(FarmingCropType.SUNFLOWER);
        if (goods != null) {
            Good good = goods.getLast();
            goods.removeLast();
            player.getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
            flag = true;
        }

        goods = AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(FarmingCropType.CAULIFLOWER);
        if (!flag && goods != null) {
            Good good = goods.getLast();
            goods.removeLast();
            player.getInventory().addGood(new ArrayList<>(Arrays.asList(good)));
            flag = true;
        }

        StringBuilder list = new StringBuilder();
        if (!flag)
            return new Result(false, "Your don't have any flower in your inventory to give to someone!");
        else if (!AppClient.getCurrentGame().getCurrentPlayer().getIsInteracted().get(player)) {
            if (AppClient.getCurrentGame().getCurrentPlayer().getMarried() == player) {
                AppClient.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(50);
                player.getEnergy().increaseTurnEnergyLeft(50);

                list.append("You and your partner got 50 extra energy!\n");
            } else {
                AppClient.getCurrentGame().getCurrentPlayer().getFriendShips().computeIfPresent(player,
                    (k, pair) -> new Pair<>(pair.first() + 1, pair.second()));
                player.getFriendShips().computeIfPresent(AppClient.getCurrentGame().getCurrentPlayer(),
                    (k, pair) -> new Pair<>(pair.first() + 1, pair.second()));

                player.updateFriendShips(AppClient.getCurrentGame().getCurrentPlayer());
                AppClient.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
                list.append("Your friendship level with " + username + " has been increased to 3!\n");
            }

            AppClient.getCurrentGame().getCurrentPlayer().getIsInteracted().put(player, true);
            player.getIsInteracted().put(AppClient.getCurrentGame().getCurrentPlayer(), true);
        }

        list.append("Your have given a flower to " + username + "!");
        return new Result(true, list.toString());
    }

    public Result askMarriage(String username) {
        username = username.trim();

        Player player = AppClient.getCurrentGame().findPlayer(username);
        Player mainPlayer = AppClient.getCurrentGame().getCurrentPlayer();

        //Errors
        if (player == null)
            return new Result(false, "Player not found!");
//        if (mainPlayer.getGender() == Gender.FEMALE)
//            return new Result(false, "Your gender should be male to ask marriage!");
        if (mainPlayer.getFriendShips().get(player).first() != 3 ||
            player.getFriendShips().get(mainPlayer).first() != 3)
            return new Result(false, "You and " + username + " should have friendship level of 3!");
        if (mainPlayer.getCoordinate().distance(player.getCoordinate()) > 1)
            return new Result(false, "You should be neighbor to " + username + " to ask marriage!");
//        if (mainPlayer.getGender() == player.getGender())
//            return new Result(false, "Your gender should be opposite to " + username + " to ask marriage!");
        if (player.getMarriageList().get(mainPlayer) != null)
            return new Result(false, "Your marriage is already in progress!");
        if (player.getMarried() != null)
            return new Result(false, username + " is already married with " +
                player.getMarried().getUsername() + "!");

        if (mainPlayer.getInHandGood().isEmpty() ||
            !mainPlayer.getInHandGood().getLast().getName().equals("Wedding_Ring"))
            return new Result(false, "Please select a Wedding_Ring from your inventory!");


        ArrayList<Good> goods = AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ProductType.WEDDING_RING);
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

        Player player = AppClient.getCurrentGame().findPlayer(username);
        Player mainPlayer = AppClient.getCurrentGame().getCurrentPlayer();
        if (player == null)
            return new Result(false, "Player not found!");
//        if (mainPlayer.getUser().getGender() == Gender.MALE)
//            return new Result(false, "Your gender should be female to respond!");
        //TODO
        if (mainPlayer.getMarriageList().get(player) == null)
            return new Result(false, username + " has not been asked you to marry him!");

        if (status.matches("\\s*-accept\\s*")) {
            mainPlayer.getInventory().addGood(new ArrayList<>(Arrays.asList(mainPlayer.getMarriageList().get(player))));

            // Friendship level increased to 4
            mainPlayer.getFriendShips().computeIfPresent(player,
                (k, pair) -> new Pair<>(4, pair.second()));
            player.getFriendShips().computeIfPresent(mainPlayer,
                (k, pair) -> new Pair<>(4, pair.second()));

            player.updateFriendShips(AppClient.getCurrentGame().getCurrentPlayer());
            AppClient.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
            // The Wallets are shared!
            mainPlayer.getWallet().increaseBalance(player.getWallet().getBalance());
            player.setWallet(mainPlayer.getWallet());

            // Now they are married
            mainPlayer.setMarried(player);
            player.setMarried(mainPlayer);

            player.getNews().add(mainPlayer.getUsername() + " has accepted your marriage! Now you can live with her!");
            mainPlayer.getMarriageList().remove(player);

            for (Player gamePlayer : AppClient.getCurrentGame().getPlayers()) {
                if (mainPlayer.getMarriageList().get(gamePlayer) != null) {
                    gamePlayer.getInventory().addGood(new ArrayList<>(Arrays.asList(mainPlayer.getMarriageList().get(gamePlayer))));
                    gamePlayer.getNews().add(mainPlayer.getUsername() + " has rejected your marriage and married with " + player.getUsername() + "!");
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

            player.updateFriendShips(AppClient.getCurrentGame().getCurrentPlayer());
            AppClient.getCurrentGame().getCurrentPlayer().updateFriendShips(player);
            player.setRejectionBuff(new Buff(BuffType.REJECT_BUFF, 7, 100));
            player.getInventory().addGood(new ArrayList<>(Arrays.asList(mainPlayer.getMarriageList().get(player))));
            player.getNews().add(mainPlayer.getUsername() + " has rejected your marriage!");
            mainPlayer.getFriendShips().remove(player);
            return new Result(true, "You have rejected your marriage from " + username + "!");
        } else
            return new Result(false, "Invalid respond to marriage ask!");
    }


    // Parsa
    // Trading methods
    public Result startTrade() {
        AppClient.setCurrentMenu(Menu.TradeMenu);

        System.out.println("Players: ");
        for (Player player : AppClient.getCurrentGame().getPlayers()) {
            System.out.println(player.getUsername());
        }
        System.out.println("____________________________");
        System.out.println("You are now in Trade Menu!");

        Player currentPlayer = AppClient.getCurrentGame().getCurrentPlayer();
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

        for (NPC npc : AppClient.getCurrentGame().getNPCs()) {
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
        for (NPC npc : AppClient.getCurrentGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                if ((abs(npc.getType().getCoordinate().getX() -
                    AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getX()) == 1 ||
                    (abs(npc.getType().getCoordinate().getX() -
                        AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getX()) == 0))
                    &&
                    (abs(npc.getType().getCoordinate().getY() -
                        AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getY()) == 1 ||
                        abs(npc.getType().getCoordinate().getY() -
                            AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getY()) == 0)) {
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
        for (ArrayList<Good> goods : AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
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
        for (NPC npc : AppClient.getCurrentGame().getNPCs()) {
            if (npc.getType().getName().equals(npcName)) {
                npc.getGift(good);
                return new Result(true, "You sent a " + itemName + " to " + npcName);

            }
        }
        return new Result(false, "NPC not found.");
    }

    public Result friendshipNPCList() {

        for (NPC npc : AppClient.getCurrentGame().getNPCs()) {
            System.out.println("------------------------------");
            System.out.println("NPC Name: " + npc.getType().getName());
            System.out.println("Friendship Level: " + npc.getFriendship().getFriendshipLevel());
            System.out.println("Friendship points: " + npc.getFriendship().getFriendshipPoints());
        }
        return new Result(true, "------------------------------");
    }

    public Result questsList() {

        for (NPC npc : AppClient.getCurrentGame().getNPCs()) {
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
        for (NPC npc : AppClient.getCurrentGame().getNPCs()) {
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
        for (NPC npc : AppClient.getCurrentGame().getNPCs()) {
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
            AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getX() + ", " +
            " " + AppClient.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
    }

    public Result showBalance() {
        return new Result(true, "Balance: " + AppClient.getCurrentGame().getCurrentPlayer().getWallet().getBalance() + " g");
    }

    public Result test() {
        AppClient.getCurrentGame().getCurrentPlayer().getInventory().addGood(Good.newGood(Good.newGoodType("Omelet")), 12);

        GoodType goodType = Good.newGoodType("Omelet");
        ArrayList<Good> newGoods = Good.newGoods(goodType, 12);
        AppClient.getCurrentGame().getCurrentPlayer().getInventory().addGood(newGoods);

        return new Result(true, "test");
    }


}

