package tutorialIsland;

import org.powerbot.script.rt4.*;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;

public class MiningArea extends Task {
    TutorialComponents tutorialComponents = new TutorialComponents(ctx);
    TutorialConditions tutorialConditions = new TutorialConditions(ctx);

    public MiningArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Starting mining area");

        // Talk to mining instructor
        Npc miningInstructor = getNpcWithID(miningID, ctx);
        talkTo(miningInstructor, ctx);
        continueChat(ctx);
//        Condition.wait(tutorialConditions.chatWindowInvalid);

        // Mine tin
        GameObject tinRock = getNearestGameObject(tinRockID, ctx);
        Item tinOre = mine(tinRock, ctx);
        while (tinOre == null) {
            mine(tinRock, ctx);
        }

        // Mine copper
        GameObject copperRock = getNearestGameObject(copperRockID, ctx);
        Item copperOre = mine(copperRock, ctx);
        while (copperOre == null) {
            mine(copperRock, ctx);
        }

        // Smelt ore
        GameObject furnace = getNearestGameObject(furnaceID, ctx);
        // TODO click bronze bar?
        furnace.interact("Smelt");
//        Condition.wait(tutorialConditions.noMovement);

        // Talk to mining instructor
        talkTo(miningInstructor, ctx);
        continueChat(ctx);
//        Condition.wait(tutorialConditions.chatWindowInvalid);

        // Smith bar
        GameObject anvil = getNearestGameObject(anvilID, ctx);
        anvil.interact("Smith");
//        Condition.wait(tutorialConditions.smithReady);
        Component bronzeDaggerComponent = tutorialComponents.bronzeDagger;
        bronzeDaggerComponent.click();
//        Condition.wait(tutorialConditions.doneSmithing);

        // Open gate
        openDoor(metalGateID, ctx);

        System.out.println("Mining area complete!");
    }
}
