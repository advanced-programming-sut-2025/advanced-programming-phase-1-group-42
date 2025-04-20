package org.example.controllers;

import org.example.models.App;
import org.example.models.Game;
import org.example.models.Result;
import org.example.models.game_structure.Map;
import org.example.models.interactions.Player;
import org.example.models.interactions.User;

public class GameMenuController extends Controller {


    //game setting methods

    //Nader
    public Result newGame(String username_1, String username_2, String username_3) {
        if (username_1 == null || username_2 == null || username_3 == null) {
            return new Result(false, "You need to add 3 players to start the game");
        }
        Player mainPlayer = new Player(App.getCurrentUser());
        Player player1 = null;
        Player player2 = null;
        Player player3 = null;
        for (User user : App.users) {
            if (user.getUsername().equals(username_1)) {
                if (user.getPlaying().equals(true)) {
                    return new Result(false, "This user is playing in another game");
                } else {
                    player1 = new Player(user);
                }
            } else if (user.getUsername().equals(username_2)) {
                if (user.getPlaying().equals(true)) {
                    return new Result(false, "This user is playing in another game");
                } else {
                    player2 = new Player(user);
                }
            } else if (user.getUsername().equals(username_3)) {
                if (user.getPlaying().equals(true)) {
                    return new Result(false, "This user is playing in another game");
                } else {
                    player3 = new Player(user);
                }
            }
        }

        if (player1 == null || player2 == null || player3 == null) {
            return new Result(false, "Added users are unavailable");
        }

        App.setCurrentGame(new Game(App.getCurrentUser()));
        return new Result(true, "Users have been added to the game successfully!");
    }

    public Result mapGame(String mapNumber) {
        int mapNumberInt = Integer.parseInt(mapNumber.trim());
        for (Map map : App.maps) {
            if (map.getMapNumber() == mapNumberInt) {
                App.getCurrentGame().setCurrentMap(map);
                return new Result(true, "Map number " + mapNumber + " has been selected");
            }
        }
        return new Result(false, "Map number is unavailable");
    }

    public Result loadGame() {
        //TODO
    }

    public Result exitGame() {
        if (App.getCurrentGame().getGameCreator() != App.getCurrentUser()) {
            return new Result(false, "Just game creator can exit the game!");
        }
    }

    public Result nextTurn() {
        App.getCurrentGame().nextPlayer();
    }


    // date & time methods
    public Result time() {
        //TODO
    }

    public Result date() {
        //TODO
    }

    public Result dateTime() {
        //TODO
    }

    public Result dayOfTheWeek() {
        //TODO
    }

    public Result showSeason() {
        //TODO
    }

    public Result cheatAdvanceTime(String hour) {
        //TODO
    }

    public Result cheatAdvanceDate(String date) {
        //TODO
    }


    // crafting methods

    public Result showCraftingRecipes() {
        //TODO
    }

    public Result craftingCraft(String itemName) {
        //TODO
    }

    public Result placeItem(String itemName, String direction) {
        //TODO
    }

    public Result cheatAddItem(String itemName, String count) {
        //TODO
    }


    //cooking methods

    public Result cookingRefrigerator(String status, String itemName) {
        //TODO
    }

    public Result showCookingRecipes() {
        //TODO
    }

    public Result cookingPrepare(String recipeName) {
        //TODO
    }

    public Result eat(String foodName) {
        //TODO
    }


    // artisan methods

    public Result artisanUse(String artisanName) {
        //TODO
    }

    public Result artisanGet(String artisanName) {
        //TODO
    }


    // buy & sell methods

    public Result showAllProducts() {
        //TODO
    }

    public Result showAllAvailableProducts() {
        //TODO
    }

    public Result purchase(String productName, String count) {
        //TODO
    }

    public Result cheatAddDollars(String count) {
        //TODO
    }

    public Result sell(String productName, String count) {
        //TODO
    }


    // NPC methods

    public Result meetNPC(String npcName) {
        //TODO
    }

    public Result giftNPC(String npcName, String itemName) {
        //TODO
    }

    public Result friendshipNPCList() {
        //TODO
    }

    public Result questsList() {
        //TODO
    }

    public Result questsFinish(String index) {
        //TODO
    }


    //TODO: Parsa

    //TODO: Parsa

    //TODO: Parsa

    //TODO: Arani

    //TODO: Arani

    //TODO: Parsa

    //TODO: Arani

    //TODO: Parsa


}
