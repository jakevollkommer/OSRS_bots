package tutorial;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.Npc;

import java.util.concurrent.Callable;

public class Fight extends Task {

    public Fight(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        int currHp = ctx.skills.level(Constants.SKILLS_HITPOINTS);
        int hpLevel = ctx.skills.realLevel(Constants.SKILLS_HITPOINTS);
        double healthPercent = currHp / hpLevel;
        return !ctx.players.local().healthBarVisible() &&
                healthPercent >= .35 &&
                !ctx.players.local().interacting().valid();
    }

    @Override
    public void execute() {
        int[] cowIds = {2790, 2791, 2793};

        Filter<Npc> filter = new Filter<Npc>() {
            @Override
            public boolean accept(Npc npc) {
                return !npc.healthBarVisible();
            }
        };
        Npc cow = ctx.npcs.select().id(cowIds).nearest().select(filter).poll();

        if(ctx.players.local().healthBarVisible() == false) {
            int currHp = ctx.skills.level(Constants.SKILLS_HITPOINTS);
            int hpLevel = ctx.skills.realLevel(Constants.SKILLS_HITPOINTS);
            double healthPercent = currHp / hpLevel;
            if(healthPercent >= .35) {
                if(cow.inViewport()) {
                    cow.interact("Attack");
                    Callable<Boolean> booleanCallable = new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.players.local().interacting().valid() &&
                                    !ctx.players.local().inMotion();
                        }
                    };
                    Condition.wait(booleanCallable, 100, 25);
                } else {
                    ctx.camera.turnTo(cow);
                }
            }
        } else {
            if(ctx.players.local().healthPercent() < .35) {

            }
        }
    }
}