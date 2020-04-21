package ironMiner;

import org.powerbot.script.Area;
import org.powerbot.script.rt4.ClientContext;

import static ironMiner.HelperMethods.*;
import static ironMiner.MiningConstants.*;

public class WalkToBankTask extends Task {

    public WalkToBankTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull() && playerInArea(miningArea, ctx);
    }

    @Override
    public void execute() {
        System.out.println("Walking to Bank");

        pathToArea(new Area(area1), ctx);
        pathToArea(new Area(area2), ctx);
        pathToArea(new Area(area3), ctx);
        pathToArea(new Area(area4), ctx);
        pathToArea(new Area(bankArea), ctx);
    }

}
