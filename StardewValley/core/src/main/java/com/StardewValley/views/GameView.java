package com.StardewValley.views;

import com.StardewValley.Main;
import com.StardewValley.controllers.ClockController;
import com.StardewValley.controllers.Controller;
import com.StardewValley.controllers.GameMenuController;
import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
import com.StardewValley.models.Pair;
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
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.interactions.Animals.AnimalTypes;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuilding;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuildingTypes;
import com.StardewValley.models.interactions.game_buildings.CarpenterShop;
import com.StardewValley.models.interactions.game_buildings.GameBuilding;
import com.StardewValley.models.interactions.game_buildings.MarnieRanch;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.ArrayList;

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

    private Window toolsWindow;
    private ScrollPane toolsScrollPane;
    private Table toolsTable;

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
    }

    @Override
    public void show() {
        stage = new Stage();

        multiplexer = new InputMultiplexer();
        setInputProcessor();
        viewport.apply();

        stage.addActor(table);

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateCamera();
        Main.getBatch().setProjectionMatrix(camera.combined);

        Main.getBatch().begin();
        renderWorld();

        Assets.getInstance().setColorFunction();
        controller.handleGame();
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

//
//    //for carpenter shop
//    Vector2 mousePos = new Vector2(0, 0);
//    ShapeRenderer shapeRenderer = new ShapeRenderer();
//
//    InputProcessor mouseProcessor = new InputAdapter() {
//        @Override
//        public boolean mouseMoved(int screenX, int screenY) {
//            Vector2 stageCoords = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
//            mousePos.set(stageCoords.x, stageCoords.y);
//            return true;
//        }
//    };
//    ///


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 touchPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPos);

        int tileX = (int) (touchPos.x / scaledSize);
        int tileY = (int) (touchPos.y / scaledSize);

        GameBuilding building = App.getCurrentGame().getMap().findGameBuilding(new Coordinate(tileX, tileY));

        return gameBuildingsShop(building);
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
                        Main.getBatch().draw(TileAssets.ROAD.getTexture(), x * scaledSize, y * scaledSize,
                                scaledSize, scaledSize);
                    }
                    case TileType.STONE_WALL -> {
                        Main.getBatch().draw(TileAssets.STONE_WALL.getTexture(), x * scaledSize, y * scaledSize,
                                scaledSize, scaledSize);
                    }
                    case TileType.SQUARE -> {
                        Main.getBatch().draw(TileAssets.SQUARE.getTexture(), x * scaledSize, y * scaledSize,
                                scaledSize, scaledSize);
                    }
                    case TileType.BEACH -> {
                        Main.getBatch().draw(TileAssets.BEACH.getTexture(), x * scaledSize, y * scaledSize,
                                scaledSize, scaledSize);
                    }
                    case TileType.SHIPPING_BIN -> {
                        Main.getBatch().draw(TileAssets.SHIPPING_BIN.getTexture(), x * scaledSize, y * scaledSize,
                                scaledSize, scaledSize);
                    }
                    default -> {
                        if (tile.isWatered()) {
                            Main.getBatch().draw(TileAssets.FARM_WET.getTexture(), x * scaledSize, y * scaledSize,
                                    scaledSize, scaledSize);
                        }
                    }
                }
            }
        }

        for (int x = max((midX - Gdx.graphics.getWidth() / 2) / scaledSize - 5, 0);
             x < min((midX + Gdx.graphics.getWidth() / 2) / scaledSize + 1, 150); x++) {
            for (int y = max((midY - Gdx.graphics.getHeight() / 2) / scaledSize - 5, 0);
                 y < min((midY + Gdx.graphics.getHeight() / 2) / scaledSize + 1, 160); y++) {
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
                            Main.getBatch().draw(TileAssets.GREEN_HOUSE.getTexture(), (x - 1) * scaledSize,
                                    (y) * scaledSize, 8 * scaledSize, 7 * scaledSize);
                        Main.getBatch().draw(TileAssets.FARM_ORDINARY.getTexture(), x * scaledSize,
                                (y + 1) * scaledSize, scaledSize, scaledSize);
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

        drawNPCs();
        drawFarmingBuilding();
    }

    private void drawNPCs() {
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            Sprite sprite = new Sprite(new Texture(npc.getType().getImagePath()));
            sprite.setPosition(npc.getType().getCoordinate().getX() * scaledSize,
                npc.getType().getCoordinate().getY() * scaledSize);
            sprite.draw(Main.getBatch());
        }
    }

    private void drawForaging(Tile tile) {
        for (Good good : tile.getGoods()) {
            if (good instanceof ForagingTree || good instanceof FarmingTree) {
                //TODO
            }
        }
    }

    private void drawFarmingBuilding() {
        for (Player player : App.getCurrentGame().getPlayers()) {
            for (FarmBuilding farmBuilding : player.getFarm().getFarmBuildings()) {
                if (farmBuilding.getType() != FarmBuildingTypes.HOME) {
                    Main.getBatch().draw(farmBuilding.getType().getTexture(), (float) (farmBuilding.getStartCordinate().getX()
                                    + farmBuilding.getEndCordinate().getX()) / 2 * scaledSize,
                            (float) (farmBuilding.getStartCordinate().getY() + farmBuilding.getEndCordinate().getY()) / 2 * scaledSize,
                            farmBuilding.getType().getSize().second() * scaledSize,
                            farmBuilding.getType().getSize().first() * scaledSize);
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
                (Gdx.graphics.getWidth()  - toolsWindow.getWidth())  / 2,
                (Gdx.graphics.getHeight() - toolsWindow.getHeight()) / 2
        );

        stage.addActor(toolsWindow);
        setInputProcessor();
    }

    private void setInputProcessor() {
        multiplexer.clear();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void closeToolsWindow() {
        toolsWindow.remove();
        toolsTable.remove();
        toolsWindow = null;
        setInputProcessor();
    }

    public Window getToolsWindow() {
        return toolsWindow;
    }

    private boolean gameBuildingsShop(GameBuilding building) {
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

            setInputProcessor();

            return true;
        }
        return false;
    }

    public Stage getStage() {
        return stage;
    }
}
