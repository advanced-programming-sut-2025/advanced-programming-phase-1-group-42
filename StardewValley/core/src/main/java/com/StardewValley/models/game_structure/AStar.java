package com.StardewValley.models.game_structure;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.Pair;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.farmings.FarmingTree;
import com.StardewValley.models.goods.foragings.ForagingMineral;
import com.StardewValley.models.goods.foragings.ForagingTree;

import java.util.*;

enum Direction {
    UP, DOWN, LEFT, RIGHT;
}

class Node {
    Coordinate coordinate;
    Coordinate distance;
    Direction direction;
    int turns;
    Node parent;

    public Node(Coordinate coordinate, Coordinate distance, Direction direction, int turns, Node parent) {
        this.coordinate = coordinate;
        this.distance = distance;
        this.direction = direction;
        this.turns = turns;
        this.parent = parent;
    }

    public int f() {
        return distance.getX() + distance.getY() + 10 * turns;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node other = (Node) o;
            return this.coordinate.getX() == other.coordinate.getX() && this.coordinate.getY() == other.coordinate.getY();
        }
        return false;
    }
}

public class AStar {
    private static ArrayList<Coordinate> directions = new ArrayList<>(Arrays.asList(new Coordinate(0, -1),
            new Coordinate(0, 1), new Coordinate(-1, 0), new Coordinate(1, 0)));
    private static ArrayList<Direction> directionTurns = new ArrayList<>(Arrays.asList(Direction.values()));

    public static ArrayList<Pair<Integer, Coordinate>> findPath(Map map, Coordinate start, Coordinate goal) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(Node::f));
        Set<String> closedSet = new HashSet<>();

        Node startNode = new Node(start, new Coordinate(0, heuristic(start, goal)), null, 0, null);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node current = openList.poll();
            if (current.coordinate.getX() == goal.getX() && current.coordinate.getY() == goal.getY()) {
                return reconstructPath(current);
            }

            closedSet.add(current.coordinate.getX() + "," + current.coordinate.getY());

            int ptr = 0;
            for (Coordinate dir : directions) {
                int newX = current.coordinate.getX() + dir.getX();
                int newY = current.coordinate.getY() + dir.getY();

                if (!isValid(map, newX, newY))
                    continue;

                if (closedSet.contains(newX + "," + newY))
                    continue;

                int g = current.distance.getX() + 1;
                int h = heuristic(new Coordinate(newX, newY), goal);
                Direction newDirection = directionTurns.get(ptr);
                int newTurns = ((current.direction != null && !current.direction.equals(newDirection)) ? 1 : 0) + current.turns;
                Node neighbor = new Node(new Coordinate(newX, newY), new Coordinate(g, h), newDirection , newTurns , current);

                Optional<Node> existing = openList.stream().filter(n -> n.equals(neighbor) &&
                        n.distance.getX() <= g).findFirst();
                if (existing.isEmpty()) {
                    openList.add(neighbor);
                }
                ptr++;
            }
        }

        return null;
    }

    private static boolean isValid(Map map, int x, int y) {
        boolean flag = x >= 0 && y >= 0 && x < map.getEndingCoordinate().getX() && y < map.getEndingCoordinate().getY();
        if(!flag)
            return false;

        Tile tile = map.findTile(new Coordinate(x, y));
        if(tile == null)
            return false;

        Farm farm = map.findFarm(new Coordinate(x, y));
//        if(farm != null &&
//                !(AppClient.getCurrentGame().getCurrentPlayer().getFarm() == farm ||
//                        (AppClient.getCurrentGame().getCurrentPlayer().getMarried() != null && AppClient.getCurrentGame().getCurrentPlayer().getMarried().getFarm() == farm)))
//            return false;
//
//        try {
//            if(tile.getTileType() == TileType.WATER || tile.getTileType() == TileType.STONE_WALL ||
//                    (tile.getTileType() == TileType.GREEN_HOUSE && !AppClient.getCurrentGame().getCurrentPlayer().getFarm().getGreenHouse().isAvailable()) ||
//                    (tile.getTileType() == TileType.GAME_BUILDING &&
//                            !AppClient.getCurrentGame().getMap().findGameBuilding(new Coordinate(x, y)).isInWorkingHours()))
//                return false;
//        }  catch (Exception e) {
//            System.out.println(new Coordinate(x, y));
//        }



        for(Good good : tile.getGoods()) {
            if(good instanceof FarmingTree || good instanceof ForagingTree || good instanceof ForagingMineral)
                return false;
        }

        return true;
    }

    private static int heuristic(Coordinate c1, Coordinate c2) {
        return Math.abs(c1.getX() - c2.getX()) + Math.abs(c1.getY() - c2.getY());
    }

    private static ArrayList<Pair<Integer, Coordinate>> reconstructPath(Node node) {
        ArrayList<Pair<Integer, Coordinate>> path = new ArrayList<>();
        while (node != null) {
            path.add(new Pair<>(node.f(), node.coordinate));
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }
}


