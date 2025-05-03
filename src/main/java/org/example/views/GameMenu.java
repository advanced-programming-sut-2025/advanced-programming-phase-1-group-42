package org.example.views;

import org.example.controllers.GameMenuController;
import org.example.controllers.ProfileMenuController;
import org.example.models.enums.GameMenuCommands;
import org.example.models.enums.LoginRegisterCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {

    private final GameMenuController controller = new GameMenuController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();

    Matcher matcher;

    //Weather
    if((matcher = GameMenuCommands.CHEAT_THUNDER.matcher(input)) != null){
        System.out.print(controller.cheatThunder(matcher.group("x") , matcher.group("y")));
    } else if((matcher = GameMenuCommands.WEATHER.matcher(input)) != null){
        System.out.println(controller.weather());
    } else if((matcher = GameMenuCommands.WEATHER_FORECAST.matcher(input)) != null){
        System.out.println(controller.weatherForecast());
    } else if((matcher = GameMenuCommands.CHEAT_WEATHER_SET.matcher(input)) != null) {
        System.out.print(controller.cheatWeatherSet(matcher.group("weather")));
    }

    //GreenHouse
    else if((matcher = GameMenuCommands.GREEN_HOUSE_BUILD.matcher(input)) != null){
        System.out.print(controller.greenHouseBuild());
    }
    //Map & Walk



    //Inventory
    else if((matcher = GameMenuCommands.INVENTORY_TRASH_ITEM.matcher(input)) != null){
        System.out.print(controller.inventoryTrashItem(matcher.group("item")
                ,matcher.group("number")));
    } else if ((matcher = GameMenuCommands.INVENTORY_SHOW.matcher(input)) != null){
        System.out.print(controller.inventoryShow());
    }
    //Energy
    else if((matcher = GameMenuCommands.ENERGY_SHOW.matcher(input)) != null) {
        System.out.println(controller.energyShow());

    } else if ((matcher = GameMenuCommands.CHEAT_ENERGY_SET.matcher(input)) != null) {
        System.out.print(controller.cheatEnergySet(matcher.group("value")));

    } else if((matcher = GameMenuCommands.CHEAT_ENERGY_UNLIMITED.matcher(input)) != null) {
        System.out.println(controller.cheatEnergyUnlimited());
        }
    }

}
