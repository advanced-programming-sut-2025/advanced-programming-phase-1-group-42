package com.StardewValley.models;

import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.game_structure.ShippingBin;
import com.StardewValley.models.game_structure.weathers.*;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.artisans.Artisan;
import com.StardewValley.models.goods.craftings.Crafting;
import com.StardewValley.models.goods.farmings.Farming;
import com.StardewValley.models.goods.farmings.FarmingCrop;
import com.StardewValley.models.goods.farmings.FarmingTree;
import com.StardewValley.models.goods.farmings.FarmingTreeSapling;
import com.StardewValley.models.goods.fishs.Fish;
import com.StardewValley.models.goods.foods.Food;
import com.StardewValley.models.goods.foragings.*;
import com.StardewValley.models.goods.products.Product;
import com.StardewValley.models.goods.recipes.CookingRecipe;
import com.StardewValley.models.goods.recipes.CraftingRecipe;
import com.StardewValley.models.goods.recipes.Recipe;
import com.StardewValley.models.goods.tools.Tool;
import com.StardewValley.models.interactions.Animals.AnimalProduct;
import com.StardewValley.models.interactions.User;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


public class JSONUtils {
	private static final GsonBuilder gsonBuilder = new GsonBuilder();
	private static final Gson gson;

	static {
        TypeAdapter<Good> goodAdapter = new TypeAdapter<Good>() {
            @Override public void write(JsonWriter out, Good value) throws IOException {
                out.beginObject();
                out.name("type").value(value.getClass().getSimpleName());
                out.name("data");
                gson.toJson(value, value.getClass(), out);
                out.endObject();
            }

            @Override public Good read(JsonReader in) throws IOException {
                JsonObject obj = JsonParser.parseReader(in).getAsJsonObject();
                String type = obj.get("type").getAsString();
                JsonElement data = obj.get("data");

                switch (type) {
                    case "Artisan":       return gson.fromJson(data, Artisan.class);
                    case "Crafting":        return gson.fromJson(data, Crafting.class);
                    case "FarmingTree": return gson.fromJson(data, FarmingTree.class);
                    case "AnimalProduct":            return gson.fromJson(data, AnimalProduct.class);
                    case "CookingRecipe": return gson.fromJson(data, CookingRecipe.class);
                    case "CraftingRecipe": return gson.fromJson(data, CraftingRecipe.class);
                    case "Farming": return gson.fromJson(data, Farming.class);
                    case "FarmingCrop": return gson.fromJson(data, FarmingCrop.class);
                    case "FarmingTreeSapling": return gson.fromJson(data, FarmingTreeSapling.class);
                    case "Fish": return gson.fromJson(data, Fish.class);
                    case "Food": return gson.fromJson(data, Food.class);
                    case "Foraging": return gson.fromJson(data, Foraging.class);
                    case "ForagingCrop": return gson.fromJson(data, ForagingCrop.class);
                    case "ForagingMineral": return gson.fromJson(data, ForagingMineral.class);
                    case "ForagingMixedSeed":  return gson.fromJson(data, ForagingMixedSeed.class);
                    case "ForagingSeed": return gson.fromJson(data, ForagingSeed.class);
                    case "ForagingTree": return gson.fromJson(data, ForagingTree.class);
                    case "Product": return gson.fromJson(data, Product.class);
                    case "Recipe": return gson.fromJson(data, Recipe.class);
                    case "ShippingBin": return gson.fromJson(data, ShippingBin.class);
                    case "Tool": return gson.fromJson(data, Tool.class);

                    // ... all other Good subtypes
                    default: throw new JsonParseException("Unknown Good type: " + type);
                }
            }
        };

//        TypeAdapter<Foraging> foragingAdapter = new TypeAdapter<Foraging>() {
//            @Override
//            public void write(JsonWriter out, Good value) throws IOException {
//                out.beginObject();
//                out.name("type").value(value.getClass().getSimpleName());
//                out.name("data");
//                gson.toJson(value, value.getClass(), out);
//                out.endObject();
//            }
//
//            @Override public Foraging read(JsonReader in) throws IOException {
//                JsonObject obj = JsonParser.parseReader(in).getAsJsonObject();
//                String type = obj.get("type").getAsString();
//                JsonElement data = obj.get("data");
//
//                switch (type) {
//                    case "Artisan":       return gson.fromJson(data, Artisan.class);
//                    case "Crafting":        return gson.fromJson(data, Crafting.class);
//                    case "FarmingTree": return gson.fromJson(data, FarmingTree.class);
//                    case "AnimalProduct":            return gson.fromJson(data, AnimalProduct.class);
//                    case "CookingRecipe": return gson.fromJson(data, CookingRecipe.class);
//                    case "CraftingRecipe": return gson.fromJson(data, CraftingRecipe.class);
//                    case "Farming": return gson.fromJson(data, Farming.class);
//                    case "FarmingCrop": return gson.fromJson(data, FarmingCrop.class);
//                    case "FarmingTreeSapling": return gson.fromJson(data, FarmingTreeSapling.class);
//                    case "Fish": return gson.fromJson(data, Fish.class);
//                    case "Food": return gson.fromJson(data, Food.class);
//                    case "ForagingCrop": return gson.fromJson(data, ForagingCrop.class);
//                    case "ForagingMineral": return gson.fromJson(data, ForagingMineral.class);
//                    case "ForagingMixedSeed":  return gson.fromJson(data, ForagingMixedSeed.class);
//                    case "ForagingSeed": return gson.fromJson(data, ForagingSeed.class);
//                    case "ForagingTree": return gson.fromJson(data, ForagingTree.class);
//                    case "Product": return gson.fromJson(data, Product.class);
//                    case "Recipe": return gson.fromJson(data, Recipe.class);
//                    case "ShippingBin": return gson.fromJson(data, ShippingBin.class);
//                    case "Tool": return gson.fromJson(data, Tool.class);
//
//                    // ... all other Good subtypes
//                    default: throw new JsonParseException("Unknown Good type: " + type);
//                }
//            }
//        };

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
