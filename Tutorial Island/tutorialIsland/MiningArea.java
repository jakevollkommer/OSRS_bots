package tutorialIsland;

import org.powerbot.script.Condition;
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
        return tutorialComponents.instructionsHeader.text().contains("mining instructor") && //TODO need a better check
                tutorialComponents.instructionsHeader.height() == 68;
    }

    @Override
    public void execute() {
        System.out.println("Starting mining area");

        // Run to mining area
        pathToArea(MINING_AREA, ctx);

        // Talk to mining instructor
        Npc miningInstructor = getNpcWithID(true, miningID, ctx);
        talkTo(miningInstructor, ctx);
        continueChat(ctx);
        boolean chatInvalid = Condition.wait(tutorialConditions.chatWindowInvalid, 300, 15);
        while (!chatInvalid) {
            continueChat(ctx);
            chatInvalid = Condition.wait(tutorialConditions.chatWindowInvalid, 300, 15);
        }

        // Mine tin
        System.out.println("Mining tin");
        Item tinOre = mine(tinRockID, tinOreID, ctx);
        while (tinOre == null) {
            tinOre = mine(tinRockID, tinOreID, ctx);
        }

        // Mine copper
        System.out.println("Mining copper");
        Item copperOre = mine(copperRockID, copperOreID, ctx);
        while (copperOre == null) {
            copperOre = mine(copperRockID, copperOreID, ctx);
        }

        // Smelt ore
        System.out.println("Smelting bar");
        Item bronzeBar = smelt(furnaceID, bronzeBarID, ctx);
        while (bronzeBar == null) {
            bronzeBar = smelt(furnaceID, bronzeBarID, ctx);
        }

        // Talk to mining instructor
        talkTo(miningInstructor, ctx);
        continueChat(ctx);
        tutorialComponents.continueItem.click();
        boolean readyToSmith = Condition.wait(tutorialConditions.readyToSmith, 300, 15);
        while (!readyToSmith) {
            talkTo(miningInstructor, ctx);
            continueChat(ctx);
            tutorialComponents.continueItem.click();
            readyToSmith = Condition.wait(tutorialConditions.readyToSmith, 300, 15);
        }

        // Smith bar
        System.out.println("Smithing dagger");
        Item bronzeDagger = smith(anvilID, bronzeDaggerID, ctx);
        while (bronzeDagger == null) {
            bronzeDagger = smith(anvilID, bronzeDaggerID, ctx);
        }

        // Open gate
        openDoor(true, COMBAT_DOOR, metalGateID, ctx);

        System.out.println("Mining area complete!");
    }
}
