package ironMiner;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;
import tutorialIsland.TutorialConditions;

import java.util.Random;
import java.util.concurrent.Callable;

public class HelperMethods {
    /**
     * Checks if the player it within an area
     *
     * @param area an array of Tiles
     * @param ctx  the client context
     * @return true if the player is within the area
     */
    public static boolean playerInArea(Tile[] area, ClientContext ctx) {
        Tile playerTile = ctx.players.local().tile();
        for (int i = 0; i < area.length; i++) {
            Tile tile = area[i];
            if (tile.equals(playerTile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates an Area from two corner tiles
     *
     * @param southWest the southwesternmost tile
     * @param northEast the northeasternmost tile
     * @return An array of tiles forming a square
     */
    public static Tile[] createTilesFromCorners(Tile southWest, Tile northEast) {
        int left = southWest.x();
        int right = northEast.x();
        int bottom = southWest.y();
        int top = northEast.y();

        int numTiles = (right - left + 1) * (top - bottom + 1);
        Tile[] tiles = new Tile[numTiles];

        int i = 0;
        for (int x = left; x <= right; x++) {
            for (int y = bottom; y <= top; y++) {
                tiles[i] = new Tile(x, y);
                i++;
            }
        }
        return tiles;
    }

    /**
     * Runs to a random tile in an area
     *
     * @param area the area to run to
     * @param ctx  the client context
     */
    public static void pathToArea(Area area, ClientContext ctx) {
        TutorialConditions tutorialConditions = new TutorialConditions(ctx);
        ctx.movement.findPath(area.getRandomTile()).traverse();
        randomSleep(400, 800);
        // Did the player stop moving?
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !ctx.players.local().inMotion();
            }
        },400, 30);
    }

    /**
     * Uses the Condition.sleep() method to sleep a random number of seconds
     *
     * @param min the shortest possible sleep time
     * @param max the longest possible sleep time
     */
    public static void randomSleep(int min, int max) {
        java.util.Random rand = new Random();
        int sleepTime = rand.nextInt(min + max) + min;
        Condition.sleep(sleepTime);
    }
}
