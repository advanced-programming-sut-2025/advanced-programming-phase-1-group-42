package com.StardewValley.models;

import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.game_structure.ShippingBin;
import com.StardewValley.models.game_structure.weathers.*;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.artisans.Artisan;
import com.StardewValley.models.goods.artisans.ArtisanType;
import com.StardewValley.models.goods.craftings.Crafting;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.farmings.*;
import com.StardewValley.models.goods.fishs.Fish;
import com.StardewValley.models.goods.fishs.FishType;
import com.StardewValley.models.goods.foods.Food;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.*;
import com.StardewValley.models.goods.products.Product;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.goods.recipes.*;
import com.StardewValley.models.goods.tools.Tool;
import com.StardewValley.models.goods.tools.ToolType;
import com.StardewValley.models.interactions.Animals.AnimalProduct;
import com.StardewValley.models.interactions.Animals.AnimalProductsType;
import com.StardewValley.models.interactions.User;
import com.StardewValley.models.interactions.game_buildings.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


public class JSONUtils {
	private static final GsonBuilder gsonBuilder = new GsonBuilder();
	private static final Gson gson;

	static {
        TypeAdapter<Good> goodAdapter = new TypeAdapter<Good>() {
            @Override public void write(JsonWriter out, Good value) throws IOException {
                if (value == null) { out.nullValue(); return; }

                // Write the “object” form (keep it consistent with your server)
                out.beginObject();
                // Don't rely on getSimpleName(); use a stable id if you can.
                out.name("type").value(value.getTypeId());
                out.name("data");
                gson.toJson(value, value.getClass(), out);
                out.endObject();

                // If you ever want to emit the string form instead, you could:
                // out.value(value.getId()); return;
            }

            @Override public Good read(JsonReader in) throws IOException {
                JsonToken t = in.peek();
                if (t == JsonToken.NULL) { in.nextNull(); return null; }

                // Handle the simple string form: "COPPER_ORE"
                if (t == JsonToken.STRING) {
                    String id = in.nextString();
                    return Good.fromId(id);         // implement: map id -> concrete Good
                }

                // Handle the object form
                if (t != JsonToken.BEGIN_OBJECT) {
                    throw new JsonSyntaxException("Expected object or string for Good but was " + t);
                }

                JsonObject obj = com.google.gson.internal.Streams.parse(in).getAsJsonObject();
                String type = obj.get("type").getAsString();
                JsonElement data = obj.get("data");

                switch (type) {
                    case "Artisan":             return gson.fromJson(data, Artisan.class);
                    case "Crafting":            return gson.fromJson(data, Crafting.class);
                    case "FarmingTree":         return gson.fromJson(data, FarmingTree.class);
                    case "AnimalProduct":       return gson.fromJson(data, AnimalProduct.class);
                    case "CookingRecipe":       return gson.fromJson(data, CookingRecipe.class);
                    case "CraftingRecipe":      return gson.fromJson(data, CraftingRecipe.class);
                    case "Farming":             return gson.fromJson(data, Farming.class);
                    case "FarmingCrop":         return gson.fromJson(data, FarmingCrop.class);
                    case "FarmingTreeSapling":  return gson.fromJson(data, FarmingTreeSapling.class);
                    case "Fish":                return gson.fromJson(data, Fish.class);
                    case "Food":                return gson.fromJson(data, Food.class);
                    case "Foraging":            return gson.fromJson(data, Foraging.class);
                    case "ForagingCrop":        return gson.fromJson(data, ForagingCrop.class);
                    case "ForagingMineral":     return gson.fromJson(data, ForagingMineral.class);
                    case "ForagingMixedSeed":   return gson.fromJson(data, ForagingMixedSeed.class);
                    case "ForagingSeed":        return gson.fromJson(data, ForagingSeed.class);
                    case "ForagingTree":        return gson.fromJson(data, ForagingTree.class);
                    case "Product":             return gson.fromJson(data, Product.class);
                    case "Recipe":              return gson.fromJson(data, Recipe.class);
                    case "ShippingBin":         return gson.fromJson(data, ShippingBin.class);
                    case "Tool":                return gson.fromJson(data, Tool.class);
                    default:
                        throw new JsonParseException("Unknown Good type: " + type);
                }
            }
        };

        TypeAdapter<GameBuilding> gameBuildingTypeAdapter = new TypeAdapter<GameBuilding>() {
            @Override
            public void write(JsonWriter out, GameBuilding value) throws IOException {
                out.beginObject();
                out.name("type").value(value.getClass().getSimpleName());
                out.name("data");
                gson.toJson(value, value.getClass(), out);
                out.endObject();
            }

            @Override public GameBuilding read(JsonReader in) throws IOException {
                JsonObject obj = JsonParser.parseReader(in).getAsJsonObject();
                String type = obj.get("type").getAsString();
                JsonElement data = obj.get("data");

                switch (type) {
                    case "CarpenterShop": return gson.fromJson(data, CarpenterShop.class);
                    case "PierreGeneralStore":  return gson.fromJson(data, PierreGeneralStore.class);
                    case "JojaMart":  return gson.fromJson(data, JojaMart.class);
                    case "FishShop": return gson.fromJson(data, FishShop.class);
                    case "TheStarDropSaloon": return gson.fromJson(data, TheStarDropSaloon.class);
                    case "MarnieRanch":  return gson.fromJson(data, MarnieRanch.class);
                    case "Blacksmith": return gson.fromJson(data, Blacksmith.class);

                    // ... all other Good subtypes
                    default: throw new JsonParseException("Unknown Good type: " + type);
                }
            }
        };

        TypeAdapter<GoodType> goodTypeTypeAdapter = new TypeAdapter<GoodType>() {
            @Override
            public void write(JsonWriter out, GoodType value) throws IOException {
                if (value == null) { out.nullValue(); return; }

                out.beginObject();
                out.name("type").value(value.getClass().getSimpleName());
                out.name("data");
                gson.toJson(value, value.getClass(), out);
                out.endObject();
            }

            @Override
            public GoodType read(JsonReader in) throws IOException {
                JsonToken t = in.peek();
                if (t == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }

                // Handle simple string form: "COPPER_ORE"
                if (t == JsonToken.STRING) {
                    String id = in.nextString();
                    return Good.newGoodType(id); // You already have this helper
                }

                // Handle full object form
                if (t != JsonToken.BEGIN_OBJECT) {
                    throw new JsonSyntaxException(
                        "Expected GoodType object or string but was " + t
                    );
                }

                JsonObject obj = com.google.gson.internal.Streams.parse(in).getAsJsonObject();
                String type = obj.get("type").getAsString();
                JsonElement data = obj.get("data");

                switch (type) {
                    case "AnimalProductsType": return gson.fromJson(data, AnimalProductsType.class);
                    case "ArtisanType": return gson.fromJson(data, ArtisanType.class);
                    case "CookingRecipeType": return gson.fromJson(data, CookingRecipeType.class);
                    case "CraftingRecipeType": return gson.fromJson(data, CraftingRecipeType.class);
                    case "CraftingType": return gson.fromJson(data, CraftingType.class);
                    case "FarmingCropType": return gson.fromJson(data, FarmingCropType.class);
                    case "FarmingTreeSaplingType": return gson.fromJson(data, FarmingTreeSaplingType.class);
                    case "FarmingTreeType": return gson.fromJson(data, FarmingTreeType.class);
                    case "FishType": return gson.fromJson(data, FishType.class);
                    case "FoodType": return gson.fromJson(data, FoodType.class);
                    case "ForagingCropType": return gson.fromJson(data, ForagingCropType.class);
                    case "ForagingMineralType": return gson.fromJson(data, ForagingMineralType.class);
                    case "ForagingSeedType": return gson.fromJson(data, ForagingSeedType.class);
                    case "ForagingMixedSeedType": return gson.fromJson(data, ForagingMixedSeedType.class);
                    case "ForagingTreeType": return gson.fromJson(data, ForagingTreeType.class);
                    case "ProductType": return gson.fromJson(data, ProductType.class);
                    case "ToolType": return gson.fromJson(data, ToolType.class);
                    default:
                        throw new JsonParseException("Unknown GoodType type: " + type);
                }
            }
        };

        TypeAdapter<Weather> weatherTypeAdapter = new TypeAdapter<Weather>() {
            @Override
            public void write(JsonWriter out, Weather value) throws IOException {
                out.beginObject();
                out.name("type").value(value.getName());
                out.name("data");
                gson.toJson(value, value.getClass(), out);
                out.endObject();
            }

            @Override
            public Weather read(JsonReader in) throws IOException {
                JsonObject obj = JsonParser.parseReader(in).getAsJsonObject();
                String type = obj.get("type").getAsString();
                JsonElement data = obj.get("data");
                switch (type) {
                    case "Sunny": return gson.fromJson(data, Sunny.class);
                    case "Rain":  return gson.fromJson(data, Rain.class);
                    case "Snow":  return gson.fromJson(data, Snow.class);
                    case "Storm": return gson.fromJson(data, Storm.class);
                    default: throw new JsonParseException("Unknown type: " + type);
                }
            }
        };
        gson = new GsonBuilder()
            .registerTypeAdapter(Weather.class, weatherTypeAdapter)
            .registerTypeAdapter(Good.class, goodAdapter)
            .registerTypeAdapter(GameBuilding.class, gameBuildingTypeAdapter)
            .registerTypeAdapter(GoodType.class, goodTypeTypeAdapter)
            .create();

		gsonBuilder.setPrettyPrinting();
//		gson = gsonBuilder.create();
	}

	public synchronized static String toJson(Message message) {
		return gson.toJson(message);
	}

	public synchronized static Message fromJson(String json) {
		return gson.fromJson(json, Message.class);
	}

    public synchronized static User fromJsonUser(String json) {
        return gson.fromJson(json, User.class);
    }

    public synchronized static String toJsonUser(User user) {
        return gson.toJson(user, User.class);
    }

    public synchronized static Labi fromJsonLabi(String json) {
        return gson.fromJson(json, Labi.class);
    }

    public synchronized static String toJsonLabi(Labi labi) {
        return gson.toJson(labi, Labi.class);
    }

    public synchronized static Game fromJsonGame(String json) {
        return gson.fromJson(json, Game.class);
    }

    public synchronized static String toJsonLabi(Game game) {
        return gson.toJson(game, Game.class);
    }

    public synchronized static Gson getGson () {
        return gson;
    }
}
