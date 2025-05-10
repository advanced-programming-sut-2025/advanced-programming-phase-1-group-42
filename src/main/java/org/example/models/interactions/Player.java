package org.example.models.interactions;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.game_structure.*;
import org.example.models.goods.Good;
import org.example.models.goods.foods.Food;
import org.example.models.goods.recipes.CookingRecipe;
import org.example.models.goods.recipes.CraftingRecipe;
import org.example.models.goods.tools.Tool;
import org.example.models.goods.tools.ToolType;
import org.example.models.interactions.Animals.Animal;
import org.example.models.interactions.PlayerBuildings.FarmBuilding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Player {
    private Coordinate coordinate;
    private Inventory inventory;
    private Good inHandGood;
    private final ArrayList<CookingRecipe> cookingRecipes = new ArrayList<>();
    private final ArrayList<CraftingRecipe> craftingRecipes = new ArrayList<>();
    private User user;
    private int points;
    private Wallet wallet;
    private Farm farm;
    private Energy energy;
    private Tool trashCan;
    private Skill skill;
    private Buff buff = null;

    // level-value
    private final HashMap<Player, Pair<Integer, Integer>> friendShips = new HashMap<>();
    private final HashMap<Player, Boolean> isInteracted = new HashMap<>();
    private final ArrayList<Pair<Player, String>> talkHistory = new ArrayList<>();

    private final ArrayList<Trade> tradeList = new ArrayList<>();
    private final ArrayList<Trade> tradeHistory = new ArrayList<>();

    private final ArrayList<Pair<Player, Gift>> giftList = new ArrayList<>();
    private final ArrayList<Pair<Player, String>> giftHistory = new ArrayList<>();

    private final ArrayList<String> news = new ArrayList<>();

    private final HashMap<Player, Good> marriageList = new HashMap<>();
    private Player married = null;

    private final ArrayList<Quest> questList = new ArrayList<>();
    private Fridge fridge;

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setInHandGood(Good inHandGood) {
        this.inHandGood = inHandGood;
    }

    public Player(User user) {
        this.user = user;

        this.trashCan = new Tool(ToolType.TRASH_CAN);

        for (Player player : App.getCurrentGame().getPlayers()) {
            if (player != this) {
                friendShips.put(player, new Pair<>(0, 0));
                isInteracted.put(player, false);
            }
        }
    }

    // Function for eat
    public void eat(Food food) {
        App.getCurrentGame().getCurrentPlayer().getEnergy().increaseDayEnergyLeft(food.getEnergy());
        Buff currentBuff = App.getCurrentGame().getCurrentPlayer().getBuff();
        if (currentBuff != null) {
            App.getCurrentGame().getCurrentPlayer().setBuff(currentBuff);
        }
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
        return coordinate;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public Buff getBuff() {
        return buff;
    }

    public Good getInHandGood() {
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
            for (Animal animal : farmBuilding.getAnimals()) {
                System.out.println(animal.getName());
                System.out.println("FriendShips: " + animal.getFriendship());
                System.out.println("Petted: " + animal.isPetted());
                System.out.println("Fed" + animal.isFed());
            }
            System.out.println("------------------------------");
        }
    }
}
