package tutorialIsland;

import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Path;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.ArrayList;

import static tutorialIsland.HelperMethods.*;
import static tutorialIsland.TutorialConstants.*;
import static tutorialIsland.TutorialConstants.gateID;

@Script.Manifest(name="Tutorial Island", description="Tutorial", properties="client=4; author=Chris; topic=999;")

public class TutorialIsland extends PollingScript<ClientContext> implements PaintListener
{
    ArrayList<Task> tasks = new ArrayList<>();
    @Override
    public void start() {
        System.out.println("Tutorial Island");
        tasks.add(new CreateCharacter(ctx));
        tasks.add(new StartingRoom(ctx));
        tasks.add(new ResourcesArea(ctx));
        tasks.add(new CookingArea(ctx));
        tasks.add(new QuestArea(ctx));
        tasks.add(new PrayerArea(ctx));
        tasks.add(new MiningArea(ctx));
        tasks.add(new CombatArea(ctx));
        tasks.add(new BankArea(ctx));
        tasks.add(new MageArea(ctx));
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