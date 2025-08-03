package com.StardewValley.models.game_structure;

import com.StardewValley.models.App;
import com.StardewValley.models.Pair;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.weathers.Rain;
import com.StardewValley.models.game_structure.weathers.Storm;
import com.StardewValley.models.game_structure.weathers.Weather;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.craftings.Crafting;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.farmings.FarmingCrop;
import com.StardewValley.models.goods.farmings.FarmingTree;
import com.StardewValley.models.goods.farmings.FarmingTreeSapling;
import com.StardewValley.models.goods.foods.Food;
import com.StardewValley.models.goods.foragings.ForagingMixedSeed;
import com.StardewValley.models.goods.foragings.ForagingSeed;
import com.StardewValley.models.interactions.Animals.Animal;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCFriendship;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuilding;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Game {

    private DateTime dateTime;
    private Weather weather;
    private Tomorrow tomorrow;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Player gameAdmin;
    private Map map = null;
    private final ArrayList<NPC> NPCs = new ArrayList<>();
    private int counter = 0;
    private ArrayList<Pair<Player, String>> publicChat = new ArrayList<>();

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
        tomorrow.setTomorrowWeather(this);
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
            List<Good> goodsToAdd = new ArrayList<>();

            while (iterator.hasNext()) {
                Good good = iterator.next();
                if (good instanceof ForagingSeed) {
                    ForagingSeed seed = (ForagingSeed) good;
                    seed.dailyChange();
                    if (seed.isCrop()) {
                        goodsToAdd.add(Good.newGood(seed.getCropType()));
                        iterator.remove();
                    }
                }
            }

            addGoodToTile(tile, goodsToAdd);
        }


        //for farming tree sapling
        for (Tile tile : map.getTiles()) {
            List<Good> goodsToAdd = new ArrayList<>();
            Iterator<Good> iterator = tile.getGoods().iterator();

            while (iterator.hasNext()) {
                Good good = iterator.next();
                if (good instanceof FarmingTreeSapling) {
                    FarmingTreeSapling sapling = (FarmingTreeSapling) good;
                    sapling.dailyChange();
                    if (sapling.isTree()) {
                        iterator.remove();
                        goodsToAdd.add(Good.newGood(sapling.getTreeType()));
                    }
                }
            }

            addGoodToTile(tile, goodsToAdd);
        }

        for (Player player : players) {
            HashMap<Player, Boolean> interaction = player.getIsInteracted();
            interaction.replaceAll((p, v) -> false);
        }


        //for mixed seed

        for (Tile tile : map.getTiles()) {
            Iterator<Good> iterator = tile.getGoods().iterator();
            ArrayList<Good> goodsToAdd = new ArrayList<>();

            while (iterator.hasNext()) {
                Good good = iterator.next();
                if (good instanceof ForagingMixedSeed) {
                    ForagingMixedSeed seed = (ForagingMixedSeed) good;
                    seed.dailyChange();
                    if (seed.isCrop()) {
                        iterator.remove();
                        goodsToAdd.add(Good.newGood(seed.getCropType()));
                    }
                }
            }

            addGoodToTile(tile, goodsToAdd);
        }

        Coordinate coordinate = App.getCurrentGame().getCurrentPlayer().getCoordinate();
        for (Tile tile : map.getTiles()) {
            for (Good good : tile.getGoods()) {
                if (good instanceof Crafting) {
                    Crafting crafting = (Crafting) good;
                    GoodType type = crafting.getType();
                    if (type.equals(CraftingType.SPRINKLER)) {
                        for (int i = 0; i < 8; i += 2) {
                            Coordinate coordinate1 = new Coordinate(coordinate.getX() + Coordinate.coordinates.get(i).getX(),
                                coordinate.getY() + Coordinate.coordinates.get(i).getY());

                            Tile t = App.getCurrentGame().getMap().findTile(coordinate1);
                            if (t != null) {
                                t.setWatered(true);
                            }
                        }
                    } else if (type.equals(CraftingType.QUALITY_SPRINKLER)) {
                        for (int i = 0; i < 8; i += 1) {
                            Coordinate coordinate1 = new Coordinate(coordinate.getX() + Coordinate.coordinates.get(i).getX(),
                                coordinate.getY() + Coordinate.coordinates.get(i).getY());

                            Tile t = App.getCurrentGame().getMap().findTile(coordinate1);
                            if (t != null) {
                                t.setWatered(true);
                            }
                        }
                    } else if (type.equals(CraftingType.IRIDIUM_SPRINKLER)) {
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
                player.getFarm().getFarmBuildings().getFirst().getStartCordinate().getY() + 2));
        }

        // Check weather
    }

    public static void addGoodToTile(Tile tile, List<Good> goodsToAdd) {
        tile.getGoods().addAll(goodsToAdd);

        ArrayList<Integer> haveToRemove = new ArrayList<>();
        for (int i = 0; i < tile.getGoods().size(); i++) {
            if (tile.getGoods().get(i) == null) {
                haveToRemove.add(i);
            }
        }

        for (Integer i : haveToRemove) {
            tile.getGoods().remove((int) i);
        }
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
            System.out.println("there is a chance " + crowCounter + " Crows would attack " + player.getUser().getUsername() + " crops");

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

    public ArrayList<Pair<Player, String>> getPublicChat() {
        return publicChat;
    }
}
