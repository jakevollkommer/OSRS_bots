package tutorialIsland;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;

public class ResourcesArea extends Task {
    // For accessing all of the ctx components and conditions
    TutorialComponents tutorialComponents = new TutorialComponents(ctx);
    TutorialConditions tutorialConditions = new TutorialConditions(ctx);

    public ResourcesArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return tutorialComponents.instructionsHeader.text().contains("survival expert") && //TODO need a better check
                tutorialComponents.instructionsHeader.height() == 84;
    }

    @Override
    public void execute() {
        System.out.println("We are outside");

        // Start skilling
        Npc survivalExpert = getNpcWithID(survivalID, ctx);

        while(!survivalExpert.inViewport()) {
            System.out.println("Turning camera");
            ctx.camera.turnTo(survivalExpert);
//            Path pathToExpert = ctx.movement.findPath(survivalExpert);
//            ctx.movement.newTilePath(pathToExpert).traverse();
        }
        talkTo(survivalExpert, ctx);
        continueChat(ctx);

        Condition.wait(tutorialConditions.tabReady, 500, 10);
        if (!tutorialComponents.instructionsHeader.text().contains("flashing")) {
            ctx.chat.clickContinue();
        }

        System.out.println("Clicking inventory tab");

        tutorialComponents.inventoryTab.click();
        Condition.wait(tutorialConditions.startFishing, 2000, 5);

        System.out.println("Attempting to fish");

        // Fish
        Item shrimp = fish(ctx);
        // Keep attempting to fish until we catch a shrimp
        while(shrimp == null) {
            shrimp = fish(ctx);
        }
        continueChat(ctx);
        Condition.wait(tutorialConditions.tabReady, 500, 10);
        tutorialComponents.statsTab.click();
        Condition.wait(tutorialConditions.skillReady, 500, 10);

        talkTo(survivalExpert, ctx);
        continueChat(ctx);
        Condition.wait(tutorialConditions.woodcutReady, 500, 10);

        System.out.println("Attempting to woodcut");
        // Woodcut
        Item logs = woodcut(ctx);
        while(logs == null) {
            logs = woodcut(ctx);
        }

        System.out.println("Attempting to firemake");
        // Firemake
        GameObject fire = firemake(ctx);
        while(fire == null) {
            fire = firemake(ctx);
        }

        System.out.println("Attempting to cook");
        // Cook
        cook(shrimp, fire);
        Condition.wait(tutorialConditions.shrimpCooked, 500, 10);

        System.out.println("Continuing to next area!");
        // Continue to Quest Area
        GameObject gate = ctx.objects.select().id(gateID).poll();
        if(!gate.inViewport()) {
            ctx.camera.turnTo(gate);
        }
        while (!gate.interact("Open")) {
            System.out.println("Could not open door");
        }
        System.out.println("Resource area completed!");
    }
}
