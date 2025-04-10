package org.example.controllers;

import org.example.models.Result;

public class GameMenuController extends Controller {


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
    //Weather methods
    public Result cheatThunder(Int x, Int y) {
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
    public Result walk(Int x, Int y) {
        //TODO
    }

    public Result printMap(Int x, Int y, Int size) {
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
    public Result cheatEnergySet(Int value) {
        //TODO
    }
    public Result cheatEnergyUnlimited() {
        //TODO
    }
    public Result inventoryTrashItem(String itemName, Int number) {
        //TODO
    }
    public Result inventoryShow() {
        //TODO
    }

    //TODO: Arani

    //TODO: Arani

    //TODO: Parsa
    //Animals & Fishing methods
    public Result buildBuilding(String buildingName, Int x, Int y) {
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
    public Result cheatSetAnimalFriendship(String animalName , Int amount) {
        //TODO
    }
    public Result shepherdAnimal(String animalName, Int x , Int y) {
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

    //TODO: Arani

    //TODO: Parsa
    //Trading methods
    public Result startTrade() {
        //TODO
    }
    public Result tradeWithMoney(String receiver, String tradeType, String Item,
                        Int amount, Int Price) {
        //TODO
    }
    public Result tradeWithGoods(String receiver, String tradeType, String Item,
                        Int amount, String targetItem, Int targetAmount) {
        //TODO
    }
    public Result tradeList() {
        //TODO
    }
    public Result tradeResponse(String response, Int tradeID) {
        //TODO
    }
    public Result tradeHistory() {
        //TODO
    }

}
