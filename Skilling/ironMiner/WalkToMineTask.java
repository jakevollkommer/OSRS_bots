package ironMiner;

import org.powerbot.script.Area;
import org.powerbot.script.rt4.ClientContext;

import static ironMiner.HelperMethods.pathToArea;
import static ironMiner.HelperMethods.playerInArea;
import static ironMiner.MiningConstants.*;

public class WalkToMineTask extends Task {

    public WalkToMineTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.inventory.isFull() && playerInArea(bankArea, ctx);
    }

    @Override
    public void execute() {
        System.out.println("Walking to mine");

        pathToArea(new Area(area4), ctx);
        pathToArea(new Area(area3), ctx);
        pathToArea(new Area(area2), ctx);
        pathToArea(new Area(area1), ctx);
        pathToArea(new Area(miningArea), ctx);
    }

}
