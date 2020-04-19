package tutorialIsland;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

import java.util.concurrent.Callable;

public class CreateCharacter extends Task {
    Component availability  = ctx.widgets.component(558, 12);
    Component suggestedName = ctx.widgets.component(558, 15);
    Component setName       = ctx.widgets.component(558, 18);
    Component acceptButton  = ctx.widgets.component(269, 100);

    public CreateCharacter(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return availability.valid();
    }

    @Override
    public void execute() {
        System.out.println("Setting a character name");
        // Generate character name
        String generatedName = "test"; // TODO generate random name

        // Try the name
        ctx.chat.sendInput(generatedName + "\n");
        Callable<Boolean> availabilityChecked = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return availability.text().contains("not");
            }
        };
        Condition.wait(availabilityChecked, 1000, 10);

        // If name is not available, use a default name
        suggestedName.click();
        Callable<Boolean> nameAvailable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return availability.text().contains("Great!");
            }
        };
        Condition.wait(nameAvailable, 1000, 10);

        // Set the name
        setName.click();
        Callable<Boolean> nameSet = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !setName.valid() && acceptButton.valid();
            }
        };
        Condition.wait(nameSet, 1000, 10);

        System.out.println("Name has been set!");

        // Accept the default character build TODO (maybe change outfit in future)
        acceptButton.click();
        Callable<Boolean> characterBuild = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !acceptButton.valid();
            }
        };
        Condition.wait(characterBuild, 100, 10);
        System.out.println("Character built!");
    }
}
