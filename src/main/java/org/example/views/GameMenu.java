package org.example.views;

import org.example.controllers.GameMenuController;
import org.example.controllers.ProfileMenuController;
import org.example.models.App;
import org.example.models.enums.GameMenuCommands;
import org.example.models.enums.LoginRegisterCommands;
import org.example.models.enums.TradeMenuCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {

    private final GameMenuController controller = new GameMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();

        Matcher matcher;

        //game setting methods
        if ((matcher = GameMenuCommands.NEW_GAME.matcher(input)) != null) {
            ArrayList<String> usernames = new ArrayList<>();

            try {

                // اضافه کردن username_1 اگر وجود دارد
                if (matcher.group("username1") != null) {
                    usernames.add(matcher.group("username1"));
                }

                // اضافه کردن username_2 اگر وجود دارد
                if (matcher.group("username2") != null) {
                    usernames.add(matcher.group("username2"));
                }

                // اضافه کردن username_3 اگر وجود دارد
                if (matcher.group("username3") != null) {
                    usernames.add(matcher.group("username3"));
                }
            } catch (Exception e) {
                System.out.println("meow");
            }

            System.out.print(controller.newGame(usernames, scanner));
        } else if ((matcher = GameMenuCommands.LOAD_GAME.matcher(input)) != null) {
            System.out.println(controller.loadGame());
        }

        if(App.getCurrentGame() == null) {
            System.out.println("You should run a game first to use this commands!");
            return;
        }

        if ((matcher = GameMenuCommands.EXIT_GAME.matcher(input)) != null) {
            System.out.println(controller.exitGame());
        } else if ((matcher = GameMenuCommands.NEXT_TURN.matcher(input)) != null) {
            System.out.println(controller.nextTurn());
        } else if(GameMenuCommands.FORCE_TERMINATE.matcher(input) != null) {
            System.out.println(controller.forceTerminate(scanner));
        }

        //  Date & Time Commands
        else if ((matcher = GameMenuCommands.TIME.matcher(input)) != null) {
            System.out.println(controller.time());
        } else if ((matcher = GameMenuCommands.DATE.matcher(input)) != null) {
            System.out.println(controller.date());
        } else if ((matcher = GameMenuCommands.DATE_TIME.matcher(input)) != null) {
            System.out.println(controller.dateTime());
        } else if ((matcher = GameMenuCommands.DAY_OF_THE_WEEK.matcher(input)) != null) {
            System.out.println(controller.dayOfTheWeek());
        } else if ((matcher = GameMenuCommands.SEASON.matcher(input)) != null) {
            System.out.println(controller.showSeason());
        } else if ((matcher = GameMenuCommands.CHEAT_ADVANCE_TIME.matcher(input)) != null) {
            System.out.println(controller.cheatAdvanceTime(matcher.group("X")));
        } else if ((matcher = GameMenuCommands.CHEAT_ADVANCE_DATE.matcher(input)) != null) {
            System.out.println(controller.cheatAdvanceDate(matcher.group("X")));
        }

        //Weather
        else if ((matcher = GameMenuCommands.CHEAT_THUNDER.matcher(input)) != null) {
            System.out.print(controller.cheatThunder(matcher.group("x"), matcher.group("y")));
        } else if ((matcher = GameMenuCommands.WEATHER.matcher(input)) != null) {
            System.out.println(controller.weather());
        } else if ((matcher = GameMenuCommands.WEATHER_FORECAST.matcher(input)) != null) {
            System.out.println(controller.weatherForecast());
        } else if ((matcher = GameMenuCommands.CHEAT_WEATHER_SET.matcher(input)) != null) {
            System.out.println(controller.cheatWeatherSet(matcher.group("weather")));
        }

        //GreenHouse
        else if ((matcher = GameMenuCommands.GREEN_HOUSE_BUILD.matcher(input)) != null) {
            System.out.println(controller.greenHouseBuild());
        }
        //Map & Walk
        else if ((matcher = GameMenuCommands.WALK.matcher(input)) != null) {
            System.out.println(controller.walk(matcher.group("x"), matcher.group("y"), scanner));
        } else if ((matcher = GameMenuCommands.PRINT_MAP.matcher(input)) != null) {
            System.out.println(controller.printMap(matcher.group("x"), matcher.group("y"), matcher.group("size")));
        } else if ((matcher = GameMenuCommands.HELP_READING_MAP.matcher(input)) != null) {
            System.out.println(controller.helpReadingMap());
        }

        //Energy
        else if ((matcher = GameMenuCommands.ENERGY_SHOW.matcher(input)) != null) {
            System.out.println(controller.energyShow());
        } else if ((matcher = GameMenuCommands.CHEAT_ENERGY_SET.matcher(input)) != null) {
            System.out.println(controller.cheatEnergySet(matcher.group("value")));
        } else if ((matcher = GameMenuCommands.CHEAT_ENERGY_UNLIMITED.matcher(input)) != null) {
            System.out.println(controller.cheatEnergyUnlimited());
        }

        //inventory
        else if ((matcher = GameMenuCommands.INVENTORY_TRASH_ITEM.matcher(input)) != null) {
            System.out.println(controller.inventoryTrashItem(matcher.group("item"), matcher.group("number")));
        } else if ((matcher = GameMenuCommands.INVENTORY_SHOW.matcher(input)) != null) {
            System.out.println(controller.inventoryShow());
        }

        // Tools
        else if ((matcher = GameMenuCommands.TOOLS_EQUIPMENT.matcher(input)) != null) {
            System.out.println(controller.toolsEquipment(matcher.group("toolName")));
        } else if ((matcher = GameMenuCommands.TOOLS_SHOW_CURRENT.matcher(input)) != null) {
            System.out.println(controller.toolsShowCurrent());
        } else if ((matcher = GameMenuCommands.TOOLS_SHOW_AVAILABLE.matcher(input)) != null) {
            System.out.println(controller.toolsShowAvailable());
        } else if ((matcher = GameMenuCommands.TOOLS_UPGRADE.matcher(input)) != null) {
            System.out.println(controller.toolsUpgrade(matcher.group("toolName")));
        } else if ((matcher = GameMenuCommands.TOOLS_USE.matcher(input)) != null) {
            System.out.println(controller.toolsUse(matcher.group("direction")));
        }
        // Craft Info
        else if ((matcher = GameMenuCommands.CRAFT_INFO.matcher(input)) != null) {
            System.out.println(controller.craftInfo(matcher.group("craftName")));
        }

        // Planting
        else if ((matcher = GameMenuCommands.PLANT_SEED.matcher(input)) != null) {
            System.out.println(controller.plantSeed(matcher.group("seed") , matcher.group("direction")));
        } else if ((matcher = GameMenuCommands.SHOW_PLANT.matcher(input)) != null) {
            System.out.println(controller.showPlant(matcher.group("x"), matcher.group("y")));
        } else if ((matcher = GameMenuCommands.FERTILIZE.matcher(input)) != null) {
            System.out.println(controller.fertilize(matcher.group("fertilizer"), matcher.group("direction")));
        } else if ((matcher = GameMenuCommands.HOW_MUCH_WATER.matcher(input)) != null) {
            System.out.println(controller.howMuchWater());
        }

        // Crafting Commands
        else if ((matcher = GameMenuCommands.SHOW_CRAFTING_RECIPES.matcher(input)) != null) {
            System.out.println(controller.showCraftingRecipes());
        } else if ((matcher = GameMenuCommands.CRAFTING_CRAFT.matcher(input)) != null) {
            System.out.print(controller.craftingCraft(matcher.group("itemName")));
        } else if ((matcher = GameMenuCommands.PLACE_ITEM.matcher(input)) != null) {
            System.out.println(controller.placeItem(matcher.group("itemName"), matcher.group("direction")));
        } else if ((matcher = GameMenuCommands.CHEAT_ADD_ITEM.matcher(input)) != null) {
            System.out.print(controller.cheatAddItem(matcher.group("itemName"), matcher.group("count")));
        }

        // Cooking Commands
        else if ((matcher = GameMenuCommands.COOKING_REFRIGERATOR.matcher(input)) != null) {
            System.out.println(controller.cookingRefrigerator(matcher.group("status"), matcher.group("itemName")));
        } else if ((matcher = GameMenuCommands.SHOW_COOKING_RECIPES.matcher(input)) != null) {
            System.out.println(controller.showCookingRecipes());
        } else if ((matcher = GameMenuCommands.COOKING_PREPARE.matcher(input)) != null) {
            System.out.println(controller.cookingPrepare(matcher.group("recipeName")));
        } else if ((matcher = GameMenuCommands.EAT.matcher(input)) != null) {
            System.out.println(controller.eat(matcher.group("foodName")));
        }

        // Animals & Fishing
        else if ((matcher = GameMenuCommands.BUILD_BUILDING.matcher(input)) != null) {
            System.out.println(controller.buildBuilding(matcher.group("buildingName"), matcher.group("x"), matcher.group("y")));
        } else if ((matcher = GameMenuCommands.BUY_ANIMAL.matcher(input)) != null) {
            System.out.println(controller.buyAnimal(matcher.group("animalName"), matcher.group("number")));
        } else if ((matcher = GameMenuCommands.PET_ANIMAL.matcher(input)) != null) {
            System.out.println(controller.petAnimal(matcher.group("petName")));
        } else if ((matcher = GameMenuCommands.ANIMAL_LIST.matcher(input)) != null) {
            System.out.println(controller.animalList());
        } else if ((matcher = GameMenuCommands.CHEAT_SET_ANIMAL_FRIENDSHIP.matcher(input)) != null) {
            System.out.println(controller.cheatSetAnimalFriendship(matcher.group("animalName"), matcher.group("amount")));
        } else if ((matcher = GameMenuCommands.SHEPHERD_ANIMAL.matcher(input)) != null) {
            System.out.println(controller.shepherdAnimal(matcher.group("animalName"), matcher.group("x"), matcher.group("y")));
        } else if ((matcher = GameMenuCommands.FEED_HAY.matcher(input)) != null) {
            System.out.println(controller.feedHay(matcher.group("animalName")));
        } else if ((matcher = GameMenuCommands.ANIMAL_PRODUCTION_LIST.matcher(input)) != null) {
            System.out.println(controller.animalProductionList());
        } else if ((matcher = GameMenuCommands.COLLECT_PRODUCT.matcher(input)) != null) {
            System.out.println(controller.collectProduct(matcher.group("name")));
        } else if ((matcher = GameMenuCommands.SELL_ANIMAL.matcher(input)) != null) {
            System.out.println(controller.sellAnimal(matcher.group("name")));
        }
        //Fishing
        else if ((matcher = GameMenuCommands.FISHING.matcher(input)) != null) {
            System.out.println(controller.fishing(matcher.group("fishingPole")));
        }


        // Artisan Commands
        else if ((matcher = GameMenuCommands.ARTISAN_USE.matcher(input)) != null) {
            System.out.println(controller.artisanUse(matcher.group("artisanName") ,
                    (matcher.group("item1Name") != null ? matcher.group("item1Name").trim() : ""),
                    (matcher.group("item2Name") != null ? matcher.group("item2Name").trim() : "")));
        } else if ((matcher = GameMenuCommands.ARTISAN_GET.matcher(input)) != null) {
            System.out.print(controller.artisanGet(matcher.group("artisanName")));
        }


        // Sell & Buy Commands
        else if ((matcher = GameMenuCommands.SHOW_ALL_PRODUCTS.matcher(input)) != null) {
            System.out.println(controller.showAllProducts());
        } else if ((matcher = GameMenuCommands.SHOW_ALL_AVAILABLE_PRODUCTS.matcher(input)) != null) {
            System.out.println(controller.showAllAvailableProducts());
        } else if ((matcher = GameMenuCommands.PURCHASE.matcher(input)) != null) {
            System.out.println(controller.purchase(matcher.group("productName"), matcher.group("count")));
        } else if ((matcher = GameMenuCommands.SELL.matcher(input)) != null) {
            System.out.println(controller.sell(matcher.group("productName"), matcher.group("count")));
        } else if ((matcher = GameMenuCommands.CHEAT_ADD_DOLLARS.matcher(input)) != null) {
            System.out.println(controller.cheatAddDollars(matcher.group("count")));
        }

        // Trading
        else if ((matcher = GameMenuCommands.START_TRADE.matcher(input)) != null) {
            System.out.println(controller.startTrade());
        }

        // Friendships
        else if ((matcher = GameMenuCommands.FRIENDSHIPS.matcher(input)) != null) {
            System.out.println(controller.friendships());
        } else if ((matcher = GameMenuCommands.TALK.matcher(input)) != null) {
            System.out.println(controller.talk(matcher.group("username"), matcher.group("message")));
        } else if ((matcher = GameMenuCommands.TALK_HISTORY.matcher(input)) != null) {
            System.out.println(controller.talkHistory(matcher.group("username")));
        } else if ((matcher = GameMenuCommands.GIFT.matcher(input)) != null) {
            System.out.println(controller.gift(matcher.group("username"),matcher.group("item"),matcher.group("amount")));
        } else if ((matcher = GameMenuCommands.GIFT_LIST.matcher(input)) != null) {
            System.out.println(controller.giftList());
        } else if ((matcher = GameMenuCommands.GIFT_RATE.matcher(input)) != null) {
            System.out.println(controller.giftRate(matcher.group("giftNumber"),matcher.group("rate")));
        } else if ((matcher = GameMenuCommands.GIFT_HISTORY.matcher(input)) != null) {
            System.out.println(controller.giftHistory(matcher.group("username")));
        } else if ((matcher = GameMenuCommands.HUG.matcher(input)) != null) {
            System.out.println(controller.hug(matcher.group("username")));
        } else if ((matcher = GameMenuCommands.FLOWER.matcher(input)) != null) {
            System.out.println(controller.flower(matcher.group("username")));
        } else if ((matcher = GameMenuCommands.ASK_MARRIAGE.matcher(input)) != null) {
            System.out.println(controller.askMarriage(matcher.group("username"),matcher.group("ring")));
        } else if ((matcher = GameMenuCommands.RESPOND.matcher(input)) != null) {
            System.out.println(controller.respond(matcher.group("status") , matcher.group("username")));
        }



        // NPC
        else if ((matcher = GameMenuCommands.MEET_NPC.matcher(input)) != null) {
            System.out.println(controller.meetNPC(matcher.group("npcName")));
        } else if ((matcher = GameMenuCommands.GIFT_NPC.matcher(input)) != null) {
            System.out.println(controller.giftNPC(matcher.group("npcName"),matcher.group("item")));
        } else if ((matcher = GameMenuCommands.FRIENDSHIP_NPC_LIST.matcher(input)) != null) {
            System.out.println(controller.friendshipNPCList());
        } else if ((matcher = GameMenuCommands.QUESTS_LIST.matcher(input)) != null) {
            System.out.println(controller.questsList());
        } else if ((matcher = GameMenuCommands.QUESTS_FINISH.matcher(input)) != null) {
            System.out.println(controller.questsFinish(matcher.group("index")));
        }


        //Additional Functions
         else if ((matcher = GameMenuCommands.SHOW_PLAYER_COORDINATE.matcher(input)) != null) {
             System.out.println(controller.showPlayerCoordinate());
         } else if ((matcher = GameMenuCommands.SHOW_BALANCE.matcher(input)) != null) {
            System.out.println(controller.showBalance());
         } else if ((matcher = GameMenuCommands.Test.matcher(input)) != null) {
            System.out.println(controller.test());
         }


         else {
             System.out.println("Invalid Command");
        }
    }
}