package tutorialIsland;

import org.powerbot.script.Condition;
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
        TutorialComponents tutorialComponents = new TutorialComponents(ctx);
        System.out.println("Talking to ID " + npc.id() + " AKA " + npc.name());
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
     * Fishes at the closest shrimp spot and returns the shrimp item.
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
        if (tinderbox == null) { System.out.println("You don't have a tinderbox!"); }
        Item logs = getItemFromInventory(logsID, ctx);
        // Must cut more logs
        if (logs == null) {
            logs = woodcut(ctx);
        }
        logs.interact("Drop");
        GroundItem groundLogs = ctx.groundItems.select().id(logsID).nearest().select().poll();
        groundLogs.interact("Light");

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

    /**
     * Cooks a shrimp on a fire
     * @param shrimp the shrimp to cook
     * @param fire the fire to cook on
     */
    public static void cook(Item shrimp, GameObject fire) {
        shrimp.interact("Use");
        fire.interact("Use", "Fire");
    }
}
