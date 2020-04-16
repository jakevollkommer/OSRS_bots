package tutorial;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

public class TutorialCompleter extends Task {
    Component instructionsHeader = ctx.widgets.component(263, 1).component(0);
    Component chatHeader = ctx.widgets.component(231, 2);
    Component accountManagementTab = ctx.widgets.component(164, 44);
    Component friendsTab = ctx.widgets.component(164, 45);
    Component optionsTab = ctx.widgets.component(164, 46);
    Component combatTab = ctx.widgets.component(164, 52);
    Component statsTab = ctx.widgets.component(164, 53);
    Component questTab = ctx.widgets.component(164, 54);
    Component inventoryTab = ctx.widgets.component(164, 55);
    Component equipmentTab = ctx.widgets.component(164, 56);
    Component prayerTab = ctx.widgets.component(164, 57);
    Component mageTab = ctx.widgets.component(164, 58);
    Component bronzeDagger = ctx.widgets.component(312, 9).component(2);
    Component wornEquipment = ctx.widgets.component(387, 1);
    // TODO Not sure about this wind strike one
    Component windStrike = ctx.widgets.component(261, 1).component(3);

    // Item IDs
    int shrimpID            = 2514;
    int cookedShrimpID      = 315;
    int logsID              = 2511;
    int tinderboxID         = 590;
    int flourID             = 2516;
    int bucketWaterID       = 1929;
    int doughID             = 2307;
    int breadID             = 2309;
    int tinOreID            = 438;
    int copperOreID         = 436;
    int bronzeBarID         = 2349;
    int bronzeDaggerID      = 1205;
    int bronzeSwordID       = 1277;
    int woodShieldID        = 1171;
    int bowID               = 841;
    int arrowID             = 882;

    // Object IDs
    int[] fishingSpots      = {505,506,507};
    int treeID              = 9730; //1570?
    int fireID              = 26185; //2260?
    int doorID              = 9398; //9476?
    int questDoorID         = 9716; //9476?
    int cookingDoorInID     = 9709; //9476?
    int cookingDoorOutID    = 9710; //9476?
    int gateID              = 9708; //966?
    int metalGateID         = 9717; //1509?
    int combatGateID        = 9720; //1509?
    int ladderDownID        = 9726; //1203?
    int ladderUpID          = 9727; //1208?
    int rangeID             = 9736; //1219?
    int tinRockID           = 10080; //1390?
    int copperRockID        = 10079; //1390?
    int furnaceID           = 10082; //11546?
    int anvilID             = 2097; //1251?
    int bankBoothID         = 10083; //1270?
    int pollBoothID         = 26801; //28905?
    int bankDoorInID        = 9721; //9476?
    int bankDoorOutID       = 9722; //9476?
    int prayerDoorID        = 9723; //9476?

    // NPC IDs
    int gielinorID          = 3308;
    int survivalID          = 8503;
    int chefID              = 3305;
    int questGuideID        = 3312;
    int miningID            = 3311;
    int vanakkaID           = 3307;
    int giantRatID          = 3313;
    int accountGuideID      = 3310;
    int brotherBraceID      = 3319;
    int mageInstructorID    = 3309;
    int chickenID           = 3316;

    // Are you doing an animation?
    Callable<Boolean> animation = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            // -1 means no animation, i.e. player is not fishing
            return ctx.players.local().animation() != -1;
        }
    };

    public TutorialCompleter(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return instructionsHeader.text().contains("Getting started"); //TODO .valid()?
    }

    @Override
    public void execute() {
        System.out.println("Starting tutorial");
        Condition.sleep(2000);
        // Talk to the Gielinor Guide
        Npc gielinorGuide = ctx.npcs.select().id(gielinorID).select().poll();
        talkTo(gielinorGuide);
        System.out.println("talked");
        ChatOption chat = ctx.chat.select().text("I am an experienced player.").poll();
        chat.select();
        continueChat();
        // TODO check for completion somehow
        // maybe check chat header for "Options menu"
        optionsTab.click();

        talkTo(gielinorGuide);

        // Continue to First Area
        GameObject firstDoor = ctx.objects.select().id(doorID).select().poll();
        firstDoor.click();
        Npc survivalExpert = ctx.npcs.select().id(survivalID).select().poll();
        talkTo(survivalExpert);
        inventoryTab.click();
        // TODO check for completion somehow
        // maybe check chat header for "Fishing"

        // Fish
        Item shrimp = fish();
        // Keep attempting to fish until we catch a shrimp
        while(shrimp == null) {
            shrimp = fish();
        }
        statsTab.click();

        // Woodcut
        Item logs = woodcut();
        while(logs == null) {
            logs = woodcut();
        }
        // Firemake
        GroundItem fire = firemake(logs);
        while(fire == null) {
            fire = firemake(logs);
        }

        // Cook
        cook(shrimp, fire); // TODO if we fail, need to fish, wc, fire, cook again

        /* TODO: HERE IS THE REST OF THE TUTORIAL ACTIONS
        OPEN GATE (gateID)
        OPEN COOKING DOOR (cookingDoorInID)
        TALK TO MASTER CHEF (chefID)
        USE FLOUR ON BUCKET WATER (flourID, bucketWaterID)
        USE DOUGH ON RANGE (doughID, rangeID)
        IF SUCCESSFUL WE HAVE BREAD (breadID)
        OPEN DOOR (cookingDoorOutID)
        RUN TO QUEST DOOR (dont have coords yet)
        OPEN QUEST DOOR (questDoorID)
        TALK TO QUEST GUIDE (questGuideID)
        CLICK QUEST JOURNAL (questTab)
        TALK TO QUEST GUIDE
        CLIMB LADDER (ladderDownID)
        TALK TO MINING INSTRUCTOR (miningID)
        MINE TIN (tinRockID, tinOreID)
        MINE COPPER (copperRockID, copperOreID)
        SMELT FURNACE (furnaceID, bronzeBarID)
        TALK TO MINING INSTRUCTOR
        SMITH ANVIL (anvilID)
        CLICK BRONZE DAGGER COMPONENT (bronzeDagger, bronzeDaggerID)
        OPEN METAL GATE (metalGateID)
        TALK TO VANAKKA (vanakkaID)
        CLICK EQUIPMENT TAB (equipmentTab)
        OPEN WORN EQUIPMENT (wornEquipment)
        CLICK BRONZE DAGGER (bronzeDaggerID)
        X OUT INTERFACE (idk i think find the widget?)
        TALK TO VANAKKA
        EQUIP BRONZE SWORD AND WOOD SHIELD (bronzeSwordID, woodenShieldID)
        CLICK COMBAT TAB (combatTab)
        OPEN COMBAT GATE (combatGateID)
        ATTACK RAT (giantRatID) (until dead)
        OPEN COMBAT GATE
        TALK TO VANAKKA
        CLICK INVENTORY TAB
        EQUIP BOW AND ARROWS (bowID, arrowID)
        ATTACK RAT (until dead)
        RUN TO END OF TUNNEL
        CLIMB LADDER (ladderUpID)
        USE BANK (bankBoothID)
        X OUT
        USE POLL BOOTH (pollBoothID)
        continueChat()
        X OUT
        OPEN DOOR (bankDoorInID)
        TALK TO ACCOUNT GUIDE (accountGuideID)
        CLICK ACCOUNT MANAGEMENT TAB (accountManagementTab)
        TALK TO ACCOUNT GUIDE
        OPEN DOOR (bankDoorOutID)
        TALK TO BROTHER BRACE (brotherBraceID)
        CLICK PRAYER TAB (prayerTab)
        TALK TO BROTHER BRACE
        CLICK FRIENDS TAB (friendsTab)
        TALK TO BROTHER BRACE
        OPEN DOOR (prayerDoorID)
        RUN TO MAGE
        TALK TO MAGE INSTRUCTOR (mageInstructorID)
        CLICK MAGE TAB (mageTab)
        TALK TO MAGE INSTRUCTOR
        WIND STRIKE -> CHICKEN (windStrike, chickenID)
        TALK TO MAGE INSTRUCTOR
        SELECT YES
        continueChat()
        SELECT "No, I'm not planning to do that."
        continueChat()
        */
    }

    public void talkTo(Npc npc) {
        System.out.println("Talking to ID " + npc.id() + " AKA " + npc.name());
        npc.interact("Talk-to");
        Callable<Boolean> talking = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return chatHeader.text().contains(npc.name());
            }
        };
        Condition.wait(talking, 500, 10);
        continueChat();
    }

    public Item getItemFromInventory(int id) {
        for(Item item : ctx.inventory.items()) {
            if (item.id() == id) {
                return item;
            }
        }
        return null;
    }

    public void continueChat() {
        while(ctx.chat.canContinue()) {
            ctx.chat.continueChat();
        }
    }

    // Returns the raw shrimp Item
    public Item fish() {
        // Fish a shrimp
        GameObject fishingSpot = ctx.objects.select().id(fishingSpots).nearest().select().poll();
        fishingSpot.interact("Net");
        Condition.wait(animation, 200, 15);

        return getItemFromInventory(shrimpID);
    }

    public Item woodcut() {
        Npc survivalExpert = ctx.npcs.select().id(8503).select().poll();
        talkTo(survivalExpert);
        GameObject tree = ctx.objects.select().id(treeID).nearest().select().poll();
        tree.click();
        Condition.wait(animation, 1000, 10);

        return getItemFromInventory(logsID);
    }

    public GroundItem firemake(Item logs) {
        Item tinderbox = getItemFromInventory(tinderboxID);
        if (tinderbox == null) { System.out.println("You don't have a tinderbox!"); }
        logs.interact("Drop");
        GroundItem groundLogs = ctx.groundItems.select().id(logsID).nearest().select().poll();
        groundLogs.interact("Light");

        // Is there a fire on the ground?
        Callable<Boolean> fireBuilt = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                GameObject fire = ctx.objects.select().id(fireID).nearest().select().poll();
                return !fire.equals(ctx.objects.nil());
            }
        };
        Condition.wait(fireBuilt, 4000, 5);
        GroundItem fire = ctx.groundItems.select().id(fireID).nearest().select().poll();
        return fire;
    }

    public void cook(Item shrimp, GroundItem fire) {
        shrimp.interact("Use");
        fire.interact("Use"); //TODO this is probably something like Use Shrimp -> Fire
    }
}
