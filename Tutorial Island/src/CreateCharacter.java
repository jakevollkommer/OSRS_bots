package tutorial;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.IdQuery;

import java.util.concurrent.Callable;

public class CreateCharacter extends Task{
    Component availability = ctx.widgets.component(558, 12);
    Component suggestedName = ctx.widgets.component(558, 15);

    public CreateCharacter(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return availability.valid();
    }

    @Override
    public void execute() {
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
        Component setName = ctx.widgets.component(558, 18);
        setName.click();
        Callable<Boolean> nameSet = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !setName.valid();
            }
        };
        Condition.wait(nameSet, 1000, 10);

        // Accept the default character build TODO (maybe change outfit in future)
        Component acceptButton = ctx.widgets.component(269, 100);
        acceptButton.click();
        Callable<Boolean> characterBuild = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !acceptButton.valid();
            }
        };
        Condition.wait(characterBuild, 100, 10);
    }
}
