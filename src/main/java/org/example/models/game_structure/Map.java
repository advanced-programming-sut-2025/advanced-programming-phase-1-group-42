package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.enums.TileType;
import org.example.models.goods.Good;
import org.example.models.goods.farmings.FarmingTree;
import org.example.models.goods.farmings.FarmingTreeSapling;
import org.example.models.goods.foragings.ForagingCropType;
import org.example.models.goods.foragings.ForagingMineralType;
import org.example.models.goods.foragings.ForagingSeedType;
import org.example.models.goods.foragings.ForagingTree;
import org.example.models.interactions.Animals.Animal;
import org.example.models.interactions.game_buildings.GameBuilding;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Farm> farms = new ArrayList<>();
    private final ArrayList<GameBuilding> gameBuildings = new ArrayList<>();
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final Coordinate startingCoordinate, endingCoordinate;

    static final HashMap<String, String> colorMap = new HashMap<String, String>() {{
        put("Reset", "\u001B[0m");
        // Foreground colors
        put("Black", "\033[0;30m");
        put("Red", "\033[0;31m");
        put("Green", "\033[0;32m");
        put("Yellow", "\033[0;33m");
        put("Blue", "\033[0;34m");
        put("Purple", "\033[0;35m");
        put("Cyan", "\033[0;36m");
        put("White", "\033[0;37m");
        put("Gray", "\033[0;90m");

        // Background colors
        put("Black_Background", "\033[40m");
        put("Red_Background", "\033[41m");
        put("Green_Background", "\033[42m");
        put("Yellow_Background", "\033[43m");
        put("Blue_Background", "\033[44m");
        put("Purple_Background", "\033[45m");
        put("Cyan_Background", "\033[46m");
        put("White_Background", "\033[47m");
        put("Gray_Background", "\033[100m");
    }};



    public Map() {
        this.startingCoordinate = new Coordinate(0, 0);
        this.endingCoordinate = new Coordinate(150, 160);
    }

    // A function to print map
    public String printMap(int x , int y, int size) {
        for(int i = 0 ; i < size; i++){
            System.out.print("\n");
            for(int j = 0 ; j < size; j++){
                Coordinate coordinate = new Coordinate(x + i,y + j);
                Tile tile = findTile(coordinate);

                if(tile == null){
                    System.out.print(colorMap.get("Black_Background") + " " + colorMap.get("Reset"));
                }else if(tile.getTileType() == TileType.FARM){
                    for (Good good : tile.getGoods()) {
                        if(good instanceof ForagingTree){
                            System.out.print(colorMap.get("Green_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else if(good instanceof FarmingTree){
                            System.out.print(colorMap.get("Green_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else if(good instanceof FarmingTreeSapling){
                            System.out.print(colorMap.get("Green_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else{
                            System.out.print(colorMap.get("Green_Background")+
                                    colorMap.get("Yellow")+" "+ colorMap.get("Reset"));
                        }
                    }
                }else if(tile.getTileType() == TileType.WATER){
                    System.out.print(colorMap.get("Cyan_Background") + " "+ colorMap.get("Reset"));
                }else if(tile.getTileType() == TileType.GREEN_HOUSE) {
                    for (Good good : tile.getGoods()) {
                        if(good instanceof ForagingTree){
                            System.out.print(colorMap.get("White_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else if(good instanceof FarmingTree){
                            System.out.print(colorMap.get("White_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else if(good instanceof FarmingTreeSapling){
                            System.out.print(colorMap.get("White_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else{
                            System.out.print(colorMap.get("White_Background")+
                                    colorMap.get("Yellow")+" "+ colorMap.get("Reset"));
                        }
                    }
                }else if(tile.getTileType() == TileType.PLAYER_BUILDING){
                    System.out.print(colorMap.get("Yellow_Background")+" "+ colorMap.get("Reset") );
                }else if(tile.getTileType() == TileType.ROAD){
                    System.out.print(colorMap.get("Gray_Background")+
                            colorMap.get("Purple")+"-"+ colorMap.get("Reset"));
                }else if(tile.getTileType() == TileType.QUARRY) {
                    System.out.print(colorMap.get("Gray_Background")+" "+ colorMap.get("Reset"));
                }else if(tile.getTileType() == TileType.PLAIN) {
                    System.out.print(colorMap.get("Green_Background")+
                            colorMap.get("Red")+"-"+ colorMap.get("Reset"));
                }
            }
        }
        return "";
    }



    public void setFarms(ArrayList<Farm> farms) {
        this.farms.addAll(farms);
    }

    public boolean dfsCheck(Coordinate start, Coordinate end) {
        //TODO
        return false;
    }

    public GameBuilding getBlackSmith() {
        return this.gameBuildings.get(0);
    }

    public GameBuilding getJojoMart() {
        return this.gameBuildings.get(1);
    }

    public GameBuilding getPierreGeneralStore() {
        return this.gameBuildings.get(2);
    }

    public GameBuilding getCarpenterShop() {
        return this.gameBuildings.get(3);
    }

    public GameBuilding getFishShop() {
        return this.gameBuildings.get(4);
    }

    public GameBuilding getMarnieRanch() {
        return this.gameBuildings.get(5);
    }

    public GameBuilding getTheStarDropSaloon() {
        return this.gameBuildings.get(6);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile findTile(Coordinate coordinate) {
        for (Tile tile : this.tiles) {
            if(tile.getCordinate().equals(coordinate))
                return tile;
        }
        return null;
    }

    public TileType findTileType(Coordinate coordinate) {
        for (Tile tile : this.tiles) {
            if (tile.getCordinate().equals(coordinate)) {
                return tile.getTileType();
            }
        }
        return null;
    }

    public Tile findTileByXY(int x, int y) {
        for (Tile tile : this.tiles) {
            if (tile.getCordinate().equals(new Coordinate(x, y))) {
                return tile;
            }
        }
        return null;
    }
    public Farm findFarm(Coordinate coordinate) {
        for (Farm farm : this.farms) {
            if(farm.findTile(coordinate) != null)
                return farm;
        }
        return null;
    }

    public Coordinate getStartingCoordinate() {
        return startingCoordinate;
    }

    public Coordinate getEndingCoordinate() {
        return endingCoordinate;
    }

    public GameBuilding findGameBuilding(Coordinate coordinate) {
        for (GameBuilding gameBuilding : this.gameBuildings) {
            if(gameBuilding.isInBuilding(coordinate))
                return gameBuilding;
        }
        return null;
    }

    public ArrayList<Animal> allAnimals() {
        return this.animals;
    }

    public Animal findAnimalByName(String name) {
        for (Animal animal : this.animals) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        return null;
    }

    public void generateRandomForagingCrops(){
        for (int i = 0 ; i < 140 ; i++){
            for(int j = 0 ; j < 160 ; j++){
                int random = (int)Math.floor((Math.random()*100));
                Coordinate coordinate = new Coordinate(i , j);
                Tile tile = findTile(coordinate);
                if(tile != null){
                    if(tile.getTileType().equals(TileType.FARM) || tile.getTileType().equals(TileType.PLAIN)  ){
                        if(random == 50){
                            int randomForaging = (int)Math.floor((Math.random()*9));

                            switch (App.getCurrentGame().getDateTime().getSeasonOfYear().getName()){
                                case "Spring":
                                    switch(randomForaging) {
                                        case 1:
                                            tile.addGood(Good.newGood(ForagingCropType.DAFFODIL));
                                            break;
                                        case 2:
                                            tile.addGood(Good.newGood(ForagingCropType.DANDELION));
                                            break;
                                        case 3:
                                            tile.addGood(Good.newGood(ForagingCropType.LEEK));
                                            break;
                                        case 4:
                                            tile.addGood(Good.newGood(ForagingCropType.MOREL));
                                            break;
                                        case 5:
                                            tile.addGood(Good.newGood(ForagingCropType.SALMONBERRY));
                                            break;
                                        case 6:
                                            tile.addGood(Good.newGood(ForagingCropType.SPRING_ONION));
                                            break;
                                        case 7:
                                            tile.addGood(Good.newGood(ForagingCropType.SPRING_ONION));
                                            break;
                                        case 8:
                                            tile.addGood(Good.newGood(ForagingCropType.WILD_HORSERADISH));
                                            break;
                                        default:
                                            break;
                                    }
                                case "Summer":
                                    switch(randomForaging) {
                                        case 1:
                                            tile.addGood(Good.newGood(ForagingCropType.SWEET_PEA));
                                            break;
                                        case 2:
                                            tile.addGood(Good.newGood(ForagingCropType.SPICE_BERRY));
                                            break;
                                        case 3:
                                            tile.addGood(Good.newGood(ForagingCropType.RED_MUSHROOM));
                                            break;
                                        case 4:
                                            tile.addGood(Good.newGood(ForagingCropType.GRAPE));
                                            break;
                                        case 5:
                                            tile.addGood(Good.newGood(ForagingCropType.FIDDLEHEAD_FERN));
                                            break;
                                        default:
                                            break;
                                    }
                                case "Fall":
                                    switch(randomForaging) {
                                        case 1:
                                            tile.addGood(Good.newGood(ForagingCropType.BLACKBERRY));
                                            break;
                                        case 2:
                                            tile.addGood(Good.newGood(ForagingCropType.CHANTERELLE));
                                            break;
                                        case 3:
                                            tile.addGood(Good.newGood(ForagingCropType.HAZELNUT));
                                            break;
                                        case 4:
                                            tile.addGood(Good.newGood(ForagingCropType.PURPLE_MUSHROOM));
                                            break;
                                        case 5:
                                            tile.addGood(Good.newGood(ForagingCropType.WILD_PLUM));
                                            break;
                                        default:
                                            break;
                                    }
                                case "Winter":
                                    switch(randomForaging) {
                                        case 1:
                                            tile.addGood(Good.newGood(ForagingCropType.CROCUS));
                                            break;
                                        case 2:
                                            tile.addGood(Good.newGood(ForagingCropType.CRYSTAL_FRUIT));
                                            break;
                                        case 3:
                                            tile.addGood(Good.newGood(ForagingCropType.HOLLY));
                                            break;
                                        case 4:
                                            tile.addGood(Good.newGood(ForagingCropType.SNOW_YAM));
                                            break;
                                        case 5:
                                            tile.addGood(Good.newGood(ForagingCropType.WINTER_ROOT));
                                            break;
                                        default:
                                            break;
                                    }
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }
    public void generateRandomForagingSeed() {
        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 160; j++) {
                int random = (int) Math.floor((Math.random() * 100));
                Coordinate coordinate = new Coordinate(i, j);
                Tile tile = findTile(coordinate);

                if (tile != null && tile.getTileType().equals(TileType.PLOWED_FARM)) {
                    if (random == 50) {
                        int randomForaging = (int) Math.floor((Math.random() * 9));
                        String season = App.getCurrentGame().getDateTime().getSeasonOfYear().getName();

                        switch (season) {
                            case "Spring":
                                switch (randomForaging) {
                                    case 1:
                                        tile.addGood(Good.newGood(ForagingSeedType.JAZZ_SEEDS));
                                        break;
                                    case 2:
                                        tile.addGood(Good.newGood(ForagingSeedType.CARROT_SEEDS));
                                        break;
                                    case 3:
                                        tile.addGood(Good.newGood(ForagingSeedType.CAULIFLOWER_SEEDS));
                                        break;
                                    case 4:
                                        tile.addGood(Good.newGood(ForagingSeedType.GARLIC_SEEDS));
                                        break;
                                    case 5:
                                        tile.addGood(Good.newGood(ForagingSeedType.KALE_SEEDS));
                                        break;
                                    case 6:
                                        tile.addGood(Good.newGood(ForagingSeedType.PARSNIP_SEEDS));
                                        break;
                                    case 7:
                                        tile.addGood(Good.newGood(ForagingSeedType.POTATO_SEEDS));
                                        break;
                                    case 8:
                                        tile.addGood(Good.newGood(ForagingSeedType.RHUBARB_SEEDS));
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case "Summer":
                                switch (randomForaging) {
                                    case 1:
                                        tile.addGood(Good.newGood(ForagingSeedType.BLUEBERRY_SEEDS));
                                        break;
                                    case 2:
                                        tile.addGood(Good.newGood(ForagingSeedType.CORN_SEEDS));
                                        break;
                                    case 3:
                                        tile.addGood(Good.newGood(ForagingSeedType.MELON_SEEDS));
                                        break;
                                    case 4:
                                        tile.addGood(Good.newGood(ForagingSeedType.PEPPER_SEEDS));
                                        break;
                                    case 5:
                                        tile.addGood(Good.newGood(ForagingSeedType.TOMATO_SEEDS));
                                        break;
                                    case 6:
                                        tile.addGood(Good.newGood(ForagingSeedType.SUNFLOWER_SEEDS));
                                        break;
                                    case 7:
                                        tile.addGood(Good.newGood(ForagingSeedType.RADISH_SEEDS));
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case "Fall":
                                switch (randomForaging) {
                                    case 1:
                                        tile.addGood(Good.newGood(ForagingSeedType.PUMPKIN_SEEDS));
                                        break;
                                    case 2:
                                        tile.addGood(Good.newGood(ForagingSeedType.YAM_SEEDS));
                                        break;
                                    case 3:
                                        tile.addGood(Good.newGood(ForagingSeedType.CRANBERRY_SEEDS));
                                        break;
                                    case 4:
                                        tile.addGood(Good.newGood(ForagingSeedType.EGGPLANT_SEEDS));
                                        break;
                                    case 5:
                                        tile.addGood(Good.newGood(ForagingSeedType.GRAPE_STARTER));
                                        break;
                                    case 6:
                                        tile.addGood(Good.newGood(ForagingSeedType.ARTICHOKE_SEEDS));
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case "Winter":
                                switch (randomForaging) {
                                    case 1:
                                        tile.addGood(Good.newGood(ForagingSeedType.POWDERMELON_SEEDS));
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
    public void generateRandomMinerals(){
        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 160; j++) {
                int random = (int) Math.floor((Math.random() * 100));
                Coordinate coordinate = new Coordinate(i, j);
                Tile tile = findTile(coordinate);

                if (tile != null && tile.getTileType().equals(TileType.QUARRY)) {
                    if (random == 50) {
                        int randomMineral = (int) Math.floor((Math.random() * 17)); // 0 to 16 (17 minerals)
                        switch (randomMineral) {
                            case 0:
                                tile.addGood(Good.newGood(ForagingMineralType.QUARTZ));
                                break;
                            case 1:
                                tile.addGood(Good.newGood(ForagingMineralType.EARTH_CRYSTAL));
                                break;
                            case 2:
                                tile.addGood(Good.newGood(ForagingMineralType.FROZEN_TEAR));
                                break;
                            case 3:
                                tile.addGood(Good.newGood(ForagingMineralType.FIRE_QUARTZ));
                                break;
                            case 4:
                                tile.addGood(Good.newGood(ForagingMineralType.EMERALD));
                                break;
                            case 5:
                                tile.addGood(Good.newGood(ForagingMineralType.AQUAMARINE));
                                break;
                            case 6:
                                tile.addGood(Good.newGood(ForagingMineralType.RUBY));
                                break;
                            case 7:
                                tile.addGood(Good.newGood(ForagingMineralType.AMETHYST));
                                break;
                            case 8:
                                tile.addGood(Good.newGood(ForagingMineralType.TOPAZ));
                                break;
                            case 9:
                                tile.addGood(Good.newGood(ForagingMineralType.JADE));
                                break;
                            case 10:
                                tile.addGood(Good.newGood(ForagingMineralType.DIAMOND));
                                break;
                            case 11:
                                tile.addGood(Good.newGood(ForagingMineralType.PRISMATIC_SHARD));
                                break;
                            case 12:
                                tile.addGood(Good.newGood(ForagingMineralType.COPPER));
                                break;
                            case 13:
                                tile.addGood(Good.newGood(ForagingMineralType.IRON));
                                break;
                            case 14:
                                tile.addGood(Good.newGood(ForagingMineralType.GOLD));
                                break;
                            case 15:
                                tile.addGood(Good.newGood(ForagingMineralType.IRIDIUM));
                                break;
                            case 16:
                                tile.addGood(Good.newGood(ForagingMineralType.COAL));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
}
