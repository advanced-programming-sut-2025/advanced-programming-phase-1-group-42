package org.example.models.interactions;

import org.example.models.game_structure.*;
import org.example.models.goods.Good;
import org.example.models.goods.recipes.CookingRecipe;
import org.example.models.goods.recipes.CraftingRecipe;
import org.example.models.goods.recipes.Recipe;
import org.example.models.goods.tools.TrashCan;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private Cordinate cordinate;
    private Inventory inventory;
    private ArrayList<CookingRecipe> cookingRecipes;
    private ArrayList<CraftingRecipe> craftingRecipes;
    private User user;
    private int points;
    private Wallet wallet;
    private Farm farm;
    private Energy energy;
    private TrashCan trashCan;
    private Skill skill;
    private HashMap<Player, Integer> friendShipLevel;
    private HashMap<Player, Integer> friendShipScore;
    private HashMap<Player, ArrayList<String>> talkHistory;
    private ArrayList<Trade> tradeList;
    private ArrayList<Trade> tradeHistory;
    private ArrayList<Gift> giftHistory;
    private ArrayList<Quest> questList;


    public Player(User user) {
        this.user = user;
    }



    // Funciton for walk
    public void walk() {
        //TODO
    }

    // Function for eat
    public void eat() {
        //TODO
    }


}
