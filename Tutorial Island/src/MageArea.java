package tutorialIsland;

import org.powerbot.script.rt4.ClientContext;

public class MageArea extends Task {
    public MageArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        /*
        RUN TO MAGE
        TALK TO MAGE INSTRUCTOR (mageInstructorID)
        CLICK MAGE TAB (mageTab)
        TALK TO MAGE INSTRUCTOR
        WIND STRIKE -> CHICKEN (windStrike, chickenID)
        TALK TO MAGE INSTRUCTOR
        SELECT YES
        continueChat()
        SELECT "No, I'm not planning to do that."
        continueChat()
        */
    }
}
