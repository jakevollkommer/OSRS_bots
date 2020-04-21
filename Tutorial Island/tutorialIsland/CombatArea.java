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

        return tutorialComponents.instructionsHeader.text().contains("melee") && //TODO need a better check
                tutorialComponents.instructionsHeader.height() == 76;
    }

    @Override
    public void execute() {

        System.out.println("Starting combat area");

        // Talk to vanakka
        Npc vanakka = getNpcWithID(vanakkaID, ctx);
        talkTo(vanakka, ctx);
        continueChat(ctx);
        boolean tabReady = Condition.wait(tutorialConditions.tabReady,300, 15);
        while (!tabReady) {
            continueChat(ctx);
            tabReady = Condition.wait(tutorialConditions.tabReady,300, 15);
        }

        // Click equipment tab
        System.out.println("Clicking equipment tab");
        Component equipmentTab = tutorialComponents.equipmentTab;
        equipmentTab.click();
        boolean tabClicked = Condition.wait(tutorialConditions.equipmentTabClicked, 300, 15);
        while (!tabClicked) {
            equipmentTab.click();
            tabClicked = Condition.wait(tutorialConditions.equipmentTabClicked, 300, 15);
        }

        // Open worn equipment
        System.out.println("Opening worn equipment");
        Component wornEquipment = tutorialComponents.wornEquipment;
        wornEquipment.click();
        boolean equipmentClicked = Condition.wait(tutorialConditions.wornEquipmentClicked, 300, 15);
        while (!equipmentClicked) {
            wornEquipment.click();
            equipmentClicked = Condition.wait(tutorialConditions.wornEquipmentClicked, 300, 15);
        }

        // Wield bronze dagger
        System.out.println("Wielding bronze dagger");
        Item bronzeDagger = getItemFromInventory(bronzeDaggerID, ctx);
        bronzeDagger.interact("Equip");
        boolean daggerEquipped = Condition.wait(tutorialConditions.daggerEquipped, 300, 15);
        while (!daggerEquipped) {
            bronzeDagger.interact("Equip");
            daggerEquipped = Condition.wait(tutorialConditions.daggerEquipped, 300, 15);
        }

        // X out the interface
        System.out.println("Closing equipment");
        Component equipmentX = tutorialComponents.equipmentX;
        equipmentX.click();
        boolean interfaceClosed = Condition.wait(tutorialConditions.equipmentInterfaceInvalid, 300, 15);
        while (!interfaceClosed) {
            equipmentX.click();
            interfaceClosed = Condition.wait(tutorialConditions.equipmentInterfaceInvalid, 300, 15);
        }

        // Talk to vannaka again
        talkTo(vanakka, ctx);
        continueChat(ctx);
        boolean chatInvalid = Condition.wait(tutorialConditions.readyToRange, 300, 15);
        while (!chatInvalid) {
            talkTo(vanakka, ctx);
            continueChat(ctx);
            chatInvalid = Condition.wait(tutorialConditions.readyToRange, 300, 15);
        }

        // Equip bronze sword and wooden shield
        System.out.println("Equipping sword and shield");
        Item bronzeSword = getItemFromInventory(bronzeSwordID, ctx);
        Item woodShield = getItemFromInventory(woodShieldID, ctx);
        bronzeSword.interact("Wield");
        randomSleep(500, 1000);
        woodShield.interact("Wield");
        tabReady = Condition.wait(tutorialConditions.tabReady, 300, 15);
        while (!tabReady) {
            bronzeSword.interact("Wield");
            randomSleep(500, 1000);
            woodShield.interact("Wield");
            tabReady = Condition.wait(tutorialConditions.tabReady, 300, 10);
        }

        // Click combat tab
        System.out.println("Clicking combat tab");
        Component combatTab = tutorialComponents.combatTab;
        combatTab.click();
        tabClicked = Condition.wait(tutorialConditions.combatTabClicked, 300, 15);
        while (!tabClicked) {
            combatTab.click();
            tabClicked = Condition.wait(tutorialConditions.combatTabClicked, 300, 15);
        }
        // intstructions header "combat interface"

        // Open the gate
        System.out.println("Opening gate");
        openDoor(true, RAT_DOOR, combatGateID, ctx);

        // Kill a rat
        System.out.println("Killing a rat");
        attack(giantRatID, ctx);

        // Open the gate
        System.out.println("Opening gate");
        openDoor(true, VANNAKA_DOOR, combatGateID, ctx);

        // Talk to vanakka
        talkTo(vanakka, ctx);
        continueChat(ctx);
        boolean rangeReady = Condition.wait(tutorialConditions.rangeReady, 300, 15);
        while (!rangeReady) {
            continueChat(ctx);
            rangeReady = Condition.wait(tutorialConditions.rangeReady, 300, 15);
        }

        // Equip bow and arrows
        System.out.println("Equipping bow and arrows");
        Component inventoryTab = tutorialComponents.inventoryTab;
        inventoryTab.click();
        Item bow = getItemFromInventory(bowID, ctx);
        randomSleep(500, 1000);
        Item arrows = getItemFromInventory(arrowID, ctx);
        bow.interact("Wield");
        arrows.interact("Wield");

        // Kill a rat
        System.out.println("Killing a rat");
        attack(giantRatID, ctx);

        // Run to the end of the tunnel
        ctx.movement.findPath(COMBAT_LADDER_AREA.getRandomTile()).traverse();
        Condition.wait(tutorialConditions.notInMotion);

        // Climb up the ladder
        climbLadder(ladderUpID, LADDER_DOWN, BANK_LADDER, ctx);

        System.out.println("Combat area complete!");
    }
}
