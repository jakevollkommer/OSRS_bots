package tutorialIsland;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ChatOption;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Npc;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;

public class MageArea extends Task {
    TutorialComponents tutorialComponents = new TutorialComponents(ctx);
    TutorialConditions tutorialConditions = new TutorialConditions(ctx);

    public MageArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return tutorialComponents.instructionsHeader.text().contains("magic instructor") && //TODO need a better check
                tutorialComponents.instructionsHeader.height() == 68;
    }

    @Override
    public void execute() {
        System.out.println("Starting mage area");

        // Run to the mage
        pathToArea(MAGE_AREA, ctx);

        // Talk to mage instructor
        Npc mageInstructor = getNpcWithID(mageInstructorID, ctx);
        talkTo(mageInstructor, ctx);
        continueChat(ctx);
        boolean tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        while (!tabReady) {
            continueChat(ctx);
            tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        }

        // Click mage tab
        System.out.println("Clicking mage tab");
        Component mageTab = tutorialComponents.mageTab;
        mageTab.click();
        boolean tabClicked = Condition.wait(tutorialConditions.mageTabClicked, 300, 15);
        while (!tabClicked) {
            mageTab.click();
            tabClicked = Condition.wait(tutorialConditions.mageTabClicked, 300, 15);
        }
        // inst header "magic interface"

        // Talk to instructor
        talkTo(mageInstructor, ctx);
        continueChat(ctx);
        boolean chatInvalid = Condition.wait(tutorialConditions.chatWindowInvalid, 300, 15);
        while (!chatInvalid) {
            continueChat(ctx);
            chatInvalid = Condition.wait(tutorialConditions.chatWindowInvalid, 300, 15);
        }
        // inst header "runes"

        // TODO NOT WORKING
        // Wind strike a chicken
        System.out.println("Wind striking chicken");
        Npc chicken = getNpcWithID(chickenID, ctx); // TODO nearest?
        ctx.camera.turnTo(chicken);
        Component windStrike = tutorialComponents.windStrike;
        windStrike.click();
        randomSleep(400,1200);
        chicken.interact("Cast", chicken.name());
        boolean spellCasted = Condition.wait(tutorialConditions.spellCasted, 300, 15);
        while (!spellCasted) {
            ctx.camera.turnTo(chicken);
            windStrike.click();
            randomSleep(400,1200);
            chicken.interact("Cast", chicken.name());
            spellCasted = Condition.wait(tutorialConditions.spellCasted, 300, 15);
        }

        // Talk to instructor
        talkTo(mageInstructor, ctx);
        continueChat(ctx);
        boolean chatOptions = Condition.wait(tutorialConditions.chatOptions, 400, 10);
        while (!chatOptions) {
            continueChat(ctx);
            chatOptions = Condition.wait(tutorialConditions.chatOptions, 400, 10);
        }

        // Tell him yes
        System.out.println("Tell him yes");
        ChatOption yesOption = ctx.chat.select().text("Yes").poll();
        yesOption.select();
        boolean canContinue = Condition.wait(tutorialConditions.canContinue, 400, 10);
        while (!canContinue) {
            yesOption.select();
            canContinue = Condition.wait(tutorialConditions.canContinue, 400, 10);
        }

        // Not ironman
        continueChat(ctx);
        System.out.println("Not ironman");
        String option = "No, I'm not planning to do that.";
        chatOptions = Condition.wait(tutorialConditions.chatOptions, 400, 10);
        while (!chatOptions) {
            continueChat(ctx);
        }
        ChatOption notIronmanOption = ctx.chat.select().text(option).poll();
        notIronmanOption.select();
        canContinue = Condition.wait(tutorialConditions.canContinue, 400, 10);
        while (!canContinue) {
            notIronmanOption.select();
            canContinue = Condition.wait(tutorialConditions.canContinue, 400, 10);
        }
        continueChat(ctx);
        tutorialComponents.continueItem.click();

        chatOptions = Condition.wait(tutorialConditions.chatOptions, 400, 10);
        while (!chatOptions) {
            continueChat(ctx);
            tutorialComponents.continueItem.click();
        }
        ChatOption mainland = ctx.chat.select().text("Yes.").poll();
        mainland.select();
        System.out.println("Mage area completed!");
    }
}
