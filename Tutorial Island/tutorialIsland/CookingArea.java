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
       return tutorialComponents.instructionsHeader.text().contains("yellow arrow") && //TODO need a better check
                tutorialComponents.instructionsHeader.height() == 68;
    }

    @Override
    public void execute() {
        System.out.println("Starting cooking area");

        // Run to the door
        pathToArea(OUTSIDE_COOKING_AREA, ctx);

        // Open the door
        System.out.println("Opening door");
        openDoor(true, COOKING_DOOR, cookingDoorInID, ctx);

        // Talk to chef
        Npc chef = getNpcWithID(true, chefID, ctx);
        talkTo(chef, ctx);
        continueChat(ctx);
        boolean gotIngredients = Condition.wait(tutorialConditions.gotIngredients, 300, 10);
        while (!gotIngredients) {
            continueChat(ctx);
            gotIngredients = Condition.wait(tutorialConditions.gotIngredients, 300, 10);
        }

        // Make bread
        System.out.println("Making bread");
        Item flour = getItemFromInventory(flourID, ctx);
        Item bucketWater = getItemFromInventory(bucketWaterID, ctx);
        useItemOnOtherItem(flour, bucketWater);
        boolean doughMade = Condition.wait(tutorialConditions.doughMade, 400, 10);
        while (!doughMade) {
            useItemOnOtherItem(flour, bucketWater);
            doughMade = Condition.wait(tutorialConditions.doughMade, 400, 10);
        }

        Item dough = getItemFromInventory(doughID, ctx);
        GameObject range = getNearestGameObject(rangeID, ctx);
        useItemOnObject(dough, range);
        boolean breadMade = Condition.wait(tutorialConditions.breadMade, 400, 10);
        while (!breadMade) {
            useItemOnObject(dough, range);
            breadMade = Condition.wait(tutorialConditions.doughMade, 400, 10);
        }

        // Open the door
        System.out.println("Opening door");
        openDoor(true, POST_COOKING_DOOR, cookingDoorOutID, ctx);

        System.out.println("Cooking area completed!");
    }
}
