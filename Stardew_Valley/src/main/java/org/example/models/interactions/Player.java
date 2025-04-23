package org.example.models.interactions;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
import org.example.models.App;
import org.example.models.game_structure.*;
import org.example.models.goods.Good;
import org.example.models.goods.foods.Food;
import org.example.models.goods.recipes.CookingRecipe;
import org.example.models.goods.recipes.CraftingRecipe;
import org.example.models.goods.recipes.Recipe;
import org.example.models.goods.tools.Tool;
=======
=======
>>>>>>> Stashed changes
import org.example.models.game_structure.*;
import org.example.models.goods.Good;
import org.example.models.goods.recipes.CookingRecipe;
import org.example.models.goods.recipes.CraftingRecipe;
import org.example.models.goods.recipes.Recipe;
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
import org.example.models.goods.tools.TrashCan;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private Cordinate cordinate;
    private Inventory inventory;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private Tool currentTool;
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    private ArrayList<CookingRecipe> cookingRecipes;
    private ArrayList<CraftingRecipe> craftingRecipes;
    private User user;
    private int points;
    private Wallet wallet;
    private Farm farm;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private Energy energy ;
    private TrashCan trashCan;
    private Skill skill;
    private Buff buff = null;
=======
=======
>>>>>>> Stashed changes

    private Energy energy;

    private TrashCan trashCan;
    private Skill skill;
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    private HashMap<Player, Integer> friendShipLevel;
    private HashMap<Player, Integer> friendShipScore;
    private HashMap<Player, ArrayList<String>> talkHistory;
    private ArrayList<Trade> tradeList;
    private ArrayList<Trade> tradeHistory;
    private ArrayList<Gift> giftHistory;
    private ArrayList<Quest> questList;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private Fridge fridge;
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes


    public Player(User user) {
        this.user = user;
    }


    // Funciton for walk
    public void walk() {
        //TODO
    }

    // Function for eat
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    public void eat(Food food) {
        App.getCurrentGame().getCurrentPlayingPlayer().getEnergy().energySet(food.getEnergy());
        Buff currentBuff = App.getCurrentGame().getCurrentPlayingPlayer().getBuff();
        if (currentBuff != null) {
            App.getCurrentGame().getCurrentPlayingPlayer().setBuff(currentBuff);
        }
=======
    public void eat() {
        //TODO
>>>>>>> Stashed changes
=======
    public void eat() {
        //TODO
>>>>>>> Stashed changes
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Skill getSkill() {
        return skill;
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
    public Energy getEnergy() {
        return energy;
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
    public int getMaxValueEnergy() {return maxValueEnergy;}
    public void setMaxValueEnergy(int maxValueEnergy) {this.maxValueEnergy = maxValueEnergy;}
>>>>>>> Stashed changes
=======
    public int getMaxValueEnergy() {return maxValueEnergy;}
    public void setMaxValueEnergy(int maxValueEnergy) {this.maxValueEnergy = maxValueEnergy;}
>>>>>>> Stashed changes

    public ArrayList<CraftingRecipe> getCraftingRecipes() {
        return craftingRecipes;
    }

    public ArrayList<CookingRecipe> getCookingRecipes() {
        return cookingRecipes;
    }

    public Cordinate getCordinate() {
        return cordinate;
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
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
=======

>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
}
