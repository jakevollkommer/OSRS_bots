package tutorialIsland;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public final class TutorialConstants extends ClientAccessor {
    // Item IDs
    public static final int rawShrimpID         = 2514;
    public static final int cookedShrimpID      = 315;
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

    public TutorialConstants(ClientContext ctx) {
        super(ctx);
    }
}
