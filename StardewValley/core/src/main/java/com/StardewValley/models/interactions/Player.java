package com.StardewValley.models.interactions;

import com.StardewValley.models.Pair;
import com.StardewValley.models.game_structure.*;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.foods.Food;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.recipes.CookingRecipe;
import com.StardewValley.models.goods.recipes.CookingRecipeType;
import com.StardewValley.models.goods.recipes.CraftingRecipe;
import com.StardewValley.models.goods.recipes.CraftingRecipeType;
import com.StardewValley.models.goods.tools.Tool;
import com.StardewValley.models.goods.tools.ToolType;
import com.StardewValley.models.interactions.Animals.Animal;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuilding;
import com.StardewValley.server.ClientHandler;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.out;

public class Player {
    private Coordinate coordinate ;
    private Coordinate lastCoordinate;
    private Inventory inventory ;
    private ArrayList<Good> inHandGood;
    private final ArrayList<CookingRecipe> cookingRecipes = new ArrayList<>(Arrays.asList(new CookingRecipe(CookingRecipeType.BREAD)));
    private final ArrayList<CraftingRecipe> craftingRecipes = new ArrayList<>(Arrays.asList(new CraftingRecipe(CraftingRecipeType.BOMB)));
    private String username;
    private Gender gender;
    private int points;
    private Wallet wallet;
    private Farm farm;
    private Energy energy;
    private Tool trashCan;
    private Skill skill;
    private int playerDirection;
    private String spritePath;
    private String inHandGoodSpritePath;
    private float time;
    private ArrayList<Pair<Player, ArrayList<String>>> privateChat = new ArrayList<>();
    private Buff buff;
    private Buff rejectionBuff;
    private ArrayList<Quest> playerQuests = new ArrayList<>();

    private boolean renderAble = true;
    // level-value
//    @JsonAdapter(ObjectOrStringMapAdapter.class)
    private final HashMap<String, Pair<Integer, Integer>> friendShips = new HashMap<>();
    private final HashMap<Player, Boolean> isInteracted;
    private final ArrayList<Pair<Player, String>> talkHistory = new ArrayList<>();

    private final ArrayList<Pair<Player, Gift>> giftList = new ArrayList<>();
    private final ArrayList<Pair<Player, String>> giftHistory = new ArrayList<>();

    private final List<Trade> sentTrades = new ArrayList<>();
    private final List<Trade> receivedTrades = new ArrayList<>();

    private final ArrayList<String> news = new ArrayList<>();

    private final HashMap<Player, Good> marriageList = new HashMap<>();
    private Player married = null;

    private final ArrayList<Quest> questList = new ArrayList<>();
    private Fridge fridge = new Fridge();

    private ArrayList<Pair<Integer, Good>> artisansGoodTime = new ArrayList<>();

    public void setFarm(Farm farm) {
        this.farm = farm;
        this.coordinate = new Coordinate(farm.getFarmBuildings().getFirst().getStartCordinate().getX() + 5,
                farm.getFarmBuildings().getFirst().getStartCordinate().getY() + 2);
        this.inHandGood = inventory.getList().getFirst();
    }

    public Farm getFarm() {
        return farm;
    }

    public void setInHandGood(ArrayList<Good> inHandGood) {
        this.inHandGood = inHandGood;
    }

    public Player(User user) {
        this.username = user.getUsername();
        this.gender = user.getGender();
        this.wallet =  new Wallet(0);
        this.points = 0;
        this.energy = new Energy();
        this.trashCan = new Tool(ToolType.TRASH_CAN);
        this.inventory = new Inventory();
        this.skill = new Skill();
        this.inHandGood = null;
        this.buff = null;
        this.rejectionBuff = null;
        this.farm = null;
        this.isInteracted = new HashMap<>();
        this.coordinate = new Coordinate(0, 0);
        this.lastCoordinate = coordinate;
        this.playerDirection = -1;
        if (user.getGender() != null && "Male".equals(user.getGender().getName())) {
            this.spritePath = playerTextureStrings.get(2).getFirst();
        } else {
            this.spritePath = femalePlayerTextureStrings.get(2).getFirst();
        }

        this.inHandGoodSpritePath = nullPNGPath;
//        this.inHandGoodSpritePath.setPosition(coordinate.getX() * 40,
//            coordinate.getY() * 40);
    }

    public void iniFriendships(ArrayList<Player> players) {
        for (Player player : players) {
            if(!player.getUsername().equals(username)) {
                this.friendShips.put(player.getUsername(), new Pair<>(0, 0));
                this.isInteracted.put(player, false);
            }
        }
    }

    // Function for eat
    public void eat(Good food, ClientHandler clientHandler) {
        clientHandler.getClientPlayer().getEnergy().increaseTurnEnergyLeft(Good.newGoodType(food.getName()).getEnergy());
        if (food instanceof Food) {
            FoodType type = (FoodType) food.getType();
            Buff currentBuff = type.getBuff();
            if (currentBuff != null) {
                clientHandler.getClientPlayer().setBuff(currentBuff);
                if (currentBuff.getType() == BuffType.ENERGY_BUFF) {
                    clientHandler.getClientPlayer().getEnergy().setDayEnergyLeft(300);
                    clientHandler.getClientPlayer().getEnergy().setMaxDayEnergy(300);
                }
            }
        }
        clientHandler.getClientPlayer().getInventory().removeItemsFromInventory(food.getType(),1);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Skill getSkill() {
        return skill;
    }


    public Energy getEnergy() {
        return energy;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<CraftingRecipe> getCraftingRecipes() {
        return craftingRecipes;
    }

    public ArrayList<CookingRecipe> getCookingRecipes() {
        return cookingRecipes;
    }

    public Coordinate getCoordinate() {
        int x = this.coordinate.getX();
        int y = this.coordinate.getY();
        return new Coordinate(x, y);
    }

    public Coordinate getLastCoordinate() {
        int x = this.lastCoordinate.getX();
        int y = this.lastCoordinate.getY();
        return new Coordinate(x, y);
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public Buff getBuff() {
        return buff;
    }

    public ArrayList<Good> getInHandGood() {
        return this.inHandGood;
    }

    public Fridge getFridge() {
       return fridge;
    }

    public Wallet getWallet() {
        return wallet;
    }


    public HashMap<Player, Boolean> getIsInteracted() {
        return isInteracted;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ArrayList<Pair<Player, String>> getTalkHistory() {
        return talkHistory;
    }

    public ArrayList<Pair<Player, Gift>> getGiftList() {
        return giftList;
    }

    public ArrayList<Pair<Player, String>> getGiftHistory() {
        return giftHistory;
    }

    public ArrayList<String> getNews() {
        return news;
    }

    public HashMap<Player, Good> getMarriageList() {
        return marriageList;
    }

    public Player getMarried() {
        return married;
    }

    public void setMarried(Player married) {
        this.married = married;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Tool getTrashCan() {
        return trashCan;
    }

    public void showAnimals(){
        for (FarmBuilding farmBuilding : farm.getFarmBuildings()) {
            out.println(farmBuilding.getName());
            for (Animal animal : farmBuilding.getAnimals()) {
                out.println(" "+animal.getAnimalType().getName() + " > " + animal.getName());
                out.println("\tFriendShip: " + animal.getFriendship());
                out.println("\tPetted: " + animal.isPetted());
                out.println("\tFed: " + animal.isFed());
            }
            out.println("------------------------------");
        }
    }

    public void addSentTrade(Trade trade) {
        sentTrades.add(trade);
    }

    public void addReceivedTrade(Trade trade) {
        receivedTrades.add(trade);
    }

    public List<Trade> getReceivedTrades() {
        return receivedTrades;
    }

    public List<Trade> getTradeHistory() {
        List<Trade> all = new ArrayList<>(sentTrades);
        all.addAll(receivedTrades);
        return all;
    }


    public int getPoints() {
        return points;
    }

    public Buff getRejectionBuff() {
        return rejectionBuff;
    }

    public void setRejectionBuff(Buff rejectionBuff) {
        this.rejectionBuff = rejectionBuff;
    }

    public String getPlayerUsername(){
        return getUsername();
    }



    public void updateFriendShips(Player player) {
        Pair<Integer, Integer> friendship = friendShips.get(player.getUsername());
        int points = 0;
        for (int i = 0; i < friendship.first(); i++) {
            points += (i + 1) * 100;
        }

        if(friendship.second() - points >= ((friendship.first() + 1) * 100)) {
            friendShips.computeIfPresent(player.getUsername(),
                    (k, pair) -> new Pair<>(pair.first() + 1, friendship.second()));
            player.friendShips.computeIfPresent(this.getUsername(),
                    (k, pair) -> new Pair<>(pair.first() + 1, friendship.second()));
        }
//        else if(friendship.second() < points) {
//            if(friendship.first() > 0) {
//                friendShips.computeIfPresent(player,
//                        (k, pair) -> new Pair<>(pair.first() - 1, friendship.second()));
//                player.getFriendShips().computeIfPresent(this,
//                        (k, pair) -> new Pair<>(pair.first() - 1, friendship.second()));
//            }
//        }
    }

    public ArrayList<Pair<Integer, Good>> getArtisansGoodTime() {
        return artisansGoodTime;
    }

    public void setArtisansGoodTime(ArrayList<Pair<Integer, Good>> artisansGoodTime) {
        this.artisansGoodTime = artisansGoodTime;
    }

    public int getPlayerDirection() {
        return playerDirection;
    }

    public void setPlayerDirection(int playerDirection) {
        this.playerDirection = playerDirection;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setLastCoordinate(Coordinate lastCoordinate) {
        this.lastCoordinate = lastCoordinate;
    }

    public ArrayList<Pair<Player, ArrayList<String>>> getPrivateChat() {
        return privateChat;
    }

    public ArrayList<Quest> getPlayerQuests() {
        return playerQuests;
    }

    public boolean isRenderAble() {
        return renderAble;
    }

    public void setRenderAble(boolean renderAble) {
        this.renderAble = renderAble;
    }

    public Gender getGender() {
        return gender;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public String getInHandGoodSpritePath() {
        return inHandGoodSpritePath;
    }

    public void setInHandGoodSpritePath(String inHandGoodSpritePath) {
        this.inHandGoodSpritePath = inHandGoodSpritePath;
    }

    private final ArrayList<ArrayList<String>> playerTextureStrings = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Male/Male_walk (10).png",
            "GameAssets/Player_Movement/Male/Male_walk (11).png",
            "GameAssets/Player_Movement/Male/Male_walk (12).png",
            "GameAssets/Player_Movement/Male/Male_walk (9).png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Male/Male_walk (14).png",
            "GameAssets/Player_Movement/Male/Male_walk (15).png",
            "GameAssets/Player_Movement/Male/Male_walk (16).png",
            "GameAssets/Player_Movement/Male/Male_walk (13).png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Male/Male_walk (2).png",
            "GameAssets/Player_Movement/Male/Male_walk (3).png",
            "GameAssets/Player_Movement/Male/Male_walk (4).png",
            "GameAssets/Player_Movement/Male/Male_walk (1).png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Male/Male_walk (6).png",
            "GameAssets/Player_Movement/Male/Male_walk (7).png",
            "GameAssets/Player_Movement/Male/Male_walk (8).png",
            "GameAssets/Player_Movement/Male/Male_walk (5).png"
        ))
    ));
    private final ArrayList<ArrayList<String>> femalePlayerTextureStrings = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Female/Untitled-7.png",
            "GameAssets/Player_Movement/Female/Untitled-8.png",
            "GameAssets/Player_Movement/Female/Untitled-7.png",
            "GameAssets/Player_Movement/Female/Untitled-9.png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Female/Untitled-4.png",
            "GameAssets/Player_Movement/Female/Untitled-5.png",
            "GameAssets/Player_Movement/Female/Untitled-4.png",
            "GameAssets/Player_Movement/Female/Untitled-6.png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Female/Untitled-1.png",
            "GameAssets/Player_Movement/Female/Untitled-2.png",
            "GameAssets/Player_Movement/Female/Untitled-1.png",
            "GameAssets/Player_Movement/Female/Untitled-3.png"
        )),
        new ArrayList<>(Arrays.asList(
            "GameAssets/Player_Movement/Female/Untitled-10.png",
            "GameAssets/Player_Movement/Female/Untitled-11.png",
            "GameAssets/Player_Movement/Female/Untitled-10.png",
            "GameAssets/Player_Movement/Female/Untitled-12.png"
        ))
    ));

    private final String nullPNGPath = "GameAssets/null.png";

    public HashMap<String, Pair<Integer, Integer>> getFriendShips() {
        return friendShips;
    }
}


//// Adapter
//final class ObjectOrStringMapAdapter extends TypeAdapter<Map<String,Integer>> {
//    private static final Gson G = new com.google.gson.Gson();
//    private static final Type T =
//        new TypeToken<Map<String,Integer>>(){}.getType();
//
//    @Override
//    public void write(JsonWriter jsonWriter, Map<String, Integer> value) throws IOException {
//        G.getAdapter(TypeToken.get(T)).write(out, value);
//    }
//
//    @Override public Map<String,Integer> read(com.google.gson.stream.JsonReader in)
//        throws java.io.IOException {
//        com.google.gson.stream.JsonToken tok = in.peek();
//        if (tok == com.google.gson.stream.JsonToken.STRING) {
//            String s = in.nextString();
//            return G.fromJson(s, T); // parse inner JSON string
//        }
//        return G.fromJson(in, T);   // normal object
//    }
//}

