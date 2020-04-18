package tutorialIsland;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;

public class StartingRoom extends Task {
    Npc gielinorGuide;

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
        randomSleep(2000,5000);

        // Find the Gielinor Guide
        while (gielinorGuide == null) {
            gielinorGuide = getNpcWithID(gielinorID, ctx);
            randomSleep(10,200);
        }

        // If we don't talk to him, restart
        if (talkTo(gielinorGuide, ctx)) {
            continueChat(ctx);
        } else {
            return;
        }

        String experienceOption = "I am an experienced player.";
        Condition.wait(tutorialConditions.chatOptions, 500, 10);

        System.out.println("We are experienced");
        ChatOption chat = ctx.chat.select().text(experienceOption).poll();
        chat.select();
        Condition.wait(tutorialConditions.canContinue, 400, 10);

        continueChat(ctx);
        Condition.wait(tutorialConditions.tabReady, 500, 10);

        System.out.println("Clicking options tab");
        tutorialComponents.optionsTab.click(); //TODO maybe interact("Options")
        Condition.wait(tutorialConditions.optionsClicked, 1000, 7);

        System.out.println("Options tab clicked!");
        // If we don't talk to him, restart
        // TODO must be a better way to do this
        if (talkTo(gielinorGuide, ctx)) {
            continueChat(ctx);
        } else {
            System.out.println("Can't talk to guide! Quitting!");
            return;
        }

        // Continue to First Area
        System.out.println("Opening door");
        openDoor(doorID, ctx);

        System.out.println("First room completed!");
    }
}
