package org.example.controllers;

import org.example.models.Result;

public class GameMenuController extends Controller {

    //TODO: Nader
    //game setting methods
    public Result newGame(String username_1, String username_2, String username_3) {
        //TODO
    }

    public Result mapGame(String mapNumber) {
        //TODO
    }

    public Result loadGame() {
        //TODO
    }

    public Result exitGame() {
        //TODO
    }

    public Result nextTurn() {
        //TODO
    }


    //TODO: Nader
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


    //TODO: Parsa
    //Weather methods
    public Result cheatThunder(String x, String y) {
        //TODO
    }

    public Result weather() {
        //TODO
    }

    public Result weatherForecast() {
        //TODO
    }

    public Result cheatWeatherSet(String weather) {
        //TODO
    }

    public Result greenHouseBuild() {
        //TODO
    }


    //TODO: Parsa
    //Map methods
    public Result walk(String x, String y) {
        //TODO
    }

    public Result printMap(String x, String y, String size) {
        //TODO
    }

    public Result helpReadingMap() {
        //TODO
    }

    //TODO: Parsa
    //inventory & Energy methods
    public Result energyShow() {
        //TODO
    }

    public Result cheatEnergySet(String value) {
        //TODO
    }

    public Result cheatEnergyUnlimited() {
        //TODO
    }

    public Result inventoryTrashItem(String itemName, String number) {
        //TODO
    }

    public Result inventoryShow() {
        //TODO
    }

    //TODO: Arani
    // Tools
    public Result toolsEquipment(String toolName) {
        //TODO
    }

    public Result toolsShowCurrent() {
        //TODO
    }

    public Result toolsShowAvailable() {
        //TODO
    }

    public Result toolsUpgrade(String toolName) {
        //TODO
    }

    public Result toolsUse(String direction) {
        //TODO
    }

    //TODO: Arani
    // Craft Info
    public Result craftInfo(String craftName) {
        //TODO
    }


    //TODO: Arani
    // Planting
    public Result plantSeed(String seed, String direction) {
        //TODO
    }

    public Result showPlant(String direction) {
        //TODO
    }

    public Result fertilize(String fertilizer, String direction) {
        //TODO
    }

    public Result howMuchWater() {
        //TODO
    }


    //TODO: Nader
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


    //TODO: Nader
    // cooking methods
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


    //TODO: Parsa
    // Animals & Fishing methods
    public Result buildBuilding(String buildingName, String x, String y) {
        //TODO
    }

    public Result buyAnimal(String animalType, String animalName) {
        //TODO
    }

    public Result petAnimal(String animalName) {
        //TODO
    }

    public Result animalList() {
        //TODO
    }

    public Result cheatSetAnimalFriendship(String animalName , String amount) {
        //TODO
    }

    public Result shepherdAnimal(String animalName, String x , String y) {
        //TODO
    }

    public Result feedHay(String animalName) {
        //TODO
    }

    public Result animalProductionList() {
        //TODO
    }

    public Result collectProduct(String animalName) {
        //TODO
    }

    public Result sellAnimal(String animalName) {
        //TODO
    }

    public Result fishing(String fishingPole) {
        //TODO
    }


    //TODO: Nader
    // artisan methods
    public Result artisanUse(String artisanName) {
        //TODO
    }

    public Result artisanGet(String artisanName) {
        //TODO
    }

    //TODO: Nader
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

    //TODO: Arani
    // Friendships methods
    public Result friendships() {
        //TODO
    }

    public Result talk(String username, String message) {
        //TODO
    }

    public Result talkHistory(String username) {
        //TODO
    }

    public Result gift(String username, String item, String amount) {
        //TODO
    }

    public Result giftList() {
        //TODO
    }

    public Result giftRate(String giftNumber, String rate) {
        //TODO
    }

    public Result giftHistory(String username) {
        //TODO
    }

    public Result hug(String username) {
        //TODO
    }

    public Result flower(String username) {
        //TODO
    }

    public Result askMarriage(String username, String ring) {
        //TODO
    }

    public Result respond(String status, String username) {
        //TODO
    }


    //TODO: Parsa
    // Trading methods
    public Result startTrade() {
        //TODO
    }

    public Result tradeWithMoney(String receiver, String tradeType, String Item,
                                 String amount, String Price) {
        //TODO
    }

    public Result tradeWithGoods(String receiver, String tradeType, String Item,
                                 String amount, String targetItem, String targetAmount) {
        //TODO
    }

    public Result tradeList() {
        //TODO
    }

    public Result tradeResponse(String response, String tradeID) {
        //TODO
    }

    public Result tradeHistory() {
        //TODO
    }

    //TODO: Nader
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


}
