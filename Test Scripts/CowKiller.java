package tutorial;


import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

import java.awt.*;
import java.util.ArrayList;

@Script.Manifest(name="Cow Killer", description="Tutorial", properties="client=4; author=Chris; topic=999;")

public class CowKiller extends PollingScript<ClientContext> implements PaintListener
{
    ArrayList<Task> tasks = new ArrayList<Task>();
    @Override
    public void start() {
        System.out.println("Hello World");
        Fight fight = new Fight(ctx);
        Loot loot = new Loot(ctx);
        tasks.add(fight);
        tasks.add(loot);
    }

    @Override
    public void poll() {
        for(Task t : tasks) {
            if(t.activate()) {
                t.execute();
            }
        }
    }

    @Override
    public void repaint(Graphics graphics) {

    }
}