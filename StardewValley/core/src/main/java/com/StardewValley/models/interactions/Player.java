package com.StardewValley.models.interactions;

import com.StardewValley.models.App;
import com.StardewValley.models.Assets;
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
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Player {
    private Coordinate coordinate ;
    private Inventory inventory ;
    private ArrayList<Good> inHandGood;
    private final ArrayList<CookingRecipe> cookingRecipes = new ArrayList<>(Arrays.asList(new CookingRecipe(CookingRecipeType.BREAD)));
    private final ArrayList<CraftingRecipe> craftingRecipes = new ArrayList<>(Arrays.asList(new CraftingRecipe(CraftingRecipeType.BOMB)));
    private User user;
    private int points;
    private Wallet wallet;
    private Farm farm;
    private Energy energy;
    private Tool trashCan;
    private Skill skill;
    private int playerDirection;
    private Sprite sprite;
    private float time;

    private Buff buff;
    private Buff rejectionBuff;

    // level-value
    private final HashMap<Player, Pair<Integer, Integer>> friendShips = new HashMap<>();
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
        this.user = user;
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
        this.playerDirection = -1;
        this.sprite = new Sprite(Assets.getInstance().getPlayerTextures().get(2).get(0));
        this.sprite.setPosition(coordinate.getX(), coordinate.getY());
    }

    public void iniFriendships(ArrayList<Player> players) {
        for (Player player : players) {
            if(!player.getUser().getUsername().equals(user.getUsername())) {
                this.friendShips.put(player, new Pair<>(0, 0));
                this.isInteracted.put(player, false);
            }
        }
    }

    // Function for eat
    public void eat(Good food) {
        App.getCurrentGame().getCurrentPlayer().getEnergy().increaseTurnEnergyLeft(Good.newGoodType(food.getName()).getEnergy());
        if (food instanceof Food) {
            FoodType type = (FoodType) food.getType();
            Buff currentBuff = type.getBuff();
            if (currentBuff != null) {
                App.getCurrentGame().getCurrentPlayer().setBuff(currentBuff);
                if (currentBuff.getType() == BuffType.ENERGY_BUFF) {
                    App.getCurrentGame().getCurrentPlayer().getEnergy().setDayEnergyLeft(300);
                    App.getCurrentGame().getCurrentPlayer().getEnergy().setMaxDayEnergy(300);
                }
            }
        }
        App.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(food.getType(),1);
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

    public User getUser() {
        return user;
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

    public HashMap<Player, Pair<Integer, Integer>> getFriendShips() {
        return friendShips;
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
            System.out.println(farmBuilding.getName());
            for (Animal animal : farmBuilding.getAnimals()) {
                System.out.println(" "+animal.getAnimalType().getName() + " > " + animal.getName());
                System.out.println("\tFriendShip: " + animal.getFriendship());
                System.out.println("\tPetted: " + animal.isPetted());
                System.out.println("\tFed: " + animal.isFed());
            }
            System.out.println("------------------------------");
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
        return user.getUsername();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateFriendShips(Player player) {
        Pair<Integer, Integer> friendship = friendShips.get(player);
        int points = 0;
        for (int i = 0; i < friendship.first(); i++) {
            points += (i + 1) * 100;
        }

        if(friendship.second() - points >= ((friendship.first() + 1) * 100)) {
            friendShips.computeIfPresent(player,
                    (k, pair) -> new Pair<>(pair.first() + 1, friendship.second()));
            player.getFriendShips().computeIfPresent(this,
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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
