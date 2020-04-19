package tutorialIsland;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;

public class StartingRoom extends Task {

    // For accessing all of the ctx components and conditions
    TutorialComponents tutorialComponents = new TutorialComponents(ctx);
    TutorialConditions tutorialConditions = new TutorialConditions(ctx);

    public StartingRoom(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return tutorialComponents.instructionsHeader.text().contains("Getting started") && //TODO need a better check
                tutorialComponents.instructionsHeader.height() == 68;
    }

    @Override
    public void execute() {
        System.out.println("Starting Tutorial!");

        // Find the Gielinor Guide
        Npc gielinorGuide = getNpcWithID(true, gielinorID, ctx);
        talkTo(gielinorGuide, ctx);
        continueChat(ctx);

        String experienceOption = "I am an experienced player.";
        boolean chatOptions = Condition.wait(tutorialConditions.chatOptions, 500, 10);
        while (!chatOptions) {
            talkTo(gielinorGuide, ctx);
            continueChat(ctx);
            chatOptions = Condition.wait(tutorialConditions.chatOptions, 500, 10);
        }

        System.out.println("We are experienced");
        ChatOption chat = ctx.chat.select().text(experienceOption).poll();
        chat.select();
        boolean canContinue = Condition.wait(tutorialConditions.canContinue, 400, 10);
        while (!canContinue) {
            chat.select();
            canContinue = Condition.wait(tutorialConditions.canContinue, 400, 10);
        }

        continueChat(ctx);
        boolean tabReady = Condition.wait(tutorialConditions.tabReady, 500, 10);
        while (!tabReady) {
            continueChat(ctx);
            tabReady = Condition.wait(tutorialConditions.tabReady, 500, 10);
        }

        System.out.println("Clicking options tab");
        tutorialComponents.optionsTab.click();
        boolean tabClicked = Condition.wait(tutorialConditions.optionsClicked, 1000, 7);
        while (!tabClicked) {
            tutorialComponents.optionsTab.click();
            tabClicked = Condition.wait(tutorialConditions.optionsClicked, 1000, 7);
        }

        System.out.println("Options tab clicked!");
        talkTo(gielinorGuide, ctx);
        continueChat(ctx);
        boolean chatInvalid = Condition.wait(tutorialConditions.chatWindowInvalid, 300, 15);
        while (!chatInvalid) {
            continueChat(ctx);
        }

        // Continue to First Area
        System.out.println("Opening door");
        openDoor(true, RESOURCE_DOOR, doorID, ctx);

        System.out.println("First room completed!");
    }
}
