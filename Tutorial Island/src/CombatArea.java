package tutorialIsland;

import org.powerbot.script.rt4.ClientContext;

public class CombatArea extends Task {

    public CombatArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        /*
        TALK TO VANAKKA (vanakkaID)
        CLICK EQUIPMENT TAB (equipmentTab)
        OPEN WORN EQUIPMENT (wornEquipment)
        CLICK BRONZE DAGGER (bronzeDaggerID)
        X OUT INTERFACE (idk i think find the widget?)
        TALK TO VANAKKA
        EQUIP BRONZE SWORD AND WOOD SHIELD (bronzeSwordID, woodenShieldID)
        CLICK COMBAT TAB (combatTab)
        OPEN COMBAT GATE (combatGateID)
        ATTACK RAT (giantRatID) (until dead)
        OPEN COMBAT GATE
        TALK TO VANAKKA
        CLICK INVENTORY TAB
        EQUIP BOW AND ARROWS (bowID, arrowID)
        ATTACK RAT (until dead)
        RUN TO END OF TUNNEL
        CLIMB LADDER (ladderUpID)
        */
    }
}
