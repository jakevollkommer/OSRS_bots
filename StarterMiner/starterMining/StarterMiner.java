package starterMining;

import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Path;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.ArrayList;

@Script.Manifest(name="Starter Miner", description="Starter Miner", properties="client=4; author=Chris; topic=999;")

public class StarterMiner extends PollingScript<ClientContext> implements PaintListener {

	ArrayList<Task> tasks = new ArrayList<>();
	@Override
	public void start() {
		tasks.add(new DropInventory(ctx));
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
