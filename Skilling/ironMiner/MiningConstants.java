package ironMiner;

import org.powerbot.script.Tile;
import static ironMiner.HelperMethods.*;

public class MiningConstants {

    public static final int[] ironRockIDs = {11364, 11365};
    public static final int bankBoothID = 10583;

    public static final int MINING_ANIMATION = 625;

    public static final Tile[] area1 = createTilesFromCorners(new Tile(3290,3377), new Tile(3296,3383));
    public static final Tile[] area2 = createTilesFromCorners(new Tile(3290,3401), new Tile(3294,3406));
    public static final Tile[] area3 = createTilesFromCorners(new Tile(3284,3420), new Tile(3288,3424));
    public static final Tile[] area4 = createTilesFromCorners(new Tile(3268,3427), new Tile(3273,3431));
    public static final Tile[] miningArea = createTilesFromCorners(new Tile(3282,3362), new Tile(3289,3370));
    public static final Tile[] bankArea = createTilesFromCorners(new Tile(3250, 3420), new Tile(3254, 3424));

}
