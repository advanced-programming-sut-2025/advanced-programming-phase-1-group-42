package org.example.models.interactions.Animals;

import org.example.models.App;
import org.example.models.goods.Good;
import org.example.models.goods.tools.ToolType;

import java.util.ArrayList;

public class Animal {

    private AnimalTypes animalType;
    private final String name = null;
    private int productCounter = 0;
    private ArrayList<AnimalProduct> products = new ArrayList<>();
    private int friendShip = 0;
    private boolean isPetted = false;
    private boolean isFed = false;
    private AnimalTypes type = null;
    private boolean isOutside = false;
    private boolean wentOutside = false;

    public Animal(AnimalTypes animalType) {
        this.animalType = animalType;
    }

    public String getName() {
        return animalType.getName();
    }

    public ArrayList<AnimalProduct> getProducts() {
        if (animalType.equals(AnimalTypes.COW)) {
            if (App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ToolType.MILK_PAIL)) {
                friendShip += 5;
                return products;
            } else {
                System.out.println("You need Milk Pail to get Milk");
                return null;
            }
        } else if (animalType.equals(AnimalTypes.GOAT)) {
            if (App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ToolType.MILK_PAIL)) {
                friendShip += 5;
                return products;
            } else {
                System.out.println("You need Milk Pail to get Milk");
                return null;
            }
        } else if (animalType.equals(AnimalTypes.SHEEP)) {
            if (App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(ToolType.SHEAR)) {
                friendShip += 5;
                return products;
            } else {
                System.out.println("You need Shear to get Sheep's wool");
                return null;
            }
        }
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

    public void shepherdAnimal() {
        if (App.getCurrentGame().getWeather().getName().equals("Snow")) {
            System.out.println("It's Snowing. You can't feed animals outside");
        } else if (App.getCurrentGame().getWeather().getName().equals("Rain")) {
            System.out.println("It's Raining. You can't feed animals outside");
        } else if (App.getCurrentGame().getWeather().getName().equals("Storm")) {
            System.out.println("It's Stormy. You can't feed animals outside");
        } else {
            if (isOutside){
                isOutside = false;
            } else {
                isOutside = true;
                wentOutside = true;
                isFed = true;
                friendShip += 8; //going outside == feed outside??
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
        return (int) (animalType.getPrice()*((int)(friendShip/1000) + 0.3f));
    }

    // specially for pig
    public void setWentOutside(boolean wentOutside) {
        this.wentOutside = wentOutside;
    }
    public boolean getWentOutside() {
        return wentOutside;
    }


}
