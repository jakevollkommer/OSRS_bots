package ironMiner;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.GameObject;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static ironMiner.HelperMethods.*;
import static ironMiner.MiningConstants.*;

public class MiningTask extends Task {

    public MiningTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.inventory.isFull() && playerInArea(miningArea, ctx);
    }

    @Override
    public void execute() {
        System.out.println("Mining");
        int[] copperRockIDs = {11161, 10943};

        mine(copperRockIDs, 0);
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() == -1;
            }
        }, 500, 10);
    }

    public boolean mine(int[] rockIDs, int oreID) {
        GameObject rock = ctx.objects.select().id(rockIDs).nearest().poll();
        rock.interact("Mine");
        return Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() == MINING_ANIMATION;
            }
        }, 500, 5);
    }
}
