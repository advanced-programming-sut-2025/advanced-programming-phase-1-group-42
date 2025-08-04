package com.StardewValley.views;

import com.StardewValley.Main;
import com.StardewValley.controllers.GameMenuController;
import com.StardewValley.controllers.TradeMenuController;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.*;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.craftings.Crafting;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.fishs.Fish;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.ForagingMineralType;
import com.StardewValley.models.goods.recipes.CookingRecipeType;
import com.StardewValley.models.goods.recipes.CraftingRecipeType;
import com.StardewValley.models.goods.tools.Tool;
import com.StardewValley.models.goods.tools.ToolLevel;
import com.StardewValley.models.interactions.Animals.Animal;
import com.StardewValley.models.interactions.Building;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.interactions.Animals.AnimalTypes;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuilding;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuildingTypes;
import com.StardewValley.models.interactions.game_buildings.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.StardewValley.models.goods.Good.newGoodType;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;


public class GameView implements Screen, InputProcessor {
    private Skin skin = Assets.getInstance().getSkin();
    private GameMenuController controller;
    private Stage stage;
    private Table table;
    private final OrthographicCamera camera;
    private Viewport viewport;
    private int scaledSize;
    private Table inventoryTable;

    private InputMultiplexer multiplexer;
    private Stage staticStage;
    private Window toolsWindow;
    private ScrollPane toolsScrollPane;
    private Table toolsTable;
    private Window fridgeWindow;
    private Table fridgeTable;
    private Table cookingTable;
    private Window cookingWindow;
    private ScrollPane cookingScrollPane;
    private ScrollPane fridgeScrollPane;
    private Table craftingTable;
    private Window craftingWindow;
    private ScrollPane craftingScrollPane;
    private Coordinate lastCoordinate;
    private TextField textFieldMessage;
    private Image npcImage;
    float timeAccumulator = 0;
    private Animal selectedAnimal = null;
    TextButton.TextButtonStyle style;
    private Boolean isFridgeOpen = false;
    private Boolean isCookingOpen = false;
    public Boolean isCraftingOpen = false;
    private Window cookingRecipeWindow;
    private Window craftingRecipeWindow;
    private Window chatRoomWindow;

    private Window cheatWindow;
    private Table cheatTable;
    private TextField cheatTextField;
    private TextButton cheatButton;

    private TextButton friendsButton;
    private Window friendsWindow;
    private Table friendsTable;
    private TextButton friendsBackButton;

    private TextButton journalButton;
    private Window journalWindow;
    private Table journalTable;
    private TextButton journalBackButton;

    private TextButton tradeButton;
    private Window tradeWindow;
    private Table tradeTable;
    private TextButton tradeBackButton;

    private TextButton tradeInboxButton;
    private Window tradeInboxWindow;
    private Table tradeInboxTable;
    private TextButton tradeInboxBackButton;

    private Window playerGiftWindow;
    private Table playerGiftTable;
    private Label playerGiftLabel;
    private SelectBox<String> playerGiftSelectBox;
    private SelectBox<String> goodsDropdown;
    private SelectBox<Integer> playerCountGiftSelectBox;
    private TextButton playerGiftButton;
    private ScrollPane playerGiftScrollPane;
    private Table giftTable;
    private Label messageGiftLabel;
    private TextButton playerGiftBackButton;

    private TextButton hugButton;
    private TextButton flowerButton;
    private TextButton marriageButton;
    private Player selectedPlayer;

    private Label upgradeLabel;
    private Label descriptionLabel;
    private Label messageLabel;
    private final TextButton[] upgradeButton = {new TextButton("Upgrade", skin)};
    private SelectBox<String> toolSelectBox;
    private Window upgradeWindow;

    private Window craftingUseWindow;
    private Table craftingUseTable;
    private Label craftingLabel;
    private ArrayList<SelectBox<String>> craftingSelectBox;
    private TextButton craftingUseButton;
    private TextButton craftingBackButton;
    private Label craftingMessageLabel;

    private boolean isTabClicked = false;

    TradeMenuController tradeController = new TradeMenuController();
    TradeManager tradeManager = new TradeManager();
    public GameView(GameMenuController controller, Skin skin) {
        this.controller = controller;
//        this.controller.initGameControllers();
        this.skin = skin;
        table = new Table(skin);
        table.setFillParent(true);
        table.defaults().pad(10);

        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        scaledSize = 40;
        controller.setGameView(this);

        this.inventoryTable = new Table(skin);
        this.inventoryTable.setFillParent(true);
        drawInventory();
        friendsButton = new TextButton("Friendships", skin);
        friendsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (friendsWindow == null)
                    initFriendshipsWindow();
                else
                    closeFriendshipsWindow();
            }
        });

        journalButton = new TextButton("Journal", skin);
        journalButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (journalWindow == null)
                    initJournalWindow();
                else
                    closeJournalWindow();
            }
        });

        tradeButton = new TextButton("Trade", skin);
        tradeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (tradeWindow == null)
                    initTradeWindow();
                else
                    closeTradeWindow();
            }
        });

        tradeInboxButton = new TextButton("Inbox", skin);
        tradeInboxButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (tradeInboxWindow == null)
                    initTradeInboxWindow();
                else{
                    //TODO
                }

            }
        });

        this.table.add(friendsButton).padTop(200).padLeft(-400).height(70).width(250).row();
        this.table.add(journalButton).padLeft(-400).height(70).padTop(5).width(250).row();
        this.table.add(tradeButton).padLeft(-525).height(70).padTop(6).width(125);
        this.table.add(tradeInboxButton).padLeft(-1650).height(70).width(125).row();
        this.table.add(inventoryTable).padTop(995).padBottom(-200).padLeft(-50);
        this.table.add(controller.getInventoryController().getProgressBar()).padTop(300).padLeft(800);
        this.table.row();


        fridgeWindow = new Window("Fridge", Assets.getInstance().getSkin());


        //TODO testing animals
//        FarmBuilding farmBuilding = new FarmBuilding(FarmBuildingTypes.BARN, new Coordinate(50, 30));
//        App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings().add(farmBuilding);
//        Animal animal = new Animal(AnimalTypes.COW, "meow");
//        animal.setCoordinate(new Coordinate(54, 34));
//        farmBuilding.addAnimal(animal);
//
//        App.getCurrentGame().getCurrentPlayer().getFridge().addItemToFridge(Good.newGood(FoodType.APPLE));
//        App.getCurrentGame().getCurrentPlayer().getFridge().addItemToFridge(Good.newGood(FoodType.BEER));
//        App.getCurrentGame().getCurrentPlayer().getInventory().addGoodByObject(Good.newGood(FoodType.WHEAT_FLOUR));

//        new Pair<>(ForagingMineralType.IRON_ORE, 4),
//            new Pair<>(ForagingMineralType.COAL, 1);
//        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(Good.newGood(ForagingMineralType.IRON_ORE),4);
//        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(Good.newGood(ForagingMineralType.COAL),1);
//        App.getCurrentGame().getCurrentPlayer().getSkill().increaseMiningLevel();
//        App.getCurrentGame().getCurrentPlayer().getSkill().increaseMiningLevel();
        Pixmap normal = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        normal.setColor(Color.YELLOW);
        normal.fill();
        Pixmap hover = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        hover.setColor(Color.SKY);
        hover.fill();
        TextureRegionDrawable normalDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(normal)));
        TextureRegionDrawable hoverDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(hover)));
        BitmapFont font = new BitmapFont();
        style = new TextButton.TextButtonStyle();
        style.up = normalDrawable;
        style.over = hoverDrawable;
        style.down = normalDrawable.tint(Color.GRAY);
        style.font = font;


        selectedPlayer = null;

    }

    @Override
    public void show() {
        stage = new Stage(viewport);
        staticStage = new Stage(new ScreenViewport());

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(staticStage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
        viewport.apply();

        staticStage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateCamera();
        Main.getBatch().setProjectionMatrix(camera.combined);


        Main.getBatch().begin();

        renderWorld();
        controller.handleGame();
        setColorFunction();

        if (showEmote && emoteAnimation != null) {
            emoteStateTime += delta;
            Texture currentFrame = emoteAnimation.getKeyFrame(emoteStateTime, false);
            // Calculate position for center

            float x = (Gdx.graphics.getWidth() - currentFrame.getWidth())/2;
            float y = (Gdx.graphics.getHeight() - currentFrame.getHeight())/2 + 90;

            Main.getBatch().draw(currentFrame, x, y,64, 64);

            // Optionally hide animation when finished
            if (emoteAnimation.isAnimationFinished(emoteStateTime)) {
                showEmote = false;
                closePopupReactionWindow();
            }
        }

        Main.getBatch().end();

        timeAccumulator += delta;

        if (timeAccumulator >= 1f) {
            for (Animal animal : App.getCurrentGame().getMap().allAnimals()) {
                animal.updateCounter();
            }

            for (FarmBuilding farmBuilding : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
                for (Animal animal : farmBuilding.getAnimals()) {
                    animal.updateCounter();
                }
            }
            timeAccumulator = 0;
        }

        stage.act(min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        staticStage.act(min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        staticStage.act(min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        staticStage.draw();


    }

    public void setColorFunction() {
        int time = App.getCurrentGame().getDateTime().getTime();
        if (time >= 19) {
            Sprite sprite = new Sprite(Assets.getInstance().getNight_background());
            sprite.draw(Main.getBatch());
        }
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPos);

        int tileX = (int) (touchPos.x / scaledSize);
        int tileY = (int) (touchPos.y / scaledSize);

        Tile playerTile = Map.findTile(App.getCurrentGame().getCurrentPlayer().getCoordinate());
        Coordinate coordinate = new Coordinate(tileX, tileY);
        Tile selectedTile = Map.findTile(coordinate);
        if (selectedTile == null || playerTile == null)
            return false;

        for (Player player : App.getCurrentGame().getPlayers()) {
            if (player.getCoordinate().equals(coordinate) &&
            player != App.getCurrentGame().getCurrentPlayer()) {
                selectedPlayer = player;
                initFriend();
                return true;
            }
            else if (player.getCoordinate().equals(coordinate) &&
                !player.getInHandGood().isEmpty() &&
                player.getInHandGood().getLast() instanceof Crafting) {
                    initCraftingWindow(player.getInHandGood());
                    return true;
            }
        }

        if (selectedTile.findGood("ShippingBin") != null) {
            initShippingBinWindow();
            return true;
        }

        if (!App.getCurrentGame().getCurrentPlayer().getInHandGood().isEmpty() &&
            App.getCurrentGame().getCurrentPlayer().getInHandGood().getLast() instanceof Tool &&
            playerTile.getTileType() != TileType.PLAIN && selectedTile.getTileType() != TileType.GREEN_HOUSE &&
            playerTile.getTileType() != TileType.PLAYER_BUILDING) {

            Tile tile = Map.findTile(coordinate);

            assert tile != null;
            controller.toolsUse(Coordinate.getDirection(playerTile.getCordinate(), tile.getCordinate()));
        }
        if (petsClicking(button, tileX, tileY)) return true;
        if (selectedTile.getTileType() == TileType.GREEN_HOUSE &&
            !App.getCurrentGame().getCurrentPlayer().getFarm().getGreenHouse().isAvailable()) {

            initGreenHouseWindow();
        }

        GameBuilding building = App.getCurrentGame().getMap().findGameBuilding(new Coordinate(tileX, tileY));
        if (building != null) {

            Texture backgroundTexture = new Texture(Gdx.files.internal("shop-menu.png"));
            Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(backgroundTexture));

            final Window window = new Window("SHOP", skin);
            window.setBackground(backgroundDrawable);
            window.setSize(940, 600);
            window.setPosition(
                (staticStage.getWidth() - window.getWidth()) / 2,
                (staticStage.getHeight() - window.getHeight()) / 2
            );

            Table header = new Table(skin);
            Label title = new Label(String.valueOf(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance()), skin);
            TextButton closeButton = new TextButton("X", skin);
            closeButton.pad(4);
            closeButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    window.remove();
                    backgroundTexture.dispose();
                }
            });

            header.add().expandX();
            header.add(title).center().expandX().padRight(95).padBottom(30);
            header.add(closeButton).top().right();

            window.add(header).expandX().fillX().padTop(5).row();

            Table content = new Table(skin);
            window.add(content).expand().fill().pad(10);

            Table itemsTable = new Table();
            ScrollPane scrollPane = new ScrollPane(itemsTable, skin);
            scrollPane.setFadeScrollBars(false);
            scrollPane.setScrollingDisabled(false, false);
            scrollPane.setForceScroll(false, true);
            scrollPane.setSmoothScrolling(true);

            Table selectedPanel = new Table(skin);
            Label selectedNameLabel = new Label("", skin);
            Label countLabel = new Label("0", skin);
            TextButton addButton = new TextButton("+", skin);
            TextButton removeButton = new TextButton("-", skin);
            TextButton purchaseButton = new TextButton("Purchase", skin);
            Label info = new Label("", skin);
            addButton.setDisabled(false);
            removeButton.setDisabled(false);
            purchaseButton.setDisabled(false);
            addButton.setVisible(false);
            removeButton.setVisible(false);
            purchaseButton.setVisible(false);
            countLabel.setVisible(false);
            Table counterPanel = new Table();
            counterPanel.center();
            selectedNameLabel.setAlignment(Align.center);
            counterPanel.add(selectedNameLabel)
                .colspan(3)
                .fillX()
                .center()
                .padLeft(5)
                .row();


            counterPanel.add(removeButton)
                .size(100, 70)
                .padLeft(0);

            counterPanel.add(countLabel)
                .width(30)
                .padLeft(5)
                .center();

            counterPanel.add(addButton)
                .size(100, 70)
                .padLeft(5)
                .row();


            counterPanel.add(purchaseButton)
                .size(150, 70)
                .pad(5)
                .padLeft(10)
                .colspan(3)
                .center()
                .row();

            info.setWrap(true);
            info.setWidth(250);
            info.setFontScale(0.7f);
            info.setAlignment(Align.center);




            // MarnieRanch
            if (building instanceof MarnieRanch) {
                marnieRanchShop(addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY,
                    (MarnieRanch) building, selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);
                staticStage.addActor(window);
            }
            else if (building instanceof CarpenterShop) {
                carpenterShop(purchaseButton, (CarpenterShop) building, selectedNameLabel, itemsTable, counterPanel,
                    info, selectedPanel, scrollPane, content);
                staticStage.addActor(window);
            }
            else if (building instanceof FishShop) {
                fishShop(addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, (FishShop) building,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);
                staticStage.addActor(window);
            }
            else if (building instanceof PierreGeneralStore) {
                pierreShop(addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, (PierreGeneralStore) building,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);
                staticStage.addActor(window);
            }
            else if (building instanceof JojaMart) {
                jojaShop(addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, (JojaMart) building,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);;
                staticStage.addActor(window);
            }
            else if (building instanceof TheStarDropSaloon) {
                stardropShop(addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, (TheStarDropSaloon) building,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);
                staticStage.addActor(window);
            }
            else if (building instanceof Blacksmith) {
                blacksmithShop(window, addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, (Blacksmith) building,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);
            }


//            multiplexer.addProcessor(stage);
//            multiplexer.addProcessor(this);
//            Gdx.input.setInputProcessor(multiplexer);

            return true;
        }
        return false;
    }

    private boolean petsClicking(int button, int tileX, int tileY) {
        // right click for pets
        if (button == Input.Buttons.RIGHT) {
            Animal animal = null;
            for (FarmBuilding building : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
                for (Animal animal2 : building.getAnimals()) {
                    if (animal2.getCoordinate().getX() == tileX && animal2.getCoordinate().getY() == tileY) {
                        animal = animal2;
                        break;
                    }
                }
                if (animal != null) break;
            }
            if (animal != null) {
                Window animalWindow = new Window("", skin);

                Table nameTable = new Table();
                Label nameLabel = new Label(animal.getName(), skin);
                nameLabel.setFontScale(0.6f);

                TextButton closeButton = new TextButton("X", style);
                closeButton.getLabel().setFontScale(1.2f);
                closeButton.pad(5);
                closeButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        animalWindow.remove();
                    }
                });

                nameTable.top().left();
                nameTable.add(nameLabel).expandX().left();
                nameTable.add(closeButton).right().padRight(5);
                animalWindow.add(nameTable).expandX().fillX().padTop(2).row();

                Label friendship = new Label("friendship: " + animal.getFriendship(), skin);
                friendship.setFontScale(0.5f);
                animalWindow.add(friendship).pad(3).padTop(1).row();

                animalWindow.pack();
                animalWindow.setSize(scaledSize * 6, scaledSize * 6);

                TextButton sellButton = new TextButton("Sell", style);
                sellButton.setHeight(scaledSize);
                sellButton.pad(5);
                animalWindow.add(sellButton).expandX().fillX().pad(3).padTop(1).row();

                String name = animal.getName();
                sellButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        Result result = controller.sellAnimal(name);
                        buildMessage();
                        textFieldMessage.setText(result.message());
                    }
                });

                TextButton feedButton = new TextButton("Feed", style);
                feedButton.setHeight(scaledSize);
                feedButton.pad(5);
                animalWindow.add(feedButton).expandX().fillX().pad(3).padTop(1).row();

                feedButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        Result result = controller.feedHay(name);
                        buildMessage();
                        textFieldMessage.setText(result.message());
                    }
                });

                TextButton productsButton = new TextButton("Products", style);
                productsButton.setHeight(scaledSize);
                productsButton.pad(5);
                animalWindow.add(productsButton).expandX().fillX().pad(3).padTop(1).row();

                productsButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        Result result = controller.collectProduct(name);
                        buildMessage();
                        textFieldMessage.setText(result.message());
                    }
                });

                animalWindow.setPosition(
                    animal.getCoordinate().getX() * scaledSize + 30,
                    animal.getCoordinate().getY() * scaledSize + 30
                );

                stage.addActor(animalWindow);
            }
        }


        if (button == Input.Buttons.LEFT) {
            // change animal place
            if (selectedAnimal != null) {
                if (abs(selectedAnimal.getCoordinate().getX() - tileX) <= 2 &&
                    abs(selectedAnimal.getCoordinate().getY() - tileY) <= 2) {
                    selectedAnimal.setCoordinate(new Coordinate(tileX, tileY));
                    selectedAnimal = null;
                    return true;
                } else {
                    buildMessage();
                    textFieldMessage.setText("Oops! You can only move the animal to nearby tiles (within 2 blocks).");
                }

            } else {
                for (FarmBuilding building : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
                    for (Animal animal2 : building.getAnimals()) {
                        if (animal2.getCoordinate().getX() == tileX && animal2.getCoordinate().getY() == tileY) {
                            selectedAnimal = animal2;
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void carpenterShop(TextButton purchaseButton, CarpenterShop building, Label selectedNameLabel, Table itemsTable,
                               Table counterPanel, Label info, Table selectedPanel, ScrollPane scrollPane, Table content) {
        final FarmBuildingTypes[] selectedBuilding = {null};

        purchaseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {

                //TODO
                //window

            }
        });

        for (FarmBuildingTypes farmBuildingType : building.getProducts()) {
            TextButton productButton = new TextButton(farmBuildingType.getName() + " - " + farmBuildingType.getCost() + "G", skin);

            productButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    selectedBuilding[0] = farmBuildingType;
                    selectedNameLabel.setText(farmBuildingType.getName() + " - " + farmBuildingType.getCost() + "G" + "\n" +
                        "required stone: " + farmBuildingType.getStone() + "\n" + "required wood: " + farmBuildingType.getWood());
                    selectedNameLabel.setFontScale(0.7f);
                    purchaseButton.setVisible(true);

                }
            });

            itemsTable.add(productButton)
                .fillX()
                .pad(5)
                .row();
        }
        counterPanel.add(info)
            .width(250)
            .pad(5)
            .colspan(3)
            .center()
            .row();

        selectedPanel.add(counterPanel)
            .colspan(3)
            .center()
            .padBottom(40)
            .row();


        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.clear();

        mainTable.add(scrollPane)
            .width(380)
            .expandY()
            .fillY()
            .pad(20)
            .padRight(30)
            .padLeft(170);

        mainTable.add(selectedPanel)
            .width(200)
            .expandY()
            .fillY()
            .pad(30)
            .padLeft(50)
            .bottom();

        content.add(mainTable)
            .expand()
            .fill();
    }

    private void marnieRanchShop(TextButton addButton, Label countLabel, TextButton removeButton, TextButton purchaseButton,
                                 Label info, int tileX, int tileY, MarnieRanch building, Label selectedNameLabel,
                                 Table itemsTable, Table counterPanel, Table selectedPanel, ScrollPane scrollPane, Table content) {
        final AnimalTypes[] selectedAnimal = {null};
        final ProductType[] selectedProductType = {null};
        final int[] selectedCount = {0};

        TextField animalName = new TextField("", skin);

        animalName.setDisabled(false);
        animalName.setVisible(false);

        addButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedProductType[0] != null) {
                    selectedCount[0]++;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });

        removeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedProductType[0] != null && selectedCount[0] > 0) {
                    selectedCount[0]--;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });


        purchaseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if (selectedAnimal[0] != null && !animalName.getText().isEmpty()) {
                    Result result = controller.buyAnimal(String.valueOf(selectedAnimal[0]), animalName.getText());
                    System.out.println(result.message());
                    info.setText(result.toString());
                } else {
                    Result result = controller.purchase(String.valueOf(selectedProductType[0]),
                        String.valueOf(selectedCount[0]), new Coordinate(tileX, tileY));
                    System.out.println(result.message());
                    info.setText(result.toString());
                }
            }
        });


        for (AnimalTypes animalType : building.animals) {
            TextButton productButton = new TextButton(animalType.getName() + " - " + animalType.getPrice() + "G", skin);

            productButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    selectedProductType[0] = null;
                    addButton.setVisible(false);
                    removeButton.setVisible(false);
                    countLabel.setVisible(false);
                    animalName.setVisible(true);
                    selectedAnimal[0] = animalType;
                    selectedCount[0] = 0;
                    selectedNameLabel.setText(animalType.getName());
                    purchaseButton.setVisible(true);
                }
            });

            itemsTable.add(productButton)
                .fillX()
                .pad(5)
                .row();
        }

        for (ProductType productType : building.products) {
            TextButton productButton = new TextButton(productType.getName() + " - " + productType.getSellPrice() + "G", skin);

            productButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    selectedAnimal[0] = null;
                    addButton.setVisible(true);
                    removeButton.setVisible(true);
                    countLabel.setVisible(true);
                    animalName.setVisible(false);
                    selectedProductType[0] = productType;
                    selectedCount[0] = 0;
                    selectedNameLabel.setText(productType.getName());
                    purchaseButton.setVisible(true);
                }
            });

            itemsTable.add(productButton)
                .fillX()
                .pad(5)
                .row();
        }


        counterPanel.add(animalName)
            .size(180, 70)
            .colspan(3)
            .center()
            .row();

        lastConstructionsForShop(info, counterPanel, selectedPanel, scrollPane, content);
    }

    private void fishShop(TextButton addButton, Label countLabel, TextButton removeButton, TextButton purchaseButton,
                          Label info, int tileX, int tileY, FishShop fishShop, Label selectedNameLabel, Table itemsTable,
                          Table counterPanel, Table selectedPanel, ScrollPane scrollPane, Table content) {

//        final AnimalTypes[] selectedAnimal = {null};
//        final ProductType[] selectedProductType = {null};
        final GoodType[] selectedGoodType = {null};
        final int[] selectedCount = {0};
        final boolean[] filterAvailable = {false};

        TextButton filterButton = new TextButton("Filter Availables", skin, "Earth");

        addButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedGoodType[0] != null) {
                    selectedCount[0]++;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });

        removeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedGoodType[0] != null && selectedCount[0] > 0) {
                    selectedCount[0]--;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });


        purchaseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if (selectedGoodType[0] != null) {
                    Result result = controller.purchase(selectedGoodType[0].getName(), String.valueOf(selectedCount[0]),
                        new Coordinate(tileX, tileY));
                    System.out.println(result.message());
                    info.setText(result.toString());
                    info.setVisible(true);
                }
            }
        });

        filterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!filterAvailable[0]) {
                    filterButton.setText("Filter All");
                    filterAvailable[0] = true;
                }
                else {
                    filterButton.setText("Filter Availables");
                    filterAvailable[0] = false;
                }
                goodsListInit(addButton, countLabel, removeButton, purchaseButton, fishShop, selectedNameLabel, itemsTable,
                    selectedGoodType, selectedCount, filterAvailable, filterButton, info);

            }
        });

        itemsTable.add(filterButton)
            .fillX()
            .pad(5)
            .row();


        goodsListInit(addButton, countLabel, removeButton, purchaseButton, fishShop, selectedNameLabel, itemsTable,
            selectedGoodType, selectedCount, filterAvailable, filterButton, info);


        lastConstructionsForShop(info, counterPanel, selectedPanel, scrollPane, content);
    }

    private void pierreShop(TextButton addButton, Label countLabel, TextButton removeButton, TextButton purchaseButton,
                            Label info, int tileX, int tileY, PierreGeneralStore pierreGeneralStore, Label selectedNameLabel, Table itemsTable,
                            Table counterPanel, Table selectedPanel, ScrollPane scrollPane, Table content) {

        final GoodType[] selectedGoodType = {null};
        final int[] selectedCount = {0};
        final boolean[] filterAvailable = {false};

        TextButton filterButton = new TextButton("Filter Availables", skin, "Earth");

        addButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedGoodType[0] != null) {
                    selectedCount[0]++;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });

        removeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedGoodType[0] != null && selectedCount[0] > 0) {
                    selectedCount[0]--;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });


        purchaseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if (selectedGoodType[0] != null) {
                    Result result = controller.purchase(selectedGoodType[0].getName(), String.valueOf(selectedCount[0]),
                        new Coordinate(tileX, tileY));
                    System.out.println(result.message());
                    info.setText(result.toString());
                    info.setVisible(true);
                }
            }
        });

        filterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!filterAvailable[0]) {
                    filterButton.setText("Filter All");
                    filterAvailable[0] = true;
                }
                else {
                    filterButton.setText("Filter Availables");
                    filterAvailable[0] = false;
                }
                goodsListInit(addButton, countLabel, removeButton, purchaseButton, pierreGeneralStore, selectedNameLabel, itemsTable,
                    selectedGoodType, selectedCount, filterAvailable, filterButton, info);

            }
        });

        itemsTable.add(filterButton)
            .fillX()
            .pad(5)
            .row();


        goodsListInit(addButton, countLabel, removeButton, purchaseButton, pierreGeneralStore, selectedNameLabel, itemsTable,
            selectedGoodType, selectedCount, filterAvailable, filterButton, info);

        lastConstructionsForShop(info, counterPanel, selectedPanel, scrollPane, content);

    }

    private void jojaShop(TextButton addButton, Label countLabel, TextButton removeButton, TextButton purchaseButton,
                            Label info, int tileX, int tileY, JojaMart jojaMart, Label selectedNameLabel, Table itemsTable,
                            Table counterPanel, Table selectedPanel, ScrollPane scrollPane, Table content) {

        final GoodType[] selectedGoodType = {null};
        final int[] selectedCount = {0};
        final boolean[] filterAvailable = {false};

        TextButton filterButton = new TextButton("Filter Availables", skin, "Earth");

        addButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedGoodType[0] != null) {
                    selectedCount[0]++;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });

        removeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedGoodType[0] != null && selectedCount[0] > 0) {
                    selectedCount[0]--;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });


        purchaseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if (selectedGoodType[0] != null) {
                    Result result = controller.purchase(selectedGoodType[0].getName(), String.valueOf(selectedCount[0]),
                        new Coordinate(tileX, tileY));
                    System.out.println(result.message());
                    info.setText(result.toString());
                    info.setVisible(true);
                }
            }
        });

        filterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!filterAvailable[0]) {
                    filterButton.setText("Filter All");
                    filterAvailable[0] = true;
                }
                else {
                    filterButton.setText("Filter Availables");
                    filterAvailable[0] = false;
                }
                goodsListInit(addButton, countLabel, removeButton, purchaseButton, jojaMart, selectedNameLabel, itemsTable,
                    selectedGoodType, selectedCount, filterAvailable, filterButton, info);

            }
        });

        itemsTable.add(filterButton)
            .fillX()
            .pad(5)
            .row();


        goodsListInit(addButton, countLabel, removeButton, purchaseButton, jojaMart, selectedNameLabel, itemsTable,
            selectedGoodType, selectedCount, filterAvailable, filterButton, info);

        lastConstructionsForShop(info, counterPanel, selectedPanel, scrollPane, content);
    }

    private void stardropShop(TextButton addButton, Label countLabel, TextButton removeButton, TextButton purchaseButton,
                          Label info, int tileX, int tileY, TheStarDropSaloon theStarDropSaloon, Label selectedNameLabel, Table itemsTable,
                          Table counterPanel, Table selectedPanel, ScrollPane scrollPane, Table content) {

        final GoodType[] selectedGoodType = {null};
        final int[] selectedCount = {0};
        final boolean[] filterAvailable = {false};

        TextButton filterButton = new TextButton("Filter Availables", skin, "Earth");

        addButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedGoodType[0] != null) {
                    selectedCount[0]++;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });

        removeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedGoodType[0] != null && selectedCount[0] > 0) {
                    selectedCount[0]--;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });


        purchaseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if (selectedGoodType[0] != null) {
                    Result result = controller.purchase(selectedGoodType[0].getName(), String.valueOf(selectedCount[0]),
                        new Coordinate(tileX, tileY));
                    System.out.println(result.message());
                    info.setText(result.toString());
                    info.setVisible(true);
                }
            }
        });

        filterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!filterAvailable[0]) {
                    filterButton.setText("Filter All");
                    filterAvailable[0] = true;
                }
                else {
                    filterButton.setText("Filter Availables");
                    filterAvailable[0] = false;
                }
                goodsListInit(addButton, countLabel, removeButton, purchaseButton, theStarDropSaloon, selectedNameLabel,
                    itemsTable, selectedGoodType, selectedCount, filterAvailable, filterButton, info);

            }
        });

        itemsTable.add(filterButton)
            .fillX()
            .pad(5)
            .row();


        goodsListInit(addButton, countLabel, removeButton, purchaseButton, theStarDropSaloon, selectedNameLabel, itemsTable,
            selectedGoodType, selectedCount, filterAvailable, filterButton, info);

        lastConstructionsForShop(info, counterPanel, selectedPanel, scrollPane, content);
    }

    private void blacksmithShop(Window window, TextButton addButton, Label countLabel, TextButton removeButton, TextButton purchaseButton,
                              Label info, int tileX, int tileY, Blacksmith blacksmith, Label selectedNameLabel, Table itemsTable,
                              Table counterPanel, Table selectedPanel, ScrollPane scrollPane, Table content) {

        final GoodType[] selectedGoodType = {null};
        final int[] selectedCount = {0};
        final boolean[] filterAvailable = {false};

        upgradeWindow = new Window("Upgrade Tool", skin);
        upgradeWindow.setSize(1000, 600);
        upgradeWindow.setResizable(false);
        upgradeWindow.setPosition(
            (staticStage.getWidth() - upgradeWindow.getWidth()) / 2,
            (staticStage.getHeight() - upgradeWindow.getHeight()) / 2
        );

        Table upgradePanel = new Table(skin);
        counterPanel.center();
        upgradeLabel = new Label("Select Tool", skin);
        descriptionLabel = new Label("", skin);
        messageLabel = new Label("", skin);
        toolSelectBox = new SelectBox<>(skin);
        Array<String> tools = new Array<>();
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getLast() instanceof Tool)
                tools.add(goods.getLast().getName());
        }
        toolSelectBox.setItems(tools);
        toolSelectBox.setSelectedIndex(0);
        upgradeSelect();

        toolSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                upgradeSelect();
            }
        });
        upgradePanel.add(upgradeLabel).center().row();
        upgradePanel.add(toolSelectBox).center().row();
        upgradePanel.add(descriptionLabel).center().row();
        upgradePanel.add(upgradeButton).center().row();
        TextButton upgradeBackButton = new TextButton("Back", skin);
        upgradeBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                upgradeWindow.remove();
            }
        });
        upgradePanel.add(upgradeBackButton).center().row();
        upgradePanel.add(messageLabel).padLeft(-50).center().row();
        upgradeWindow.add(upgradePanel).center().row();

        Window blacksmithWindow = new Window("Blacksmith Shop", skin);
        blacksmithWindow.setSize(300, 300);
        blacksmithWindow.setResizable(false);
        blacksmithWindow.setPosition(
            (Gdx.graphics.getWidth() - blacksmithWindow.getWidth()) / 2,
            (Gdx.graphics.getHeight() - blacksmithWindow.getHeight()) / 2
        );
        Table blacksmithTable = new Table(skin);
        TextButton shopButton = new TextButton("Shop", skin);
        shopButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                blacksmithWindow.remove();
                smithShop(window, addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, blacksmith,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content, selectedGoodType,
                    selectedCount, filterAvailable);
            }
        });
        TextButton upgradeButton = new TextButton("Upgrade", skin);
        upgradeButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               blacksmithWindow.remove();
               toolsUpgradeInit(upgradeWindow);
           }
        });
        blacksmithTable.add(shopButton).fillX().expandX().center().row();
        blacksmithTable.add(upgradeButton).fillX().expandX().center().row();
        blacksmithWindow.add(blacksmithTable).center().row();

        staticStage.addActor(blacksmithWindow);
    }

    private void upgradeSelect() {
        String selectedToolName = toolSelectBox.getSelected();
        Tool selectedTool = null;
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty() && goods.getLast() instanceof Tool && goods.getLast().getName().equals(selectedToolName)) {
                selectedTool = (Tool) goods.getLast();
                break;
            }

        }

        descriptionLabel.setText(Blacksmith.getUpgradeDescription().get(selectedTool.getToolLevel().getLevelNumber()));
        upgradeButton[0] = new TextButton("Upgrade", skin);
        Tool finalSelectedTool = selectedTool;
        upgradeButton[0].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result res = controller.toolsUpgrade(finalSelectedTool.getName());
                messageLabel.setText(res.message());
            }
        });
    }

    private void smithShop(Window window, TextButton addButton, Label countLabel, TextButton removeButton, TextButton purchaseButton,
                                Label info, int tileX, int tileY, Blacksmith blacksmith, Label selectedNameLabel, Table itemsTable,
                                Table counterPanel, Table selectedPanel, ScrollPane scrollPane, Table content,
                                GoodType[] selectedGoodType, int[] selectedCount, boolean[] filterAvailable) {
        TextButton filterButton = new TextButton("Filter Availables", skin, "Earth");
        staticStage.addActor(window);

        addButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedGoodType[0] != null) {
                    selectedCount[0]++;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });

        removeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedGoodType[0] != null && selectedCount[0] > 0) {
                    selectedCount[0]--;
                    countLabel.setText(String.valueOf(selectedCount[0]));
                }
            }
        });


        purchaseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if (selectedGoodType[0] != null) {
                    Result result = controller.purchase(selectedGoodType[0].getName(), String.valueOf(selectedCount[0]),
                        new Coordinate(tileX, tileY));
                    System.out.println(result.message());
                    info.setText(result.toString());
                    info.setVisible(true);
                }
            }
        });

        filterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!filterAvailable[0]) {
                    filterButton.setText("Filter All");
                    filterAvailable[0] = true;
                }
                else {
                    filterButton.setText("Filter Availables");
                    filterAvailable[0] = false;
                }
                goodsListInit(addButton, countLabel, removeButton, purchaseButton, blacksmith, selectedNameLabel,
                    itemsTable, selectedGoodType, selectedCount, filterAvailable, filterButton, info);
            }
        });

        itemsTable.add(filterButton)
            .fillX()
            .pad(5)
            .row();

        goodsListInit(addButton, countLabel, removeButton, purchaseButton, blacksmith, selectedNameLabel, itemsTable,
            selectedGoodType, selectedCount, filterAvailable, filterButton, info);

        lastConstructionsForShop(info, counterPanel, selectedPanel, scrollPane, content);
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        int ptr = -1;
        for (int i = 0; i < App.getCurrentGame().getCurrentPlayer().getInventory().getSize(); i++) {
            ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i);
            if (goods == App.getCurrentGame().getCurrentPlayer().getInHandGood())
                ptr = i;
        }

        if (v1 > 0) {
            ptr = (ptr + 1) % App.getCurrentGame().getCurrentPlayer().getInventory().getSize();
        }
        else if (v1 < 0){
            ptr = (ptr - 1 + App.getCurrentGame().getCurrentPlayer().getInventory().getSize())
            % App.getCurrentGame().getCurrentPlayer().getInventory().getSize();
        }

        App.getCurrentGame().getCurrentPlayer().setInHandGood(
            App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(ptr)
        );
        return false;
    }

    private void updateCamera() {
        camera.position.set((App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()) * scaledSize,
            (App.getCurrentGame().getCurrentPlayer().getCoordinate().getY()) * scaledSize, 0);
        camera.update();
    }

    private void renderWorld() {
        int midX = App.getCurrentGame().getCurrentPlayer().getCoordinate().getX() * scaledSize;
        int midY = App.getCurrentGame().getCurrentPlayer().getCoordinate().getY() * scaledSize;

        for (int x = max((midX - Gdx.graphics.getWidth() / 2) / scaledSize - 5, 0); x < min((midX + Gdx.graphics.getWidth() / 2) / scaledSize + 1, 150); x++) {
            for (int y = max((midY - Gdx.graphics.getHeight() / 2) / scaledSize - 5, 0); y < min((midY + Gdx.graphics.getHeight() / 2) / scaledSize + 1, 160); y++) {
                Coordinate coordinate = new Coordinate(x, y);
                Tile tile = Map.findTile(coordinate);
                switch (tile.getTileType()) {
                    case TileType.QUARRY -> {
                        Main.getBatch().draw(TileAssets.QUARRY.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                    }
                    case TileType.FARM, TileType.PLAYER_BUILDING, TileType.GREEN_HOUSE -> {
                        if (App.getCurrentGame().getDateTime().getSeason() == Season.WINTER)
                            Main.getBatch().draw(TileAssets.FARM_WINTER.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                        else
                            Main.getBatch().draw(TileAssets.FARM_ORDINARY.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                    }
                    case TileType.PLOWED_FARM -> {
                        if (tile.isWatered()) {
                            Main.getBatch().draw(TileAssets.FARM_WET.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                        } else
                            Main.getBatch().draw(TileAssets.FARM_PLOWED.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                    }
                    case TileType.WATER -> {
                        Main.getBatch().draw(TileAssets.WATER.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                    }
                    case TileType.PLAIN, TileType.GAME_BUILDING -> {
                        if (App.getCurrentGame().getDateTime().getSeason() == Season.WINTER)
                            Main.getBatch().draw(TileAssets.FARM_WINTER.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                        else
                            Main.getBatch().draw(TileAssets.GRASS.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                    }
                    case TileType.ROAD -> {
                        Main.getBatch().draw(TileAssets.ROAD.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                    }
                    case TileType.STONE_WALL -> {
                        Main.getBatch().draw(TileAssets.STONE_WALL.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                    }
                    case TileType.SQUARE -> {
                        Main.getBatch().draw(TileAssets.SQUARE.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                    }
                    case TileType.BEACH -> {
                        Main.getBatch().draw(TileAssets.BEACH.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                    }
                    case TileType.SHIPPING_BIN -> {
                        Main.getBatch().draw(TileAssets.SHIPPING_BIN.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                    }
                }
            }
        }

        for (int x = max((midX - Gdx.graphics.getWidth() / 2) / scaledSize - 5, 0); x < min((midX + Gdx.graphics.getWidth() / 2) / scaledSize + 1, 150); x++) {
            for (int y = max((midY - Gdx.graphics.getHeight() / 2) / scaledSize - 5, 0); y < min((midY + Gdx.graphics.getHeight() / 2) / scaledSize + 1, 160); y++) {
                Coordinate coordinate = new Coordinate(x, y);
                Tile tile = Map.findTile(coordinate);
                switch (tile.getTileType()) {
                    case TileType.GAME_BUILDING -> {
                        Tile backTile = Map.findTile(new Coordinate(coordinate.getX() - 1, coordinate.getY()));
                        Tile upTile = Map.findTile(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
                        if (backTile.getTileType() != TileType.GAME_BUILDING && upTile.getTileType() != TileType.GAME_BUILDING) {
                            GameBuilding gameBuilding = App.getCurrentGame().getMap().findGameBuilding(coordinate);
                            Coordinate size = new Coordinate(gameBuilding.getEndCordinate().getX() - gameBuilding.getStartCordinate().getX(),
                                gameBuilding.getEndCordinate().getY() - gameBuilding.getStartCordinate().getY());
                            Main.getBatch().draw(gameBuilding.getTexture(), x * scaledSize, y * scaledSize, size.getX() * scaledSize, size.getY() * scaledSize);
                        }
                    }
                    case TileType.PLAYER_BUILDING -> {
                        Tile backTile = Map.findTile(new Coordinate(coordinate.getX() - 1, coordinate.getY()));
                        Tile upTile = Map.findTile(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
                        if (backTile.getTileType() != TileType.PLAYER_BUILDING && upTile.getTileType() != TileType.PLAYER_BUILDING) {
                            Main.getBatch().draw(TileAssets.HOUSE.getTexture(), x * scaledSize, y * scaledSize, 10 * scaledSize, 10 * scaledSize);
                        }
                    }
                    case TileType.GREEN_HOUSE -> {
                        Tile backTile = Map.findTile(new Coordinate(coordinate.getX() - 1, coordinate.getY()));
                        Tile upTile = Map.findTile(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
                        if (backTile.getTileType() != TileType.GREEN_HOUSE && upTile.getTileType() != TileType.GREEN_HOUSE) {
                            if (App.getCurrentGame().getCurrentPlayer().getFarm().getGreenHouse().isAvailable())
                                Main.getBatch().draw(TileAssets.GREEN_HOUSE.getTexture(), (x - 1) * scaledSize, (y) * scaledSize, 8 * scaledSize, 7 * scaledSize);
                            else
                                Main.getBatch().draw(TileAssets.BROKEN_GREEN_HOUSE.getTexture(), (x) * scaledSize, (y) * scaledSize, 6 * scaledSize, 5 * scaledSize);
                        }

                        if (App.getCurrentGame().getCurrentPlayer().getFarm().getGreenHouse().isAvailable())
                            Main.getBatch().draw(TileAssets.FARM_ORDINARY.getTexture(), x * scaledSize, (y + 1) * scaledSize, scaledSize, scaledSize);
                    }
                }
            }
        }

        for (int x = min((midX + Gdx.graphics.getWidth() / 2) / scaledSize + 1, 150) - 1; x >= max((midX - Gdx.graphics.getWidth() / 2) / scaledSize - 5, 0); x--) {
            for (int y = min((midY + Gdx.graphics.getHeight() / 2) / scaledSize + 1, 160) - 1; y >= max((midY - Gdx.graphics.getHeight() / 2) / scaledSize - 5, 0); y--) {
                Coordinate coordinate = new Coordinate(x, y);
                Tile tile = Map.findTile(coordinate);
                switch (tile.getTileType()) {
                    case TileType.FARM, TileType.PLOWED_FARM, TileType.PLAIN -> {
                        drawGood(tile);
                    }
                }
            }
        }


        drawInventory();
        drawNPCs();
        isPlayerMoved();
        drawFarmingBuilding();
        drawAnimals();


    }

    private void drawNPCs() {
        NPCTypes[] validNPC = new NPCTypes[]{
            NPCTypes.ABIGAIL,
            NPCTypes.HARVEY,
            NPCTypes.ROBIN,
            NPCTypes.SEBASTIAN,
            NPCTypes.LEAH
        };

        for (NPC npc : App.getCurrentGame().getNPCs()) {
            Sprite sprite = new Sprite(new Texture(npc.getType().getImagePath()));
            float x = npc.getType().getCoordinate().getX() * scaledSize;
            float y = npc.getType().getCoordinate().getY() * scaledSize;

            sprite.setPosition(x, y);
            sprite.draw(Main.getBatch());

            if (Arrays.asList(validNPC).contains(npc.getType())) {


                TextButton talk = new TextButton("Talk", style);
                talk.getLabel().setColor(Color.BLACK);
                talk.setSize((float) scaledSize, (float) (0.5 * scaledSize));
                talk.getLabel().setFontScale(0.6f);
                talk.setPosition(x + scaledSize + 10, y);
                stage.addActor(talk);

                TextButton info = new TextButton("info", style);
                info.getLabel().setColor(Color.BLACK);
                info.setSize((float) scaledSize, (float) (0.5 * scaledSize));
                info.getLabel().setFontScale(0.6f);
                info.setPosition(x + scaledSize + 10, y + 22);
                stage.addActor(info);


                talk.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        Texture texture = new Texture(Gdx.files.internal(npc.getType().getAvatarPath()));
                        npcImage = new Image(texture);
                        buildMessage();
                        textFieldMessage.setText(controller.meetNPC(npc.getType().getName()).message());
                    }
                });
                final Window[] questsWindow = {null};

                info.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        Label label = new Label(npc.getType().getName(), skin);
                        Window infoWindow = new Window("NPC Info", skin);
                        infoWindow.getTitleLabel().setFontScale(0.7f);

                        Label friendShip = new Label("Friendship: " + npc.getFriendship().getFriendshipLevel() + "\n" +
                            "Points: " + npc.getFriendship().getFriendshipPoints(), skin);
                        infoWindow.pad(10);
                        infoWindow.add(label).row();
                        infoWindow.add(friendShip).left().padTop(5).row();
                        label.setFontScale(0.5f);
                        friendShip.setFontScale(0.5f);

                        infoWindow.setSize(6 * scaledSize, 5 * scaledSize);
                        infoWindow.setPosition(info.getX(), info.getY() + info.getHeight() + 10);
                        infoWindow.pad(6);

                        TextButton closeButton = new TextButton("X", style);
                        closeButton.setWidth(scaledSize);
                        closeButton.setHeight(scaledSize);
                        closeButton.getLabel().setFontScale(1f);
                        closeButton.setColor(Color.YELLOW);
                        closeButton.getLabel().setColor(Color.BLACK);
                        closeButton.addListener(new ClickListener() {
                            public void clicked(InputEvent event, float x, float y) {
                                infoWindow.remove();
                                if (questsWindow[0] != null) {
                                    questsWindow[0].remove();
                                    questsWindow[0] = null;
                                }
                            }
                        });

                        closeButton.setPosition(infoWindow.getWidth() - closeButton.getWidth(),
                            infoWindow.getHeight() - closeButton.getHeight());
                        infoWindow.addActor(closeButton);

                        TextButton gift = new TextButton("send gift", style);
                        gift.getLabel().setColor(Color.BLACK);
                        gift.setSize((float) 2 * scaledSize, (float) (0.7 * scaledSize));
                        gift.pad(7);
                        infoWindow.add(gift).center().padTop(7).padBottom(5).row();

                        TextButton quest = new TextButton("quests", style);
                        quest.getLabel().setColor(Color.BLACK);
                        quest.setSize((float) 2 * scaledSize, (float) (0.7 * scaledSize));
                        quest.pad(7);
                        infoWindow.add(quest).center().padTop(7).row();

                        gift.addListener(new ClickListener() {
                            public void clicked(InputEvent event, float x, float y) {
                                if (controller.isCloseEnough(npc.getType().getName())) {
                                    Result result = controller.giftNPC(npc.getType().getName(),
                                        App.getCurrentGame().getCurrentPlayer().getInHandGood().getFirst().getName());
                                    buildMessage();
                                    textFieldMessage.setText(result.message());
                                } else {
                                    buildMessage();
                                    textFieldMessage.setText("Too far away. Approach the NPC to send a gift.");
                                }
                            }
                        });

                        quest.addListener(new ClickListener() {
                            public void clicked(InputEvent event, float x, float y) {
                                if (questsWindow[0] != null) {
                                    questsWindow[0].remove();
                                    questsWindow[0] = null;
                                }
                                questsWindow[0] = new Window("Quests", skin);
                                questsWindow[0].getTitleLabel().setFontScale(0.5f);
                                questsWindow[0].pad(10);
                                questsWindow[0].setSize(6 * scaledSize, 4 * scaledSize);
                                questsWindow[0].setPosition(infoWindow.getX() + infoWindow.getWidth() + 10, infoWindow.getY());
                                String result = controller.getQuests(npc.getType().getName());
                                Label labelQuest = new Label(result, skin);
                                labelQuest.setFontScale(0.5f);
                                questsWindow[0].add(labelQuest);
                                stage.addActor(questsWindow[0]);
                            }
                        });

                        stage.addActor(infoWindow);
                    }
                });


            }
        }
    }

    public void buildMessage() {
        lastCoordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();
        float screenWidth = stage.getViewport().getWorldWidth();
        if (textFieldMessage != null) {
            textFieldMessage.remove();
        }
        if (npcImage != null) {
            npcImage.remove();
        }
        textFieldMessage = new TextArea("", skin);
        textFieldMessage.setSize(800, 150);
        float textFieldX = screenWidth / 2 - textFieldMessage.getWidth() / 2;
        float textFieldY = 10f;
        textFieldMessage.setPosition(textFieldX, textFieldY);
        staticStage.addActor(textFieldMessage);

        if (npcImage != null) {
            npcImage.setSize(150, 150);
            npcImage.setPosition(textFieldX - npcImage.getWidth() - 10, textFieldY);
            staticStage.addActor(npcImage);
        }
    }

    private void isPlayerMoved() {
        if (lastCoordinate != null) {
            if (App.getCurrentGame().getCurrentPlayer().getCoordinate().getX() != lastCoordinate.getX() ||
                App.getCurrentGame().getCurrentPlayer().getCoordinate().getY() != lastCoordinate.getY()) {
                textFieldMessage.remove();
                if (npcImage != null) {
                    npcImage.clear();
                    npcImage.remove();
                }

            }
        }
    }

    private void drawGood(Tile tile) {
        Good good;
        Coordinate coordinate = tile.getCordinate();
        if ((good = tile.doesHasTree()) != null ||
            (good = tile.doesHasMineral()) != null ||
            (good = tile.doesHasTreeSapling()) != null ||
            (good = tile.doesHasSeed()) != null) {
            Texture texture = new Texture(good.getType().imagePath());
            Main.getBatch().draw(texture, coordinate.getX() * scaledSize,
                coordinate.getY() * scaledSize, texture.getWidth(), texture.getHeight());
        }
    }

    private void drawAnimals() {
        for (Player player : App.getCurrentGame().getPlayers()) {
            for (FarmBuilding farmBuilding : player.getFarm().getFarmBuildings()) {
                for (Animal animal : farmBuilding.getAnimals()) {
                    if (animal.getPetCounter() >= 0) {
                        Texture texture = new Texture(animal.getAnimalType().getPettedPath());
                        Main.getBatch().draw(texture, (float) (animal.getCoordinate().getX() * scaledSize), (float) (animal.getCoordinate().getY() * scaledSize),
                            (float) (0.8 * scaledSize), (float) (0.8 * scaledSize));
                        break;
                    } else {
                        Texture texture = new Texture(animal.getAnimalType().getImagePath());
                        Main.getBatch().draw(texture, (float) (animal.getCoordinate().getX() * scaledSize), (float) (animal.getCoordinate().getY() * scaledSize),
                            (float) (0.8 * scaledSize), (float) (0.8 * scaledSize));
                        break;
                    }

                }
            }
        }
    }

    private void drawFarmingBuilding() {
        for (Player player : App.getCurrentGame().getPlayers()) {
            for (FarmBuilding farmBuilding : player.getFarm().getFarmBuildings()) {
                if (farmBuilding.getType() != FarmBuildingTypes.HOME) {
                    Main.getBatch().draw(farmBuilding.getType().getInteriorTexture(), (float) (farmBuilding.getStartCordinate().getX() + farmBuilding.getEndCordinate().getX()) / 2 * scaledSize,
                        (float) (farmBuilding.getStartCordinate().getY() + farmBuilding.getEndCordinate().getY()) / 2 * scaledSize,
                        (float) (1.2 * farmBuilding.getType().getSize().second() * scaledSize), (float) (1.2 * farmBuilding.getType().getSize().first() * scaledSize));
                }
            }
        }
    }

    public void drawInventory() {
        for (Quadruple<ImageButton, Image, Label, Label> quadruple : controller.getInventoryController().getInventoryElements()) {
            Table table = new Table();
            table.add(quadruple.a);
            table.add(quadruple.b).padLeft(-48);
            table.add(quadruple.c).padLeft(-88).padTop(-36);
            table.add(quadruple.d).padTop(36).padLeft(-88);
            inventoryTable.add(table);
        }

        controller.getInventoryController().getProgressBar().setValue(
            App.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft()
        );
    }

    public void initFridgeWindow() {
        if (fridgeWindow != null) {
            fridgeWindow.remove();
        }
        this.fridgeTable = new Table(skin);
        this.fridgeTable.setFillParent(true);

        controller.getFridgeController().refreshFridgeElements();
        controller.getFridgeController().updateFridge();

        fridgeTable.clearChildren();
        fridgeTable.clear();

        int columns = 4;
        int count = 0;

        ArrayList<ArrayList<Good>> fridgeItems = App.getCurrentGame().getCurrentPlayer().getFridge().getInFridgeItems();

        for (Pair<Pair<ImageButton, Image>, Integer> pair : controller.getFridgeController().getFridgeElements()) {
            ImageButton imageButtonBackground = pair.first().first();
            Image image = pair.first().second();
            int index = pair.second();

            int quantity = 0;
            ArrayList<Good> goods = fridgeItems.get(index);
            if (!goods.isEmpty()) {
                quantity = goods.size();
            }

            Label countLabel = new Label(String.valueOf(quantity), skin);
            countLabel.setFontScale(0.7f);

            Table itemTable = new Table();
            itemTable.add(imageButtonBackground);
            itemTable.add(image).padLeft(-48);
            itemTable.add(countLabel).padLeft(5);

            fridgeTable.add(itemTable).pad(5);

            count++;
            if (count % columns == 0) {
                fridgeTable.row();
            }
        }

        this.fridgeWindow = new Window("Fridge", skin, "Letter");
        this.fridgeScrollPane = new ScrollPane(fridgeTable, skin);

        fridgeWindow.add(fridgeScrollPane);
        fridgeWindow.setSize(scaledSize * 12, scaledSize * 12);
        fridgeWindow.setPosition(
            (Gdx.graphics.getWidth() - fridgeWindow.getWidth()) / 2,
            (Gdx.graphics.getHeight() - fridgeWindow.getHeight()) / 2
        );

        staticStage.addActor(fridgeWindow);
        setInputProcessor();
    }

    // cooking
    public void initCookingWindow() {
        if (cookingWindow != null) {
            cookingWindow.remove();
        }

        cookingTable = new Table(skin);
        cookingTable.top().left();
        cookingTable.setFillParent(false);

        controller.getCookingController().refreshRecipes();
        ArrayList<Pair<Pair<ImageButton, Image>, Integer>> cookingRecipes = controller.getCookingController().getCookingRecipe();

        int columns = 5;
        int count = 0;

        for (Pair<Pair<ImageButton, Image>, Integer> pair : cookingRecipes) {
            ImageButton slotButton = pair.first().first();
            Image itemImage = pair.first().second();

            Table itemTable = new Table(skin);
            itemTable.add(slotButton).size(64, 64).padRight(5);
            itemTable.add(itemImage).size(48, 48).padLeft(-48);

            cookingTable.add(itemTable).pad(5);

            count++;
            if (count % columns == 0) {
                cookingTable.row();
            }
        }

        ScrollPane cookingScrollPane = new ScrollPane(cookingTable, skin);
        cookingScrollPane.setFadeScrollBars(false);
        cookingScrollPane.setForceScroll(false, true);

        cookingWindow = new Window("Cooking Recipes", skin, "Letter");
        cookingWindow.setSize(700, 550);
        cookingWindow.setPosition(
            (Gdx.graphics.getWidth() - cookingWindow.getWidth()) / 2,
            (Gdx.graphics.getHeight() - cookingWindow.getHeight()) / 2
        );

        cookingWindow.add(cookingScrollPane).padTop(12).expand().fill();
        staticStage.addActor(cookingWindow);
        setInputProcessor();
    }

    public void showRecipeDetails(CookingRecipeType recipeType) {
        if (cookingRecipeWindow != null) {
            cookingRecipeWindow.remove();
        }

        cookingRecipeWindow = new Window("Recipe: " + recipeType.getName(), skin, "Letter");
        cookingRecipeWindow.pad(10);
        cookingRecipeWindow.getTitleLabel().setFontScale(0.7f);
        cookingRecipeWindow.setMovable(true);
        cookingRecipeWindow.setModal(true);

        cookingRecipeWindow.setSize(400, 300);
        cookingRecipeWindow.setPosition(
            (Gdx.graphics.getWidth() - cookingRecipeWindow.getWidth()) / 2 + scaledSize * 6,
            (Gdx.graphics.getHeight() - cookingRecipeWindow.getHeight()) / 2
        );

        Table contentTable = new Table(skin);
        contentTable.pad(10);
        contentTable.defaults().left().pad(4);

        Label recipeLabel = new Label(recipeType.getName(), skin);
        recipeLabel.setFontScale(0.6f);
        contentTable.add(recipeLabel).left().row();

        StringBuilder ingredientsText = new StringBuilder();
        for (Pair<GoodType, Integer> pair : recipeType.getIngredients()) {
            ingredientsText.append("- ").append(pair.first().getName())
                .append(" × ").append(pair.second()).append("\n");
        }

        Label ingredientsLabel = new Label("Ingredients:\n\n" + ingredientsText.toString(), skin);
        ingredientsLabel.setFontScale(0.5f);
        contentTable.add(ingredientsLabel).left().row();

        final Label cookingResult = new Label(" ", skin);
        cookingResult.setFontScale(0.5f);
        cookingResult.setAlignment(Align.center);
        cookingResult.setWrap(true);
        contentTable.add(cookingResult)
            .center()
            .minWidth(300)
            .minHeight(40)
            .row();

        Table buttonTable = new Table();
        TextButton closeButton = new TextButton("X", style);
        closeButton.pad(5);
        TextButton cookingButton = new TextButton("Cook", style);
        cookingButton.pad(5);

        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cookingRecipeWindow.remove();
            }
        });

        cookingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result res = controller.cookingPrepare(recipeType.getName());
                cookingResult.setText(res.message());
            }
        });

        buttonTable.add(closeButton).pad(5);
        buttonTable.add(cookingButton).pad(5);
        contentTable.add(buttonTable).center().padTop(10).row();

        cookingRecipeWindow.add(contentTable).expand().fill();

        staticStage.addActor(cookingRecipeWindow);

        setInputProcessor();
    }

    // crafting
    public void initCraftingWindow() {
        if (craftingWindow != null) {
            craftingWindow.remove();
        }

        craftingWindow = new Window("Crafting Recipes", skin, "Letter");
        craftingWindow.setSize(700, 550);
        craftingWindow.setPosition(
            (Gdx.graphics.getWidth() - craftingWindow.getWidth()) / 2,
            (Gdx.graphics.getHeight() - craftingWindow.getHeight()) / 2
        );

        craftingUseTable = new Table(skin);
        craftingUseTable.top().left();
        craftingUseTable.setFillParent(false);

        controller.getCraftingController().refreshRecipes();
        ArrayList<Pair<Pair<ImageButton, Image>, Integer>> craftingRecipes = controller.getCraftingController().getCraftingRecipes();

        int columns = 5;
        int count = 0;

        for (Pair<Pair<ImageButton, Image>, Integer> pair : craftingRecipes) {
            ImageButton slotButton = pair.first().first();
            Image itemImage = pair.first().second();

            Table itemTable = new Table(skin);
            itemTable.add(slotButton).size(64, 64).padRight(5);
            itemTable.add(itemImage).size(48, 48).padLeft(-48);

            craftingUseTable.add(itemTable).pad(5);

            count++;
            if (count % columns == 0) {
                craftingUseTable.row();
            }
        }

        ScrollPane craftingScrollPane = new ScrollPane(craftingUseTable, skin);
        craftingScrollPane.setFadeScrollBars(false);
        craftingScrollPane.setForceScroll(false, true);

        craftingWindow.add(craftingScrollPane).padTop(12).expand().fill();
        staticStage.addActor(craftingWindow);

        setInputProcessor();
    }


    public void showCraftingRecipeDetails(CraftingRecipeType recipeType) {
        if (craftingRecipeWindow != null) {
            craftingRecipeWindow.remove();
        }

        craftingRecipeWindow = new Window("Recipe: " + recipeType.getName(), skin, "Letter");
        craftingRecipeWindow.pad(10);
        craftingRecipeWindow.getTitleLabel().setFontScale(0.7f);
        craftingRecipeWindow.setMovable(true);
        craftingRecipeWindow.setModal(true);

        craftingRecipeWindow.setSize(400, 300);
        craftingRecipeWindow.setPosition(
            (Gdx.graphics.getWidth() - craftingRecipeWindow.getWidth()) / 2 + scaledSize * 6,
            (Gdx.graphics.getHeight() - craftingRecipeWindow.getHeight()) / 2
        );

        Table contentTable = new Table(skin);
        contentTable.pad(10);
        contentTable.defaults().left().pad(4);

//        multiplexer.clear();
//        multiplexer.addProcessor(recipeWindow.getStage());
//        multiplexer.addProcessor(staticStage);
//        Gdx.input.setInputProcessor(multiplexer);
        Label recipeLabel = new Label(recipeType.getName(), skin);
        recipeLabel.setFontScale(0.6f);
        contentTable.add(recipeLabel).left().row();

        StringBuilder ingredientsText = new StringBuilder();
        for (Pair<GoodType, Integer> pair : recipeType.getIngredients()) {
            ingredientsText.append("- ").append(pair.first().getName())
                .append(" × ").append(pair.second()).append("\n");
        }

        Label ingredientsLabel = new Label("Ingredients:\n\n" + ingredientsText.toString(), skin);
        ingredientsLabel.setFontScale(0.5f);
        contentTable.add(ingredientsLabel).left().row();

        final Label buildingResult = new Label(" ", skin);
        buildingResult.setFontScale(0.5f);
        buildingResult.setAlignment(Align.center);
        buildingResult.setWrap(true);
        contentTable.add(buildingResult).center().minWidth(300).minHeight(40).row();

        Table buttonTable = new Table();
        TextButton closeButton = new TextButton("X", style);
        closeButton.pad(5);
        TextButton craftingButton = new TextButton("Build", style);
        craftingButton.pad(5);

        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                craftingRecipeWindow.remove();
            }
        });

        craftingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result res = controller.craftingCraft(recipeType.getName());
                buildingResult.setText(res.message());
            }
        });

        buttonTable.add(closeButton).pad(5);
        buttonTable.add(craftingButton).pad(5);
        contentTable.add(buttonTable).center().padTop(10).row();

        craftingRecipeWindow.add(contentTable).expand().fill();

        staticStage.addActor(craftingRecipeWindow);
    }

    public int getScaledSize() {
        return scaledSize;
    }

    public void initToolsWindow() {
        this.toolsTable = new Table(skin);
        this.toolsTable.setFillParent(true);

        controller.getInventoryController().getToolsElements().clear();
        TextureRegionDrawable drawableSlot = Assets.getInstance().getDrawableSlot();
        TextureRegionDrawable drawableHighlight = Assets.getInstance().getDrawableHighlight();

        for (int i = 0; i < controller.getInventoryController().getInventoryElements().size(); i++) {
            ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i);
            if (!goods.isEmpty() && goods.getLast() instanceof Tool) {
                ImageButton imageButtonBackground = new ImageButton(drawableSlot, drawableSlot, drawableHighlight);
                Image image = new Image(new TextureRegion(new Texture(goods.getFirst().getType().imagePath())));

                Image finalImage = image;
                image.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        for (int j = 0; j < controller.getInventoryController().getToolsElements().size(); j++) {
                            Pair<Pair<ImageButton, Image>, Integer> pair =
                                controller.getInventoryController().getToolsElements().get(j);
                            pair.first().first().setChecked(false);
                            if (pair.first().second() == finalImage) {
                                pair.first().first().setChecked(true);
                                App.getCurrentGame().getCurrentPlayer().setInHandGood(
                                    App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(pair.second())
                                );
                                closeToolsWindow();
                            }
                        }

                    }
                });

                controller.getInventoryController().getToolsElements().add(
                    new Pair<>(new Pair<>(imageButtonBackground, image), i)
                );

                Table table = new Table();
                table.add(imageButtonBackground);
                table.add(image).padLeft(-48);
                toolsTable.add(table);
            }
        }

        this.toolsWindow = new Window("Tools", skin, "Letter");
        this.toolsScrollPane = new ScrollPane(toolsTable, skin);

        toolsWindow.add(toolsScrollPane);
        toolsWindow.setSize(350, 120);
        toolsWindow.setPosition(
            (Gdx.graphics.getWidth() - toolsWindow.getWidth()) / 2,
            (Gdx.graphics.getHeight() - toolsWindow.getHeight()) / 2
        );

        staticStage.addActor(toolsWindow);
        setInputProcessor();
    }

    public Window getToolsWindow() {
        return toolsWindow;
    }

    public Stage getStage() {
        return stage;
    }

    private ArrayList<ImageButton> mainInventoryElements;
    private Table mainTable;


    private Window currentWindow = null; // Currently shown window
    private Container<Window> windowContainer;
    public void initMainTable(int index) {
        ArrayList<Window> inventoryWindows = controller.getInventoryController().getInventoryWindows();
        mainInventoryElements = controller.getInventoryController().getMainInventoryElements();
        mainTable = new Table(skin);
        mainTable.setFillParent(true);

        for (int i = 0; i < 6; i++) {
            ImageButton imageButton = controller.getInventoryController().getMainInventoryElements().get(i);
            if (i == index) {
                mainTable.add(imageButton);
                imageButton.setChecked(true);
            } else if (i == 5)
                mainTable.add(imageButton).padLeft(100);
            else
                mainTable.add(imageButton);
        }
        mainTable.row();

        currentWindow = controller.getInventoryController().getInventoryWindows().get(index);
        windowContainer = new Container<>(currentWindow);
        windowContainer.size(1000, 800);

        mainTable.add(windowContainer).colspan(7); // Only add container to table

        staticStage.addActor(mainTable);
        setInputProcessor();
    }

    public void switchWindow(Window newWindow ,int index) {

        currentWindow = newWindow;
        windowContainer.setActor(currentWindow); // Replaces content without changing layout
    }

    private void setInputProcessor() {
//        multiplexer.clear();
//        multiplexer.addProcessor(stage);
//        multiplexer.addProcessor(staticStage);
//        multiplexer.addProcessor(this);
//        Gdx.input.setInputProcessor(multiplexer);
    }

    public void closeToolsWindow() {
        toolsWindow.remove();
        toolsTable.remove();
        toolsWindow = null;
        setInputProcessor();
    }

    public void closeMainTable() {
        mainTable.remove();
        mainTable = null;
        //mainWindow.remove();
    }

    public Table getMainTable() {
        return mainTable;
    }

    public void initCheatWindow() {
        this.cheatWindow = new Window("Cheat Window", skin, "Letter");
        this.cheatTable = new Table(skin);
        cheatTable.setFillParent(true);

        this.cheatTextField = new TextField("", skin);
        this.cheatButton = new TextButton("Submit", skin);
        this.cheatButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameMenu gameMenu = new GameMenu();
                gameMenu.check(cheatTextField.getText());
                closeCheatWindow();
            }
        });

        this.cheatTable.add(cheatTextField).center().width(400).row();
        this.cheatTable.add(cheatButton).center().width(400).row();
        this.cheatWindow.addActor(cheatTable);
        this.cheatWindow.setSize(600, 300);
        this.cheatWindow.setPosition(
            (Gdx.graphics.getWidth() - cheatWindow.getWidth()) / 2,
            (Gdx.graphics.getHeight() - cheatWindow.getHeight()) / 2
        );

        staticStage.addActor(cheatWindow);
        setInputProcessor();
    }

    public void closeCheatWindow() {
        cheatWindow.remove();
        cheatTable.remove();
        cheatWindow = null;
        setInputProcessor();
    }

    public Window getCheatWindow() {
        return cheatWindow;
    }

    private void goodsListInit(TextButton addButton, Label countLabel, TextButton removeButton, TextButton purchaseButton,
                               GameBuilding gameBuilding, Label selectedNameLabel, Table itemsTable, GoodType[] selectedGoodType,
                               int[] selectedCount, boolean[] filterAvailable, TextButton filterButton, Label info) {
        itemsTable.clear();
        itemsTable.add(filterButton)
            .fillX()
            .pad(5)
            .row();

        ArrayList<GoodType> goodTypes = (filterAvailable[0]) ? gameBuilding.showProducts() : gameBuilding.showAllProducts();
        for (GoodType product : goodTypes) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(product.getName());
            stringBuilder.append(" - ");
            if(product.getSellPrice() <= 0)
                stringBuilder.append("Free");
            else
                stringBuilder.append(product.getSellPrice());
            TextButton productButton = new TextButton(stringBuilder.toString(), skin);

            productButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (gameBuilding.findProduct(product).second() == 0) {
                        info.setText("This product's daily quantity is 0");
                        info.setVisible(true);
                        addButton.setVisible(false);
                        removeButton.setVisible(false);
                        countLabel.setVisible(false);
                        purchaseButton.setVisible(false);
                    } else {
                        selectedGoodType[0] = null;
                        addButton.setVisible(true);
                        removeButton.setVisible(true);
                        countLabel.setVisible(true);
                        selectedGoodType[0] = product;
                        selectedCount[0] = 0;
                        selectedNameLabel.setText(product.getName());
                        countLabel.setText(String.valueOf(selectedCount[0]));
                        purchaseButton.setVisible(true);
                        info.setVisible(false);
                    }
                }
            });

            itemsTable.add(productButton)
                .fillX()
                .pad(5)
                .row();
        }
    }

    private void toolsUpgradeInit(Window window) {
        staticStage.addActor(window);
    }

    private void lastConstructionsForShop(Label info, Table counterPanel, Table selectedPanel, ScrollPane scrollPane, Table content) {
        info.setWrap(true);
        info.setWidth(250);
        info.setFontScale(0.7f);
        info.setAlignment(Align.center);

        counterPanel.add(info)
            .width(250)
            .pad(5)
            .colspan(3)
            .center()
            .row();

        selectedPanel.add(counterPanel)
            .colspan(3)
            .center()
            .padBottom(40)
            .row();


        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.clear();

        mainTable.add(scrollPane)
            .width(380)
            .expandY()
            .fillY()
            .pad(20)
            .padRight(30)
            .padLeft(170);

        mainTable.add(selectedPanel)
            .width(200)
            .expandY()
            .fillY()
            .pad(30)
            .padLeft(50)
            .bottom();

        content.add(mainTable)
            .expand()
            .fill();
    }

    public TextField getTextFieldMessage() {
        return textFieldMessage;
    }

    public Boolean getFridgeOpen() {
        return isFridgeOpen;
    }

    public void setFridgeOpen(Boolean fridgeOpen) {
        isFridgeOpen = fridgeOpen;
    }

    public Window getFridgeWindow() {
        return fridgeWindow;
    }

    public Boolean getCookingOpen() {
        return isCookingOpen;
    }

    public void setCookingOpen(Boolean cookingOpen) {
        isCookingOpen = cookingOpen;
    }

    public Window getCookingWindow() {
        return cookingWindow;
    }

    private void initGreenHouseWindow() {
        Window greenHouseWindow = new Window("Build GreenHouse", skin, "Letter");
        greenHouseWindow.setSize(1200, 400);
        greenHouseWindow.setResizable(false);
        greenHouseWindow.setPosition(
            (staticStage.getWidth() - greenHouseWindow.getWidth()) / 2,
            (staticStage.getHeight() - greenHouseWindow.getHeight()) / 2
        );

        Label label = new Label("For Building greenhouse, you require 1000G & 500 Woods!", skin);
        TextButton button = new TextButton("Build GreenHouse", skin);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result res = controller.greenHouseBuild();
                label.setText(res.message());
                button.setText("Back");
                button.removeListener(this);
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        greenHouseWindow.remove();
                    }
                });
            }
        });

        greenHouseWindow.add(label).fillX().row();
        greenHouseWindow.add(button).fillX().row();

        staticStage.addActor(greenHouseWindow);

    }

    public Window getCraftingWindow(){
        return craftingWindow;
    }

    public void setIsCraftingOpen(boolean isCraftingOpen) {
        this.isCraftingOpen = isCraftingOpen;
    }

    public Boolean getIsCraftingOpen() {
        return isCraftingOpen;
    }


    final Label tradeInfoLabel = new Label("", skin);
    final Label tradeWithGoodsLabel = new Label("", skin);
    private void initTradeWindow() {
        this.tradeWindow = new Window("Trade", skin, "Letter");
        this.tradeWindow.setSize(1000, 560);
        this.tradeWindow.setResizable(false);
        tradeWindow.setPosition(
            (staticStage.getWidth() - tradeWindow.getWidth()) / 2,
            (staticStage.getHeight() - tradeWindow.getHeight()) / 2
        );
        tradeTable = new Table(skin);
        tradeTable.setFillParent(true);
        tradeTable.pad(40).padRight(130);

        tradeInfoLabel.setColor(Color.RED);
        tradeWithGoodsLabel.setColor(Color.RED);

        for (Player player : App.getCurrentGame().getCurrentPlayer().getFriendShips().keySet()) {
            Pair<Integer, Integer> pair = App.getCurrentGame().getCurrentPlayer().getFriendShips().get(player);
            Label label = new Label(player.getPlayerUsername() + "> Level: " + pair.first(), skin);
            TextButton goodsButton = new TextButton("Goods", skin);
            TextButton moneyButton = new TextButton("Money", skin);
            goodsButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    closeTradeWindow();
                    initPlayerTradeWithGoodsWindow(player);
                }
            });
            moneyButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    closeTradeWindow();
                    initPlayerTradeWithMoneyWindow(player);
                }
            });

            tradeTable.add(label).height(50).padRight(50);
            tradeTable.add(goodsButton).height(70).width(150).height(70);
            tradeTable.add(moneyButton).height(70).width(150).height(70);
            tradeTable.row();
            tradeTable.add(tradeInfoLabel).colspan(2).row();
        }

        tradeBackButton = new TextButton("Back", skin);
        tradeBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeTradeWindow();
            }
        });
        tradeTable.add(tradeBackButton).colspan(2).width(200).height(70).padTop(10).row();

        tradeWindow.add(tradeTable);
        staticStage.addActor(tradeWindow);
    }
    private void closeTradeWindow() {
        tradeWindow.remove();
        tradeTable.remove();
        tradeWindow = null;
    }



    private void initPlayerTradeWithGoodsWindow(Player receiver) {
        Window window = new Window("New Goods Trade With " + receiver.getPlayerUsername(), skin);
        window.setSize(1000, 800);
        window.setPosition(
            (staticStage.getWidth() - window.getWidth()) / 2,
            (staticStage.getHeight() - window.getHeight()) / 2
        );

        Table table = new Table(skin);
        table.pad(15);
        table.defaults().pad(5).fillX();

        final SelectBox<String> tradeTypeBox = new SelectBox<>(skin);
        tradeTypeBox.setItems("OFFER", "REQUEST");
        tradeTypeBox.setSelected("OFFER");

        goodsDropdown = new SelectBox<>(skin);
        Array<String> inventoryNames = new Array<>();
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty())
                inventoryNames.add(goods.getLast().getName());

        }
        goodsDropdown.setItems(inventoryNames);

        final TextField amountField = new TextField("", skin);
        amountField.setMessageText("e.g. 10");
        amountField.setTextFieldFilter((textField, c) -> Character.isDigit(c)); // Only digits allowed

        final TextField demandItemField = new TextField("", skin);
        demandItemField.setMessageText("e.g. Wheat");

        final TextField demandAmountField = new TextField("", skin);
        amountField.setMessageText("e.g. 10");
        amountField.setTextFieldFilter((textField, c) -> Character.isDigit(c)); // Only digits allowed

        TextButton submitButton = new TextButton("Send Trade", skin);
        TextButton cancelButton = new TextButton("Cancel", skin);

        // Layout components
        table.add(new Label("Trade Type:", skin)).left();
        table.add(tradeTypeBox).width(200).row();

        table.add(new Label("Goods:", skin)).left();
        table.add(goodsDropdown).width(200).row();

        table.add(new Label("Amount:", skin)).left();
        table.add(amountField).width(200).row();

        table.add(new Label("Target Goods:", skin)).left();
        table.add(demandItemField).width(200).row();

        table.add(new Label("Target Amount:", skin)).left();
        table.add(demandAmountField).width(200).row();

        table.add(tradeWithGoodsLabel).width(200).row();

        Table buttonTable = new Table(skin);
        buttonTable.defaults().expandX().fillX().pad(5);
        buttonTable.add(submitButton);
        buttonTable.add(cancelButton);

        table.add(buttonTable).colspan(2).padTop(10).row();

        window.add(table);
        staticStage.addActor(window);

        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
            }
        });


        submitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tradeInfoLabel.setText(""); // Clear previous errors
                tradeWithGoodsLabel.setText("");
                String selectedItem = goodsDropdown.getSelected();
                String amountStr = amountField.getText().trim();
                String demandItemStr = demandItemField.getText().trim();
                String demandAmountStr = demandAmountField.getText().trim();
                String tradeType = tradeTypeBox.getSelected();

                TradeType type = TradeType.valueOf(tradeType);

                if(goodsDropdown.isDisabled()){
                    tradeWithGoodsLabel.setText("No good selected");
                } else if (selectedItem == null || selectedItem.isEmpty()) {
                    tradeWithGoodsLabel.setText("Please select an item.");
                    return;
                } else if (amountStr.isEmpty()) {
                    tradeWithGoodsLabel.setText("Please enter Amount.");
                    return;
                } else if(demandItemStr.isEmpty()) {
                    tradeWithGoodsLabel.setText("Please enter Target Item.");
                    return;
                } else if(demandAmountStr.isEmpty()) {
                    tradeWithGoodsLabel.setText("Please enter Target Item Amount.");
                    return;
                }
                if(type == TradeType.OFFER) {
                    ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(selectedItem);
                    if(goods == null){
                        tradeWithGoodsLabel.setText("Your don't have " + selectedItem + " in your inventory!");
                        return;
                    }
                    if(goods.size() < Integer.parseInt(amountStr)){
                        tradeWithGoodsLabel.setText("Your don't have enough number of " + selectedItem + " in your inventory!");
                        return;
                    }
                }
                if(type == TradeType.REQUEST) {
                    ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(demandItemStr);
                    if (goods == null){
                        tradeWithGoodsLabel.setText("Your don't have " + demandItemStr + " in your inventory!");
                        return;
                    }
                    if(goods.size() < Integer.parseInt(demandAmountStr)){
                        tradeWithGoodsLabel.setText("Your don't have enough number of " + demandItemStr + " in your inventory!");
                        return;
                    }
                }

                Result result = tradeController.tradeWithGoods(receiver.getPlayerUsername(), tradeType, selectedItem, amountStr, demandItemStr, demandAmountStr);
                tradeInfoLabel.setText(result.message());

                window.remove(); // close window on success
                initTradeWindow();
            }
        });
    }

    private void initPlayerTradeWithMoneyWindow(Player receiver) {
        Window window = new Window("New Money Trade With " + receiver.getPlayerUsername(), skin);
        window.setSize(1000, 800);
        window.setPosition(
            (staticStage.getWidth() - window.getWidth()) / 2,
            (staticStage.getHeight() - window.getHeight()) / 2
        );

        Table table = new Table(skin);
        table.pad(15);
        table.defaults().pad(5).fillX();

        final SelectBox<String> tradeTypeBox = new SelectBox<>(skin);
        tradeTypeBox.setItems("OFFER", "REQUEST");
        tradeTypeBox.setSelected("OFFER");

        goodsDropdown = new SelectBox<>(skin);
        Array<String> inventoryNames = new Array<>();
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty())
                inventoryNames.add(goods.getLast().getName());

        }
        goodsDropdown.setItems(inventoryNames);

        final TextField amountField = new TextField("", skin);
        amountField.setMessageText("e.g. 10");
        amountField.setTextFieldFilter((textField, c) -> Character.isDigit(c)); // Only digits allowed

        final TextField priceField = new TextField("", skin);
        amountField.setMessageText("e.g. 10");
        amountField.setTextFieldFilter((textField, c) -> Character.isDigit(c)); // Only digits allowed

        TextButton submitButton = new TextButton("Send Trade", skin);
        TextButton cancelButton = new TextButton("Cancel", skin);

        // Layout components
        table.add(new Label("Trade Type:", skin)).left();
        table.add(tradeTypeBox).width(200).row();

        table.add(new Label("Goods:", skin)).left();
        table.add(goodsDropdown).width(200).row();

        table.add(new Label("Amount:", skin)).left();
        table.add(amountField).width(200).row();

        table.add(new Label("Price:", skin)).left();
        table.add(priceField).width(200).row();

        table.add(tradeWithGoodsLabel).width(200).row();

        Table buttonTable = new Table(skin);
        buttonTable.defaults().expandX().fillX().pad(5);
        buttonTable.add(submitButton);
        buttonTable.add(cancelButton);

        table.add(buttonTable).colspan(2).padTop(10).row();

        window.add(table);
        staticStage.addActor(window);

        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
            }
        });


        submitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tradeInfoLabel.setText(""); // Clear previous errors
                tradeWithGoodsLabel.setText("");
                String selectedItem = goodsDropdown.getSelected();
                String amountStr = amountField.getText().trim();
                String priceStr = priceField.getText().trim();
                String tradeType = tradeTypeBox.getSelected();

                TradeType type = TradeType.valueOf(tradeType);

                if(goodsDropdown.isDisabled()){
                    tradeWithGoodsLabel.setText("No good selected");
                } else if (selectedItem == null || selectedItem.isEmpty()) {
                    tradeWithGoodsLabel.setText("Please select an item.");
                    return;
                } else if (amountStr.isEmpty()) {
                    tradeWithGoodsLabel.setText("Please enter Amount.");
                    return;
                } else if(priceStr.isEmpty()) {
                    tradeWithGoodsLabel.setText("Please enter Target Item Amount.");
                    return;
                }
                if(type == TradeType.OFFER) {
                    ArrayList<Good> goods = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(selectedItem);
                    if(goods == null)
                        tradeWithGoodsLabel.setText( "Your don't have " + selectedItem + " in your inventory!");
                    if(goods.size() < Integer.parseInt(amountStr))
                        tradeWithGoodsLabel.setText("Your don't have enough number of " + selectedItem + " in your inventory!");
                }
                if(type == TradeType.REQUEST) {
                    if(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() < Integer.parseInt(priceStr))
                        tradeWithGoodsLabel.setText( "Your don't enough Money in your wallet!");
                }

                Result result = tradeController.tradeWithMoney(receiver.getPlayerUsername(), tradeType, selectedItem, amountStr, priceStr);
                tradeInfoLabel.setText(result.message());

                window.remove(); // close window on success
                initTradeWindow();
            }
        });
    }

    Label feedbackLabel = new Label("", skin);
    private void initTradeInboxWindow() {
        Window inboxWindow = new Window("Trade Inbox", skin, "Letter");
        inboxWindow.setSize(1350, 800); // smaller width and height for a popup
        inboxWindow.setPosition(
            (staticStage.getWidth() - inboxWindow.getWidth()) / 2,
            (staticStage.getHeight() - inboxWindow.getHeight()) / 2
        );
        feedbackLabel.setColor(Color.RED);
        feedbackLabel.setText("");

        Table inboxTable = new Table(skin).pad(10);

        java.util.List<Trade> tradeData = tradeManager.getRespondedTradesFor(App.getCurrentGame().getCurrentPlayer());

        Table tradesListTable = new Table(skin); // this table holds trade rows

        boolean foundAny = false;
        for (Trade trade : tradeData) {
            // Only show trades for current player and not responded
            if (trade.getReceiver().equals(App.getCurrentGame().getCurrentPlayer()) && !trade.isResponded()) {
                foundAny = true;

                Label tradeLabel = new Label(trade.shortTradeString(), skin);
                TextButton acceptButton = new TextButton("Accept", skin);
                TextButton rejectButton = new TextButton("Reject", skin);

                acceptButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Result result = tradeController.tradeResponse("–accept", String.valueOf(trade.getId()));
                        feedbackLabel.setText(result.message());
                        inboxWindow.remove();
                        reInitTradeInboxWindow();
                    }
                });

                rejectButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Result result = tradeController.tradeResponse("–reject", String.valueOf(trade.getId()));
                        feedbackLabel.setText(result.message());
                        inboxWindow.remove();
                        reInitTradeInboxWindow();
                    }
                });

                // Add one trade row: trade label + accept + reject
                tradesListTable.add(tradeLabel).expandX().left().padRight(10);
                tradesListTable.add(acceptButton).width(100).padRight(5);
                tradesListTable.add(rejectButton).width(100);
                tradesListTable.row().padBottom(8);
            }
        }

        if (!foundAny) {
            tradesListTable.add(new Label("No incoming trades found.", skin));
        }

        // Wrap the trades list table in a scroll pane
        ScrollPane scrollPane = new ScrollPane(tradesListTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);
        scrollPane.setForceScroll(false, true);

        // Add scroll pane to main inboxTable with fixed size
        inboxTable.add(scrollPane).padTop(100).width(1250).height(500).row();

        // Add feedback label and close button
        inboxTable.add(feedbackLabel).colspan(3).padTop(10).row();

        TextButton closeBtn = new TextButton("Close", skin);
        closeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inboxWindow.remove();
            }
        });
        TextButton historyBtn = new TextButton("History", skin);
        historyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inboxWindow.remove();
                initTradeHistoryWindow();
            }
        });

        inboxTable.add(closeBtn).colspan(3).padTop(10);
        inboxTable.add(historyBtn).padLeft(-300).colspan(3);

        inboxWindow.add(inboxTable);
        staticStage.addActor(inboxWindow);
    }

    private void reInitTradeInboxWindow() {
        Window inboxWindow = new Window("Trade Inbox", skin, "Letter");
        inboxWindow.setSize(1350, 800); // smaller width and height for a popup
        inboxWindow.setPosition(
            (staticStage.getWidth() - inboxWindow.getWidth()) / 2,
            (staticStage.getHeight() - inboxWindow.getHeight()) / 2
        );
        feedbackLabel.setColor(Color.RED);

        Table inboxTable = new Table(skin).pad(10);

        java.util.List<Trade> tradeData = tradeManager.getRespondedTradesFor(App.getCurrentGame().getCurrentPlayer());

        Table tradesListTable = new Table(skin); // this table holds trade rows

        boolean foundAny = false;
        for (Trade trade : tradeData) {
            // Only show trades for current player and not responded
            if (trade.getReceiver().equals(App.getCurrentGame().getCurrentPlayer()) && !trade.isResponded()) {
                foundAny = true;

                Label tradeLabel = new Label(trade.shortTradeString(), skin);
                TextButton acceptButton = new TextButton("Accept", skin);
                TextButton rejectButton = new TextButton("Reject", skin);

                acceptButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Result result = tradeController.tradeResponse("–accept", String.valueOf(trade.getId()));
                        feedbackLabel.setText(result.message());
                        inboxWindow.remove();
                        reInitTradeInboxWindow();
                    }
                });

                rejectButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Result result = tradeController.tradeResponse("–reject", String.valueOf(trade.getId()));
                        feedbackLabel.setText(result.message());
                        inboxWindow.remove();
                        reInitTradeInboxWindow();
                    }
                });

                // Add one trade row: trade label + accept + reject
                tradesListTable.add(tradeLabel).expandX().left().padRight(10);
                tradesListTable.add(acceptButton).width(100).padRight(5);
                tradesListTable.add(rejectButton).width(100);
                tradesListTable.row().padBottom(8);
            }
        }

        if (!foundAny) {
            tradesListTable.add(new Label("No incoming trades found.", skin));
        }

        // Wrap the trades list table in a scroll pane
        ScrollPane scrollPane = new ScrollPane(tradesListTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);
        scrollPane.setForceScroll(false, true);

        // Add scroll pane to main inboxTable with fixed size
        inboxTable.add(scrollPane).padTop(100).width(1250).height(500).row();

        // Add feedback label and close button
        inboxTable.add(feedbackLabel).colspan(3).padTop(10).row();

        TextButton closeBtn = new TextButton("Close", skin);
        closeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inboxWindow.remove();
            }
        });
        TextButton historyBtn = new TextButton("History", skin);
        historyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inboxWindow.remove();
                initTradeHistoryWindow();
            }
        });

        inboxTable.add(closeBtn).colspan(3).padTop(10);
        inboxTable.add(historyBtn).padLeft(-300).colspan(3);

        inboxWindow.add(inboxTable);
        staticStage.addActor(inboxWindow);
    }

    private void initTradeHistoryWindow() {
        Window inboxWindow = new Window("Trade History", skin, "Letter");
        inboxWindow.setSize(1350, 800);
        inboxWindow.setPosition(
            (staticStage.getWidth() - inboxWindow.getWidth()) / 2,
            (staticStage.getHeight() - inboxWindow.getHeight()) / 2
        );

        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        java.util.List<Trade> history = currentPlayer.getTradeHistory();

        Table inboxTable = new Table(skin).pad(10);

        Table tradesListTable = new Table(skin);

        for (Trade trade : history) {
            Label tradeLabel = new Label(trade.shortTradeHistoryString(), skin);
            tradesListTable.add(tradeLabel).expandX().left().padRight(10);
            tradesListTable.row().padBottom(8);
        }

        if (history.isEmpty()) {
            tradesListTable.add(new Label("No trade History found.", skin));
        }

        ScrollPane scrollPane = new ScrollPane(tradesListTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);
        scrollPane.setForceScroll(false, true);

        inboxTable.add(scrollPane).padTop(100).width(1250).height(500).row();

        inboxTable.add(feedbackLabel).colspan(3).padTop(10).row();

        TextButton closeBtn = new TextButton("Close", skin);
        closeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inboxWindow.remove();
                reInitTradeInboxWindow();
            }
        });

        inboxTable.add(closeBtn).colspan(3).padTop(10);

        inboxWindow.add(inboxTable);
        staticStage.addActor(inboxWindow);
    }

    private void initFriendshipsWindow() {
        this.friendsWindow = new Window("Friendships", skin, "Letter");
        this.friendsWindow.setSize(1000, 350);
        this.friendsWindow.setResizable(false);
        friendsWindow.setPosition(
            (staticStage.getWidth() - friendsWindow.getWidth()) / 2,
            (staticStage.getHeight() - friendsWindow.getHeight()) / 2
        );
        friendsTable = new Table(skin);
        friendsTable.setFillParent(true);
        friendsTable.pad(40).padRight(130);

        for (Player player : App.getCurrentGame().getCurrentPlayer().getFriendShips().keySet()) {
            Pair<Integer, Integer> pair = App.getCurrentGame().getCurrentPlayer().getFriendShips().get(player);
            Label label = new Label(player.getPlayerUsername() + "> Level: " +
                pair.first() + ", Value: " + pair.second(), skin);
            TextButton button = new TextButton("Send Gift", skin);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    closeFriendshipsWindow();
                    initPlayerGiftWindow(player);
                }
            });


            friendsTable.add(label).height(50).padRight(50);
            friendsTable.add(button).height(70);
            friendsTable.row();
        }

        friendsBackButton = new TextButton("Back", skin);
        friendsBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeFriendshipsWindow();
            }
        });
        friendsTable.add(friendsBackButton).colspan(2).fillX().height(70).padTop(10).row();

        friendsWindow.add(friendsTable);
        staticStage.addActor(friendsWindow);
    }

    private void closeFriendshipsWindow() {
        friendsWindow.remove();
        friendsTable.remove();
        friendsWindow = null;
    }

    private void initPlayerGiftWindow(Player player) {
        playerGiftWindow = new Window(player.getPlayerUsername() + " Gift", skin);
        playerGiftWindow.setSize(1300, 800);
        this.playerGiftWindow.setResizable(false);
        playerGiftWindow.setPosition(
            (staticStage.getWidth() - playerGiftWindow.getWidth()) / 2,
            (staticStage.getHeight() - playerGiftWindow.getHeight()) / 2
        );
        playerGiftTable = new Table(skin);
        playerGiftTable.setFillParent(true);
        playerGiftTable.pad(40).padRight(150);

        playerGiftLabel = new Label("Select your gift: ", skin);
        playerGiftTable.add(playerGiftLabel).padRight(10);
        playerGiftSelectBox = new SelectBox<>(skin);
        Array<String> inventoryNames = new Array<>();
        for (ArrayList<Good> goods : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
            if (!goods.isEmpty())
                inventoryNames.add(goods.getLast().getName());

        }
        playerGiftSelectBox.setItems(inventoryNames);
        playerGiftSelectBox.setSelectedIndex(0);
        playerGiftTable.add(playerGiftSelectBox).padRight(10);

        playerCountGiftSelectBox = new SelectBox<>(skin);
        Array<Integer> counters = new Array<>();
        int ptr = App.getCurrentGame().getCurrentPlayer().getInventory().getFirstElementSize();
        for (int i = 1; i <= ptr; i++) {
            counters.add(i);
        }
        playerCountGiftSelectBox.setItems(counters);
        playerGiftSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Array<Integer> counters = new Array<>();
                String selected = playerGiftSelectBox.getSelected();
                int ptr = App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(selected).size();
                for (int i = 1; i <= ptr; i++) {
                    counters.add(i);
                }

                playerCountGiftSelectBox.setItems(counters);
            }
        });
        playerGiftTable.add(playerCountGiftSelectBox).padRight(10);

        playerGiftButton = new TextButton("Send Gift", skin);
        playerGiftTable.add(playerGiftButton).row();
        messageGiftLabel = new Label("", skin);
        playerGiftTable.add(messageGiftLabel).colspan(4).row();
        playerGiftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result res = controller.gift(player.getPlayerUsername(), playerGiftSelectBox.getSelected(),
                    Integer.toString(playerCountGiftSelectBox.getSelected()));

                messageGiftLabel.setText(res.message());
            }
        });

        giftTable = new Table(skin);
        int giftListPtr = 1;
        for (Pair<Player, Gift> playerGiftPair : App.getCurrentGame().getCurrentPlayer().getGiftList()) {
            if (!playerGiftPair.first().getPlayerUsername().equals(player.getPlayerUsername()))
                continue;

            Label label = new Label(giftListPtr++ + ". From <" + playerGiftPair.first().getPlayerUsername() + "> Good Name: " +
                playerGiftPair.second().getList().getFirst().getName() + ", \nAmount : " + playerGiftPair.second().getList().size()
                , skin);
            TextField field = new TextField("", skin);
            TextButton button = new TextButton("Rate", skin);
            int finalGiftListPtr = giftListPtr;
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Result res = controller.giftRate(String.valueOf(finalGiftListPtr - 1), field.getText());
                    messageGiftLabel.setText(res.message());
                }
            });
            giftTable.add(label);
            giftTable.add(field);
            giftTable.add(button);
            giftTable.row();
        }

        for (Pair<Player, String> playerStringPair : App.getCurrentGame().getCurrentPlayer().getGiftHistory()) {
            if (player.getPlayerUsername().equals(playerStringPair.first().getPlayerUsername())) {
                Label label = new Label(giftListPtr++ + ". " + playerStringPair.second(), skin);
                giftTable.add(label).row();
            }
        }

        playerGiftScrollPane = new ScrollPane(giftTable, skin);
        playerGiftScrollPane.setSize(1000, 700);
        playerGiftTable.add(playerGiftScrollPane).colspan(4).row();
        playerGiftBackButton = new TextButton("Back", skin);
        playerGiftBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closePlayerGiftWindow();
            }
        });
        playerGiftTable.add(playerGiftBackButton).colspan(4).row();
        playerGiftWindow.add(playerGiftTable);

        staticStage.addActor(playerGiftWindow);

    }

    private void closePlayerGiftWindow() {
        playerGiftWindow.remove();
        playerGiftWindow = null;
        playerGiftTable.remove();
    }

    private void initFriend() {
        hugButton = new TextButton("Hug", style);
        hugButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result res = controller.hug(selectedPlayer.getPlayerUsername());
                buildMessage();
                textFieldMessage.setText(res.message());
                closeFriend();
            }
        });
        hugButton.getLabel().setColor(Color.BLACK);
        hugButton.setSize((float) scaledSize, (float) (0.5 * scaledSize));
        hugButton.getLabel().setFontScale(0.6f);

        flowerButton = new TextButton("Flower", style);
        flowerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result res = controller.flower(selectedPlayer.getPlayerUsername());
                buildMessage();
                textFieldMessage.setText(res.message());
                closeFriend();
            }
        });
        flowerButton.getLabel().setColor(Color.BLACK);
        flowerButton.setSize((float) scaledSize, (float) (0.5 * scaledSize));
        flowerButton.getLabel().setFontScale(0.6f);

        marriageButton = new TextButton("Marriage", style);
        marriageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result res = controller.askMarriage(selectedPlayer.getPlayerUsername());
                if (!res.success()) {
                    buildMessage();
                    textFieldMessage.setText(res.message());
                }
                else {
                    intiRespondWindow(App.getCurrentGame().getCurrentPlayer());
                }
                closeFriend();
            }
        });
        marriageButton.getLabel().setColor(Color.BLACK);
        marriageButton.setSize((float) scaledSize, (float) (0.5 * scaledSize));
        marriageButton.getLabel().setFontScale(0.6f);

        hugButton.setPosition(selectedPlayer.getCoordinate().getX() * scaledSize + scaledSize + 10,
            selectedPlayer.getCoordinate().getY() * scaledSize + 44);
        flowerButton.setPosition(selectedPlayer.getCoordinate().getX() * scaledSize + scaledSize + 10,
            selectedPlayer.getCoordinate().getY() * scaledSize + 22);
        marriageButton.setPosition(selectedPlayer.getCoordinate().getX() * scaledSize + scaledSize + 10,
            selectedPlayer.getCoordinate().getY() * scaledSize);

        stage.addActor(hugButton);
        stage.addActor(flowerButton);
        stage.addActor(marriageButton);
    }

    private void closeFriend() {
        hugButton.remove();
        flowerButton.remove();
        marriageButton.remove();
    }

    private void intiRespondWindow(Player askedPlayer) {
        App.getCurrentGame().setCurrentPlayer(selectedPlayer);
        Window window = new Window("Respond to " + askedPlayer.getPlayerUsername(), skin, "Letter");
        window.setSize(1200, 200);
        window.setPosition(
            (staticStage.getWidth() - window.getWidth()) / 2,
            (staticStage.getHeight() - window.getHeight()) / 2
        );

        Table respondTable = new Table(skin);
        respondTable.setFillParent(true);
        respondTable.pad(30).padRight(150);

        Label label = new Label("Your respond is: ", skin);
        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems("Accept", "Reject");
        selectBox.setSelectedIndex(0);

        TextButton button = new TextButton("Send", skin);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
                Result res = controller.respond("-" + selectBox.getSelected().toLowerCase(), askedPlayer.getPlayerUsername());
                buildMessage();
                textFieldMessage.setText(res.message());

            }
        });

        respondTable.add(label).padRight(10);
        respondTable.add(selectBox).padRight(10);
        respondTable.add(button).padRight(10);
        respondTable.row();

        window.add(respondTable);
        staticStage.addActor(window);
    }

    public Stage getStaticStage() {
        return staticStage;
    }

    private void initCraftingWindow(ArrayList<Good> goods) {
        craftingUseWindow = new Window(goods.getLast().getName() + " Crafting", skin);
        craftingUseWindow.setSize(1000, 500);
        craftingUseWindow.setPosition(
            (staticStage.getWidth() - craftingUseWindow.getWidth()) / 2,
            (staticStage.getHeight() - craftingUseWindow.getHeight()) / 2
        );

        craftingUseTable = new Table(skin);
        craftingUseTable.setFillParent(true);
        craftingUseTable.pad(30);

        craftingLabel = new Label("Select Crafting ingredients:", skin);
        craftingUseTable.add(craftingLabel).fillX().expandX().center().row();
        craftingSelectBox = new ArrayList<>();
        craftingSelectBox.add(new SelectBox<>(skin));
        craftingSelectBox.add(new SelectBox<>(skin));
        for (SelectBox<String> selectBox : craftingSelectBox) {
            Array<String> craftingArray = new Array<>();
            for (ArrayList<Good> goodArrayList : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
                if (!goodArrayList.isEmpty())
                    craftingArray.add(goodArrayList.getLast().getName());
            }

            selectBox.setItems(craftingArray);
            selectBox.setSelectedIndex(0);
            craftingUseTable.add(selectBox).fillX().expandX().center().row();
        }

        craftingUseButton = new TextButton("Use Crafting", skin);
        craftingUseTable.add(craftingUseButton).fillX().expandX().center().row();
        craftingMessageLabel = new Label("", skin);
        craftingUseButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               Result res = controller.artisanUse(goods.getLast().getName(), craftingSelectBox.get(0).getSelected(),
                   craftingSelectBox.get(1).getSelected());
                craftingMessageLabel.setText(res.message());
           }
        });

        craftingBackButton = new TextButton("Back", skin);
        craftingUseTable.add(craftingBackButton).fillX().expandX().center().row();
        craftingBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeCraftingWindow();
            }
        });

        craftingUseTable.add(craftingMessageLabel).fillX().expandX().center().row();

        craftingUseWindow.addActor(craftingUseTable);
        staticStage.addActor(craftingUseWindow);
    }

    private void closeCraftingWindow() {
        craftingUseWindow.remove();
    }

    private void initShippingBinWindow() {
        Window shippingBinWindow = new Window("Shipping Bin", skin);
        shippingBinWindow.setSize(1000, 600);
        shippingBinWindow.setPosition(
            (staticStage.getWidth() - shippingBinWindow.getWidth()) / 2,
            (staticStage.getHeight() - shippingBinWindow.getHeight()) / 2
        );

        Table shippingBinTable = new Table(skin);
        shippingBinTable.setFillParent(true);
        shippingBinTable.pad(30);

        Label shippingBinLabel = new Label("Choose your Good to sell: ", skin);
        SelectBox<String> goodsSelectBox = new SelectBox<>(skin);
        SelectBox<String> goodsCountSelectBox = new SelectBox<>(skin);
        Array<String> goodsArray = new Array<>();
        Array<String> goodsCountArray = new Array<>();
        App.getCurrentGame().getCurrentPlayer().getInventory().getList().forEach(good -> {
           if (!good.isEmpty())
               goodsArray.add(good.getLast().getName());
        });

        goodsSelectBox.setItems(goodsArray);
        goodsSelectBox.setSelectedIndex(0);

        for (int i = 1; i <= App.getCurrentGame().getCurrentPlayer().getInventory().getFirstElementSize(); i++) {
            goodsCountArray.add(String.valueOf(i));
        }
        goodsCountSelectBox.setItems(goodsCountArray);
        goodsCountSelectBox.setSelectedIndex(0);

        goodsSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                String selected = goodsSelectBox.getSelected();
                for (ArrayList<Good> good : App.getCurrentGame().getCurrentPlayer().getInventory().getList()) {
                    if (!good.isEmpty() && good.getLast().getName().equals(selected)) {
                        goodsCountArray.clear();
                        for (int i = 1; i <= good.size(); i++) {
                            goodsCountArray.add(String.valueOf(i));
                        }
                        goodsCountSelectBox.setItems(goodsCountArray);
                        goodsCountSelectBox.setSelectedIndex(0);
                        break;
                    }
                }
            }
        });

        TextButton sellButton = new TextButton("Sell", skin);
        Label sellMessageLabel = new Label("", skin);
        sellButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result res = controller.sell(goodsSelectBox.getSelected(), goodsCountSelectBox.getSelected());
                sellMessageLabel.setText(res.message());
            }
        });

        TextButton sellBackButton = new TextButton("Back", skin);
        sellBackButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               shippingBinWindow.remove();
               shippingBinTable.remove();
           }
        });

        shippingBinTable.add(shippingBinLabel).fillX().expandX().center().row();
        shippingBinTable.add(goodsSelectBox).fillX().expandX().center().row();
        shippingBinTable.add(goodsCountSelectBox).fillX().expandX().center().row();
        shippingBinTable.add(sellButton).fillX().expandX().center().row();
        shippingBinTable.add(sellBackButton).fillX().expandX().center().row();
        shippingBinTable.add(sellMessageLabel).fillX().expandX().center().row();

        shippingBinWindow.addActor(shippingBinTable);

        staticStage.addActor(shippingBinWindow);
    }

    public void initExitMenu(Window window) {
        window.add(new Label("Exit", skin)).left().padBottom(10);
        window.row();
        Label messageLabel = new Label("", skin);

        TextButton exitButton = new TextButton("Exit Game", skin);
        exitButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               Result res = controller.exitGame();
               messageLabel.setText(res.message());
           }
        });

        window.add(exitButton).fillX().expandX().center().row();

        TextButton textButton = new TextButton("Force Terminate", skin);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeMainTable();
                initTerminateWindow(0);
            }
        });
        window.add(textButton).fillX().expandX().center().row();
        window.add(messageLabel).fillX().expandX().center().row();


    }

    public void initTerminateWindow(int playerNumber) {
        App.getCurrentGame().setCurrentPlayer(App.getCurrentGame().getPlayers().get(playerNumber));
        Player player = App.getCurrentGame().getCurrentPlayer();
        Window terminateWindow = new Window("", skin, "Letter");
        terminateWindow.setSize(1000, 500);
        terminateWindow.setPosition(
            (staticStage.getWidth() - terminateWindow.getWidth()) / 2,
            (staticStage.getHeight() - terminateWindow.getHeight()) / 2
        );

        Label terminateMessageLabel = new Label(player.getPlayerUsername() + ", Are you agree to terminate the game?", skin);
        TextButton yesButton = new TextButton("Yes", skin);
        TextButton noButton = new TextButton("No", skin);
        TextButton backButton = new TextButton("Back", skin);

        terminateWindow.add(terminateMessageLabel).colspan(3).fillX().expandX().center().row();
        terminateWindow.add(yesButton).fillX().padTop(10);
        terminateWindow.add(noButton).fillX().padTop(10);
        terminateWindow.add(backButton).fillX().padTop(10).row();

        yesButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               terminateWindow.remove();
               if (playerNumber == 3)
                   controller.forceTerminate();
               else
                   initTerminateWindow(playerNumber + 1);
           }
        });

        noButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               terminateWindow.remove();
           }
        });

        backButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               terminateWindow.remove();
               if (playerNumber > 0)
                   initTerminateWindow(playerNumber - 1);
           }
        });

        staticStage.addActor(terminateWindow);
    }

    public void initJournalWindow() {
        journalWindow = new Window("Journal", skin, "Letter");
        journalWindow.setSize(1000, 500);
        journalWindow.setPosition(
            (staticStage.getWidth() - journalWindow.getWidth()) / 2,
            (staticStage.getHeight() - journalWindow.getHeight()) / 2
        );

        journalTable = new Table(skin);
        journalWindow.add(new Label("Your news:", skin, "Bold")).fillX().expandX().center().row();
        int ctr = 1;
        for (int i = App.getCurrentGame().getCurrentPlayer().getNews().size() - 1; i >= 0; i--) {
            String s = App.getCurrentGame().getCurrentPlayer().getNews().get(i);
            journalTable.add(new Label(ctr++ + ". \n" + s, skin)).fillX().expandX().center().padTop(10).row();
        }

        ScrollPane scrollPane = new ScrollPane(journalTable);
        journalWindow.add(scrollPane).padTop(10).row();

        journalBackButton = new TextButton("Back", skin);
        journalBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeJournalWindow();
            }
        });
        journalWindow.add(journalBackButton).fillX().expandX().center().row();

        staticStage.addActor(journalWindow);
    }

    public void closeJournalWindow() {
        journalWindow.remove();
        journalWindow = null;
        journalTable.remove();
    }
    public void chatWindow() {
        chatRoomWindow = new Window("Chat Room", skin);
        chatRoomWindow.setSize(1200, 700);
        chatRoomWindow.setPosition(
            (staticStage.getWidth() - chatRoomWindow.getWidth()) / 2,
            (staticStage.getHeight() - chatRoomWindow.getHeight()) / 2
        );

        final Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        final Player[] selectedPrivateReceiver = {null};
        final boolean[] isPublicMode = {true};

        TextButton publicChatButton = new TextButton("Public", skin);
        TextButton privateChatButton = new TextButton("Private", skin);
        publicChatButton.setColor(Color.GREEN);
        privateChatButton.setColor(Color.GRAY);

        Table modeButtons = new Table();
        modeButtons.add(publicChatButton).width(100).padRight(10);
        modeButtons.add(privateChatButton).width(100);

        Table messageTable = new Table();
        messageTable.align(Align.top | Align.left);

        ScrollPane messageScroll = new ScrollPane(messageTable, skin);
        messageScroll.setFadeScrollBars(false);
        messageScroll.setScrollingDisabled(true, false);
        messageScroll.setForceScroll(false, true);

        Table userListTable = new Table();
        ScrollPane userListScroll = new ScrollPane(userListTable, skin);
        userListScroll.setVisible(false);
        userListScroll.setScrollingDisabled(true, false);

        Table chatArea = new Table();
        chatArea.add(messageScroll).expand().fill();
        chatArea.add(userListScroll).width(200).padLeft(5);
        chatArea.row();

        TextField messageField = new TextField("", skin);
        TextButton sendButton = new TextButton("Send", skin);

        Table inputArea = new Table();
        inputArea.add(messageField).expandX().fillX().pad(5);
        inputArea.add(sendButton).pad(5);

        Runnable updatePublicMessages = () -> {
            messageTable.clear();
            for (Pair<Player, String> msg : App.getCurrentGame().getPublicChat()) {
                Label label = new Label(msg.first().getPlayerUsername() + ": " + msg.second(), skin);
                label.setAlignment(Align.left);
                messageTable.add(label).left().expandX().fillX().pad(2).row();
            }
            messageScroll.layout();
            messageScroll.scrollTo(0, 0, 0, 0);
        };

        Runnable updatePrivateMessages = () -> {
            messageTable.clear();
            if (selectedPrivateReceiver[0] == null) return;

            for (Pair<Player, ArrayList<String>> entry : currentPlayer.getPrivateChat()) {
                if (entry.first().equals(selectedPrivateReceiver[0])) {
                    for (String msg : entry.second()) {
                        Label label = new Label(msg, skin);
                        label.setAlignment(Align.left);
                        messageTable.add(label).left().expandX().fillX().pad(2).row();
                    }
                    break;
                }
            }
            messageScroll.layout();
            messageScroll.scrollTo(0, 0, 0, 0);
        };

        Runnable updateMessages = () -> {
            messageTable.clear();
            userListTable.clear();

            if (isPublicMode[0]) {
                userListScroll.setVisible(false);
                updatePublicMessages.run();
                selectedPrivateReceiver[0] = null;
            } else {
                userListScroll.setVisible(true);

                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.equals(currentPlayer)) continue;

                    TextButton btn = new TextButton(player.getPlayerUsername(), style);
                    btn.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            selectedPrivateReceiver[0] = player;
                            updatePrivateMessages.run();
                        }
                    });
                    btn.getLabel().setFontScale(1.2f);
                    btn.setWidth(200);
                    btn.setHeight(60);
                    userListTable.add(btn).fillX().pad(5).row();
                }

                if (selectedPrivateReceiver[0] != null) {
                    updatePrivateMessages.run();
                } else {
                    messageTable.clear();
                }
            }
        };

        publicChatButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPublicMode[0] = true;
                publicChatButton.setColor(Color.GREEN);
                privateChatButton.setColor(Color.GRAY);
                selectedPrivateReceiver[0] = null;
                updateMessages.run();
            }
        });

        privateChatButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPublicMode[0] = false;
                publicChatButton.setColor(Color.GRAY);
                privateChatButton.setColor(Color.GREEN);
                updateMessages.run();
            }
        });

        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String text = messageField.getText().trim();
                if (text.isEmpty()) return;

                if (isPublicMode[0]) {
                    App.getCurrentGame().getPublicChat().add(new Pair<>(currentPlayer, text));
                    updatePublicMessages.run();
                } else if (selectedPrivateReceiver[0] != null) {
                    String formattedMessage = currentPlayer.getPlayerUsername() + ": " + text;

                    boolean found = false;
                    for (Pair<Player, ArrayList<String>> entry : currentPlayer.getPrivateChat()) {
                        if (entry.first().equals(selectedPrivateReceiver[0])) {
                            entry.second().add(formattedMessage);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        ArrayList<String> msgs = new ArrayList<>();
                        msgs.add(formattedMessage);
                        currentPlayer.getPrivateChat().add(new Pair<>(selectedPrivateReceiver[0], msgs));
                    }

                    found = false;
                    for (Pair<Player, ArrayList<String>> entry : selectedPrivateReceiver[0].getPrivateChat()) {
                        if (entry.first().equals(currentPlayer)) {
                            entry.second().add(formattedMessage);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        ArrayList<String> msgs = new ArrayList<>();
                        msgs.add(formattedMessage);
                        selectedPrivateReceiver[0].getPrivateChat().add(new Pair<>(currentPlayer, msgs));
                    }

                    updatePrivateMessages.run();
                }

                messageField.setText("");
            }
        });

        chatRoomWindow.clearChildren();
        chatRoomWindow.add(modeButtons).pad(5).left().row();
        chatRoomWindow.add(chatArea).expand().fill().pad(5).row();
        chatRoomWindow.add(inputArea).fillX().pad(5).bottom();

        staticStage.addActor(chatRoomWindow);
        updateMessages.run();
    }



    public Window getJournalWindow() {
        return journalWindow;
    }

    public boolean isTabClicked() {
        return isTabClicked;
    }

    public void setTabClicked(boolean tabClicked) {
        isTabClicked = tabClicked;
    }

    public Window getChatRoomWindow() {
        return chatRoomWindow;
    }

    public void setChatRoomWindow(Window chatRoomWindow) {
        this.chatRoomWindow = chatRoomWindow;
    }


    public Window getEmojiWindow() {
        return emojiWindow;
    }

    private Window emojiWindow;
    private Table emojiTable = new Table();
    public void initPopupReactionWindow() {
        emojiWindow = new Window("Reactions", skin);
        emojiWindow.setName("emojiWindow"); // Important for later reference
        emojiWindow.setSize(520, 300); // Smaller height since we show fewer emotes
        emojiWindow.setPosition(
            (staticStage.getWidth() - emojiWindow.getWidth()) / 2,
            (staticStage.getHeight() - emojiWindow.getHeight()) / 2 + 300
        );

        Table emojiTable = new Table();
        emojiTable.defaults().pad(10).size(80, 80);

        // Add the 7 selected emotes
        for (int i = 0; i <  EmoteManager.selectedEmotes.size; i++) {
            String emoteName = EmoteManager.selectedEmotes.get(i);
            emojiTable.add(createEmoteButton(getTextureForEmote(emoteName), emoteName));
            if ((i + 1) % 4 == 0 && i != 6) emojiTable.row(); // 4 per row
        }

        // Add edit button in remaining space (8th slot)
        TextButton editBtn = new TextButton("Edit", skin);
        editBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showEmoteSelectionWindow();
                emojiWindow.remove();
            }
        });
        emojiTable.add(editBtn).size(80, 80);

        emojiWindow.add(emojiTable).pad(10);
        staticStage.addActor(emojiWindow);
    }
    public void closePopupReactionWindow(){
        emojiWindow.remove();
        emojiWindow = null;
        emojiTable.remove();
    }
    private Texture getTextureForEmote(String emoteName) {
        switch(emoteName) {
            case "Like": return Assets.getInstance().getLike();
            case "Dislike": return Assets.getInstance().getDislike();
            case "Heart": return Assets.getInstance().getHeart();
            case "Music": return Assets.getInstance().getSmile();
            case "UwU": return Assets.getInstance().getUwu();
            case "Question": return Assets.getInstance().getQuestion();
            case "Angry": return Assets.getInstance().getAngry();
            case "Speechless": return Assets.getInstance().getSpeechless();
            case "Surprised": return Assets.getInstance().getSurprised();
            case "Sleepy": return Assets.getInstance().getSleepy();
            case "Not Sure": return Assets.getInstance().getNotSure();
            case "Edit": return Assets.getInstance().getEdit();
            default: return Assets.getInstance().getLike();
        }
    }

    private void showEmoteSelectionWindow() {
        final Window selectionWindow = new Window("Select Emotes", skin);
        selectionWindow.setModal(true);
        selectionWindow.setSize(600, 800);
        selectionWindow.setPosition(
            (staticStage.getWidth() - selectionWindow.getWidth()) / 2,
            (staticStage.getHeight() - selectionWindow.getHeight()) / 2
        );

        Table mainTable = new Table();
        ScrollPane scrollPane = new ScrollPane(mainTable, skin);
        scrollPane.setFadeScrollBars(false);

        // Create checkboxes for all emotes
        for (String emote : EmoteManager.ALL_EMOTES) {
            CheckBox checkBox = new CheckBox(emote, skin);
            checkBox.setChecked(EmoteManager.selectedEmotes.contains(emote, false));

            checkBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (((CheckBox)actor).isChecked()) {
                        if (!EmoteManager.selectedEmotes.contains(emote, false)) {
                            EmoteManager.selectedEmotes.add(emote);
                        }
                    } else {
                        EmoteManager.selectedEmotes.removeValue(emote, false);
                    }
                }
            });

            mainTable.add(checkBox).left().pad(5).width(150);
            mainTable.add(createEmoteButton(getTextureForEmote(emote), emote)).pad(5).size(60, 60);
            mainTable.row();
        }

        // Add save button
        TextButton saveBtn = new TextButton("Save", skin);
        saveBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                EmoteManager.savePreferences();
                selectionWindow.remove();
                initPopupReactionWindow(); // Reopen main window with new selection
            }
        });

        selectionWindow.add(scrollPane).expand().fill().pad(10);
        selectionWindow.row();
        selectionWindow.add(saveBtn).padBottom(10).padTop(10);

        staticStage.addActor(selectionWindow);
    }

    private ImageButton createEmoteButton(Texture texture, final String emoteName) {
        // Create drawable from texture
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));

        // Create button style
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = drawable;
        style.down = drawable;
        style.over = drawable;
        // Create button
        ImageButton button = new ImageButton(style);
        // Add click listener
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showEmote(emoteName);
                button.getStage().getRoot().findActor("emojiWindow").remove(); // Close window
            }
        });

        return button;
    }

    public class EmoteManager {
        public static final String[] ALL_EMOTES = {
            "Like", "Dislike", "Heart", "Music", "UwU",
            "Question", "Angry", "Speechless", "Surprised",
            "Sleepy", "Not Sure"
        };

        public static Array<String> selectedEmotes = new Array<>(new String[]{
            "Like", "Dislike", "Heart", "Music", "UwU", "Question", "Angry"
        });

        public static void savePreferences() {

        }
    }

    private Animation<Texture> emoteAnimation;
    private float emoteStateTime = 0f;
    private boolean showEmote = false;

    private void showEmote(String emoteName) {
        Texture popup_1Frame = new Texture("assets/GameAssets/Popup/popup1.png");
        Texture popup_2Frame = new Texture("assets/GameAssets/Popup/popup2.png");
        Texture popup_3Frame = new Texture("assets/GameAssets/Popup/popup3.png");
        Texture popup_4Frame = new Texture("assets/GameAssets/Popup/popup4.png");
        Texture _1Frame = new Texture("assets/GameAssets/Popup/" + emoteName + "1.png");
        Texture _2Frame = new Texture("assets/GameAssets/Popup/" + emoteName + "2.png");
        Texture _3Frame = new Texture("assets/GameAssets/Popup/" + emoteName + "3.png");
        Texture _4Frame = new Texture("assets/GameAssets/Popup/" + emoteName + "4.png");

        emoteAnimation = new Animation<>(0.1f,
            popup_1Frame, popup_1Frame, popup_2Frame, popup_2Frame,
            popup_3Frame, popup_3Frame, popup_4Frame, popup_4Frame,
            _1Frame, _1Frame, _2Frame, _2Frame, _3Frame, _3Frame, _4Frame, _4Frame,
            _3Frame, _3Frame, _2Frame, _2Frame, _1Frame, _1Frame, _2Frame, _2Frame,
            _3Frame, _3Frame, _4Frame, _4Frame, _3Frame, _3Frame, _2Frame, _2Frame,
            _1Frame, _1Frame, _2Frame, _2Frame, _3Frame, _3Frame, _4Frame, _4Frame,
            _3Frame, _3Frame, _2Frame, _2Frame, _1Frame, _1Frame,
            popup_4Frame, popup_4Frame, popup_3Frame, popup_3Frame,
            popup_2Frame, popup_2Frame, popup_1Frame, popup_1Frame
        );

        emoteAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        emoteStateTime = 0f;
        showEmote = true;
    }


}





