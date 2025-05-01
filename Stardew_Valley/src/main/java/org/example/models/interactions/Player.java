package org.example.models.interactions;

import org.example.models.App;
import org.example.models.game_structure.*;
import org.example.models.goods.Good;
import org.example.models.goods.foods.Food;
import org.example.models.goods.recipes.CookingRecipe;
import org.example.models.goods.recipes.CraftingRecipe;
import org.example.models.goods.tools.Tool;
import org.example.models.goods.tools.ToolType;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private Cordinate cordinate;
    private Inventory inventory;
    private Good inHandGood;
    private ArrayList<CookingRecipe> cookingRecipes;
    private ArrayList<CraftingRecipe> craftingRecipes;
    private User user;
    private int points;
    private Wallet wallet;
    private Farm farm;
    private Energy energy;
    private Tool trashCan;
    private Skill skill;
    private Buff buff = null;
    private HashMap<Player, Integer> friendShipLevel;
    private HashMap<Player, Integer> friendShipScore;
    private HashMap<Player, ArrayList<String>> talkHistory;
    private ArrayList<Trade> tradeList;
    private ArrayList<Trade> tradeHistory;
    private ArrayList<Gift> giftHistory;
    private ArrayList<Quest> questList;
    private Fridge fridge;


    // Funciton for walk
    public void walk() {
        //TODO
    }

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

    public Cordinate getCordinate() {
        return cordinate;
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
}
