package tutorial;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Loot extends Task {
    int cowhideID = 1739;
    Tile cowTile = Tile.NIL;
    Area cowLootArea = null;
    ArrayList<Area> lootAreas = new ArrayList<Area>();

    public Loot(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        if(ctx.players.local().interacting().valid() &&
                !ctx.players.local().interacting().tile().equals(cowTile) &&
                !ctx.players.local().interacting().inMotion()) {
            cowTile = ctx.players.local().interacting().tile();
            cowLootArea = new Area(
                    new Tile(cowTile.x() - 2, cowTile.y() - 2, cowTile.floor()),
                    new Tile(cowTile.x() + 2, cowTile.y() + 2, cowTile.floor())
            );
            lootAreas.add(cowLootArea);
        }

        boolean lootExists = false;
        for(Area a : lootAreas) {
            if(!ctx.groundItems.select().within(a).id(cowhideID).isEmpty()) {
                lootExists = true;
                break;
            }
        }

        System.out.println("We have " + lootAreas.size() + " areas in our list");

        return cowLootArea!= null && lootExists &&
                !ctx.players.local().interacting().valid() &&
                !ctx.inventory.isFull();
    }

    @Override
    public void execute() {
        ArrayList<Area> toRemove = new ArrayList<Area>();
        for(Area a : lootAreas) {
            if(!ctx.groundItems.select().within(a).id(cowhideID).isEmpty()) {
                toRemove.add(a);
            }
        }
        System.out.println("We have removed" + toRemove.size() + " areas in our list");
        lootAreas.removeAll(toRemove);

        GroundItem cowhide = ctx.groundItems.select().within(cowLootArea).id(cowhideID).poll();
        cowhide.interact("Take", cowhide.name());
        Callable<Boolean> booleanCallable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !cowhide.valid();
            }
        };
        Condition.wait(booleanCallable, 300, 5);
    }
}
