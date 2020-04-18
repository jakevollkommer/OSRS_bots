package tutorialIsland;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Npc;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;

public class BankArea extends Task {
    TutorialComponents tutorialComponents = new TutorialComponents(ctx);
    TutorialConditions tutorialConditions = new TutorialConditions(ctx);

    public BankArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {

        System.out.println("Starting bank area");

        // Use the bank booth
        System.out.println("Using bank booth");
        GameObject bankBooth = getNearestGameObject(bankBoothID, ctx);
        bankBooth.interact("Use");
//        Condition.wait(bankBoothOpen);
        // X OUT TODO

        // Use the poll booth
        System.out.println("Using poll booth");
        GameObject pollBooth = getNearestGameObject(pollBoothID, ctx);
        pollBooth.interact("Use"); //TODO might be diff
//        continueChat(ctx);
        // X OUT TODO
//        Condition.wait(interfaceOpen);

        // Open the door
        System.out.println("Opening door");
        openDoor(bankDoorInID, ctx);

        // Talk to the account guide
        Npc accountGuide = getNpcWithID(accountGuideID, ctx);
        talkTo(accountGuide, ctx);
        continueChat(ctx);
        Condition.wait(tutorialConditions.tabReady);

        // Click the account management tab
        System.out.println("Clicking account management tab");
        Component accountManagementTab = tutorialComponents.accountManagementTab;
        accountManagementTab.click();
        Condition.wait(tutorialConditions.managementTabClicked);

        // Talk to the account guide again
        talkTo(accountGuide, ctx);
//        Condition.wait(tutorialConditions.chatWindowInvalid);

        // Open the door
        System.out.println("Opening door");
        openDoor(bankDoorOutID, ctx);

        System.out.println("Bank area completed!");
    }
}
