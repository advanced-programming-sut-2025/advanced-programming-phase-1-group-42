package com.StardewValley.client.views;

import com.StardewValley.client.Main;
import com.StardewValley.client.AppClient;
import com.StardewValley.server.controllers.GameMenuController;
import com.StardewValley.models.Assets;
import com.StardewValley.models.game_structure.Farm;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class GameMenuView implements Screen {
    private Skin skin;
    private Stage stage;
    private Table table;
    private Window menuWindow;
    private TextButton newGameButton;
    private TextButton loadGameButton;
    private TextButton backButton;
    private Label titleSavedGameLabel;
    private Label detailsSavedGameLabel;
    private Label errorLabel;

    private Window newGameWindow;
    private Table newGameTable;
    private Label addPlayerLabel;
    private TextField addPlayerField;
    private TextButton addPlayerButton;
    private ArrayList<Label> playerLabels;
    private ArrayList<String> playerUsernames;
    private int playersPtr;
    private TextButton startNewGameButton;
    private TextButton backNewGameButton;
    private Label newGameErrorLabel;

    private Window choiceFarmWindow;
    private Table choiceFarmTable;
    private Label playerUsernameLabel;
    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ArrayList<Table> farmButtons;
    private TextButton choiceFarmBackButton;

    public GameMenuView(GameMenuController controller, Skin skin) {
        this.skin = skin;

        this.menuWindow = new Window("Game Menu", skin);
        this.newGameButton = new TextButton("New Game", skin);
        this.loadGameButton = new TextButton("Load Game", skin);
        this.backButton = new TextButton("Back", skin);
        this.titleSavedGameLabel = new Label("Saved Game: " + AppClient.getCurrentUser().getPlaying(), skin);
        this.titleSavedGameLabel.setFontScale(1.0f);
        this.detailsSavedGameLabel = new Label("", skin);
        if(AppClient.getCurrentUser().getPlaying()) {
            this.detailsSavedGameLabel.setText("Details:\nDays: " + AppClient.getCurrentUser().getGame().getDateTime().getDays() +
                "\nSeason: " + AppClient.getCurrentUser().getGame().getDateTime().getSeason() +
                "\nYear: " + AppClient.getCurrentUser().getGame().getDateTime().getYear() +
                "\nPoints: " + AppClient.getCurrentUser().getGame().findPlayer(AppClient.getCurrentUser().getUsername()).getPoints());
        }
        this.detailsSavedGameLabel.setText("Details:\nDays: " +
            "\nSeason: " +
            "\nYear: " +
            "\nPoints: ");
        this.detailsSavedGameLabel.setFontScale(0.7f);
        this.errorLabel = new Label("", skin);
        this.errorLabel.setFontScale(0.7f);


        this.newGameWindow = new Window("New Game", skin);
        this.addPlayerLabel = new Label("Add Player(Username): ", skin);
        this.addPlayerLabel.setFontScale(0.7f);
        this.addPlayerField = new TextField("example: Parsa-374", skin);
        this.addPlayerButton = new TextButton("Add Player", skin);
        this.playerLabels = new ArrayList();
        Label tempLabel = new Label("Player 1:\n" + AppClient.getCurrentUser().getUsername(), skin);
        tempLabel.setFontScale(0.7f);
        this.playerLabels.add(tempLabel);
        this.playerUsernames = new ArrayList<>();
        this.playerUsernames.add(AppClient.getCurrentUser().getUsername());
        for (int i = 2; i < 5; i++) {
            tempLabel = new Label("Player " + i + ":\nGuest " + (i - 1), skin);
            tempLabel.setFontScale(0.7f);
            this.playerLabels.add(tempLabel);
            this.playerUsernames.add("Guest " + (i - 1));
        }
        this.playersPtr = 1;
        this.startNewGameButton = new TextButton("Start New Game", skin);
        this.backNewGameButton = new TextButton("Back", skin);
        this.newGameErrorLabel = new Label("", skin);
        this.newGameErrorLabel.setFontScale(0.7f);


        this.choiceFarmWindow = new Window("Choice Farm", skin);
        this.playerUsernameLabel = new Label("", skin);
        this.playerUsernameLabel.setFontScale(0.7f);
        this.farmButtons = new ArrayList();
        this.farmButtons.add(new Table(skin));
        tempLabel = new Label("Normal Farm", skin);
        tempLabel.setFontScale(0.7f);
        this.farmButtons.getLast().add(tempLabel);
        this.farmButtons.getLast().row();
        this.imageButton1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Farm_1.png"))),
            new TextureRegionDrawable(new TextureRegion(new Texture("Farm_1_Checked.png"))));
        this.farmButtons.getLast().add(imageButton1).padTop(15);
        this.farmButtons.getLast().setWidth(140);
        this.farmButtons.getLast().row();
        this.farmButtons.add(new Table(skin));
        tempLabel = new Label("Aquatic Farm", skin);
        tempLabel.setFontScale(0.7f);
        this.farmButtons.getLast().add(tempLabel);
        this.farmButtons.getLast().row();
        this.imageButton2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Farm_2.png"))),
            new TextureRegionDrawable(new TextureRegion(new Texture("Farm_2_Checked.png"))));
        this.farmButtons.getLast().add(imageButton2).padTop(15);
        this.farmButtons.getLast().setWidth(140);
        this.farmButtons.getLast().row();
        this.choiceFarmBackButton = new TextButton("Back", skin);

        this.imageButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                choiceFarmWindow.setVisible(false);

                Farm farm = new Farm(0, controller.ptr, controller.tiles);
                controller.players.get(controller.ptr).setFarm(farm);
                controller.farms.add(farm);

                if (controller.ptr == 3) {
                    controller.newGamePhase2();
                    return;
                }

                controller.ptr++;
                initChoiceFarmWindow(controller.players.get(controller.ptr).getPlayerUsername());
            }
        });

        this.imageButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                choiceFarmWindow.setVisible(false);

                Farm farm = new Farm(1, controller.ptr, controller.tiles);
                controller.players.get(controller.ptr).setFarm(farm);
                controller.farms.add(farm);

                if (controller.ptr == 3) {
                    controller.newGamePhase2();
                    return;
                }
                controller.ptr++;
                initChoiceFarmWindow(controller.players.get(controller.ptr).getPlayerUsername());
            }
        });

        this.choiceFarmBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                choiceFarmWindow.setVisible(false);
                if (controller.ptr == 0) {
                    newGameWindow.setVisible(true);
                    return;
                }

                controller.farms.removeLast();
                controller.ptr--;
                initChoiceFarmWindow(controller.players.get(controller.ptr).getPlayerUsername());
            }
        });

    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        this.table = new Table(skin);
        table.setFillParent(true);
        table.pad(70).defaults().expandX().padBottom(15);

        Table leftTable = new Table(skin);
        leftTable.setFillParent(true);
        table.add(newGameButton).width(250).height(70).expandX().fillX().row();
        table.add(loadGameButton).width(250).height(70).expandX().fillX().row();
        table.add(backButton).width(250).height(70).expandX().fillX().row();

        Table rightTable = new Table(skin);
        rightTable.setFillParent(true);
        rightTable.add(titleSavedGameLabel).expandX().fillX().row();
        rightTable.add(detailsSavedGameLabel).expandX().fillX().row();

// Row 1
        table.add(leftTable).left();
        table.add(rightTable).padLeft(30).left();
        table.row();

// Error label in full-width row
        table.add(errorLabel).colspan(2).center().padTop(-200);
        table.row();

        menuWindow.setSize(800, 450);
        setWindowForTable(menuWindow, table, stage);
        menuWindow.setVisible(true);

        this.newGameTable = new Table(skin);
        this.newGameTable.setFillParent(true);
        this.newGameTable.pad(60).defaults().expandX().fillX().row();

// Row 1: Add player input row
        newGameTable.add(addPlayerLabel).left().padRight(30);
        newGameTable.add(addPlayerField).fillX().width(420).left().height(50).padRight(30).row();
        newGameTable.add(addPlayerButton).colspan(2).fillX().left().height(70).padRight(30);
        newGameTable.row();

// Row 2: Player labels (0 and 2)
        newGameTable.add(playerLabels.get(0)).left().padRight(30).padTop(20);
        newGameTable.add(playerLabels.get(2)).left().padRight(30).padTop(20);
        newGameTable.row();

// Row 3: Player labels (1 and 3)
        newGameTable.add(playerLabels.get(1)).left().padRight(30).padTop(20);
        newGameTable.add(playerLabels.get(3)).left().padRight(30).padTop(20);
        newGameTable.row();

// Row 4: Start / Back buttons
        newGameTable.add(startNewGameButton).expandX().left().padRight(30).padTop(20).height(70);
        newGameTable.add(backNewGameButton).expandX().left().padRight(30).padTop(20).height(70);
        newGameTable.row();

// Row 5: Error label (full width)
        newGameTable.add(newGameErrorLabel).colspan(2).center().padTop(30);
        newGameTable.row();

        setWindowForTable(newGameWindow, newGameTable, stage);

        choiceFarmTable = new Table(skin);
        choiceFarmTable.setFillParent(true);
        choiceFarmTable.pad(100).defaults().expandX().fillX().padLeft(-60).row();

        choiceFarmTable.add(playerUsernameLabel).colspan(2).left().fillX().padBottom(20);
        choiceFarmTable.row();

        choiceFarmTable.add(farmButtons.get(0)).left().padLeft(-60).padRight(20);
        choiceFarmTable.add(farmButtons.get(1)).right();
        choiceFarmTable.row();

        choiceFarmTable.add(choiceFarmBackButton).colspan(2).height(70).center().expandX().fillX().padTop(20).padLeft(-60).row();

        setWindowForTable(choiceFarmWindow, choiceFarmTable, stage);
    }

    static void setWindowForTable(Window window, Table table, Stage stage) {
        window.setModal(true);
        window.setMovable(true);
        window.setResizable(false);

        window.add(table).grow().pad(10);
        window.pack();
        window.setPosition(
            Gdx.graphics.getWidth() / 2f - window.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - window.getHeight() / 2f
        );

        stage.addActor(window);
        window.setVisible(false);
    }

    @Override
    public void render(float v) {
        Main.getBatch().begin();
        Assets.getInstance().getMenuBackground2().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Assets.getInstance().getMenuBackground2().setPosition(0, 0);
        Assets.getInstance().getMenuBackground2().draw(Main.getBatch());
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        handleGameMenu();
    }

    @Override
    public void resize(int i, int i1) {

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
        stage.dispose();
    }

    public TextButton getNewGameButton() {
        return newGameButton;
    }

    public TextButton getLoadGameButton() {
        return loadGameButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TextField getAddPlayerField() {
        return addPlayerField;
    }

    public TextButton getAddPlayerButton() {
        return addPlayerButton;
    }

    public ArrayList<Label> getPlayerLabels() {
        return playerLabels;
    }

    public ArrayList<String> getPlayerUsernames() {
        return playerUsernames;
    }

    public int getPlayersPtr() {
        return playersPtr;
    }

    public TextButton getStartNewGameButton() {
        return startNewGameButton;
    }

    public TextButton getBackNewGameButton() {
        return backNewGameButton;
    }

    public Label getNewGameErrorLabel() {
        return newGameErrorLabel;
    }

    public void initNewGameWindow() {
        this.addPlayerField.setText("example: Parsa-374");
        this.playerLabels.getFirst().setText("Player 1:\n" + AppClient.getCurrentUser().getUsername());
        this.playerUsernames.clear();
        this.playerUsernames.add(AppClient.getCurrentUser().getUsername());
        for (int i = 2; i < 5; i++) {
            this.playerLabels.get(i - 1).setText("Player " + i + ":\nGuest " + (i - 1));
            this.playerUsernames.add("Guest " + (i - 1));
        }
        this.playersPtr = 1;
        this.newGameErrorLabel.setText("");

        this.newGameWindow.setVisible(true);
        this.newGameWindow.toFront();
    }

    public Window getNewGameWindow() {
        return newGameWindow;
    }

    public void increasePlayersPtr() {
        playersPtr++;
    }

    public void initChoiceFarmWindow(String username) {
        this.playerUsernameLabel.setText(username + ", Please choice one farm: ");

        this.choiceFarmWindow.setVisible(true);
    }

    public Window getMenuWindow() {
        return menuWindow;
    }

    private void handleGameMenu() {

    }
}
