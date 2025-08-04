package com.StardewValley.models.interactions.Animals;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuilding;

import java.util.ArrayList;

public class Animal {

    private AnimalTypes animalType;
    private final String name;
    private int productCounter = 0;
    private ArrayList<AnimalProduct> products = new ArrayList<>();
    private int friendShip = 0;
    private boolean isPetted = false;
    private int petCounter = -1;
    private boolean isFed = false;
    private AnimalTypes type = null;
    private boolean isOutside = false;
    private boolean wentOutside = false;
    private Coordinate coordinate = null;
    private FarmBuilding locatedPLace = null;

    public Animal(AnimalTypes animalType, String name) {
        this.animalType = animalType;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public ArrayList<AnimalProduct> getProducts() {
        if (animalType.equals(AnimalTypes.COW)) {
            if (AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventoryBoolean(ProductType.MILK_PAIL)) {
                friendShip += 5;
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ProductType.MILK_PAIL, 1);
                AppClient.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(7);
                return products;
            } else {
                System.out.println("You need Milk Pail to get Milk");
                return null;
            }
        } else if (animalType.equals(AnimalTypes.GOAT)) {
            if (AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventoryBoolean(ProductType.MILK_PAIL)) {
                friendShip += 5;
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ProductType.MILK_PAIL, 1);
                AppClient.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(7);
                return products;
            } else {
                System.out.println("You need Milk Pail to get Milk");
                return null;
            }
        } else if (animalType.equals(AnimalTypes.SHEEP)) {
            if (AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventoryBoolean(ProductType.SHEARS)) {
                friendShip += 5;
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().removeItemsFromInventory(ProductType.SHEARS, 1);
                AppClient.getCurrentGame().getCurrentPlayer().getEnergy().decreaseTurnEnergyLeft(7);
                return products;
            } else {
                System.out.println("You need Shear to get Sheep's wool");
                return null;
            }
        } else if (animalType.equals(AnimalTypes.PIG)) {
            if (!isOutside) {
                System.out.println("You need to bring Pig outSide to get Products");
            } else {
                return products;
            }
        }
        return products;
    }

    public ArrayList<AnimalProduct> getProductList() {
        return products;
    }

    public boolean isPetted() {
        return isPetted;
    }

    public void setPetted(boolean isPetted) {
        this.isPetted = isPetted;
    }

    public boolean isFed() {
        return isFed;
    }

    public void setFed(boolean isFed) {
        this.isFed = isFed;
    }

    public void shepherdAnimal(Coordinate coordinate) {
        if (AppClient.getCurrentGame().getWeather().getName().equals("Snow")) {
            System.out.println("It's Snowing. You can't feed animals outside");
        } else if (AppClient.getCurrentGame().getWeather().getName().equals("Rain")) {
            System.out.println("It's Raining. You can't feed animals outside");
        } else if (AppClient.getCurrentGame().getWeather().getName().equals("Storm")) {
            System.out.println("It's Stormy. You can't feed animals outside");
        } else {
            if (isOutside) {
                isOutside = false;
            } else {
                isOutside = true;
                wentOutside = true;
                isFed = true;
                friendShip += 8; //going outside == feed outside??
                this.coordinate = coordinate;
            }
        }
    }

    public int getProductCounter() {
        return productCounter;
    }

    public void setProductCounter() {
        this.productCounter++;
    }

    public void resetProductCounter() {
        this.productCounter = 0;
    }

    public void animalDayResult() {
        switch (animalType) {
            case CHICKEN -> AnimalDayResultFunctions.chicken(this);
            case DUCK -> AnimalDayResultFunctions.duck(this);
            case RABBIT -> AnimalDayResultFunctions.rabbit(this);
            case DINOSAUR -> AnimalDayResultFunctions.dinosaur(this);
            case COW -> AnimalDayResultFunctions.cow(this);
            case GOAT -> AnimalDayResultFunctions.goat(this);
            case SHEEP -> AnimalDayResultFunctions.sheep(this);
            case PIG -> AnimalDayResultFunctions.pig(this);
        }
    }

    public void petAnimal() {
        isPetted = true;
        petCounter = 0;
        friendShip += 15;
    }

    public int getFriendship() {
        if (friendShip <= 0) {
            friendShip = 0;
        }
        return Math.min(1000, friendShip);
    }

    public void setFriendship(int friendship) {
        this.friendShip = friendship;
    }

    public boolean isOutside() {
        return isOutside;
    }

    public int getAnimalSellPrice() {
        return (int) (animalType.getPrice() * ((int) (friendShip / 1000) + 0.3f));
    }

    // specially for pig
    public void setWentOutside(boolean wentOutside) {
        this.wentOutside = wentOutside;
    }

    public boolean getWentOutside() {
        return wentOutside;
    }

    public void showProducts() {
        System.out.println(" " + animalType.getName() + " " + name + " >");
        for (AnimalProduct product : products) {
            System.out.println("\t" + product.getName() + " " + product.getQuality());
        }
        System.out.println("------------------------------------");
    }

    public void setLocatedPLace(FarmBuilding locatedPLace) {
        this.locatedPLace = locatedPLace;
    }

    public FarmBuilding getLocatedPLace() {
        return locatedPLace;
    }

    public AnimalTypes getAnimalType() {
        return animalType;
    }

    public void updateCounter() {
        if (petCounter >= 0) {
            petCounter++;
            if (petCounter >= 3) {
                petCounter = -1;
            }
        }
    }

    public int getPetCounter() {
        return petCounter;
    }


}
