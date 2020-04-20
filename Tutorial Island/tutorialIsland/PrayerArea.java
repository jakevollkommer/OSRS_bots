package tutorialIsland;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Npc;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;

public class PrayerArea extends Task {
    TutorialComponents tutorialComponents = new TutorialComponents(ctx);
    TutorialConditions tutorialConditions = new TutorialConditions(ctx);

    public PrayerArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return tutorialComponents.instructionsHeader.text().contains("chapel") && //TODO need a better check
                tutorialComponents.instructionsHeader.height() == 68;
    }

    @Override
    public void execute() {
        System.out.println("Starting Prayer Area");

        //Walk to the chapel
        pathToArea(PRAYER_AREA, ctx);

        // Talk to brother brace
        Npc brotherBrace = getNpcWithID(brotherBraceID, ctx);
        talkTo(brotherBrace, ctx);
        continueChat(ctx);
        boolean tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        while (!tabReady) {
            talkTo(brotherBrace, ctx);
            continueChat(ctx);
            tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        }

        // Click prayer tab
        System.out.println("Clicking prayer tab");
        Component prayerTab = tutorialComponents.prayerTab;
        prayerTab.click();
        boolean tabClicked = Condition.wait(tutorialConditions.prayerTabClicked, 300, 15);
        while (!tabClicked) {
            prayerTab.click();
            tabClicked = Condition.wait(tutorialConditions.prayerTabClicked, 300, 15);
        }
        // inst header "prayers"

        // Talk to brother brace
        talkTo(brotherBrace, ctx);
        continueChat(ctx);
        tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        while (!tabReady) {
            continueChat(ctx);
            tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        }

        // Click friends tab
        System.out.println("Clicking friends tab");
        Component friendsTab = tutorialComponents.friendsTab;
        friendsTab.click();
        tabClicked = Condition.wait(tutorialConditions.friendsTabClicked, 300, 15);
        while (!tabClicked) {
            friendsTab.click();
            tabClicked = Condition.wait(tutorialConditions.friendsTabClicked, 300, 15);
        }
        // inst header "friends"

        // Talk to brother brace
        talkTo(brotherBrace, ctx);
        continueChat(ctx);
        boolean chatInvalid = Condition.wait(tutorialConditions.chatWindowInvalid, 300, 15);
        while (!chatInvalid) {
            continueChat(ctx);
            chatInvalid = Condition.wait(tutorialConditions.chatWindowInvalid, 300, 15);
        }

        // Open door
        System.out.println("Opening door");
        openDoor(true, POST_PRAYER_DOOR, prayerDoorID, ctx);

        System.out.println("Prayer area complete!");
    }
}
