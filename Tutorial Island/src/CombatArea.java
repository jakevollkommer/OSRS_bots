package tutorialIsland;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;

public class CombatArea extends Task {
    TutorialComponents tutorialComponents = new TutorialComponents(ctx);
    TutorialConditions tutorialConditions = new TutorialConditions(ctx);

    public CombatArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {

        System.out.println("Starting combat area");

        // Talk to vanakka
        Npc vanakka = getNpcWithID(vanakkaID, ctx);
        talkTo(vanakka, ctx);
        continueChat(ctx);
//        Condition.wait(tutorialConditions.tabReady);

        // Click equipment tab
        System.out.println("Clicking equipment tab");
        Component equipmentTab = tutorialComponents.equipmentTab;
        equipmentTab.click();
//        Condition.wait(tutorialConditions.equipmentTabClicked);

        // Open worn equipment
        System.out.println("Opening worn equipment");
        Component wornEquipment = tutorialComponents.wornEquipment;
//        Condition.wait(tutorialConditions.wornEquipmentClicked);
        // Wear bronze dagger
        Item bronzeDagger = getItemFromInventory(bronzeDaggerID, ctx);
        bronzeDagger.interact("Wield");

        // X out the interface TODO
        System.out.println("Closing equipment");

        // Talk to vanakka again
        talkTo(vanakka, ctx);
        continueChat(ctx);

        // Equip bronze sword and wooden shield
        System.out.println("Equipping sword and shield");
        Item bronzeSword = getItemFromInventory(bronzeSwordID, ctx);
        Item woodShield = getItemFromInventory(woodShieldID, ctx);
        bronzeSword.interact("Wield");
        woodShield.interact("Wear");
//        Condition.wait(tutorialConditions.tabReady);

        // Click combat tab
        System.out.println("Clicking combat tab");
        Component combatTab = tutorialComponents.combatTab;
        combatTab.click();
//        Condition.wait(tutorialConditions.combatTabClicked);

        // Open the gate
        System.out.println("Opening gate");
        openDoor(combatGateID, ctx);
//        Condition.wait(tutorialConditions.notInMotion);

        // Kill a rat
        System.out.println("Killing a rat");
        Npc rat = getNpcWithID(giantRatID, ctx);
        // TODO kill the rat

        // Open the gate
        System.out.println("Opening gate");
        openDoor(combatGateID, ctx);
//        Condition.wait(tutorialConditions.notInMotion);

        // Talk to vanakka
        talkTo(vanakka, ctx);
        continueChat(ctx);
        Condition.wait(tutorialConditions.tabReady);

        // Equip bow and arrows
        System.out.println("Equipping bow and arrows");
        Component inventoryTab = tutorialComponents.inventoryTab;
        inventoryTab.click();
        Item bow = getItemFromInventory(bowID, ctx);
        Item arrows = getItemFromInventory(arrowID, ctx);
//        bow.interact("Wield");
//        arrows.interact("Equip");

        // Kill a rat
        System.out.println("Killing a rat");
        // TODO kill the rat

        // Run to the end of the tunnel
        // TODO pathing

        // Climb up the ladder
        climbLadder(ladderUpID, LADDER_DOWN, ctx);

        System.out.println("Combat area complete!");
    }
}
