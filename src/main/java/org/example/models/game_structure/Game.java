package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.enums.TileType;
import org.example.models.game_structure.weathers.Rain;
import org.example.models.game_structure.weathers.Storm;
import org.example.models.game_structure.weathers.Weather;
import org.example.models.goods.Good;
import org.example.models.goods.craftings.Crafting;
import org.example.models.goods.craftings.CraftingType;
import org.example.models.goods.farmings.FarmingTreeSapling;
import org.example.models.goods.foragings.ForagingMixedSeed;
import org.example.models.goods.foragings.ForagingSeed;
import org.example.models.interactions.Animals.Animal;
import org.example.models.goods.farmings.FarmingCrop;
import org.example.models.goods.farmings.FarmingTree;
import org.example.models.goods.foods.Food;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.NPCs.NPCFriendship;
import org.example.models.interactions.Player;
import org.example.models.interactions.PlayerBuildings.FarmBuilding;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Game {

    private DateTime dateTime;
    private Weather weather;
    private Tomorrow tomorrow;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Player gameAdmin;
    private Map map = null;
    private Player currentPlayingPlayer;
    private final ArrayList<NPC> NPCs = new ArrayList<>();
    private int counter = 0;
    private static FileWriter myWriter;

    public static void writeIntoFile(String string) {
        try (FileWriter myWriter = new FileWriter("commands.txt", true)) {
            myWriter.append(string);
            myWriter.append("\n");
        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }


    public void setPlayers(ArrayList<Player> players) {
        this.players.addAll(players);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Player getGameAdmin() {
        return gameAdmin;
    }

    public void setGameAdmin(Player gameAdmin) {
        this.gameAdmin = gameAdmin;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void nextPlayer() {
        counter++;
        if (counter >= players.size()) {
            counter = 0;
            this.dateTime.timeFlow();
        }
        if (players.get(counter).getEnergy().isAwake()) {
            currentPlayer = players.get(counter);
        } else {
            nextPlayer();
        }
    }

    public Map getMap() {
        return map;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getWeatherName() {
        return weather.getName();
    }

    public Weather getWeather() {
        return weather;
    }

    public void cheatSetWeather(Weather weather) {
        tomorrow.setWeather(weather);
    }

    public Tomorrow getTomorrow() {
        return tomorrow;
    }

    public void gameFlow() {

        // Weather setups for next day
        App.getCurrentGame().getDateTime().setTime(9);
        this.weather = tomorrow.getWeather();
        tomorrow.setWeather(weather);
        if (this.weather instanceof Storm) {
            ((Storm) this.weather).randomThunder();
            ((Storm) this.weather).waterAllTiles();
        }
        if (this.weather instanceof Rain) {
            ((Rain) this.weather).waterAllTiles();
        }
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (player.getRejectionBuff() != null) {
                player.getRejectionBuff().setRemainEffectTime();
                if (player.getRejectionBuff().getRemainEffectTime() == 0) {
                    player.setRejectionBuff(null);
                }
                player.getEnergy().setTurnValueLeft(50);
                player.getEnergy().setDayEnergyLeft(100);
                player.getEnergy().setAwake(true);
            } else {
                player.getEnergy().setTurnValueLeft(50);
                player.getEnergy().setDayEnergyLeft(200);
                player.getEnergy().setAwake(true);
            }
        }

        for (Tile tile : App.getCurrentGame().getMap().getTiles()) {
            tile.setWatered(false);
        }

        crowAttack();
        App.getCurrentGame().getMap().generateRandomForagingCrops(99);
        App.getCurrentGame().getMap().generateRandomForagingSeed(99);
        App.getCurrentGame().getMap().generateRandomMinerals(99);

        App.getCurrentGame().getMap().Fertilize();

        for (ShippingBin shippingBin : App.getCurrentGame().getMap().getShippingBins()) {
            shippingBin.emptyShippingBin();
        }

        if (App.getCurrentGame().getDateTime().getDayOfSeason() == 1) {
            App.getCurrentGame().getDateTime().farmingSeasonChange();
        }

        //for animals
        for (Player player : players) {
            for (FarmBuilding building : player.getFarm().getFarmBuildings()) {
                for (Animal animal : building.getAnimals()) {
                    animal.animalDayResult();
                }
            }
        }

        //for foraging seed
        for (Tile tile : map.getTiles()) {
            Iterator<Good> iterator = tile.getGoods().iterator();
            while (iterator.hasNext()) {
                Good good = iterator.next();
                if (good instanceof ForagingSeed) {
                    ForagingSeed seed = (ForagingSeed) good;
                    seed.dailyChange();
                    if (seed.isCrop()) {
                        iterator.remove();
                        tile.getGoods().add(Good.newGood(seed.getCropType()));
                    }
                }
            }
        }

        //for farming tree sapling
        for (Tile tile : map.getTiles()) {
            Iterator<Good> iterator = tile.getGoods().iterator();
            while (iterator.hasNext()) {
                Good good = iterator.next();
                if (good instanceof FarmingTreeSapling) {
                    FarmingTreeSapling sapling = (FarmingTreeSapling) good;
                    sapling.dailyChange();
                    if (sapling.isTree()) {
                        iterator.remove();
                        tile.getGoods().add(Good.newGood(sapling.getTreeType()));
                    }
                }
            }
        }

        for (Player player : players) {
            HashMap<Player, Boolean> interaction = player.getIsInteracted();
            interaction.replaceAll((p, v) -> false);
        }


        //for mixed seed

        for (Tile tile : map.getTiles()) {
            Iterator<Good> iterator = tile.getGoods().iterator();
            while (iterator.hasNext()) {
                Good good = iterator.next();
                if (good instanceof ForagingMixedSeed) {
                    ForagingMixedSeed seed = (ForagingMixedSeed) good;
                    seed.dailyChange();
                    if (seed.isCrop()) {
                        iterator.remove();
                        tile.getGoods().add(Good.newGood(seed.getCropType()));
                    }
                }
            }
        }

        Coordinate coordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();
        for (Tile tile : map.getTiles()) {
            for (Good good : tile.getGoods()) {
                if (good instanceof Crafting crafting) {
                    switch (crafting.getType()) {
                        case CraftingType.SPRINKLER -> {
                            for (int i = 0; i < 8; i += 2) {
                                Coordinate coordinate1 = new Coordinate(coordinate.getX() + Coordinate.coordinates.get(i).getX(),
                                        coordinate.getY() + Coordinate.coordinates.get(i).getY());

                                Tile t = App.getCurrentGame().getMap().findTile(coordinate1);
                                if (t != null) {
                                    t.setWatered(true);
                                }
                            }
                        }
                        case CraftingType.QUALITY_SPRINKLER -> {
                            for (int i = 0; i < 8; i += 1) {
                                Coordinate coordinate1 = new Coordinate(coordinate.getX() + Coordinate.coordinates.get(i).getX(),
                                        coordinate.getY() + Coordinate.coordinates.get(i).getY());

                                Tile t = App.getCurrentGame().getMap().findTile(coordinate1);
                                if (t != null) {
                                    t.setWatered(true);
                                }
                            }
                        }
                        case CraftingType.IRIDIUM_SPRINKLER -> {
                            for (int i = 0; i < 8; i += 1) {
                                for (int j = 1; j <= 2; j++) {
                                    Coordinate coordinate1 = new Coordinate(coordinate.getX() + j * Coordinate.coordinates.get(i).getX(),
                                            coordinate.getY() + j * Coordinate.coordinates.get(i).getY());

                                    Tile t = App.getCurrentGame().getMap().findTile(coordinate1);
                                    if (t != null) {
                                        t.setWatered(true);
                                    }
                                }
                            }
                        }
                        default -> {

                        }
                    }
                }
            }
        }

        //NPC friendShips
        for (NPC npc : getNPCs()) {
            for (NPCFriendship friendship : npc.getFriendships()) {
                friendship.setFriendshipToday();
            }
        }

        // players come back to their home
        for (Player player : players) {
            player.setCoordinate(new Coordinate(player.getFarm().getFarmBuildings().getFirst().getStartCordinate().getX() + 5,
                    player.getFarm().getFarmBuildings().getFirst().getStartCordinate().getY() + 5));
        }

        // Check weather
    }

    public Player findPlayer(String playerName) {
        for (Player player : players) {
            if (player.getUser().getUsername().equals(playerName))
                return player;
        }
        return null;
    }

    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void crowAttack() {
        for (Player player : App.getCurrentGame().getPlayers()) {
            int cropCounter = 0;
            for (Tile tile : player.getFarm().getTiles()) {
                if (!(tile.getTileType().equals(TileType.GREEN_HOUSE))) {
                    for (Good good : tile.getGoods()) {
                        if (good instanceof FarmingCrop) {
                            cropCounter++;
                        }
                    }
                }
            }
            int numberOfCrows = (int) Math.floor((double) cropCounter / 16);
            int crowCounter = 0;
            System.out.println("there is a chance " + crowCounter + " Crows would attack "+ player.getUser().getUsername() + " crops");

            while (numberOfCrows != crowCounter) {
                int randomAttack = (int) Math.floor((Math.random() * 4));
                int x = (int) Math.floor(Math.random() * (player.getFarm().getTiles().size()));
                Tile randomTile = player.getFarm().getTiles().get(x);
                for (Good good : randomTile.getGoods()) {
                    if (good instanceof FarmingCrop) {
                        crowCounter++;
                    }
                }
                if (randomAttack == 2) {
                    if (!(randomTile.checkAroundForScarCrow())) {
                        Iterator<Good> iterator = randomTile.getGoods().iterator();
                        while (iterator.hasNext()) {
                            Good good = iterator.next();
                            if (good instanceof FarmingCrop) {
                                iterator.remove();
                                System.out.println("a Crow attacked your crops");
                                break;
                            }
                        }
                    }
                } else if (randomAttack == 3) {
                    if (!(randomTile.checkAroundForScarCrow())) {
                        for (Good good : randomTile.getGoods()) {
                            if (good instanceof FarmingTree) {
                                Iterator<Good> iterator = randomTile.getGoods().iterator();
                                while (iterator.hasNext()) {
                                    Good good2 = iterator.next();
                                    if (good2 instanceof Food) {
                                        iterator.remove();
                                        System.out.println("a Crow attacked your trees");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void setTomorrow(Tomorrow tomorrow) {
        this.tomorrow = tomorrow;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void setNPCs(ArrayList<NPC> NPCs) {
        this.NPCs.addAll(NPCs);
    }
}
