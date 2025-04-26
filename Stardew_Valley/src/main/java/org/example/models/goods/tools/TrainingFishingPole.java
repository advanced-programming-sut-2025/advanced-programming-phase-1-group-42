package org.example.models.goods.tools;

import org.example.models.App;
import org.example.models.Result;
import org.example.models.enums.Season;
import org.example.models.enums.TileType;
import org.example.models.game_structure.Cordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.fishs.Fish;
import org.example.models.goods.fishs.FishType;

import java.util.ArrayList;

public class TrainingFishingPole extends Tool {
    public TrainingFishingPole() {
        super(ToolType.TRAINING_FISHING_POLE);
    }

    @Override
    public Result useTool(Cordinate cordinate) {
        Tile tile = App.getCurrentGame().getMap().findTile(cordinate);
        if(tile.getTileType() != TileType.WATER)
            return new Result(false, "You should use " + getName() + " in Water Tiles!");

        Good good = null;
        switch (App.getCurrentGame().getDateTime().getSeasonOfYear()) {
            case Season.SPRING ->
                good = tile.findGoods(FishType.HERRING);
            case Season.SUMMER ->
                good = tile.findGoods(FishType.SUNFISH);
            case Season.FALL ->
                good = tile.findGoods(FishType.SARDINE);
            case Season.WINTER ->
                good = tile.findGoods(FishType.PERCH);
        }

        //TODO
        return null;
    }

    @Override
    public GoodType getType() {
        return null;
    }
}
