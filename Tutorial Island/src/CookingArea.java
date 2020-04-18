package tutorialIsland;

import org.powerbot.script.rt4.ClientContext;

public class CookingArea extends Task {
    public CookingArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        /*
        OPEN GATE (gateID)
        OPEN COOKING DOOR (cookingDoorInID)
        TALK TO MASTER CHEF (chefID)
        USE FLOUR ON BUCKET WATER (flourID, bucketWaterID)
        USE DOUGH ON RANGE (doughID, rangeID)
        IF SUCCESSFUL WE HAVE BREAD (breadID)
        OPEN DOOR (cookingDoorOutID)
        */
    }
}
