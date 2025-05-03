package org.example.models.interactions.Animals;

public enum AnimalTypes {
    CHICKEN("Chicken",800),
    DUCK("Duck" , 1200),
    RABBIT("Rabbit" , 8000),
    DINOSAUR("Dinosaur",14000),
    COW("Cow",1500),
    GOAT("Goat",4000),
    SHEEP("Sheep",8000),
    PIG("Pig", 16000);
    private String name;
    private int price;

    AnimalTypes(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
}
