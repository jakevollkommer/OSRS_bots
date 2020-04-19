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
        return tutorialComponents.instructionsHeader.text().contains("Bank") && //TODO need a better check
                tutorialComponents.instructionsHeader.height() == 84;
    }

    @Override
    public void execute() {

        System.out.println("Starting bank area");

        // Run to bank
        // TODO pathing

        // Use the bank booth
        System.out.println("Using bank booth");
        GameObject bankBooth = getNearestGameObject(bankBoothID, ctx);
        bankBooth.interact("Use");
        boolean bankOpen = Condition.wait(tutorialConditions.bankOpen, 300, 15);
        while (!bankOpen) {
            bankBooth.interact("Use");
            bankOpen = Condition.wait(tutorialConditions.bankOpen, 300, 15);
        }
        randomSleep(600, 1200);
        tutorialComponents.bankX.click();
        boolean bankClosed = Condition.wait(tutorialConditions.bankClosed, 300, 15);
        while (!bankClosed) {
            tutorialComponents.bankX.click();
            bankClosed = Condition.wait(tutorialConditions.bankOpen, 300, 15);
        }

        // Use the poll booth
        System.out.println("Using poll booth");
        GameObject pollBooth = getNearestGameObject(pollBoothID, ctx);
        pollBooth.interact("Use");
        continueChat(ctx);
        tutorialComponents.continueItem.click();
        boolean pollBoothOpen = Condition.wait(tutorialConditions.pollBoothOpen, 300, 15);
        while (!pollBoothOpen) {
            pollBooth.interact("Use");
            continueChat(ctx);
            tutorialComponents.continueItem.click();
            pollBoothOpen = Condition.wait(tutorialConditions.pollBoothOpen, 300, 15);
        }
        tutorialComponents.pollX.click();
        boolean pollBoothClosed = Condition.wait(tutorialConditions.pollBoothClosed, 300, 15);
        while (!pollBoothClosed) {
            tutorialComponents.pollX.click();
            pollBoothClosed = Condition.wait(tutorialConditions.pollBoothClosed, 300, 15);
        }

        // Open the door
        System.out.println("Opening door");
        openDoor(true, MANAGEMENT_DOOR, bankDoorInID, ctx);

        // Talk to the account guide
        Npc accountGuide = getNpcWithID(accountGuideID, ctx);
        talkTo(accountGuide, ctx);
        continueChat(ctx);
        boolean tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        while (!tabReady) {
            continueChat(ctx);
            tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        }

        // Click the account management tab
        System.out.println("Clicking account management tab");
        Component accountManagementTab = tutorialComponents.accountManagementTab;
        accountManagementTab.click();
        boolean tabClicked = Condition.wait(tutorialConditions.managementTabClicked, 300, 15);
        // inst header "Account Management"
        while (!tabClicked) {
            accountManagementTab.click();
            tabClicked = Condition.wait(tutorialConditions.tabReady, 300, 15);
        }

        // Talk to the account guide again
        talkTo(accountGuide, ctx);
        continueChat(ctx);
        boolean chatInvalid = Condition.wait(tutorialConditions.chatWindowInvalid, 300, 15);
        while (!chatInvalid) {
            continueChat(ctx);
            chatInvalid = Condition.wait(tutorialConditions.chatWindowInvalid, 300, 15);
        }

        // Open the door
        System.out.println("Opening door");
        openDoor(true, POST_MANAGEMENT_DOOR, bankDoorOutID, ctx);

        System.out.println("Bank area completed!");
    }
}
