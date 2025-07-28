package com.StardewValley.views;

import com.StardewValley.Main;
import com.StardewValley.controllers.GameMenuController;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Gift;
import com.StardewValley.models.game_structure.Map;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
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
import com.badlogic.gdx.graphics.*;
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

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;


public class GameView implements Screen, InputProcessor {
    private Skin skin;
    private GameMenuController controller;
    private Stage stage;
    private Table table;
    private final OrthographicCamera camera;
    private final Viewport viewport;
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
    private Table mainTable;
    private Window mainWindow;

    private Window cheatWindow;
    private Table cheatTable;
    private TextField cheatTextField;
    private TextButton cheatButton;

    private TextButton friendsButton;
    private Window friendsWindow;
    private Table friendsTable;
    private TextButton friendsBackButton;

    private Window playerGiftWindow;
    private Table playerGiftTable;
    private Label playerGiftLabel;
    private SelectBox<String> playerGiftSelectBox;
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

        this.table.add(friendsButton).padTop(400).padLeft(-400).height(70).row();
        this.table.add(inventoryTable).padTop(1100).padLeft(-50);
        this.table.add(controller.getInventoryController().getProgressBar()).padTop(200).padLeft(800);
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
            } else if (building instanceof CarpenterShop) {
                carpenterShop(purchaseButton, (CarpenterShop) building, selectedNameLabel, itemsTable, counterPanel,
                    info, selectedPanel, scrollPane, content);
            }
            else if (building instanceof FishShop) {
                fishShop(addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, (FishShop) building,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);
            }
            else if (building instanceof PierreGeneralStore) {
                pierreShop(addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, (PierreGeneralStore) building,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);
            }
            else if (building instanceof JojaMart) {
                jojaShop(addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, (JojaMart) building,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);;
            }
            else if (building instanceof TheStarDropSaloon) {
                stardropShop(addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, (TheStarDropSaloon) building,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);
            }
            else if (building instanceof Blacksmith) {
                blacksmithShop(addButton, countLabel, removeButton, purchaseButton, info, tileX, tileY, (Blacksmith) building,
                    selectedNameLabel, itemsTable, counterPanel, selectedPanel, scrollPane, content);
            }
            staticStage.addActor(window);

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

    private void blacksmithShop(TextButton addButton, Label countLabel, TextButton removeButton, TextButton purchaseButton,
                              Label info, int tileX, int tileY, Blacksmith blacksmith, Label selectedNameLabel, Table itemsTable,
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
                goodsListInit(addButton, countLabel, removeButton, purchaseButton, blacksmith, selectedNameLabel,
                    itemsTable, selectedGoodType, selectedCount, filterAvailable, filterButton, info);
                toolsUpgradeInit(addButton, countLabel, removeButton, purchaseButton, blacksmith, selectedNameLabel,
                    itemsTable, selectedGoodType, selectedCount, filterAvailable, filterButton, info);

            }
        });

        itemsTable.add(filterButton)
            .fillX()
            .pad(5)
            .row();

        goodsListInit(addButton, countLabel, removeButton, purchaseButton, blacksmith, selectedNameLabel, itemsTable,
            selectedGoodType, selectedCount, filterAvailable, filterButton, info);
        toolsUpgradeInit(addButton, countLabel, removeButton, purchaseButton, blacksmith, selectedNameLabel,
            itemsTable, selectedGoodType, selectedCount, filterAvailable, filterButton, info);

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

        craftingTable = new Table(skin);
        craftingTable.top().left();
        craftingTable.setFillParent(false);

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

            craftingTable.add(itemTable).pad(5);

            count++;
            if (count % columns == 0) {
                craftingTable.row();
            }
        }

        ScrollPane craftingScrollPane = new ScrollPane(craftingTable, skin);
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

    public void initMainTable() {
        mainTable = new Table(skin);
        mainTable.setFillParent(true);
        mainWindow = new Window("", skin);
        mainWindow.setSize(800, 600);

        for (int i = 0; i < 8; i++) {
            ImageButton imageButton = controller.getInventoryController().getMainInventoryElements().get(i);
            if (i == 0) {
                mainTable.add(imageButton);
                imageButton.setChecked(true);
            } else if (i == 7)
                mainTable.add(imageButton).padLeft(100);
            else
                mainTable.add(imageButton);
        }
        mainTable.row();
        mainTable.add(mainWindow).colspan(7);

        staticStage.addActor(mainTable);
        setInputProcessor();
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
        mainWindow.remove();
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

    private void toolsUpgradeInit(TextButton addButton, Label countLabel, TextButton removeButton, TextButton purchaseButton,
                             Blacksmith blacksmith, Label selectedNameLabel, Table itemsTable, GoodType[] selectedGoodType,
                             int[] selectedCount, boolean[] filterAvailable, TextButton filterButton, Label info) {
        for (int i = 0; i < 4; i++) {
            TextButton upgradeButton = new TextButton("Upgrade to " + ToolLevel.toolLevels.get(i + 1).getName(), skin);
//            upgradeButton.addListener(new ChangeListener() {
//                @Override
//                public void changed(ChangeEvent event, Actor actor) {
//                    if (gameBuilding.findProduct(product).second() == 0) {
//                        info.setText("This product's daily quantity is 0");
//                        info.setVisible(true);
//                        addButton.setVisible(false);
//                        removeButton.setVisible(false);
//                        countLabel.setVisible(false);
//                        purchaseButton.setVisible(false);
//                    } else {
//                        selectedGoodType[0] = null;
//                        addButton.setVisible(true);
//                        removeButton.setVisible(true);
//                        countLabel.setVisible(true);
//                        selectedGoodType[0] = product;
//                        selectedCount[0] = 0;
//                        selectedNameLabel.setText(product.getName());
//                        countLabel.setText(String.valueOf(selectedCount[0]));
//                        purchaseButton.setVisible(true);
//                        info.setVisible(false);
//                    }
//                }
//            });
        }
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
}






