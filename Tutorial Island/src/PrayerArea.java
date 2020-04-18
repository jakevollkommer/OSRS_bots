package tutorialIsland;

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
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Starting Prayer Area");

        // Talk to brother brace
        Npc brotherBrace = getNpcWithID(brotherBraceID, ctx);
        talkTo(brotherBrace, ctx);
        continueChat(ctx);
//        Condition.wait(tutorialConditions.tabReady);

        // Click prayer tab
        System.out.println("Clicking prayer tab");
        Component prayerTab = tutorialComponents.prayerTab;
        prayerTab.click();

        // Talk to brother brace
        talkTo(brotherBrace, ctx);
        continueChat(ctx);
//        Condition.wait(tutorialConditions.tabReady);

        // Click friends tab
        System.out.println("Clicking friends tab");
        Component friendsTab = tutorialComponents.friendsTab;
        friendsTab.click();

        // Talk to brother brace
        talkTo(brotherBrace, ctx);
        continueChat(ctx);
//        Condition.wait(tutorialConditions.chatWindowInvalid);

        // Open door
        System.out.println("Opening door");
        openDoor(prayerDoorID, ctx);

        System.out.println("Prayer area complete!");
    }
}
