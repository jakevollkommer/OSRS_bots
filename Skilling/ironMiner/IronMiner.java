package ironMiner;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.awt.*;
import java.util.ArrayList;

@Script.Manifest(name="Iron Miner", description="Mines iron and banks", properties="client=4; author=Jake; topic=999;")

public class IronMiner extends PollingScript<ClientContext> implements PaintListener
{
    ArrayList<Task> tasks = new ArrayList<>();
    @Override
    public void start() {
        tasks.add(new BankingTask(ctx));
        tasks.add(new MiningTask(ctx));
        tasks.add(new WalkToBankTask(ctx));
        tasks.add(new WalkToMineTask(ctx));
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