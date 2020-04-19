package tutorialIsland;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
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

        return tutorialComponents.instructionsHeader.text().contains("Running") && //TODO need a better check
                tutorialComponents.instructionsHeader.height() == 84;
    }

    @Override
    public void execute() {
        System.out.println("Starting Quest area");

        // Run to the door
        System.out.println("Running to the door");
        pathToArea(PRE_QUEST_AREA, ctx);

        // Open the door
        System.out.println("Opening the door");
        openDoor(true, QUEST_DOOR, questDoorID, ctx);

        // Talk to quest guide
        Npc questGuide = getNpcWithID(questGuideID, ctx);
        talkTo(questGuide, ctx);
        continueChat(ctx);
        boolean tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        while (!tabReady) {
            continueChat(ctx);
            tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        }

        // Click quest journal
        System.out.println("Clicking quest journal");
        Component questTab = tutorialComponents.questTab;
        questTab.click();
        boolean tabClicked = Condition.wait(tutorialConditions.questTabClicked, 300, 15);
        while (!tabClicked) {
            questTab.click();
            tabClicked = Condition.wait(tutorialConditions.questTabClicked, 300, 15);
        }
        randomSleep(500,1500);
        // "every quest" instructions header

        // Talk to quest guide
        talkTo(questGuide, ctx);
        continueChat(ctx);
        tutorialComponents.continueItem.click();
        boolean ladderReady = Condition.wait(tutorialConditions.ladderReady, 300, 15);
        while (!ladderReady) {
            continueChat(ctx);
            tutorialComponents.continueItem.click();
            ladderReady = Condition.wait(tutorialConditions.ladderReady, 300, 15);
        }

        // Climb ladder
        climbLadder(ladderDownID, LADDER_DOWN, MINING_LADDER, ctx);

        System.out.println("Quest area complete!");
    }
}
