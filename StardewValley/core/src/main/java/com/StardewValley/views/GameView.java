package com.StardewValley.views;

import com.StardewValley.Main;
import com.StardewValley.controllers.ClockController;
import com.StardewValley.controllers.GameMenuController;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Map;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.farmings.FarmingTree;
import com.StardewValley.models.goods.foragings.ForagingTree;
import com.StardewValley.models.goods.tools.Tool;
import com.StardewValley.models.interactions.Animals.Animal;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.interactions.Animals.AnimalTypes;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuilding;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuildingTypes;
import com.StardewValley.models.interactions.game_buildings.CarpenterShop;
import com.StardewValley.models.interactions.game_buildings.GameBuilding;
import com.StardewValley.models.interactions.game_buildings.MarnieRanch;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.Math.abs;


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
    private Coordinate lastCoordinate;
    private TextField textFieldMessage;
    private Image npcImage;
    float timeAccumulator = 0;
    private Animal selectedAnimal = null;


    private ClockController clockController = new ClockController();

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

        this.table.add(inventoryTable).padTop(1500).padLeft(-50);
        this.table.add(controller.getInventoryController().getProgressBar()).padTop(600).padLeft(800);
        this.table.row();
        FarmBuilding farmBuilding = new FarmBuilding(FarmBuildingTypes.BARN, new Coordinate(50, 30));
        App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings().add(farmBuilding);
        Animal animal = new Animal(AnimalTypes.COW, "meow");
        animal.setCoordinate(new Coordinate(54, 34));
        farmBuilding.addAnimal(animal);

    }

    @Override
    public void show() {
        stage = new Stage(viewport);
        staticStage = new Stage(new ScreenViewport());

        multiplexer = new InputMultiplexer();
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(staticStage);
        multiplexer.addProcessor(stage);
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


        clockController.update();

        Assets.getInstance().setColorFunction();

        controller.handleGame();
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
        stage.draw();
        staticStage.act(min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        staticStage.draw();


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
        if (i == Input.Keys.O) {
            for (FarmBuilding building : App.getCurrentGame().getCurrentPlayer().getFarm().getFarmBuildings()) {
                for (Animal animal : building.getAnimals()) {
                    if ((Math.abs(animal.getCoordinate().getX() -
                        App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()) <= 2) &&
                        (Math.abs(animal.getCoordinate().getY() -
                            App.getCurrentGame().getCurrentPlayer().getCoordinate().getY()) <= 2)) {
                        animal.petAnimal();
                        buildMessage();
                        textFieldMessage.setText("You petted " + animal.getName());
                        return true;
                    }
                }
            }
            buildMessage();
            textFieldMessage.setText("Please approach an animal to pet");
            return true;
        }
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
            }
            if (animal != null) {
//                Texture backgroundTexture = new Texture(Gdx.files.internal("petWindow.png"));
//                Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(backgroundTexture));
//
//                Window.WindowStyle style = new Window.WindowStyle(new BitmapFont(), Color.WHITE, backgroundDrawable);
                Window animalWindow = new Window("", skin);

                Table nameTable = new Table();
                Label nameLabel = new Label(animal.getName(), skin);
                nameTable.top().left();
                nameTable.add(nameLabel).pad(4);
                animalWindow.add(nameTable).expandX().fillX().padTop(2).row();

                Label friendship = new Label("friendship: " + animal.getFriendship(), skin);
                friendship.setFontScale(0.5f);
                nameLabel.setFontScale(0,5f);
                animalWindow.add(friendship).pad(3).padTop(1).row();

                animalWindow.pack();
                animalWindow.setSize(scaledSize * 5, scaledSize * 5);

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

        //

        GameBuilding building = App.getCurrentGame().getMap().findGameBuilding(new Coordinate(tileX, tileY));

        if (building != null) {

            Texture backgroundTexture = new Texture(Gdx.files.internal("shop-menu.png"));
            Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(backgroundTexture));

            final Window window = new Window("SHOP", skin);
            window.setBackground(backgroundDrawable);
            window.setSize(940, 600);
            window.setPosition(
                (stage.getWidth() - window.getWidth()) / 2,
                (stage.getHeight() - window.getHeight()) / 2
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
                                String.valueOf(selectedCount[0]));
                            System.out.println(result.message());
                            info.setText(result.toString());
                        }

                    }
                });


                for (AnimalTypes animalType : ((MarnieRanch) building).animals) {
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

                for (ProductType productType : ((MarnieRanch) building).products) {
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
            } else if (building instanceof CarpenterShop) {
                final FarmBuildingTypes[] selectedBuilding = {null};

                purchaseButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent changeEvent, Actor actor) {

                        //TODO
                        //window

                    }
                });

                for (FarmBuildingTypes farmBuildingType : ((CarpenterShop) building).getProducts()) {
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


            stage.addActor(window);

            multiplexer.addProcessor(stage);
            multiplexer.addProcessor(this);
            Gdx.input.setInputProcessor(multiplexer);

            return true;
        }
        return false;
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
                    default -> {
                        if (tile.isWatered()) {
                            Main.getBatch().draw(TileAssets.FARM_WET.getTexture(), x * scaledSize, y * scaledSize, scaledSize, scaledSize);
                        }
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
                        if (backTile.getTileType() != TileType.GREEN_HOUSE && upTile.getTileType() != TileType.GREEN_HOUSE)
                            Main.getBatch().draw(TileAssets.GREEN_HOUSE.getTexture(), (x - 1) * scaledSize, (y) * scaledSize, 8 * scaledSize, 7 * scaledSize);
                        Main.getBatch().draw(TileAssets.FARM_ORDINARY.getTexture(), x * scaledSize, (y + 1) * scaledSize, scaledSize, scaledSize);
                    }
                    case TileType.FARM, TileType.PLOWED_FARM, TileType.PLAIN -> {
                        drawForaging(tile);
                    }
                    default -> {
                        if (tile.isWatered()) {
                            drawForaging(tile);
                        }
                    }
                }
            }
        }

        drawInventory();
        drawNPCs();
        isPlayerMoved();
        drawFarmingBuilding();
        drawAnimals();
        drawPlayers();


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

                Pixmap normal = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
                normal.setColor(Color.YELLOW);
                normal.fill();
                Pixmap hover = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
                hover.setColor(Color.SKY);
                hover.fill();
                TextureRegionDrawable normalDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(normal)));
                TextureRegionDrawable hoverDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(hover)));
                BitmapFont font = new BitmapFont();
                TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
                style.up = normalDrawable;
                style.over = hoverDrawable;
                style.down = normalDrawable.tint(Color.GRAY);
                style.font = font;

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

    private void buildMessage() {
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

    private void drawPlayers() {
        for (Player player : App.getCurrentGame().getPlayers()) {
            player.getSprite().setPosition(player.getCoordinate().getX() * scaledSize,
                player.getCoordinate().getY() * scaledSize);
            player.getSprite().draw(Main.getBatch());
        }
    }

    private void drawForaging(Tile tile) {
        for (Good good : tile.getGoods()) {
            if (good instanceof ForagingTree || good instanceof FarmingTree) {
                //TODO
            }
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
        for (Pair<ImageButton, Image> inventoryElement : controller.getInventoryController().getInventoryElements()) {
            Table table = new Table();
            table.add(inventoryElement.first());
            table.add(inventoryElement.second()).padLeft(-48);
            inventoryTable.add(table);
        }

        controller.getInventoryController().getProgressBar().setValue(
            App.getCurrentGame().getCurrentPlayer().getEnergy().getDayEnergyLeft()
        );
    }

    public int getScaledSize() {
        return scaledSize;
    }

    public void initToolsWindow() {
        this.toolsWindow = new Window("Tools", skin, "Letter");
        this.toolsTable = new Table(skin);
        this.toolsTable.setFillParent(true);
        this.toolsScrollPane = new ScrollPane(toolsTable, skin);

        toolsWindow.add(toolsScrollPane);
        toolsWindow.pack();
        toolsWindow.setPosition(
            (Gdx.graphics.getWidth() - toolsWindow.getWidth()) / 2,
            (Gdx.graphics.getHeight() - toolsWindow.getHeight()) / 2
        );
//        stage.addActor(toolsWindow);


        for (int i = 0; i < controller.getInventoryController().getInventoryElements().size(); i++) {
            if (!App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).isEmpty() &&
                App.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i).getLast() instanceof Tool) {
                Pair<ImageButton, Image> inventoryElement = controller.getInventoryController().getInventoryElements().get(i);
                toolsTable.add(inventoryElement.first());
                toolsTable.add(inventoryElement.second()).padLeft(-48);
            }
        }

        toolsWindow.draw(Main.getBatch(), 10);

    }

    public Window getToolsWindow() {
        return toolsWindow;
    }

    public Stage getStage() {
        return stage;
    }
}
