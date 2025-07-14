package org.example.models.interactions.Animals;

import org.example.models.goods.Good;
import org.example.models.goods.GoodLevel;
import org.example.models.goods.GoodType;

import java.util.ArrayList;
import java.util.Random;

public class AnimalDayResultFunctions {

    public static void chicken(Animal animal) {
        animal.setProductCounter();
        ArrayList<AnimalProduct> products = animal.getProductList();
        int counter = animal.getProductCounter();
        if (counter == 1) {
            animal.resetProductCounter();
            animal.getProductList().clear();
            // choose product
            double randomNumberWhichProduct = 0.5 + (Math.random() * 1.0);
            int whichProduct = ((int) (animal.getFriendship() + (150 * randomNumberWhichProduct))) / 1500;

            double randomNumberQuality = Math.random() * 1.0;
            double quality = (animal.getFriendship() / 1000) * (0.5 + 0.5 * randomNumberQuality);
            GoodLevel animalProductQuality = null;

            if (quality <= 0.5) {
                animalProductQuality = GoodLevel.ORDINARY;
            } else if (quality > 0.5 && quality <= 0.7) {
                animalProductQuality = GoodLevel.STEEL;
            } else if (quality > 0.7 && quality <= 0.9) {
                animalProductQuality = GoodLevel.GOLD;
            } else if (quality >= 0.9) {
                animalProductQuality = GoodLevel.IRIDIUM;
            }

            if (whichProduct == 0) {
                products.add(new AnimalProduct(AnimalProductsType.CHICKEN_EGG, animalProductQuality));
            } else if (whichProduct == 1) {
                products.add(new AnimalProduct(AnimalProductsType.BIG_CHICKEN_EGG, animalProductQuality));
            }
        }
        if (!animal.isFed()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
        if (!animal.isPetted()) {
            animal.setFriendship((int) (animal.getFriendship() / 200 - 20));
        }
        animal.setPetted(false);
        animal.setFed(false);
        if (animal.isOutside()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
    }

    public static void duck(Animal animal) {
        animal.setProductCounter();
        ArrayList<AnimalProduct> products = animal.getProductList();
        int counter = animal.getProductCounter();
        if (counter == 2) {
            animal.resetProductCounter();
            animal.getProductList().clear();
            // choose product
            double randomNumberWhichProduct = 0.5 + (Math.random() * 1.0);
            int whichProduct = ((int) (animal.getFriendship() + (150 * randomNumberWhichProduct))) / 1500;

            double randomNumberQuality = Math.random() * 1.0;
            double quality = (animal.getFriendship() / 1000) * (0.5 + 0.5 * randomNumberQuality);
            GoodLevel animalProductQuality = null;

            if (quality <= 0.5) {
                animalProductQuality = GoodLevel.ORDINARY;
            } else if (quality > 0.5 && quality <= 0.7) {
                animalProductQuality = GoodLevel.STEEL;
            } else if (quality > 0.7 && quality <= 0.9) {
                animalProductQuality = GoodLevel.GOLD;
            } else if (quality >= 0.9) {
                animalProductQuality = GoodLevel.IRIDIUM;
            }

            if (whichProduct == 0) {
                products.add(new AnimalProduct(AnimalProductsType.DUCK_EGG, animalProductQuality));
            } else if (whichProduct == 1) {
                products.add(new AnimalProduct(AnimalProductsType.DUCK_FEATHER, animalProductQuality));
            }
        }
        if (!animal.isFed()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
        if (!animal.isPetted()) {
            animal.setFriendship((int) (animal.getFriendship() / 200 - 20));
        }
        animal.setPetted(false);
        animal.setFed(false);
        if (animal.isOutside()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
    }

    public static void rabbit(Animal animal) {
        animal.setProductCounter();
        ArrayList<AnimalProduct> products = animal.getProductList();
        int counter = animal.getProductCounter();
        if (counter == 4) {
            animal.resetProductCounter();
            animal.getProductList().clear();
            // choose product
            double randomNumberWhichProduct = 0.5 + (Math.random() * 1.0);
            int whichProduct = ((int) (animal.getFriendship() + (150 * randomNumberWhichProduct))) / 1500;

            double randomNumberQuality = Math.random() * 1.0;
            double quality = (animal.getFriendship() / 1000) * (0.5 + 0.5 * randomNumberQuality);
            GoodLevel animalProductQuality = null;

            if (quality <= 0.5) {
                animalProductQuality = GoodLevel.ORDINARY;
            } else if (quality > 0.5 && quality <= 0.7) {
                animalProductQuality = GoodLevel.STEEL;
            } else if (quality > 0.7 && quality <= 0.9) {
                animalProductQuality = GoodLevel.GOLD;
            } else if (quality >= 0.9) {
                animalProductQuality = GoodLevel.IRIDIUM;
            }

            if (whichProduct == 0) {
                products.add(new AnimalProduct(AnimalProductsType.RABBIT_WOOL, animalProductQuality));
            } else if (whichProduct == 1) {
                products.add(new AnimalProduct(AnimalProductsType.RABBIT_FOOT, animalProductQuality));
            }
        }
        if (!animal.isFed()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
        if (!animal.isPetted()) {
            animal.setFriendship((int) (animal.getFriendship() / 200 - 20));
        }
        animal.setPetted(false);
        animal.setFed(false);
        if (animal.isOutside()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
    }

    public static void dinosaur(Animal animal) {
        animal.setProductCounter();
        ArrayList<AnimalProduct> products = animal.getProductList();
        int counter = animal.getProductCounter();
        if (counter == 7) {
            animal.resetProductCounter();
            animal.getProductList().clear();

            // choose product
            double randomNumberQuality = Math.random() * 1.0;
            double quality = (animal.getFriendship() / 1000) * (0.5 + 0.5 * randomNumberQuality);
            GoodLevel animalProductQuality = null;

            if (quality <= 0.5) {
                animalProductQuality = GoodLevel.ORDINARY;
            } else if (quality > 0.5 && quality <= 0.7) {
                animalProductQuality = GoodLevel.STEEL;
            } else if (quality > 0.7 && quality <= 0.9) {
                animalProductQuality = GoodLevel.GOLD;
            } else if (quality >= 0.9) {
                animalProductQuality = GoodLevel.IRIDIUM;
            }

            products.add(new AnimalProduct(AnimalProductsType.DINOSAUR_EGG, animalProductQuality));
        }
        if (!animal.isFed()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
        if (!animal.isPetted()) {
            animal.setFriendship((int) (animal.getFriendship() / 200 - 20));
        }
        animal.setPetted(false);
        animal.setFed(false);
        if (animal.isOutside()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
    }

    public static void cow(Animal animal) {
        animal.setProductCounter();
        ArrayList<AnimalProduct> products = animal.getProductList();
        int counter = animal.getProductCounter();
        if (counter == 1) {
            animal.resetProductCounter();
            animal.getProductList().clear();
            // choose product
            double randomNumberWhichProduct = 0.5 + (Math.random() * 1.0);
            int whichProduct = ((int) (animal.getFriendship() + (150 * randomNumberWhichProduct))) / 1500;

            double randomNumberQuality = Math.random() * 1.0;
            double quality = (animal.getFriendship() / 1000) * (0.5 + 0.5 * randomNumberQuality);
            GoodLevel animalProductQuality = null;

            if (quality <= 0.5) {
                animalProductQuality = GoodLevel.ORDINARY;
            } else if (quality > 0.5 && quality <= 0.7) {
                animalProductQuality = GoodLevel.STEEL;
            } else if (quality > 0.7 && quality <= 0.9) {
                animalProductQuality = GoodLevel.GOLD;
            } else if (quality >= 0.9) {
                animalProductQuality = GoodLevel.IRIDIUM;
            }

            if (whichProduct == 0) {
                products.add(new AnimalProduct(AnimalProductsType.COW_MILK, animalProductQuality));
            } else if (whichProduct == 1) {
                products.add(new AnimalProduct(AnimalProductsType.BIG_COW_MILK, animalProductQuality));
            }
        }
        if (!animal.isFed()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
        if (!animal.isPetted()) {
            animal.setFriendship((int) (animal.getFriendship() / 200 - 20));
        }
        animal.setPetted(false);
        animal.setFed(false);
        if (animal.isOutside()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
    }

    public static void goat(Animal animal) {
        animal.setProductCounter();
        ArrayList<AnimalProduct> products = animal.getProductList();
        int counter = animal.getProductCounter();
        if (counter == 1) {
            animal.resetProductCounter();
            animal.getProductList().clear();

            // choose product
            double randomNumberWhichProduct = 0.5 + (Math.random() * 1.0);
            int whichProduct = ((int) (animal.getFriendship() + (150 * randomNumberWhichProduct))) / 1500;

            double randomNumberQuality = Math.random() * 1.0;
            double quality = (animal.getFriendship() / 1000) * (0.5 + 0.5 * randomNumberQuality);
            GoodLevel animalProductQuality = null;

            if (quality <= 0.5) {
                animalProductQuality = GoodLevel.ORDINARY;
            } else if (quality > 0.5 && quality <= 0.7) {
                animalProductQuality = GoodLevel.STEEL;
            } else if (quality > 0.7 && quality <= 0.9) {
                animalProductQuality = GoodLevel.GOLD;
            } else if (quality >= 0.9) {
                animalProductQuality = GoodLevel.IRIDIUM;
            }

            if (whichProduct == 0) {
                products.add(new AnimalProduct(AnimalProductsType.GOAT_MILK, animalProductQuality));
            } else if (whichProduct == 1) {
                products.add(new AnimalProduct(AnimalProductsType.BIG_GOAT_MILK, animalProductQuality));
            }
        }
        if (!animal.isFed()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
        if (!animal.isPetted()) {
            animal.setFriendship((int) (animal.getFriendship() / 200 - 20));
        }
        animal.setPetted(false);
        animal.setFed(false);
        if (animal.isOutside()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
    }

    public static void sheep(Animal animal) {
        animal.setProductCounter();
        ArrayList<AnimalProduct> products = animal.getProductList();
        int counter = animal.getProductCounter();
        if (counter == 1) {
            animal.resetProductCounter();
            animal.getProductList().clear();
            // choose product

            double randomNumberQuality = Math.random() * 1.0;
            double quality = (animal.getFriendship() / 1000) * (0.5 + 0.5 * randomNumberQuality);
            GoodLevel animalProductQuality = null;

            if (quality <= 0.5) {
                animalProductQuality = GoodLevel.ORDINARY;
            } else if (quality > 0.5 && quality <= 0.7) {
                animalProductQuality = GoodLevel.STEEL;
            } else if (quality > 0.7 && quality <= 0.9) {
                animalProductQuality = GoodLevel.GOLD;
            } else if (quality >= 0.9) {
                animalProductQuality = GoodLevel.IRIDIUM;
            }
            products.add(new AnimalProduct(AnimalProductsType.SHEEP_WOOL, animalProductQuality));

        }
        if (!animal.isFed()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
        if (!animal.isPetted()) {
            animal.setFriendship((int) (animal.getFriendship() / 200 - 20));
        }
        animal.setPetted(false);
        animal.setFed(false);
        if (animal.isOutside()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
    }

    public static void pig(Animal animal) {
        animal.setProductCounter();
        ArrayList<AnimalProduct> products = animal.getProductList();
        int counter = animal.getProductCounter();
        if (counter == 1 && animal.getWentOutside()) {
            animal.resetProductCounter();
            animal.getProductList().clear();

            // choose product
            double randomNumberQuality = Math.random() * 1.0;
            double quality = (animal.getFriendship() / 1000) * (0.5 + 0.5 * randomNumberQuality);
            GoodLevel animalProductQuality = null;

            if (quality <= 0.5) {
                animalProductQuality = GoodLevel.ORDINARY;
            } else if (quality > 0.5 && quality <= 0.7) {
                animalProductQuality = GoodLevel.STEEL;
            } else if (quality > 0.7 && quality <= 0.9) {
                animalProductQuality = GoodLevel.GOLD;
            } else if (quality >= 0.9) {
                animalProductQuality = GoodLevel.IRIDIUM;
            }
            products.add(new AnimalProduct(AnimalProductsType.TRUFFLE, animalProductQuality));

        }
        if (!animal.isFed()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
        if (!animal.isPetted()) {
            animal.setFriendship((int) (animal.getFriendship() / 200 - 20));
        }

        animal.setPetted(false);
        animal.setFed(false);
        if (animal.isOutside()) {
            animal.setFriendship(animal.getFriendship() - 20);
        }
        animal.setWentOutside(false);
    }

}
