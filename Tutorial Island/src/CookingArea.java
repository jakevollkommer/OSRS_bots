package tutorialIsland;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Npc;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;

public class CookingArea extends Task {
    TutorialComponents tutorialComponents = new TutorialComponents(ctx);
    TutorialConditions tutorialConditions = new TutorialConditions(ctx);

    public CookingArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Starting cooking area");

        // Open the gate TODO did we do this already?
        System.out.println("Opening gate");
        openDoor(gateID, ctx);
//        Condition.wait(tutorialConditions.notInMotion);

        // Open the door
        System.out.println("Opening door");
        openDoor(cookingDoorInID, ctx);
//        Condition.wait(tutorialConditions.notInMotion);

        // Talk to chef
        Npc chef = getNpcWithID(chefID, ctx);
        talkTo(chef, ctx);
        continueChat(ctx);
        Condition.wait(tutorialConditions.chatWindowInvalid);

        // Make bread
        System.out.println("Making bread");
        Item flour = getItemFromInventory(flourID, ctx);
        Item bucketWater = getItemFromInventory(bucketWaterID, ctx);
        useItemOnOtherItem(flour, bucketWater);
        randomSleep(500, 2000);

        Item dough = getItemFromInventory(doughID, ctx);
        GameObject range = getNearestGameObject(rangeID, ctx);
        useItemOnObject(dough, range);
//        Condition.wait(tutorialConditions.breadMade);
        // breadID

        // Open the door
        System.out.println("Opening door");
        openDoor(cookingDoorOutID, ctx);

        System.out.println("Cooking area completed!");
    }
}
