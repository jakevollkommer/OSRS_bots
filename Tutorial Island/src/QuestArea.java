package tutorialIsland;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Npc;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;

public class QuestArea extends Task {
    TutorialComponents tutorialComponents = new TutorialComponents(ctx);
    TutorialConditions tutorialConditions = new TutorialConditions(ctx);

    public QuestArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Starting Quest area");

        // Run to the door
        System.out.println("Running to the door");
        // TODO pathing

        // Open the door
        System.out.println("Opening the door");
        openDoor(questDoorID, ctx);

        // Talk to quest guide
        Npc questGuide = getNpcWithID(questGuideID, ctx);
        talkTo(questGuide, ctx);
        continueChat(ctx);

        // Click quest journal
        System.out.println("Clicking quest journal");
        Component questTab = tutorialComponents.questTab;
        questTab.click();
//        Condition.wait(questTabClicked);

        // Climb ladder
        climbLadder(ladderDownID, LADDER_DOWN, ctx);

        System.out.println("Quest area complete!");
    }
}
