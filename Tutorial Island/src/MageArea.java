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
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Starting mage area");

        // Run to the mage
        // TODO pathing

        // Talk to mage instructor
        Npc mageInstructor = getNpcWithID(mageInstructorID, ctx);
        talkTo(mageInstructor, ctx);
        continueChat(ctx);
//        Condition.wait(tutorialConditions.tabReady);

        // Click mage tab
        System.out.println("Clicking mage tab");
        Component mageTab = tutorialComponents.mageTab;
        mageTab.click();
//        Condition.wait(tutorialConditions.mageTabClicked);

        // Talk to instructor
        talkTo(mageInstructor, ctx);
        continueChat(ctx);

        // Wind strike a chicken
        System.out.println("Wind striking chicken");
        Npc chicken = getNpcWithID(chickenID, ctx); // TODO nearest?
        Component windStrike = tutorialComponents.windStrike;
        mageTab.click();
        windStrike.click();
        chicken.interact("Cast", chicken.name()); // TODO might be diff action name

        // Talk to instructor
        talkTo(mageInstructor, ctx);
        continueChat(ctx);
        Condition.wait(tutorialConditions.chatOptions, 400, 10);

        // Tell him yes TODO what
        System.out.println("Tell him yes");
        ChatOption yesOption = ctx.chat.select().text("Yes").poll();
        yesOption.select();
        Condition.wait(tutorialConditions.canContinue, 400, 10);
        continueChat(ctx);

        // Not ironman
        System.out.println("Not ironman");
        String option = "No, I'm not planning to do that.";
        Condition.wait(tutorialConditions.chatOptions, 400, 10);
        ChatOption notIronmanOption = ctx.chat.select().text(option).poll();
        notIronmanOption.select();
        Condition.wait(tutorialConditions.canContinue, 400, 10);
        continueChat(ctx);

        System.out.println("Mage area completed!");
    }
}
