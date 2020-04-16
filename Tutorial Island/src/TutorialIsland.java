package tutorial;


import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

@Script.Manifest(name="Tutorial Island", description="Tutorial", properties="client=4; author=Chris; topic=999;")

public class TutorialIsland extends PollingScript<ClientContext> implements PaintListener
{
    ArrayList<Task> tasks = new ArrayList<Task>();
    @Override
    public void start() {
        System.out.println("Tutorial Island");
        CreateCharacter createCharacter = new CreateCharacter(ctx);
        TutorialCompleter tutorialCompleter = new TutorialCompleter(ctx);
        tasks.add(createCharacter);
        tasks.add(tutorialCompleter);
    }

    @Override
    public void poll() {
        for(Task t : tasks) {
            if(t.activate()) {
                t.execute();
            }
        }

        //System.out.println("Script Completed");
        Condition.sleep(2000);
    }

    @Override
    public void repaint(Graphics graphics) {

    }
}