package org.example.models.interactions.game_buildings;

import org.example.models.interactions.Building;

public interface GameBuilding extends Building {
    public String showAllProducts();
    public String showAllAvailableProducts();
    public String purchaseProduct(String productName, int quantity);
}
