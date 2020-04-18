package tutorialIsland;

import org.powerbot.script.rt4.ClientContext;

public class MiningArea extends Task {
    public MiningArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        /*
        TALK TO MINING INSTRUCTOR (miningID)
        MINE TIN (tinRockID, tinOreID)
        MINE COPPER (copperRockID, copperOreID)
        SMELT FURNACE (furnaceID, bronzeBarID)
        TALK TO MINING INSTRUCTOR
        SMITH ANVIL (anvilID)
        CLICK BRONZE DAGGER COMPONENT (bronzeDagger, bronzeDaggerID)
        OPEN METAL GATE (metalGateID)
        */
    }
}
