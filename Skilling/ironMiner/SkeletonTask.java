package ironMiner;

import org.powerbot.script.rt4.ClientContext;

public class SkeletonTask extends Task {

    public SkeletonTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return true;
    }

    @Override
    public void execute() {
    }

}
