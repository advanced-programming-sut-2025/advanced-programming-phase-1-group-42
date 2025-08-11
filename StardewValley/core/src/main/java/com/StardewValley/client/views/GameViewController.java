package com.StardewValley.client.views;

import com.StardewValley.client.AppClient;
import com.StardewValley.client.Main;
import com.StardewValley.models.Assets;
import com.StardewValley.models.JSONUtils;
import com.StardewValley.models.Message;
import com.StardewValley.models.Pair;
import com.StardewValley.models.enums.LeaderboardSortType;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.*;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.interactions.Animals.Animal;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuilding;
import com.StardewValley.models.interactions.game_buildings.Quadruple;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import static java.lang.Math.abs;

public class GameViewController {
    private Skin skin;
    private GameView gameView;

    private int inHandGoodPtr = 0;
    private ArrayList<Quadruple<ImageButton, Image, Label, Label>> inventoryElements;
    private ArrayList<Pair<Pair<ImageButton, Image>, Integer>> toolsElements;
    private ArrayList<ImageButton> mainInventoryElements;
    private ProgressBar progressBar;
    private ArrayList<Window> inventoryWindows = new ArrayList<>();

    private Table leaderboardTable;

    public GameViewController(GameView gameView) {
        skin = AppClient.getAssets().getSkin();
        this.gameView = gameView;
    }

    public void initInventory() {
        inventoryElements = new ArrayList<>();
        TextureRegionDrawable drawableSlot = AppClient.getAssets().getDrawableSlot();
        TextureRegionDrawable drawableHighlight = AppClient.getAssets().getDrawableHighlight();

        int ctr = 1;
        for (ArrayList<Good> goods : AppClient.getCurrentPlayer().getInventory().getList()) {
            ImageButton imageButtonBackground = new ImageButton(drawableSlot, drawableHighlight, drawableHighlight);
            Image image = new Image(new TextureRegion(new Texture("GameAssets/null.png")));
            if (!goods.isEmpty())
                image = new Image(new TextureRegion(new Texture(goods.getFirst().getType().imagePath())));

            Image finalImage = image;
            Label counterLabel = new Label(String.valueOf(ctr++), skin);
            counterLabel.setFontScale(0.4f);
            Label quantityLabel = new Label((goods.isEmpty()) ? "" : String.valueOf(goods.size()), skin, "Bold");
            quantityLabel.setFontScale(0.4f);
            image.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    for (int i = 0; i < inventoryElements.size(); i++) {
                        Quadruple<ImageButton, Image, Label, Label> quadruple = inventoryElements.get(i);
                        quadruple.a.setChecked(false);
                        if (quadruple.b == finalImage) {
                            quadruple.a.setChecked(true);
                            int finalI = i;
                            if (gameView.getFridgeOpen()) {

                                Message message = new Message(new HashMap<>() {{
                                    put("function", "addItemToFridge");
                                    put("arguments", String.valueOf(finalI));
                                }}, Message.Type.command);
                                Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                                methodUseMessage(responseMessage);

//                                AppClient.getCurrentGame().getCurrentPlayer().getFridge().addItemToFridge();
                            } else {
                                Message message = new Message(new HashMap<>() {{
                                    put("function", "setInHandGood");
                                    put("arguments", String.valueOf(finalI));
                                }}, Message.Type.command);
                                Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                                methodUseMessage(responseMessage);
                                inHandGoodPtr = finalI;

                                 playerChangedInventory();
                                gameView.updateGameObject();
//                                AppClient.getCurrentGame().getCurrentPlayer().setInHandGood(
//                                    AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i));
                            }
                        }
                    }
                }
            });

            inventoryElements.add(new Quadruple<>(imageButtonBackground, image, counterLabel, quantityLabel));
        }

        progressBar = new ProgressBar(0, 200, 1, true, AppClient.getAssets().getSkin());
        progressBar.setValue(AppClient.getCurrentPlayer().getEnergy().getDayEnergyLeft());

        toolsElements = new ArrayList<>();
        mainInventoryElements = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            TextureRegionDrawable tabDrawable = new TextureRegionDrawable(new Texture("GameAssets/Main_Inventory/MainTable" + (i + 1) + ".png"));
            TextureRegionDrawable tabDrawableClicked = new TextureRegionDrawable(new Texture("GameAssets/Main_Inventory/MainTable" + (i + 1) + "Clicked.png"));
            ImageButton tabButton = new ImageButton(tabDrawable, tabDrawableClicked, tabDrawableClicked);
            if (i == 6) {
                tabButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        for (int i = 0; i < mainInventoryElements.size(); i++) {
                            ImageButton imageButton = mainInventoryElements.get(i);
                            imageButton.setChecked(false);
                            if (imageButton == tabButton) {
//                                imageButton.setChecked(true);
                                gameView.closeMainTable();
                            }
                        }
                    }
                });
            }
            else {
                tabButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        for (int i = 0; i < mainInventoryElements.size(); i++) {
                            ImageButton imageButton = mainInventoryElements.get(i);
                            imageButton.setChecked(false);
                            if (imageButton == tabButton) {
                                imageButton.setChecked(true);
                                switch (i){
                                    case 0:
                                        gameView.switchWindow(inventoryWindows.get(0),0);
                                        break;
                                    case 1:
                                        gameView.switchWindow(inventoryWindows.get(1),1);
                                        break;
                                    case 2:
                                        gameView.switchWindow(inventoryWindows.get(2),2);
                                        break;
                                    case 3:
                                        gameView.switchWindow(inventoryWindows.get(3),3);
                                        break;
                                    case 4:
                                        gameView.switchWindow(inventoryWindows.get(4),4);
                                        break;
                                    case 5:
                                        gameView.switchWindow(inventoryWindows.get(5),5);
                                        break;
                                    default:
                                        gameView.switchWindow(inventoryWindows.get(3),3);
                                        break;

                                }
                            }
                        }
                    }
                });
            }

            mainInventoryElements.add(tabButton);
        }

    }

    public void handleInput() {
        if (gameView.getCheatWindow() != null)
            return;

        Player player = AppClient.getCurrentPlayer();
        Pair<Sprite, Sprite> playerSprite = null;
        int ptr = 0;
        for (Player eachPlayer : AppClient.getCurrentGame().getPlayers()) {
            if (eachPlayer.getPlayerUsername().equals(player.getPlayerUsername())) {
                playerSprite = gameView.getPlayersSprite().get(ptr);
                break;
            }
            ptr++;
        }

        Message message = new Message(new HashMap<>() {{
            put("function", "setPlayerDirection");
            put("arguments", String.valueOf(-1));
        }}, Message.Type.command);
        Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
        methodUseMessage(responseMessage);
        if (player.getPlayerDirection() != -1)
            gameView.updateGameObject();


//        player.setPlayerDirection(-1);

        if(Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            message = new Message(new HashMap<>() {{
                put("function", "increaseBalance");
                put("arguments", String.valueOf(1000));
            }}, Message.Type.command);
            responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
            methodUseMessage(responseMessage);
            gameView.updateGameObject();

//            AppClient.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(1000);
        }
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
                message = new Message(new HashMap<>() {{
                    put("function", "increaseBalance");
                    put("arguments", String.valueOf(1000));
                }}, Message.Type.command);
                responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                methodUseMessage(responseMessage);


//                AppClient.getCurrentGame().getCurrentPlayer().getWallet().increaseBalance(1000);
            }

            boolean flag = false;
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (tileValidity(AppClient.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX(), player.getCoordinate().getY() + 1))) {
                    sendLastCoordinate(player);

                    sendCoordinate(message, (new Coordinate(player.getCoordinate().getX(),
                        player.getCoordinate().getY() + 1)).toString(), responseMessage);
//                    player.setCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY() + 1));

                    setPlayerDirection(String.valueOf(0));
//                    player.setPlayerDirection(0);
                }
                flag = true;
//                AppClient.getCurrentGame().getMap().updateMap();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                if (tileValidity(AppClient.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX() - 1, player.getCoordinate().getY()))) {
                    sendLastCoordinate(player);

                    sendCoordinate(message, (new Coordinate(player.getCoordinate().getX() - 1, player.getCoordinate().getY())).toString(),
                        responseMessage);
//                    player.setCoordinate(new Coordinate(player.getCoordinate().getX() - 1, player.getCoordinate().getY()));

                    setPlayerDirection(String.valueOf(1));
//                    player.setPlayerDirection(1);


                }
                flag = true;
//                AppClient.getCurrentGame().getMap().updateMap();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (tileValidity(AppClient.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX(), player.getCoordinate().getY() - 1))) {
                    sendLastCoordinate(player);

                    sendCoordinate(message, (new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY() - 1)).toString(),
                        responseMessage);

                    setPlayerDirection(String.valueOf(2));

//                    player.setLastCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY()));
//                    player.setCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY() - 1));
//                    player.setPlayerDirection(2);


                }
                flag = true;
//                AppClient.getCurrentGame().getMap().updateMap();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (tileValidity(AppClient.getCurrentGame().getMap().findTileByXY(player.getCoordinate().getX() + 1, player.getCoordinate().getY()))) {
                    sendLastCoordinate(player);

                    sendCoordinate(message, (new Coordinate(player.getCoordinate().getX() + 1, player.getCoordinate().getY())).toString(),
                        responseMessage);
//                    player.setCoordinate(new Coordinate(player.getCoordinate().getX() + 1, player.getCoordinate().getY()));

                    setPlayerDirection(String.valueOf(3));
//                    player.setPlayerDirection(1);

//                    player.setLastCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY()));
//                    player.setCoordinate(new Coordinate(player.getCoordinate().getX() + 1, player.getCoordinate().getY()));
//                    player.setPlayerDirection(3);
                }
                flag = true;
//                AppClient.getCurrentGame().getMap().updateMap();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {

            }
            //TESTES COMMANDS //////////////////////////////////////////////////
            if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
                gameView.showThunder();
                gameView.updateGameObject();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                gameView.updateGameObject();
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
                setInventoryWindows(inventoryWindows);
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
                gameView.toggleMapVisibility();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
                gameView.setTabClicked(!gameView.isTabClicked());
                if (gameView.isTabClicked()) {
                    for (Quadruple<ImageButton, Image, Label, Label> inventoryElement : getInventoryElements()) {
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
                for (FarmBuilding building : AppClient.getCurrentPlayer().getFarm().getFarmBuildings()) {
                    for (Animal animal : building.getAnimals()) {
                        if ((abs(animal.getCoordinate().getX() -
                            AppClient.getCurrentPlayer().getCoordinate().getX()) <= 2) &&
                            (abs(animal.getCoordinate().getY() -
                                AppClient.getCurrentPlayer().getCoordinate().getY()) <= 2)) {

                            message = new Message(new HashMap<>() {{
                                put("function", "petAnimal");
                                put("arguments", new ArrayList<>(Arrays.asList(
                                    animal.getName(), building.getName()
                                )));
                            }}, Message.Type.command);
                            responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                            methodUseMessage(responseMessage);
                            gameView.updateGameObject();
                            //TODO

//                            animal.petAnimal();
                            gameView.buildMessage();
                            gameView.getTextFieldMessage().setText("You petted " + animal.getName());
                        }
                    }
                }
                gameView.buildMessage();
                gameView.getTextFieldMessage().setText("Please approach an animal to pet");
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
                Tile selectedTile = AppClient.getCurrentGame().getMap().findTileByXY(AppClient.getCurrentPlayer().getCoordinate().getX()
                    , AppClient.getCurrentPlayer().getCoordinate().getY());

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
                Tile selectedTile = AppClient.getCurrentGame().getMap().findTileByXY(AppClient.getCurrentPlayer().getCoordinate().getX()
                    , AppClient.getCurrentPlayer().getCoordinate().getY());
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
                Tile selectedTile = AppClient.getCurrentGame().getMap().findTileByXY(AppClient.getCurrentPlayer().getCoordinate().getX()
                    , AppClient.getCurrentPlayer().getCoordinate().getY());
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
                    int finalI = i;
                    message = new Message(new HashMap<>() {{
                        put("function", "setInHandGood");
                        put("arguments", String.valueOf(finalI));
                    }}, Message.Type.command);
                    responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                    methodUseMessage(responseMessage);
                    playerChangedInventory();
                    inHandGoodPtr = finalI;
                    gameView.updateGameObject();
//                    AppClient.getCurrentGame().getCurrentPlayer().setInHandGood(
//                        AppClient.getCurrentGame().getCurrentPlayer().getInventory().getList().get(i));                               gameView.updateGameObject();
                    break;
                }
            }

            if (flag) {
                message = new Message(new HashMap<>() {{
                    put("function", "decreaseTurnEnergyLeft");
                    put("arguments", String.valueOf(0.25));
                }}, Message.Type.command);
                responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                methodUseMessage(responseMessage);
                gameView.updateGameObject();
//                AppClient.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(0.25, );
            }

        }

    }

    private void setPlayerDirection(String value) {
        Message message = new Message(new HashMap<>() {{
            put("function", "setPlayerDirection");
            put("arguments", value);
        }}, Message.Type.command);
        Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
        methodUseMessage(responseMessage);
    }

    private void sendCoordinate(Message message, String setCoordinate, Message responseMessage) {
        message = new Message(new HashMap<>() {{
            put("function", "setCoordinate");
            put("arguments", setCoordinate);
        }}, Message.Type.command);
        responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
        methodUseMessage(responseMessage);
    }

    private void sendLastCoordinate(Player player) {
        Message responseMessage;
        Message message;
        message = new Message(new HashMap<>() {{
            put("function", "setLastCoordinate");
            put("arguments",
                (new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY())).toString());
        }}, Message.Type.command);
        responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
        methodUseMessage(responseMessage);
//                    player.setLastCoordinate(new Coordinate(player.getCoordinate().getX(), player.getCoordinate().getY()));
    }

    public ArrayList<Window> getInventoryWindows() {
        return inventoryWindows;
    }

    private ArrayList<Good> cursorGoods = null;
    private Image cursorImage = new Image();

    private ArrayList<Window> createWindows() {
        ArrayList<Window> inventoryWindows = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            final int index = i;
            Skin skin = AppClient.getAssets().getSkin();
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
                    TextureRegionDrawable drawableSlot = AppClient.getAssets().getDrawableSlot();
                    TextureRegionDrawable drawableHighlight = AppClient.getAssets().getDrawableHighlight();

                    int inventorySize = AppClient.getCurrentPlayer().getInventory().getSize();
                    ArrayList<ArrayList<Good>> inventoryData = AppClient.getCurrentPlayer().getInventory().getList();
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
                                int totalPrice = cursorGoods.getLast().getSellPrice()*cursorGoods.size();
                                Message message = new Message(new HashMap<>() {{
                                    put("function", "useTrashCan");
                                    put("arguments", new ArrayList<>(Arrays.asList(
                                        String.valueOf(totalPrice)
                                    )));
                                }}, Message.Type.command);
                                Message responseMessage = AppClient.getServerHandler().sendAndWaitForResponse(message);
                                methodUseMessage(responseMessage);
                                int finalPrice = Integer.parseInt(responseMessage.getFromBody("message"));

//                                int finalPrice = ToolFunctions.useTrashCan(App.getCurrentGame().getCurrentPlayer().getTrashCan(), totalPrice);
                                AppClient.getCurrentPlayer().getWallet().increaseBalance(finalPrice);

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

                        Texture hearts = new Texture("GameAssets\\Main_Inventory\\" + npc.getFriendship(
                            AppClient.getCurrentPlayer()).getFriendshipLevel() + ".png");
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
                        if (!p.equals(AppClient.getCurrentPlayer())) {
                            rightSideTable.row().padBottom(12);
                            Table row = new Table().right();

                            Texture playerTxr = new Texture("GameAssets\\Main_Inventory\\Player.png");
                            Image npc_img = new Image(playerTxr);

                            Pair<Integer, Integer> friendship = p.getFriendShips().get(AppClient.getCurrentPlayer().getUsername());
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
                    Skill skill = AppClient.getCurrentPlayer().getSkill();
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

                    window.setVisible(false);

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

        int inventorySize = AppClient.getCurrentPlayer().getInventory().getSize();
        ArrayList<ArrayList<Good>> inventoryData = AppClient.getCurrentPlayer().getInventory().getList();

        int itemIndex = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 12; col++) {
                Table slotContainer = new Table();

                if (itemIndex < inventorySize) {
                    ArrayList<Good> goods = inventoryData.get(itemIndex);
                    ImageButton imageButtonBackground = new ImageButton(
                        AppClient.getAssets().getDrawableSlot(),
                        AppClient.getAssets().getDrawableSlot(),
                        AppClient.getAssets().getDrawableHighlight()
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

                    Label quantityLabel = new Label(goods.isEmpty() ? "" : String.valueOf(goods.size()), AppClient.getAssets().getSkin(), "Bold");
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
            (tile.getTileType() == TileType.GREEN_HOUSE && !AppClient.getCurrentPlayer().getFarm().getGreenHouse().isAvailable()))
            return false;
        return true;
    }

    private LeaderboardSortType currentSortType = LeaderboardSortType.POINTS;
    private float refreshTimer = 0f;

    private void refreshLeaderboardTable() {
        ArrayList<Player> players = new ArrayList<>(AppClient.getCurrentGame().getPlayers());
        Skin skin = AppClient.getAssets().getSkin();

        // Sort by current type
        switch (currentSortType) {
            case USERNAME -> players.sort(Comparator.comparing(p -> p.getUsername()));
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
            String name = p.getUsername();

            Color rowColor = switch (rank) {
                case 1 -> Color.GOLD;
                case 2 -> Color.LIGHT_GRAY;
                case 3 -> new Color(0.8f, 0.5f, 0.2f, 1); // bronze-ish
                default -> AppClient.getCurrentPlayer().equals(p) ? Color.SKY : Color.WHITE;
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
    public void refreshLeaderBoard(){
        refreshTimer += Gdx.graphics.getDeltaTime();
        if (currentTab == 4 && refreshTimer >= 1f) { // refresh every second
            refreshLeaderboardTable();
            refreshTimer = 0f;
        }
    }

    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
    }

    public void setInventoryWindows(ArrayList<Window> inventoryWindows) {
        this.inventoryWindows = inventoryWindows;
    }

    public void updateInventory() {
        try {
            for (int i = 0; i < 12; i++) {
                ArrayList<Good> goods = AppClient.getCurrentPlayer().getInventory().getList().get(i);
                Quadruple<ImageButton, Image, Label, Label> quadruple = inventoryElements.get(i);
                if (!AppClient.getCurrentPlayer().getInventory().getList().get(i).isEmpty()) {
                    quadruple.b.setDrawable(new TextureRegionDrawable(new Texture(
                        AppClient.getCurrentPlayer().getInventory().getList().get(i).getLast().getType().imagePath()
                    )));
                } else {
                    quadruple.b.setDrawable(new TextureRegionDrawable(new Texture("GameAssets/null.png")));
                }


                if (!gameView.isTabClicked()) {
                    if (i == inHandGoodPtr)
                        quadruple.a.setChecked(true);
                    else
                        quadruple.a.setChecked(false);
                }

                quadruple.d.setText((goods.isEmpty()) ? "" : String.valueOf(goods.size()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        progressBar.setValue(AppClient.getCurrentPlayer().getEnergy().getDayEnergyLeft());
    }

    public void playerChangedInventory() {
        gameView.drawInventory();
        for (int i = 0; i < inventoryElements.size(); i++) {
            Quadruple<ImageButton, Image, Label, Label> quadruple = inventoryElements.get(i);
            if (i == inHandGoodPtr)
                quadruple.a.setChecked(true);
            else
                quadruple.a.setChecked(false);
        }

    }

    public int getInHandGoodPtr() {
        return inHandGoodPtr;
    }

    public void setInHandGoodPtr(int inHandGoodPtr) {
        this.inHandGoodPtr = inHandGoodPtr;
    }

    public ArrayList<Quadruple<ImageButton, Image, Label, Label>> getInventoryElements() {
        return inventoryElements;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public ArrayList<Pair<Pair<ImageButton, Image>, Integer>> getToolsElements() {
        return toolsElements;
    }

    public ArrayList<ImageButton> getMainInventoryElements() {
        return mainInventoryElements;
    }


    public void updatePlayer() {
        int ptr = 0;
        for (Player player : AppClient.getCurrentGame().getPlayers()) {
            if(player.isRenderAble()) {
                Pair<Sprite, Sprite> playerSprite = gameView.getPlayersSprite().get(ptr++);
                playerSprite.first().setPosition(player.getCoordinate().getX() * 40,
                    player.getCoordinate().getY() * 40);
                playerSprite.first().draw(Main.getBatch());

                if (player.getPlayerDirection() != -1) {
                    if (player.getGender().getName().equals("Male")) {
                        animation(player, player.getPlayerDirection(), playerSprite.first());
                    } else {
                        femaleAnimation(player, player.getPlayerDirection(), playerSprite.first());
                    }
                }

                if (!player.getInHandGood().isEmpty())
                    playerSprite.second().setRegion(new Texture(player.getInHandGood().getFirst().getType().imagePath()));
                else
                    playerSprite.second().setRegion(new Texture(AppClient.getAssets().getNullPNGPath()));

                switch (player.getPlayerDirection()) {
                    case 0, 2 -> {
                        playerSprite.second().setPosition(player.getCoordinate().getX() * gameView.getScaledSize(),
                            player.getCoordinate().getY() * gameView.getScaledSize() + 23);
                    }
                    case 1 -> {
                        playerSprite.second().setPosition(player.getCoordinate().getX() * gameView.getScaledSize() - 20,
                            player.getCoordinate().getY() * gameView.getScaledSize() + 23);
                        if (!playerSprite.second().isFlipX())
                            playerSprite.second().flip(true, false);
                    }
                    case 3 -> {
                        playerSprite.second().setPosition(player.getCoordinate().getX() * gameView.getScaledSize() + 20,
                            player.getCoordinate().getY() * gameView.getScaledSize() + 23);
                        if (playerSprite.second().isFlipX())
                            playerSprite.second().flip(true, false);
                    }
                }


                playerSprite.second().setSize(40, 40);
                Main.getBatch().draw(playerSprite.second(),
                    playerSprite.second().getX(), playerSprite.second().getY());
            }
        }
    }

    private void animation(Player player, int i, Sprite playerSprite) {
        Array<Texture> regions = new Array<>(AppClient.getAssets().getPlayerTextures().getFirst().size());
        AppClient.getAssets().getPlayerTextures().get(i).forEach(regions::add);
        Animation<Texture> animation = new Animation<>(0.1f, regions);

        playerSprite.setRegion(animation.getKeyFrame(gameView.getPlayerTime()));

        if(!animation.isAnimationFinished(gameView.getPlayerTime())) {
            gameView.setPlayerTime(gameView.getPlayerTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            gameView.setPlayerTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    private void femaleAnimation(Player player, int i, Sprite playerSprite) {
        Array<Texture> regions = new Array<>(AppClient.getAssets().getFemalePlayerTextures().getFirst().size());
        AppClient.getAssets().getFemalePlayerTextures().get(i).forEach(regions::add);
        Animation<Texture> animation = new Animation<>(0.1f, regions);

        playerSprite.setRegion(animation.getKeyFrame(gameView.getPlayerTime()));

        if(!animation.isAnimationFinished(gameView.getPlayerTime())) {
            gameView.setPlayerTime(gameView.getPlayerTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            gameView.setPlayerTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static java.util.List<Trade> getRespondedTradesFor(Player player) {
        java.util.List<Trade> list = new ArrayList<>();
        for (Trade trade : AppClient.getCurrentGame().getAllTrades()) {
            if (trade.getReceiver().equals(player) && !trade.isResponded()) {
                list.add(trade);
            }
        }
        return list;
    }

    private boolean methodUseMessage(Message responseMessage) {
        //            label.setText("Network error!");
        return !checkMessageValidity(responseMessage, Message.Type.response) || !responseMessage.getBooleanFromBody("success");
    }

    public boolean checkMessageValidity(Message message, Message.Type type) {
        return message.getType() == type;
    }

}
