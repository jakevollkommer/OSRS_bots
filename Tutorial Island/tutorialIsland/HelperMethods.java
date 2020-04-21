package tutorialIsland;

import com.sun.corba.se.pept.protocol.ClientDelegate;
import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

import java.util.Random;
import java.util.concurrent.Callable;

import static tutorialIsland.TutorialConstants.*;

public class HelperMethods {
    /**
     * Method to return an NPC with a given ID.
     * Sometimes the client selects an NPC with the wrong ID, so this is a more robust function.
     * @param id the id of the npc
     * @return the first NPC with the given id
     */
    public static Npc getNpcWithID(int id, ClientContext ctx) {
        for (Npc npc : ctx.npcs.get()) {
            if (npc.id() == id) {
                return npc;
            }
        }
        return null;
    }

    public static Npc getNpcWithID(boolean forced, int id, ClientContext ctx) {
        Npc npc = null;
        while (npc == null) {
            npc = getNpcWithID(id, ctx);
            randomSleep(10,200);
        }
        return npc;
    }

    /**
     * Gets the first Item object in your inventory with a given ID.
     * @param id the item ID
     * @return the Item object
     */
    public static Item getItemFromInventory(int id, ClientContext ctx) {
        for(Item item : ctx.inventory.items()) {
            if (item.id() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * Gets the nearest GameObject with a given ID.
     * @param ids array of object IDs
     * @param ctx the ClientContext instance
     * @return the GameObject
     */
    public static GameObject getNearestGameObject(int[] ids, ClientContext ctx) {
        GameObject o = ctx.objects.select().id(ids).nearest().poll();
        if (o.tile().x() == -1) {
            return null;
        }
        return o;
    }

    /**
     * Helper method for getNearestGameObject
     * @param id the object ID
     * @param ctx the ClientContext instance
     * @return the GameObject
     */
    public static GameObject getNearestGameObject(int id, ClientContext ctx) {
        return getNearestGameObject(new int[]{id}, ctx);
    }

        /**
         * Talk to a specific npc and complete all no-input dialogue.
         * @param npc
         *          the Npc object we want to talk to
         */
    public static boolean talkTo(Npc npc, ClientContext ctx) {
        System.out.println("Talking to ID " + npc.id() + " AKA " + npc.name());
        TutorialComponents tutorialComponents = new TutorialComponents(ctx);

        // Turn to the NPC before talking
        ctx.camera.turnTo(npc);

        npc.interact("Talk-to");
        Callable<Boolean> talking = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return tutorialComponents.chatHeader.text().contains(npc.name());
            }
        };
        Condition.wait(talking, 500, 10);

        return tutorialComponents.chatHeader.text().contains(npc.name());
    }

    /**
     * Simply continues chat as many times as possible.
     */
    public static void continueChat(ClientContext ctx) {
        while(ctx.chat.canContinue()) {
            ctx.chat.continueChat(true);
            randomSleep(500, 1000);
        }
    }

    /**
     * Runs to a random tile in an area
     * @param area the area to run to
     * @param ctx the client context
     */
    public static void pathToArea(Area area, ClientContext ctx) {
        TutorialConditions tutorialConditions = new TutorialConditions(ctx);
        ctx.movement.findPath(area.getRandomTile()).traverse();
        randomSleep(400, 800);
        Condition.wait(tutorialConditions.notInMotion, 400, 30);
    }

    /**
     * Tries to open a door
     * @param doorID the ID of the door to open
     * @param area the area we want to end up in through the door
     * @param ctx the client context
     * @return whether the door was opened successfully
     */
    public static boolean openDoor(int doorID, Tile[] area, ClientContext ctx) {
        GameObject door = ctx.objects.select().id(doorID).poll();
        ctx.camera.turnTo(door);
        boolean openedDoor = door.interact("Open");
        randomSleep(1500, 3000);

        return areaContainsTile(area, ctx.players.local().tile(), ctx);
    }

    /**
     * Tries to open a door until successful
     * @param forced don't return until a door is passed
     * @param doorID the ID of the door to open
     * @param area the area we want to end up in through the door
     * @param ctx the client context
     * @return whether the door was opened successfully
     */
    public static boolean openDoor(boolean forced, Tile[] area, int doorID, ClientContext ctx) {
        if (!forced) { return openDoor(doorID, area, ctx); };

        boolean doorOpened = openDoor(doorID, area, ctx);
        while (!doorOpened) {
            System.out.println("Didn't open door, try again");
            doorOpened = openDoor(doorID, area, ctx);
            randomSleep(500, 1000);
        }
        return doorOpened;
    }

    /**
     * Tries to open a door until successful
     * @param forced don't return until a door is passed
     * @param doorID the ID of the door to open
     * @param ctx the client context
     * @return whether the door was opened successfully
     */
    public static boolean openDoor(boolean forced, int doorID, ClientContext ctx) {
        Tile[] area = {ctx.players.local().tile()};
        if (!forced) { return openDoor(doorID, area, ctx); };
        return openDoor(forced, area, doorID, ctx);
    }

    /**
     * Tries to climb a ladder until successful
     * @param ladderID the ID of the ladder to climb
     * @param direction the direction to climb
     * @param destination the array of tiles we should end up at
     * @param ctx the client context
     * @return whether the ladder was climbed successfully
     */
    public static boolean climbLadder(int ladderID, String direction, Tile[] destination, ClientContext ctx) {
        GameObject ladder = ctx.objects.select().id(ladderID).poll();
        ctx.camera.turnTo(ladder);
        String action = "Climb-" + direction;
        boolean climbed = ladder.interact(action);
        randomSleep(300,1800);

        return climbed && areaContainsTile(destination, ctx.players.local().tile(), ctx);
    }

    /**
     * Tries to climb a ladder until successful
     * @param ladderID the ID of the ladder to climb
     * @param direction the direction to climb
     * @param destination the array of tiles we should end up at
     * @param ctx the client context
     * @return whether the ladder was climbed successfully
     */
    public static boolean climbLadder(boolean forced, int ladderID, String direction, Tile[] destination, ClientContext ctx) {
        if (!forced) { climbLadder(ladderID, direction, destination, ctx); }

        boolean ladderClimbed = climbLadder(ladderID, direction, destination, ctx);
        while (!ladderClimbed) {
            System.out.println("Didn't climb ladder, try again");
            ladderClimbed = climbLadder(ladderID, direction, destination, ctx);
            randomSleep(300, 800);
        }
        return ladderClimbed;
    }

    /**
     * Uses the Condition.sleep() method to sleep a random number of seconds
     * @param min
     *      the shortest possible sleep time
     * @param max
     *      the longest possible sleep time
     */
    public static void randomSleep(int min, int max) {
        java.util.Random rand = new Random();
        int sleepTime = rand.nextInt(min + max) + min;
        Condition.sleep(sleepTime);
    }

    /**
     * Creates an Area from two corner tiles
     * @param southWest the southwesternmost tile
     * @param northEast the northeasternmost tile
     * @return An square area of tiles
     */
    public static Area createAreaFromCorners(Tile southWest, Tile northEast) {
        int left    = southWest.x();
        int right   = northEast.x();
        int bottom  = southWest.y();
        int top     = northEast.y();

        int numTiles = (right - left + 1) * (top - bottom + 1);
        Tile[] tiles = new Tile[numTiles];

        int i = 0;
        for (int x = left; x <= right; x++) {
            for (int y = bottom; y <= top; y++) {
                tiles[i] = new Tile(x, y);
                i++;
            }
        }
//        System.out.println(tiles[0].x() + ", " + tiles[0].y());
//        System.out.println(tiles[numTiles-1].x() + ", " + tiles[numTiles-1].y());
        return new Area(tiles);
    }

    /**
     * Fishes at the closest shrimp spot and returns the shrimp item.
     * @param ctx the Client Context
     * @return the Item object of the shrimp we caught (may be null)
     */
    public static Item fish(ClientContext ctx) {
        // Fish a shrimp
        Npc nearestFishingSpot = getNpcWithID(fishingSpotID, ctx);
        System.out.println("Turning camera");
        ctx.camera.turnTo(nearestFishingSpot);

        nearestFishingSpot.interact("Net");
        TutorialConditions tutorialConditions = new TutorialConditions(ctx);
        Condition.wait(tutorialConditions.animation, 1000, 15);

        return getItemFromInventory(rawShrimpID, ctx);
    }

    /**
     * Smelts at a furnace
     * @param furnaceID the id of the furnace
     * @param barID the id of the bar we smelted
     * @param ctx the Client Context
     * @return the Item object of the bar we smelted
     */
    public static Item smelt(int furnaceID, int barID, ClientContext ctx) {
        GameObject furnace = getNearestGameObject(furnaceID, ctx);
        ctx.camera.turnTo(furnace);
        furnace.interact("Use");
        TutorialConditions tutorialConditions = new TutorialConditions(ctx);
        Condition.wait(tutorialConditions.barSmelted, 500, 20);
        return getItemFromInventory(barID, ctx);
    }

    /**
     * Smiths at an anvil
     * @param anvilID the id of the anvil
     * @param itemID the id of the item we smithed
     * @param ctx the Client Context
     * @return the Item object of the shrimp we caught (may be null)
     */
    public static Item smith(int anvilID, int itemID, ClientContext ctx) {
        TutorialConditions tutorialConditions = new TutorialConditions(ctx);
        TutorialComponents tutorialComponents = new TutorialComponents(ctx);

        GameObject anvil = getNearestGameObject(anvilID, ctx);
        ctx.camera.turnTo(anvil);
        anvil.interact("Smith");
        Condition.wait(tutorialConditions.smithReady, 300, 15);
        Component bronzeDaggerComponent = tutorialComponents.bronzeDagger;
        bronzeDaggerComponent.click();
        Condition.wait(tutorialConditions.daggerSmithed, 300, 15);

        return getItemFromInventory(itemID, ctx);
    }

    /**
     * A function to determine if a tile is within a defined area
     * @param area an array of Tiles
     * @param tile the tile we're checking
     * @param ctx the client context
     * @return true if the tile is contained in the area, false otherwise
     */
    public static boolean areaContainsTile(Tile[] area, Tile tile, ClientContext ctx) {
        for (int i = 0; i < area.length; i++) {
            if (area[i].x() == tile.x() &&
                    area[i].y() == tile.y() &&
                    area[i].floor() == tile.floor()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Cuts a tree and returns the log item.
     * @return the Item object of the logs we chopped (may be null)
     */
    public static Item woodcut(ClientContext ctx) {
        GameObject tree = getNearestGameObject(treeID, ctx);
        System.out.println("Turning camera");
        ctx.camera.turnTo(tree);
        tree.interact("Chop down");
        TutorialConditions tutorialConditions = new TutorialConditions(ctx);
        Condition.wait(tutorialConditions.animation, 1000, 10);

        return getItemFromInventory(logsID, ctx);
    }

    /**
     * Lights logs on fire by dropping them and using the "Light" action.
     * @return the GroundItem object of the closest fire
     */
    public static GameObject firemake(ClientContext ctx) {
        Item tinderbox = getItemFromInventory(tinderboxID, ctx);
        Item logs = getItemFromInventory(logsID, ctx);

        try {
            useItemOnOtherItem(tinderbox, logs);
        } catch (Exception e) {
            System.out.println("No logs in inventory!");
        }

        // Is there a fire on the ground?
        Callable<Boolean> fireBuilt = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                GameObject fire = getNearestGameObject(fireID, ctx);
                System.out.println(fire);
                return !fire.equals(ctx.objects.nil());
            }
        };
        Condition.wait(fireBuilt, 4000, 5);
        GameObject fire = getNearestGameObject(fireID, ctx);
        return fire;
    }

    public static Item mine(int rockID, int oreID, ClientContext ctx) {
        GameObject rock = getNearestGameObject(rockID, ctx);
        ctx.camera.turnTo(rock);
        rock.interact("Mine");
        TutorialConditions tutorialConditions = new TutorialConditions(ctx);
        // TODO this does work lol?
        Condition.wait(tutorialConditions.animation, 300, 15);
        randomSleep(400, 1000);

        return getItemFromInventory(oreID, ctx);
    }

    /**
     * Cooks a shrimp on a fire
     * @param shrimp the shrimp to cook
     * @param fire the fire to cook on
     */
    public static void cook(Item shrimp, GameObject fire) {
        shrimp.interact("Use");
        fire.interact("Use", "Fire");
    }

    public static void attack(int npcID, ClientContext ctx) {
        Filter<Npc> healthNotVisible = new Filter<Npc>() {
            @Override
            public boolean accept(Npc npc) {
                return !npc.healthBarVisible();
            }
        };
        Npc npc = ctx.npcs.select().id(npcID).nearest().select(healthNotVisible).poll();
        npc.interact("Attack");
        TutorialConditions tutorialConditions = new TutorialConditions(ctx);
        boolean inCombat = Condition.wait(tutorialConditions.inCombat, 500, 10);
        while (!inCombat) {
            npc = ctx.npcs.select().id(npcID).nearest().select(healthNotVisible).poll();
            npc.interact("Attack");
            inCombat = Condition.wait(tutorialConditions.inCombat, 500, 10);
        }
        boolean npcKilled = Condition.wait(tutorialConditions.outOfCombat, 500, 10);
        while (!npcKilled) {
            npcKilled = Condition.wait(tutorialConditions.outOfCombat, 500, 10);
        }
    }

    /**
     * Uses an item on another item
     * @param useThis the first item to click
     * @param onThis the second item to click
     */
    public static void useItemOnOtherItem(Item useThis, Item onThis) {
        useThis.interact("Use");
        onThis.interact("Use", onThis.name());
    }

    /**
     * Uses an item on an object
     * @param useThis the item to use
     * @param onThis the object to use the item on
     */
    public static void useItemOnObject(Item useThis, GameObject onThis) {
        useThis.interact("Use");
        onThis.interact("Use", onThis.name());
    }
}
