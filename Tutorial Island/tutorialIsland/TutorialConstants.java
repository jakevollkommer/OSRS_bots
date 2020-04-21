package tutorialIsland;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import static tutorialIsland.HelperMethods.createAreaFromCorners;

public final class TutorialConstants extends ClientAccessor {
    // Item IDs
    public static final int rawShrimpID         = 2514;
    public static final int cookedShrimpID      = 315;
    public static final int burntShrimpID       = 7954;
    public static final int logsID              = 2511;
    public static final int tinderboxID         = 590;
    public static final int flourID             = 2516;
    public static final int bucketWaterID       = 1929;
    public static final int doughID             = 2307;
    public static final int breadID             = 2309;
    public static final int tinOreID            = 438;
    public static final int copperOreID         = 436;
    public static final int bronzeBarID         = 2349;
    public static final int bronzeDaggerID      = 1205;
    public static final int bronzeSwordID       = 1277;
    public static final int woodShieldID        = 1171;
    public static final int bowID               = 841;
    public static final int arrowID             = 882;

    // Object IDs
    public static final int[] fishingSpots      = {505,506,507};
    public static final int treeID              = 9730; //1570?
    public static final int fireID              = 26185; //2260?
    public static final int doorID              = 9398; //9476?
    public static final int questDoorID         = 9716; //9476?
    public static final int cookingDoorInID     = 9709; //9476?
    public static final int cookingDoorOutID    = 9710; //9476?
    public static final int gateID              = 9708; //966?
    public static final int metalGateID         = 9717; //1509?
    public static final int combatGateID        = 9720; //1509?
    public static final int ladderDownID        = 9726; //1203?
    public static final int ladderUpID          = 9727; //1208?
    public static final int rangeID             = 9736; //1219?
    public static final int tinRockID           = 10080; //1390?
    public static final int copperRockID        = 10079; //1390?
    public static final int furnaceID           = 10082; //11546?
    public static final int anvilID             = 2097; //1251?
    public static final int bankBoothID         = 10083; //1270?
    public static final int pollBoothID         = 26801; //28905?
    public static final int bankDoorInID        = 9721; //9476?
    public static final int bankDoorOutID       = 9722; //9476?
    public static final int prayerDoorID        = 9723; //9476?

    // NPC IDs
    public static final int gielinorID          = 3308;
    public static final int survivalID          = 8503;
    public static final int chefID              = 3305;
    public static final int questGuideID        = 3312;
    public static final int miningID            = 3311;
    public static final int vanakkaID           = 3307;
    public static final int giantRatID          = 3313;
    public static final int accountGuideID      = 3310;
    public static final int brotherBraceID      = 3319;
    public static final int mageInstructorID    = 3309;
    public static final int chickenID           = 3316;
    public static final int fishingSpotID       = 3317;

    // Strings
    public static final String LADDER_UP            = "Up";
    public static final String LADDER_DOWN          = "Down";

    // Door areas
    public static final Tile[] RESOURCE_DOOR          = {new Tile(3098, 3107, 0)};
    public static final Tile[] POST_RESOURCE_DOOR     = {new Tile(3089, 3091, 0), new Tile(3089, 3092, 0)};
    public static final Tile[] COOKING_DOOR           = {new Tile(3078, 3084, 0)};
    public static final Tile[] POST_COOKING_DOOR      = {new Tile(3072, 3090, 0)};
    public static final Tile[] QUEST_DOOR             = {new Tile(3086, 3125, 0)};
    public static final Tile[] COMBAT_DOOR            = {new Tile(3095, 9502, 0), new Tile(3095, 9503, 0)};
    public static final Tile[] VANNAKA_DOOR           = {new Tile(3111, 9518, 0), new Tile(3111, 9519, 0)};
    public static final Tile[] RAT_DOOR               = {new Tile(3110, 9518, 0), new Tile(3110, 9519, 0)};
    public static final Tile[] MANAGEMENT_DOOR        = {new Tile(3125, 3124, 0)};
    public static final Tile[] POST_MANAGEMENT_DOOR   = {new Tile(3130, 3124, 0)};
    public static final Tile[] POST_PRAYER_DOOR       = {new Tile(3122, 3102, 0)};

    // Ladder areas
    public static final Tile[] MINING_LADDER      = {new Tile(3088, 9520, 0)};
    public static final Tile[] BANK_LADDER        = {
            new Tile(3110, 3125, 0),
            new Tile(3112, 3127, 0)
    };

    // Areas
    public static final Area OUTSIDE_COOKING_AREA   = createAreaFromCorners(
            new Tile(3079, 3080, 0),
            new Tile(3084, 3086, 0)
    );
    public static final Area PRE_QUEST_AREA1         = createAreaFromCorners(
            new Tile(3067, 3109, 0),
            new Tile(3078, 3114, 0)
    );
    public static final Area PRE_QUEST_AREA2         = createAreaFromCorners(
            new Tile(3077, 3126, 0),
            new Tile(3090, 3129, 0)
    );
    public static final Area MINING_AREA            = createAreaFromCorners(
            new Tile(3076, 9499, 0),
            new Tile(3084, 9508, 0)
    );
    public static final Area POST_MINING_AREA            = createAreaFromCorners(
            new Tile(3089, 9502, 0),
            new Tile(3094, 9503, 0)
    );
    public static final Area COMBAT_LADDER_AREA     = createAreaFromCorners(
            new Tile(3109, 9523, 0),
            new Tile(3113, 9527, 0)
    );
    public static final Area BANK_AREA              = createAreaFromCorners(
            new Tile(3118, 3119, 0),
            new Tile(3124, 3125, 0)
    );
    public static final Area PRAYER_AREA            = createAreaFromCorners(
            new Tile(3120, 3103, 0),
            new Tile(3128, 3110, 0)
    );

    public static final Area MAGE_AREA              = createAreaFromCorners(
            new Tile(3134, 3083, 0),
            new Tile(3143, 3089, 0)
    );

    public TutorialConstants(ClientContext ctx) {
        super(ctx);
    }
}
