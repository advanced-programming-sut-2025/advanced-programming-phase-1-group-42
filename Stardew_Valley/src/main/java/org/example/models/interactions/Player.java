package org.example.models.interactions;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.game_structure.*;
import org.example.models.goods.Good;
import org.example.models.goods.foods.Food;
import org.example.models.goods.recipes.CookingRecipe;
import org.example.models.goods.recipes.CraftingRecipe;
import org.example.models.goods.recipes.Recipe;
import org.example.models.goods.tools.Tool;
import org.example.models.goods.tools.TrashCan;

import java.util.ArrayList;

public class Player {
    private Cordinate cordinate;
    private Inventory inventory;
    private Tool currentTool;
    private ArrayList<CookingRecipe> cookingRecipes;
    private ArrayList<CraftingRecipe> craftingRecipes;
    private User user;
    private int points;
    private Wallet wallet;
    private Farm farm;

    private Energy energy;

    private TrashCan trashCan;
    private Skill skill;
    private Buff buff = null;
    private Pair<Player, Integer> friendShipLevel;
    private Pair<Player, Integer> friendShipScore;
    private Pair<Player, ArrayList<String>> talkHistory;
    private ArrayList<Trade> tradeList;
    private ArrayList<Trade> tradeHistory;
    private ArrayList<Gift> giftHistory;
    private ArrayList<Quest> questList;
    private Fridge fridge;


    public Player(User user) {
        this.user = user;
    }


    // Funciton for walk
    public void walk() {
        //TODO
    }

    // Function for eat
    public void eat(Food food) {
        App.getCurrentGame().getCurrentPlayingPlayer().getEnergy().setDayEnergyLeft(food.getEnergy());
        Buff currentBuff = App.getCurrentGame().getCurrentPlayingPlayer().getBuff();
        if (currentBuff != null) {
            App.getCurrentGame().getCurrentPlayingPlayer().setBuff(currentBuff);
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

    public Tool getCurrentTool() {
        return this.currentTool;
    }

    public Fridge getFridge() {
       return fridge;
    }
}
