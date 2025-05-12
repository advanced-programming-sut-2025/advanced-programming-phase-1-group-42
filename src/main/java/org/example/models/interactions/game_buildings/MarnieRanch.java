package org.example.models.interactions.game_buildings;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.products.Product;
import org.example.models.goods.products.ProductType;
import org.example.models.interactions.Animals.Animal;
import org.example.models.interactions.Animals.AnimalTypes;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.NPCs.NPCTypes;

import java.util.ArrayList;
import java.util.List;

public class MarnieRanch extends GameBuilding {

    public ArrayList<AnimalTypes> animals = new ArrayList<>(
            List.of(AnimalTypes.RABBIT,
                    AnimalTypes.COW,
                    AnimalTypes.GOAT,
                    AnimalTypes.CHICKEN,
                    AnimalTypes.DINOSAUR,
                    AnimalTypes.DUCK,
                    AnimalTypes.SHEEP,
                    AnimalTypes.PIG)
    );

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, 45, 85);
    }

    public MarnieRanch(ArrayList<Tile> tiles) {
        super(tiles,
                "MarineRanch",
                new NPC(NPCTypes.MARNIE),
                new Pair<>(9, 16),
                new Coordinate(45, 85),
                new Coordinate(65, 105));
    }


    @Override
    public String showAllProducts() {
        for (AnimalTypes animalType : animals) {
            System.out.println(animalType.getName() + "  " + animalType.getPrice()+" G");
        }
        return null;
    }

    @Override
    public String showProducts() {
        return "";
    }

    @Override
    public Result purchase(String productName, String count) {
        int countInt = Integer.parseInt(count);
        ProductType type = null;
        if (productName.equals("Hay")) {
            type = ProductType.HAY;
            if (!(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() > countInt * type.getSellPrice())) {
                return new Result(false, "You don't have enough money");
            } else {
                if (!App.getCurrentGame().getCurrentPlayer().getInventory().addGood((Good) new Product(ProductType.WOOD), countInt)) {
                    return new Result(false, "Your inventory is full");
                } else {
                    App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(countInt * type.getSellPrice());
                }
                return new Result(true, "You bought " + countInt + " Wood");
            }
        } else if (productName.equals("Shears")) {
            type = ProductType.SHEARS;
            if (!(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() > countInt * type.getSellPrice())) {
                return new Result(false, "You don't have enough money");
            } else {
                if (!App.getCurrentGame().getCurrentPlayer().getInventory().addGood((Good) new Product(ProductType.WOOD), countInt)) {
                    return new Result(false, "Your inventory is full");
                } else {
                    App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(countInt * type.getSellPrice());
                }
                return new Result(true, "You bought " + countInt + " Wood");
            }
        } else if (productName.equals("Milk Pail")) {
            type = ProductType.MILK_PAIL;
            if (!(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() > countInt * type.getSellPrice())) {
                return new Result(false, "You don't have enough money");
            } else {
                if (!App.getCurrentGame().getCurrentPlayer().getInventory().addGood((Good) new Product(ProductType.WOOD), countInt)) {
                    return new Result(false, "Your inventory is full");
                } else {
                    App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(countInt * type.getSellPrice());
                }
                return new Result(true, "You bought " + countInt + " Wood");
            }
        } else {
            return new Result(false, "This item is not available in this shop");
        }
    }

    public AnimalTypes getAnimalType(String animalType) {
        for (AnimalTypes type : animals) {
            if (type.name().equals(animalType)) {
                return type;
            }
        }
        return null;
    }

    public Animal buildAnimal(AnimalTypes animalType,String animalName) {
        return new Animal(animalType,animalName);
    }
}
