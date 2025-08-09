package com.StardewValley.client.views;

import com.StardewValley.client.Main;
import com.StardewValley.client.AppClient;
import com.StardewValley.models.*;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.User;
import com.StardewValley.server.AppServer;
import com.StardewValley.server.controllers.GameController;
import com.StardewValley.models.game_structure.Farm;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameMenuView implements Screen {
    private Skin skin;
    private Stage stage;
    private Table menuTable;
    private Window menuWindow;
    private TextButton newGameButton;
    private TextButton loadGameButton;
    private TextButton refreshButton;
    private TextButton backButton;
    private TextField searchField;
    private ScrollPane labiesScrollPane;
    private Table labiesTable;
    private Label errorLabel;

    private Window newLabiWindow;
    private Table newLabiTable;
    private Label newLabiNameLabel;
    private TextField newLabiNameField;
    private CheckBox newLabiPrivateCheckBox;
    private TextField newLabiPasswordField;
    private CheckBox newLabiVisibleCheckBox;
    private TextButton createNewLabiButton;
    private TextButton newLabiBackButton;
    private Label newLabiErrorLabel;

    private Window labiWindow;
    private Table labiTable;
    private Label labiDetailsLabel;
    private Label labiAdminUserLabel;
    private Label labiUsersLabel;
    private TextButton startGameButton;
    private TextButton exitLabiButton;
    private Label labiErrorLabel;
    private Thread labiUpdateThread;

    private Window choiceFarmWindow;
    private Table choiceFarmTable;
    private Label playerUsernameLabel;
    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ArrayList<Table> farmButtons;

    private Window waitingWindow;
    private Label waitingLabel;
    private TextButton waitingBackButton;
    private boolean waitingBoolean = true;

    private Window passwordWindow;
    private Label passwordLabel;
    private TextField passwordField;
    private TextButton passwordSubmitButton;
    private TextButton passwordBackButton;

    public GameMenuView(Skin skin) {
        this.skin = skin;

        this.menuWindow = new Window("Game Menu", skin);
        this.menuTable = new Table(skin);
        this.newGameButton = new TextButton("New Labi", skin);
        this.loadGameButton = new TextButton("Load Game", skin);
        this.refreshButton = new TextButton("Refresh", skin);
        this.backButton = new TextButton("Back", skin);
        this.searchField = new TextField("", skin);
        this.labiesScrollPane = new ScrollPane(labiesTable, skin);
        this.labiesTable = new Table(skin);
        this.errorLabel = new Label("", skin);
        this.errorLabel.setFontScale(0.7f);
        this.menuTable.setFillParent(true);
        menuTable.padTop(50).defaults().padBottom(15);

        this.waitingLabel = new Label("Waiting.", skin);


    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        this.menuWindow.setMovable(false);
        this.menuWindow.setResizable(false);
        this.menuWindow.setSize(1200, 800);
        this.menuWindow.setPosition(
            (stage.getWidth() - menuWindow.getWidth()) / 2,
            (stage.getHeight() - menuWindow.getHeight()) / 2
        );

        this.menuTable.add(newGameButton).expandX().fillX().padTop(5).padRight(5).height(70).padLeft(-100);
        this.menuTable.add(loadGameButton).expandX().fillX().padTop(5).height(70).padRight(200);
        this.menuTable.row();

        this.menuTable.add(refreshButton).expandX().fillX().padTop(5).padRight(5).height(70).padLeft(-100);
        this.menuTable.add(backButton).expandX().fillX().padTop(5).height(70).padRight(200);
        this.menuTable.row();

        this.menuTable.add(searchField).expandX().fillX().colspan(2).padTop(5).padRight(200).padLeft(-100).row();
        this.menuTable.add(labiesScrollPane).expandX().fillX().colspan(2).padRight(200).padLeft(-100).row();
        menuWindow.add(menuTable).row();
        initMenuWindow();

        stage.addActor(menuWindow);
//        this.menuTable = new Table(skin);
//        menuTable.setFillParent(true);
//        menuTable.pad(70).defaults().expandX().padBottom(15);
//
//        Table leftTable = new Table(skin);
//        leftTable.setFillParent(true);
//        menuTable.add(newGameButton).width(250).height(70).expandX().fillX().row();
//        menuTable.add(loadGameButton).width(250).height(70).expandX().fillX().row();
//        menuTable.add(backButton).width(250).height(70).expandX().fillX().row();
//
//        Table rightTable = new Table(skin);
//        rightTable.setFillParent(true);
//
//// Row 1
//        menuTable.add(leftTable).left();
//        menuTable.add(rightTable).padLeft(30).left();
//        menuTable.row();
//
//// Error label in full-width row
//        menuTable.add(errorLabel).colspan(2).center().padTop(-200);
//        menuTable.row();
//
//        menuWindow.setSize(800, 450);
//        setWindowForTable(menuWindow, menuTable, stage);
//        menuWindow.setVisible(true);
//
//        this.newLabiTable = new Table(skin);
//        this.newLabiTable.setFillParent(true);
//        this.newLabiTable.pad(60).defaults().expandX().fillX().row();
//
//// Row 1: Add player input row
//        newLabiTable.add(addPlayerLabel).left().padRight(30);
//        newLabiTable.add(addPlayerField).fillX().width(420).left().height(50).padRight(30).row();
//        newLabiTable.add(addPlayerButton).colspan(2).fillX().left().height(70).padRight(30);
//        newLabiTable.row();
//
//// Row 2: Player labels (0 and 2)
//        newLabiTable.add(playerLabels.get(0)).left().padRight(30).padTop(20);
//        newLabiTable.add(playerLabels.get(2)).left().padRight(30).padTop(20);
//        newLabiTable.row();
//
//// Row 3: Player labels (1 and 3)
//        newLabiTable.add(playerLabels.get(1)).left().padRight(30).padTop(20);
//        newLabiTable.add(playerLabels.get(3)).left().padRight(30).padTop(20);
//        newLabiTable.row();
//
//// Row 4: Start / Back buttons
//        newLabiTable.add(startNewGameButton).expandX().left().padRight(30).padTop(20).height(70);
//        newLabiTable.add(backNewGameButton).expandX().left().padRight(30).padTop(20).height(70);
//        newLabiTable.row();
//
//// Row 5: Error label (full width)
//        newLabiTable.add(newLabiErrorLabel).colspan(2).center().padTop(30);
//        newLabiTable.row();
//

//

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
        AppClient.getAssets().getMenuBackground2().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        AppClient.getAssets().getMenuBackground2().setPosition(0, 0);
        AppClient.getAssets().getMenuBackground2().draw(Main.getBatch());

        Main.getBatch().end();

        handleGameMenu();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

    public Label getNewLabiErrorLabel() {
        return newLabiErrorLabel;
    }

    public void initMenuWindow() {
        menuWindow.setVisible(true);
        Message message = new Message(new HashMap<>() {{
            put("function", "getLabies");
            put("arguments", searchField.getText());
        }}, Message.Type.command);
        Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
        if (methodUseMessage(responseMessage, errorLabel)) return;

        this.labiesTable.clear();

        Object raw = responseMessage.getFromBody("message");
        java.util.List<?> rawList = (java.util.List<?>) raw;

        for (Object item : rawList) {
            String json = JSONUtils.getGson().toJson(item);
            Labi labi = JSONUtils.getGson().fromJson(json, Labi.class);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Labi : " + labi.getName() + ", Users (" + labi.getUsers().size() + ") : ");
            for (int i = 0; i < labi.getUsers().size(); i++) {
                User user = labi.getUsers().get(i);
                stringBuilder.append(user.getNickname());
                if (i != labi.getUsers().size() - 1)
                    stringBuilder.append(", ");
            }
            Label labiLabel = new Label(stringBuilder.toString(), skin);
            TextButton textButton = new TextButton("Enter", skin);
            textButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (labi.getUsers().size() == 4) {
                        errorLabel.setText("This labi is full!");
                        return;
                    }
                    if (labi.isPrivate()) {
                        menuWindow.setVisible(false);
                        initPasswordWindow(labi);
                    }
                    else {
                        Message message = new Message(new HashMap<>() {{
                            put("function", "enterLabi");
                            put("arguments", new ArrayList<>(Arrays.asList(String.valueOf(labi.getID()),
                                AppClient.getCurrentUser().getUsername(), "")));
                        }}, Message.Type.command);
                        Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                        if (methodUseMessage(responseMessage, errorLabel)) return;

                        Object msgObj = responseMessage.getFromBody("message");
                        Labi labi;

                        if (msgObj instanceof String) {
                            // ✅ Already a JSON string, just parse directly
                            labi = JSONUtils.fromJsonLabi((String) msgObj);
                        } else {
                            // ✅ Not a string — convert to JSON string first
                            String json = JSONUtils.getGson().toJson(msgObj);
                            labi = JSONUtils.fromJsonLabi(json);
                        }
                        AppClient.setCurrentLabi(labi);
                        menuWindow.setVisible(false);
                        initLabiWindow(false, labi);
                    }

                }
            });

            labiesTable.add(labiLabel).width(800).fillX().padTop(5).padRight(5).left();
            labiesTable.add(textButton).width(130).fillX().padTop(5).padRight(5).right();
            labiesTable.row();
        }

        this.labiesScrollPane.setActor(this.labiesTable);
    }

    private void initPasswordWindow(Labi labi) {
        this.passwordWindow = new Window("", skin);
        passwordWindow.setMovable(false);
        passwordWindow.setResizable(false);
        passwordWindow.setSize(800, 400);
        passwordWindow.setPosition(
            (stage.getWidth() - passwordWindow.getWidth()) / 2,
            (stage.getHeight() - passwordWindow.getHeight()) / 2
        );
        this.passwordLabel = new Label("Enter your password for " + labi.getName() + " labi :", skin);
        this.passwordField = new TextField("", skin);
        this.passwordBackButton = new TextButton("Back", skin);
        this.passwordBackButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               initMenuWindow();
               passwordWindow.remove();
           }
        });
        this.passwordSubmitButton = new TextButton("Submit", skin);
        this.passwordSubmitButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               passwordWindow.remove();
               if (!labi.getPassword().equals(passwordField.getText())) {
                   errorLabel.setText("Wrong password for " + labi.getName() + " Labi");
                   initMenuWindow();
                   return;
               }

               Message message = new Message(new HashMap<>() {{
                   put("function", "enterLabi");
                   put("arguments", new ArrayList<>(Arrays.asList(String.valueOf(labi.getID()),
                           AppClient.getCurrentUser().getUsername(), "")));
               }}, Message.Type.command);
               Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
               if (methodUseMessage(responseMessage, errorLabel)) return;

               AppClient.setCurrentLabi(labi);
               initLabiWindow(false, labi);
           }
        });

        passwordWindow.add(passwordLabel).fillX().expandX().row();
        passwordWindow.add(passwordField).fillX().expandX().row();
        passwordWindow.add(passwordSubmitButton).fillX().expandX().row();
        passwordWindow.add(passwordBackButton).fillX().expandX().row();

        stage.addActor(passwordWindow);
        passwordWindow.top();
    }

    private void initLabiWindow(boolean isAdmin, Labi labi) {
        labiWindow = new Window(labi.getName() + " Labi", skin);
        labiWindow.setMovable(false);
        labiWindow.setResizable(false);
        labiWindow.setSize(1000, 800);
        labiWindow.setPosition(
            (stage.getWidth() - labiWindow.getWidth()) / 2,
            (stage.getHeight() - labiWindow.getHeight()) / 2
        );

        labiTable = new Table(skin);
        labiDetailsLabel = new Label("ID: " + labi.getID() +
            ", isPrivate:" + labi.isPrivate() + ", isVisible" + labi.isVisible(), skin);

        labiAdminUserLabel = new Label("Admin User: " + labi.getAdminUser().getUsername(), skin);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Users: \n");
        for (User user : labi.getUsers()) {
            stringBuilder.append("\t" + user.getUsername() + "\n");
        }
        labiUsersLabel = new Label(stringBuilder.toString(), skin);
        startGameButton = new TextButton("Start Game", skin);
        if (!isAdmin)
            startGameButton.setVisible(false);
        exitLabiButton = new TextButton("Exit Game", skin);

        startGameButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
//               if (labi.getUsers().size() != 4) {
//                   labiErrorLabel.setText("There should be exactly 4 users!");
//                   return;
//               }

               closeLabiWindow();
               Message message = new Message(new HashMap<>() {{
                   put("function", "startGame");
                   put("arguments", labi.getID());
               }}, Message.Type.command);
               Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
               if (methodUseMessage(responseMessage, labiErrorLabel)) return;

               initChoiceFarmWindow();
           }
        });

        exitLabiButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeLabiWindow();

                Message message = new Message(new HashMap<>() {{
                    put("function", "exitLabi");
                    put("arguments", new ArrayList<>(Arrays.asList(String.valueOf(labi.getID()),
                        AppClient.getCurrentUser().getUsername()
                    )));
                }}, Message.Type.command);
                Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                if (methodUseMessage(responseMessage, errorLabel)) return;

                errorLabel.setText(responseMessage.getFromBody("message"));
                AppClient.setCurrentLabi(null);

                initMenuWindow();
            }
        });
        labiErrorLabel = new Label("", skin);


        labiTable.add(labiDetailsLabel).fillX().expandX().row();
        labiTable.add(labiUsersLabel).fillX().expandX().row();
        labiTable.add(startGameButton).fillX().expandX();
        labiTable.add(exitLabiButton).fillX().expandX().row();
        labiTable.add(labiErrorLabel).fillX().expandX().row();

        labiWindow.add(labiTable);
        stage.addActor(labiWindow);
        labiWindow.top();
    }

    private void closeLabiWindow() {
        labiWindow.remove();
        labiWindow = null;
        labiTable.remove();
    }

    private void initWaitWindow() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        waitingWindow = new Window("", skin, "Letter");
        waitingWindow.setMovable(false);
        waitingWindow.setResizable(false);
        waitingWindow.setSize(300, 100);
        waitingWindow.setPosition(
            (stage.getWidth() - waitingWindow.getWidth()) / 2,
            (stage.getHeight() - waitingWindow.getHeight()) / 2
        );

        switch (waitingLabel.getText().toString()) {
            case "Waiting.":
                waitingLabel.setText("Waiting..");
                break;
            case "Waiting..":
                waitingLabel.setText("Waiting...");
                break;
            case "Waiting...":
                waitingLabel.setText("Waiting.");
                break;
        }

        waitingWindow.add(waitingLabel).fillX().expandX().row();
        waitingBackButton = new TextButton("Back", skin);
        waitingBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                waitingBoolean = false;
            }
        });
        waitingWindow.add(waitingBackButton).fillX().expandX().row();

        stage.addActor(waitingWindow);
        waitingWindow.top();
    }

    private void closeWaitWindow() {
        waitingWindow.remove();
        waitingWindow = null;
    }

    private void initNewLabiWindow() {
        newLabiWindow = new Window("New Labi", skin);
        newLabiWindow.setMovable(false);
        newLabiWindow.setResizable(false);
        newLabiWindow.setSize(1000, 800);
        newLabiWindow.setPosition(
            (stage.getWidth() - newLabiWindow.getWidth()) / 2,
            (stage.getHeight() - newLabiWindow.getHeight()) / 2
        );

        newLabiTable = new Table(skin);
        newLabiNameLabel = new Label("Labi name : ", skin);
        newLabiNameField = new TextField("testLabi", skin);
        newLabiPrivateCheckBox = new CheckBox("is Private ? ", skin);
        newLabiPasswordField = new TextField("pass", skin);
        newLabiVisibleCheckBox = new CheckBox("is visible ? ", skin);
        createNewLabiButton = new TextButton("Create New Labi", skin);
        newLabiErrorLabel = new Label("", skin);
        createNewLabiButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeNewLabiWindow();
                Message message = new Message(new HashMap<>() {{
                    put("function", "createNewLabi");
                    put("arguments", new ArrayList<>(Arrays.asList(
                        AppClient.getCurrentUser().getUsername(),
                        newLabiNameField.getText(),
                        String.valueOf(newLabiPrivateCheckBox.isChecked()),
                        (newLabiPrivateCheckBox.isChecked() ? newLabiPasswordField.getText() : ""),
                        String.valueOf(newLabiVisibleCheckBox.isChecked())
                    )));
                }}, Message.Type.command);
                Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                if (methodUseMessage(responseMessage, newLabiErrorLabel)) return;

                Object msgObj = responseMessage.getFromBody("message");
                Labi labi;

                if (msgObj instanceof String) {
                    // ✅ Already a JSON string, just parse directly
                    labi = JSONUtils.fromJsonLabi((String) msgObj);
                } else {
                    // ✅ Not a string — convert to JSON string first
                    String json = JSONUtils.getGson().toJson(msgObj);
                    labi = JSONUtils.fromJsonLabi(json);
                }

                AppClient.setCurrentLabi(labi);
                closeNewLabiWindow();
                initLabiWindow(true, labi);
            }
        });

        newLabiBackButton = new TextButton("Back", skin);
        newLabiBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                closeNewLabiWindow();
                initMenuWindow();
            }
        });

        newLabiTable.add(newLabiNameLabel).fillX().expandX().padRight(5);
        newLabiTable.add(newLabiNameField).fillX().expandX().row();
        newLabiTable.add(newLabiPrivateCheckBox).fillX().expandX().padRight(5);
        newLabiTable.add(newLabiPasswordField).fillX().expandX().row();
        newLabiTable.add(newLabiVisibleCheckBox).fillX().expandX().row();
        newLabiTable.add(createNewLabiButton).fillX().expandX().padRight(5);
        newLabiTable.add(newLabiBackButton).fillX().expandX().row();
        newLabiTable.add(newLabiErrorLabel).fillX().expandX().row();

        newLabiWindow.add(newLabiTable);
        stage.addActor(newLabiWindow);
        newLabiTable.top();
    }

    private void closeNewLabiWindow() {
        newLabiWindow.remove();
        newLabiTable.remove();
    }

    public Window getNewLabiWindow() {
        return newLabiWindow;
    }

    public void initChoiceFarmWindow() {
        Label tempLabel;
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
        choiceFarmTable = new Table(skin);
        choiceFarmTable.setFillParent(true);
        choiceFarmTable.pad(100).defaults().expandX().fillX().padLeft(-60).row();

        choiceFarmTable.add(playerUsernameLabel).colspan(2).left().fillX().padBottom(20);
        choiceFarmTable.row();

        choiceFarmTable.add(farmButtons.get(0)).left().padLeft(-60).padRight(20);
        choiceFarmTable.add(farmButtons.get(1)).right();
        choiceFarmTable.row();

        choiceFarmWindow.setModal(true);
        choiceFarmWindow.setMovable(true);
        choiceFarmWindow.setResizable(false);

        choiceFarmWindow.add(choiceFarmTable).grow().pad(10);
        choiceFarmWindow.pack();
        choiceFarmWindow.setPosition(
            Gdx.graphics.getWidth() / 2f - choiceFarmWindow.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - choiceFarmWindow.getHeight() / 2f
        );

        this.playerUsernameLabel.setText(AppClient.getCurrentUser().getUsername() + ", Please choice one farm: ");
        this.imageButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                choiceFarmWindow.remove();
                Message message2 = new Message(new HashMap<>() {{
                    put("function", "choiceFarm");
                    put("arguments", new ArrayList<>(Arrays.asList(
                        AppClient.getCurrentUser().getUsername(),
                        String.valueOf(AppClient.getCurrentLabi().getID()),
                        String.valueOf(0)
                    )));
                }}, Message.Type.command);
                Message responseMessage2 = AppClient.getServerHandler().sendAndWaitForResponse(message2);
                if (!checkMessageValidity(responseMessage2, Message.Type.response)) {
                    return;
                }

                initWaitWindow();
            }
        });

        this.imageButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                choiceFarmWindow.remove();
                Message message2 = new Message(new HashMap<>() {{
                    put("function", "choiceFarm");
                    put("arguments", new ArrayList<>(Arrays.asList(
                        AppClient.getCurrentUser().getUsername(),
                        String.valueOf(AppClient.getCurrentLabi().getID()),
                        String.valueOf(1)
                    )));
                }}, Message.Type.command);
                Message responseMessage2 = AppClient.getServerHandler().sendAndWaitForResponse(message2);
                if (!checkMessageValidity(responseMessage2, Message.Type.response)) {
                    return;
                }

                initWaitWindow();
            }
        });

        choiceFarmWindow.setVisible(true);
        stage.addActor(choiceFarmWindow);
        choiceFarmWindow.top();
    }

    public Window getMenuWindow() {
        return menuWindow;
    }

    private void handleGameMenu() {
        loadCurrentLabi();
        loadWaitingWindow();

        if (getBackButton().isChecked()) {
            getBackButton().setChecked(false);

            Message message = new Message(new HashMap<>() {{
                put("field", "controller");
                put("change", "MainMenuController");
            }}, Message.Type.change);
            Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
            if (methodUseMessage(responseMessage, errorLabel)) return;

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(AppClient.getAssets().getSkin()));
        } else if (getLoadGameButton().isChecked()) {
            getLoadGameButton().setChecked(false);

            boolean flag = true;
            waitingBoolean = true;
            while (flag && waitingBoolean) {
                Message message = new Message(new HashMap<>() {{
                    put("function", "loadGame");
                    put("arguments", new ArrayList<>(Arrays.asList(
                        AppClient.getCurrentUser().getUsername()
                    )));
                }}, Message.Type.command);
                Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                if (!checkMessageValidity(responseMessage, Message.Type.response)) {
                    errorLabel.setText("Network error!");
                    flag = false;
                }
                if(responseMessage.getBooleanFromBody("success")) {
                    break;
                }

                initWaitWindow();
            }

            closeWaitWindow();
            if (!flag || !waitingBoolean) {

                return;
            }

            Main.getMain().getScreen().dispose();
//            Main.getMain().setScreen(new GameView(new GameController(), AppClient.getAssets().getSkin()));
        } else if (getNewGameButton().isChecked()) {
            getNewGameButton().setChecked(false);

            menuWindow.setVisible(false);
            initNewLabiWindow();
        }
        else if (refreshButton.isChecked()) {
            refreshButton.setChecked(false);

            initMenuWindow();
        }
//        else if (getBackNewGameButton().isChecked()) {
//            getBackNewGameButton().setChecked(false);
//
//            getMenuWindow().setVisible(true);
//            getNewLabiWindow().setVisible(false);
//        } else if (getAddPlayerButton().isChecked()) {
//            getAddPlayerButton().setChecked(false);
//
//            if (getPlayersPtr() >= 4) {
//                getNewLabiErrorLabel().setText("At most 4 players can play the game!");
//                return;
//            }
//
//            addPlayerToNewGame();
//        } else if (getStartNewGameButton().isChecked()) {
//            getStartNewGameButton().setChecked(false);
//
//            getNewLabiWindow().setVisible(false);
//            newGame(getPlayerUsernames());
//        }
    }

    private void loadCurrentLabi() {
        if (labiWindow != null && AppClient.getCurrentLabi() != null) {
            Message message = new Message(new HashMap<>() {{
                put("function", "updateLabi");
                put("arguments", AppClient.getCurrentLabi().getID());
            }}, Message.Type.command);
            Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
            if (methodUseMessage(responseMessage, labiErrorLabel)) return;

            if (responseMessage.getFromBody("message").equals("choice farm")) {
                closeLabiWindow();
                initChoiceFarmWindow();
                return;
            }

            Object msgObj = responseMessage.getFromBody("message");
            Labi updatedLabi;

            if (msgObj instanceof String) {
                // ✅ Already a JSON string, just parse directly
                updatedLabi = JSONUtils.fromJsonLabi((String) msgObj);
            } else {
                // ✅ Not a string — convert to JSON string first
                String json = JSONUtils.getGson().toJson(msgObj);
                updatedLabi = JSONUtils.fromJsonLabi(json);
            }
            labiAdminUserLabel.setText("Admin User: " + updatedLabi.getAdminUser().getUsername());
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Users: \n");
            for (User user : updatedLabi.getUsers()) {
                stringBuilder1.append("\t").append(user.getUsername()).append("\n");
            }
            labiUsersLabel.setText(stringBuilder1.toString());
        }
        return;
    }

    private void loadWaitingWindow() {
        if (waitingWindow != null) {
            Message message = new Message(new HashMap<>() {{
                put("function", "updateWait");
                put("arguments", AppClient.getCurrentLabi().getID());
            }}, Message.Type.command);
            Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
            if (responseMessage.getBooleanFromBody("success")) {
                AppClient.setCurrentLabi(null);

                int gameID = responseMessage.getIntFromBody("message");
                Message message2 = new Message(new HashMap<>() {{
                    put("function", "getNewGame");
                    put("arguments", new ArrayList<>(Arrays.asList(
                            String.valueOf(gameID),
                            AppClient.getCurrentUser().getUsername()
                    )));
                }}, Message.Type.command);
                Message responseMessage2 = AppClient.getServerHandler().sendAndWaitForResponse(message2);
                Object msgObj = responseMessage2.getFromBody("message");
                Game userGame;
                if (msgObj instanceof String) {
                    userGame = JSONUtils.fromJsonGame((String) msgObj);
                } else {
                    String json = JSONUtils.getGson().toJson(msgObj);
                    userGame = JSONUtils.fromJsonGame(json);
                }

                AppClient.setCurrentGame(userGame);
                for (Player player : userGame.getPlayers()) {
                    if (player.getUsername().equals(AppClient.getCurrentUser().getUsername())) {
                        AppClient.setCurrentPlayer(player);
                        break;
                    }
                }

                closeWaitWindow();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(AppClient.getAssets().getSkin()));
            }
            else {
                initWaitWindow();
            }
        }
    }

    private boolean methodUseMessage(Message responseMessage, Label label) {
        if (!checkMessageValidity(responseMessage, Message.Type.response)) {
            label.setText("Network error!");
            return true;
        }
        if(!responseMessage.getBooleanFromBody("success")) {
            label.setText(responseMessage.getFromBody("message"));
            return true;
        }
        return false;
    }

    public boolean checkMessageValidity(Message message, Message.Type type) {
        return message.getType() == type;
    }
}
