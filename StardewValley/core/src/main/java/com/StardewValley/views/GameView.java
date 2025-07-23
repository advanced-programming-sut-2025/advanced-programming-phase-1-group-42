package com.StardewValley.views;

import com.StardewValley.Main;
import com.StardewValley.controllers.GameMenuController;
import com.StardewValley.models.App;
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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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
    private Coordinate coordinate;
    private int scaledSize;
    InputMultiplexer multiplexer = new InputMultiplexer();
    private final Pair<Boolean,FarmBuildingTypes> isCarpenterShopOn = new Pair<>(false,null);


    public GameView(GameMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        stage = new Stage();
        table = new Table(skin);
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        coordinate = new Coordinate(0, 0);
        scaledSize = 40;
    }

    @Override
    public void show() {

        viewport.apply();
        Gdx.input.setInputProcessor(this);
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);


    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateCamera();
        Main.getBatch().setProjectionMatrix(camera.combined);

        Main.getBatch().begin();


        renderWorld();

        Main.getBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            coordinate = new Coordinate(coordinate.getX(), coordinate.getY() + 1);
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            coordinate = new Coordinate(coordinate.getX() - 1, coordinate.getY());
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            coordinate = new Coordinate(coordinate.getX(), coordinate.getY() - 1);
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            coordinate = new Coordinate(coordinate.getX() + 1, coordinate.getY());

        stage.act(min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());

//        if (isCarpenterShopOn.first().equals(true)) {
//            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//            if (!controller.isValidBuilding(new Coordinate((int) mousePos.x, (int) mousePos.y),isCarpenterShopOn.second())){
//                shapeRenderer.setColor(Color.RED);
//            }else{
//                shapeRenderer.setColor(Color.GREEN);
//            }
//            shapeRenderer.rect(mousePos.x, mousePos.y,isCarpenterShopOn.second().getSize().first() ,
//                isCarpenterShopOn.second().getSize().second());
//            shapeRenderer.end();
//
//        }



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
//                        isCarpenterShopOn.setFirst(true);
//                        isCarpenterShopOn.setSecond(selectedBuilding[0]);
//                        final Window window = new Window("choose where to build", skin);
//
//                        String number = String.valueOf(App.getCurrentGame().getCurrentPlayer().getFarm().getFarmNumber() + 1);
//                        Texture backgroundTexture = new Texture(Gdx.files.internal("Farm_" + number + ".png"));
//                        Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(backgroundTexture));
//                        window.setBackground(backgroundDrawable);
//
//                        window.setSize(940, 600);
//                        window.setPosition(
//                            (stage.getWidth() - window.getWidth()) / 2,
//                            (stage.getHeight() - window.getHeight()) / 2
//                        );
//
//                        window.setMovable(true);
//                        window.setResizable(false);
//
//
//
//                        window.row();
//                        stage.addActor(window);
//
//                        multiplexer.addProcessor(mouseProcessor);
//                        multiplexer.addProcessor(stage);
//                        Gdx.input.setInputProcessor(multiplexer);
//
//                        window.addListener(new ChangeListener() {
//                            @Override
//                            public void changed(ChangeEvent changeEvent, Actor actor) {
//                                isCarpenterShopOn.setFirst(false);
//                                Result result = controller.buildBuilding(selectedBuilding[0].getName(),  String.valueOf(screenX),
//                                    String.valueOf(screenY));
//                                info.setText(result.message());
//                            }
//                        });

                    }
                });

                for (FarmBuildingTypes farmBuildingType : ((CarpenterShop) building).getProducts()) {
                    TextButton productButton = new TextButton(farmBuildingType.getName() + " - " + farmBuildingType.getCost() + "G", skin);

                    productButton.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            selectedBuilding[0] = farmBuildingType;
                            selectedNameLabel.setText(farmBuildingType.getName() + " - " + farmBuildingType.getCost() + "G" + "\n" +
                                "required stone: " + farmBuildingType.getStone() + "\n" + "required wood: " +farmBuildingType.getWood());
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
        camera.position.set(1600 + coordinate.getX() * scaledSize, 1600 + coordinate.getY() * scaledSize, 0);
        camera.update();
    }

    private void renderWorld() {
        int midX = 1600 + coordinate.getX() * scaledSize;
        int midY = 1600 + coordinate.getY() * scaledSize;

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
        drawFarmingBuilding();
    }

    private void drawForaging(Tile tile) {
        for (Good good : tile.getGoods()) {
            if (good instanceof ForagingTree || good instanceof FarmingTree) {

            }
        }
    }

    private void drawFarmingBuilding() {
        for (Player player : App.getCurrentGame().getPlayers()) {
            for (FarmBuilding farmBuilding : player.getFarm().getFarmBuildings()) {
                if (farmBuilding.getType() != FarmBuildingTypes.HOME) {
                    Main.getBatch().draw(farmBuilding.getType().getTexture(), (float) (farmBuilding.getStartCordinate().getX() + farmBuilding.getEndCordinate().getX()) / 2 * scaledSize,
                        (float) (farmBuilding.getStartCordinate().getY() + farmBuilding.getEndCordinate().getY()) / 2 * scaledSize,
                        farmBuilding.getType().getSize().second() * scaledSize, farmBuilding.getType().getSize().first() * scaledSize);
                }
            }
        }
    }
}
