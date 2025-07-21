package com.StardewValley.views;

import com.StardewValley.Main;
import com.StardewValley.controllers.GameMenuController;
import com.StardewValley.models.App;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Map;
import com.StardewValley.models.game_structure.Tile;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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

    public GameView(GameMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        stage = new Stage();
        table = new Table(skin);
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        coordinate = new Coordinate(0, 0);
    }

    @Override
    public void show() {
//        InputMultiplexer multiplexer = new InputMultiplexer();
//        multiplexer.addProcessor(stage);
//        multiplexer.addProcessor(this);
        viewport.apply();
        Gdx.input.setInputProcessor(this);

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
            coordinate = new Coordinate(coordinate.getX(), coordinate.getY() - 1);
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            coordinate = new Coordinate(coordinate.getX() - 1, coordinate.getY());
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            coordinate = new Coordinate(coordinate.getX(), coordinate.getY() + 1);
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            coordinate = new Coordinate(coordinate.getX() + 1, coordinate.getY());

//        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        stage.draw();

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
    public boolean touchDown(int i, int i1, int i2, int i3) {
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

//    private void updateCamera() {
////        Vector2 p = new Vector2(App.getCurrentGame().getCurrentPlayer().getCoordinate().getX(),
////                                App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
//        camera.position.set(coordinate.getX() * 40, coordinate.getY() * 40, 0);
//        camera.update();
//    }

    private void updateCamera() {
        camera.position.set(1600 + coordinate.getX() * 40, 1600 + coordinate.getY() * 40, 0);
        camera.update();
    }

    private void renderWorld() {
        int midX = 1600 + coordinate.getX() * 40;
        int midY = 1600 + coordinate.getY() * 40;

        for (int x = max((midX - Gdx.graphics.getWidth() / 2) / 40, 0); x < min((midX + Gdx.graphics.getWidth() / 2) / 40 + 1, 150); x++) {
            for (int y = max((midY - Gdx.graphics.getHeight() / 2) / 40, 0); y < min((midY + Gdx.graphics.getHeight() / 2) / 40 + 1, 160); y++) {
                Coordinate coordinate = new Coordinate(x, y);
                Tile tile = Map.findTile(coordinate);
                switch (tile.getTileType()) {
                    case TileType.GREEN_HOUSE -> {
                        Main.getBatch().draw(TileAssets.FARM_PLOWED.getTexture(), x * 40, y * 40, 40, 40);
                        //TODO
                    }
                    case TileType.QUARRY -> {
                        Main.getBatch().draw(TileAssets.QUARRY.getTexture(), x * 40, y * 40, 40, 40);
                    }
                    case TileType.FARM -> {
                        if (App.getCurrentGame().getDateTime().getSeason() == Season.WINTER)
                            Main.getBatch().draw(TileAssets.FARM_WINTER.getTexture(), x * 40, y * 40, 40, 40);
                        else
                            Main.getBatch().draw(TileAssets.FARM_ORDINARY.getTexture(), x * 40, y * 40, 40, 40);
                    }
                    case TileType.PLOWED_FARM -> {
                        Main.getBatch().draw(TileAssets.FARM_PLOWED.getTexture(), x * 40, y * 40, 40, 40);
                    }
                    case TileType.WATER -> {
                        Main.getBatch().draw(TileAssets.WATER.getTexture(), x * 40, y * 40, 40, 40);
                    }
                    case TileType.PLAIN -> {
                        Main.getBatch().draw(TileAssets.GRASS.getTexture(), x * 40, y * 40, 40, 40);
                    }
                    case TileType.ROAD -> {
                        Main.getBatch().draw(TileAssets.ROAD.getTexture(), x * 40, y * 40, 40, 40);
                    }
                    case TileType.STONE_WALL -> {
                        Main.getBatch().draw(TileAssets.STONE_WALL.getTexture(), x * 40, y * 40, 40, 40);
                    }
                    case TileType.SQUARE -> {
                        Main.getBatch().draw(TileAssets.SQUARE.getTexture(), x * 40, y * 40, 40, 40);
                    }
                    case TileType.BEACH -> {
                        Main.getBatch().draw(TileAssets.BEACH.getTexture(), x * 40, y * 40, 40, 40);
                    }
                    case TileType.SHIPPING_BIN -> {
                        Main.getBatch().draw(TileAssets.SHIPPING_BIN.getTexture(), x * 40, y * 40, 40, 40);
                    }
                    case TileType.GAME_BUILDING -> {
                        Main.getBatch().draw(TileAssets.FARM_PLOWED.getTexture(), x * 40, y * 40, 40, 40);
                        //TODO
                    }
                    case TileType.PLAYER_BUILDING -> {
                        Main.getBatch().draw(TileAssets.FARM_PLOWED.getTexture(), x * 40, y * 40, 40, 40);
                        //TODO
                    }
                }
            }
        }
    }
}
