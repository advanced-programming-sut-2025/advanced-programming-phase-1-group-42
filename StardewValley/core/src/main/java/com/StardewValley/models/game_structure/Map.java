package com.StardewValley.models.game_structure;



import com.StardewValley.client.AppClient;
import com.StardewValley.models.Assets;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.farmings.FarmingCrop;
import com.StardewValley.models.goods.farmings.FarmingTree;
import com.StardewValley.models.goods.farmings.FarmingTreeSapling;
import com.StardewValley.models.goods.foragings.*;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.interactions.Animals.Animal;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.game_buildings.GameBuilding;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Map {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Farm> farms = new ArrayList<>();
    private final ArrayList<GameBuilding> gameBuildings = new ArrayList<>();
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final Coordinate startingCoordinate, endingCoordinate;
    private final ArrayList<ShippingBin> shippingBins = new ArrayList<>();



    //Textures
    Texture farm_Background;
    Texture empty;
    Texture null_txr;
    Texture wall;
    Texture water;
    Texture greenhouse;
    Texture playerBuilding;
    Texture quarry ;
    Texture road;
    Texture beach ;
    Texture square ;
    Texture wateredFarm;
    Texture tree ;
    Texture seed ;
    Texture Crop ;
    Texture playerTexture;
    Texture mushroomTree;
    Texture plain ;
    Texture abigail ;
    Texture clint ;
    Texture gus ;
    Texture harvey ;
    Texture leah ;
    Texture marnie ;
    Texture morris;
    Texture pierre;
    Texture robin ;
    Texture sebastian ;
    Texture willy ;
    Image farm_Background_img;
    Image empty_img ;
    Image null_img;
    Image wall_img ;
    Image water_img ;
    Image greenhouse_img;
    Image playerBuilding_img;
    Image quarry_img;
    Image road_img ;
    Image beach_img ;
    Image square_img ;
    Image wateredFarm_img;
    Image tree_img ;
    Image seed_img ;
    Image Crop_img ;
    Image player_img ;
    Image mushroomTree_img;
    Image plain_img ;
    Image abigail_img ;
    Image clint_img ;
    Image gus_img ;
    Image harvey_img;
    Image leah_img ;
    Image marnie_img ;
    Image morris_img ;
    Image pierre_img ;
    Image robin_img ;
    Image sebastian_img;
    Image willy_img ;

    static final HashMap<String, String> colorMap = new HashMap<String, String>() {{
        // Reset
        put("Reset", "\u001B[0m");

        // Standard Foreground Colors
        put("Black", "\033[0;30m");
        put("Red", "\033[0;31m");
        put("Green", "\033[0;32m");
        put("Yellow", "\033[0;33m");
        put("Blue", "\033[0;34m");
        put("Purple", "\033[0;35m");
        put("Cyan", "\033[0;36m");
        put("White", "\033[0;37m");
        put("Gray", "\033[0;90m");

        // Bright/Bold Foreground Colors
        put("Bright_Black", "\033[1;30m");
        put("Bright_Red", "\033[1;31m");
        put("Bright_Green", "\033[1;32m");
        put("Bright_Yellow", "\033[1;33m");
        put("Bright_Blue", "\033[1;34m");
        put("Bright_Purple", "\033[1;35m");
        put("Bright_Cyan", "\033[1;36m");
        put("Bright_White", "\033[1;37m");

        // Standard Background Colors
        put("Black_Background", "\033[40m");
        put("Red_Background", "\033[41m");
        put("Green_Background", "\033[42m");
        put("Yellow_Background", "\033[43m");
        put("Blue_Background", "\033[44m");
        put("Purple_Background", "\033[45m");
        put("Cyan_Background", "\033[46m");
        put("White_Background", "\033[47m");
        put("Gray_Background", "\033[100m");

        // Bright Background Colors
        put("Bright_Black_Background", "\033[100m");  // Same as Gray_Background
        put("Bright_Red_Background", "\033[101m");
        put("Bright_Green_Background", "\033[102m");
        put("Bright_Yellow_Background", "\033[103m");
        put("Bright_Blue_Background", "\033[104m");
        put("Bright_Purple_Background", "\033[105m");
        put("Bright_Cyan_Background", "\033[106m");
        put("Bright_White_Background", "\033[107m");

        // 8-bit (256-color) Foreground (example selections)
        put("Orange", "\033[38;5;208m");      // Vivid orange
        put("Pink", "\033[38;5;205m");        // Pink
        put("Lime", "\033[38;5;154m");        // Bright lime green
        put("Teal", "\033[38;5;6m");          // Teal
        put("Indigo", "\033[38;5;54m");       // Indigo
        put("Maroon", "\033[38;5;88m");       // Dark red/maroon
        put("Gold", "\033[38;5;178m");        // Gold

        // 8-bit (256-color) Background (example selections)
        put("Orange_Background", "\033[48;5;208m");
        put("Pink_Background", "\033[48;5;205m");
        put("Lime_Background", "\033[48;5;154m");
        put("Teal_Background", "\033[48;5;6m");
        put("Indigo_Background", "\033[48;5;54m");
        put("Maroon_Background", "\033[48;5;88m");
        put("Gold_Background", "\033[48;5;178m");

        // RGB (True Color) Support (if terminal supports it)
        // Format: \033[38;2;R;G;Bm (foreground) or \033[48;2;R;G;Bm (background)
        put("Custom_RGB_Foreground", "\033[38;2;255;100;0m");  // Example: Orange
        put("Custom_RGB_Background", "\033[48;2;50;50;150m");  // Example: Dark blue
    }};



    public Map() {
        this.startingCoordinate = new Coordinate(0, 0);
        this.endingCoordinate = new Coordinate(150, 160);
        declareTextures();

    }

    // A function to print map
    public void printMap(int x , int y, int size) {
        String[][] map = new String[size][size];

        for(int j = 0 ; j < size; j++){
            for(int i = 0 ; i < size; i++){
                boolean printedPlayer = false;
                boolean printedNPC = false;
                Coordinate coordinate = new Coordinate(x + i,y + j);
                Tile tile = findTile(coordinate);

                if(!printedPlayer && !printedNPC) {
                    if (tile == null) {
                        map[i][j] = colorMap.get("Purple") + colorMap.get("White_Background") + "N" + colorMap.get("Reset");


                    } else if (tile.getTileType() == TileType.FARM) {
                        boolean printed = false;
                        for (Good good : tile.getGoods()) {
                            if (good instanceof ForagingTree) {
                                map[i][j] = colorMap.get("Black") + colorMap.get("Green_Background") + "T" + colorMap.get("Reset");

                                printed = true;
                            } else if (good instanceof FarmingTree) {
                                map[i][j] = colorMap.get("Black") + colorMap.get("Green_Background") + "T" + colorMap.get("Reset");

                                printed = true;
                            } else if (good instanceof FarmingTreeSapling) {
                                map[i][j] = colorMap.get("Black") + colorMap.get("Green_Background") + "T" + colorMap.get("Reset");

                                printed = true;
                            } else if(good instanceof ForagingSeed) {
                                map[i][j] = colorMap.get("Black") + colorMap.get("Green_Background") + "S" + colorMap.get("Reset");
                                printed = true;
                            } else if(good instanceof ForagingCrop | good instanceof FarmingCrop) {
                                map[i][j] = colorMap.get("Black") + colorMap.get("Green_Background") + "C" + colorMap.get("Reset");
                                printed = true;
                            }
                        }
                        if (!printed) {
                            map[i][j] = colorMap.get("Bright_Green_Background") + " " + colorMap.get("Reset");

                        }
                    } else if (tile.getTileType() == TileType.WATER) {
                        map[i][j] = colorMap.get("Custom_RGB_Background") + " " + colorMap.get("Reset");

                    } else if (tile.getTileType() == TileType.GREEN_HOUSE) {
                        boolean printed = false;
                        for (Good good : tile.getGoods()) {
                            if (good instanceof ForagingTree) {
                                map[i][j] = colorMap.get("White_Background") + colorMap.get("Yellow") + "T" + colorMap.get("Reset");

                                printed = true;
                            } else if (good instanceof FarmingTree) {
                                map[i][j] = colorMap.get("White_Background") + colorMap.get("Yellow") + "T" + colorMap.get("Reset");

                                printed = true;
                            } else if (good instanceof FarmingTreeSapling) {
                                map[i][j] = colorMap.get("White_Background") + colorMap.get("Yellow") + "T" + colorMap.get("Reset");

                                printed = true;
                            }
                        }
                        if (!printed) {
                            map[i][j] = colorMap.get("White_Background") + " " + colorMap.get("Reset");

                        }
                    } else if (tile.getTileType() == TileType.PLAYER_BUILDING ) {
                        map[i][j] = colorMap.get("Yellow_Background") + "H" + colorMap.get("Reset");

                    } else if (tile.getTileType() == TileType.GAME_BUILDING) {
                        map[i][j] = colorMap.get("Yellow_Background") + "U" + colorMap.get("Reset");

                    } else if (tile.getTileType() == TileType.ROAD) {
                        map[i][j] = colorMap.get("Purple") + colorMap.get("Gray_Background") + "-" + colorMap.get("Reset");


                    } else if (tile.getTileType() == TileType.QUARRY) {
                        boolean printed = false;
                        for (Good good : tile.getGoods()) {
                            if (good instanceof ForagingMineral) {
                                map[i][j] = colorMap.get("Maroon") + colorMap.get("Gray_Background") + "s" + colorMap.get("Reset");

                                printed = true;
                            }
                        }
                        if (!printed) {
                            map[i][j] = colorMap.get("Gray_Background") + "-" + colorMap.get("Reset");

                        }
                    } else if (tile.getTileType() == TileType.PLAIN) {
                        boolean printed = false;
                        for (Good good : tile.getGoods()) {
                            if (good instanceof ForagingTree) {
                                map[i][j] = colorMap.get("Black") + colorMap.get("Green_Background") + "T" + colorMap.get("Reset");

                                printed = true;
                            } else if (good instanceof FarmingTree) {
                                map[i][j] = colorMap.get("Black") + colorMap.get("Green_Background") + "T" + colorMap.get("Reset");

                                printed = true;
                            } else if (good instanceof FarmingTreeSapling) {
                                map[i][j] = colorMap.get("Black") + colorMap.get("Green_Background") + "T" + colorMap.get("Reset");

                                printed = true;
                            }
                        }
                        if (!printed) {
                            map[i][j] = colorMap.get("Red") + colorMap.get("Bright_Green_Background") + "-" + colorMap.get("Reset");

                        }

                    } else if (tile.getTileType() == TileType.STONE_WALL) {
                        map[i][j] = colorMap.get("Black") + colorMap.get("Indigo_Background") + "|" + colorMap.get("Reset");

                    } else if (tile.getTileType() == TileType.BEACH) {
                        map[i][j] = colorMap.get("Gray") + colorMap.get("Yellow_Background") + "B" + colorMap.get("Reset");

                    } else if (tile.getTileType() == TileType.SQUARE) {
                        map[i][j] = colorMap.get("Black") + colorMap.get("Red_Background") + "O" + colorMap.get("Reset");

//                    } else if (tile.getTileType() == TileType.PLOWED_FARM) {
//                        map[i][j] = colorMap.get("Black") + colorMap.get("Bright_Green_Background") + "p" + colorMap.get("Reset");

                    } else if (tile.getTileType() == TileType.SHIPPING_BIN) {
                        map[i][j] = colorMap.get("Gray") + colorMap.get("Gold_Background") + " " + colorMap.get("Reset");

                    }
                }
            }
        }
        for(int j = 0 ; j < size; j++) {
            for (int i = 0; i < size; i++) {
                Coordinate coordinate = new Coordinate(x + i,y + j);
                Tile tile = findTile(coordinate);
                if (tile != null) {
                    if(tile.isWatered()) {
                        map[i][j] =  colorMap.get("Teal_Background") + " " + colorMap.get("Reset");
                    }
                    for (Good good : tile.getGoods()) {
                        if(good != null) {
                            if (good instanceof ForagingCrop) {
                                map[i][j] = colorMap.get("Maroon") + colorMap.get("Bright_Green_Background") + "c" + colorMap.get("Reset");
                                if(tile.isWatered()){
                                    map[i][j] = colorMap.get("Maroon") + colorMap.get("Teal_Background") + "c" + colorMap.get("Reset");
                                }
                            } else if (good instanceof ForagingSeed) {
                                map[i][j] = colorMap.get("Pink") + colorMap.get("Bright_Green_Background") + "s" + colorMap.get("Reset");
                                if(tile.isWatered()){
                                    map[i][j] = colorMap.get("Pink") + colorMap.get("Teal_Background") + "s" + colorMap.get("Reset");
                                }
                            } else if (good.getName().equals("Grass")) {
                                map[i][j] = colorMap.get("Lime") + colorMap.get("Bright_Green_Background") + "g" + colorMap.get("Reset");
                                if(tile.isWatered()){
                                    map[i][j] = colorMap.get("Lime") + colorMap.get("Teal_Background") + "g" + colorMap.get("Reset");
                                }
                            }
                        }
                    }
//                    if(tile.getTileType() == TileType.PLOWED_FARM) {
//                        if(tile.isWatered()) {
//                            map[i][j] = colorMap.get("Teal_Background") + "p" + colorMap.get("Reset");
//                        }
//                    }
                }

                int counter = 0;
                for(Player player: AppClient.getCurrentGame().getPlayers()) {
                    counter++;
                    if (player.getCoordinate().equals(coordinate)) {
                        map[i][j] = colorMap.get("White") + counter + colorMap.get("Reset");

                    }
                }
                for(NPC npc: AppClient.getCurrentGame().getNPCs()) {
                    if (npc.getType().getCoordinate().equals(coordinate)) {
                        String name = npc.getType().getName();
                        String firstName = name.substring(0, 1).toUpperCase();
                        map[i][j] = colorMap.get("Black") + colorMap.get("Red_Background") + firstName + colorMap.get("Reset");

                    }
                }
                for(Animal animal: AppClient.getCurrentGame().getMap().allAnimals()) {
                    if (animal.getCoordinate().equals(coordinate)) {
                        String name = animal.getName();
                        String firstName = name.substring(0, 1).toUpperCase();
                        map[i][j] = colorMap.get("Black") + colorMap.get("Red_Background") + firstName + colorMap.get("Reset");

                    }
                }
            }
        }
        System.out.print("\n\n\n\n");
        for(int j = 0 ; j < size; j++) {
            System.out.print("\n");
            for (int i = 0; i < size; i++) {
                System.out.print(map[i][j]);
            }
        }

    }


    private Table mapTable;
    private Stack[][] mapCells = new Stack[160][170];
    public ScrollPane createGraphicalMap() {
        mapTable = new Table(); // use the field instead of a local variable
        mapTable.defaults().width(6).height(6);

        rebuildMapTable(); // extract the build logic into a reusable method

        ScrollPane scrollPane = new ScrollPane(mapTable, Assets.getInstance().getSkin());
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setForceScroll(false, true);
        scrollPane.setSize(980, 780);

        return scrollPane;
    }
    private void rebuildMapTable() {
        mapTable.clear();

        for (int j = 1; j < 161; j++) {
            mapTable.row();
            for (int i = 0; i < 150; i++) {
                Coordinate coordinate = new Coordinate(i, 160 - j);
                Tile tile = findTile(coordinate);
                Image img = null_img;

                if (tile == null) {
                    img = empty_img;
                } else {
                    if (tile.isWatered()) {
                        img = wateredFarm_img;
                    }

                    for (Good good : tile.getGoods()) {
                        if (good instanceof ForagingCrop) img = Crop_img;
                        else if (good instanceof ForagingSeed) img = seed_img;
                        else if ("Grass".equals(good.getName())) img = tree_img;
                    }

                    for (Player player : AppClient.getCurrentGame().getPlayers()) {
                        if (player.getCoordinate().equals(coordinate)) img = player_img;
                    }

                    for (NPC npc : AppClient.getCurrentGame().getNPCs()) {
                        if (npc.getType().getCoordinate().equals(coordinate)) {
                            img = switch (npc.getType().getName()) {
                                case "Abigail" -> abigail_img;
                                case "Clint" -> clint_img;
                                case "Gus" -> gus_img;
                                case "Harvey" -> harvey_img;
                                case "Leah" -> leah_img;
                                case "Marnie" -> marnie_img;
                                case "Morris" -> morris_img;
                                case "Pierre" -> pierre_img;
                                case "Robin" -> robin_img;
                                case "Sebastian" -> sebastian_img;
                                case "Willy" -> willy_img;
                                default -> mushroomTree_img;
                            };
                        }
                    }

                    for (Animal animal : AppClient.getCurrentGame().getMap().allAnimals()) {
                        if (animal.getCoordinate().equals(coordinate)) {
                            img = mushroomTree_img;
                        }
                    }
                }

                Stack cellStack = new Stack();
                Image bg = new Image(determineTileBackground(tile).getDrawable());
                Image fg = new Image(img.getDrawable());

                bg.setSize(6, 6);
                fg.setSize(6, 6);

                cellStack.add(bg);
                cellStack.add(fg);


                mapCells[i][160 - j] = cellStack;
                mapTable.add(cellStack).width(6).height(6);
            }
        }
    }
    public void updateMap() {
        if (mapCells[App.getCurrentGame().getCurrentPlayer().getCoordinate().getX()][App.getCurrentGame().getCurrentPlayer().getCoordinate().getY()] != null) {
            updateMapAround(App.getCurrentGame().getCurrentPlayer().getLastCoordinate().getX() , App.getCurrentGame().getCurrentPlayer().getLastCoordinate().getY());
            updateMapAround(App.getCurrentGame().getCurrentPlayer().getCoordinate().getX() , App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
        }
    }
    private void updateMapAround(int x , int y) {

                Stack cellStack = mapCells[x][y];
                cellStack.clearChildren(); // Remove all previous images in the stack

                Coordinate coord = new Coordinate(x,  y);
                Tile tile = findTile(coord);

                // Background image
                Image bg = new Image(determineTileBackground(tile).getDrawable());
                bg.setSize(6, 6);
                cellStack.add(bg);

                // Determine what foreground image to show:
                Image fg = null_img; // default

                if (tile == null) {
                    fg = empty_img;
                } else {
                    if (tile.isWatered()) {
                        fg = wateredFarm_img;
                    }

                    for (Good good : tile.getGoods()) {
                        if (good instanceof ForagingCrop) fg = Crop_img;
                        else if (good instanceof ForagingSeed) fg = seed_img;
                        else if ("Grass".equals(good.getName())) fg = tree_img;
                    }

                    for (Player player : App.getCurrentGame().getPlayers()) {
                        if (player.getCoordinate().equals(coord)) {
                            fg = player_img;
                        }
                    }

                    for (NPC npc : App.getCurrentGame().getNPCs()) {
                        if (npc.getType().getCoordinate().equals(coord)) {
                            fg = switch (npc.getType().getName()) {
                                case "Abigail" -> abigail_img;
                                case "Clint" -> clint_img;
                                case "Gus" -> gus_img;
                                case "Harvey" -> harvey_img;
                                case "Leah" -> leah_img;
                                case "Marnie" -> marnie_img;
                                case "Morris" -> morris_img;
                                case "Pierre" -> pierre_img;
                                case "Robin" -> robin_img;
                                case "Sebastian" -> sebastian_img;
                                case "Willy" -> willy_img;
                                default -> mushroomTree_img;
                            };
                        }
                    }

                    for (Animal animal : App.getCurrentGame().getMap().allAnimals()) {
                        if (animal.getCoordinate().equals(coord)) {
                            fg = mushroomTree_img;
                        }
                    }
                }

                fg.setSize(6, 6);
                cellStack.add(fg);

    }
    private Image determineTileBackground(Tile tile) {
        if (tile == null) return empty_img;
        if (tile.isWatered()) return wateredFarm_img;

        return switch (tile.getTileType()) {
            case FARM -> farm_Background_img;
            case WATER -> water_img;
            case GREEN_HOUSE -> greenhouse_img;
            case PLAYER_BUILDING -> playerBuilding_img;
            case ROAD -> road_img;
            case QUARRY -> quarry_img;
            case BEACH -> beach_img;
            case SQUARE -> square_img;
            case STONE_WALL -> wall_img;
            default -> plain_img;
        };
    }
    public void declareTextures(){
        farm_Background = new Texture("GameAssets/Map/Farm.png");
        empty = new Texture("GameAssets/Map/Border.png");
        null_txr = new Texture("GameAssets/Map/Null.png");
        wall = new Texture("GameAssets/Map/Wall.png");
        water = new Texture("GameAssets/Map/Water.png");
        greenhouse = new Texture("GameAssets/Map/Greenhouse.png");
        playerBuilding = new Texture("GameAssets/Map/House.png");
        quarry = new Texture("GameAssets/Map/Quarry.png");
        road = new Texture("GameAssets/Map/Road.png");
        beach = new Texture("GameAssets/Map/Beach.png");
        square = new Texture("GameAssets/Map/Square.png");
        wateredFarm = new Texture("GameAssets/Map/WateredFarm.png");
        tree = new Texture("GameAssets/Map/Tree.png");
        seed = new Texture("GameAssets/Map/Seed.png");
        Crop = new Texture("GameAssets/Map/Crop.png");
        playerTexture = new Texture("GameAssets/Map/Player.png");
        mushroomTree = new Texture("GameAssets/Map/MushroomTree.png");
        plain = new Texture("GameAssets/Map/Plain.png");
        abigail = new Texture("GameAssets/Map/Abigail.png");
        clint = new Texture("GameAssets/Map/Clint.png");
        gus = new Texture("GameAssets/Map/Gus.png");
        harvey = new Texture("GameAssets/Map/Harvey.png");
        leah = new Texture("GameAssets/Map/Leah.png");
        marnie = new Texture("GameAssets/Map/Marnie.png");
        morris = new Texture("GameAssets/Map/Morris.png");
        pierre = new Texture("GameAssets/Map/Pierre.png");
        robin = new Texture("GameAssets/Map/Robin.png");
        sebastian = new Texture("GameAssets/Map/Sebastian.png");
        willy = new Texture("GameAssets/Map/Willy.png");

// Create Image objects for all textures
        farm_Background_img = new Image(new TextureRegionDrawable(farm_Background));
        empty_img = new Image(new TextureRegionDrawable(empty));
        null_img = new Image(new TextureRegionDrawable(null_txr));
        wall_img = new Image(new TextureRegionDrawable(wall));
        water_img = new Image(new TextureRegionDrawable(water));
        greenhouse_img = new Image(new TextureRegionDrawable(greenhouse));
        playerBuilding_img = new Image(new TextureRegionDrawable(playerBuilding));
        quarry_img = new Image(new TextureRegionDrawable(quarry));
        road_img = new Image(new TextureRegionDrawable(road));
        beach_img = new Image(new TextureRegionDrawable(beach));
        square_img = new Image(new TextureRegionDrawable(square));
        wateredFarm_img = new Image(new TextureRegionDrawable(wateredFarm));
        tree_img = new Image(new TextureRegionDrawable(tree));
        seed_img = new Image(new TextureRegionDrawable(seed));
        Crop_img = new Image(new TextureRegionDrawable(Crop));
        player_img = new Image(new TextureRegionDrawable(playerTexture));
        mushroomTree_img = new Image(new TextureRegionDrawable(mushroomTree));
        plain_img = new Image(new TextureRegionDrawable(plain));
        abigail_img = new Image(new TextureRegionDrawable(abigail));
        clint_img = new Image(new TextureRegionDrawable(clint));
        gus_img = new Image(new TextureRegionDrawable(gus));
        harvey_img = new Image(new TextureRegionDrawable(harvey));
        leah_img = new Image(new TextureRegionDrawable(leah));
        marnie_img = new Image(new TextureRegionDrawable(marnie));
        morris_img = new Image(new TextureRegionDrawable(morris));
        pierre_img = new Image(new TextureRegionDrawable(pierre));
        robin_img = new Image(new TextureRegionDrawable(robin));
        sebastian_img = new Image(new TextureRegionDrawable(sebastian));
        willy_img = new Image(new TextureRegionDrawable(willy));

// Set sizes for all images (6x6)
        int n = 8;
        farm_Background_img.setSize(n, n);
        empty_img.setSize(n, n);
        null_img.setSize(n, n);
        wall_img.setSize(n, n);
        water_img.setSize(n, n);
        greenhouse_img.setSize(n, n);
        playerBuilding_img.setSize(n, n);
        quarry_img.setSize(n, n);
        road_img.setSize(n, n);
        beach_img.setSize(n, n);
        square_img.setSize(n, n);
        wateredFarm_img.setSize(n, n);
        tree_img.setSize(n, n);
        seed_img.setSize(n, n);
        Crop_img.setSize(n, n);
        player_img.setSize(n, n);
        mushroomTree_img.setSize(n, n);
        plain_img.setSize(n, n);
        abigail_img.setSize(n, n);
        clint_img.setSize(n, n);
        gus_img.setSize(n, n);
        harvey_img.setSize(n, n);
        leah_img.setSize(n, n);
        marnie_img.setSize(n, n);
        morris_img.setSize(n, n);
        pierre_img.setSize(n, n);
        robin_img.setSize(n, n);
        sebastian_img.setSize(n ,n);
        willy_img.setSize(n, n);
    }


    public void setFarms(ArrayList<Farm> farms) {
        this.farms.addAll(farms);
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

    public static Tile findTile(Coordinate coordinate) {
        for (Tile tile : AppClient.getCurrentGame().getMap().getTiles()) {
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

    public void generateRandomForagingCrops(int probability) {
        for (int i = 0 ; i < 140 ; i++){
            for(int j = 0 ; j < 160 ; j++){
                int random = (int)Math.floor((Math.random()*100));
                Coordinate coordinate = new Coordinate(i , j);
                Tile tile = findTile(coordinate);
                if(tile != null){
                    if(tile.getTileType().equals(TileType.FARM) || tile.getTileType().equals(TileType.PLAIN)){
                        if(random >= probability){
                            int randomForaging = (int)Math.floor((Math.random()*9));
                            switch (AppClient.getCurrentGame().getDateTime().getSeasonOfYear().getName()){
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

    public void generateRandomForagingSeed(int probability) {
        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 160; j++) {
                int random = (int) Math.floor((Math.random() * 100));
                Coordinate coordinate = new Coordinate(i, j);
                Tile tile = findTile(coordinate);

                if (tile != null && tile.getTileType().equals(TileType.PLOWED_FARM)) {
                    if (random >= probability) {
                        int randomForaging = (int) Math.floor((Math.random() * 9));
                        String season = AppClient.getCurrentGame().getDateTime().getSeasonOfYear().getName();

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
    public void generateRandomMinerals(int probability) {
        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 160; j++) {
                int random = (int) Math.floor((Math.random() * 100));
                Coordinate coordinate = new Coordinate(i, j);
                Tile tile = findTile(coordinate);

                if (tile != null && tile.getTileType().equals(TileType.QUARRY)) {
                    if (random >= probability) {
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
                                tile.addGood(Good.newGood(ForagingMineralType.COPPER_ORE));
                                break;
                            case 13:
                                tile.addGood(Good.newGood(ForagingMineralType.IRON_ORE));
                                break;
                            case 14:
                                tile.addGood(Good.newGood(ForagingMineralType.GOLD_ORE));
                                break;
                            case 15:
                                tile.addGood(Good.newGood(ForagingMineralType.IRIDIUM_ORE));
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

    public void generateRandomForagingTrees(int probability) {
        for (int i = 0 ; i < 140 ; i++){
            for(int j = 0 ; j < 160 ; j++){
                int random = (int)Math.floor((Math.random()*100));
                Coordinate coordinate = new Coordinate(i , j);
                Tile tile = findTile(coordinate);
                if(tile != null){
                    if(tile.getTileType().equals(TileType.FARM) || tile.getTileType().equals(TileType.PLAIN)  ){
                        if(random >= probability){
                            int randomForaging = (int)Math.floor((Math.random()*5));

                            switch (randomForaging) {
                                case 0:
                                    tile.addGood(Good.newGood(ForagingTreeType.ACORNS.getFarmingTreeType()));
                                    break;
                                case 1:
                                    tile.addGood(Good.newGood(ForagingTreeType.MAHOGANY_SEEDS.getFarmingTreeType()));
                                    break;
                                case 2:
                                    tile.addGood(Good.newGood(ForagingTreeType.MUSHROOM_TREE_SEEDS.getFarmingTreeType()));
                                    break;
                                case 3:
                                    tile.addGood(Good.newGood(ForagingTreeType.MAPLE_SEEDS.getFarmingTreeType()));
                                    break;
                                case 4:
                                    tile.addGood(Good.newGood(ForagingTreeType.PINE_CONES.getFarmingTreeType()));
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void Fertilize(){
        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 160; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Tile tile = findTile(coordinate);
                Iterator<Good> iterator = tile.getGoods().iterator();

                while (iterator.hasNext()) {
                    Good good = iterator.next();
                    if (good.getType() != null && good.getType().equals(ProductType.DELUXE_RETAINING_SOIL)) {
                        iterator.remove();
                        if (tile.getTileType().equals(TileType.PLOWED_FARM) || tile.getTileType().equals(TileType.FARM)) {
                            tile.setWatered(true);
                        }
                    } else if (good.getType() != null && good.getType().equals(ProductType.QUALITY_RETAINING_SOIL)) {
                        int rand = (int) Math.floor((Math.random() * 3));
                        iterator.remove();
                        if (tile.getTileType().equals(TileType.PLOWED_FARM) || tile.getTileType().equals(TileType.FARM)) {
                            if (!(rand == 0)) {
                                tile.setWatered(true);
                            }
                        }
                    } else if (good.getType() != null && good.getType().equals(ProductType.BASIC_RETAINING_SOIL)) {
                        int rand = (int) Math.floor((Math.random() * 3));
                        iterator.remove();
                        if (tile.getTileType().equals(TileType.PLOWED_FARM) || tile.getTileType().equals(TileType.FARM)) {
                            if (rand == 0) {
                                tile.setWatered(true);
                            }
                        }
                    } else if (good.getType() != null && good.getType().equals(ProductType.SPEED_GRO)) {
                        Iterator<Good> iterator2 = tile.getGoods().iterator();
                        ArrayList<Good> goodsToAdd = new ArrayList<>();
                        while (iterator2.hasNext()) {
                            Good good2 = iterator2.next();
                            if (good2 instanceof ForagingSeed) {
                                ForagingSeed seed = (ForagingSeed) good2;
                                seed.dailyChange();
                                if (seed.isCrop()) {
                                    iterator2.remove();
                                    goodsToAdd.add(Good.newGood(seed.getCropType()));
                                }
                            } else if (good instanceof FarmingTreeSapling) {
                                FarmingTreeSapling sapling = (FarmingTreeSapling) good;
                                sapling.dailyChange();
                                if (sapling.isTree()) {
                                    iterator.remove();
                                    goodsToAdd.add(Good.newGood(sapling.getTreeType()));
                                }
                            } else if (good instanceof ForagingMixedSeed) {
                                ForagingMixedSeed seed = (ForagingMixedSeed) good;
                                seed.dailyChange();
                                if (seed.isCrop()) {
                                    iterator.remove();
                                    goodsToAdd.add(Good.newGood(seed.getCropType()));
                                }
                            }

                            Game.addGoodToTile(tile, goodsToAdd);
                        }
                    }
                }
            }
        }
    }

    public void generateRandomGrassTrees(int probability) {
        for (int i = 0 ; i < 140 ; i++){
            for(int j = 0 ; j < 160 ; j++){
                int random = (int)Math.floor((Math.random()*100));
                Coordinate coordinate = new Coordinate(i , j);
                Tile tile = findTile(coordinate);
                if(tile != null){
                    if(tile.getTileType().equals(TileType.FARM) || tile.getTileType().equals(TileType.PLAIN)  ){
                        if(random >= probability){
                            tile.addGood(Good.newGood(ProductType.GRASS));
                        }
                    }
                }
            }
        }
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles.addAll(tiles);
    }

    public void setGameBuildings(ArrayList<GameBuilding> gameBuildings) {
        this.gameBuildings.addAll(gameBuildings);
    }

    public void setShippingBins(ArrayList<ShippingBin> shippingBins) {
        this.shippingBins.addAll(shippingBins);
    }

    public ArrayList<Farm> getFarms() {
        return farms;
    }

    public ArrayList<ShippingBin> getShippingBins() {
        return shippingBins;
    }
}
